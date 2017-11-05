package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.File;
import java.io.IOException;

import com.zte.ums.uep.psl.jsch.jcraft.sftp.api.SftpClientException;

/**
 * <p>文件名称: SftpClientImpl</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2005-2015</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-12-12</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author ChenDuoduo_10087118
 */
public class SftpClientImpl implements FTPClient
{
    private com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl sftpClient;
    
    private String displayedHomePath = null;//显示在FTP客户端(如FileZilla)上的初始目录
    
    @Override
    public void connect(String serverIP, String userName, String password) throws IOException
    {
        try
        {
            sftpClient = new com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl(serverIP, 22, 60000, userName, password);
            initDisplayedHomePath();
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }
    
    private void initDisplayedHomePath() throws IOException
    {
        try
        {
            String homePath = sftpClient.getHomePath();
            homePath = homePath.endsWith("/") ? homePath : homePath + "/";
            homePath = "//".equals(homePath) ? "/" : homePath;//windows下为"//"
            
            displayedHomePath = homePath;
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void disConnect() throws IOException
    {
        if (this.sftpClient != null)
        {
            try
            {
                this.sftpClient.quit();
                this.sftpClient = null;
            }
            catch(SftpClientException e)
            {
                throw new IOException(e);
            }
        }
    }

    @Override
    public String getDisplayedRootDir() throws IOException
    {
        return displayedHomePath;
    }

    @Override
    public void cd(String dir) throws IOException
    {
        try
        {
            sftpClient.chdir(dir);
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public String pwd() throws IOException
    {
        String pwd;
        try
        {
            pwd = sftpClient.pwd();//在平台的这个控件中，SftpClientImpl.pwd()不是显示在FTP客户端(如FileZilla)上的初始目录。初始登录时，pwd永远是"/"
            pwd = pwd.startsWith("/") ? pwd.substring(1) : pwd;
            
            return displayedHomePath + pwd;
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void mkDirs(String dirs) throws IOException
    {
        String[] arrDirs = dirs.split("/");
        String pwd = pwd();
        
        for(String dir : arrDirs)
        {
            mkDir(dir);
            cd(dir);
        }
        
        cd(pwd);
    }
    
    private void mkDir(String dir) throws IOException
    {
        try
        {
            sftpClient.mkdir(dir);//SftpClientImpl.mkdir可以创建多层目录
            sftpClient.dir(dir, false);//SftpClientImpl.dir可以检查目录是否存在
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void delDir(String dir) throws IOException
    {
        doDelete(dir);
        
        try
        {
            sftpClient.dir(dir, false);//SftpClientImpl.dir可以检查目录是否存在
        }
        catch(SftpClientException e)
        {
            return;
        }
        
        throw new IOException("dir still exists after execute cmd:" + dir);
    }

    @Override
    public void delFile(String file) throws IOException
    {
        doDelete(file);
    }
    
    private void doDelete(String dir) throws IOException
    {
        try
        {
            sftpClient.delete(dir);//delete API - windows下可以删除非空目录；soliars下只能是空目录，否则抛异常
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void upload(String remotePath, String localFile) throws IOException
    {
        String localFileName = new File(localFile).getName();
        String remoteFile = remotePath.endsWith("/") ? remotePath + localFileName : remotePath + "/" + localFileName;
        try
        {
            sftpClient.putFile(localFile, remoteFile);//putFile API支持目录不存在时自动创建目录
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

}

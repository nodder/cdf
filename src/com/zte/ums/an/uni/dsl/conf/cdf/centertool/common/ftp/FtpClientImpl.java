package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * <p>文件名称: FtpClientImpl</p>
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
public class FtpClientImpl implements FTPClient
{
    private FtpClient ftpClient;
    private String displayedHomePath;//显示在FTP客户端(如FileZilla)上的初始目录

    @Override
    public void connect(String serverIP, String userName, String password) throws IOException
    {
        this.ftpClient = new FtpClient();
        this.ftpClient.setConnectTimeout(Integer.MAX_VALUE - 1);
        this.ftpClient.openServer(serverIP);
        this.ftpClient.login(userName, password);
        
        this.displayedHomePath = getDisplayedHomePath();
    }
    
    private String getDisplayedHomePath() throws IOException
    {
        String displayedHomePath = ftpClient.pwd();
        if(!displayedHomePath.endsWith("/"))
        {
            displayedHomePath = displayedHomePath + "/";
        }
        
        return displayedHomePath;
    }

    @Override
    public void disConnect() throws IOException
    {
        if (this.ftpClient != null)
        {
            this.ftpClient.closeServer();
            this.ftpClient = null;
        }
    }

    @Override
    public void cd(String dir) throws IOException
    {
        ftpClient.cd(dir);
    }

    @Override
    public String pwd() throws IOException
    {
        String pwd = ftpClient.pwd();
        pwd = pwd.endsWith("/") ? pwd : pwd + "/";
        
        return pwd;
    }

    /** dirs只能为相对路径,例如AAA或者AAA/SUBAAA或者AAA/SUBAAA/ */
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
        ftpClient.sendServer("XMKD " + dir + "\r\n");
        ftpClient.readServerResponse();
    }

    //删除目录，只能在当前目录下创建一个目录，无法递归
    @Override
    public void delDir(String dir) throws IOException
    {
        ftpClient.sendServer("XRMD " + dir + "\r\n");
        ftpClient.readServerResponse();
        
        try
        {
            cd(dir);
        }
        catch(IOException ex)
        {
            return;
        }
        
        throw new IOException("dir still exists after execute cmd:" + dir);
    }

    @Override
    public void delFile(String dir) throws IOException
    {
        ftpClient.sendServer("DELE " + dir + "\r\n");
        ftpClient.readServerResponse();
    }

    @Override
    public void upload(String remotePath, String localFile) throws IOException
    {
        String pwd = ftpClient.pwd();
        
        TelnetOutputStream out = null;
        BufferedInputStream bis = null;

        try
        {
            cd(remotePath);
            File file = new File(localFile);
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024000];
            int read;
            out = this.ftpClient.put(file.getName());
            while((read = bis.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
        }
        finally
        {
            closeInputStream(bis);
            closeOutputStream(out);
            ftpClient.cd(pwd);
        }
    }
    
    private static void closeInputStream(InputStream is)
    {
        try
        {            
            if(is != null)
            {
                is.close();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private static void closeOutputStream(OutputStream os)
    {
        try
        {
            if(os != null)
            {
                os.close();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String getDisplayedRootDir() throws IOException
    {
        return displayedHomePath;
    }
}

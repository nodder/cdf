package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.IOException;

/**
 * <p>文件名称: FtpConnection</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-22</p>
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

public class FtpConnection implements FTPClient
{
    public static final int PROTOCAL_TYPE_FTP = 0x1000;
    public static final int PROTOCAL_TYPE_SFTP = 0x1001;
    
    private FTPClient ftpClient = null;
    
    /** isSFTP 是否使用SFTP协议，true表示SFTP，false表示FTP */
    public FtpConnection(int protocalType) throws IOException
    {
        this.ftpClient = getFTPClient(protocalType);
    }
    
    private static FTPClient getFTPClient(final int protocalType)
    {
        switch (protocalType)
        {
            case PROTOCAL_TYPE_FTP:
                return new FtpClientImpl();
            case PROTOCAL_TYPE_SFTP:
                return new SftpClientImpl();
            default:
                throw new IllegalArgumentException("invalid protocal type:" + protocalType);
        }
    }
    
    @Override
    public void connect(String serverIP, String userName, String password) throws IOException
    {
        ftpClient.connect(serverIP, userName, password);
    }

    @Override
    public void disConnect() throws IOException
    {
        ftpClient.disConnect();
    }

    @Override
    public String getDisplayedRootDir() throws IOException
    {
        return ftpClient.getDisplayedRootDir();
    }

    @Override
    public void mkDirs(String dir) throws IOException
    {
        ftpClient.mkDirs(dir);
    }

    @Override
    public void delDir(String dir) throws IOException
    {
        ftpClient.delDir(dir);
    }
    
    @Override
    public void cd(String dir) throws IOException
    {
        this.ftpClient.cd(dir);
    }
    
    @Override
    public String pwd() throws IOException
    {
        return this.ftpClient.pwd();
    }
    
    @Override
    public void delFile(String dir) throws IOException
    {
        ftpClient.delFile(dir);
    }
    
    @Override
    public void upload(String remotePath, String localFile) throws IOException
    {
        ftpClient.upload(remotePath, localFile);
    }
}

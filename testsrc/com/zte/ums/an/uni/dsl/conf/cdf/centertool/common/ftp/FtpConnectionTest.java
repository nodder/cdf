package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.uep.psl.jsch.jcraft.sftp.api.SftpClientException;

/**
 * <p>文件名称: FtpConnectionTest</p>
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
public class FtpConnectionTest extends TestCase
{
    private FtpInfo getFtpInfoFromWindows()
    {
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = "10.63.204.112";
        ftpInfo.ftpUser = "webnms";
        ftpInfo.ftpUserPasswd = "webnms";

        return ftpInfo;
    }
    
    private FtpInfo getFtpInfoFromRedHat()
    {
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = "10.63.174.204";
        ftpInfo.ftpUser = "webnms";
        ftpInfo.ftpUserPasswd = "webnms";

        return ftpInfo;
    }

    public static void testSftp() throws IOException, SftpClientException
    {
        //測試需要FTP環境，因此將test方法名改為test，需要測試時打開。

        com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl sftpClient = new com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl("10.63.204.112",
                        22, 60000, "webnms", "swss");
        // 列文件
//        String[] x = sftpClient.dir("/", true);
//        
//        for (String s : x){
//            System.out.println(s);
//        }
//        System.out.println(x.length);
        File tmpFile = new File("test.txt");
        tmpFile.createNewFile();
        sftpClient.putFile(tmpFile.getName(), "/myTest.txt");
    }

//    public void testFtpFromOSWindows() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromWindows();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_FTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/", "AAA", "1.1.1.1");
//    }
//
//    public void testFtpFromOSRedHat() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromRedHat();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_FTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/home/ftpdir/", "AAA", "1.1.1.1");
//    }
    
//    public void testSftpFromOSWindows() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromWindows();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_SFTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, "dddd");
//        
//        doTest(ftpConn, "/", "AAA", "1.1.1.1");
//    }
    
//    public void testSftpFromOSRedHat() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromRedHat();
//        ftpInfo.isSFTP = true;
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_SFTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/home/ftpdir/", "AAA", "1.1.1.1");
//    }
    
    private void doTest(FtpConnection ftpConn, String expectedDisplayedRootDir, String dir1, String dir2) throws IOException, Exception
    {
        assertEquals(expectedDisplayedRootDir, ftpConn.getDisplayedRootDir());
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());

        ftpConn.mkDirs(dir1 + "/" + dir2);
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());

        File tmpFile = createTestFile();
        ftpConn.upload(dir1 + "/" + dir2, tmpFile.getAbsolutePath());
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());
        
        ftpConn.delFile(ftpConn.getDisplayedRootDir() + dir1 + "/" + dir2 + "/" + tmpFile.getName());
        ftpConn.delDir(ftpConn.getDisplayedRootDir() + dir1 + "/" + dir2 + "/");
        ftpConn.delDir(ftpConn.getDisplayedRootDir() + dir1 + "/");
    }
    
    private File createTestFile() throws Exception
    {
        File testFile;
        try
        {
            testFile = new File("tmp.tst");
            testFile.createNewFile();
            return testFile;
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_TEST_FILE_FAIL);
        }
    }
}

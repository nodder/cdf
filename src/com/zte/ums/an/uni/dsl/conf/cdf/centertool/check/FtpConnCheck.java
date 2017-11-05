package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.io.File;
import java.io.IOException;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.SysOutRediction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp.FtpConnection;
import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;

/**
 * <p>文件名称: FtpConnCheck.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-12</p>
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
public class FtpConnCheck implements ICdfCheck
{
    private FtpInfo ftpInfo;
    private FtpConnection conn = null;
    
    public FtpConnCheck(FtpInfo ftpInfo)
    {
        this.ftpInfo = ftpInfo;
    }
    
    public FtpConnCheck()
    {
    }
    
    private static FtpInfo getFtpInfoFromXML()
    {
        CenterToolUtil.changeToSubCollectDir();
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = CollectXmlParser.getInstance().getFTPServerIp();
        ftpInfo.ftpUser = CollectXmlParser.getInstance().getFTPUser();
        ftpInfo.ftpUserPasswd = CollectXmlParser.getInstance().getFTPPassword();
        ftpInfo.ftpRootDir = CollectXmlParser.getInstance().getFTPMainDir();
        ftpInfo.isSFTP = CollectXmlParser.getInstance().isUsingSFTP();
        
        return ftpInfo;
    }
    
    @Override
    public String getName()
    {
        return "sub-collect server FTP";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start sub-collect server FTP test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("Sub-collect server FTP test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        this.ftpInfo = getFtpInfoFromXML();
        return doCheck(true);
    }
    
    public CdfTestResult doCheck(boolean isLocalHost)
    {
        File tmpFile = null;
        try
        {
            this.conn = createFtpConn(ftpInfo);
            
            mkdir();
            tmpFile = createTestFile();
            upload(tmpFile);
            
            if(isLocalHost)
            {
                checkIsLocalHost();
                checkFtpRootDir(tmpFile);
            }

            cleanTestTemp();
            
            if(isLocalHost)
            {
                createAndDeleteInFtpDir();//测试使用当前程序在FTP目录创建文件的权限
            }
            
            return new CdfTestResult(true);
        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
        finally
        {
            closeConn();
            deleteTestTmpFile(tmpFile);
        }
    }

    private void closeConn()
    {
        if(conn != null)
        {
            try
            {
                conn.disConnect();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void deleteTestTmpFile(File tmpFile)
    {
        if(tmpFile != null && tmpFile.exists())
        {
            tmpFile.delete();
        }
    }
    
    private void checkIsLocalHost() throws Exception
    {
        CenterToolUtil.printCheckTile("Check if ftp server IP address is local host");
        boolean isLocalHostIP = CenterToolUtil.isLocalHostIP(this.ftpInfo.ftpServerIp);
        if(isLocalHostIP)
        {
            CenterToolUtil.printSucess();
        }
        else
        {
            throw new Exception(CdfTestResultConst.IPV4_NOT_LOCAL + ":" + this.ftpInfo.ftpServerIp);
        }
    }

    private void checkFtpRootDir(File tmpFile) throws Exception
    {
        CenterToolUtil.printCheckTile("Check ftp root directory");
        File hostAbsFile;

        hostAbsFile = new File(this.ftpInfo.ftpRootDir, "AAA/1.1.1.1/" + tmpFile.getName());

        if(hostAbsFile.exists())
        {
            CenterToolUtil.printSucess();
        }
        else
        {
            throw new Exception(CdfTestResultConst.FTP_ROOTDIR_INVALID + ":" + this.ftpInfo.ftpRootDir);
        }
    }

    private void cleanTestTemp() throws Exception
    {
        delDir();
    }
    
    private void upload(File tmpFile) throws Exception
    {
        CenterToolUtil.printCheckTile("Uploading test");
        
        boolean isSuccess = doUploadWithNoLog(tmpFile);
        
        
        if(isSuccess)
        {
            CenterToolUtil.printSucess();
        }
        else
        {
            throw new Exception(CdfTestResultConst.FTP_UPLOAD_FAIL);
        }
    }

    private boolean doUploadWithNoLog(File tmpFile)
    {
        SysOutRediction rediction = new SysOutRediction(new File("tmpRedict.txt"));
        rediction.startRediction();
        
        try
        {
            conn.upload("AAA/1.1.1.1", tmpFile.getAbsolutePath());
            return true;
        }
        catch(IOException e)
        {
            return false;
        }
        finally
        {
            rediction.endRecdition();
        }
    }

    private void mkdir() throws Exception
    {
        try
        {
            CenterToolUtil.printCheckTile("Directory creating authority test");
            conn.mkDirs("AAA/1.1.1.1");
            CenterToolUtil.printSucess();
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.FTP_MKDIR_FAIL);
        }
    }
    
    private void delDir() throws Exception
    {
        try
        {
            CenterToolUtil.printCheckTile("Directory deleting authority test");
            conn.delFile(conn.getDisplayedRootDir() + "AAA/1.1.1.1/tmp.tst");
            conn.delDir(conn.getDisplayedRootDir() + "AAA/1.1.1.1/");
            conn.delDir(conn.getDisplayedRootDir() + "AAA/");
            CenterToolUtil.printSucess();
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.FTP_DELDIR_FAIL);
        }
        
    }

    private FtpConnection createFtpConn(FtpInfo ftp) throws Exception
    {
        try
        {
            CenterToolUtil.printCheckTile("Connecting to FTP server");
            FtpConnection conn = new FtpConnection(ftp.isSFTP ? FtpConnection.PROTOCAL_TYPE_SFTP : FtpConnection.PROTOCAL_TYPE_FTP);
            conn.connect(ftp.ftpServerIp, ftp.ftpUser, ftp.ftpUserPasswd);
            CenterToolUtil.printSucess();
            return conn;
            
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_FTP_CONN_FAIL);
        }
    }

    private File createTestFile() throws Exception
    {
        File testFile;
        try
        {
            testFile = new File("tmp.tst").getAbsoluteFile();
            testFile.createNewFile();
            
            return testFile;
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_TEST_FILE_FAIL);
        }
    }
    
    private String createAndDeleteInFtpDir() throws Exception
    {
        File testFile = new File(ftpInfo.ftpRootDir, "tmp2.tst");
        
        try
        {
            testFile.createNewFile();
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_TEST_FTPFILE_FAIL);
        }
        
        
        if(!testFile.delete())
        {
            throw new Exception(CdfTestResultConst.DELETE_TEST_FTPFILE_FAIL);
        }
        
        return testFile.getName();
    }
    
    public static void main(String[] args)
    {
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = "10.63.204.112";
        ftpInfo.ftpUser = "webnms";
        ftpInfo.ftpUserPasswd = "webnms";
        ftpInfo.ftpRootDir = "c:\\ftpdir";
        ftpInfo.isSFTP = false;
        
        FtpConnCheck check = new FtpConnCheck(ftpInfo);
        CdfTestResult result = check.doCheck(false);
        System.out.println(result.isSuccess);
    }
}

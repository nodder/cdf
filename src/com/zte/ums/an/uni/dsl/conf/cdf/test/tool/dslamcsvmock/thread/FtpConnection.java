package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

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

public class FtpConnection
{
    private FtpClient ftpClient = null;
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());

    public FtpConnection(String serverIP, String userName, String password) throws IOException
    {
        connect(serverIP, userName, password);
    }
    
    public FtpConnection(FtpInfo ftpInfo) throws IOException
    {
        this(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
    }

    private void connect(String serverIP, String userName, String password) throws IOException
    {
        this.ftpClient = new FtpClient();
        this.ftpClient.setConnectTimeout(Integer.MAX_VALUE - 1);
        this.ftpClient.openServer(serverIP);
        this.ftpClient.login(userName, password);
    }
    
    public void disconnect()
    {
        try
        {
            if (this.ftpClient != null)
            {
                this.ftpClient.closeServer();
                this.ftpClient = null;
            }
        }
        catch (IOException ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }
    
    public void cd(String dir) throws IOException
    {
        this.ftpClient.cd(dir);
    }
    
    public String pwd() throws IOException
    {
        return this.ftpClient.pwd();
    }
    
    /**
     * 创建目录，只能在当前目录下创建一个目录，无法递归
     * @param dir
     * @return
     * @throws IOException
     */
    public int mkdir(String dir) throws IOException
    {
        ftpClient.sendServer("XMKD " + dir + "\r\n");
        return ftpClient.readServerResponse();
    }
    
    /**
     * 删除目录，同mkdir
     * @param dir
     * @return
     * @throws IOException
     */
    public int deldir(String dir) throws IOException
    {
        ftpClient.sendServer("XRMD " + dir + "\r\n");
        return ftpClient.readServerResponse();
    }
    
    public int delFile(String dir) throws IOException
    {
        ftpClient.sendServer("DELE " + dir + "\r\n");
        return ftpClient.readServerResponse();
    }
    
    public ArrayList<String> dir() throws IOException
    {
        ArrayList<String> dirList = new ArrayList<String>();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(ftpClient.list()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            dirList.add(line);
        }
        
        return dirList;
    }
    
    public ArrayList<String> listFiles() throws IOException
    {
        ArrayList<String> dirList = new ArrayList<String>();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(ftpClient.list()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            LineParser p = new LineParser(line);
            if(!p.isCurrentDir() && !p.isParentDir())
            {
                dirList.add(line);
            }
        }
        
        return dirList;
    }
    
    public ArrayList<String> listFileNames() throws IOException
    {
        ArrayList<String> fileNames = new ArrayList<String>();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(ftpClient.list()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            LineParser p = new LineParser(line);
            if(!p.isCurrentDir() && !p.isParentDir())
            {
                fileNames.add((new LineParser(line)).getFileName());
            }
        }
        
        return fileNames;
    }
    
    /**
     * 本地目录下的单个文件，上传到FTP远程文件夹下
     * @param remotePath
     * @param localFile
     * @throws IOException
     */
    public boolean upload(String remotePath, String localFile)
    {
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
            return true;
        }
        catch(IOException e)
        {
//            LogPrint.logError(logger, "", e);
        }
        finally
        {
            closeBufferedInputStream(bis);
            closeTelnetOutputStream(out);
        }
        
        return false;
    }

    private void closeBufferedInputStream(BufferedInputStream bis)
    {
        try
        {            
            if(bis != null)
            {
                bis.close();
            }
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }

    private void closeTelnetOutputStream(TelnetOutputStream out)
    {
        try
        {
            if(out != null)
            {
                out.close();
            }
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
    
    /**
     * 远程FTP文件夹下的所有文件，下载到本地指定目录。
     * @param remotePath 远程文件夹
     * @param localPath  本地文件夹
     * @return
     */
    public void download(String remotePath, String localPath)
    {
        remotePath = addLastSeperator(remotePath);
        localPath = addLastSeperator(localPath);
               
        try
        {
            cd(remotePath);
            ArrayList<String> allFileLines = listFiles();
            
            this.ftpClient.binary();
            for (int i = 0; i < allFileLines.size(); i++)
            {
                LineParser currLine = new LineParser(allFileLines.get(i));
                String fileName = currLine.getFileName();

                if (currLine.isFile())
                {
                    downloadFile(remotePath, localPath, fileName);
                }
                else
                {
                    (new File(localPath + fileName)).mkdirs();
                    download(remotePath + fileName, localPath + fileName);
                }
            }
        }
        catch (IOException ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    private void downloadFile(String remotePath, String localPath, String fileName) throws IOException
    {
        FileOutputStream outStream = new FileOutputStream(new File(localPath, fileName));
        
//        System.out.println(fileName + "!!!" + ftpClient.pwd() + "!!!" + remotePath);
        
        if(!ftpClient.pwd().equals(remotePath))
        {
            cd(remotePath);
        }     
        TelnetInputStream is = ftpClient.get(fileName);
        byte[] bytes = new byte[1024000];

        int read;
        while ((read = is.read(bytes)) != -1)
        {
            outStream.write(bytes, 0, read);
        }

        closeInputStream(is);
        closeOutputStream(outStream);

        LogPrint.logInfo(logger, "Succeeded in downloading:" + remotePath + fileName);
    }

    private String addLastSeperator(String remotePath)
    {
        if(!remotePath.endsWith("/") || !remotePath.endsWith(File.separator))
        {
            remotePath = remotePath + "/";
        }
        return remotePath;
    }

    private void closeOutputStream(FileOutputStream outStream)
    {
        try
        {
            if (outStream != null)
            {
                outStream.close();
            }
        }
        catch (IOException ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    private static void closeInputStream(TelnetInputStream is) throws IOException
    {
        if (is != null)
        {
            is.close();
        }
    }
        
    /**
     * 测试代码见cdd.test.useful.LineParser
     * @author chenduoduo
     *
     */
    public class LineParser
    {
        private static final int ARRAY_LENGTH = 9;
        private static final String PARENT_DIR = "..";
        private static final String CURR_DIR = ".";

        private static final int INDEX_PROPERTY = 0;
        private static final int START_INDEX_FILENAME = 8;

        String[] lineParams = new String[ARRAY_LENGTH];;

        public LineParser(String line)
        {
            parserLine(line);
        }

        private void parserLine(final String line)
        {
            int beginIndex = 0;

            boolean isLastPosionSpace = false;
            int paramIndex = 0;

            String trimLine = line.trim();
            for(int i = 0; i < trimLine.length(); i++)
            {            
                if(trimLine.charAt(i) == ' ')
                {
                    if(!isLastPosionSpace)
                    {
                        lineParams[paramIndex++] = trimLine.substring(beginIndex, i);
                    }
                    
                    isLastPosionSpace = true;
                }
                else
                {
                    if(isLastPosionSpace)
                    {
                        beginIndex = i;
                        isLastPosionSpace = false;
                    }
                    
                    if((paramIndex == ARRAY_LENGTH - 1) || (i == trimLine.length() - 1))
                    {
                        lineParams[paramIndex++] = trimLine.substring(beginIndex);
                        break;
                    }
                }
            }
        }

        public String getFileName()
        {
            return lineParams[START_INDEX_FILENAME];
        }

        public boolean isParentDir()
        {
            return lineParams[START_INDEX_FILENAME].equals(PARENT_DIR);
        }

        public boolean isCurrentDir()
        {
            return lineParams[START_INDEX_FILENAME].equals(CURR_DIR);
        }

        public boolean isDir()
        {
            return lineParams[INDEX_PROPERTY].indexOf("d") != -1;
        }

        public boolean isFile()
        {
            return !isDir();
        }   
        
        @Override
        public String toString()
        {
            StringBuffer buf = new StringBuffer();
            for(String p : lineParams)
            {
                if(p != null)
                {
                    buf.append(p).append("|");
                }
            }
            
            return buf.delete(buf.length() - 1, buf.length()).toString();
        }
    }
        
    public static void main(String[] args) throws IOException
    {
        System.out.println(System.getProperty("user.dir"));
        FtpConnection ftp = new FtpConnection("127.0.0.1", "webnms", "webnms"); 
        
//        ftp.download("/SCM", "g:\\TEMP/SCM");
        
//        ftp.upload("/SCM/", "g:\\TEMP/SCM/tmp/10.63.156.90.xml");

        //        System.out.println("====================listFiles============");
//        ArrayList<String> files = ftp.listFiles();
//        for(String file : files)
//        {
//            System.out.println(file);
//        }
//        
//        System.out.println("====================listFileNames============");
//        files = ftp.listFileNames();
//        for(String file : files)
//        {
//            System.out.println(file);
//        }
//        
//        ftp.uploadDir("/CDF/", "g:/TEMP/1222/1212");
    }

}

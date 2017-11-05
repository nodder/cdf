package com.zte.ums.an.uni.dsl.conf.cdf.common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.SeqRecProxy;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.CommonConst;
import com.zte.ums.n3common.api.ZXLogger;
import com.zte.ums.uep.protocol.snmp.beans.SnmpTarget;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpOID;

/**
 * <p>文件名称: CdfUtil.java</p>
 * <p>文件描述: CDF工具类</p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月14日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  jingxueshi_10118495
 */
public class CdfUtil
{
    //****** 代码段: 变量定义 **********************************************************************/

    private static Logger logger = ZXLogger.getLogger(CdfUtil.class.getName());

    private static final String[] MAP_NE_ARRAY = {
           CommonConst.NE_TYPE_DSL_9806H_STR, CommonConst.NE_TYPE_DSL_9806I_STR,
           CommonConst.NE_TYPE_DSL_9812_STR, CommonConst.NE_TYPE_DSL_9816_STR,
           CommonConst.NE_TYPE_DSL_9836_STR, CommonConst.NE_TYPE_DSL_9856_STR,
           CommonConst.NE_TYPE_F402_STR, CommonConst.NE_TYPE_F802_STR,
           CommonConst.NE_TYPE_F803_8_STR, CommonConst.NE_TYPE_F803_16_STR,
           CommonConst.NE_TYPE_F803G_8_STR, CommonConst.NE_TYPE_F803G_16_STR,
           CommonConst.NE_TYPE_F803V1_STR, CommonConst.NE_TYPE_F809_STR,
           CommonConst.NE_TYPE_F821_STR, CommonConst.NE_TYPE_F822_STR,
           CommonConst.NE_TYPE_F823_STR, CommonConst.NE_TYPE_F826_STR,
           CommonConst.NE_TYPE_F829_STR, CommonConst.NE_TYPE_EC4022_STR,
           CommonConst.NE_TYPE_MSG5208_STR, CommonConst.NE_TYPE_F822P_STR,
           CommonConst.NE_TYPE_EC4023_STR, CommonConst.NE_TYPE_EC4026_STR,
           CommonConst.NE_TYPE_F803V3_STR};
    //****** 代码段: 工具方法 **********************************************************************/

    private CdfUtil()
    {
    }
    /**
     * 获取自定义SnmpTarget
     * @return SnmpTarget
     */
    public static SnmpTarget getCustomizedSnmpTarget(SnmpNode snmpNode)
    {
        SnmpTarget snmpTarget = new SnmpTarget();
        snmpTarget.setTargetHost(snmpNode.getIpAddress());
        snmpTarget.setSnmpVersion(SubCollectCache.snmpTargetModule.getSnmpVersion());
        snmpTarget.setCommunity(snmpNode.getSnmpCommunity());
        snmpTarget.setWriteCommunity(snmpNode.getSnmpWriteCommunity());
        snmpTarget.setTargetPort(SubCollectCache.snmpTargetModule.getTargetPort());
        snmpTarget.setRetries(SubCollectCache.snmpTargetModule.getRetries());
        snmpTarget.setTimeout(SubCollectCache.snmpTargetModule.getTimeout());

        return snmpTarget;
    }

    /**
     * 快速SNMP ping
     * @return boolean
     */
    public static boolean fastSnmpPing(SnmpNode snmpNode)
    {
        SnmpTarget snmpTarget = new SnmpTarget();
        snmpTarget.setTargetHost(snmpNode.getIpAddress());
        snmpTarget.setSnmpVersion(SubCollectCache.snmpTargetModule4Fast.getSnmpVersion());
        snmpTarget.setCommunity(snmpNode.getSnmpCommunity());
        snmpTarget.setTargetPort(SubCollectCache.snmpTargetModule4Fast.getTargetPort());
        snmpTarget.setRetries(SubCollectCache.snmpTargetModule4Fast.getRetries());
        snmpTarget.setTimeout(SubCollectCache.snmpTargetModule4Fast.getTimeout());

        snmpTarget.setAttemptPartial(true);
        snmpTarget.setSnmpOID(new SnmpOID("1.1.0"));
        String retVar = snmpTarget.snmpGet();
        if(retVar != null)
        {
            return true;
        }

        return false;
    }
    public static String formatPath(String filePath)
    {
        filePath.replace('\\', File.separatorChar);
        filePath.replace('/', File.separatorChar);
        
        if(filePath.charAt(filePath.length() - 1) != File.separatorChar)
        {
            filePath += File.separatorChar;
        }
        
        return filePath;
    }
    
    /**
     * 给定目录路径，如果该路径下不存在目录，则创建。
     * @return File
     */
    public static File createdDirIfNoExist(String fullFileName)
    {
        File file = new File(fullFileName);
        if(!file.isDirectory())
        {
            if(!file.mkdirs())
            {
                LogPrint.logError(logger, "create dir for " + fullFileName + "failed.");
            }
        }

        return file;
    }
    
    /**
     * 列出指定路径下指定后缀名的所有文件
     * @param dir
     * @param fileNameSuffix
     * @return
     */
    public static File[] getFiles(String dir, final String fileNameSuffix)
    {
        if(!new File(dir).isDirectory())
        {
            return null;
        }
        
        File[] files = new File(dir).listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if(pathname.getName().endsWith(fileNameSuffix))
                {
                    return true;
                }
                
                return false;
            }

        });

        return files;
    }
    
    public static String getCurDateTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public static String getCurDateTime(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * UNIX系统下，给指定目录及其子目录赋予完全控制权限
     * @return void
     */
    public static void makeFullAuthority(String dir)
    {
        String osName = System.getProperty("os.name");

        if((osName != null) && osName.startsWith("Windows"))
        {
            //OS为windows，不需要修改权限，直接返回。
        }
        else
        {
            String command = "chmod -R 777 " + dir;
            try
            {
                Runtime r = Runtime.getRuntime();
                Process process = r.exec(command);
                sleep(2500);

                if(process.exitValue() != 0)
                {
                    LogPrint.logError(logger, "chmod failed: " + dir);
                }
            }
            catch(Exception ex)
            {
                LogPrint.logError(logger, "Executing chmod commond Exception : ", ex);
            }
        }
    }
    
    /**
     * 线程休眠，单位毫秒
     * @return void
     */
    public static void sleep(int millSec)
    {
        try
        {
            Thread.sleep(millSec);
        }
        catch(InterruptedException ie)
        {
            LogPrint.logError(logger, "Thread.sleep exception :", ie);
        }
    }
    
    public static String getFtpRelativePath(String ipAddr)
    {
        return "." + getDefaultRelativePath(ipAddr);
    }
    
    public static String getFtpFullPath(String ipAddr)
    {
        return removeSeperator(SubCollectCache.ftpInfo.ftpRootDir) + getDefaultRelativePath(ipAddr);
    }
    
    private static String getDefaultRelativePath(String ipAddr)
    {
        return "/" + CdfConst.FTP_CDF_PATH + "/" + ipAddr + "/";
    }
    
    private static String removeSeperator(String ftpRootDir)
    {
        if((ftpRootDir.charAt(ftpRootDir.length() - 1) == '/') 
                        || (ftpRootDir.charAt(ftpRootDir.length() - 1) == '\\'))
        {
            return ftpRootDir.substring(0, ftpRootDir.length() - 1);
        }
        
        return ftpRootDir;
    }
    
    public static void closeFileOutputStream(FileOutputStream fs)
    {
        if(fs != null)
        {
            try
            {
                fs.flush();
                fs.close();
            }
            catch(IOException ex)
            {
                LogPrint.logError(logger, "", ex);
            }         
        }
    }

    public static void closeBufferedReader(BufferedReader reader)
    {
        try
        {
            if(reader != null)
            {
                reader.close();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }
    
    public static void closeWriter(Writer out)
    {
        if(out != null)
        {
            try
            {
                out.flush();
                out.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 如果file是目录，则file下所有子目录和所有文件(不删除当前目录)
     * 如果file是文件，则删除file
     * @return void
     */
    public static void deleteFiles(File file)
    {
        try
        {
            if(file.isDirectory())
            {
                File delFile[] = file.listFiles();
                for(int i = 0; i < delFile.length; i++)
                {
                    deleteFiles(delFile[i]);
                    delFile[i].delete();
                }
            }
            else
            {
                file.delete();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    /**
     * 删除指定目录，如果file表示一个文件，则删除该文件
     * @return void
     */
    public static boolean deleteDir(File file)
    {
        if(file.isDirectory())
        {
            deleteFiles(file);
        }

        return file.delete();
    }
    
    /**
     * 关闭缓冲输出流和文件输出流
     * @return void
     */
    public static void closeOutputStream(BufferedOutputStream buff, FileOutputStream outSTr)
    {
        try
        {
            if(buff != null)
            {
                buff.close();
            }
            if(outSTr != null)
            {
                outSTr.close();
            }
        }
        catch(Exception ex)
        {
            logger.error("Error ocured in close Buff or outSTr. Detail : ", ex);
        }
    }
    
    public static SeqRecProxy createProxy(File file, String bufferedReaderClass) throws Exception
    {
        if(bufferedReaderClass == null || "".equalsIgnoreCase(bufferedReaderClass))
        {
            return new SeqRecProxy(file);
        }
        else
        {
            BufferedReader br = createBufferedReader(bufferedReaderClass, file);
            return new SeqRecProxy(br);
        }
    }
    
    public static BufferedReader createBufferedReader(String bufferedReaderClass, File file) throws Exception
    {
        Class<? extends BufferedReader> cc = (Class<? extends BufferedReader>)Class.forName(bufferedReaderClass);

        Class<?>[] argClasses = new Class[] {Reader.class};
        Constructor<? extends BufferedReader> constructor = cc.getConstructor(argClasses);
        
        Object[] argObjects = new Object[] {new FileReader(file)};
        return constructor.newInstance(argObjects);
    }
    
    public static boolean isMapNe(String neType)
    {
        return arrayContainsKey(MAP_NE_ARRAY, neType);
    }

    private static boolean arrayContainsKey(String[] arr, String key)
    {
        for(String item : arr)
        {
            if(item.equals(key))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private static List<String> readVersionFile()
    {
        List<String> contextList = new ArrayList<String>();

        try
        {
            File file = getVersionFile();
            if(!file.exists())
            {
                return contextList;
            }
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String oneLineContext;
            while((oneLineContext = br.readLine()) != null)
            {
                contextList.add(oneLineContext);
            }
            br.close();
        }
        catch(Exception e)
        {
            return contextList;
        }

        return contextList;
    }
    private static File getVersionFile()
    {
        String strFile = "conf/release";
        if(new File(strFile).exists())
        {
            return new File(strFile);
        }
        else if(new File("..", strFile).exists())
        {
            return new File("..", strFile);
        }
        else if(new File("../tools/cdf", strFile).exists())
        {
            return new File("../tools/cdf", strFile);
        }
        else if(new File("../../../../tools/cdf", strFile).exists())
        {
            return new File("../../../../tools/cdf", strFile);
        }
        else
        {
            return new File("../..", strFile);
        }
    }
    
    public static String getVersion()
    {
        String version = null;
        List<String> versionFileContext = readVersionFile();
        
        if(versionFileContext.size() == 0)
        {
            return version;
        }
        
        String versionLine = (String)versionFileContext.get(0);
        if(!versionLine.startsWith("v") && !versionLine.startsWith("V"))
        {
            return version;
        }
        
        int blankIndex = versionLine.indexOf(" ");
        if(blankIndex == -1)
        {
            version = versionLine;
        }
        else
        {
            version = versionLine.substring(0, blankIndex);
        }
        
        return version;
    }
    
    public static String getIndexFromProfileName(String strProfileName)
    {
        byte[] index = strProfileName.getBytes();
        String strIndex = String.valueOf(index.length);
        for(int i = 0; i < index.length; i++)
        {
            strIndex += ("." + index[i]);
        }
        return strIndex;
    }
    
    public static void copyFile(String oldFile, String newFile) throws IOException
    {
        String lineSeparator = (String) java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("line.separator"));
        
        FileWriter out = null;
        BufferedReader bufferReader = null;
        
        try
        {
            File newF = new File(newFile);
            if(!newF.getParentFile().exists())
            {
                newF.getParentFile().mkdirs();
            }
            out = new FileWriter(newF);
            bufferReader = new BufferedReader(new FileReader(oldFile));
            
            String line;
            while ((line = bufferReader.readLine()) != null)
            {
                out.write(line + lineSeparator);
            }
            
            logger.debug("File copied from " + oldFile + " to " + newFile);
        }
        finally
        {
            closeWriter(out);         
            closeBufferedReader(bufferReader);
        }
    }
}

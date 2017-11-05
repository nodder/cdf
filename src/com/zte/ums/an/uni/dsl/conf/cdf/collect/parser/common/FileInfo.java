package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: FileInfo</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月19日</p>
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
public class FileInfo
{    
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private final String bulkPoolName;
    private final Timestamp time;
    private final File file;
    
    private String readProxy = null;
    private String matcherName = null;
    private FileAppendInfo[] appendInfos = null;

    public FileInfo(File file)
    {
        this.file = file;
        this.time = analyzeCollectionTime();
        this.bulkPoolName = analyzeBulkPoolName();
    }
    
    //TODO 测试用
    public FileInfo(String bulkPoolName)
    {
        this.bulkPoolName = bulkPoolName;
        this.time = null;
        this.file = null;
    }
    
    public String getbulkPoolName()
    {
        return bulkPoolName;
    }
    
    public Timestamp getTimeStamp()
    {
        return time;
    }

    public File getFile()
    {
        return file;
    }
    
    public String getReadProxy()
    {
        return readProxy;
    }

    public void setReadProxy(String readProxy)
    {
        this.readProxy = readProxy;
    }
    
    public String getMatcherName()
    {
        return matcherName;
    }

    public void setMatcherName(String matcherName)
    {
        this.matcherName = matcherName;
    }

    public FileAppendInfo[] getAppendInfos()
    {
        return appendInfos;
    }

    public void setAppendInfos(FileAppendInfo[] appendInfos)
    {
        this.appendInfos = appendInfos;
    }

    
    /** 从形如c:/10.63.192.162_ADSLPORT_20000101191502.csv的文件名中将业务类型ADSLPORT获取出来 */
    public String analyzeBulkPoolName()
    {
        String fileName = this.file.getName();
        
        int first = fileName.indexOf("_");
        int second = fileName.lastIndexOf("_");
        
        if((first < second) && (second < fileName.length()))
        {
            return fileName.substring(first + 1, second);
        }
        
        return null;
    }
            
    /** 从形如c:/10.63.192.162_ADSLPORT_20000101191502.csv的文件名中将时间20000101191502解析出来 */
    private Timestamp analyzeCollectionTime()
    {
        String strTime = getStrTimeFromFileName();

        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date date = sdf.parse(strTime);
            return new Timestamp(date.getTime());
        }
        catch(ParseException ex)
        {
            LogPrint.logError(logger, "analyzeCollectionTime", ex);
        }

        return null;
    }

    private String getStrTimeFromFileName()
    {
        String fileName = this.file.getName();
        
        if((fileName.indexOf("_") >= 0) && (fileName.indexOf(".") >= 0))
        {
            return this.file.getName().substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("."));
        }
        else
        {
            return "19700101010101";
        }
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" bulkPoolName == " + bulkPoolName + "\n");
        buf.append(" time         == " + time + "\n");
        buf.append(" file         == " + file + "\n");
        buf.append(" readProxy    == " + readProxy + "\n");
        
        if(appendInfos == null)
        {
            buf.append(" appendInfos    == null\n");
        }
        else
        {
            for(FileAppendInfo info : appendInfos)
            {
                buf.append(info);
            }
        }
        
        return buf.toString();
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <ul>
 * <li>文件名称: RecordReadProxy</li>
 * <li>文件描述: </li>
 * <li>版权所有: 版权所有(C) 2012</li>
 * <li>公 司: 中兴通讯股份有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>完成日期:2011-12-06 </li>
 * </ul>
 * <ul>
 * <li>修改记录: </li>
 * <li>版 本 号: </li>
 * <li>修改日期: </li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 * 
 * @author jingxueshi
 * @version 1.0.0
 */

public class SeqRecProxy
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private BufferedReader reader;
    
    private Record currRecord;
    private boolean isEndofFile = false;
    
    public SeqRecProxy(BufferedReader reader) throws IOException
    {
        this.reader = reader;
        next();
    }
    
    public SeqRecProxy(File file) throws IOException
    {
        this.reader = new BufferedReader(new FileReader(file));
        next();
    }
    
    public boolean next()
    {
        try
        {
            String line = reader.readLine();

            //讀到空或者空字符串認為文件結束
            if(line == null || line.equalsIgnoreCase(""))
            {
                this.isEndofFile = true;
                this.currRecord = null;
                return false;
            }

            this.currRecord = new Record(line);
            return true;
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
            return false;
        }
    }

    public Record getRecord()
    {
        return currRecord;
    }
    
    public boolean isEndOfFile()
    {
        return isEndofFile;
    }

    public void close()
    {
        try
        {
            if(reader != null)
            {
                reader.close();
            }
        }
        catch (IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolAnalysis;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.DataProcess;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ParserByDB</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月26日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */

public class ParserByDB extends AbsParserProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    public ParserByDB(SnmpNode snmpNode)
    {
        super(snmpNode);
    }
    
    @Override
    public boolean parseDataFromCsv()
    {
        String csvFilePath = CdfUtil.getFtpFullPath(snmpNode.getIpAddress());
        File[] csvFiles = CdfUtil.getFiles(csvFilePath, ".csv");
        
        if(csvFiles == null)
        {
            LogPrint.logError(logger, "Invalid dir :" + csvFilePath + ", cancel the parsing operation.");
            return false;
        }
        
        LogPrint.logDebug(logger, "Total " + csvFiles.length + " csv files in dir " + csvFilePath);
              
        return dbOperForDoParse(csvFiles);
    }

    private boolean dbOperForDoParse(File[] csvFiles)
    {
        DataProcess dataProcess = null;
        try
        {
            boolean result = true;
            BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(csvFiles);
            ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
            
            if(groups.size() == 0)
            {
                return false;
            }
            
            for(BulkPoolGroupInfo group : groups)
            {
                dataProcess = new DataProcess(group);
                DbOperForCdfParser dbOper = new DbOperForCdfParser(group, this.snmpNode);
                
                result = result && dbOper.dbOperForInsertRecords(dataProcess);
                dataProcess.close();
            }
            
            return result;
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "parseDataFromCsv", e);
            return false;
        }
        finally
        {
            if(dataProcess != null)
            {
                dataProcess.close();
            }
        }
    }
    
    public static void main(String[] args)
    {
        SubCollectCache.initParams();  
        SnmpNode snmpNode = new SnmpNode();
        snmpNode.setIpAddress("192.168.10.116");
        snmpNode.setName("Name_192.168.10.116");
        snmpNode.setMoc("ZXDSL9836");
        
        ParserByDB parser = new ParserByDB(snmpNode);
        parser.parseDataFromCsv();
        
        System.exit(1);
    }
}
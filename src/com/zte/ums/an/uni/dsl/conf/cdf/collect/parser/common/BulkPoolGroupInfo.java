package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: BulkPoolGroupInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-20</p>
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
public class BulkPoolGroupInfo
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static final HashMap<String, String[]> groupToMergedBulkPool = CollectDataXmlParser.getInstance().getGroupToMergedBulkPoolMapInfo();
    private static final HashMap<String, String[]> groupToAppendBulkPool = CollectDataXmlParser.getInstance().getGroupToAppendBulkPoolMapInfo();
    private static final HashMap<String, String[]> allGroupToBulkPool = CollectDataXmlParser.getInstance().getGroupToBulkPoolMapInfo();
   
    private final String groupName;
    private final Timestamp time;
    
    private FileInfo[] mergedFileInfoList;
    private FileInfo[] appendFileInfoList;
    private FileInfo[] allFileInfoList;
    
    public BulkPoolGroupInfo(String groupName, Timestamp time)
    {
        this.groupName = groupName;
        this.time = time;
        
        this.mergedFileInfoList = new FileInfo[getFileInfoListLength(groupToMergedBulkPool.get(groupName))];
        this.appendFileInfoList = new FileInfo[getFileInfoListLength(groupToAppendBulkPool.get(groupName))];
        this.allFileInfoList = new FileInfo[getFileInfoListLength(allGroupToBulkPool.get(groupName))];
    }
    
    public void addBulkPool(FileInfo bulkPool)
    {
        addBulkPool(bulkPool, groupToMergedBulkPool.get(groupName), mergedFileInfoList);
        addBulkPool(bulkPool, groupToAppendBulkPool.get(groupName), appendFileInfoList);
        addBulkPool(bulkPool, allGroupToBulkPool.get(groupName), allFileInfoList);
    }

    private void addBulkPool(FileInfo bulkPool, String[] bulkPoolNames, FileInfo[] fileInfoList)
    {
        int index = getIndex(bulkPool.getbulkPoolName(), bulkPoolNames); 
        
        if(index != -1)
        {
            if(fileInfoList[index] != null)
            {
                LogPrint.logWarn(logger, "same group same bulkpool aleady exist." + fileInfoList[index]);
            }
            
            fileInfoList[index] = bulkPool;
        }
    }
    
    private int getFileInfoListLength(String[] bulkPools)
    {
        return bulkPools == null ? 0 : bulkPools.length;
    }
        
    private int getIndex(String key, String[] allKeys)
    {        
        for(int i = 0; i < allKeys.length; i++)
        {
            if(key.equals(allKeys[i]))
            {
                return i;
            }
        }
        
        return -1;
    }
        
    public FileInfo[] getFileInfoList()
    {
        return allFileInfoList;
    }
    
    public FileInfo[] getAppendFileInfoList()
    {
        return appendFileInfoList;
    }
    
    public FileInfo[] getMergedFileInfoList()
    {
        return mergedFileInfoList;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public Timestamp getTime()
    {
        return time;
    }
        
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" name == " + groupName + "\n");
        buf.append(" time == " + time + "\n");
        
        appendFileInfoList(buf, mergedFileInfoList, "mergedFileInfoList");
        appendFileInfoList(buf, appendFileInfoList, "appendFileInfoList");
        appendFileInfoList(buf, allFileInfoList, "allFileInfoList");
        
        return buf.toString();
    }
    
    public String getAllFileStr()
    {
        StringBuffer buf = new StringBuffer();
        if(allFileInfoList != null)
        {
            for(FileInfo file : allFileInfoList)
            {
                if(file != null)
                {
                    buf.append(file.getFile().getName()).append(";");
                }
            }
        }
        
        return buf.toString();
    }
    
    protected void appendFileInfoList(StringBuffer buf, FileInfo[] fileInfoList, String name)
    {
        if(allFileInfoList == null)
        {
            buf.append(name + " == null" + "\n");
        }
        else
        {
            buf.append(name + " == \n[\n");
            for(FileInfo info : fileInfoList)
            {
                if(info == null)
                {
                    buf.append("info == null\n");
                }
                else
                {
                    buf.append(info.toString());
                }
            }
            buf.append("]");
        }
    }
}

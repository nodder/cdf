package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: BulkPoolAnalysis.java</p>
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
public class BulkPoolAnalysis
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static final HashMap<String, String[]> groupToBulkPool = CollectDataXmlParser.getInstance().getGroupToBulkPoolMapInfo();
    private static final HashMap<String, FileAppendInfo[]> bulkPoolToAppendInfos = CollectDataXmlParser.getInstance().getBulkPoolToAppendInfos();

    private static final HashMap<String, String> bulkPoolToReadProxy = CollectDataXmlParser.getInstance().getBulkPoolToReadProxy();
    
    private static final HashMap<String, String> bulkPoolToGroup = bulkPoolToGroup();
    
    private static final HashMap<String, String> bulkPoolToMatcher = CollectDataXmlParser.getInstance().getBulkPoolToMatcher();
    
    ArrayList<BulkPoolGroupInfo> groups = new ArrayList<BulkPoolGroupInfo>();
    
    public BulkPoolAnalysis(File[] files)
    {
        buildGroups(files);
        removeInvalidFromGroups();
    }
    
    public ArrayList<BulkPoolGroupInfo> exportBulkPoolGroups()
    {
        return this.groups;
    }

    private void buildGroups(File[] files)
    {
        for(File file : files)
        {
            FileInfo f = new FileInfo(file);
            
            String bulkPoolName = f.getbulkPoolName();
            if(isValidBulkPool(bulkPoolName))
            {
                int index = getSameGroupIndex(f);
                if(index == -1)
                {
                    BulkPoolGroupInfo newGroup = new BulkPoolGroupInfo(getGroupName(bulkPoolName), f.getTimeStamp());
                    groups.add(newGroup);
                    index = groups.size() - 1;
                }
                
                f.setReadProxy(bulkPoolToReadProxy.get(f.getbulkPoolName()));
                f.setMatcherName(bulkPoolToMatcher.get(f.getbulkPoolName()));
                f.setAppendInfos(bulkPoolToAppendInfos.get(f.getbulkPoolName()));
                
                groups.get(index).addBulkPool(f);
            }
        }
    }

    
    private void removeInvalidFromGroups()
    {
        for(int i = groups.size() - 1; i >= 0; i--)
        {     
            BulkPoolGroupInfo group = groups.get(i);
            if(!validate(group))
            {
                groups.remove(i);
            }
        }
    }

    private boolean validate(BulkPoolGroupInfo group)
    {
        for(int i = 0; i < group.getFileInfoList().length; i++)
        {
            if(group.getFileInfoList()[i] == null)
            {
                LogPrint.logWarn(logger, "group validate fail. Detail: " + group.getAllFileStr());
                return false;
            }
        }
        
        return true;
    }

    private int getSameGroupIndex(FileInfo f)
    {
        for(int i = 0; i < groups.size(); i++)
        {
            if(isSameGroup(f, groups.get(i)))
            {
                return i;
            }
        }
        
        return -1;
    }

    private boolean isSameGroup(FileInfo f, BulkPoolGroupInfo group)
    {
        return (group.getGroupName().equals(bulkPoolToGroup.get(f.getbulkPoolName())))
               && (Math.abs((group.getTime().getTime() - f.getTimeStamp().getTime())) < 1000 * Integer.parseInt(CollectXmlParser.getInstance()
                               .getCsvFileTimeTolerance()));
    }
    
    private boolean isValidBulkPool(String bulkPoolName)
    {
        return bulkPoolToGroup.containsKey(bulkPoolName);
    }
    
    private String getGroupName(String bulkPoolName)
    {
        return bulkPoolToGroup.get(bulkPoolName);
    }
    
    private static HashMap<String, String> bulkPoolToGroup()
    {
        HashMap<String, String> mapBulkPoolToGroup = new HashMap<String, String>();
        
        Iterator<Entry<String, String[]>> it = groupToBulkPool.entrySet().iterator();     
        while(it.hasNext())
        {
            Entry<String, String[]> entry = (Entry<String, String[]>)(it.next());
            
            String group = entry.getKey();
            String[] bulkPools = entry.getValue();
            for(int i = 0; i < bulkPools.length; i++)
            {
                mapBulkPoolToGroup.put(bulkPools[i], group);
            }
        }
        
        return mapBulkPoolToGroup;
    }
            
    public static void main(String[] args)
    {
        File[] csvFiles = CdfUtil.getFiles("C:\\ftpdir", ".csv");
        
        BulkPoolAnalysis b = new BulkPoolAnalysis(csvFiles);
        
        ArrayList<BulkPoolGroupInfo> g = b.exportBulkPoolGroups();
        
        for(BulkPoolGroupInfo info : g)
        {
            System.out.println("=====OK==========");
            System.out.println(info);
        }
    }
}

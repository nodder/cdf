package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher.IMatcher;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileAppendInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FilterInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CollectDataXmlParser</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月25日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */

public class CollectDataXmlParser
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private static CollectDataXmlParser instance = new CollectDataXmlParser();
    private XmlIO xmlIo;
    private HashMap<String, String[]> groupToBulkPool = new HashMap<String, String[]>();
    private HashMap<String, String[]> groupToMergeBulkPool = new HashMap<String, String[]>();
    private HashMap<String, String[]> groupToAppendBulkPool = new HashMap<String, String[]>();
    private HashMap<String, String[]> groupToFixedArea = new HashMap<String, String[]>();
    
    private HashMap<String, String> bulkPoolToReadProxy = new HashMap<String, String>();
    private HashMap<String, String> bulkPoolToMatcher = new HashMap<String, String>();
    private HashMap<String, FileAppendInfo[]> bulkPoolToAppendInfos = new HashMap<String, FileAppendInfo[]>();
    
    private HashMap<String, FilterInfo[]> groupToFilterInfos = new HashMap<String, FilterInfo[]>();
    
    private HashMap<String, IMatcher> matchers = new HashMap<String, IMatcher>();
    
    private CollectDataXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-sub-collect-data.xml");
        xmlIo = new XmlIO(file);
        
        initHashTable();
    }

    public static CollectDataXmlParser getInstance() 
    {
        return instance;
    }
        
  //BulkPool_Validation
    private void initHashTable()
    {
        try
        {
            ArrayList<Element> groups = XmlIO.getAllElements(xmlIo.getRootElement(), "BulkPool_Validation", "Group");
            
            matchers.put("com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher.DefaultMatcher", 
                createMatcher("com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher.DefaultMatcher"));
            
            for(Element group: groups)
            {
                ArrayList<String> mergedPools = XmlIO.getAllElementsValue("name", group, "merge", "BulkPool");
                groupToMergeBulkPool.put(group.getAttributeValue("name"), (String[])mergedPools.toArray(new String[mergedPools.size()]));
                
                ArrayList<String> appendBulkPools = XmlIO.getAllElementsValue("name", group, "append", "BulkPool");
                groupToAppendBulkPool.put(group.getAttributeValue("name"), (String[])appendBulkPools.toArray(new String[appendBulkPools.size()]));
                
                mergedPools.addAll(appendBulkPools);
                groupToBulkPool.put(group.getAttributeValue("name"), (String[])mergedPools.toArray(new String[mergedPools.size()]));
                
                initBulkPoolToReadProxy(group);
                initBulkPoolToFileAppendInfo(group);
                initGroupToFieldArea(group);
                
                initGroupToFilterInfos(group);
                
                initBulkPoolToMatcher(group);
                List<String> matcherNameList = XmlIO.getAllElementsValue("matcher", group, "append", "BulkPool");
                initMatcherInstance(matcherNameList);
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
            ex.printStackTrace();
        }
    }

    protected void initBulkPoolToReadProxy(Element group)
    {
        ArrayList<Element> mergeBulkPoolElements = XmlIO.getAllElements(group, "merge", "BulkPool");
        for(Element bulkPool : mergeBulkPoolElements)
        {
            bulkPoolToReadProxy.put(bulkPool.getAttributeValue("name"), bulkPool.getAttributeValue("readProxy"));
        }
        
        ArrayList<Element> appendBulkPoolElements = XmlIO.getAllElements(group, "append", "BulkPool");
        for(Element bulkPool : appendBulkPoolElements)
        {
            bulkPoolToReadProxy.put(bulkPool.getAttributeValue("name"), bulkPool.getAttributeValue("readProxy"));
        }
    }

    protected void initBulkPoolToMatcher(Element group)
    {
        ArrayList<Element> appendBulkPoolElements = XmlIO.getAllElements(group, "append", "BulkPool");
        String matcherName = null;
        for(Element bulkPool : appendBulkPoolElements)
        {
            matcherName = bulkPool.getAttributeValue("matcher");
            if(matcherName == null || "".equals(matcherName))
            {
                bulkPoolToMatcher.put(bulkPool.getAttributeValue("name"), "com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher.DefaultMatcher");
            }
            else
            {
                bulkPoolToMatcher.put(bulkPool.getAttributeValue("name"), matcherName);
            }
        }
    }
    
    protected void initGroupToFieldArea(Element group)
    {
        ArrayList<Element> fields = XmlIO.getAllElements(group, "fixedArea", "Field");
        
        String[] arrFieldValue = new String[fields.size()]; 
        for(int i = 0; i < arrFieldValue.length; i++)
        {
            arrFieldValue[i] = fields.get(i).getText();
        }
        
        groupToFixedArea.put(group.getAttributeValue("name"), arrFieldValue);
    }

    private void initGroupToFilterInfos(Element group)
    {
        ArrayList<Element> filterInfoElements = XmlIO.getAllElements(group, "filter", "FilterInfo");
        FilterInfo[] filterInfoArray = new FilterInfo[filterInfoElements.size()];
        
        Element element = null;
        int elementCount = filterInfoElements.size();

        for(int i = 0; i < elementCount; i++)
        {
            element = filterInfoElements.get(i);
            
            int index = Integer.parseInt(element.getAttributeValue("indexFilter"));
            String value = element.getAttributeValue("valueFilter");
            
            String filterValues[] = null;
            if(!"".equals(value))
            {
                filterValues = value.split(",");
            }
            
            filterInfoArray[i] = new FilterInfo();
            filterInfoArray[i].setIndexFilter(index);
            filterInfoArray[i].setValuesFilter(filterValues);
        }
        groupToFilterInfos.put(group.getAttributeValue("name"), filterInfoArray);
    }
    
    private void initMatcherInstance(List<String> matchersName)
    {
        int length = matchersName.size();
        String name = null;
        IMatcher matcher = null;
        for(int i = 0; i < length; i++)
        {
            name = matchersName.get(i);
            if(name != null && !matchers.containsKey(name))
            {
                matcher = createMatcher(name);
                if(matcher != null)
                {
                    matchers.put(name, matcher);
                }
            }
        }
    }
    
    private IMatcher createMatcher(String matcherName)
    {
        IMatcher matcher = null;
        
        try
        {
            matcher = (IMatcher)Class.forName(matcherName).newInstance();
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "createMatcher Error: ", e);
        }
        return matcher;
    }
    
    private void initBulkPoolToFileAppendInfo(Element group)
    {
        ArrayList<Element> appendBulkPoolElements = XmlIO.getAllElements(group, "append", "BulkPool");
        for(Element bulkPool : appendBulkPoolElements)
        {
            ArrayList<Element> appendInfoElements = XmlIO.getAllElements(bulkPool, "AppendInfo");
            
            FileAppendInfo[] infos = new FileAppendInfo[appendInfoElements.size()];
            
            for(int i = 0; i < appendInfoElements.size(); i++)
            {
                Element appendE = appendInfoElements.get(i);
                
                int indexMerge = Integer.parseInt(appendE.getAttributeValue("indexMerge"));
                int indexAppend = Integer.parseInt(appendE.getAttributeValue("indexAppend"));
                int[] valueAppend = buildArrIntFromStr(appendE.getAttributeValue("valueAppend"));
                
                infos[i] = new FileAppendInfo(indexMerge, indexAppend, valueAppend);
            }
            
            bulkPoolToAppendInfos.put(bulkPool.getAttributeValue("name"), infos);
        }
    }
    
    private int[] buildArrIntFromStr(String attributeValue)
    {
        if(null == attributeValue)
        {
            throw new IllegalArgumentException("Must indicate node valueAppend in node AppendInfo.");
        }
        
        int[] arrInt;
        try
        {
            String[] split = attributeValue.split(",");
            arrInt = new int[split.length];
            
            for(int i = 0; i < split.length; i++)
            {
                arrInt[i] = Integer.parseInt(split[i]);
            }
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("The value of valueAppend must consist of numbers seperated by comma, like '1,2'");
        }

        return arrInt;
    }

    public synchronized HashMap<String, String[]> getGroupToMergedBulkPoolMapInfo()
    {   
        return groupToMergeBulkPool;
    }
    
    public synchronized HashMap<String, String[]> getGroupToAppendBulkPoolMapInfo()
    {   
        return groupToAppendBulkPool;
    }
    
    public synchronized HashMap<String, String[]> getGroupToBulkPoolMapInfo()
    {   
        return groupToBulkPool;
    }
    
    public synchronized HashMap<String, String[]> getGroupToFixedArea()
    {   
        return groupToFixedArea;
    }
    
    public synchronized HashMap<String, String> getBulkPoolToReadProxy()
    {   
        return bulkPoolToReadProxy;
    }
    
    public synchronized HashMap<String, FileAppendInfo[]> getBulkPoolToAppendInfos()
    {
        return bulkPoolToAppendInfos;
    }
    
    public synchronized HashMap<String, FilterInfo[]> getGroupToFilterInfos()
    {
        return groupToFilterInfos;
    }
    
    public synchronized HashMap<String, String> getBulkPoolToMatcher()
    {
        return bulkPoolToMatcher;
    }
    
    public synchronized HashMap<String, IMatcher> getMatchers()
    {
        return matchers;
    }
    
    public boolean save()
    {
        return xmlIo.save();
    }
}

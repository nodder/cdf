package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.XmlIO;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdField;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CollectXmlParser</p>
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

public class CdfMockServerXmlParser
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static CdfMockServerXmlParser instance = new CdfMockServerXmlParser();
    private XmlIO xmlIo;
    HashMap<String, EntryIdField[]> mapEntryId = null;
    HashMap<String, String> mapBulkPoolToEntryId = null;
    HashMap<String, String> mapBulkPoolToParams = null;
    
    private CdfMockServerXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-mock-server.xml");
        xmlIo = new XmlIO(file);
    }

    public static CdfMockServerXmlParser getInstance() 
    {
        return instance;
    }
    
    public String getServerIP()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Server_IP").getText();
    }
    
    public String getServerPort()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Server_Port").getText();
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Remote_Object_Name").getText();
    }
   
    public String getTempFilePath()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getAttributeValue("tempFilePath");
    }
    
    public int getFtpConnPoolNum()
    {
        return Integer.parseInt(xmlIo.getRootElement().getChild("Mock_DSLAM").getAttributeValue("ftpConnPool"));
    }
    
    public int getMockedDSLAMNumber()
    {
        try
        {
            int mockNum = Integer.parseInt(xmlIo.getRootElement().getChild("Mock_DSLAM").getAttributeValue("mockNumber"));
            System.out.println("Get mock number from Mock server xml :" + mockNum);
            return mockNum;
        }
        catch(NumberFormatException  ex)
        {
            LogPrint.logError(logger, "", ex);
            return 0;
        }
    }
        
    public synchronized HashMap<String, EntryIdField[]> getMapEntryId()
    {
        if(this.mapEntryId == null)
        {
            mapEntryId = new HashMap<String, EntryIdField[]>();

            List elements = xmlIo.getRootElement().getChild("File_Contents").getChildren("EntryId");
            Iterator it = elements.iterator();
            while(it.hasNext())
            {
                Element e = (Element)it.next();

                mapEntryId.put(e.getAttributeValue("type"), getEntryIdFields(e));
            }
        }

        return mapEntryId;
    }
    
    private EntryIdField[] getEntryIdFields(Element group)
    {
        Element e = group.getChild("Indicate");
        if(e != null)
        {
            String start = e.getAttributeValue("start");
            String end = e.getAttributeValue("end");

            String[] startFields = start.split("/");
            String[] endFields = end.split("/");

            if((startFields.length != endFields.length) || startFields.length == 0)
            {
                return null;
            }

            EntryIdField[] fields = new EntryIdField[startFields.length];

            for(int i = 0; i <= startFields.length - 1; i++)
            {
                fields[i] = new EntryIdField(Integer.parseInt(startFields[i]), Integer.parseInt(endFields[i]));
            }

            return fields;
        }
        
        return new EntryIdField[0];
    }  
    
    public synchronized HashMap<String, String> getMapBulkPoolToEntryId()
    {
        if(this.mapBulkPoolToEntryId == null)
        {
            mapBulkPoolToEntryId = new HashMap<String, String>();

            List elements = xmlIo.getRootElement().getChild("BulkPools").getChildren("BulkPool");
            Iterator it = elements.iterator();
            while(it.hasNext())
            {
                Element e = (Element)it.next();

                String entryId = e.getChild("EntryId").getText();
                mapBulkPoolToEntryId.put(e.getAttributeValue("name"), entryId);
            }
        }

        return mapBulkPoolToEntryId;
    }
    
    public synchronized HashMap<String, String> getMapBulkPoolToParams()
    {
        if(this.mapBulkPoolToParams == null)
        {
            mapBulkPoolToParams = new HashMap<String, String>();

            List elements = xmlIo.getRootElement().getChild("BulkPools").getChildren("BulkPool");
            Iterator it = elements.iterator();
            while(it.hasNext())
            {
                Element e = (Element)it.next();

                String params = e.getChild("Params").getText();
                mapBulkPoolToParams.put(e.getAttributeValue("name"), params);
            }
        }

        return mapBulkPoolToParams;
    }
    
    public static void main(String[] args) throws Exception
    {
        HashMap<String, EntryIdField[]> map = CdfMockServerXmlParser.getInstance().getMapEntryId();
        
        Iterator<Entry<String, EntryIdField[]>> it = map.entrySet().iterator();
        
        while(it.hasNext())
        {
            Entry<String, EntryIdField[]> entry = (Entry<String, EntryIdField[]>)(it.next());
            
            String group = entry.getKey();
            EntryIdField[] fields = entry.getValue();
            
            System.out.println("===" + group);
            for(EntryIdField f : fields)
            {
                System.out.println(f);
            }
        }
        
//        HashMap<String, String> map1 = CdfMockXmlParser.getInstance().getMapBulkPoolToEntryId();
//        
//        Iterator<Entry<String, String>> it = map1.entrySet().iterator();
//        
//        while(it.hasNext())
//        {
//            Entry<String, String> entry = (Entry<String, String>)(it.next());
//            
//            String group = entry.getKey();
//            String field = entry.getValue();
//            
//            System.out.println(group + ":" + field);
//        }
        
//        HashMap<String, String> map2 = CdfMockServerXmlParser.getInstance().getMapBulkPoolToParams();
//        
//        Iterator<Entry<String, String>> it = map2.entrySet().iterator();
//        
//        while(it.hasNext())
//        {
//            Entry<String, String> entry = (Entry<String, String>)(it.next());
//            
//            String group = entry.getKey();
//            String field = entry.getValue();
//            
//            System.out.println(group + ":" + field);
//        }
    }
}

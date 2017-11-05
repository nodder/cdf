package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;

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

public class CollectXmlParser
{
    private static CollectXmlParser instance = new CollectXmlParser();
    private XmlIO xmlIo;
    
    private String[] supportedNETypes = null;
    
    private CollectXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-sub-collect.xml");
        xmlIo = new XmlIO(file);
    }

    public static CollectXmlParser getInstance() 
    {
        return instance;
    }
    
    //Sub_Server
    public String getSubServerAlias()
    {
        return xmlIo.getRootElement().getChild("Sub_Server").getChild("Alias").getText();
    }
    
    public void setSubServerAlias(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("Alias").setText(text);
    }
    
    public String getSubServerIP()
    {
        return xmlIo.getRootElement().getChild("Sub_Server").getChild("Server_IP").getText();
    }
    
    public void setSubServerIP(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("Server_IP").setText(text);
    }
    
    public String getScheduledPeriod()
    {
        return xmlIo.getRootElement().getChild("Sub_Server").getChild("Scheduled_Period").getText();
    }
    
    public void setScheduledPeriod(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("Scheduled_Period").setText(text);
    }
        
    public void setHeartBeat(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("Heart_Beat").setText(text);
    }
    
    public String getHeartBeat()
    {
        return xmlIo.getRootElement().getChild("Sub_Server").getChild("Heart_Beat").getText();
    }
    
    public void setCheckXml(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("isCheckXml").setText(text);
    }
    
    public boolean getCheckXml()
    {
        return "true".equalsIgnoreCase(xmlIo.getRootElement().getChild("Sub_Server").getChild("isCheckXml").getText());
    }
    
    public void setSupportedNETypes(String text)
    {
        xmlIo.getRootElement().getChild("Sub_Server").getChild("NE_Type_Filter").setText(text);
    }
    
    public synchronized String[] getSupportedNETypes()
    {
        if(supportedNETypes == null)
        {
            String neTypes = xmlIo.getRootElement().getChild("Sub_Server").getChild("NE_Type_Filter").getText();
            this.supportedNETypes = neTypes.split(",");
        }
        
        return supportedNETypes;
    }
        
    public boolean getReduceIO()
    {
        return "true".equalsIgnoreCase(xmlIo.getRootElement().getChild("Sub_Server").getChild("Reduce_IO").getText());
    }
    
    public boolean getDeletePreFiles()
    {
        return "true".equalsIgnoreCase(xmlIo.getRootElement().getChild("Sub_Server").getChild("Delete_PreFiles").getText());
    }
    
    //Thread
    public String getThreadNum()
    {
        return xmlIo.getRootElement().getChild("Thread").getChild("Thread_Num").getText();
    }
    
    public void setThreadNum(String text)
    {
        xmlIo.getRootElement().getChild("Thread").getChild("Thread_Num").setText(text);
    }
    
    public String getPoolReinitialTime()
    {
        return xmlIo.getRootElement().getChild("Thread").getChild("Pool_Reinitial_Time").getText();
    }
    
    public void setPoolReinitialTime(String text)
    {
        xmlIo.getRootElement().getChild("Thread").getChild("Pool_Reinitial_Time").setText(text);
    }
    
    //Database
    public String getDbType()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("DbType").getText();
    }
    
    public void setDbType(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("DbType").setText(text);
    }
    
    public String getDbPort()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("Port").getText();
    }
    
    public void setDbPort(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("Port").setText(text);
    }
    
    public String getDbServerIp()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("Server_Ip").getText();
    }
    
    public void setDbServerIp(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("Server_Ip").setText(text);
    }
    
    public String getDbName()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("DbName").getText();
    }
    
    public void setDbName(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("DbName").setText(text);
    }
    
    public String getDbSuperUser()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("Super_User").getText();
    }
    
    public void setDbSuperUser(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("Super_User").setText(text);
    }
    
    public String getDbSuperUserPassword()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("Super_User_Password").getText();
    }
    
    public void setDbSuperUserPassword(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("Super_User_Password").setText(text);
    }

    public String getDBMaxConnect()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("Max_Connect").getText();
    }
    
    public void setDBMaxConnect(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("Max_Connect").setText(text);
    }
    
    public String getDBPath()
    {
        return xmlIo.getRootElement().getChild("Database").getChild("DbPath").getText();
    }
    
    public void setDBPath(String text)
    {
        xmlIo.getRootElement().getChild("Database").getChild("DbPath").setText(text);
    }
    
    //Remote_Dispatch_Server
    public String getRemoteMainServerIp()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_IP").getText();
    }
    
    public void setRemoteMainServerIp(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_IP").setText(text);
    }
    
    public String getRemoteServerPort()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_Port").getText();
    }
    
    public void setRemoteServerPort(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_Port").setText(text);
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Remote_Object_Name").getText();
    }
    
    public void setRemoteObjectName(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Remote_Object_Name").setText(text);
    }
    
    //FTP
    public String getTransferType()
    {
        return xmlIo.getRootElement().getChild("FTP").getChild("Transfer_Type").getText();
    }
    
    public void setTransferType(String text)
    {
        xmlIo.getRootElement().getChild("FTP").getChild("Transfer_Type").setText(text);
    }
    
    public boolean isUsingSFTP()
    {
        return "SFTP".equalsIgnoreCase(getTransferType());
    }
    
    public String getFTPServerIp()
    {
        return xmlIo.getRootElement().getChild("FTP").getChild("Server_IP").getText();
    }
    
    public void setFTPServerIp(String text)
    {
        xmlIo.getRootElement().getChild("FTP").getChild("Server_IP").setText(text);
    }
    
    public String getFTPUser()
    {
        return xmlIo.getRootElement().getChild("FTP").getChild("User").getText();
    }
    
    public void setFTPUser(String text)
    {
        xmlIo.getRootElement().getChild("FTP").getChild("User").setText(text);
    }
    
    public String getFTPPassword()
    {
        return xmlIo.getRootElement().getChild("FTP").getChild("Password").getText();
    }
    
    public void setFTPPassword(String text)
    {
        xmlIo.getRootElement().getChild("FTP").getChild("Password").setText(text);
    }
    
    public String getFTPMainDir()
    {
        return xmlIo.getRootElement().getChild("FTP").getChild("Main_Dir").getText();
    }
    
    public void setFTPMainDir(String text)
    {
        xmlIo.getRootElement().getChild("FTP").getChild("Main_Dir").setText(text);
    }
    
    //SNMP
    public String getSNMPTimeout()
    {
        return xmlIo.getRootElement().getChild("SNMP").getChild("Timeout").getText();
    }
    
    public void setSNMPTimeout(String text)
    {
        xmlIo.getRootElement().getChild("SNMP").getChild("Timeout").setText(text);
    }
    
    public String getSNMPRetries()
    {
        return xmlIo.getRootElement().getChild("SNMP").getChild("Retries").getText();
    }
    
    public void setSNMPRetries(String text)
    {
        xmlIo.getRootElement().getChild("SNMP").getChild("Retries").setText(text);
    }
    
    //Fast_SNMP
    public String getFastSNMPTimeout()
    {
        return xmlIo.getRootElement().getChild("Fast_SNMP").getChild("Timeout").getText();
    }
    
    public void setFastSNMPTimeout(String text)
    {
        xmlIo.getRootElement().getChild("Fast_SNMP").getChild("Timeout").setText(text);
    }
    
    public String getFastSNMPRetries()
    {
        return xmlIo.getRootElement().getChild("Fast_SNMP").getChild("Retries").getText();
    }
    
    public void setFastSNMPRetries(String text)
    {
        xmlIo.getRootElement().getChild("Fast_SNMP").getChild("Retries").setText(text);
    }
        
    //BulkPool_Validation
    public String getCsvFileTimeTolerance()
    {
        return xmlIo.getRootElement().getChild("BulkPool_Validation").getChild("Time_Tolerance").getText();
    }
    
    public boolean save()
    {
        return xmlIo.save();
    }
    
    public static void main(String[] args)
    {        
//        HashMap<String, String[]> map = CollectXmlParser.getInstance().getGroupToMergedBulkPoolMapInfo();
//        Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
//        
//        while(it.hasNext())
//        {
//            Entry<String, String[]> entry = (Entry<String, String[]>)(it.next());
//            
//            String group = entry.getKey();
//            String[] bulkpools = entry.getValue();
//            
//            System.out.println("===" + group);
//            for(String b : bulkpools)
//            {
//                System.out.println(b);
//            }
//        }
        
//        HashMap<String, String> map = CollectXmlParser.getInstance().getBulkPoolToReadProxy();
//        Iterator<Entry<String, String>> it = map.entrySet().iterator();
//        
//        while(it.hasNext())
//        {
//            Entry<String, String> entry = (Entry<String, String>)(it.next());
//            
//            String bulkPool = entry.getKey();
//            String readProxy = entry.getValue();
//            if(readProxy == null)
//            {
//                System.out.println("null true");
//            }
//            System.out.println(bulkPool + ":" + readProxy);
//        }
        
//      HashMap<String, FileAppendInfo[]> map = CollectXmlParser.getInstance().getBulkPoolToAppendInfos();
//      HashMap<String, String[]> map = CollectXmlParser.getInstance().getGroupToFixedArea();
//      
//      Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
//      
//      while(it.hasNext())
//      {
//          Entry<String, String[]> entry = (Entry<String, String[]>)(it.next());
//          
//          String bulkPool = entry.getKey();
//          String[] fileAppInfos = entry.getValue();
//          
//          System.out.println("======" + bulkPool);
//          for(String info : fileAppInfos)
//          {
//              System.out.println(info);
//          }
//      }
        
        System.out.println(CollectXmlParser.getInstance().getReduceIO());
    }
}

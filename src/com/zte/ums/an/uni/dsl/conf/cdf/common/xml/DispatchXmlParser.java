package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;

/**
 * <p>文件名称: DispatchXmlParser</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月28日</p>
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

public class DispatchXmlParser
{
    private static DispatchXmlParser instance = new DispatchXmlParser();
    private XmlIO xmlIo;
    
    private DispatchXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-dispatch.xml");
        xmlIo = new XmlIO(file);
    }

    public static DispatchXmlParser getInstance() 
    {
        return instance;
    }
    
    public String getServerIP()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Server_IP").getText();
    }
    
    public void setServerIP(String text)
    {
        xmlIo.getRootElement().getChild("Main_Service").getChild("Server_IP").setText(text);
    }
    
    public String getServerPort()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Server_Port").getText();
    }
    
    public void setServerPort(String text)
    {
        xmlIo.getRootElement().getChild("Main_Service").getChild("Server_Port").setText(text);
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Main_Service").getChild("Remote_Object_Name").getText();
    }
    
    public void setRemoteObjectName(String text)
    {
        xmlIo.getRootElement().getChild("Main_Service").getChild("Remote_Object_Name").setText(text);
    }
    
    //DB
    public String getDbType()
    {
        return xmlIo.getRootElement().getChild("NetNumen_DB").getChild("Type").getText();
    }
    
    public void setDbType(String text)
    {
        xmlIo.getRootElement().getChild("NetNumen_DB").getChild("Type").setText(text);
    }
    
    public String getDbServerIp()
    {
        return xmlIo.getRootElement().getChild("NetNumen_DB").getChild("IP").getText();
    }
    
    public void setDbServerIP(String text)
    {
        xmlIo.getRootElement().getChild("NetNumen_DB").getChild("IP").setText(text);
    }
    
    public String getDbPort()
    {
        return xmlIo.getRootElement().getChild("NetNumen_DB").getChild("Port").getText();
    }
    
    public void setDbPort(String text)
    {
        xmlIo.getRootElement().getChild("NetNumen_DB").getChild("Port").setText(text);
    }
    
    public String getDbName()
    {
        return xmlIo.getRootElement().getChild("NetNumen_DB").getChild("DbName").getText();
    }
    
    public void setDbName(String text)
    {
        xmlIo.getRootElement().getChild("NetNumen_DB").getChild("DbName").setText(text);
    }
    
    public String getDispatchPeriod()
    {
        return xmlIo.getRootElement().getChild("Dispatch").getChild("Period").getText();
    }
    
    public void setDispatchPeriod(String text)
    {
        xmlIo.getRootElement().getChild("Dispatch").getChild("Period").setText(text);
    }
    
    public String getDispatchNumPerRound()
    {
        return xmlIo.getRootElement().getChild("Dispatch").getChild("DispatchNum_Per_Round").getText();
    }
    
    public void setDispatchNumPerRound(String text)
    {
        xmlIo.getRootElement().getChild("Dispatch").getChild("DispatchNum_Per_Round").setText(text);
    }
    
    public String getSubSvrConnMaintenancePeriod()
    {
        return xmlIo.getRootElement().getChild("Dispatch").getChild("SubSvrConnMaintenance_Period").getText();
    }
    
    public void setSubSvrConnMaintenancePeriod(String text)
    {
        xmlIo.getRootElement().getChild("Dispatch").getChild("SubSvrConnMaintenance_Period").setText(text);
    }
    
    public void setSubServerTimeout(String text)
    {
        xmlIo.getRootElement().getChild("Dispatch").getChild("SubSvr_Timeout").setText(text);
    }
    
    public String getSubServerTimeout()
    {
        return xmlIo.getRootElement().getChild("Dispatch").getChild("SubSvr_Timeout").getText();
    }
    
    public boolean save()
    {
        return xmlIo.save();
    }
}

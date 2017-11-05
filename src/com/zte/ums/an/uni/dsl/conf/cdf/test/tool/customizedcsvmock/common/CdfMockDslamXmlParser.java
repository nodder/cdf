package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common;

import java.io.File;

import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.XmlIO;

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

public class CdfMockDslamXmlParser
{
    private static CdfMockDslamXmlParser instance = new CdfMockDslamXmlParser();
    private XmlIO xmlIo;
    
    private CdfMockDslamXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-mock-dslam.xml");
        xmlIo = new XmlIO(file);
    }

    public static CdfMockDslamXmlParser getInstance() 
    {
        return instance;
    }
    
    public String getServerIP()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Server_IP").getText();
        
    }
    
    public String getServerPort()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Server_Port").getText();
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Remote_Object_Name").getText();
    }
   
 
    public static void main(String[] args) throws Exception
    {
        System.out.println(CdfMockDslamXmlParser.getInstance().getRemoteObjectName());
        System.out.println(CdfMockDslamXmlParser.getInstance().getServerIP());
        System.out.println(CdfMockDslamXmlParser.getInstance().getServerPort());
    }
}

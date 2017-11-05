package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockDslamXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi.IDslamMock;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: DispatchRemoteAgent.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月23日</p>
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
public class DslamMockRemoteAgent
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static DslamMockRemoteAgent instance = new DslamMockRemoteAgent();
    private IDslamMock DslamMockInterface = null;

    private DslamMockRemoteAgent()
    {
        initDslamMockInterface();
    }

    public static DslamMockRemoteAgent getInstance()
    {
        return instance;
    }

    public IDslamMock getDslamMockInterface()
    {
        if(DslamMockInterface == null)
        {
            LogPrint.logError(logger, "Please ensure the DSLAM Mocker Interface server already startup.");
        }
        return DslamMockInterface;
    }

    private void initDslamMockInterface()
    {
        String mainServerIP = CdfMockDslamXmlParser.getInstance().getServerIP();
        String mainServerPort = CdfMockDslamXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = CdfMockDslamXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            DslamMockInterface = (IDslamMock)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
            DslamMockInterface = null;
        }
    }
}

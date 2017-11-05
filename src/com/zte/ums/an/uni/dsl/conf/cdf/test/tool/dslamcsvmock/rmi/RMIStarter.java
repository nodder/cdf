package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: StartRmi.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-22</p>
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
public class RMIStarter
{
    private static Logger logger = ZXLogger.getLogger(RMIStarter.class.getName());
    
    public static void start()
    {
        String mainServerIP = CdfMockServerXmlParser.getInstance().getServerIP();
        String mainServerPort = CdfMockServerXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = CdfMockServerXmlParser.getInstance().getRemoteObjectName();
        
        try
        {
            System.setProperty("java.rmi.server.hostname", mainServerIP);
            LocateRegistry.createRegistry(Integer.parseInt(mainServerPort));
            DslMockRmiImplement dslMockService = new DslMockRmiImplement();
            
            String rmiName = "rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject;
            Naming.rebind(rmiName, dslMockService);
            
            LogPrint.logInfo(logger, "RMI starts at " + rmiName);
        }
        catch(Exception e)
        {
        }
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.report;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: DispatchRemoteAgent.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月29日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi
 */
public class DispatchRemoteAgent
{
    protected Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private static DispatchRemoteAgent instance = null;
    private IDispatch dispatchInterface = null;

    private DispatchRemoteAgent()
    {
        initDispatchInterface();
    }

    public static DispatchRemoteAgent getInstance()
    {
        instance = new DispatchRemoteAgent();
        return instance;
    }

    public IDispatch getDispatchInterface()
    {
        return dispatchInterface;
    }

    private synchronized void initDispatchInterface()
    {
        String mainServerIP = ReportXmlParser.getInstance().getServerIP();
        String mainServerPort = ReportXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = ReportXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            dispatchInterface = (IDispatch)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
            dispatchInterface = null;
        }
    }
}

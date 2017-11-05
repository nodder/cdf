package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.rmi.Naming;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;

/**
 * <p>文件名称: DispatchRemoteAgent.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年9月1日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author lixiaochun
 */
public class DispatchRemoteAgent
{
    private static DispatchRemoteAgent instance = null;
    private IDispatch dispatchInterface = null;

    private DispatchRemoteAgent()
    {
        initDispatchInterface();
    }

    public static DispatchRemoteAgent getInstance()
    {
        if(instance == null)
        {
            instance = new DispatchRemoteAgent();
        }
        return instance;
    }

    public IDispatch getDispatchInterface()
    {
        if(SubCollectCache.needReconnectToDispatchSvr)
        {
            initDispatchInterface();
        }
        
        return dispatchInterface;
    }
    
    public void reconnect()
    {
        initDispatchInterface();
    }

    private void initDispatchInterface()
    {
        String mainServerIP = CollectXmlParser.getInstance().getRemoteMainServerIp();
        String mainServerPort = CollectXmlParser.getInstance().getRemoteServerPort();
        String dispatchRemoteObject = CollectXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            dispatchInterface = (IDispatch)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
        }
    }
}

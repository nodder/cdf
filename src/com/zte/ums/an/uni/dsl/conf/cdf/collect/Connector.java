package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: Connector</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
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
public class Connector implements Runnable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private SubCollectInfo subServer = null;

    Connector(SubCollectInfo subServer)
    {
        this.subServer = subServer;
    }

    public void regAndSendHeartBeat()
    {
        try
        {
            subServer.nanoTime = System.nanoTime();
            DispatchRemoteAgent.getInstance().getDispatchInterface().heartBeatFroCollectServer(subServer);
            LogPrint.logDebug(logger, subServer.getName() + " sends heart beat.");
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "HartBeart error, Please ensure the dispatch server already startup.");
            SubCollectCache.needReconnectToDispatchSvr = true;
        }
    }

    public void run()
    {
        regAndSendHeartBeat();
    }
}
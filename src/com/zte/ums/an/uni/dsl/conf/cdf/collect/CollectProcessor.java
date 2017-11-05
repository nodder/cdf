package com.zte.ums.an.uni.dsl.conf.cdf.collect;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.wathdog.Watchdog;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;


/**
 * <p>文件名称: CollectProcessor</p>
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
 *    
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author lixiaochun
 */
public class CollectProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static CollectProcessor instance = null;

    private CollectProcessor()
    {

    }

    public static CollectProcessor getInstance()
    {
        if(instance == null)
        {
            instance = new CollectProcessor();
        }
        return instance;
    }
 
    public void start(SubCollectInfo subCollectInfo)
    {
        try
        {
            // 初始化主分发服务器连接
            if(DispatchRemoteAgent.getInstance().getDispatchInterface() != null)
            {
                startConnector(subCollectInfo);
                startCollector(subCollectInfo);
            }
            else
            {
                LogPrint.logError(logger, "Please ensure the dispatch server already startup, then restart this sub-collect server.");
            }
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
    }

    private void startWatchDog(Collector collector)
    {
        int timeout = 1000 * Integer.parseInt(CollectXmlParser.getInstance().getPoolReinitialTime());
        Watchdog w = new Watchdog(timeout);
        w.addTimeoutObserver(collector);
        w.start();
    }

    // 周期性从主分发服务器获取待采集网元列表然后从网元获取CSV文件并将解析后的数据保存至数据库
    private void startCollector(SubCollectInfo subCollectInfo)
    {
        Collector collector = new Collector(subCollectInfo);
        int collectPeriod = Integer.parseInt(CollectXmlParser.getInstance().getScheduledPeriod());
        final ScheduledExecutorService collectScheduler = Executors.newSingleThreadScheduledExecutor();
        collectScheduler.scheduleAtFixedRate(collector, 0, collectPeriod, TimeUnit.SECONDS);
    
        startWatchDog(collector);
    }

    // 注册采集服务器并周期性发送心跳
    private void startConnector(SubCollectInfo subCollectInfo)
    {
        int heartBeatPeriod = Integer.parseInt(CollectXmlParser.getInstance().getHeartBeat());
        final ScheduledExecutorService connectScheduler = Executors.newSingleThreadScheduledExecutor();
        connectScheduler.scheduleAtFixedRate(new Connector(subCollectInfo), 0, heartBeatPeriod, TimeUnit.SECONDS);
    }
}

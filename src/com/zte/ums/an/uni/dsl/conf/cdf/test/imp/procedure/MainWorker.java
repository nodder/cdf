package com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.CollectPoints;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.subsvrlist.SubServerConnManager;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: MainWorker.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月14日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi_10118495
 */
public class MainWorker implements IDispatch
{
    private Logger logger = ZXLogger.getLogger(MainWorker.class.getName());
    
    @Override
    public void heartBeatFroCollectServer(SubCollectInfo subCollectInfo)
    {
        SubServerConnManager.getInstance().updateSubServerList(subCollectInfo);
        
        LogPrint.logDebug(logger, "Receive heartbeat from Collect-Server" + subCollectInfo.getName() + ".");
    }

    @Override
    public synchronized Vector getCollectNesFroDispatchServer(SubCollectInfo subCollectInfo)
    {
        int neNum = Integer.parseInt(DispatchXmlParser.getInstance().getDispatchNumPerRound());
        
        Vector nes = CollectPoints.getNes(neNum);
        
        if(nes.size() == 0)
        {
            LogPrint.logInfo(logger, "Dectect a collection task request from " + subCollectInfo.getName()
                                     + " , but there is no task for it right now.");
        }
        else
        {
            LogPrint.logInfo(logger, "Detect a collection task request from " + subCollectInfo.getName() + ". Dispatch " + nes.size() 
                + " DSLAMs, and left with " + CollectPoints.getUnprocessedNeCount() + " DSLAMs in all.\n");
        }

        return nes;
    }

    @Override
    public ArrayList<SubCollectInfo> getSubCollectSvrList() throws RemoteException
    {
        LogPrint.logInfo(logger, "Request current sub-collect server list from report server.");
        return SubServerConnManager.getInstance().getSubServerList();
    }
}

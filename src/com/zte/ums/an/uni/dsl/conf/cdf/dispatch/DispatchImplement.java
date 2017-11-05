package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;

/**
 * <p>文件名称: DispatchImplement.java</p>
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
public class DispatchImplement extends UnicastRemoteObject implements IDispatch
{
    private IDispatch realWorker;

    public DispatchImplement() throws RemoteException
    {
        this.realWorker = DispatchFactory.createDispatchProc();
    }

    @Override
    public void heartBeatFroCollectServer(SubCollectInfo subCollectInfo) throws RemoteException
    {
        realWorker.heartBeatFroCollectServer(subCollectInfo);
    }

    @Override
    public Vector getCollectNesFroDispatchServer(SubCollectInfo subCollectInfo) throws RemoteException
    {
        return realWorker.getCollectNesFroDispatchServer(subCollectInfo);
    }

    @Override
    public ArrayList<SubCollectInfo> getSubCollectSvrList() throws RemoteException
    {
        return realWorker.getSubCollectSvrList();
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;

/**
 * <p>文件名称: DispatchInterface.java</p>
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
public interface IDispatch extends Remote
{
    /**
     * Invoked by sub server send heart beat to main dispatcher
     */
    public void heartBeatFroCollectServer(SubCollectInfo subServer) throws RemoteException;

    /**
     * Invoked by sub server to get NEs to be collected
     */
    public Vector getCollectNesFroDispatchServer(SubCollectInfo subServer) throws RemoteException;
    
    /**
     * Invoked by report server to get active sub-server list
     */
    public ArrayList<SubCollectInfo> getSubCollectSvrList() throws RemoteException;
}

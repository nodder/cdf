package com.zte.ums.an.uni.dsl.conf.cdf.collect.listener;

import java.util.Vector;

/**
 * <p>文件名称: ListenerCollection.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-31</p>
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
public class ListenerCollection
{
    private Vector<ICollectorListener> fListeners = new Vector<ICollectorListener>();
    private static ListenerCollection instance = new ListenerCollection();
    
    public static ListenerCollection getInstance()
    {
        return instance;
    }
    
    private ListenerCollection()
    {
    }
    
    public void addListener(ICollectorListener fListener)
    {
        fListeners.add(fListener);
    }
    
    public void removeListener(ICollectorListener fListener)
    {
        fListeners.remove(fListener);
    }
    
    public void notifyOnStart()
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onStart();
        }
    }
    
    public void notifyOnStartCollectSingleDSLAM(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onStartCollectSingleDSLAM(arg);
        }
    }
        
    public void notifyOnBeforeCollect(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onBeforeCollect(arg);
        }
    }
    
    public void notifyOnAfterCollect(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onAfterCollect(arg);
        }
    }
    
    public void notifyOnBeforeParser(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onBeforeParser(arg);
        }
    }
    
    public void notifyOnAfterParser(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onAfterPaser(arg);
        }
    }

    public void notifyOnFinishCollectSingleDSLAM(boolean isCollectSuccess, Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onFinishCollectSingleDSLAM(isCollectSuccess, arg);
        }
    }
}

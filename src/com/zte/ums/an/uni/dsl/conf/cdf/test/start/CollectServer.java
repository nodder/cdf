package com.zte.ums.an.uni.dsl.conf.cdf.test.start;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ListenerCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp.MassiveCollectorListener;

/**
 * <p>文件名称: DispatchServer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-28</p>
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
public class CollectServer
{
    public static void main(String[] args)
    {
//        CdfMockUtil.setUserDirToParentPath();
        ListenerCollection.getInstance().addListener(new MassiveCollectorListener());

        com.zte.ums.an.uni.dsl.conf.cdf.collect.CollectServer.main(args);
    }
}

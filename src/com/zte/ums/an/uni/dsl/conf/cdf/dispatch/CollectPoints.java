package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>文件名称: CollectPoints.java</p>
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
public class CollectPoints
{
    /** 待采集网元容器 */
    private static Vector snmpNodeList = new Vector();
    
    public synchronized static void AddNes(ArrayList<SnmpNode> nes)
    {
        snmpNodeList.addAll(nes);
    }

    /** 获取前N个待采集网元列表 */
    public synchronized static Vector getNes(int stepLength)
    {
        Vector ret = new Vector();

        int len = snmpNodeList.size() > stepLength ? stepLength : snmpNodeList.size();

        for(int i = 0; i < len; i++)
        {
            ret.add(snmpNodeList.get(i));
        }
        for(int i = 0; i < len; i++)
        {
            snmpNodeList.remove(0);
        }
        return ret;
    }

    public synchronized static boolean isEmpty()
    {
        return snmpNodeList.isEmpty();
    }

    /** 获取待采集网元个数 */
    public synchronized static int getUnprocessedNeCount()
    {
        return snmpNodeList.size();
    }
}

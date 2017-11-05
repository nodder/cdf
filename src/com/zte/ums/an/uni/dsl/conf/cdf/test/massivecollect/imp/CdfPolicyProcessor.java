package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.CollectPoints;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.AbsCdfPolicyProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.ipmock.IPv4AddrMocker;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CdfPolicyProcessor</p>
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
 * @author ChenDuoduo_10087118
 */
public class CdfPolicyProcessor extends AbsCdfPolicyProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    @Override
    public void runPolicy()
    {
        try
        {
            synchronized(CollectPoints.class)
            {
                if(CollectPoints.isEmpty())
                {
                    CollectPoints.AddNes(getSnmpNodesListWithCP());
                    LogPrint.logInfo(logger, "Get " + CollectPoints.getUnprocessedNeCount() + " NE(s) by mock and add to CDF task.\n");
                }
                else
                {
                    LogPrint.logInfo(logger, "Check there remains " + CollectPoints.getUnprocessedNeCount() + "NEs to collect. Do Nothing.\n");
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    
    private static ArrayList<SnmpNode> getSnmpNodesListWithCP()
    {
        ArrayList<SnmpNode> snmpNodes = new ArrayList<SnmpNode>();

        String[] ips = IPv4AddrMocker.getInstance().getAllIp();
        
        for(String ip : ips)
        {
            snmpNodes.add(mockSnmpNode(ip));
        }
        return snmpNodes;
    }

    private static SnmpNode mockSnmpNode(String ip)
    {
        SnmpNode snmpNode = new SnmpNode();
        
        snmpNode.setIpAddress(ip);
        snmpNode.setName("SnmpNode");
        snmpNode.setMoc("ZXDSL9836");
        snmpNode.setSnmpCommunity("public");
        snmpNode.setSnmpPort(161);
        snmpNode.setSnmpWriteCommunity("private");
        snmpNode.setSnmpVersion("v2c");

        return snmpNode;
    }
}

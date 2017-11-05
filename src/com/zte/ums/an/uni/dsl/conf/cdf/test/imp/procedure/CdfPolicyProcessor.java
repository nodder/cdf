package com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.CollectPoints;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.AbsCdfPolicyProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
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
 * @author jingxueshi_10118495
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
                    LogPrint.logInfo(logger, "Get " + CollectPoints.getUnprocessedNeCount() + " NE(s) from EMS and add to CDF task.\n");
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
    
    /**
     * 从网管数据库中获取所有配置了采集点的网元SNMPNODE信息
     * @return Vector
     */
    private static ArrayList<SnmpNode> getSnmpNodesListWithCP()
    {
        ArrayList<SnmpNode> snmpNodes = new ArrayList<SnmpNode>();

        int neCounts = CdfMockServerXmlParser.getInstance().getMockedDSLAMNumber();
        for(int i = 0; i < neCounts; i++)
        {
            snmpNodes.add(mockSnmpNode(i));
        }
        return snmpNodes;
    }

    private static SnmpNode mockSnmpNode(int i)
    {
        SnmpNode snmpNode = new SnmpNode();
        snmpNode.setIpAddress(Integer.toString(i));
        
        return snmpNode;
    }

}

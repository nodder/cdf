package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock.CPNMocker;

/**
 * <p>文件名称: MockedDataBuilder</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月21日</p>
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

public class MockedDataBuilder
{
    public static final String HEADER = "rack/shelf/slot/port,"
            + "zxDslTrunkingOutOctets,zxDslTrunkingInOctets,zxDslTrunkingOutUCastPkts,zxDslTrunkingInUCastPkts,"
            + "zxDslTrunkingOutMulticastPkts,zxDslTrunkingInMulitcastPkts,zxDslTrunkingOutBroadcastPkts,zxDslTrunkingInBroadcastPkts," 
            + "zxDslTrunkingOutDiscardRatio,zxDslTrunkingInDiscardRatio,zxDslTrunkingInErrorRatio,zxDslTrunkingOutDiscards," 
            + "zxDslTrunkingInDiscards,zxDslTrunkingInErrors";

    private static int paramNum = HEADER.split(",").length - 1;;
        
    public static String mockHeader()
    {
        return HEADER;
    }
    
    public static String mockDataLine()
    {
        StringBuffer line = new StringBuffer(CPNMocker.getInstance().getNextCPN());
        
        for(int i = 0; i < paramNum - 1; i++)
        {
            line.append("," + mockData(i));
        }
        return line.toString();
    }
            
    private static int mockData(int length)
    {
        return ((int)(Math.random() * 100000)) % 10000;
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock;

/**
 * <p>文件名称: TestCPNMocker</p>
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

public class TestCPNMocker
{
    public static void main(String[] args)
    {
        for(long i = 0; i < 200; i++)
        {
            System.out.println(CPNMocker.getInstance().getNextCPN());
        }
    }
}

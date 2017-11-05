package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.AbstractChainField;

/**
 * <p>文件名称: IPv4AddrMocker</p>
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

public class IPv4AddrMocker
{
    private static IPv4AddrMocker instance = new IPv4AddrMocker();
    
    private AbstractChainField ipv4Field1 = new IPv4Field();
    private AbstractChainField ipv4Field2 = new IPv4Field();
    private AbstractChainField ipv4Field3 = new IPv4Field();
    private AbstractChainField ipv4Field4 = new IPv4Field();
    
    private StringBuffer ipAddr = new StringBuffer();
    
    private IPv4AddrMocker()
    {        
        ipv4Field4.setNextField(ipv4Field3);
        ipv4Field3.setNextField(ipv4Field2);
        ipv4Field2.setNextField(ipv4Field1);
        ipv4Field1.setNextField(ipv4Field4);
    }
    
    public static IPv4AddrMocker getInstance()
    {
        return instance;
    }
    
    public String getNextIPv4Addr()
    {
        ipAddr.delete(0, ipAddr.length());
        String nextIp = (ipAddr.append(ipv4Field1.getFieldValue()).append(".").append(ipv4Field2.getFieldValue()).append(".")
                        .append(ipv4Field3.getFieldValue()).append(".").append(ipv4Field4.getFieldValue())).toString();

        ipv4Field4.next();

        return nextIp;
    }

}

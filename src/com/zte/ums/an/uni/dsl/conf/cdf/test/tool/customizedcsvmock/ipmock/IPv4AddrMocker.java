package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.ipmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdBuilder;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdField;

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
    private static final int TOP = CdfMockServerXmlParser.getInstance().getMockedDSLAMNumber();
    
    private EntryIdBuilder builder;
    private String[] allMockedIP = null;
        
    private IPv4AddrMocker()
    {   
        EntryIdField f1 = new EntryIdField(10, 254);
        EntryIdField f2 = new EntryIdField(10, 254);
        EntryIdField f3 = new EntryIdField(10, 254);
        EntryIdField f4 = new EntryIdField(1, 254);
        
        builder = new EntryIdBuilder(new EntryIdField[] {f1, f2, f3, f4}, ".");
    }
    
    public static IPv4AddrMocker getInstance()
    {
        return instance;
    }
    
    public synchronized String[] getAllIp()
    {
        if(allMockedIP == null)
        {
            initAllMockedIP();
        }
        
        return allMockedIP;
    }
    
    private void initAllMockedIP()
    {
        allMockedIP = new String[TOP];
        
        int i = 0;
        while(i < TOP)
        {
            allMockedIP[i++] = builder.getNext();
        }
    }

    public static void main(String[] args)
    {
        String[] all = IPv4AddrMocker.getInstance().getAllIp();
        
        for(String ip : all)
        {
            System.out.println(ip);
        }
    }
}

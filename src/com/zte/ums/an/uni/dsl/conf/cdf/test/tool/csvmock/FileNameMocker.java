package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock.IPv4AddrMocker;

/**
 * <p>文件名称: FileNameMocker</p>
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

public class FileNameMocker
{
    private static FileNameMocker instance = new FileNameMocker();
    private String ip;
    
    
    private FileNameMocker()
    {
    }
    
    public static FileNameMocker getInstance()
    {
        return instance;
    }
    
    private static String mockTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    
    public String getNextFileName()
    {
        ip = IPv4AddrMocker.getInstance().getNextIPv4Addr();
        return ip + "_VDSLPORT_TRUNK_" + mockTimeStamp() + ".csv";
    }
    
    public String getIPAddrFromCurrFileName()
    {
        return ip;
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;

/**
 * <p>文件名称: ThreadObject.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-23</p>
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
public class ThreadObject
{    
    private Thread thread;
    private int result;
    private String ipAddr;
    private FtpInfo ftpInfo;

    public ThreadObject()
    {
    }
    
    public Thread getThread()
    {
        return thread;
    }

    public void setThread(Thread thread)
    {
        this.thread = thread;
    }
    
    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }
    
    public String getIpAddr()
    {
        return ipAddr;
    }
    
    public void setIpAddr(String ipAddr)
    {
        this.ipAddr = ipAddr;
    }
    
    public FtpInfo getFtpInfo()
    {
        return ftpInfo;
    }

    public void setFtpInfo(FtpInfo ftpInfo)
    {
        this.ftpInfo = ftpInfo;
    }
}


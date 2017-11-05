package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.threadmanager;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread.CsvUploadThread;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread.ThreadObject;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ThreadManager.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-22</p>
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
public class ThreadManager
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    Hashtable<String, ThreadObject> threadsProcess = new Hashtable<String, ThreadObject>();
    
    public void addTask(String ipAddr, FtpInfo ftpInfo)
    {
        ThreadObject obj = new ThreadObject();
        obj.setResult(2);
        obj.setIpAddr(ipAddr);
        obj.setFtpInfo(ftpInfo);
        
        Thread csvUploadThread = new CsvUploadThread(obj);
        
        obj.setThread(csvUploadThread);
        
        csvUploadThread.start();
        
        threadsProcess.put(ipAddr, obj);
    }
    
    public Integer getResult(String ipAddr)
    {
        if(threadsProcess.containsKey(ipAddr))
        {
            return (threadsProcess.get(ipAddr)).getResult();
        }
        
        LogPrint.logError(logger, "Cannot find the uploading progresss of DSLAM " + ipAddr);
        return 4;
    }
}

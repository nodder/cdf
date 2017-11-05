package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import java.io.File;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.KPNTimeMarkConst;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.OriginalTimeMark;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi.IDslamMock;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CollectionProcessor.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月23日</p>
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
public class CollectorProcessor extends AbsCollectorProcessor
{
    //****** 代码段: 常量定义 **********************************************************************/

    private static final int TOTAL_NUM = 100;
    private static final int WAIT_SECOND = 1;
    
    private static final int NOT_STARTED = 1; // "Not Started"
    private static final int IN_PROGRESS = 2; // "In Progress"
    private static final int SUCCESS = 3; // "Success"
    private static final int FAILED = 4; // "Failed"

    //****** 代码段: 变量定义 **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    //****** 代码段: 公共方法 **********************************************************************/

    public CollectorProcessor(SnmpNode snmpNode)
    {
        super(snmpNode);
        
        String ftpFullPath = CdfUtil.getFtpFullPath(snmpNode.getIpAddress());
        CdfUtil.createdDirIfNoExist(ftpFullPath);
        CdfUtil.deleteFiles(new File(ftpFullPath));
    }

    @Override
    public boolean getCSVFileFromNe()
    {
        OriginalTimeMark tMark = new OriginalTimeMark(KPNTimeMarkConst.COLLECT);
        
        //防止同一时刻过多RMI请求
        randomSleep();
        
        tMark.markStart();
        
        boolean status = false;
        if(requestCsv())
        {
            status = checkStatus();
        }

        tMark.markEnd();
        return status;
    }

    private void randomSleep()
    {
        try
        {
            Thread.sleep((long)(Math.random() * 1000));
        }
        catch(InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private boolean checkStatus()
    {
        return snmpOperForCheckStatus();
    }

    private boolean snmpOperForCheckStatus()
    {
        try
        {
            int count = 0;
            while(count < TOTAL_NUM)
            {
                sleep(WAIT_SECOND * 1000);

                int status;

                IDslamMock dslamMocker = DslamMockRemoteAgent.getInstance().getDslamMockInterface();
                if(dslamMocker != null)
                {
                    status = dslamMocker.checkStatus(this.snmpNode.getIpAddress());
                    LogPrint.logDebug(logger, "NE: " + this.snmpNode.getIpAddress() + " get status " + status);

                    if(status == SUCCESS)
                    {
                        return true;
                    }
                    else if(status == IN_PROGRESS)
                    {
                        continue;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
                
            }
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "", e);
        }
        return false;
    }

    private void sleep(int second)
    {
        try
        {
            Thread.sleep(WAIT_SECOND * 1000);
        }
        catch(InterruptedException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }

    private boolean requestCsv()
    {
        try
        {
            IDslamMock dispatchInterface = DslamMockRemoteAgent.getInstance().getDslamMockInterface();
            if(dispatchInterface != null)
            {
                dispatchInterface.requestCsvByFTP(this.snmpNode.getIpAddress(), SubCollectCache.ftpInfo);
                return true;
            }
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
        
        return false;
    }

    @Override
    public boolean isVersionInconsistent()
    {
        return false;
    }

    @Override
    public void downloadXMLWithCurrentVersion()
    {
        
    }

    @Override
    public boolean isNeedOperate()
    {
        return true;
    }
}
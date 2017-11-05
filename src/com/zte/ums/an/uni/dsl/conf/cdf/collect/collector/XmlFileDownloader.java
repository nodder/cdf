package com.zte.ums.an.uni.dsl.conf.cdf.collect.collector;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.n3common.api.CommonConst;
import com.zte.ums.n3common.api.ZXLogger;
import com.zte.ums.uep.protocol.snmp.beans.DataException;
import com.zte.ums.uep.protocol.snmp.beans.SnmpTarget;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpAPI;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpException;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpInt;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpVar;

/**
 * <p>文件名称: XmlFileDownloader</p>
 * <p>文件描述: 业务类</p>
 * <p>版权所有: 版权所有(C)2001-2013</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012年7月13日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */
public class XmlFileDownloader
{
    private static Logger logger = ZXLogger.getLogger(XmlFileDownloader.class.getName());
    
    private static final String TMP_XML_FILE_PATH = "./SCM/tmp/";
    
    /** zxDslFtpManagedObjectType新增枚举值 : BULKPOOL_XML， 适用于9806H下载*/
    public final static String STR_FTP_OBJECT_TYPE_9806H_DOWNLOAD = "BULKPOOL_XML";
    
    /** FTP传输类型：下载操作 */
    public final static int FTP_LOAD_TYPE_DOWNLOAD = 2;
    
    /** 以下为zxDslDownloadStatus的枚举值 */
    private static final int NOT_STARTED = 1; // "Not Started"
    private static final int IN_PROGRESS = 2; // "In Progress"
    private static final int SUCCESS = 3; // "Success"
    
    private final static int TRANSFER_PROTOCAL_SFTP = 2;
    
    private static String version = CdfUtil.getVersion();
    private static FtpInfo ftpInfo = SubCollectCache.ftpInfo;
    
    private static String xmlForDownload = generateXmlForDownload();
    
    private boolean isUsingSFTP;
        
    private String[] exsistedXmlFiles;
    private SnmpTarget target;
    
    public XmlFileDownloader(SnmpTarget target, String[] currentXmlFilesFromDSLAM, boolean isUsingSFTP)
    {
        this.target = target;
        this.exsistedXmlFiles = currentXmlFilesFromDSLAM;
        this.isUsingSFTP = isUsingSFTP;
    }
    
    private static String generateXmlForDownload()
    {
        String relativeTargetXmlFile = TMP_XML_FILE_PATH + getTargetXmlFileName();
        String xmlTemplateFile = new File("../conf/cdf.xml").getAbsolutePath();
        String xmlTargetFile = new File(ftpInfo.ftpRootDir, relativeTargetXmlFile).getAbsolutePath();

        try
        {
            CdfUtil.copyFile(xmlTemplateFile, xmlTargetFile);
            return relativeTargetXmlFile;
        }
        catch(IOException e)
        {
            //如果复制文件出错，是严重故障，再执行后面动作没有意义，在类初始化时强制抛出运行时异常。
            throw new RuntimeException("Failed to copy to " + xmlTargetFile + ":", e);
        }
    }
    
    public void downloadXMLWithCurrentVersion()
    {        
        try
        {
            deleteAllExistedBulkpoolXmls();
            snmpOperForDownloadXmlForMap();
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "downloadXMLWithCurrentVersion exception:", e);
        }
    }

    private void deleteAllExistedBulkpoolXmls() throws SnmpException, DataException
    {
        for(String xmlFile : exsistedXmlFiles)
        {
            String strIndex = CdfUtil.getIndexFromProfileName(xmlFile);

            target.setObjectIDList(null);
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.18.2.1.50." + strIndex);//zxDslBulkPoolXmlFileRowStatus
            SnmpVar[] setVars = new SnmpVar[target.getSnmpOIDList().length];
            setVars[0] = SnmpVar.createVariable(CommonConst.ROW_STATUS_DESTROY, SnmpAPI.INTEGER);
            SnmpVar[] rtnVars = target.snmpSetVariables(setVars);

            if(rtnVars == null)
            {
                throw new SnmpException("delete existed task file --" + xmlFile + " failed, rtnVars==null");
            }
        }
    }
    
    private static String getTargetXmlFileName()
    {
        return version + ".xml";
    }

    private boolean snmpOperForDownloadXmlForMap()
    {
        SnmpVar[] rtnVars = null;      

        try
        {                    
            target.setObjectIDList(null);
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.1.0"); //zxDslFtpManagedObjectType
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.2.0"); //zxDslFtpAddress
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.3.0"); //zxDslFtpUserName
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.4.0"); //zxDslFtpUserPwd
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.6.0"); //zxDslFtpFileName
            target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.7.0"); //zxDslFtpLoadType
            if(isUsingSFTP)
            {
                target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.11.0"); //zxDslFtpProtocolType
            }

            SnmpVar[] setVars = new SnmpVar[target.getObjectIDList().length];
            
            int i = 0;

            setVars[i++] = SnmpVar.createVariable(String.valueOf(STR_FTP_OBJECT_TYPE_9806H_DOWNLOAD),
                                                  SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(ftpInfo.ftpServerIp, SnmpAPI.IPADDRESS);
            setVars[i++] = SnmpVar.createVariable(ftpInfo.ftpUser, SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(ftpInfo.ftpUserPasswd, SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(xmlForDownload, SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(String.valueOf(FTP_LOAD_TYPE_DOWNLOAD), SnmpAPI.INTEGER);
            if(isUsingSFTP)
            {
                setVars[i++] = SnmpVar.createVariable(String.valueOf(TRANSFER_PROTOCAL_SFTP), SnmpAPI.INTEGER);
            }

            rtnVars = target.snmpSetVariables(setVars);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "snmpOperForDownloadXmlForMap 1 SNMP Operation Exception : ", ex);
            return false;
        }

        if(rtnVars == null)
        {
            LogPrint.logError(logger, "snmpOperForDownloadXmlForMap 1 rtnVars == null.");
            return false;
        }

        //每隔一段时间查看一次zxDslDownloadStatus，看操作是否完成，并且结果是否成功
        int timeoutCounter = 600;

        try
        {
            //等待一会再去查看结果,如果马上去读取的话，该量还没有被网元设置
            Thread.sleep(1000);

            //定时轮询
            while(timeoutCounter > 0)
            {
                rtnVars = null;

                try
                {
                    target.setObjectIDList(null);
                    target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.8.0"); //zxDslDownloadStatus
                    rtnVars = target.snmpGetVariables();
                }
                catch(Exception ex)
                {
                    LogPrint.logError(logger, "snmpOperForDownloadXmlForMap 2 SNMP Operation Exception : ", ex);
                    return false;
                }

                if(rtnVars == null)
                {
                    String errStr = "snmpOperForDownloadXmlForMap get zxDslDownloadStatus rtnVars == null  Error : "
                        + target.getErrorString();
                    LogPrint.logError(logger, errStr);
                    return false;
                }

                int state = ((SnmpInt)rtnVars[0]).intValue();

                if((state == IN_PROGRESS) || (state == NOT_STARTED))
                {
                    timeoutCounter--;

                    //正在执行,等待一会再去查看结果
                    LogPrint.logDebug(logger, "snmpOperForDownloadXmlForMap operation in progress timeoutCounter = "
                                 + timeoutCounter);
                    Thread.sleep(1000);

                    continue;
                }
                else
                {
                    //已经执行完成，成败都有可能
                    if(state == SUCCESS)
                    {
                        LogPrint.logInfo(logger, "Succeeded to set task to " + target.getTargetHost()
                                                 + ". Performace data will be collected next period.");
                        return true;
                    }
                    else
                    {
                        LogPrint.logError(logger, "snmpOperForDownloadXmlForMap opeation failed.");
                        return false;
                    }
                }
            }

            if(timeoutCounter == 0)
            {
                //执行超时
                String errStr = "snmpOperForDownloadXmlForMap operation timeout.";
                LogPrint.logError(logger, errStr);

                return false;
            }
        }
        catch(Exception ex)
        {
            String errStr = "snmpOperForDownloadXmlForMap Exception";
            LogPrint.logError(logger, errStr, ex);
            return false;
        }

        return true;
    }
    
}

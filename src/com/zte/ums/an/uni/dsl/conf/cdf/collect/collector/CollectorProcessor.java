package com.zte.ums.an.uni.dsl.conf.cdf.collect.collector;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;
import com.zte.ums.uep.protocol.snmp.beans.SnmpTarget;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpAPI;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpInt;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpVar;
import com.zte.ums.uep.protocol.snmp.snmp2.SnmpVarBind;

/**
 * <p>文件名称: CollectionProcessor.java</p>
 * <p>文件描述: 每个采集线程任务</p>
 * <p>版权所有: 版权所有(C)2007-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月1日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi
 */
public class CollectorProcessor extends AbsCollectorProcessor
{
    //****** 代码段: 常量定义 **********************************************************************/
    
    /** zxDslFtpManagedObjectType新增枚举值 : BULKPOOL_DAT， 适用于9836上传*/
    private final static String STR_FTP_OBJECT_TYPE_9836_UPLOAD = "BULKPOOL_DAT";
    
    /** FTP传输类型：下载操作 */
    public final static int FTP_LOAD_TYPE_DOWNLOAD = 2;
    
    private final static int FTP_OBJECT_TYPE_9800V3 = 259;
    
    /** FTP传输类型：上传操作 */
    private final static int FTP_LOAD_TYPE_UPLOAD = 1;
    
    private final static String[] supportedNETypes = CollectXmlParser.getInstance().getSupportedNETypes();
    
    /** 以下为zxDslDownloadStatus的枚举值 */
    private static final int NOT_STARTED = 1; // "Not Started"
    private static final int IN_PROGRESS = 2; // "In Progress"
    private static final int SUCCESS = 3; // "Success"
//    private static final int FAILED = 4; // "Failed"
    
    /** 是否使用SFTP协议，true表示SFTP，false表示FTP */
    private final static boolean isUsingSFTP = CollectXmlParser.getInstance().isUsingSFTP();
    
//    private final static int TRANSFER_PROTOCAL_FTP = 1;
    private final static int TRANSFER_PROTOCAL_SFTP = 2;

    //****** 代码段: 变量定义 **********************************************************************/
    
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private String ftpRelativePath;
    private String ftpFullPath;
    
    private SnmpTarget target;
    private String[] currentXmlFilesFromDSLAM = new String[0];

    //****** 代码段: 公共方法 **********************************************************************/

    public CollectorProcessor(SnmpNode snmpNode)
    {
        super(snmpNode);
        
        ftpRelativePath = CdfUtil.getFtpRelativePath(snmpNode.getIpAddress());
        ftpFullPath = CdfUtil.getFtpFullPath(snmpNode.getIpAddress());
        CdfUtil.createdDirIfNoExist(ftpFullPath);
        
        if(isNeedDeleteFormerCsv())
        {
            CdfUtil.deleteFiles(new File(ftpFullPath));
        }
        
        this.target = CdfUtil.getCustomizedSnmpTarget(snmpNode);
    }
    
    @Override
    public boolean isNeedOperate()
    {
        if(isSupport(snmpNode.getMoc()))
        {
            if(!CdfUtil.fastSnmpPing(snmpNode))
            {
                String errStr = snmpNode.getIpAddress() + " is disconnected.";
                LogPrint.logWarn(logger, errStr);
                return false;
            }
        }
        else
        {
            String errStr = "NE type " + snmpNode.getMoc() + " is not supported.(" + this.snmpNode.getIpAddress() + ")";
            LogPrint.logError(logger, errStr);
            return false;
        }
        
        return true;
    }

    private boolean isNeedDeleteFormerCsv()
    {
        return CollectXmlParser.getInstance().getDeletePreFiles();
    }

    @Override
    public boolean getCSVFileFromNe()
    {
        return snmpOperForUploadCsv();
    }

    private static boolean isSupport(String neType)
    {
        for(String supportedType : supportedNETypes)
        {
            if(neType.equals(supportedType))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean snmpOperForUploadCsv()
    {
        SnmpVar[] rtnVars = null;

        try
        {
            setFtpParaMib();

            SnmpVar[] setVars = new SnmpVar[target.getObjectIDList().length];

            int i = 0;
            setVars[i++] = getFtpObjType();
            setVars[i++] = SnmpVar.createVariable(SubCollectCache.ftpInfo.ftpServerIp, SnmpAPI.IPADDRESS);
            setVars[i++] = SnmpVar.createVariable(SubCollectCache.ftpInfo.ftpUser, SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(SubCollectCache.ftpInfo.ftpUserPasswd, SnmpAPI.STRING);
            setVars[i++] = SnmpVar.createVariable(ftpRelativePath, SnmpAPI.STRING); //取所有文件，不需要指定文件名
            setVars[i++] = SnmpVar.createVariable(String.valueOf(FTP_LOAD_TYPE_UPLOAD), SnmpAPI.INTEGER);
            
            if(isUsingSFTP)
            {
                setVars[i++] = SnmpVar.createVariable(String.valueOf(TRANSFER_PROTOCAL_SFTP), SnmpAPI.INTEGER);
            }

            rtnVars = target.snmpSetVariables(setVars);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "uploadConfDataFor9836 1 SNMP Operation Exception : " + ex.getMessage());
            return false;
        }

        if(rtnVars == null)
        {
            LogPrint.logError(logger, "uploadConfDataFor9836 1 rtnVars == null.");
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
                    setFtpStatusMib();
                    rtnVars = target.snmpGetVariables();
                }
                catch(Exception ex)
                {
                    LogPrint.logError(logger, "uploadConfDataFor9836 2 SNMP Operation Exception : " + "IP == "
                        + snmpNode.getIpAddress() + ex.getMessage());
                    return false;
                }

                if(rtnVars == null)
                {
                    String errStr = "uploadConfDataFor9836 get zxDslDownloadStatus rtnVars == null  Error : "
                        + target.getErrorString()
                        + "IP == " + snmpNode.getIpAddress();
                    LogPrint.logError(logger, errStr);
                    return false;
                }

                int state = ((SnmpInt)rtnVars[0]).intValue();

                if((state == IN_PROGRESS) || (state == NOT_STARTED))
                {
                    timeoutCounter--;

                    //正在执行,等待一会再去查看结果
//                    LogPrint.logDebug(logger, "uploadConfDataFor9836 operation in progress timeoutCounter = " + timeoutCounter);
                    Thread.sleep(1000);

                    continue;
                }
                else
                {
                    //已经执行完成，成败都有可能
                    if(state == SUCCESS)
                    {
                        String resultStr = "Get performace data from NE " + this.snmpNode.getIpAddress() + " has done successfully.";
                        String fileStr = getFileStr();
                        LogPrint.logInfo(logger, resultStr + "(" + fileStr + ")");
                        return true;
                    }
                    else
                    {
                        LogPrint.logError(logger, "uploadConfDataFor9836 opeation failed." + "IP == " + snmpNode.getIpAddress());
                        return false;
                    }
                }
            }

            if(timeoutCounter == 0)
            {
                //执行超时
                String errStr = "uploadConfDataFor9836 operation timeout. " + "IP == " + snmpNode.getIpAddress();
                LogPrint.logError(logger, errStr);

                return false;
            }
        }
        catch(Exception ex)
        {
            String errStr = "uploadConfDataFor9836 Exception" + "IP == " + snmpNode.getIpAddress();
            LogPrint.logError(logger, errStr, ex);
            return false;
        }
        
        return true;
    }

    private void setFtpParaMib()
    {
        if(CdfUtil.isMapNe(snmpNode.getMoc()))
        {
            setFtpParaMib4MapNe();
        }
        else
        {
            setFtpParaMib4RosNe();
        }
    }
    
    private void setFtpStatusMib()
    {
        if(CdfUtil.isMapNe(snmpNode.getMoc()))
        {
            setFtpStatusMib4MapNe();
        }
        else
        {
            setFtpStatusMib4RosNe();
        }
    }
    
    private void setFtpParaMib4MapNe()
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
    }
    
    private void setFtpStatusMib4MapNe()
    {
        target.setObjectIDList(null);
        target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.7.8.0"); //zxDslDownloadStatus
    }
    
    private void setFtpParaMib4RosNe()
    {
        target.setObjectIDList(null);
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.1.0"); //zxAnFtpManagedFileType
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.3.0"); //zxAnFtpServerIpAddress
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.4.0"); //zxAnFtpServerUserName
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.5.0"); //zxAnFtpServerUserPwd
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.7.0"); //zxAnFtpServerFileName
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.2.0"); //zxAnFtpClntOperType
        
        if(isUsingSFTP)
        {
            target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.11.0"); //zxAnFileFtpSvrProtocolType
        }
    }
    
    private void setFtpStatusMib4RosNe()
    {
        target.setObjectIDList(null);
        target.addObjectID(".1.3.6.1.4.1.3902.1015.1.1.50.1.9.0"); //.zxAnFtpClntOperStatus
    }
    
    private SnmpVar getFtpObjType()
    {
        SnmpVar snmpVar = null;
        
        try
        {
            if(CdfUtil.isMapNe(snmpNode.getMoc()))
            {
                snmpVar = SnmpVar.createVariable(String.valueOf(STR_FTP_OBJECT_TYPE_9836_UPLOAD),
                    SnmpAPI.STRING);
            }
            else
            {
                snmpVar = SnmpVar.createVariable(String.valueOf(FTP_OBJECT_TYPE_9800V3),
                    SnmpAPI.INTEGER);
            }
        }
        catch(Exception e)
        {
            return snmpVar;
        }
        return snmpVar;
    }
    
    private String getFileStr()
    {
        int fileNum = getFileNum();
        
        if(fileNum > 1)
        {
            return "Total " + fileNum + " performance files";
        }
        else if(fileNum == 1)
        {
            return "Total " + fileNum + " performance file";
        }
        else
        {
            return "No performance file";
        }
    }

    private int getFileNum()
    {
        int fileNum = 0;
        File[] csvFiles = CdfUtil.getFiles(ftpFullPath, ".csv");
        if(csvFiles != null)
        {
            fileNum = csvFiles.length;
        }
        return fileNum;
    }

    @Override
    public boolean isVersionInconsistent()
    {
        target.setObjectIDList(null);
        target.addObjectID(".1.3.6.1.4.1.3902.1004.3.1.18.2.1.1"); //zxDslBulkPoolXmlFileName
        SnmpVarBind[][] varbind = target.snmpGetAllVariableBindings();
        if(varbind == null)
        {
            if(target.getErrorCode() == SnmpAPI.SNMP_ERR_NOERROR)//9806H不支持该mib，但经测试，仍会进入到noerror分支。
            {
                LogPrint.logWarn(logger, "No collection task xml found for NE:" + snmpNode.getIpAddress() + ", start to download task.");
                return true;
            }
            else
            {
                LogPrint.logError(logger, "Not support collection task xml checking for NE:" + snmpNode.getIpAddress() + ".");
                return false;
            }
        }

        this.currentXmlFilesFromDSLAM = assembleSnmpResult(varbind);
        String currentVersion = CdfUtil.getVersion();
        
        return !isSameVersion(currentVersion, currentXmlFilesFromDSLAM);
    }

    private String[] assembleSnmpResult(SnmpVarBind[][] varbind)
    {
        ArrayList<String> xmlFiles = new ArrayList<String>();
        int oidLength = "1.3.6.1.4.1.3902.1004.3.1.18.2.1.1".split("\\.").length + 1;

        for(int i = 0; i < varbind.length; i++)
        {
            int[] oidIndex = varbind[i][0].getObjectID().toIntArray();
            char[] xmlNameChars = new char[oidIndex.length - oidLength];
            for(int j = 0; j < xmlNameChars.length; j++)
            {
                xmlNameChars[j] = (char)(oidIndex[oidLength + j]);
            }
            xmlFiles.add(new String(xmlNameChars));
        }

        String[] arrXmlFiles = xmlFiles.toArray(new String[0]);
        return arrXmlFiles;
    }

    private boolean isSameVersion(String currentVersion, String[] arrXmlFiles)
    {
        boolean isSameVersion = arrXmlFiles.length == 1 && arrXmlFiles[0].equalsIgnoreCase(currentVersion + ".xml");
        if(!isSameVersion)
        {
            logVersionInconsistent(arrXmlFiles);
        }
        
        return isSameVersion;
    }

    private void logVersionInconsistent(String[] arrXmlFiles)
    {
        String errStr = "Inconsistent collection task: NE " + snmpNode.getIpAddress() + " ";
        if(arrXmlFiles.length > 1)
        {
            errStr += "multiple tasks";
        }
        else
        {
            errStr += "is using \"" + arrXmlFiles[0] + "\"";
        }
        
        errStr +=". CDF will download the correct task automatically。";
        
        LogPrint.logInfo(logger, errStr);
    }

    @Override
    public void downloadXMLWithCurrentVersion()
    {
        XmlFileDownloader xmlDown = new XmlFileDownloader(target, currentXmlFilesFromDSLAM, isUsingSFTP);
        xmlDown.downloadXMLWithCurrentVersion();
    }
    
    private static boolean isCheckXml()
    {
        return CollectXmlParser.getInstance().getCheckXml();
    }
    
    public static void main(String[] args)
    {
        SubCollectCache.initParams();
        SnmpNode ne = new SnmpNode();
        ne.setIpAddress("10.63.174.99");
        ne.setMoc("ZXDSL9836");
        ne.setSnmpCommunity("public");
        ne.setSnmpWriteCommunity("private");
        
//        ne.setIpAddress("10.63.174.165");
//        ne.setMoc("ZXDSL9806H");
//        ne.setSnmpCommunity("public");
//        ne.setSnmpWriteCommunity("private");
        
        CollectorProcessor cp = new CollectorProcessor(ne);
        
        try
        {
            if(cp.isNeedOperate())
            {
                if(isCheckXml() && cp.isVersionInconsistent())
                {
                    cp.downloadXMLWithCurrentVersion();
                }
                else
                {
                    System.out.println("Do cp.getCSVFileFromNe()");
                }
            }
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        
        System.out.println("before exit");
        
        System.exit(1);
    }
}
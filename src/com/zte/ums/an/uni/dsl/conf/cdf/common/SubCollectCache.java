package com.zte.ums.an.uni.dsl.conf.cdf.common;

import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.uep.protocol.snmp.beans.SnmpTarget;

/**
 * <p>文件名称: CdfCache</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年9月1日</p>
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
public class SubCollectCache
{
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TEST_PROCEDURE = 100;
    public static final int TYPE_TEST_COLLECTOR = 101;
    public static final int TYPE_TEST_PARSER_DB = 102;
    public static final int TYPE_TEST_MASSIVE_COLLECT = 103;
    
    public static int currType = TYPE_NORMAL;
    public static boolean needReconnectToDispatchSvr = false;
    
    public static FtpInfo ftpInfo = new FtpInfo();
    public static SubCollectInfo subCollectInfo = new SubCollectInfo();
    public static SnmpTarget snmpTargetModule = new SnmpTarget();
    public static SnmpTarget snmpTargetModule4Fast = new SnmpTarget();
    
    public static boolean isReduceIO = false;

    public static void initParams()
    {
        initFtpInfo();
        initSnmpTargetModule();
        initSnmpTargetModule4Fast();
        initSubCollectInfo();
        initIsReduceIO();
    }
    
    private static void initIsReduceIO()
    {
        isReduceIO = CollectXmlParser.getInstance().getReduceIO();
    }

    private static void initSubCollectInfo()
    {
        subCollectInfo.ipAddr = CollectXmlParser.getInstance().getSubServerIP();
        subCollectInfo.alias = CollectXmlParser.getInstance().getSubServerAlias();
        subCollectInfo.dbType = CollectXmlParser.getInstance().getDbType();
        subCollectInfo.dbServerIp = CollectXmlParser.getInstance().getDbServerIp();
        subCollectInfo.dbPort = CollectXmlParser.getInstance().getDbPort();
        subCollectInfo.dbUser = CollectXmlParser.getInstance().getDbSuperUser();
        subCollectInfo.dbPassword = CollectXmlParser.getInstance().getDbSuperUserPassword();
        subCollectInfo.dbName = CollectXmlParser.getInstance().getDbName();
    }

    private static void initFtpInfo()
    {
        ftpInfo.ftpServerIp = CollectXmlParser.getInstance().getFTPServerIp();
        ftpInfo.ftpUser = CollectXmlParser.getInstance().getFTPUser();
        ftpInfo.ftpUserPasswd = CollectXmlParser.getInstance().getFTPPassword();
        ftpInfo.ftpRootDir = CollectXmlParser.getInstance().getFTPMainDir();
    }

    private static void initSnmpTargetModule()
    {
        snmpTargetModule.setSnmpVersion(SnmpTarget.VERSION2C);
        snmpTargetModule.setTargetPort(161);
        snmpTargetModule.setRetries(Integer.parseInt(CollectXmlParser.getInstance().getSNMPRetries()));
        snmpTargetModule.setTimeout(Integer.parseInt(CollectXmlParser.getInstance().getSNMPTimeout()));
    }

    private static void initSnmpTargetModule4Fast()
    {
        snmpTargetModule4Fast.setSnmpVersion(SnmpTarget.VERSION2C);
        snmpTargetModule4Fast.setTargetPort(161);
        snmpTargetModule.setRetries(Integer.parseInt(CollectXmlParser.getInstance().getFastSNMPRetries()));
        snmpTargetModule.setTimeout(Integer.parseInt(CollectXmlParser.getInstance().getFastSNMPTimeout()));
    }
}

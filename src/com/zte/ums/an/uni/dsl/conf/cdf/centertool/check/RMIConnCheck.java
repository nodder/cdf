package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.RmiInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.DispatchProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;

/**
 * <p>文件名称: RMIConnCheck.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-12</p>
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
public class RMIConnCheck implements ICdfCheck
{    
    @Override
    public String getName()
    {
        return "RMI";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start RMI test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("RMI test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        try
        {
            checkRMIXmlConfig();
            checkProgrameRMIConn();
            
            return new CdfTestResult(true);
        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }

    private void checkProgrameRMIConn() throws Exception
    {
        CenterToolUtil.printCheckTile("Simulate RMI connection");
        
        try
        {
            CenterToolUtil.changeToDispatchDir();
            if(!DispatchProcessor.getInstance().startRmi())
            {
                throw new Exception(CdfTestResultConst.RMI_START_FAIL);
            }
            
            CenterToolUtil.changeToReportDir();
            IDispatch reportServerConn = com.zte.ums.an.uni.dsl.conf.cdf.report.DispatchRemoteAgent.getInstance().getDispatchInterface();
            if(reportServerConn == null)
            {
                throw new Exception(CdfTestResultConst.RMI_CONNECT_FAIL + ":" + "report server");
            }
            
            CenterToolUtil.changeToSubCollectDir();
            IDispatch subServerConn = com.zte.ums.an.uni.dsl.conf.cdf.collect.DispatchRemoteAgent.getInstance().getDispatchInterface();
            if(subServerConn == null)
            {
                throw new Exception(CdfTestResultConst.RMI_CONNECT_FAIL + ":" + "sub-collect server");
            }
            
            CenterToolUtil.printSucess();
        }
        finally
        {
            CenterToolUtil.changeToDispatchDir();
            DispatchProcessor.getInstance().stopRmi();
        }
    }

    private void checkRMIXmlConfig() throws Exception
    {
        RmiInfo dispatchRMI = getDispatchRMI();
        RmiInfo reportRMI = getReportRMI();
        RmiInfo subcollectRMI = getSubServerRMI();

        compareRMI(dispatchRMI, reportRMI, subcollectRMI);
    }
    
    private void compareRMI(RmiInfo dispatchRMI, RmiInfo reportRMI, RmiInfo subcollectRMI) throws Exception
    {
        CenterToolUtil.printCheckTile("Check RMI configuration consistency");

        if(!dispatchRMI.equals(reportRMI))
        {
            throw new Exception(CdfTestResultConst.RMI_INCONSISTENT + ":" + "report server:" + reportRMI + ",dispatch server:" + dispatchRMI);
        }

        if(!dispatchRMI.equals(subcollectRMI))
        {
            throw new Exception(CdfTestResultConst.RMI_INCONSISTENT + ":" + "subcollect server:" + subcollectRMI + ",dispatch server:" + dispatchRMI);
        }
        
        CenterToolUtil.printSucess();
    }

    public RmiInfo getReportRMI() throws Exception
    {
        CenterToolUtil.printCheckTile("Check report server RMI configuration");
        CenterToolUtil.changeToReportDir();
        
        RmiInfo rmiInfo = new RmiInfo();
        rmiInfo.ipAddr = ReportXmlParser.getInstance().getServerIP();
        rmiInfo.port = ReportXmlParser.getInstance().getServerPort();
        rmiInfo.remoteObject = ReportXmlParser.getInstance().getRemoteObjectName();
        
        
        testIsLocalHostIP(rmiInfo.ipAddr, "IP address of report server");
        testInt(rmiInfo.port, "port of report server");
        
        CenterToolUtil.printSucess();
        return rmiInfo;
    }
    
    public RmiInfo getDispatchRMI() throws Exception
    {
        CenterToolUtil.printCheckTile("Check dispatch server RMI configuration");
        
        CenterToolUtil.changeToDispatchDir();
        
        RmiInfo rmiInfo = new RmiInfo();
        rmiInfo.ipAddr = DispatchXmlParser.getInstance().getServerIP();
        rmiInfo.port = DispatchXmlParser.getInstance().getServerPort();
        rmiInfo.remoteObject = DispatchXmlParser.getInstance().getRemoteObjectName();
        
        testIsLocalHostIP(rmiInfo.ipAddr, "IP address of dipspatch server");
        testInt(rmiInfo.port, "port of dipspatch server");
        
        CenterToolUtil.printSucess();
        return rmiInfo;
    }
    
    public RmiInfo getSubServerRMI() throws Exception
    {
        CenterToolUtil.printCheckTile("Check sub-collect server RMI configuration");
        CenterToolUtil.changeToSubCollectDir();
        RmiInfo rmiInfo = new RmiInfo();
        rmiInfo.ipAddr = CollectXmlParser.getInstance().getRemoteMainServerIp();
        rmiInfo.port = CollectXmlParser.getInstance().getRemoteServerPort();
        rmiInfo.remoteObject = CollectXmlParser.getInstance().getRemoteObjectName();
        
        
        testIsLocalHostIP(rmiInfo.ipAddr, "IP address of sub-collect server");
        testInt(rmiInfo.port, "port of sub-collect server");
        
        CenterToolUtil.printSucess();
        return rmiInfo;
    }
    
    private int testInt(String str, String descrip) throws Exception
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch(NumberFormatException e)
        {
            throw new Exception(CdfTestResultConst.RMI_CONF_INVALID_port + ":" + descrip);
        }
    }
    
    private void testIsLocalHostIP(String ipAddr, String descrip) throws Exception
    {
        boolean isLocalHostIP = CenterToolUtil.isLocalHostIP(ipAddr);

        if(!isLocalHostIP)
        {
            throw new Exception(CdfTestResultConst.IPV4_NOT_LOCAL + ":" + ipAddr + ":" + descrip);
        }
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import java.io.File;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;

/**
 * <p>文件名称: ActionDeleteTmp.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-15</p>
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
public class ActionDeleteTmp implements IMenuAction
{
    @Override
    public void doAction()
    {
        deleteTempData();
        deleteReport();
    }

    private void deleteReport()
    {
        System.out.print("Do you want to delete all the reports?(y/n)[n]");
        String userInput = CenterToolUtil.readValidInput(true, "y", "n", "");

        if("y".equalsIgnoreCase(userInput))
        {
            System.out.print("The report is the interface to the northbound system. Are you sure to delete?(y/n)[n]");
            userInput = CenterToolUtil.readValidInput(true, "y", "n", "");
            if("y".equalsIgnoreCase(userInput))
            {
                doDeleteReport();
            }
        }
    }

    private void doDeleteReport()
    {
        String reportDir = CenterToolUtil.getReportXml().getExportDir();
        File reportFile = (new File(reportDir)).getAbsoluteFile();
        
        if(reportFile.exists())
        {
            CdfUtil.deleteFiles((new File(reportDir)).getAbsoluteFile());
            System.out.println("Deleting reports has finished.");
        }
        else
        {
            System.out.println("The report directory " + reportFile.getAbsolutePath() + " does not exist.");
        }
    }

    private void deleteTempData()
    {
        System.out.print("This operation will delete all the logs and all the temporary files that uploaded from NEs.\n"
                           + "Are you sure to delete?(y/n)[n]");
        String userInput = CenterToolUtil.readValidInput(true, "y", "n", "");

        if("y".equalsIgnoreCase(userInput))
        {
            deleteTempCsvByNEs();
            deleteLogs();
            System.out.println("Operation has finished.");
        }
    }

    private void deleteLogs()
    {
        CenterToolUtil.resetDir();
        deleteRemoteLog();
        
        CdfUtil.deleteFiles((new File("server_dispatch/log")).getAbsoluteFile());
        CdfUtil.deleteFiles((new File("server_report/log")).getAbsoluteFile());
        CdfUtil.deleteFiles((new File("server_subcollect/log")).getAbsoluteFile());
    }

    private void deleteRemoteLog()
    {
        File[] allFiles = CdfUtil.getFiles(".", ".log");
        for(File f : allFiles)
        {
            f.delete();
        }
    }

    private void deleteTempCsvByNEs()
    {
        CenterToolUtil.changeToSubCollectDir();
        String ftpRootDir = CollectXmlParser.getInstance().getFTPMainDir();
        File ftpCdfPath = new File(ftpRootDir, CdfConst.FTP_CDF_PATH);
        
        if(ftpCdfPath.exists())
        {
            CdfUtil.deleteFiles(ftpCdfPath);
            System.out.println("Deleting temporary file at FTP directory has finished.");
        }
        else
        {
            System.out.println("The FTP directory " + ftpCdfPath.getAbsolutePath() + " does not exist.");
        }
    }
    
    public void deleteTmp()
    {
        deleteTempCsvByNEs();
        doDeleteReport();
    }
    
//    public static void main(String[] args)
//    {
//        if(args == null || args.length != 2)
//        {
//            System.out.println("Wrong program arguments: the length must be 2.");
//        }
//        
//        boolean isDeleteTempCsvByNEs = "y".equalsIgnoreCase(args[0]);
//        boolean isDeleteReports = "y".equalsIgnoreCase(args[1]);
//        
//        ActionDeleteTmp act = new ActionDeleteTmp();
//        
//        if(isDeleteTempCsvByNEs)
//        {
//            act.deleteTempCsvByNEs();
//        }
//        
//        if(isDeleteReports)
//        {
//            act.doDeleteReport();
//        }
//    }
}

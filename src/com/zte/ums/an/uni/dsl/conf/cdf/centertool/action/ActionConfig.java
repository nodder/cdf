package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnForCdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnOfDispachCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnOfSubcollectCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.FtpConnCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.ICdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ConfigCheckRunner;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ReportExprtDirDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.DispatchDbIpDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.DispatchDbPortDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.DispatchDbTypeDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbInstallDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbIpDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbNameDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbPasswordDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbPortDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbTypeDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db.SubCollectDbUserDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp.FTPMainDirDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp.FTPPasswordDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp.FTPServerIpDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp.FTPUserDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.rmi.RmiServerIpDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.rmi.RmiServerPortDataModel;

/**
 * <p>文件名称: ActionConfig</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-13</p>
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
public class ActionConfig implements IMenuAction
{
    private final ICdfConfigDataModel[] ftpCheckList = new ICdfConfigDataModel[] {
//         new FTPTransferTypeDataModel(),
         new FTPServerIpDataModel(),
         new FTPUserDataModel(),
         new FTPPasswordDataModel(),
         new FTPMainDirDataModel()
     };
    
    private final ICdfConfigDataModel[] rmiCheckList = new ICdfConfigDataModel[] {
        new RmiServerIpDataModel(),
        new RmiServerPortDataModel()
    };
    
    private final ICdfConfigDataModel[] dispatchDbCheckList = new ICdfConfigDataModel[] {
        new DispatchDbTypeDataModel(),
        new DispatchDbIpDataModel(),
        new DispatchDbPortDataModel()
    };
    
    private final ICdfConfigDataModel[] subCollectDbCheckList = new ICdfConfigDataModel[] {
        new SubCollectDbTypeDataModel(),
        new SubCollectDbIpDataModel(),
        new SubCollectDbPortDataModel(),
        new SubCollectDbNameDataModel(),
        new SubCollectDbUserDataModel(),
        new SubCollectDbPasswordDataModel(),
    };
    
    private final ICdfConfigDataModel[] cdfDbInstallCheckList = new ICdfConfigDataModel[] {
       new SubCollectDbInstallDataModel()
   };
        
    private final ICdfConfigDataModel[] reportDir = new ICdfConfigDataModel[] {
         new ReportExprtDirDataModel()
    };
    
    private ConfigCheckRunner runner = new ConfigCheckRunner();
    
    private final HashMap<ICdfConfigDataModel[], ICdfCheck> configToCheck = buildConfigToCheck();
    
    @Override
    public void doAction()
    {
        runSingelCheck(dispatchDbCheckList, "Start configure dispatch server", getDispatchDBHelpStr());
        runSingelCheck(ftpCheckList, "Start configure FTP server paramters", getFTPHelpStr());
//        runSingelCheck(rmiCheckList, "Start configure RMI", getRMIHelpStr());
        runSingelCheck(subCollectDbCheckList, "Start configure database parameters for sub-collect server", getSubcollectDBHelpStr());
        runSingelCheck(cdfDbInstallCheckList, "Start install CDF database for sub-collect server", getSubcollectDBHelpStr());
        runSingelCheck(reportDir, "Report Absolute Directory", null);
        
        CenterToolUtil.printGroupTile("Start configure report schedule");
        ReportCronConfig.runCheck(runner);
        System.out.println();
        
        CenterToolUtil.askAndSaveXML();
    }
    
    private void runSingelCheck(ICdfConfigDataModel[] checkList, String title, String helpStr)
    {
        boolean needRun = true;
        CenterToolUtil.printGroupTile(title);
        if(null != helpStr)
        {
            CenterToolUtil.printGroupHelp(helpStr);
        }
        
        while(needRun)
        {
            runner.runCheck(checkList);
            needRun = askAndRunCheck(checkList);
            System.out.println();
        }
    }

    private boolean askAndRunCheck(ICdfConfigDataModel[] checkList)
    {
        boolean needReconfig = false;
        if(configToCheck.containsKey(checkList))
        {
            ICdfCheck check = configToCheck.get(checkList);
            System.out.print("Do you want to test " + check.getName() + "?(y/n)[n]");
            String input = CenterToolUtil.readValidInput(true, "y", "n", "");
            if("y".equalsIgnoreCase(input))
            {
                boolean isSuccess = (ActionConfigCheck.doCheck(configToCheck.get(checkList))).isSuccess;
                if(isSuccess)
                {
                    System.out.println("Test Result: PASS");
                }
                else
                {
                    needReconfig = askReconfig();
                }
            }
        }
        
        return needReconfig;
    }
    
    private boolean askReconfig()
    {
        System.out.print("Test Result: FAIL! Do you want to re-configure?(y/n)[y]");
        String userInput = CenterToolUtil.readValidInput(true, "y", "n", "");
        if("n".equalsIgnoreCase(userInput))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    private String getSubcollectDBHelpStr()
    {
       return "Sub-collect server needs its own database to store performance data. It is suggested to install this database locally.";
    }

    private String getDispatchDBHelpStr()
    {
        return "Dispatch server need to connect to the EMS for collection NE list.";
    }

    private String getRMIHelpStr()
    {
        return "RMI is for communication between servers within CDF system.";
    }

    private String getFTPHelpStr()
    {
        return "FTP server must be installed locally for communication between NEs and sub-collect server.";
    }
    
    private HashMap<ICdfConfigDataModel[], ICdfCheck> buildConfigToCheck()
    {
        HashMap<ICdfConfigDataModel[], ICdfCheck> map = new HashMap<ICdfConfigDataModel[], ICdfCheck>();
        map.put(ftpCheckList, new FtpConnCheck());
        map.put(dispatchDbCheckList, new DbConnOfDispachCheck());
        map.put(subCollectDbCheckList, new DbConnOfSubcollectCheck());
        map.put(cdfDbInstallCheckList, new DbConnForCdfCheck());
        
        return map;
    }

    public static void main(String[] args)
    {
        (new ActionConfig()).doAction();
    }
}


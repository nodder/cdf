package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnOfDispachCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnForCdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.FtpConnCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.ICdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.RMIConnCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.ReportDirCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;

/**
 * <p>文件名称: ActionConfigCheck.java</p>
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
public class ActionConfigCheck implements IMenuAction
{
    private final ICdfCheck[] checkList = new ICdfCheck[] {
        new DbConnOfDispachCheck(),
        new DbConnForCdfCheck(),
        new DbConnForCdfCheck(),
        new FtpConnCheck(),
        new RMIConnCheck(),
        new ReportDirCheck()
    };
    
    @Override
    public void doAction()
    {
        if(CenterToolUtil.isCDFRunning())
        {
            System.out.println(CenterToolUtil.getSvrRunningDiscripStr() + " Please shutdown CDF first before running test.");
            return;
        }
        else
        {
            doCheck();
        }
    }

    private void doCheck()
    {
        CdfTestResult[] results = new CdfTestResult[checkList.length];
        
        for(int i = 0; i < this.checkList.length; i++)
        {
            this.checkList[i].presentTitle();
            results[i] = doCheck(this.checkList[i]);
            this.checkList[i].presentEnd();
            System.out.println();
        }
        
        afterFinish(results);
    }
    
    public static CdfTestResult doCheck(ICdfCheck check)
    {
        CdfTestResult result = check.doCheck();
        presentError(result);
        return result;
    }

    private void afterFinish(CdfTestResult[] results)
    {
        if(isAllSuccess(results))
        {
            System.out.println("Congratulations!!! All the tests passed.");
        }
        else
        {
            System.out.println("Failure exists during the test. See result details above and re-configure again.");
        }
    }

    private boolean isAllSuccess(CdfTestResult[] results)
    {
        for(CdfTestResult result : results)
        {
            if(!result.isSuccess)
            {
                return false;
            }
        }
        
        return true;
    }

    private static void presentError(CdfTestResult result)
    {
        if(!result.isSuccess)
        {
            System.out.println("FAIL!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERROR: " + result.detailStr);
        }
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressParser;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;

/**
 * <p>文件名称: ReportCronDataModel</p>
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
public class ReportCronModeDataModel extends AbsCdfConfigDataModelWithSequence
{
    private String userInputMode = "";

    public ReportCronModeDataModel(ReportInfo reportInfo)
    {
        super(reportInfo);
    }
        
    @Override
    public String getTitle()
    {
        String currStartInfo = (new CronExpressParser(reportInfo.getScheduleCron())).toDisplayStr();
        
        String generalInfo = "The report named '" + reportInfo.getName() + "' is scheduled as + (" + currStartInfo + ")\n"
        + "Rechedule the time when this report begin to generate.";
        
        String modeTitle = "Reconfigure Report Frequence(1-everyday, 2-once a week, 3-once a month)";
        
        return generalInfo + "\n" + modeTitle;
    }
           
    @Override
    public String getCurrValue()
    {
        CronExpressParser parse = new CronExpressParser(reportInfo.getScheduleCron());
        
        if(parse.getIsDaily())
        {
            return CenterToolUtil.CRON_MODE_DAILY + "";
        }
        
        if(parse.getIsWeekly())
        {
            return CenterToolUtil.CRON_MODE_WEEKLY + "";
        }
        
        if(parse.getIsMonthly())
        {
            return CenterToolUtil.CRON_MODE_MONTHLY + "";
        }
        
        return "--";
    }
    
    @Override
    public boolean setNewValue(String newMode)
    {
        if(CenterToolUtil.isValidCronMode(newMode))
        {
            this.userInputMode = newMode;
            return true;
        }
        
        return false;
    }
    
    @Override
    public String getUserInput()
    {
        return userInputMode;
    }
}

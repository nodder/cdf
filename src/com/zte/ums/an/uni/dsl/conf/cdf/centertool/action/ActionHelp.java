package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;

/**
 * <p>文件名称: ActionExit.java</p>
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
public class ActionHelp implements IMenuAction
{
    @Override
    public void doAction()
    {
        System.out.println(getHelpStr());
    }

    private String getHelpStr()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append("\n");
        buf.append("=========The steps to configure and run CDF=========").append("\n\n");
        
        buf.append("STEP 1: Preparation").append("\n\n");
        buf.append("1.) FTP server is installed locally and the FTP directory has write permission").append(";\n");
        buf.append("2.) One database(mssql or oracle) exclusive for CDF to collect and store performace data").append(";\n");
        buf.append("3.) Be connected to EMS database").append(";\n");
        buf.append("4.) Add or delete collection NE list in the EMS").append(";\n");
        
        buf.append("\n");
        buf.append("STEP 2: Configure and Run").append("\n\n");
        buf.append("1.) Execute \"Configurations\" in Main Menu").append(";\n");
        buf.append("2.) Execute \"Run Configuration Tests\" in Test and Maintenance Menu").append(";\n");
        buf.append("3.) If you see \"Congratulations!!! All tests passed.\", then execute \"Start CDF\" in Main Menu").append(";\n");
        buf.append("4.) Wait until \"Report Scheduled Time\" arrives ,see final report at \"Report Absolute Directory\"").append(";\n");
        
        return buf.toString();
    }
}

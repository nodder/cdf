package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;



/**
 * <p>文件名称: CronTimeGenerator.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-14</p>
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
public class CronExpressGenerator
{
    public static final int CRON_WEEKDAY_SUN = 1;
    public static final int CRON_WEEKDAY_MON = 2;
    public static final int CRON_WEEKDAY_TUE = 3;
    public static final int CRON_WEEKDAY_WED = 4;
    public static final int CRON_WEEKDAY_THU = 5;
    public static final int CRON_WEEKDAY_FRI = 6;
    public static final int CRON_WEEKDAY_SAT = 7;
    
    private String second = "*";
    private String minute = "*";
    private String hour = "*";
    private String day = "?";
    private String month = "*";
    private String weekDay = "*";

    /** 解析形如13:59:59的字段 */
    public boolean setDayTime(String dayTime, String splitStr)
    {
        if(dayTime == null)
        {
            return false;
        }
        
        if(dayTime.startsWith(splitStr) || dayTime.endsWith(splitStr))
        {
            return false;
        }
        
        String[] split = dayTime.split(splitStr);
        if(split.length != 3)
        {
            return false;
        }
        
        if(CenterToolUtil.isValidHour(split[0]) && CenterToolUtil.isValidMinute(split[1]) && CenterToolUtil.isValidSecond(split[2]))
        {
            this.hour = Integer.parseInt(split[0]) + "";
            this.minute = Integer.parseInt(split[1]) + "";
            this.second = Integer.parseInt(split[2]) + "";
            return true;
        }

        return false;
    }
    
    public boolean setWeekDay(String value)
    {
        if(CenterToolUtil.isValidWeekDay(value))
        {
            this.weekDay = value;
            return true;
        }
        
        return false;
    }
    
    public boolean setDate(String value)
    {
        if(CenterToolUtil.isValidDay(value))
        {
            this.day = value;
            this.weekDay = "?";
            return true;
        }
        
        return false;
    }

    public String getCronExpress()
    {
        return this.second + " " + this.minute + " " + this.hour + " " + this.day + " " + this.month + " " + this.weekDay;
    }
}

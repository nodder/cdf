package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

import java.util.HashMap;


/**
 * <p>文件名称: CronTime.java</p>
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
public class CronExpressParser
{
    private static final HashMap<String ,String> weekDisplayStr = buildWeekDisplayStr();

    private String second = null;
    private String minute = null;
    private String hour = null;
    private String day = null;
    private String month = null;
    private String weekDay = null;

    private String expression = null;
    private boolean isDaily = false;
    private boolean isWeekly = false;
    private boolean isMonth = false;

    public CronExpressParser(String expression)
    {
        this.expression = expression;
        buildExpression();
        parseExpress();
    }

    private static HashMap<String, String> buildWeekDisplayStr()
    {
        HashMap<String, String> weekMap = new HashMap<String, String>();
        
        for(int i = 0; i< CenterToolUtil.WEEKDAY.length; i++)
        {
            weekMap.put(CenterToolUtil.WEEKDAY[i][0], CenterToolUtil.WEEKDAY[i][1]);
        }
        
        return weekMap;
    }
    
    private void buildExpression()
    {
        String[] split = expression.trim().split(" ");
        if(split.length == 6)
        {
            try
            {
                second = split[0];
                minute = split[1];
                hour = split[2];
                day = split[3];
                month = split[4];
                weekDay = split[5];
            }
            catch(NumberFormatException e)
            {
            }
        }
    }

    private void parseExpress()
    {
        this.isDaily = isDaily();
        this.isWeekly = isWeekly();
        this.isMonth = isMonthly();
    }

    private boolean isMonthly()
    {
        return CenterToolUtil.isValidSecond(second) && CenterToolUtil.isValidMinute(minute) && CenterToolUtil.isValidHour(hour)
        &&  CenterToolUtil.isValidDay(day) && "*".equalsIgnoreCase(month) && "?".equalsIgnoreCase(weekDay);
    }

    private boolean isDaily()
    {
        return CenterToolUtil.isValidSecond(second) && CenterToolUtil.isValidMinute(minute) && CenterToolUtil.isValidHour(hour)
               && "?".equalsIgnoreCase(day) && "*".equalsIgnoreCase(month) && "*".equalsIgnoreCase(weekDay);
    }

    private boolean isWeekly()
    {
        return CenterToolUtil.isValidSecond(second) && CenterToolUtil.isValidMinute(minute) && CenterToolUtil.isValidHour(hour)
               && "?".equalsIgnoreCase(day) && "*".equalsIgnoreCase(month) && CenterToolUtil.isValidWeekDay(weekDay);
    }
    
    private static String getLengthTwo(String str)
    {
        if(str.length() == 1)
        {
            return "0" + str;
        }
        
        return str;
    }

    public String getDayTime()
    {
        if(hour == null || minute == null || second == null)
        {
            return "--";
        }
        
        return getLengthTwo(hour) + ":" + getLengthTwo(minute) + ":" + getLengthTwo(second);
    }

    public String getWeekDay()
    {
        return weekDisplayStr.get(weekDay);
    }
    
    public String getDay()
    {
        if(day == null)
        {
            return "--";
        }
        
        return this.day;
    }

    public String toDisplayStr()
    {
        if(isDaily)
        {
            return getDayTime() + " on everyday";
        }
        else if(isWeekly)
        {
            return getDayTime() + " on every " + getWeekDay();
        }
        else if(isMonth)
        {
            return getDayTime() + " on " + getDate() + " every month";
        }
        
        return this.expression;
    }
    
    private String getDate()
    {
        return this.day + getDaySuffix();
    }

    private String getDaySuffix()
    {
        String suffix;
        if("1".endsWith(day))
        {
            suffix = "st";
        }
        else if("2".endsWith(day))
        {
            suffix = "nd";
        }
        else if("3".endsWith(day))
        {
            suffix = "rd";
        }
        else
        {
            suffix = "th";
        }
        return suffix;
    }

    public boolean getIsDaily()
    {
        return this.isDaily;
    }
    
    public boolean getIsWeekly()
    {
        return this.isWeekly;
    }
    
    public boolean getIsMonthly()
    {
        return this.isMonth;
    }
}

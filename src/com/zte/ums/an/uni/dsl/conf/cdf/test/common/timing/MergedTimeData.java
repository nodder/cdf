package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.util.ArrayList;

/**
 * <p>文件名称: MergedTimeData.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-30</p>
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
public class MergedTimeData
{
    private String markName;
    private int round = -1;
    private float max = -1;
    private float min = -1;
    private float average = -1;
    
    public String getMarkName()
    {
        return markName;
    }
    public void setMarkName(String markName)
    {
        this.markName = markName;
    }
    
    public int getRound()
    {
        return round;
    }
    public void setRound(int round)
    {
        this.round = round;
    }
    public float getMax()
    {
        return max;
    }
    public void setMax(float max)
    {
        this.max = max;
    }
    public float getMin()
    {
        return min;
    }
    public void setMin(float min)
    {
        this.min = min;
    }
    public float getAverage()
    {
        return average;
    }
    public void setAverage(float average)
    {
        this.average = average;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(this.round).append(KPNTimeMarkConst.SEPARATE).append(this.markName).append(KPNTimeMarkConst.SEPARATE)
        .append(min).append(KPNTimeMarkConst.SEPARATE).append(max).append(KPNTimeMarkConst.SEPARATE).append(average).append("\n");
        return buf.toString();
    }
    
    public String[] getData()
    {
        ArrayList<String> data = new ArrayList<String>();
        data.add(Long.toString(round));
        data.add(markName);
        data.add(Float.toString(min));
        data.add(Float.toString(max));
        data.add(Float.toString(average));
        
        return (String[])data.toArray(new String[data.size()]);
    }
    
    public static String[] getHeader()
    {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("Round");
        headers.add("Mark Name");
        headers.add("Minimum Time(s)");
        headers.add("Maximum Time(s)");
        headers.add("Average Time(s)");
        
        return (String[])headers.toArray(new String[headers.size()]);
    }
}

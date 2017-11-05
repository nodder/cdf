package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

/**
 * <p>文件名称: OriginalTimeMark</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-29</p>
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
public class OriginalTimeMark
{
    private String mark;
    private long startTime;
    private long endTime;
    
    public OriginalTimeMark(String markName)
    {
        this.mark = markName;
    }
    
    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }
    
    public void markStart()
    {
        startTime = System.currentTimeMillis();
    }
    
    public void markEnd()
    {
        endTime = System.currentTimeMillis();
        OriTimeMarkCollection.getInstance().addMark(this);
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("markName == " + this.mark + "\n");
        buf.append("startTime == " + this.startTime + "\n");
        buf.append("endTime == " + this.endTime + "\n");
        
        return buf.toString();
    }
}

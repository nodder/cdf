package com.zte.ums.an.uni.dsl.conf.cdf.common;

/**
 * <p>文件名称: CdfTime</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月14日</p>
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
public class CdfTime
{
    private long startTime;
    private long endTime;
    
    public void markStartTime()
    {
        startTime = System.currentTimeMillis();
    }
    
    public void markEndTime()
    {
        endTime = System.currentTimeMillis();
    }
    
    public float caculateTimeCost() throws Exception
    {
        if(endTime == 0 || startTime == 0)
        {
            throw new Exception("remark start time and end time first.");
        }
        
        if(endTime < startTime)
        {
            throw new Exception("end time must be larger than start time.");
        }
        
       return (float)(endTime - startTime)/1000;
    }
}

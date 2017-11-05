package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

/**
 * <p>文件名称: TestResult.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-12</p>
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
public class CdfTestResult
{
    public boolean isSuccess = false;
    public String detailStr = "";
    
    public CdfTestResult(String detailStr)
    {
        this.detailStr = detailStr;
    }
    
    public CdfTestResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("isSuccess      = ").append(isSuccess).append("\n");
        buf.append("detailStr      = ").append(detailStr).append("\n");
        
        return buf.toString();
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((detailStr == null) ? 0 : detailStr.hashCode());
        result = prime * result + (isSuccess ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(!(obj instanceof CdfTestResult))
        {
            return false;
        }
        
        CdfTestResult result = (CdfTestResult)obj;
        if((detailStr == result.detailStr) && (isSuccess == result.isSuccess))
        {
            return true;
        }
        
        return false;
    }
}

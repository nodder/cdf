package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;


/**
 * <p>文件名称: AppendInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-2-29</p>
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
public class FileAppendInfo
{ 
    private final int indexMerge;
    private final int indexAppend;
    private final int[] valueAppend;
    
    public FileAppendInfo(int indexMerge, int indexAppend, int[] valueAppend)
    {
        this.indexMerge = indexMerge;
        this.indexAppend = indexAppend;
        this.valueAppend = valueAppend;
    }
    
    public int getIndexMerge()
    {
        return indexMerge;
    }


    public int getIndexAppend()
    {
        return indexAppend;
    }


    public int[] getValueAppend()
    {
        return valueAppend;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" indexMerge    == " + indexMerge + "\n");
        buf.append(" indexAppend   == " + indexAppend + "\n");
        buf.append(" valueAppend   == [");
        for(int i : valueAppend)
        {
            buf.append(i + ",");
        }
        if(valueAppend.length != 0)
        {
            buf.delete(buf.length() - 1, buf.length());
        }
        buf.append("]\n");
        
        return buf.toString();
    }
}

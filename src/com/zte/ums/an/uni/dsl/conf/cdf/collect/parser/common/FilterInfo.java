package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.util.Arrays;


/**
 * <p>文件名称: FilterInfo</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012年7月12日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author ljy
 */
public class FilterInfo
{
    private int indexFilter = -1;
    private String[] valuesFilter = null;
    
    public FilterInfo()
    {
        
    }
    
    public FilterInfo(int index, String[] values)
    {
        indexFilter = index;
        valuesFilter = values;
    }
    
    public int getIndexFilter()
    {
        return indexFilter;
    }
    public void setIndexFilter(int indexFilter)
    {
        this.indexFilter = indexFilter;
    }
    public String[] getValuesFilter()
    {
        return valuesFilter;
    }
    public void setValuesFilter(String[] valuesFilter)
    {
        this.valuesFilter = valuesFilter;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" indexFilter == " + indexFilter + "\n");
        buf.append(" valuesFilter == " + Arrays.toString(valuesFilter) + "\n");
        return buf.toString();
    }
}

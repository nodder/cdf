package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;


/**
 * <p>文件名称: IPv4Field</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月21日</p>
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

public class EntryIdField implements Cloneable
{
    int min;
    int max;
    int value;
    
    EntryIdField nextField;
    boolean isEnd = false;
    
    public EntryIdField(int min, int max)
    {
        this.min = min;
        this.max = max;
        
        value = min;
    }
  
    public void setNextField(EntryIdField nextField)
    {
        this.nextField = nextField;
    }
        
    public void next()
    {
        if(value >= max)
        {
            if(this.nextField != null)
            {
                this.nextField.next();
            }
            else
            {
                this.isEnd = true;
            }
        }
        
        increaseFieldValue();
    }
    
    private void increaseFieldValue()
    {
        if(value >= max)
        {            
            value = min;
        }
        else
        {
            ++value;
        }
    }
    
    public int getFieldValue()
    {
        return value;
    }
    
    public boolean isEnd()
    {
        return this.isEnd;
    }
    
    @Override
    public String toString()
    {
        return "min == " + min + "max == " + max;
    }
    
    @Override
    protected Object clone()
    {
        EntryIdField oc = null;
        try
        {
            oc = (EntryIdField)super.clone();
            
            if(this.nextField != null)
            {
                oc.nextField = (EntryIdField)(this.nextField.clone());
            }
        }
        catch(CloneNotSupportedException ex)
        {
            ex.printStackTrace();
        }

        return oc;
    }
}

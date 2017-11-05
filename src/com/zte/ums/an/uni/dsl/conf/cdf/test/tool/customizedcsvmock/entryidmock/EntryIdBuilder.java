package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;


/**
 * <p>文件名称: EntryIdMocker.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-26</p>
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
public class EntryIdBuilder
{   
    private String separate = "/";
    
    private EntryIdField[] fields;
      
    public EntryIdBuilder(EntryIdField[] fields)
    {
        this.fields = new EntryIdField[fields.length];
        for(int i = 0; i < fields.length; i++)
        {
            this.fields[i] = (EntryIdField)(fields[i].clone());
        }
        
        buildChain();
    }
    
    public EntryIdBuilder(EntryIdField[] fields, String separate)
    {
        this(fields);
        
        this.separate = separate;
    }

    private void buildChain()
    {        
        if(fields.length >= 2)
        {
            for(int i = fields.length - 2; i >= 0; i--)
            {
                fields[i + 1].setNextField(fields[i]);
            }
        }
    }
    
    private boolean isEnd()
    {
        return fields[0].isEnd;
    }
    
    private void next()
    {
        fields[fields.length - 1].next();
    }
    
    public String getNext()
    {
        String nextEntry = null;
        
        if(!isEnd())
        {
            nextEntry = assembleEntryId();
            next();
        }
        
        return nextEntry;
    }

    private String assembleEntryId()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(fields[0].getFieldValue());
        
        if(fields.length >= 2)
        {
            for(int i = 1; i <= fields.length - 1; i++)
            {
                buf.append(separate).append(fields[i].getFieldValue());
            }
        }

        return buf.toString();
    }
}

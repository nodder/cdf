package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;

import java.util.ArrayList;

/**
 * <p>文件名称: MultyEntryIdBuilder.java</p>
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
public class MultiEntryIdBuilder
{
    private ArrayList<EntryIdBuilder> singleBuilders;
    private int index = 0;
    
    public MultiEntryIdBuilder(EntryIdField[][] arrFields)
    {
        for(EntryIdField[] fields : arrFields)
        {
            EntryIdBuilder singleBuilder = new EntryIdBuilder(fields);
            singleBuilders.add(singleBuilder);
        }
    }
    
    public String getNext()
    {
        String next = singleBuilders.get(index).getNext();

        if(next == null)
        {
            while(index < singleBuilders.size() - 1)
            {
                index++;

                next = singleBuilders.get(index).getNext();
                return next;
            }
        }

        return next;
    }
    
}

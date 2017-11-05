package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.bufferreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;


/**
 * <p>文件名称: DoubleLineBufferedReader</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月14日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */
public class DoubleLineBufferedReader extends BufferedReader
{
    private int lineNumber = 0;
    
    public DoubleLineBufferedReader(Reader in)
    {
        super(in);
    }
    
    @Override
    public String readLine() throws IOException
    {
        //TODO 校验上下行
        if(lineNumber == 0)
        {
            lineNumber++;
            return super.readLine();
        }
        
        String line1 = super.readLine();
        String line2 = super.readLine();
        
        if(line1 == null || line2 == null)
        {
            return null;
        }
        
        Record r1 = new Record(line1);
        Record r2 = new Record(line2);
        
        String entryId = r1.getEntryId().substring(0, r1.getEntryId().length() - 2);
        
        return entryId + "," + r1.getLineRec() + "," + r2.getLineRec();
    }
}

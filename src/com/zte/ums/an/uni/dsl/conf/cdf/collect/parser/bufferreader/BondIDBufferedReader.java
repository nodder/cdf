package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.bufferreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * <p>文件名称: BondIDBufferedReader.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-5</p>
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
public class BondIDBufferedReader extends BufferedReader
{
    public BondIDBufferedReader(Reader in)
    {
        super(in);
    }
    
    @Override
    public String readLine() throws IOException
    {        
        String line = super.readLine();
        
        if(line == null || line.equalsIgnoreCase(""))
        {
            return line;
        }
        
        String entryId = line.substring(0, line.indexOf(","));
        
        String cpn = entryId.substring(0, entryId.lastIndexOf("/"));
        String bondId = entryId.substring(entryId.lastIndexOf("/") + 1);
        
        StringBuffer buf = new StringBuffer();
        buf.append(cpn).append(",").append(bondId).append(",").append(line.substring(line.indexOf(",") + 1));
        
        return buf.toString();
    }
}

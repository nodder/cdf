package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append;

import java.util.ArrayList;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileAppendInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.SeqRecProxy;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;

/**
 * <p>文件名称: DataAppender.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-2-27</p>
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
public class CopyOfDataAppender
{
    private FileInfo[] appendFileInfoList;    
    
    /** 文件读取缓存 */
    private DataAppenderBuffer buf;
    
    public CopyOfDataAppender(BulkPoolGroupInfo group)
    {
        appendFileInfoList = group.getAppendFileInfoList();
        buf = new DataAppenderBuffer(appendFileInfoList);
    }
    
    public ArrayList<String> afterAppend(Record mergedRecord) throws Exception
    {
        ArrayList<String> oriData = mergedRecord.lineToArrayList();
        
        for(FileInfo fileInfo : appendFileInfoList)
        {
            appendToMergeLineThroughFileIO(oriData, fileInfo);
        }
        
        return oriData;
    }

    private void appendToMergeLineThroughFileIO(ArrayList<String> mainMergedLine, FileInfo fileInfo) throws Exception
    {
        for(FileAppendInfo appendInfo : fileInfo.getAppendInfos())
        {
            boolean operByBuffer = appendByBuffer(mainMergedLine, fileInfo, appendInfo);

            if(!operByBuffer)
            {
                appendByFileIO(mainMergedLine, fileInfo, appendInfo);
            }
        }
    }

    private boolean appendByBuffer(ArrayList<String> mainMergedLine, FileInfo fileInfo, FileAppendInfo appendInfo)
    {
        buf.resetCursor(fileInfo);
        
        while(buf.hasNext(fileInfo))
        {
            ArrayList<String> tmpAppendLine = buf.next(fileInfo);
            
            if(matchAndAppendTmpLineToMergeLine(mainMergedLine, appendInfo.getIndexMerge(), tmpAppendLine, appendInfo.getIndexAppend(),
                appendInfo.getValueAppend()))
            {
                return true;
            }
        }
        
        return false;
    }

    private void appendByFileIO(ArrayList<String> mainMergedLine, FileInfo fileInfo, FileAppendInfo appendInfo) throws Exception
    {
        SeqRecProxy appendFileReadProxy = null;

        try
        {
            appendFileReadProxy = CdfUtil.createProxy(fileInfo.getFile(), fileInfo.getReadProxy());

            while(appendFileReadProxy.next())
            {
                ArrayList<String> tmpAppendLine = appendFileReadProxy.getRecord().lineToArrayList();

                if(matchAndAppendTmpLineToMergeLine(mainMergedLine, appendInfo.getIndexMerge(), tmpAppendLine, appendInfo.getIndexAppend(),
                    appendInfo.getValueAppend()))
                {
                    buf.addBuffer(fileInfo, tmpAppendLine);
                    return;
                }
            }
            
            appendDefault(mainMergedLine, appendInfo);
        }
        catch(IndexOutOfBoundsException ex)
        {
            throw new RuntimeException("fileInfo : " + fileInfo, ex);
        }
        finally
        {
            if(appendFileReadProxy != null)
            {
                appendFileReadProxy.close();
            }
        }
    }

    private void appendDefault(ArrayList<String> mainMergedLine, FileAppendInfo appendInfo)
    {
        int count = appendInfo.getValueAppend().length;
        
        for(int i = 0; i < count; i++)
        {
            mainMergedLine.add(CdfConst.REPORT_VALUE_NOTSET);
        }
    }
    
    private static boolean matchAndAppendTmpLineToMergeLine(ArrayList<String> mainMergedLine, int indexMerge, ArrayList<String> appendLine,
                                                int indexAppend, int[] valueAppend)
    {
        if(mainMergedLine.get(indexMerge).equals(appendLine.get(indexAppend)))
        {
            for(int targeIndex : valueAppend)
            {
                mainMergedLine.add(appendLine.get(targeIndex));
            }
            return true;
        }
        
        return false;
    }

    public static void main(String[] args)
    {
        //TEST1 
//        ArrayList<String> oriLine = new ArrayList<String>();
//        oriLine.add("1/1/1/1");
//        oriLine.add("1");
//        oriLine.add("11");
//        
//        ArrayList<String> targetLine = new ArrayList<String>();
//        targetLine.add("1/1/1/1");
//        targetLine.add("target1");
//        targetLine.add("target11");
//        
//        matchAndAppendTmpLineToMergeLine(oriLine, 0, targetLine, 0, new int[] {1, 2});
//
//        Assert.assertEquals(5, oriLine.size());
//        int i = 0;
//        Assert.assertEquals("1/1/1/1", oriLine.get(i++));
//        Assert.assertEquals("1", oriLine.get(i++));
//        Assert.assertEquals("11", oriLine.get(i++));
//        Assert.assertEquals("target1", oriLine.get(i++));
//        Assert.assertEquals("target11", oriLine.get(i++));
//        
//        //TEST2 
//        oriLine = new ArrayList<String>();
//        oriLine.add("1/1/1/1");
//        oriLine.add("1");
//        oriLine.add("11");
//        
//        targetLine = new ArrayList<String>();
//        targetLine.add("2221/1/1/1");
//        targetLine.add("target1");
//        targetLine.add("target11");
//        
//        matchAndAppendTmpLineToMergeLine(oriLine, 0, targetLine, 0, new int[] {1, 2});
//
//        Assert.assertEquals(3, oriLine.size());
//        i = 0;
//        Assert.assertEquals("1/1/1/1", oriLine.get(i++));
//        Assert.assertEquals("1", oriLine.get(i++));
//        Assert.assertEquals("11", oriLine.get(i++));
        
        System.out.println("OK");
    }
}

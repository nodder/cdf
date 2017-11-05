package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append;

import java.util.ArrayList;
import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;

/**
 * <p>文件名称: DataAppenderBuffer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-1</p>
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
public class CopyOfDataAppenderBuffer
{
    private HashMap<String, ArrayList<ArrayList<String>>> fileToLine = null;
    private HashMap<String, Integer> fileToCursor = null;
    
    public CopyOfDataAppenderBuffer(FileInfo[] appendFileInfoList)
    {
        initHashMap(appendFileInfoList);
    }

    private void initHashMap(FileInfo[] appendFileInfoList)
    {
        initFileToLine(appendFileInfoList);
        
        initFileToCursor(appendFileInfoList);
    }

    private void initFileToCursor(FileInfo[] appendFileInfoList)
    {
        fileToCursor = new HashMap<String, Integer>();
        for(FileInfo fileInfo : appendFileInfoList)
        {
            fileToCursor.put(getKey(fileInfo), -1);
        }
    }

    private void initFileToLine(FileInfo[] appendFileInfoList)
    {
        fileToLine = new HashMap<String, ArrayList<ArrayList<String>>>();
        for(FileInfo fileInfo : appendFileInfoList)
        {
            fileToLine.put(getKey(fileInfo), new ArrayList<ArrayList<String>>());   
        }
    }
    
    private String getKey(FileInfo fileInfo)
    {
        return fileInfo.getbulkPoolName();
    }

    public synchronized void addBuffer(FileInfo fileInfo, ArrayList<String> tmpAppendLine)
    {
        fileToLine.get(getKey(fileInfo)).add(tmpAppendLine);
    }

    public synchronized boolean hasNext(FileInfo fileInfo)
    {
        return fileToCursor.get(getKey(fileInfo)) < fileToLine.get(getKey(fileInfo)).size() - 1;
    }

    public void resetCursor(FileInfo fileInfo)
    {
        fileToCursor.put(getKey(fileInfo), -1);
    }

    public synchronized ArrayList<String> next(FileInfo fileInfo)
    {
        int next = 1 + fileToCursor.get(getKey(fileInfo));
        fileToCursor.put(getKey(fileInfo), next);
        
        return fileToLine.get(getKey(fileInfo)).get(next);
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>文件名称: OriTimeMarkCollection</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-29</p>
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
public class OriTimeMarkCollection
{
    private static OriTimeMarkCollection instance = new OriTimeMarkCollection();
    
    ArrayList<OriginalTimeMark> markList = new ArrayList<OriginalTimeMark>();
    private int round = 0;
    
    public synchronized void addMark(OriginalTimeMark timeMark)
    {
        this.markList.add(timeMark);
    }

    public static OriTimeMarkCollection getInstance()
    {
        return instance;
    }
    
    public void reInistialize()
    {
        this.round++;
        this.markList.clear();
    }

    public synchronized int size()
    {
        return markList.size();
    }
    
    public synchronized ArrayList<OriginalTimeMark> getTimeMarkList()
    {
        return markList;
    }
    
    public void mergeAndPrint(int totalNE, int failedNE)
    {
        TimeMarkMerger merger = new TimeMarkMerger(markList);
        HashMap<String, MergedTimeData> allData = merger.getMergedTimeData();

        print(allData, totalNE, failedNE);
    }

    private void print(HashMap<String, MergedTimeData> allData, int totalNE, int failedNE)
    {
        KPNTimingResultPrinter.exportResult(round, allData, totalNE, failedNE);
    }
    
    //TEST
    private static void mockTimeMark2()
    {
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
    }
    
    private static void mockTimeMark()
    {
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.DB);
        createNamedTimeMark(KPNTimeMarkConst.DB);
        createNamedTimeMark("useless");
    }
    
    private static void createNamedTimeMark(String name)
    {
        OriginalTimeMark tMark = new OriginalTimeMark(name);
        tMark.markStart();
        sleep();
        tMark.markEnd();
    }
    
    private static void sleep()
    {
        try
        {
            Thread.sleep((long)(Math.random() * 300));
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
//        mockTimeMark();
//        OriTimeMarkCollection.getInstance().mergeAndPrint(2);
//        
//        OriTimeMarkCollection.getInstance().reInistialize();
//        mockTimeMark();
//        OriTimeMarkCollection.getInstance().mergeAndPrint(3);
    }
}


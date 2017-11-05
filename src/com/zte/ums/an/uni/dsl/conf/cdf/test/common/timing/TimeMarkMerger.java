package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import com.zte.ums.an.uni.dsl.conf.cdf.test.common.CdfMockUtil;

/**
 * <p>文件名称: TimeMarkMerger.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-30</p>
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
public class TimeMarkMerger
{
    HashMap<String, MergedTimeData> map = new HashMap<String, MergedTimeData>(); 
    
    public TimeMarkMerger(ArrayList<OriginalTimeMark> markList)
    {
        HashMap<String, TreeSet<Float>> mapTimeCost = markNameToTimeCost(markList);
        
        buildMap(mapTimeCost);
    }
    
    private void buildMap(HashMap<String, TreeSet<Float>> mapTimeCost)
    {
        Iterator<Entry<String, TreeSet<Float>>> it = mapTimeCost.entrySet().iterator();
        
        while(it.hasNext())
        {
            Entry<String, TreeSet<Float>> entry = it.next();
            
            String markName = entry.getKey();
            TreeSet<Float> timeCostSet = entry.getValue();
            
            MergedTimeData data = assembleMergedData(timeCostSet, markName);
            
            map.put(markName, data);
        }
    }

    private MergedTimeData assembleMergedData(TreeSet<Float> timeCostSet, String markName)
    {
        MergedTimeData data = new MergedTimeData();
        data.setMarkName(markName);
        data.setMin(CdfMockUtil.formatFloat(timeCostSet.first()));
        data.setMax(CdfMockUtil.formatFloat(timeCostSet.last()));
        data.setAverage(CdfMockUtil.formatFloat(averageValue(timeCostSet)));
        
        return data;
    }
    
    private float averageValue(TreeSet<Float> timeCostSet)
    {
        float sum = 0;
        Iterator<Float> it = timeCostSet.iterator();
        while(it.hasNext())
        {
            sum += it.next().floatValue();
        }
        
        return sum/timeCostSet.size();
    }

    private HashMap<String, TreeSet<Float>> markNameToTimeCost(ArrayList<OriginalTimeMark> markList)
    {
       HashMap<String, TreeSet<Float>> markNameToTimeCost = new HashMap<String, TreeSet<Float>>();
       
       for(OriginalTimeMark o : markList)
       {
           String markName = o.getMark();
           
           if(!markNameToTimeCost.containsKey(markName))
           {
               markNameToTimeCost.put(markName, new TreeSet<Float>());
           }
           
           float timeCost = ((float)(o.getEndTime() - o.getStartTime()))/1000;
           markNameToTimeCost.get(markName).add(timeCost);
       }
        
       return markNameToTimeCost;
    }

    public HashMap<String, MergedTimeData> getMergedTimeData()
    {
        return map;
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FilterInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.filter.DataFilter;

/**
 * <p>文件名称: DataFilterTest.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-7-13</p>
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
public class DataFilterTest extends TestCase
{
    private DataFilter filter;
    
    private FilterInfo f1;
    private FilterInfo f2;
    private FilterInfo f3;
    
    private ArrayList<String> line1 = new ArrayList<String>();
    private ArrayList<String> line2 = new ArrayList<String>();
    private ArrayList<String> line3 = new ArrayList<String>();
    private ArrayList<String> line4 = new ArrayList<String>();
    
    @Override
    protected void setUp() throws Exception
    {
        f1 = new FilterInfo(2, new String[]{"a"});
        f2 = new FilterInfo(4, new String[]{"5", "7"});
        f3 = new FilterInfo(0, new String[]{"1/1/1/3/.*"});

        filter = new DataFilter();
        
        FilterInfo[] infos = new FilterInfo[3];
        infos[0] = f1;
        infos[1] = f2;
        infos[2] = f3;
        
        filter.setFilterInfos(infos);

        addToArrayList(line1, "1/1/1/1", "11", "12", "13", "14");
        addToArrayList(line2, "1/1/1/2", "22", "22", "23", "5");
        addToArrayList(line3, "1/1/1/3/4094", "22a", "22a", "23a", "5a");
        addToArrayList(line4, "1/1/1/4/4094", "22a", "22a", "23a", "5a");
    }
    
    private void addToArrayList(ArrayList<String> list, String... params)
    {
        for(String p : params)
        {
            list.add(p);
        }
    }
    
    public void testNoneFilter()
    {
        assertTrue(new DataFilter().isNeedReserve(line1));
    }
    
    public void testNoMatchFilter()
    {
        assertFalse(filter.isNeedReserve(line1));
    }
    
    public void testMatchFilter()
    {
        assertTrue(filter.isNeedReserve(line2));
    }
    
    public void testMatchRegex()
    {
        assertTrue(filter.isNeedReserve(line3));
    }
    
    public void testNoMatchRegex()
    {
        assertFalse(filter.isNeedReserve(line4));
    }
}

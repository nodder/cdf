package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolAnalysis;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;

/**
 * <p>文件名称: DataProcessTest.java</p>
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
public class DataProcessTest extends TestCase
{
    private static boolean isInit = false;
    
    private static final String CSV0TITLE = "slot/port,p1,p2";
    private static final String CSV0LINE1 = "1/1/1/1,a1,a11";
    private static final String CSV0LINE2 = "1/1/1/2,a2,a22";
    private static final String CSV0LINE3 = "1/1/1/3,a3,a33";
    private static final String CSV0LINE4 = "1/1/1/4,a4,a44";
    private static final String CSV0LINE5 = "1/1/2/1,b1,b11";
    private static final String CSV0LINE6 = "1/1/2/2,b2,b2";
    
    private static final String CSV1TITLE = "slot/port,p1,p2";
    private static final String CSV1LINE1 = "1/1/1/1,1,11";
    private static final String CSV1LINE2 = "1/1/2/1,2,22";
    
    private static final String CSV2TITLE = CSV1TITLE;
    private static final String CSV2LINE1 = "1/1/1/1,a,aa";
    private static final String CSV2LINE2 = "1/1/1/2,a,bb";
    private static final String CSV2LINE3 = "1/1/2/1,c,cc";
    
    private static final String CSV_APPTITLE = "string,value";
    private static final String CSV_APPLINE1 = "a,A1_From_APPFile,A2_From_APPFile";
    private static final String CSV_APPLINE2 = "c,C1_From_APPFile,C2_From_APPFile";
    
    private static final String CSV_APPTITLEEE = "string,value,value2";
    private static final String CSV_APPLINE111 = "aa,AA1_From_APPFile,AA2_From_APPFile";
    private static final String CSV_APPLINE222 = "bb,BB1_From_APPFile,BB2_From_APPFile";
    private static final String CSV_APPLINE333 = "ee,ee1_From_APPFile,ee2_From_APPFile";
    
    private static final String CSV1 = "conf/10.63.192.155_KPNTEST11_20000318003409.csv";
    private static final String CSV2 = "conf/10.63.192.155_KPNTEST22_20000318003429.csv";
    
    private static final String CSVD2TITLE = CSV1TITLE;
    private static final String CSVD2LINE1 = "1/1/1/1/1,a1,aa11";
    private static final String CSVD2LINE2 = "1/1/1/1/2,a2,aa22";
    private static final String CSVD2LINE3 = "1/1/1/2/1,b1,bb11";
    private static final String CSVD2LINE4 = "1/1/1/2/2,b2,bb22";
    private static final String CSVD2LINE5 = "1/1/2/1/1,c1,cc11";
    private static final String CSVD2LINE6 = "1/1/2/1/2,c2,cc22";
    
    private static final String CSVD0 = "conf/10.63.192.155_KPNDoubleLine000_20000318003419.csv";
    private static final String CSVD1 = "conf/10.63.192.155_KPNDoubleLine111_20000318003409.csv";
    private static final String CSVD2 = "conf/10.63.192.155_KPNDoubleLine222_20000318003429.csv";
    
    private static final String CSV_APPEND111 = "conf/10.63.192.155_KPN111_20000318003427.csv";
    private static final String CSV_APPEND_APP111 = "conf/10.63.192.155_KPNAppend111_20000318003427.csv";
    private static final String CSV_APPEND_APP222 = "conf/10.63.192.155_KPNAppend222_20000318003427.csv";
    
    private static final String CSV_Filter111 = "conf/10.63.192.155_OneFilterMerge111_20000318003427.csv";
    private static final String CSV_Filter222 = "conf/10.63.192.155_OneFilterMerge222_20000318003427.csv";
    private static final String CSV_Filter333 = "conf/10.63.192.155_OneFilterMerge333_20000318003427.csv";
    
    private static final String CSV_MultiFilter111 = "conf/10.63.192.155_MultiFilterMerge111_20000318003427.csv";
    private static final String CSV_MultiFilter222 = "conf/10.63.192.155_MultiFilterMerge222_20000318003427.csv";
    private static final String CSV_MultiFilter333 = "conf/10.63.192.155_MultiFilterMerge333_20000318003427.csv";
    
    private static void createFile(File outputFile, String fileContent) throws FileNotFoundException
    {
        outputFile.getParentFile().mkdirs();
        PrintWriter pw = new PrintWriter(outputFile);
        pw.println(fileContent);
        pw.close();
    }
    
    @Override
    protected void setUp() throws Exception
    {
        if(!isInit)
        {
            createFile(new File("conf/cdf-sub-collect-data.xml"), SubCollectXmlContent.subCollectDataXml);
            createFile(new File("conf/cdf-sub-collect.xml"), SubCollectXmlContent.subCollectXml);
            
            SubCollectCache.initParams();
            snmpOperForInitCsv();
            
            isInit = true;
        }
      
    }
//    
//    @Override
//    protected void tearDown() throws Exception
//    {
//        CdfUtil.deleteDir(new File("conf"));
//    }
    
    private void snmpOperForInitCsv() throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV1)));
        pw.println(CSV1TITLE);
        pw.println(CSV1LINE1);
        pw.println(CSV1LINE2);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV2)));
        pw.println(CSV2TITLE);
        pw.println(CSV2LINE1);
        pw.println(CSV2LINE2);
        pw.println(CSV2LINE3);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSVD1)));
        pw.println(CSV1TITLE);
        pw.println(CSV1LINE1);
        pw.println(CSV1LINE2);
        pw.close();

        //testDouble
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSVD0)));
        pw.println(CSV0TITLE);
        pw.println(CSV0LINE1);
        pw.println(CSV0LINE2);
        pw.println(CSV0LINE3);
        pw.println(CSV0LINE4);
        pw.println(CSV0LINE5);
        pw.println(CSV0LINE6);
        pw.close();
        
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSVD1)));
        pw.println(CSV1TITLE);
        pw.println(CSV1LINE1);
        pw.println(CSV1LINE2);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSVD2)));
        pw.println(CSVD2TITLE);
        pw.println(CSVD2LINE1);
        pw.println(CSVD2LINE2);
        pw.println(CSVD2LINE3);
        pw.println(CSVD2LINE4);
        pw.println(CSVD2LINE5);
        pw.println(CSVD2LINE6);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_APPEND111)));
        pw.println(CSV2TITLE);
        pw.println(CSV2LINE1);
        pw.println(CSV2LINE2);
        pw.println(CSV2LINE3);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_APPEND_APP111)));
        pw.println(CSV_APPTITLE);
        pw.println(CSV_APPLINE1);
        pw.println(CSV_APPLINE2);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_APPEND_APP222)));
        pw.println(CSV_APPTITLEEE);
        pw.println(CSV_APPLINE111);
        pw.println(CSV_APPLINE222);
        pw.println(CSV_APPLINE333);
        pw.close();
        
        // Test one filter
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_Filter111)));
        pw.println(CSV2TITLE);
        pw.println(CSV2LINE1);
        pw.println(CSV2LINE2);
        pw.println(CSV2LINE3);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_Filter222)));
        pw.println(CSV_APPTITLE);
        pw.println(CSV_APPLINE1);
        pw.println(CSV_APPLINE2);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_Filter333)));
        pw.println(CSV_APPTITLEEE);
        pw.println(CSV_APPLINE111);
        pw.println(CSV_APPLINE222);
        pw.println(CSV_APPLINE333);
        pw.close();
        
        // Test multy filter
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_MultiFilter111)));
        pw.println(CSV2TITLE);
        pw.println(CSV2LINE1);
        pw.println(CSV2LINE2);
        pw.println(CSV2LINE3);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_MultiFilter222)));
        pw.println(CSV_APPTITLE);
        pw.println(CSV_APPLINE1);
        pw.println(CSV_APPLINE2);
        pw.close();
        
        pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(CSV_MultiFilter333)));
        pw.println(CSV_APPTITLEEE);
        pw.println(CSV_APPLINE111);
        pw.println(CSV_APPLINE222);
        pw.println(CSV_APPLINE333);
        pw.close();
    }
    
    private static String arrayListToString(ArrayList<String> arrayList)
    {
        StringBuffer buf = new StringBuffer();
        for(String s : arrayList)
        {
            buf.append(s).append(",");
        }
        
        if(buf.length() > 0)
        {
            return buf.substring(0, buf.length() - 1);
        }
        
        return "";
    }

    public void testSimple() throws Exception
    {      
        File[]  files = new File[2];
        files[0] = new File(CSV1);
        files[1] = new File(CSV2);
        
        BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(files);
        ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
        
        BulkPoolGroupInfo group = groups.get(0);
        DataProcess dp = new DataProcess(group);
        
        String expect1 = CSV1LINE1 + ",a,aa";
        String expect2 = CSV1LINE2 + ",c,cc";
        
        assertEquals(expect1, arrayListToString(dp.readNextLineParams()));
        assertEquals(expect2, arrayListToString(dp.readNextLineParams()));
        assertNull(dp.readNextLineParams());
        
        dp.close();
    }
    
    public void testDoubleLine() throws Exception
    {      
        File[] files = new File[3];
        files[0] = new File(CSVD1);
        files[1] = new File(CSVD2);
        files[2] = new File(CSVD0);//乱序
        
        
        BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(files);
        ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
        
        assertEquals(1, groups.size());
        
        BulkPoolGroupInfo group = groups.get(0);
        DataProcess dp = new DataProcess(group);
        
        String expect1 = CSV0LINE1 + ",1,11,a1,aa11,a2,aa22";
        String expect2 = CSV0LINE5 + ",2,22,c1,cc11,c2,cc22";
        
        assertEquals(expect1, arrayListToString(dp.readNextLineParams()));
        assertEquals(expect2, arrayListToString(dp.readNextLineParams()));
        assertNull(dp.readNextLineParams());
        
        dp.close();
    }
    
    public void testAppend() throws Exception
    {      
        File[] files = new File[3];
        files[0] = new File(CSV_APPEND111);
        files[1] = new File(CSV_APPEND_APP111);
        files[2] = new File(CSV_APPEND_APP222);
        
        BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(files);
        ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
        
        assertEquals(1, groups.size());
        
        BulkPoolGroupInfo group = groups.get(0);
        DataProcess dp = new DataProcess(group);
        
        String expect1 = CSV2LINE1 + ",A1_From_APPFile,A2_From_APPFile,AA1_From_APPFile,AA2_From_APPFile";
        String expect2 = CSV2LINE2 + ",A1_From_APPFile,A2_From_APPFile,BB1_From_APPFile,BB2_From_APPFile";
        String expect3 = CSV2LINE3 + ",C1_From_APPFile,C2_From_APPFile,,";
        assertEquals(expect1, arrayListToString(dp.readNextLineParams()));
        assertEquals(expect2, arrayListToString(dp.readNextLineParams()));
        assertEquals(expect3, arrayListToString(dp.readNextLineParams()));
        assertNull(dp.readNextLineParams());
        
        dp.close();
    }
    
    public void testOneFilter() throws Exception
    {
        File[] files = new File[3];
        files[0] = new File(CSV_Filter111);
        files[1] = new File(CSV_Filter222);
        files[2] = new File(CSV_Filter333);
        
        BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(files);
        ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
        
        assertEquals(1, groups.size());
        
        BulkPoolGroupInfo group = groups.get(0);
        DataProcess dp = new DataProcess(group);
        
        String expect1 = CSV2LINE1 + ",A1_From_APPFile,A2_From_APPFile,AA1_From_APPFile,AA2_From_APPFile";
        String expect2 = CSV2LINE2 + ",A1_From_APPFile,A2_From_APPFile,BB1_From_APPFile,BB2_From_APPFile";
        String expect3 = CSV2LINE3 + ",C1_From_APPFile,C2_From_APPFile,,";
        assertEquals(expect1, arrayListToString(dp.readNextLineParams()));
//        assertEquals(expect2, arrayListToString(dp.readNextLineParams()));
//        assertEquals(expect3, arrayListToString(dp.readNextLineParams()));
        assertNull(dp.readNextLineParams());
        
        dp.close();
    }
    
    public void testMultyFilter() throws Exception
    {
        File[] files = new File[3];
        files[0] = new File(CSV_MultiFilter111);
        files[1] = new File(CSV_MultiFilter222);
        files[2] = new File(CSV_MultiFilter333);
        
        BulkPoolAnalysis bpAnalysis = new BulkPoolAnalysis(files);
        ArrayList<BulkPoolGroupInfo> groups = bpAnalysis.exportBulkPoolGroups();
        
        assertEquals(1, groups.size());
        
        BulkPoolGroupInfo group = groups.get(0);
        DataProcess dp = new DataProcess(group);
        
        String expect1 = CSV2LINE1 + ",A1_From_APPFile,A2_From_APPFile,AA1_From_APPFile,AA2_From_APPFile";
        String expect2 = CSV2LINE2 + ",A1_From_APPFile,A2_From_APPFile,BB1_From_APPFile,BB2_From_APPFile";
        String expect3 = CSV2LINE3 + ",C1_From_APPFile,C2_From_APPFile,,";
        assertEquals(expect1, arrayListToString(dp.readNextLineParams()));
//        assertEquals(expect2, arrayListToString(dp.readNextLineParams()));
//        assertEquals(expect3, arrayListToString(dp.readNextLineParams()));
        assertNull(dp.readNextLineParams());
        
        dp.close();
        
        CdfUtil.deleteDir(new File("conf"));
    }
}

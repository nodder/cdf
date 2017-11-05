package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <ul>
 * <li>文件名称: Record</li>
 * <li>文件描述: </li>
 * <li>版权所有: 版权所有(C) 2003</li>
 * <li>公 司: 中兴通讯股份有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>完成日期:2011-12-06 </li>
 * </ul>
 * <ul>
 * <li>修改记录: </li>
 * <li>版 本 号: </li>
 * <li>修改日期: </li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 * 
 * @author jingxueshi
 * @version 1.0.0
 */

public class Record
{   
    private String entryId;
    private String lineRec;

    public Record(String line)
    {
        int commaIndex = line.indexOf(",");
        
        this.entryId = line.substring(0, commaIndex);
        this.lineRec = line.substring(commaIndex + 1);
    }
    
//    public Record(ArrayList<String> valueList)
//    {
//        entryId = valueList.get(0);
//        for(int i = 1; i < valueList.size(); i++)
//        {
//            
//        }
//    }
    
    public Record()
    {
    }
    
    public int getParamsNum()
    {
        StringTokenizer st = new StringTokenizer(this.lineRec, ",");
        return st.countTokens();
    }
    
    public String[] lineRecToArray()
    {
        return lineRec.split(",");
    }
        
    public String getEntryId()
    {
        return entryId;
    }

    public void setEntryId(String entryId)
    {
        this.entryId = entryId;
    }

    public String getLineRec()
    {
        return lineRec;
    }

    public void setLineRec(String lineRec)
    {
        this.lineRec = lineRec;
    }
        
    public ArrayList<String> lineToArrayList()
    {
        ArrayList<String> rtnList = new ArrayList<String>();
        rtnList.add(this.entryId);
        
        int lastIndex = 0;
        int index = lastIndex;
        while((index = this.lineRec.indexOf(",", lastIndex)) != -1)
        {
            String tmp = this.lineRec.substring(lastIndex, index);
            rtnList.add(tmp);
            lastIndex = index + 1;
        }
        
        rtnList.add(this.lineRec.substring(lastIndex));
        
        return rtnList;
    }
    
    @Override
    public String toString()
    {
        return "entryId == " + entryId + "\n" + "lineRec == " + lineRec + "\n";
    }
    
    public static void main(String[] args)
    {
        Record r = new Record("1/1/4/1,,VDDRPDEFVAL.PRF,VUDRPDEFVAL.PRF,VSPDEFVAL.PRF,VDPDEFVAL.PRF,VSMPDEFVAL.PRF,VIDPDEFVAL.PRF,"
                              + "0000000000800000,29506,1412,98,81,0,4,0,1,90,91");
        ArrayList<String> al = r.lineToArrayList();
        for(String s : al)
        {
            System.out.println(s);
        }

    }
}


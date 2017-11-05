package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: ActualTransmissionModeTranslate.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-6-28</p>
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
public class ActualTransmissionModeTranslate implements IFieldDataTranslate
{
    public static final int LENGTH_TRANSLATE = 8;
    
    @Override
    public String translate(String bitsValue)
    {
        if(bitsValue == null || bitsValue.length() != 16)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }

        return getBinary(bitsValue.substring(0, LENGTH_TRANSLATE)) + getBinary(bitsValue.substring(LENGTH_TRANSLATE));
    }
    
    private static String getBinary(String bitsValue)
    {
        long longValue = Long.parseLong(bitsValue, 16);
        String binaryStr = Long.toBinaryString(longValue);

        int addCount = LENGTH_TRANSLATE * 4 - binaryStr.length();
        if(addCount > 0)
        {
            StringBuffer buf = new StringBuffer();
            for(int i = 0; i < addCount; i++)
            {
                buf.append("0");
            }
            buf.append(binaryStr);
            return buf.toString();
        }
        return binaryStr;
    }
    
    
    
//    public static void main(String[] args)
//    {
//        IFieldDataTranslate t = new ActualTransmissionModeTranslate();
//        String bitsValue = "1000000000000000";
//        Assert.assertEquals("0001000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "0000000000800000";
//        Assert.assertEquals("0000000000000000000000000000000000000000100000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "1000000000000001";
//        Assert.assertEquals("0001000000000000000000000000000000000000000000000000000000000001", t.translate(bitsValue));
//        
//        bitsValue = "8000000000000000";
//        Assert.assertEquals("1000000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "0000000000000000";
//        Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "FFFFFFFFFFFFFFFF";
//        Assert.assertEquals("1111111111111111111111111111111111111111111111111111111111111111", t.translate(bitsValue));
//        System.out.println("OK");
//    }
    

}

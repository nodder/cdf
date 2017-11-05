package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: MinProtectionTranslate.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-8</p>
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
public class MinProtectionTranslate implements IFieldDataTranslate
{
    private static CdfLocalize localize = new CdfLocalize();

    private static final String[][] table = new String[][] {
        {"1", localize.findLocalString("RES_MinInp_NoProtection")},
        {"2", localize.findLocalString("RES_MinInp_HalfSymbol")},
        {"3", localize.findLocalString("RES_MinInp_1Symbol")},
        {"4", localize.findLocalString("RES_MinInp_2Symbols")},
        {"5", localize.findLocalString("RES_MinInp_3Symbols")},
        {"6", localize.findLocalString("RES_MinInp_4Symbols")},
        {"7", localize.findLocalString("RES_MinInp_5Symbols")},
        {"8", localize.findLocalString("RES_MinInp_6Symbols")},
        {"9", localize.findLocalString("RES_MinInp_7Symbols")},
        {"10", localize.findLocalString("RES_MinInp_8Symbols")},
        {"11", localize.findLocalString("RES_MinInp_9Symbols")},
        {"12", localize.findLocalString("RES_MinInp_10Symbols")},
        {"13", localize.findLocalString("RES_MinInp_11Symbols")},
        {"14", localize.findLocalString("RES_MinInp_12Symbols")},
        {"15", localize.findLocalString("RES_MinInp_13Symbols")},
        {"16", localize.findLocalString("RES_MinInp_14Symbols")},
        {"17", localize.findLocalString("RES_MinInp_15Symbols")},
        {"18", localize.findLocalString("RES_MinInp_16Symbols")}
    };
    
    private final static HashMap<String, String> valueMap = initHashMap(); 
    
    private static HashMap<String, String> initHashMap()
    {
        HashMap<String, String> valueMap = new HashMap<String , String>();
        
        for(int i = 0; i < table.length; i++)
        {
            valueMap.put(table[i][0], table[i][1]);
        }
        
        return valueMap;
    }
    
    @Override
    public String translate(String original)
    {
        return valueMap.containsKey(original) ? valueMap.get(original) : CdfConst.REPORT_VALUE_INVALID;
    }
}

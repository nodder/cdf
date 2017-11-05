package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: ASCIITransalte.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-6</p>
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
public class ASCIITransalte implements IFieldDataTranslate
{
    @Override
    public String translate(String bitAscii)
    {
        if(bitAscii == null)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        return new String(toAscII(bitAscii));
    }
    
    private static StringBuffer toAscII(String bitsValue)
    {        
        StringBuffer buf = new StringBuffer();
        
        int index = 0;
        while((index + 1) < bitsValue.length())
        {
            String strValue = bitsValue.substring(index, index + 2);
            int intValue = Integer.parseInt(strValue, 16);
            if(intValue == 0)
            {
                break;
            }
            
            buf.append((char)intValue);
            index += 2;
        }
        
        return buf;
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.simplerule;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: SubtractTranslate</p>
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
class SubtractTranslate implements IFieldDataTranslate
{
    private long subtrahend;

    public SubtractTranslate(long subtrahend)
    {
        this.subtrahend = subtrahend;
    }

    @Override
    public String translate(String original)
    {
        try
        {
            return Long.toString(Long.parseLong(original) - subtrahend);
        }
        catch(NumberFormatException e)
        {
        }
        
        return CdfConst.REPORT_VALUE_INVALID;
    }

}

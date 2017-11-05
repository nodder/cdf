package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: StatusTranslate</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-10-29</p>
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
public class StatusTranslate implements IFieldDataTranslate
{
    private static CdfLocalize localize = new CdfLocalize();
    
    @Override
    public String translate(String value)
    {
        if(value == null)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        if("1".equals(value))
        {
            return localize.findLocalString("Enabled");
        }
        else if("2".equals(value))
        {
            return localize.findLocalString("Disabled");
        }
        
        return value;
    }
}

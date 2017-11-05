package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.TranslateDirector;

/**
 * <p>文件名称: FieldFormatInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-1</p>
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
public class FieldInfo
{
    private final String fieldName;
    private final boolean isFromDb;
    private final String translateFunctionName;//允许为空
    private final String i18n;
    
    private IFieldDataTranslate translateFunc;
    
    public FieldInfo(String i18n, String fieldName, boolean isFromDb, String translateFunctionName)
    {
        this.i18n = i18n;
        this.fieldName = fieldName;
        this.isFromDb = isFromDb;
        this.translateFunctionName = translateFunctionName;
        
        translateFunc = TranslateDirector.getTranslateInstance(translateFunctionName);
    }
    
    public String getI18n()
    {
        return i18n;
    }
    
    public String translate(String oriData)
    {
        return translateFunc.translate(oriData);
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    public boolean isFromDB()
    {
        return isFromDb;
    }
    public String getTranslate()
    {
        return translateFunctionName;
    }

    @Override
    public String toString()
    {
        return "FieldInfo [fieldName=" + fieldName + ", isFromDb=" + isFromDb + ", translateFunctionName=" + translateFunctionName + ", i18n="
               + i18n + ", translateFunc=" + translateFunc + "]";
    }
}

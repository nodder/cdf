package com.zte.ums.an.uni.dsl.conf.cdf.report.report;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.FieldInfo;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: LineDataGenerator</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-2</p>
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
public class LineDataGenerator
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private static CdfLocalize localize = new CdfLocalize();
    
    private FieldInfo[] fields;
    private ResultSet rs;
    
    LineDataGenerator(final FieldInfo[] fields, ResultSet rs) throws SQLException
    {
        this.fields = fields;
        this.rs = rs;
    }
    
    public String getFieldI18n()
    {
        if(fields.length == 0)
        {
            return "";
        }
        
        StringBuffer strBuf = new StringBuffer();
        for(int i = 0; i < fields.length; i++)
        {
            strBuf.append(localize.findLocalString(fields[i].getI18n())).append(",");
        }
        
        return strBuf.substring(0, strBuf.length() - 1);
    }
    
    public boolean next() throws SQLException
    {
        return rs.next();
    }

    public String getLineData()
    {
        if(fields.length == 0)
        {
            return "";
        }
        
        int i = 0;
        try
        {
            StringBuffer strBuf = new StringBuffer();
            for(i = 0; i < fields.length; i++)
            {
                strBuf.append(getTranslatedFieldValue(i)).append(",");
            }
            
            return strBuf.substring(0, strBuf.length() - 1);
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
            System.out.println(fields[i]);
        }
        
        return CdfConst.REPORT_PROGRAM_ERROR;
    }

    private String getTranslatedFieldValue(int index) throws SQLException
    {
        FieldInfo field = fields[index];
        
        String fieldValue = null;
        if(field.isFromDB())
        {
            fieldValue = rs.getString(field.getFieldName());
        }
        
        return field.translate(fieldValue);
    }
}

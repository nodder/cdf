package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>文件名称: DbTypeInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-13</p>
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
public class SubCollectDbTypeDataModel implements ICdfConfigDataModel
{
    public String getTitle()
    {
        return "CDF Database Type(1-mssql, 2-oracle)";
    }
            
    public String getCurrValue()
    {
        String currValue = "";
        
        String dbType = CenterToolUtil.getCollectXml().getDbType();
        
        if("mssql".equalsIgnoreCase(dbType))
        {
            currValue = "1";
        }
        else if("oracle".equalsIgnoreCase(dbType))
        {
            currValue = "2";
        }
        else
        {
            currValue = "--";
        }
        
        return currValue;
    }
    
    public boolean setNewValue(String newValue)
    {
        String dbType;
        if("1".equalsIgnoreCase(newValue))
        {
            dbType = "mssql";
        }
        else if("2".equalsIgnoreCase(newValue))
        {
            dbType = "oracle";
        }
        else
        {
            return false;
        }
        
        CenterToolUtil.getCollectXml().setDbType(dbType);
        
        return true;
    }
}
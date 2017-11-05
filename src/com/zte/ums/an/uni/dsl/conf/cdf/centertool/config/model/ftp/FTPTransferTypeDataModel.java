package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>文件名称: FTPTransferTypeDataModel</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2005-2015</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-12-12</p>
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
public class FTPTransferTypeDataModel implements ICdfConfigDataModel
{
    public String getTitle()
    {
        return "CDF Transfer Protocal Type(1-FTP, 2-SFTP)";
    }
            
    public String getCurrValue()
    {
        String currValue = "";
        
        String transferType = CenterToolUtil.getCollectXml().getTransferType();
        
        if("FTP".equalsIgnoreCase(transferType))
        {
            currValue = "1";
        }
        else if("SFTP".equalsIgnoreCase(transferType))
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
        String transferType;
        if("1".equalsIgnoreCase(newValue))
        {
            transferType = "FTP";
        }
        else if("2".equalsIgnoreCase(newValue))
        {
            transferType = "SFTP";
        }
        else
        {
            return false;
        }
        
        CenterToolUtil.getCollectXml().setTransferType(transferType);
        
        return true;
    }
}
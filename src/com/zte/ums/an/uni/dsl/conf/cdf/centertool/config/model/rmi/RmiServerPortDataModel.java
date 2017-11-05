package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.rmi;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>文件名称: DbPortInfo.java</p>
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
public class RmiServerPortDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "RMI Server Port";
    }
        
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getDispatchXml().getServerPort();
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        try
        {
            int portNo = Integer.parseInt(newValue);
            if(portNo <= 0 || portNo >= 65536)
            {
                System.out.println("Invalid port number.");
                return false;
            }
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Invalid port number.");
            return false;
        }
        
        CenterToolUtil.getDispatchXml().setServerPort(newValue);
        CenterToolUtil.getReportXml().setServerPort(newValue);
        CenterToolUtil.getCollectXml().setRemoteServerPort(newValue);
        
        return true;
    }
}
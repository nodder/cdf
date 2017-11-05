package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

/**
 * <p>文件名称: RmiInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-12</p>
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
public class RmiInfo
{
    public String port;
    public String ipAddr = "";
    public String remoteObject = "";

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ipAddr == null) ? 0 : ipAddr.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
        result = prime * result + ((remoteObject == null) ? 0 : remoteObject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(!(obj instanceof RmiInfo))
        {
            return false;
        }
        
        RmiInfo other = (RmiInfo)obj;
        
        if((ipAddr.equals(other.ipAddr)) &&(remoteObject.equals(other.remoteObject)) && (port.equalsIgnoreCase(other.port)))
        {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString()
    {
        return "port=" + port + ", ipAddr=" + ipAddr + ", remoteObject=" + remoteObject;
    }
}

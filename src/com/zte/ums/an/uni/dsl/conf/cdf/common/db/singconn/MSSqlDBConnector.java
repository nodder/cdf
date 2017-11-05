package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

/**
 * <p>文件名称: MSSqlDBConnector</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月25日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */

public class MSSqlDBConnector extends SingDBConnector
{    
    private String dbName;
    
    MSSqlDBConnector(String dbtype, String serverIp, String port, String user, String password, String dbName)
    {
        super(dbtype, serverIp, port, user, password);

        this.dbName = dbName;
        
        this.drivertitle = "jdbc:jtds:sqlserver";
        this.drivername = "net.sourceforge.jtds.jdbc.Driver";
    }
    
    @Override
    String getURL()
    {        
        if(dbName == null || dbName.equalsIgnoreCase(""))
        {
            return drivertitle + "://" + serverIp + ":" + port;
        }
        else
        {
            return drivertitle + "://" + serverIp + ":" + port + "/" + dbName;
        }
    }
}

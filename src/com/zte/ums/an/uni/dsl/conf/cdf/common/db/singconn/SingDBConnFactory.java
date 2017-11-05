package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

import java.sql.Connection;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;

/**
 * <p>文件名称: SingDBConnFactory</p>
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

public class SingDBConnFactory
{
    private SingDBConnFactory()
    {
    }

    private static SingDBConnector getDBConnector(String dbtype, String serverIp, String port, String user, String password, String dbName)
                    throws Exception
    {
        if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
        {
            String oracleSid = dbName;
            return new OracleDBConnector(dbtype, serverIp, port, user, password, oracleSid);
        }
        else if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_MSSQL))
        {
            return new MSSqlDBConnector(dbtype, serverIp, port, user, password, dbName);
        }

        throw new Exception("Invalid DB Type: " + dbtype);
    }
    
    public static Connection getDBConnect(String dbtype, String serverIp, String port, String user, String password, String dbName) throws Exception
    {
        SingDBConnector connector = getDBConnector(dbtype, serverIp, port, user, password, dbName);
        return connector.getDBConnection();
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ConnectionFactory</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月21日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */
public class CdfDbConnectionUtil
{
    private static Logger logger = ZXLogger.getLogger(CdfDbConnectionUtil.class.getName());
    
    private CdfDbConnectionUtil()
    {
    }
    
    public static Connection getDBConnection()
    {
        try
        {
            String dbtype = CollectXmlParser.getInstance().getDbType();
            String serverIp = CollectXmlParser.getInstance().getDbServerIp();
            String port = CollectXmlParser.getInstance().getDbPort();
            String user = CollectXmlParser.getInstance().getDbSuperUser();
            String password = CollectXmlParser.getInstance().getDbSuperUserPassword();
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = CollectXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }

        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection", ex);
            return null;
        }
    }
    
    public static Connection getDBConnection4Cdf()
    {
        try
        {
            String dbtype = CollectXmlParser.getInstance().getDbType();
            String serverIp = CollectXmlParser.getInstance().getDbServerIp();
            String port = CollectXmlParser.getInstance().getDbPort();
            String user = "cdf";
            String password = CdfConst.DB_PASSWORD_FOR_CDF;
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = CollectXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4Cdf", ex);
            return null;
        }
    }

    public static Connection getDBConnection4EmsAN()
    {
        try
        {
            String dbtype = DispatchXmlParser.getInstance().getDbType();
            String serverIp = DispatchXmlParser.getInstance().getDbServerIp();
            String port = DispatchXmlParser.getInstance().getDbPort();
            String user = "an";
            String password = "U_tywg_2008";
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = DispatchXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4EmsAN", ex);
            return null;
        }
    }
   
    public static Connection getDBConnection4EmsUep4x()
    {
        try
        {
            String dbtype = DispatchXmlParser.getInstance().getDbType();
            String serverIp = DispatchXmlParser.getInstance().getDbServerIp();
            String port = DispatchXmlParser.getInstance().getDbPort();
            String user = "uep4x";
            String password = "U_tywg_2008";
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = DispatchXmlParser.getInstance().getDbName();
            }

            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4EmsUep4x", ex);
            return null;
        }
    }
}

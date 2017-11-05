package com.zte.ums.an.uni.dsl.conf.cdf.report.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.SingDBConnFactory;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportDataXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: DbOperGatewayForCdfReport</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-2-6</p>
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
public class DbOperGateway
{
    private static Logger logger = ZXLogger.getLogger(DbOperGateway.class.getName());
    private ArrayList<SubCollectInfo> subCollectSvrList = null;
    private ArrayList<Connection> conns = new ArrayList<Connection>();
    private ResultSetGateway rs;
    List<PreparedStatement> psList = new ArrayList<PreparedStatement>();
    List<ResultSet> rsList = new ArrayList<ResultSet>();
    private String tableName = null;
    
    public DbOperGateway(ArrayList<SubCollectInfo> subCollectSvrList)
    {
        this.subCollectSvrList = subCollectSvrList;
        
        if(subCollectSvrList != null)
        {
            for(SubCollectInfo svr : subCollectSvrList)
            {
                Connection conn = getSubCollectSvrDBConn(svr);
                if(conn != null)
                {
                    conns.add(conn);
                }
            }
        }
    }

    public ResultSet startQuery(String tableName)
    {
        this.tableName  = tableName;

        for(int i = 0; i < conns.size(); i++)
        {
            Connection conn = conns.get(i);
            SubCollectInfo subCollectInfo = subCollectSvrList.get(i);
            
            try
            {
                String strSQL = getQuerySQL(tableName, subCollectInfo);
                PreparedStatement ps4Select = conn.prepareStatement(strSQL);
                
                psList.add(ps4Select);
                rsList.add(ps4Select.executeQuery());
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "", e);
            }
        }

        this.rs = new ResultSetGateway(rsList);
        return rs;
    }

    private String getQuerySQL(String tableName, SubCollectInfo subCollectInfo)
    {
        String sql = "select * from " + tableName + " where insertTime<=" + subCollectInfo.nanoTime;
        
        String[] arrOrderBy = ReportDataXmlParser.getInstance().getOrderBy(tableName);
        if(arrOrderBy.length != 0)
        {
            sql = sql + " ORDER BY " + getOrderBy(arrOrderBy);
        }
        
        LogPrint.logDebug(logger, sql);
        return sql;
    }

    private String getOrderBy(String[] arrOrderBy)
    {
        StringBuffer buf = new StringBuffer();
        for(String s : arrOrderBy)
        {
            buf.append(s).append(",");
        }
        
        return buf.substring(0, buf.length() - 1);
    }
    
    /**
     * 判断collect Svr是否存在记录。该方法必须在startQuery返回的rs没有被next方法使用时才能返回有效数据。
     * @param subCollectInfo
     * @return
     */
    public boolean isCurrQueryHasRecords(SubCollectInfo subCollectInfo)
    {
        int index = this.subCollectSvrList.indexOf(subCollectInfo);
        
        if(index < 0)
        {
            return false;
        }
            
        return CdfDbUtil.hasRecords(this.rsList.get(index));
    }
    
    /**
     * 将运行startQuery方法所查询到的记录从数据库表中删除。如果多次运行startQuery后调用此方法，则仅对最后一次有效。
     * 如果调用此方法前，先调用了closeQuery，则返回false
     * 因此原则上，deleteQueriedRecords的调用需要和startQuery一一对应。
     * @return
     */
    public void deleteQueriedRecords()
    {
        try
        {
            if(tableName != null)
            {
                long[] latestDate = this.rs.getLatestDate();
                for(int i = 0; i < conns.size(); i++)
                {
                    String strSQL = "delete from " + tableName + " where insertTime<=" + latestDate[i];
                    PreparedStatement ps = conns.get(i).prepareStatement(strSQL);
                    
                    ps.execute();
                    ps.close();
                }
            }
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
    
    /**
     * 关闭startQuery方法产生的ResultSet。原则上，closeQuery的调用需要和startQuery一一对应。
     */
    public void closeQuery()
    {
        this.tableName = null;
        
        if(this.rs != null)
        {
            rs.close();
            rs = null;
        }
        this.rsList = new ArrayList<ResultSet>();
        closeStatements();
    }
        
    private static Connection getSubCollectSvrDBConn(SubCollectInfo subCollectInfo)
    {
        Connection conn = null;

        try
        {
            String dbtype = subCollectInfo.dbType;
            String serverIp = subCollectInfo.dbServerIp;
            String port = subCollectInfo.dbPort;
            
            String user = "cdf";
            String password = CdfConst.DB_PASSWORD_FOR_CDF;
            
            String dbName = null;
            
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = subCollectInfo.dbName;
            }
            
            conn = SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
        
        return conn;
    }

    public void closeAll()
    {
        if(this.rs != null)
        {
            this.rs.close();
        }
        
        closeStatements();
        closeConnections();
    }

    private void closeConnections()
    {
        try
        {
            for(Connection conn : conns)
            {
                if(conn != null)
                {
                    conn.close();
                }
            }
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
    

    private void closeStatements()
    {
        try
        {
            for(Statement st : this.psList)
            {
                if(st != null)
                {
                    st.close();
                }
            }
            
            psList = new ArrayList<PreparedStatement>();
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
}

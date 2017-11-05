package com.zte.ums.an.uni.dsl.conf.cdf.common.db.connpool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ConnectionPool.java</p>
 * <p>文件描述: 数据库连接池</p>
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
public class ConnectionPool
{
    //****** 代码段: 变量定义 **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    /** 连接池，存放已创建但未使用的连接 */
    private Stack pool = new Stack();

    /** 已被取走、正在使用的连接 */
    private Map using = new HashMap();

    /** 已经创建连接池计数 */
    private int created = 0;

    /** 连接池最大数 */
    private int max = 100;

    /** 各种同步锁对象，byte[]对象创建时间最短，占资料最少 */
    private final byte[] createdLock = new byte[0];
    private final byte[] usingLock = new byte[0];
    private final byte[] poolLock = new byte[0];
    private final byte[] waitNofityLock = new byte[0];

    /** 单实例 */
    private static ConnectionPool instance = new ConnectionPool();

    //****** 代码段: 构造方法 **********************************************************************/

    /**
     * 私有的构造方法，防止从外部直接实例化
     */
    private ConnectionPool()
    {
        max = Integer.parseInt(CollectXmlParser.getInstance().getDBMaxConnect());
    }

    //****** 代码段: 公共方法 **********************************************************************/

    public static ConnectionPool getInstance()
    {
        return instance;
    }

    /**
     * 从连接池中取得一个空闲数据库连接；或者如果池中没有空闲连接且连接数没有超过最大值，则创建新连接；
     * 否则一直重试直到获取成功；
     * 注意，使用完毕后，需要调用freeConnection将其释放。
     * @return
     */
    public Connection getConnection(String threadName)
    {
        Connection conn = null;
        do
        {
            synchronized(poolLock)
            {
                if(!pool.isEmpty())
                {
                    conn = (Connection)pool.pop();
                }
                else
                {
                    conn = createNewConn();
                }
            }

            if(conn == null)
            {
                waitUntilNotify();
            }
        }
        while(conn == null);

        synchronized(usingLock)
        {
            using.put(conn, threadName);
        }

        return conn;
    }

    /**
     * 返回一个数据库连接到池中，再由线程返回连接池
     * @return void
     */
    public void freeConnection(Connection conn)
    {
        if(conn == null)
        {
            return;
        }

        try
        {
            synchronized(usingLock)
            {
                using.remove(conn);
            }
            synchronized(poolLock)
            {
                pool.add(conn);
            }
            synchronized(waitNofityLock)
            {
                waitNofityLock.notify();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    /**
     * 释放所有连接,创建新池
     * return void
     */
    public void releasePool()
    {
        instance = new ConnectionPool();

        /* 释放所有连接，除了Using，Using是正在使用的且会在新的池里被释放 */
        int poolSize = -1;
        synchronized(poolLock)
        {
            poolSize = pool.size();
            ;
            while(!pool.isEmpty())
            {
                Connection conn = (Connection)pool.pop();
                CdfDbUtil.closeDB(conn);
            }
        }
        synchronized(createdLock)
        {
            created -= poolSize;
        }
        
        LogPrint.logInfo(logger, "Success in releasing DB pool. Now in DB pool, created == " + created + ". pool == " + pool.size() + ". using == "
            + using.size() + ".");
    }

    //****** 代码段: 私有方法 **********************************************************************/

    private void waitUntilNotify()
    {
        synchronized(waitNofityLock)
        {
            try
            {
                waitNofityLock.wait();
            }
            catch(InterruptedException e)
            {
                LogPrint.logError(logger, "", e);
            }
        }
    }

    private Connection createNewConn()
    {
        try
        {
            synchronized(createdLock)
            {
                if(created < max)
                {
                    Connection conn = CdfDbConnectionUtil.getDBConnection4Cdf();

                    if(conn != null)
                    {
                        created++;
                    }
                    else
                    {
                        LogPrint.logError(logger, "Failed to get db connection, please check the parameters.");
                    }
                    
                    return conn;
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }

        return null;
    }
}

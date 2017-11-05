package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.sql.Connection;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;

/**
 * <p>文件名称: DbConnOfSubcollectCheck.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2013</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-7-11</p>
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
public class DbConnOfSubcollectCheck implements ICdfCheck
{
    @Override
    public String getName()
    {
        return "sub-collect server database configuration";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start sub-collect server database configuration test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("Sub-collect server database configuration test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        try
        {
            testConn();
            return new CdfTestResult(true);

        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }
    
    private void testConn() throws Exception
    {
        CenterToolUtil.printCheckTile("Connecting to sub-collect server Database");
        Connection conn = getConn();
        closeConn(conn);
        CenterToolUtil.printSucess();
    }
    
    private Connection getConn() throws Exception
    {
        CenterToolUtil.changeToSubCollectDir();
        Connection conn = CdfDbConnectionUtil.getDBConnection();
        if(conn == null)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_SUBCOLLECT_FAIL);
        }
        
        return conn;
    }
    
    private void closeConn(Connection conn) throws Exception
    {
        try
        {
            conn.close();
        }
        catch(Exception e)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_CLOSE_FAIL);
        }
    }
}

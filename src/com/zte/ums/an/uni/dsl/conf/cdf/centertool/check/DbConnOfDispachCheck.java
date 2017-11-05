package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>文件名称: DbConnCheck.java</p>
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
public class DbConnOfDispachCheck implements ICdfCheck
{
    @Override
    public String getName()
    {
        return "dispatch database configuration";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start dispatch database configuration test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("Dispatch database configuration test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        try
        {
            testConn4EmsAn();
            testConn4UEP4x();
            testQueryNeListToCollect();
            
            return new CdfTestResult(true);

        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }

    private void testQueryNeListToCollect() throws Exception
    {
        CenterToolUtil.printCheckTile("Check NE list for collecting");
        
        ArrayList<SnmpNode> neListToCollect = CdfDbUtil.getSnmpNodesListWithCP();
        if(neListToCollect == null)
        {
            throw new Exception(CdfTestResultConst.DB_QUERY_COLLECTNELIST_FAIL);
        }
        
        System.out.println("OK (Total Number:" + neListToCollect.size() + ")");
        showAdditionInfo(neListToCollect);
    }

    private static void showAdditionInfo(ArrayList<SnmpNode> neListToCollect)
    {
        if(neListToCollect.size() > 0)
        {
            final int threathod = 100;
            if(neListToCollect.size() <= threathod)
            {
                System.out.println("Detailed NE IP Address:" + getIPList(neListToCollect));
            }
        }
        
        System.out.println("Note: The collecting NE list is added or deleted in the EMS.");
    }
    
    private static String getIPList(ArrayList<SnmpNode> neListToCollect)
    {
        String[] ipList = new String[neListToCollect.size()];
        for(int i = 0; i < neListToCollect.size(); i++)
        {
            ipList[i] = neListToCollect.get(i).getIpAddress();
        }
        
        return Arrays.toString(ipList);
    }
    
    private void testConn4EmsAn() throws Exception
    {
        CenterToolUtil.printCheckTile("Connecting to EMS AN Database");
        Connection conn = getConn4EmsAn();
        closeConn(conn);
        CenterToolUtil.printSucess();
    }
    
    private void testConn4UEP4x() throws Exception
    {
        CenterToolUtil.printCheckTile("Connecting to EMS UEP4X Database");
        Connection conn = getConn4EmsUEP4x();
        closeConn(conn);
        CenterToolUtil.printSucess();
    }
    
    private Connection getConn4EmsAn() throws Exception
    {
        CenterToolUtil.changeToDispatchDir();
        Connection conn = CdfDbConnectionUtil.getDBConnection4EmsAN();
        if(conn == null)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_EMSAN_FAIL);
        }
        
        return conn;
    }
    
    private Connection getConn4EmsUEP4x() throws Exception
    {
        CenterToolUtil.changeToDispatchDir();
        Connection conn = CdfDbConnectionUtil.getDBConnection4EmsUep4x();
        if(conn == null)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_EMSUEP4X_FAIL);
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
    
    public static void main(String[] args)
    {
        ArrayList<SnmpNode> list = new ArrayList<SnmpNode>();
        
        SnmpNode ne1 = new SnmpNode();
        ne1.setIpAddress("1.1.1.1");
        
        SnmpNode ne2 = new SnmpNode();
        ne2.setIpAddress("1.1.1.2");
        
        list.add(ne1);
        list.add(ne2);
        
        showAdditionInfo(list);
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

/**
 * <p>文件名称: OracleDBConnector</p>
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

public class OracleDBConnector extends SingDBConnector
{    
    private String oracleSid;
    
    OracleDBConnector(String dbtype, String serverIp, String port, String user, String password, String oracleSid)
    {
        super(dbtype, serverIp, port, user, password);
        
        this.oracleSid = oracleSid;
        
        this.drivername = "oracle.jdbc.driver.OracleDriver";
        this.drivertitle = "jdbc:oracle:thin";
    }
    
    @Override
    String getURL()
    {
        return drivertitle + ":@" + serverIp + ":" + port + ":" + oracleSid;
    }

}

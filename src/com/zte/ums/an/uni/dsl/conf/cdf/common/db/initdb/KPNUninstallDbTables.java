package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.File;
import java.sql.Connection;
import java.util.Properties;

import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;

/**
 * <p>文件名称: KPNUninstallDbTables</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2013</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012年7月17日</p>
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

public class KPNUninstallDbTables extends InitDbTemplate
{
    @Override
    public boolean doInitDB()
    {
       if(initWholeDatabase())
       {
           return true;
       }
       
       return false;
    }
    
    @Override
    String getOperName()
    {
        return "Uninstalling CDF database";
    }
    
    @Override
    Connection getDBConnection4WholeDatabase()
    {
        return CdfDbConnectionUtil.getDBConnection();
    }
    
    @Override
    String getSqlFile4InitWholeDatabase()
    {
        String userdir = System.getProperty("user.dir");
        String dbtype = CollectXmlParser.getInstance().getDbType();
        
        String sqlFile = null;
        if(dbtype.equalsIgnoreCase("oracle"))
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_destroydb_oracle.sql";
        }
        else
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_destroydb_mssql.sql";
        }

        return sqlFile;
    }
    
    @Override
    @Deprecated
    boolean isNeedInitWholeDatabase()
    {
        return false;
    }
    
    @Override
    @Deprecated
    Connection getDBConnection4Table()
    {
        return null;
    }
    
    @Override
    @Deprecated
    String getSqlFile4InitTable()
    {
        return null;
    }
    
    private static void customUserDir()
    {
        String userdir = System.getProperty("user.dir");
        String newUserDir = (new File(userdir)).getParent();
        
        Properties p = System.getProperties();
        p.setProperty("user.dir", newUserDir);
    }
    
    public static void main(String[] args)
    {
        customUserDir();
        
        if(args != null && args.length == 1 && args[0].equalsIgnoreCase("-quick"))
        {
            if((new KPNUninstallDbTables()).doInitDB())
            {
                System.out.println("Uninstalling CDF database is complete successfully.");
            }
        }
        else
        {
            (new KPNUninstallDbTables()).mainProcess();
        }
    }
}

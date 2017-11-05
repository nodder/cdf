package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.DataProcess;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea.FixedFieldCenter;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea.IFixedAreaGetter;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.connpool.ConnectionPool;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: DbOperForCdfParser</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-5</p>
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
public class DbOperForCdfParser
{
    private SnmpNode snmpNode = null;
    private BulkPoolGroupInfo group;
    private IFixedAreaGetter[] fieldValueGetters = null;
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    public DbOperForCdfParser(BulkPoolGroupInfo group, SnmpNode snmpNode) throws SecurityException, NoSuchMethodException
    {
        this.group = group;
        this.snmpNode = snmpNode;
        
        String[] fieldMethodNames = CollectDataXmlParser.getInstance().getGroupToFixedArea().get(group.getGroupName());
        fieldValueGetters = new IFixedAreaGetter[fieldMethodNames.length];
        for(int i = 0; i < fieldMethodNames.length; i++)
        {
            fieldValueGetters[i] = FixedFieldCenter.getFixedAreaGetter(fieldMethodNames[i]);
        }
    }
    
    public boolean dbOperForInsertRecords(DataProcess dataProcess)
    {
        String sql = null;
        PreparedStatement ps4Insert = null;
        Connection conn = null;

        try
        {
            ArrayList<String> firstLine = dataProcess.readNextLineParams();
            if(firstLine != null)
            {
                sql = assembleSql4Insert(group.getGroupName(), firstLine);

                conn = ConnectionPool.getInstance().getConnection("");
                ps4Insert = conn.prepareStatement(sql);

                conn.setAutoCommit(false);
                addBatchRecord(ps4Insert, firstLine, group);

                ArrayList<String> line = null;
                while((line = dataProcess.readNextLineParams()) != null)
                {
                    addBatchRecord(ps4Insert, line, group);
                }
                ps4Insert.executeBatch();
                conn.commit();
            }
            return true;
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
            LogPrint.logError(logger, sql);
            return false;
        }
        finally
        {
            CdfDbUtil.closeDB(ps4Insert);
            ConnectionPool.getInstance().freeConnection(conn);
        }
    }

    public String assembleSql4Insert(String dbTableName,ArrayList<String> line)
    {
        int questionMarkNumber = 2 + fieldValueGetters.length + line.size();
        StringBuffer sql = new StringBuffer();
                
        sql.append("insert into ").append(dbTableName).append(" values(");
        for(int i = 0; i < questionMarkNumber; i++)
        {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        return sql.toString();
    }

    public void addBatchRecord(PreparedStatement ps4Insert,  ArrayList<String> line, BulkPoolGroupInfo group) throws SQLException
    {
        int i = 1;
        
        //第一個字段固定為採集時間
        ps4Insert.setTimestamp(i++, group.getTime());
        
        //添加fixedArea各字段
        for(IFixedAreaGetter field : fieldValueGetters)
        {
            ps4Insert.setString(i++, field.getFieldValue(snmpNode));
        }

        //添加merge和 append各字段
        for(int j = 0; j < line.size(); j++)
        {
            ps4Insert.setString(i++, line.get(j));
        }
        
        //最后一个字段固定為插入时间
        ps4Insert.setLong(i++, System.nanoTime());

        ps4Insert.addBatch();
    }
}

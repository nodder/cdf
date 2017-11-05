package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>文件名称: ReportInfo.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-1</p>
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
public class ReportInfo
{
    private final String name;
    private final String scheduleCron;
    private final String[] dbTables;
    
    private boolean isRunning = false;
    
    private HashMap<String, FieldInfo[]> tableToFields;//不可能为空 //TODO
    
    public ReportInfo(String name, String scheduleCron, String[] dbTables)
    {
        this.name = name;
        this.scheduleCron = scheduleCron;
        this.dbTables = dbTables;
        this.tableToFields = new HashMap<String, FieldInfo[]>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getScheduleCron()
    {
        return scheduleCron;
    }
    
    public String[] getDbTables()
    {
        return dbTables;
    }
    
    public FieldInfo[] getFields(String belongedTable)
    {
        return this.tableToFields.get(belongedTable);
    }

    public void addFieldsForSingleDBTable(FieldInfo[] fields, String belongedTable)
    {
        this.tableToFields.put(belongedTable, fields);
    }
    
    public boolean isRunning()
    {
        return isRunning;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("  name           = " + this.name + "\n");
        sb.append("  scheduleCron   = " + this.scheduleCron + "\n");
        sb.append("  dbTables       = " + Arrays.toString(dbTables) + "\n");
        sb.append("  isRunning      = " + this.isRunning + "\n");
        sb.append(toStringForTable());
  
        return sb.toString();
    }
    
    private String toStringForTable()
    {
        StringBuffer sb = new StringBuffer("   tables     =[\n");
        for(String table : dbTables)
        {
            sb.append("  table = " + table + ", fields = " + Arrays.toString(tableToFields.get(table)) + "\n");
        }
        
        sb.delete(sb.length() - 1, sb.length()).append("]");
        
        return sb.toString();
    }
}

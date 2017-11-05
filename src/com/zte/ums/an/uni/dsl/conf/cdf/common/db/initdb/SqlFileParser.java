package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>文件名称: SqlFileParser.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2010年7月6日</p>
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
public class SqlFileParser
{
    private Properties p = null;
    
    private static final String START_PATTEN = "%$";
    private static final String END_PATTEN = "$%";

    private String filename;
    private BufferedReader reader;
    
    private String[] allStatements;
    private int currStatementIndex = 0;
    
    public SqlFileParser(String filename, Properties p) throws Exception
    {
        this.filename = filename;
        this.p = p;
        if(null == this.p)
        {
            this.p = new Properties();
        }
        
        openFile();
        
        try
        {
            parseFile();
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage() + " of File:" + filename);
        }
    }
    
    public String nextStatement()
    {
        if(currStatementIndex == allStatements.length)
        {
            return null;
        }
        
        return allStatements[currStatementIndex++];
    }

    private void parseFile() throws Exception
    {
        ArrayList<String> statements = new ArrayList<String>();
        String statement;
        while((statement = getNextStatement()) != null)
        {
            statements.add(statement);
        }
        
        allStatements = statements.toArray(new String[0]);
    }

    private String getNextStatement() throws Exception
    {
        StringBuffer sqlStatement = new StringBuffer();
        String line;

        while((line = this.reader.readLine()) != null)
        {
            line = parseSqlLine(line);

            if(line.endsWith("/"))
            {
                return sqlStatement.toString().trim();
            }
            sqlStatement.append(line + " ");
        }
        String statement = sqlStatement.toString();
        if(statement == null || statement.trim().equals(""))
        {
            return null;
        }
        else
        {
            throw new Exception("Error - unexpected EOF");
        }
    }
    
    
    private String parseSqlLine(String sqlLine) throws Exception
    {
        sqlLine = sqlLine.trim();
        int indexPattenStart = sqlLine.indexOf(START_PATTEN);
        int indexPattenEnd = sqlLine.indexOf(END_PATTEN);
        
        if(indexPattenStart == -1 && indexPattenEnd == -1)
        {
            return sqlLine;
        }
        else if(indexPattenStart != -1 && indexPattenEnd > indexPattenStart)
        {
            String toBeReplaced = sqlLine.substring(indexPattenStart + START_PATTEN.length(), indexPattenEnd);

            return sqlLine.substring(0, indexPattenStart) + getReplacedStr(toBeReplaced) + sqlLine.substring(indexPattenEnd + END_PATTEN.length());
        }
        else
        {
            throw new Exception("Invalid %$ or $% in line \"" + sqlLine + "\"");
        }
    }

    private String getReplacedStr(String toBeReplaced) throws Exception
    {
        if (p.containsKey(toBeReplaced))
        {
            return (String)p.get(toBeReplaced);
        }
        
        throw new Exception("Cannot find parameter " + START_PATTEN + toBeReplaced + END_PATTEN);
    }

    //****** 代码段: 私有方法 **********************************************************************/

    private void openFile() throws FileNotFoundException
    {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    }

}

package com.zte.ums.an.uni.dsl.conf.cdf.report.report;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ResultSetGateway</p>
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
public class ResultSetGateway implements ResultSet
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private List<ResultSet> rsList;
    private int index;
    
    /** Select语句所查询到的记录中的最迟的时间点，早于该时间点的记录都将被删除 */
    private long[] latestDate;
    
    private int[] columnNum;
    

    public ResultSetGateway(List<ResultSet> rsList)
    {
        this.rsList = rsList;
        this.index = 0;
        
        if(rsList == null || rsList.size() == 0)
        {
            throw new IllegalArgumentException("size of stList or rsList must larger than 0");
        }
        
        this.latestDate = new long[rsList.size()];
        
        this.columnNum = new int[rsList.size()];
        try
        {
            for(int i = 0; i < rsList.size(); i++)
            {
                columnNum[i] = rsList.get(i).getMetaData().getColumnCount();
            }
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
    
    public long[] getLatestDate()
    {
        return this.latestDate;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;
    }

    @Override
    public boolean next() throws SQLException
    {
        while(index < this.rsList.size())
        {
            if(getCurrResultSet().next())
            {
                updateLatestData();
                return true;
            }
            
            index++;
        }
        
        return false;
    }
    
    private void updateLatestData() throws SQLException
    {
        int insertTimeIndex = this.columnNum[index];
        long insertTime = getCurrResultSet().getLong(insertTimeIndex);
        
        latestDate[index] = Math.max(latestDate[index], insertTime);
    }

    private ResultSet getCurrResultSet()
    {
        return this.rsList.get(index);
    }

    @Override
    public void close()
    {
        this.latestDate = null;
        this.columnNum = null;
        
        closeResultSet();
        this.rsList = null;
    }

    private void closeResultSet()
    {
        try
        {
            for(ResultSet rs : this.rsList)
            {
                if(rs != null)
                {
                    rs.close();
                }
            }
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }

    @Override
    public boolean wasNull() throws SQLException
    {
        return false;
    }

    @Override
    public String getString(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getString(columnIndex);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getBoolean(columnIndex);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getByte(columnIndex);
    }

    @Override
    public short getShort(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getShort(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getLong(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getFloat(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getLong(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException
    {
        return getCurrResultSet().getBigDecimal(columnIndex);
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getBytes(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getDate(columnIndex);
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getTime(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getTimestamp(columnIndex);
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getAsciiStream(columnIndex);
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getUnicodeStream(columnIndex);
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getBinaryStream(columnIndex);
    }

    @Override
    public String getString(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getString(columnLabel);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getBoolean(columnLabel);
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getByte(columnLabel);
    }

    @Override
    public short getShort(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getShort(columnLabel);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getInt(columnLabel);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getLong(columnLabel);
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getFloat(columnLabel);
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getDouble(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException
    {
        return getCurrResultSet().getBigDecimal(columnLabel, scale);
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getBytes(columnLabel);
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getDate(columnLabel);
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getTime(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getTimestamp(columnLabel);
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getAsciiStream(columnLabel);
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getUnicodeStream(columnLabel);
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getBinaryStream(columnLabel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getCursorName() throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException
    {
        return getCurrResultSet().getMetaData();
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getObject(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getObject(columnLabel);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException
    {
        return getCurrResultSet().findColumn(columnLabel);
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getCharacterStream(columnIndex);
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getCharacterStream(columnLabel);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException
    {
        return getCurrResultSet().getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException
    {
        return getCurrResultSet().getBigDecimal(columnLabel);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAfterLast() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isFirst() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLast() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void beforeFirst() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterLast() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean first() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean last() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getRow() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean absolute(int row) throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean relative(int rows) throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean previous() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getFetchDirection() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFetchSize(int rows) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getFetchSize() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getType() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getConcurrency() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean rowUpdated() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean rowInserted() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean rowDeleted() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertRow() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateRow() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteRow() throws SQLException
    {
        getCurrResultSet().deleteRow();
    }

    @Override
    public void refreshRow() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void cancelRowUpdates() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveToInsertRow() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveToCurrentRow() throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Statement getStatement() throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getHoldability() throws SQLException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isClosed() throws SQLException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getNString(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNString(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException
    {
        // TODO Auto-generated method stub
        
    }

}

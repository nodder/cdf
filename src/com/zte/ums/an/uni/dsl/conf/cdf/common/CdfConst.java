package com.zte.ums.an.uni.dsl.conf.cdf.common;

/**
 * <p>文件名称: CdfConst</p>
 * <p>文件描述: </p>
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

public class CdfConst
{
    public final static String DB_USER_CDF = "cdf";
    public final static String DB_PASSWORD_FOR_CDF = "U_tywg_2008";
    
    public final static String DB_TYPE_ORACLE = "oracle";
    public final static String DB_TYPE_MSSQL = "mssql";
    
    public static final String CSV_DATA_REGEX = ",";
    
    public static final String REPORT_VALUE_NOTSET = "";
    public static final String REPORT_VALUE_INVALID = "--";
    public static final String REPORT_PROGRAM_ERROR = "---";
    
    public static final String FTP_CDF_PATH = "CDF";
    
    public static final String DB_PATH = "DBPATH";
    public static final String VERSION_ID = "VERSION_ID";
    
    // Location length of BOARD
    public static final int LOCATION_LENGTH_BOARD = 3;
    
    // Location length of ADSL, VDSL, SHDSL, ETHERNET
    public static final int LOCATION_LENGTH_XDSL = 4;
    
    // Location length of IGMPUSER, IGMPVLAN
    public static final int LOCATION_LENGTH_IGMP = 5;
    
    // Location length of VDSLPORTTEX
    public static final int LOCATION_LENGTH_VDSLPORTTEX = 6;
}

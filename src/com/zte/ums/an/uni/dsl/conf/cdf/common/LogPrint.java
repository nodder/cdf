package com.zte.ums.an.uni.dsl.conf.cdf.common;

import org.apache.log4j.Logger;

/**
 * Error级别，窗口和日志都记录；
 * Info级别，窗口和日志都记录；
 * Debug级别，仅日志记录；
 * printStep方法是Info级别的一种特殊实现。
 * @author chenduoduo
 *
 */
public class LogPrint
{
    public static void logError(Logger logger, String str, Throwable ex)
    {
        ex.printStackTrace();
        logger.error(str, ex);
    }
    
    public static void logError(Logger logger, String str)
    {
        printInfo("Error:" + str);
        logger.error(str);
    }
    
    public static void logWarn(Logger logger, String str)
    {
        printInfo("Warning:" + str);
        logger.warn(str);
    }

    public static void logDebug(Logger logger, String info)
    {       
        logger.debug(info);
    }
    
    public static void logInfo(Logger logger, String info)
    {
        printInfo(info);
        logger.info(info);
    }
    
    private static void printInfo(String info)
    {
        System.out.println(CdfUtil.getCurDateTime() + ":" + info);
    }

    private LogPrint()
    {
    }
}

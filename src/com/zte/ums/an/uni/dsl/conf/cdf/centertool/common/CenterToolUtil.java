package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.singleton.InstanceControlByFile;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportDataXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportXmlParser;

/**
 * <p>文件名称: CenterToolUtil.java</p>
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
public class CenterToolUtil
{
    public static final String[][] WEEKDAY = new String[][]
    {
        {"1", "Sunday"},
        {"2", "Monday"},
        {"3", "Tuesday"},
        {"4", "Wednesday"},
        {"5", "Thursday"},
        {"6", "Friday"},
        {"7", "Saturday"}};
    
    public static final int CRON_MODE_DAILY = 1;
    public static final int CRON_MODE_WEEKLY = 2;
    public static final int CRON_MODE_MONTHLY = 3;
    
    private static final String USERDIR = System.getProperty("user.dir");
    
    private static final String SVR_NAME_DISPATCH = "dispatch server";
    private static final String SVR_NAME_SUBCOLLECT = "sub-collect server";
    private static final String SVR_NAME_REPORT = "report server";
    
    public static boolean isLocalHostIP(String ipAddr)
    {
        ArrayList<String> allHostIPs = getIpv4List();
        
        for(String ip : allHostIPs)
        {
            if(ip.equalsIgnoreCase(ipAddr))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static String getAllHosts()
    {
        ArrayList<String> allHostIPs = CenterToolUtil.getIpv4List();
        
        if(allHostIPs.size() > 0)
        {
            StringBuffer buf = new StringBuffer("(Options:");
            for(String host : allHostIPs)
            {
                buf.append(host).append(",");
            }
            
            buf.delete(buf.length() - 1, buf.length());
            buf.append(")");
            
            return buf.toString();
        }
        
        return "";
    }
    
    private static ArrayList<String> getIpv4List()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        try
        {
            Enumeration<?> enumNetworkInterface = NetworkInterface.getNetworkInterfaces();
            if(enumNetworkInterface != null)
            {
                while(enumNetworkInterface.hasMoreElements())
                {
                    NetworkInterface ni = (NetworkInterface)enumNetworkInterface.nextElement();
                    Enumeration<?> cardipenum = ni.getInetAddresses();
                    while(cardipenum.hasMoreElements())
                    {
                        InetAddress inetAddress = (InetAddress)cardipenum.nextElement();
                        if(inetAddress instanceof Inet4Address&&!inetAddress.getHostAddress().equalsIgnoreCase("127.0.0.1") 
                                        && !inetAddress.getHostAddress().equalsIgnoreCase("0.0.0.0"))
                        {
                            String ip = inetAddress.getHostAddress();
                            arrayList.add(ip);
                        }
                    }
                }
            }
        }
        catch(SocketException e)
        {
            e.printStackTrace();
        }
        return arrayList;
    }
    
    public static boolean saveAllXml()
    {
        boolean result = true;
        result =  result && getReportXml().save();
        result =  result && getReportDataXml().save();
        result =  result && getDispatchXml().save();
        result =  result && getCollectXml().save();
        
        return result;
    }
    
    public static ReportXmlParser getReportXml()
    {
        changeToReportDir();
        return ReportXmlParser.getInstance();
    }
    
    public static ReportDataXmlParser getReportDataXml()
    {
        changeToReportDir();
        return ReportDataXmlParser.getInstance();
    }
    
    public static DispatchXmlParser getDispatchXml()
    {
        changeToDispatchDir();
        return DispatchXmlParser.getInstance();
    }
    
    public static CollectXmlParser getCollectXml()
    {
        changeToSubCollectDir();
        return CollectXmlParser.getInstance();
    }
    
    public static void changeToReportDir()
    {
        setUserDir("server_report");
    }
    
    public static void changeToDispatchDir()
    {
        setUserDir("server_dispatch");
    }
    
    public static void changeToSubCollectDir()
    {
        setUserDir("server_subcollect");
    }
    
    public static void resetDir()
    {
        setUserDir(".");
    }
    
    private static void setUserDir(String relativeDir)
    {
        String newDir = (new File(USERDIR, relativeDir)).getAbsolutePath();
        System.setProperty("user.dir", newDir);
    }
    
    public static boolean isValidIPAddr(String ipAddr)
    {
        if(!checkStr(ipAddr))
        {
            return false;
        }
        
        String[] fields = ipAddr.split("\\.");
        if(fields.length != 4)
        {
            return false;
        }
        
        try
        {
            for(String field : fields)
            {
                int intField = Integer.parseInt(field);
                if(intField < 0 || intField > 255)
                {
                    return false;
                }
            }
            
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private static boolean checkStr(String ipAddr)
    {
        if(ipAddr == null)
        {
            return false;
        }
        
        if(ipAddr.startsWith(".") || ipAddr.endsWith("."))
        {
            return false;
        }
        
        return true;
    }
    
    private static boolean isValid(int value, int min, int max)
    {
        return value >= min && value <= max;
    }
    
    public static boolean isValidSecond(String value)
    {
        try
        {
            int tmpSecond = Integer.parseInt(value);
            return isValid(tmpSecond, 0, 59);
        }
        catch(NumberFormatException e)
        {
        }
        
        return false;
    }
    
    public static boolean isValidMinute(String value)
    {
        return isValidSecond(value);
    }
    
    public static boolean isValidHour(String value)
    {
        try
        {
            int tmpHour = Integer.parseInt(value);
            return isValid(tmpHour, 0, 23);
        }
        catch(NumberFormatException e)
        {
        }
        
        return false;
    }
    
    public static boolean isValidDay(String value)
    {
        try
        {
            int tmpDay = Integer.parseInt(value);
            return isValid(tmpDay, 1, 31);
        }
        catch(NumberFormatException e)
        {
        }
        
        return false;
    }
    
    public static boolean isValidWeekDay(String value)
    {
        try
        {
            int tmpWeekDay = Integer.parseInt(value);
            return isValid(tmpWeekDay, 1, 7);//1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT
        }
        catch(NumberFormatException e)
        {
        }
        
        return false;
    }
    
    public static boolean isValidCronMode(String value)
    {
        try
        {
            int tmpMode = Integer.parseInt(value);
            return isValid(tmpMode, CRON_MODE_DAILY, CRON_MODE_MONTHLY);
        }
        catch(NumberFormatException e)
        {
        }
        
        return false;
    }
    
    public static boolean isCDFRunning()
    {
        return isReportServerRunning() || isDispatchServerRunning() || isSubcollectServerRunning();
    }
    
    public static boolean isAllCDFRunning()
    {
        return isReportServerRunning() && isDispatchServerRunning() && isSubcollectServerRunning();
    }
        
    public static String getSvrRunningDiscripStr()
    {
        ArrayList<String> runningSvr = getRunningServer();
        
        String displacyStr = "";
        
        if(runningSvr == null || runningSvr.size() == 0)
        {
            return "";
        }
        else if(runningSvr.size() == 1)
        {
            displacyStr = runningSvr.get(0) + " is";
        }
        else
        {
            for(int i = 0; i < runningSvr.size(); i++)
            {
                displacyStr += runningSvr.get(i) + ", ";
            }
            
            displacyStr = displacyStr.substring(0, displacyStr.length() - 2);
            
            displacyStr += " are";
        }
        
        displacyStr += " running.";
        return displacyStr;
    }
    
    public static String getNotRunningSvrStr()
    {
        ArrayList<String> notRunningSvrs = getNotRunningServer();
        
        String displacyStr = "";
        
        for(String notRunningSvr : notRunningSvrs)
        {
            displacyStr += "," + notRunningSvr + "";
        }
        
        if(displacyStr.length() > 0)
        {
            return displacyStr.substring(1);
        }
        
        return "";
    }
    
    private static ArrayList<String> getNotRunningServer()
    {
        ArrayList<String> allSvrs = new ArrayList<String>();
        allSvrs.add(SVR_NAME_DISPATCH);
        allSvrs.add(SVR_NAME_SUBCOLLECT);
        allSvrs.add(SVR_NAME_REPORT);
        
        ArrayList<String> runningSvrs = getRunningServer();
        for(String runningSvr : runningSvrs)
        {
            allSvrs.remove(runningSvr);
        }
        
        return allSvrs;
    }

    private static ArrayList<String> getRunningServer()
    {
        boolean isDispatchSvrRunning = CenterToolUtil.isDispatchServerRunning();
        boolean isSubcolletSvrRunning = CenterToolUtil.isSubcollectServerRunning();
        boolean isReportSvrRunning = CenterToolUtil.isReportServerRunning();
        
        ArrayList<String> runningSvr = new ArrayList<String>();
        if(isDispatchSvrRunning)
        {
            runningSvr.add(SVR_NAME_DISPATCH);
        }
        if(isSubcolletSvrRunning)
        {
            runningSvr.add(SVR_NAME_SUBCOLLECT);
        }
        if(isReportSvrRunning)
        {
            runningSvr.add(SVR_NAME_REPORT);
        }
        return runningSvr;
    }
    
    public static boolean isDispatchServerRunning()
    {
        changeToDispatchDir();
        return testRunningstatus();
    }
    
    public static boolean isSubcollectServerRunning()
    {
        changeToSubCollectDir();
        return testRunningstatus();
    }
    
    public static boolean isReportServerRunning()
    {
        changeToReportDir();
        return testRunningstatus();
    }

    private static boolean testRunningstatus()
    {
        boolean isRunning = false;
        
        try
        {
            isRunning = InstanceControlByFile.tryRunning();
        }
        finally
        {
            InstanceControlByFile.close();
        }
        
        return isRunning;
    }
    
    public static void runProcess(String cmd)
    {
        ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));      
        try
        {
            Process p = pb.start();
            System.out.println("Please wait...");
            p.waitFor();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean isWindowsOS()
    {
        String osName = System.getProperty("os.name");
        if((osName != null) && osName.startsWith("Windows"))
        {
            return true;
        }
        
        return false;
    }
    
    public static boolean isLinux()
    {
        String osName = System.getProperty("os.name");
        if((osName != null) && osName.startsWith("Linux"))
        {
            return true;
        }
        
        return false;
    }
    
    public static void printGroupTile(String checkName)
    {
        System.out.println("===============" + checkName);
    }
    
    public static void printGroupHelp(String helpStr)
    {
        System.out.println("Note:" + helpStr);
    }
    
    public static void printCheckTile(String checkName)
    {
        System.out.print(checkName + "...");
    }
    
    public static void printSucess()
    {
        System.out.println("OK");
    }
    
    public static void pressEnterToConitue(boolean showMessage)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        if(showMessage)
        {
            System.out.println("Press enter to continue...");
        }
        
        try
        {
            reader.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static String readValidInput(boolean ignoreCase, String... allValidStr)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String input = null;
            while(!isInputValid(ignoreCase,input, allValidStr))
            {
                input = getInput(reader);
            }
            
            return input;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private static boolean isInputValid(boolean ignoreCase, String input, String... allValidStr)
    {
        if(input != null)
        {
            for(String validStr : allValidStr)
            {
                if(ignoreCase)
                {
                    if(validStr.equalsIgnoreCase(input))
                    {
                        return true;
                    }
                }
                else
                {
                    if(validStr.equals(input))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private static String getInput(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }
    
    public static void askAndSaveXML()
    {
        System.out.print("Do you want to save all the configuration?(y/n)[y]");
        String input = CenterToolUtil.readValidInput(true, "y", "n", "");
        if("y".equalsIgnoreCase(input) || "".equalsIgnoreCase(input))
        {
            if(CenterToolUtil.saveAllXml())
            {
                System.out.println("All saved.");
                System.out.println("Configuration has changged. "
                                   + "Suggestion to run 'Run Configuraton Tests' in the 'Test and Maintenance' menu before start CDF.");
            }
            else
            {
                System.out.println("Failed to save.");
            }
        }
    }
    
    public static String getCdfDBDataVersion()
    {
        Connection conn = CdfDbConnectionUtil.getDBConnection4Cdf();
        
        if(conn == null)
        {
            return "--";
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from DATA_VERSION");
            
            if(rs.next())
            {
                return rs.getString("version");
            }
        }
        catch(SQLException e)
        {
           
        }
        finally
        {
            CdfDbUtil.closeDB(stmt, rs);
            CdfDbUtil.closeDB(conn);
        }
        
        return "unkown";
    }
    
    public static String getVersion()
    {
        resetDir();
        return CdfUtil.getVersion();
    }
        
    public static void main(String[] args)
    {
//        Assert.assertTrue(isValidIPAddr("255.255.255.255"));
//        Assert.assertTrue(isValidIPAddr("0.0.0.0"));
//        Assert.assertTrue(isValidIPAddr("192.168.1.1"));
//        Assert.assertTrue(isValidIPAddr("192.168.1.01"));
//        
//        Assert.assertFalse(isValidIPAddr(".0.0.0.0"));
//        Assert.assertFalse(isValidIPAddr("0.0.0.0."));
//        Assert.assertFalse(isValidIPAddr("0.0.0.0.0"));
//        Assert.assertFalse(isValidIPAddr("0"));
//        Assert.assertFalse(isValidIPAddr("xxx"));
        
//        System.out.println(readValidInput(true, "y", "n"));
        
        System.out.println("OK");
    }

}

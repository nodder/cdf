package com.zte.ums.an.uni.dsl.conf.cdf.centertool;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;

/**
 * <p>文件名称: MainMenu</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-13</p>
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
public class MainMenu extends CenterToolMenu
{    
    private static String welcomInfo = null;
    
    private final static String[][] menuListForUnix = new String[][] {
      {"1", "1. Start CDF", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionStartup"},
      {"2", "2. Shutdown CDF", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionShutDown"},
      {"3", "3. Configurations", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfig"},
      {"4", "4. Run Configuration Tests", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfigCheck"},
      {"5", "5. Delete Temporary Files", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionDeleteTmp"},
      {"6", "6. How to run correctly?", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionHelp"},
      {"7", "7. Exit", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionExit"}};
  
    private final static String[][] menuListForWindows = new String[][] {
      {"1", "1. Start CDF", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionStartup"},
      {"2", "2. Configurations", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfig"},
      {"3", "3. Run Configuration Tests", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfigCheck"},
      {"4", "4. Delete Temporary Files", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionDeleteTmp"},
      {"5", "5. How to run correctly?", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionHelp"},
      {"6", "6. Exit", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionExit"}};

    private static CenterToolMenu instance = new MainMenu();
    
    public static CenterToolMenu getInstance()
    {
        return instance;
    }
    
    @Override
    public String[][] getMenuList()
    {
        if(CenterToolUtil.isWindowsOS())
        {
            return menuListForWindows;
        }
        
        return menuListForUnix;
    }
    
    @Override
    public void presentTitle()
    {
        System.out.println(welcomeInfo());
        System.out.println();
    }
    
    private static String welcomeInfo()
    {
        if(welcomInfo == null)
        {
            if(version != null)
            {
                welcomInfo = "==============CDF Main Menu (" + version + ")==============";
            }
            else
            {
                welcomInfo = "==============CDF Main Menu==============";
            }
        }
        return welcomInfo;
    }
    
    public static void main(String[] args)
    {
        MainMenu.getInstance().runMenu();
    }
}

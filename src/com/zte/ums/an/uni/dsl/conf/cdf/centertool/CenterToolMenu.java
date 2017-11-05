package com.zte.ums.an.uni.dsl.conf.cdf.centertool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;

/**
 * <p>文件名称: CenterToolMenu.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-17</p>
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
public abstract class CenterToolMenu
{        
    private static InputStreamReader systemInReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(systemInReader);
    public static String version = null;
    
    private String[][] menuList = getMenuList();
    
    public void runMenu()
    {
//        menuList = getMenuList();
        try
        {
            version = CdfUtil.getVersion();
            while(true)
            {
                presentTitle();
                presentMenu();
                String input = getValidUserInput();
                doAction(input);
                afterAction();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void presentMenu()
    {
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < menuList.length; i++)
        {
            buf.append(menuList[i][1]).append("\n");
        }
        
        System.out.println(buf.toString());
    }
    
    private static void afterAction() throws IOException
    {
        System.out.println("Press enter to back to menu...");
        reader.readLine();
        System.out.println("\n");
    }

    private static void doAction(String input)
    {
        IMenuAction action = createActionInstance(input);
        
        if(action != null)
        {
            action.doAction();
        }
    }

    private static IMenuAction createActionInstance(String actionClass)
    {
        try
        {
            Class<?> instance = Class.forName(actionClass);
            try
            {
                return (IMenuAction)instance.newInstance();
            }
            catch(InstantiationException e)
            {
                e.printStackTrace();
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    private String getValidUserInput()
    {

        try
        {
            while(true)
            {
                System.out.print("Please input index:");
                String input = reader.readLine();
                if(input != null)
                {
                    input = input.trim();
                    
                    String inputAction = getInputAction(input);
                    if(null != inputAction)
                    {
                        return inputAction;
                    }
                }
            }  
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
//            CommonUtil.closeInputStreamReader(systemInReader);
//            CommonUtil.closeBufferedReader(reader);
        }
        
        return null;
    }
    
    private String getInputAction(String input)
    {
        for(int i = 0; i < menuList.length; i++)
        {
            if(menuList[i][0].equalsIgnoreCase(input))
            {
                return menuList[i][2];
            }
        }
        
        return null;
    }
    
    abstract public String[][] getMenuList();
    abstract public void presentTitle();
}

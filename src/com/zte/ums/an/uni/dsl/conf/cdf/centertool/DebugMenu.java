package com.zte.ums.an.uni.dsl.conf.cdf.centertool;

/**
 * <p>文件名称: DebugMenu</p>
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
public class DebugMenu extends CenterToolMenu implements IMenuAction
{    
    private final static String[][] debugMenuList = new String[][] {
      {"1", "1. Run Configuration Tests", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfigCheck"},
      {"2", "2. Delete Temporary Files", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionDeleteTmp"},
      {"3", "3. Zip Log", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionZipLog"},
      {"4", "4. Back to Main Menu", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionBacktoMainMenu"}};
      
    @Override
    public String[][] getMenuList()
    {
        return debugMenuList;
    }

    @Override
    public void presentTitle()
    {
        System.out.println("=======CDF Test and Maintenance Menu=======");
        System.out.println();
    }
    
    @Override
    public void doAction()
    {
        runMenu();
    }
}

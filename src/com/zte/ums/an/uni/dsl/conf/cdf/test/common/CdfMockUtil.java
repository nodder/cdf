package com.zte.ums.an.uni.dsl.conf.cdf.test.common;

import java.io.File;

/**
 * <p>文件名称: CdfMockUtil.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011-12-28</p>
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
public class CdfMockUtil
{
    private CdfMockUtil()
    {
    }
    
    public static void setUserDirToParentPath()
    {
        String userDir = System.getProperty("user.dir");
        userDir = (new File(userDir)).getParent();
        System.setProperty("user.dir", userDir);
    }
    
    public static float formatFloat(float oldFloat)
    {
        return (float)((int)(oldFloat * 100))/100;
    }
    
    public static float caculatePercent(int i1, int i2)
    {
        return ((float)i1/i2) * 100;
    }
}

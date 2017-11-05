package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import junit.framework.Assert;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: NetworkToPortIDTranslate</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-2</p>
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
public class NetworkToPortIDTranslate implements IFieldDataTranslate
{
    /**
     * 类似"1/1/7/1/4093"转换为"R1.S1.LT01.01"
     */
    @Override
    public String translate(String networkID)
    {
        if(!isValid(networkID))
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        String[] cpnArray = networkID.split("/");
        
        StringBuffer buf = new StringBuffer();
        
        buf.append("R").append(cpnArray[0]).append(".");
        
        buf.append("S").append(cpnArray[1]).append(".");
        
        buf.append("LT");
        if(cpnArray[2].length() == 1)
        {
            buf.append("0");
        }
        buf.append(cpnArray[2]).append(".");
        
        if(cpnArray[3].length() == 1)
        {
            buf.append("0");
        }
        buf.append(cpnArray[3]);
        
        return buf.toString();
    }
    
    private boolean isValid(String strCPN)
    {
        if(strCPN == null || !strCPN.contains("/"))
        {
            return false;
        }
        
        String[] arr = strCPN.split("/");
        
        if(arr.length != 5)
        {
            return false;
        }
        
        return true;
    }
    
  public static void main(String[] args)
  {
      IFieldDataTranslate t = new NetworkToPortIDTranslate();
      
      String networkID = "1/1/7/1/4093";
      Assert.assertEquals("R1.S1.LT07.01", t.translate(networkID));
      
      System.out.println("OK");
  }
}

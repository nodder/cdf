package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.simplerule;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: DefaultTranslateCenter.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-1</p>
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
public class SimpleRuleTranslateCenter
{    
    private static Logger logger = ZXLogger.getLogger(SimpleRuleTranslateCenter.class.getName());
    
    public static IFieldDataTranslate getDefaultTranslateInstance()
    {
        return new DefaultTranslate();
    }
    
    public static IFieldDataTranslate getTranslateInstance(String translateFunctionName)
    {
        if(translateFunctionName == null ||translateFunctionName.equalsIgnoreCase(""))
        {
            return getDefaultTranslateInstance();
        }
        
        if(isConstant(translateFunctionName))
        {
            return new ConstantTranslate(translateFunctionName.substring(1, translateFunctionName.length() - 1));
        }

        if(isMathOper(translateFunctionName))
        {
            return getMathTranslate(translateFunctionName);
        }

        return new DefaultTranslate();
    }
    

    private static IFieldDataTranslate getMathTranslate(String translateFunctionName)
    {
        char oper = translateFunctionName.charAt(0);
        String strNum = translateFunctionName.substring(1);
        
        switch(oper)
        {
            case '/':
                return new DivisionTranslate(Double.parseDouble(strNum));
            case '*':
                return new MultiTranslate(Double.parseDouble(strNum));
            case '+':
                return new AddTranslate(Long.parseLong(strNum));
            case '-':
                return new SubtractTranslate(Long.parseLong(strNum));

            default:
                return null;
        }
    }

    private static boolean isMathOper(String translateFunctionName)
    {
        final String[] mathOperators = new String[] {"/", "*", "+", "-"};
        
        for(String mathOper : mathOperators)
        {
            if(translateFunctionName.startsWith(mathOper))
            {
                return isNumber(translateFunctionName.substring(1), translateFunctionName);
            }
        }
        
        return false;
    }
    
    private static boolean isNumber(String s, String translateFunctionName)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            LogPrint.logError(logger, "The translateRule " + translateFunctionName + " is incorrect");
        }
        
        return false;
    }

    private static boolean isConstant(String translateFunctionName)
    {
        return translateFunctionName.startsWith("@") && translateFunctionName.endsWith("@");
    }
    
}

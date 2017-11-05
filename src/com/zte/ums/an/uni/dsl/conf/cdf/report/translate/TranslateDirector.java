package com.zte.ums.an.uni.dsl.conf.cdf.report.translate;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.simplerule.SimpleRuleTranslateCenter;
import com.zte.ums.n3common.api.ZXLogger;


/**
 * <p>文件名称: TranslateDirector.java</p>
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
public class TranslateDirector
{
    private static Logger logger = ZXLogger.getLogger(TranslateDirector.class.getName());
    
    private TranslateDirector()
    {
    }
    
    public static IFieldDataTranslate getTranslateInstance(String translateFunctionName)
    {
        if(translateFunctionName == null)
        {
            return SimpleRuleTranslateCenter.getDefaultTranslateInstance();
        }
            
        Class<?> transClass = loadDataTranslateClass(translateFunctionName);
        
        if(transClass != null)
        { 
            return translateByUserDefineClass(transClass);
            
        }
        
        return SimpleRuleTranslateCenter.getTranslateInstance(translateFunctionName);
    }

    private static IFieldDataTranslate translateByUserDefineClass(Class<?> transClass)
    {
        try
        {
            return (IFieldDataTranslate)transClass.newInstance();
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
            return SimpleRuleTranslateCenter.getDefaultTranslateInstance();
        }
    }
    
    private static Class<?> loadDataTranslateClass(String className)
    {
        Class<?> myClass = null;
        try
        {
            myClass = ClassLoader.getSystemClassLoader().loadClass(className);
        }
        catch(ClassNotFoundException e)
        {
            return null;
        }
        
        if(!IFieldDataTranslate.class.isAssignableFrom(myClass))
        {
            throw new IllegalArgumentException("The translate class must be assignable from " + IFieldDataTranslate.class.getSimpleName());
        }
        
        return myClass;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
//        String s = "com.zte.ums.an.uni.dsl.conf.cdf.report.translate.SimpleFieldTranslate";
//        Class<?> myClass = ClassLoader.getSystemClassLoader().loadClass(s);
//        
//        System.out.println("className:" + myClass.getName());
//        
//        Class<?>[] interfaces = myClass.getInterfaces();
//        for(Class<?> cls : interfaces)
//        {
//            System.out.println("interface:" + cls.getName());
//        }
//        
//        Class<?> superClass = myClass.getSuperclass();
//        System.out.println("superClass:" + superClass.getName());
//        
//        System.out.println(IFieldDataTranslate.class.isAssignableFrom(myClass));
//        
//        System.out.println(((IFieldDataTranslate)(myClass.newInstance())).translate());
        
        loadDataTranslateClass("com.zte.ums.an.uni.dsl.conf.cdf.report.translate.SimpleFieldTranslate");
    }
}

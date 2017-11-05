package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: SnmpFieldValueGetter.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-5</p>
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
public class SnmpFieldValueGetter implements IFixedAreaGetter
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private Method myMethod;
    
    public SnmpFieldValueGetter(String methodName) throws SecurityException, NoSuchMethodException
    {
        myMethod = SnmpNode.class.getMethod(methodName);
    }
    
    @Override
    public String getFieldValue(SnmpNode snmpNode)
    {
        if(myMethod != null)
        {
            try
            {
                return (String)(myMethod.invoke(snmpNode));
            }
            catch(Exception e)
            {
                LogPrint.logError(logger, "", e);
            }
        }
        
        return CdfConst.REPORT_PROGRAM_ERROR;
    }
    
}

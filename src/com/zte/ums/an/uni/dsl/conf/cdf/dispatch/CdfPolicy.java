package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.AbsCdfPolicyProcessor;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CdfPolicy.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年9月1日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author lixiaochun
 */
public class CdfPolicy implements Runnable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    AbsCdfPolicyProcessor policy = null;
    
    public CdfPolicy()
    {
        this.policy = DispatchFactory.createCdfPolicyProc();
    }
    
    //****** 代码段: 线程方法 **********************************************************************/

    public void run()
    {
        try
        {
            this.policy.runPolicy();
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

}

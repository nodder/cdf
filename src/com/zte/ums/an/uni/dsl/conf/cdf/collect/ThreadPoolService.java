package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ThreadPoolService.java</p>
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
public class ThreadPoolService
{
    //****** 代码段: 变量定义 **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    public static ThreadPoolService object = new ThreadPoolService();

    private int poolSize = -1;

    private ExecutorService executorService = null;

    //****** 代码段: 公共方法 **********************************************************************/

    public static ThreadPoolService instance()
    {
        return object;
    }

    public void initalize()
    {
        this.poolSize = Integer.parseInt(CollectXmlParser.getInstance().getThreadNum());

        createExecutorService();
    }

    public boolean release()
    {
        return destoryExecutorService();
    }

    public Future submit(Callable task)
    {
        return executorService.submit(task);
    }

    public boolean isBusy()
    {
        return ((ThreadPoolExecutor)executorService).getQueue().size() > 0 ? true : false;
    }

    //****** 代码段: 私有方法 **********************************************************************/

    private boolean destoryExecutorService()
    {
        boolean result = true;

        if(executorService != null && !executorService.isShutdown())
        {
            executorService.shutdown();
            
            try
            {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
            catch(InterruptedException e)
            {
                LogPrint.logError(logger, "Release thread pool failed.", e);

                result = false;
            }
        }
        return result;
    }

    private void createExecutorService()
    {
        destoryExecutorService();

        executorService = Executors.newFixedThreadPool(poolSize);
    }
}

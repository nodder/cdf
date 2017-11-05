package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ListenerCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.AbsParserProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: ThreadTask.java</p>
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
public class ThreadTask implements Callable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private SnmpNode snmpNode = null;

    public ThreadTask(SnmpNode snmpNode)
    {
        this.snmpNode = snmpNode;
    }

    //****** 代码段: Callable接口方法实现 ****************************************************************/

    public Object call()
    {
        try
        {
            AbsCollectorProcessor cp = CollectFactory.createCollectorProc(snmpNode);
            ListenerCollection.getInstance().notifyOnStartCollectSingleDSLAM(snmpNode);
            ListenerCollection.getInstance().notifyOnBeforeCollect(snmpNode);
            
            if(cp.isNeedOperate())
            {
                boolean isCollectSuccess = doCollectAndParse(cp);
                
                ListenerCollection.getInstance().notifyOnFinishCollectSingleDSLAM(isCollectSuccess, snmpNode);
            }
        }
        catch(Throwable t)
        {
            LogPrint.logError(logger, "", t);
        }
            
        return null;
    }

    private boolean doCollectAndParse(AbsCollectorProcessor cp)
    {
        boolean isCollectSuccess = true;
        
        if(isCheckXml() && cp.isVersionInconsistent())
        {
            cp.downloadXMLWithCurrentVersion();
        }
        else
        {
            isCollectSuccess = cp.getCSVFileFromNe();
            ListenerCollection.getInstance().notifyOnAfterCollect(snmpNode);
            
            if(isCollectSuccess)
            {
                parseData();
            }
        }
        return isCollectSuccess;
    }

    private static boolean isCheckXml()
    {
        return CollectXmlParser.getInstance().getCheckXml();
    }

    protected void parseData()
    {
        ListenerCollection.getInstance().notifyOnBeforeParser(snmpNode);
        
        AbsParserProcessor pp = CollectFactory.createParserProc(snmpNode);
        boolean result = pp.parseDataFromCsv();
        
        ListenerCollection.getInstance().notifyOnAfterParser(snmpNode);
        
        if(result)
        {
            LogPrint.logInfo(logger, "Succeeded in parsing data with " + snmpNode.getIpAddress() + ".");
        }
    }
}

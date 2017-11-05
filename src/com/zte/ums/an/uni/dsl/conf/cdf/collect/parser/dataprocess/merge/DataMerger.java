package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.merge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.SeqRecProxy;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;

/**
 * <p>文件名称: DataMerger</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年12月14日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */

public class DataMerger
{
    public List<SeqRecProxy> allProxy;
    public List<SeqRecProxy> availableProxy = new ArrayList<SeqRecProxy>();
    public List<SeqRecProxy> toRemProxy = new ArrayList<SeqRecProxy>();
    
    private Comparator entryIdComp;
    private int initialSize = 0;
    
    public DataMerger(List<SeqRecProxy> allProxy)
    {
        this.allProxy = allProxy;
        this.availableProxy.addAll(allProxy);
        this.toRemProxy.addAll(availableProxy);
        this.entryIdComp = new CurRecEntryIdComparator();
        this.initialSize = allProxy.size();
    }

    public Record readMergedData()
    {
        while(prepareAndCheck())
        {
            Collections.sort(availableProxy, entryIdComp);
            compareData();
            if(isAllSameEntryId())
            {
                return mergeReportRec();
            }
        }

        return null;
    }
    
    public void close()
    {
        for(SeqRecProxy proxy : allProxy)
        {
            proxy.close();
        }
    }

    private boolean isAllSameEntryId()
    {
        return toRemProxy.size() == availableProxy.size();
    }

    private boolean prepareAndCheck()
    {
        prepareData();
        
        return checkExit();
    }

    private boolean checkExit()
    {
        return (initialSize != 0) && (this.availableProxy.size() == initialSize);
    }

    private void prepareData()
    {
        for(int i = 0; i <= this.toRemProxy.size() - 1; i++)
        {
            toRemProxy.get(i).next();
            if(toRemProxy.get(i).isEndOfFile())
            {
                toRemProxy.get(i).close();
                availableProxy.remove(toRemProxy.get(i));
            }
        }
        toRemProxy.clear();
    }
    
    private void compareData()
    {
        toRemProxy.addAll(availableProxy);
        
        int lastIndex = availableProxy.size() - 1;
        SeqRecProxy lastProxy = (SeqRecProxy) availableProxy.get(lastIndex);
              
        int currentIndex = lastIndex - 1;
        
        while(currentIndex >= 0)
        {
            SeqRecProxy currProxy = (SeqRecProxy) availableProxy.get(currentIndex);
            if (!isSameEntryId(lastProxy, currProxy))
            {
                for(int j = lastIndex - 1; j >= currentIndex; j--)
                {
                    toRemProxy.remove(toRemProxy.size() - 1);
                }
                return;
            }
            currentIndex--;
        }
    }

    private boolean isSameEntryId(SeqRecProxy former, SeqRecProxy backer)
    {
        return former.getRecord().getEntryId().equals(backer.getRecord().getEntryId());
    }

    private Record mergeReportRec()
    {
        Record mergedRecord = new Record();
        mergedRecord.setEntryId(this.availableProxy.get(0).getRecord().getEntryId());
        mergedRecord.setLineRec(getMergedLineRecord());
       
        return mergedRecord;
    }

    private String getMergedLineRecord()
    {
        StringBuffer buffMergedLine = new StringBuffer();
        
        for(SeqRecProxy readProxy : this.allProxy)
        {
            buffMergedLine.append(readProxy.getRecord().getLineRec() + CdfConst.CSV_DATA_REGEX);
        }

        if(buffMergedLine.length() > 0)
        {
            return buffMergedLine.delete(buffMergedLine.length() - 1, buffMergedLine.length()).toString();
        }

        return buffMergedLine.toString();
    }


    public static void main(String[] args) throws IOException
    {
        List<SeqRecProxy> allProxy = new ArrayList<SeqRecProxy>();

//            SeqRecProxy proxy2 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(ALL)_20000218212244.csv"));
//            SeqRecProxy proxy3 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(LINE)_20000218212243.csv"));
//            allProxy.add(proxy2);
//            allProxy.add(proxy3);

//            SeqRecProxy proxy2 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(ALL)_20000218212244.csv"));
//            SeqRecProxy proxy3 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORT_20000218212243.csv"));
//            allProxy.add(proxy2);
//            allProxy.add(proxy3);

//            SeqRecProxy proxy1 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORTLAG_20000218212243.csv"));
//            SeqRecProxy proxy2 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORTLAG_20000218212740.csv"));
//            allProxy.add(proxy1);
//            allProxy.add(proxy2);

//            SeqRecProxy proxy = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORTLAG_20000218212740.csv"));
//            allProxy.add(proxy);
        
//            SeqRecProxy proxy = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(LINE)_20000218212243.csv"));
//            allProxy.add(proxy);
        
//      SeqRecProxy proxy2 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(ALL)_20000218212244.csv"));
//      SeqRecProxy proxy3 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORT_20000218212740.csv"));
//      allProxy.add(proxy2);
//      allProxy.add(proxy3);
      
//      SeqRecProxy proxy1 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORT_20000218212243.csv"));
//      SeqRecProxy proxy2 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_USERPORT(ALL)_20000218212244.csv"));
//      SeqRecProxy proxy3 = new SeqRecProxy(new File("C:\\ftpdir\\CDF\\10.63.192.120\\10.63.192.120_UPLINKPORT_20000218212740.csv"));
//      allProxy.add(proxy1);
//      allProxy.add(proxy2);
//      allProxy.add(proxy3);
          
        DataMerger merger = new DataMerger(allProxy);

        long startTime = System.currentTimeMillis();

        Record mergerdData;
        while((mergerdData = merger.readMergedData()) != null)
        {
            String collectPoint = mergerdData.getEntryId();
            String collectResult = mergerdData.getLineRec();

            System.out.println(collectPoint + "," + collectResult);

        }

        System.out.println("Time cost : " + (System.currentTimeMillis() - startTime));
    }
}

/**
 * CurRecEntryIdComparator
 */
class CurRecEntryIdComparator implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        SeqRecProxy proxy1 = (SeqRecProxy) o1;
        SeqRecProxy proxy2 = (SeqRecProxy) o2;
        String entryId1 = proxy1.getRecord().getEntryId();
        String entryId2 = proxy2.getRecord().getEntryId();
        return entryId1.compareTo(entryId2);
    }
}

package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.io.IOException;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfTime;

/**
 * <p>文件名称: StartCsvMocker</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月21日</p>
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

public class StartCsvMocker
{
    public static void main(String[] args) throws Exception
    {
        String exportFilePath = "C:\\ftpdir\\CDF";
        int portNum = 192;
        int fileNum = 10000;
        
        CdfTime tm = new CdfTime();
        tm.markStartTime();
        
        CsvBuilderDirector dirct = new CsvBuilderDirector(exportFilePath, portNum, fileNum);
        
        try
        {
            dirct.build();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        tm.markEndTime();
        System.out.println("Time cost :" + tm.caculateTimeCost() + "s");
        
    }
}

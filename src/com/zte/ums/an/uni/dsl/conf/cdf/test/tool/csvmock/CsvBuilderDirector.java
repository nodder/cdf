package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;

/**
 * <p>文件名称: CsvBuilderDirector</p>
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

public class CsvBuilderDirector
{
    private static final int DEFAULT_CSV_FILE_NUM = 1;
    private static final int DEFAULT_PORT_NUM = 192;
    
    private String exportPathBase = "g:\\";
    private int portNum = DEFAULT_PORT_NUM;
    private int csvFileNum = DEFAULT_CSV_FILE_NUM;
        
    private PrintWriter printWriter;
    
    public CsvBuilderDirector()
    {
    }
    
    public CsvBuilderDirector(String exportPath, int portNum, int csvFileNum)
    {
        this.exportPathBase = CdfUtil.formatPath(exportPath);        
        this.portNum = portNum;
        this.csvFileNum = csvFileNum;
    }
    
    public void build() throws IOException
    {
        CdfUtil.createdDirIfNoExist(this.exportPathBase);

        for(int i = 0; i < csvFileNum; i++)
        {
            generateOneCsv();
            System.out.println("Remaining " + (csvFileNum - i) + " files.");
        }
    }
    
    private void generateOneCsv() throws IOException
    {
        String fileName = FileNameMocker.getInstance().getNextFileName();
        String filepath = exportPathBase + FileNameMocker.getInstance().getIPAddrFromCurrFileName() + File.separatorChar;
        (new File(filepath)).mkdirs();
        
        String exportFile = filepath + fileName;
        
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(exportFile)));

        printWriter.println(MockedDataBuilder.mockHeader());
        
        for(int i = 0; i < portNum; i++)
        {
            printWriter.println(MockedDataBuilder.mockDataLine());
        }
        
        printWriter.close();
        System.out.print("succeded in generate CSV at " + exportFile + ".");
    }
}

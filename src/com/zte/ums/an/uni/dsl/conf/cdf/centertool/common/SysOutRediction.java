package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * <p>文件名称: SysOutRediction.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-17</p>
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
public class SysOutRediction
{
    private static PrintStream sysOut = null;
    private static PrintStream sysErrOut = null;
    private static PrintStream newOut = null;
    private File targetFile;
    
    public SysOutRediction(File targetFile)
    {
        sysOut = System.out;
        sysErrOut = System.err;
        
        this.targetFile = targetFile.getAbsoluteFile();
    }

    public void startRediction()
    {
        try
        {
            newOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(targetFile)));
            System.setErr(newOut);
            System.setOut(newOut);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void endRecdition()
    {
        if(newOut != null)
        {
            newOut.close();
        }
        
        System.setErr(sysErrOut);
        System.setOut(sysOut);
        
        this.targetFile.delete();
    }
}

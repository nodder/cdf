package com.zte.ums.an.uni.dsl.conf.cdf.common.singleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * <p>文件名称: InstanceControlByFile.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-7</p>
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
public class InstanceControlByFile
{
    private static File lockFile = null;
    private static FileChannel channel = null;
    private static RandomAccessFile raf = null;
    private static FileLock lock = null;
    
//    static
//    {
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//            @Override
//            public void run()
//            {
//                closeFileLock();
//                closeChannel();
//                closeRaf();
//                deleteFile();
//            }
//        });
//    }
    
    public static void close()
    {
      closeFileLock();
      closeChannel();
      closeRaf();
      deleteFile();
    }
    
    public static boolean tryRunning()
    {
        try
        {
            lockFile = new File("conf/lock");
            raf = new RandomAccessFile(lockFile.getAbsoluteFile(), "rw");
            channel = raf.getChannel();
            lock = channel.tryLock();
            if(lock != null)
            {
                return false;
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return true;
    }
    
    private InstanceControlByFile()
    {
    }
    
    private static void deleteFile()
    {
        lockFile.delete();
    }

    private static void closeRaf()
    {
        if(raf != null)
        {
            try
            {
                raf.close();
            }
            catch(IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void closeChannel()
    {
        if(channel != null)
        {
            try
            {
                channel.close();
            }
            catch(IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void closeFileLock()
    {
        if(lock != null && lock.isValid())
        {
            try
            {
                lock.release();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

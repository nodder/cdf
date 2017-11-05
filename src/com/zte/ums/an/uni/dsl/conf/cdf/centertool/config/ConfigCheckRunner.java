package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.AbsCdfConfigDataModelWithSequence;

/**
 * <p>文件名称: ConfigCheckRunner.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-3-13</p>
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
public class ConfigCheckRunner
{
    InputStreamReader systemInReader = null;
    BufferedReader reader = null;

    public ConfigCheckRunner()
    {
        this.systemInReader = new InputStreamReader(System.in);
        this.reader = new BufferedReader(systemInReader);
    }
    
    public void runCheck(ICdfConfigDataModel[] checkList)
    {
        for(ICdfConfigDataModel model : checkList)
        {
            runCheck(model);
        }
    }

    private void runCheck(ICdfConfigDataModel model)
    {
        while(true)
        {
            String input = getValidInput(model);
            if(model.setNewValue(input))
            {
                break;
            }
        }
    }
    
    public String runSingleCheck(AbsCdfConfigDataModelWithSequence model)
    {
        runCheck(model);
        return model.getUserInput();
    }

    private String getValidInput(ICdfConfigDataModel info)
    {
        try
        {
            while(true)
            {
                System.out.print(info.getTitle() + "[" + info.getCurrValue() + "]:");
                return getUserInput(info);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private String getUserInput(ICdfConfigDataModel info) throws IOException
    {
        String input = reader.readLine();
        if(input != null)
        {
            input = input.trim();
            
            if(input.equalsIgnoreCase(""))
            {
                input = info.getCurrValue();
            }
        }
        
        return input;
    }
    
    
    public void close()
    {
        if(systemInReader != null)
        {
            try
            {
                systemInReader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        if(reader != null)
        {
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

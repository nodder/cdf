package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import java.io.File;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: CdfLocalize</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012年01月09日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi
 */
public class CdfLocalize
{
  //****** 代码段: 常量定义 **********************************************************************/

    private final String LANGEUAGE_ENGLISH = "English";
    private final String LANGEUAGE_CHINESE = "Chinese";

    //****** 代码段: 变量定义 **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private String language = LANGEUAGE_ENGLISH;
    private String localizeParamFileName = null;

    private String nativeStringKey;
    private String nativeString;

    private Hashtable hashtable = new Hashtable();

    private TextDataFile textDataFile = new TextDataFile();
    private TableDataSet tableDataSet = new TableDataSet();
    private Column columnKey = new Column();
    private Column columnEng = new Column();
    private Column columnChi = new Column();

    //****** 代码段: 构造方法 **********************************************************************/

    public CdfLocalize()
    {
        this.language = LANGEUAGE_ENGLISH;
        this.localizeParamFileName = getLocalizeFile();
        loadLocalizeFiles(this.localizeParamFileName);
    }

    //****** 代码段: 公共方法 **********************************************************************/

    public String findLocalString(String nativeStringKey)
    {
        String temp = (String)hashtable.get((Object)(nativeStringKey.toUpperCase()));
        if(temp == null)
        {
            temp = nativeStringKey;
        }
        
        return temp;
    }

    //****** 代码段: 私有方法 **********************************************************************/

    private void loadLocalizeFiles(String fileName)
    {
        fileName = this.localizeParamFileName;

        prepareColumn();
        prepareDataSet(fileName);

        int rowCount = tableDataSet.rowCount();
        try
        {
            for(int i = 0; i < rowCount; i++)
            {
                nativeStringKey = tableDataSet.getString("ID").toUpperCase();

                nativeString = tableDataSet.getString(language);

                hashtable.put(nativeStringKey, nativeString);
                tableDataSet.next();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }

        tableDataSet.close();
    }

    private void prepareColumn()
    {
        columnKey.setCaption("ID");
        columnKey.setColumnName("ID");
        columnKey.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnKey.setServerColumnName("NewcolumnKey");
        columnKey.setSqlType(0);

        columnEng.setCaption(LANGEUAGE_ENGLISH);
        columnEng.setColumnName(LANGEUAGE_ENGLISH);
        columnEng.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnEng.setServerColumnName("NewcolumnEng");
        columnEng.setSqlType(0);

        columnChi.setCaption(LANGEUAGE_CHINESE);
        columnChi.setColumnName(LANGEUAGE_CHINESE);
        columnChi.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnChi.setServerColumnName("NewcolumnChi");
        columnChi.setSqlType(0);
    }

    private void prepareDataSet(String fileName)
    {
        tableDataSet.setDataFile(textDataFile);
        textDataFile.setFileName(fileName);
        textDataFile.setSeparator(",");
        tableDataSet.setColumns(new Column[]
            {columnKey, columnEng, columnChi});
        try
        {
            tableDataSet.open();
            tableDataSet.atFirst();
        }
        catch(Exception ex)
        {
            tableDataSet.close();
            LogPrint.logError(logger, "", ex);
            return;
        }
    }

    private static String getLocalizeFile()
    {
        return (new File("localize/zxnm01-uni-dsl-cdf-i18n.csv")).getAbsolutePath();
    }
}

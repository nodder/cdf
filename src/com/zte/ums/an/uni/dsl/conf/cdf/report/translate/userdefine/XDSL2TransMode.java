package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import java.util.ArrayList;

import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.n3common.api.BitMap;

/**
 * <p>文件名称: XDSL2TransMode.java</p>
 * <p>文件描述: UNI-DSL端口管理xDSL2传输模式类</p>
 * <p>版权所有: 版权所有(C)2001-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2007年5月31日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  cyf
 */

public class XDSL2TransMode
{
//****** 代码段: 变量定义 *******************************************************************************/

    private static CdfLocalize localize = new CdfLocalize();

    /**　传输模式分类 */
    public static String TRANSMODE_CATEGORY_ADSL = "ADSL";
    public static String TRANSMODE_CATEGORY_ADSL2 = "ADSL2";
    public static String TRANSMODE_CATEGORY_ADSL2PLUS = "ADSL2+";
    public static String TRANSMODE_CATEGORY_VDSL2 = "VDSL2";
    public static String TRANSMODE_CATEGORY_RESERVED = "RESERVED";

//****** 代码段: 常量定义 *******************************************************************************/

    /**
     * 传输模式表
     */
    public static final String[][] TRANSMODES =
        {
        //MIB-Bit, Transmission Mode(Abbreviation), Standard, Annex, FDM/EC, Transmission Mode(Description), Category
        {"0", "ansit1413", localize.findLocalString("RES_TransMode_Standard_ansit1413"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_ansit1413"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"1", "etsi", localize.findLocalString("RES_TransMode_Standard_etsi"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_etsi"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"2", "g9921PotsNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921PotsNonOverlapped"), "A",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9921PotsNonOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"3", "g9921PotsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921PotsOverlapped"), "A", "EC",
        localize.findLocalString("RES_TransMode_Description_g9921PotsOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"4", "g9921IsdnNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921IsdnNonOverlapped"), "B",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9921IsdnNonOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"5", "g9921IsdnOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921IsdnOverlapped"), "B", "EC",
        localize.findLocalString("RES_TransMode_Description_g9921IsdnOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"6", "g9921tcmIsdnNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921tcmIsdnNonOverlapped"),
        "C", "FDM", localize.findLocalString("RES_TransMode_Description_g9921tcmIsdnNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL}
        ,
        {"7", "g9921tcmIsdnOverlapped", localize.findLocalString("RES_TransMode_Standard_g9921tcmIsdnOverlapped"), "C",
        "EC", localize.findLocalString("RES_TransMode_Description_g9921tcmIsdnOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"8", "g9922potsNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9922potsNonOverlapped"), "A",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9922potsNonOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"9", "g9922potsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9922potsOverlapped"), "A", "EC",
        localize.findLocalString("RES_TransMode_Description_g9922potsOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"10", "g9922tcmIsdnNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9922tcmIsdnNonOverlapped"),
        "C", "FDM", localize.findLocalString("RES_TransMode_Description_g9922tcmIsdnNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL}
        ,
        {"11", "g9922tcmIsdnOverlapped", localize.findLocalString("RES_TransMode_Standard_g9922tcmIsdnOverlapped"), "C",
        "EC", localize.findLocalString("RES_TransMode_Description_g9922tcmIsdnOverlapped"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"12", "g9921tcmIsdnSymmetric", localize.findLocalString("RES_TransMode_Standard_g9921tcmIsdnSymmetric"), "H",
        "--", localize.findLocalString("RES_TransMode_Description_g9921tcmIsdnSymmetric"), TRANSMODE_CATEGORY_ADSL}
        ,
        {"13", "reserved1", localize.findLocalString("RES_TransMode_Standard_reserved1"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved1"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"14", "reserved2", localize.findLocalString("RES_TransMode_Standard_reserved2"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved2"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"15", "reserved3", localize.findLocalString("RES_TransMode_Standard_reserved3"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved3"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"16", "reserved4", localize.findLocalString("RES_TransMode_Standard_reserved4"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved4"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"17", "reserved5", localize.findLocalString("RES_TransMode_Standard_reserved5"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved5"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"18", "g9923PotsNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9923PotsNonOverlapped"), "A",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9923PotsNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"19", "g9923PotsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9923PotsOverlapped"), "A", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923PotsOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"20", "g9923IsdnNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9923IsdnNonOverlapped"), "B",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9923IsdnNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"21", "g9923IsdnOverlapped", localize.findLocalString("RES_TransMode_Standard_g9923IsdnOverlapped"), "B", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923IsdnOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"22", "reserved6", localize.findLocalString("RES_TransMode_Standard_reserved6"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved6"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"23", "reserved7", localize.findLocalString("RES_TransMode_Standard_reserved7"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved7"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"24", "g9924potsNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9924potsNonOverlapped"), "A",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9924potsNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"25", "g9924potsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9924potsOverlapped"), "A", "EC",
        localize.findLocalString("RES_TransMode_Description_g9924potsOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"26", "reserved8", localize.findLocalString("RES_TransMode_Standard_reserved8"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved8"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"27", "reserved9", localize.findLocalString("RES_TransMode_Standard_reserved9"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved9"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"28", "g9923AnnexIAllDigNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexIAllDigNonOverlapped"), "I", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexIAllDigNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"29", "g9923AnnexIAllDigOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexIAllDigOverlapped"), "I", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexIAllDigOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"30", "g9923AnnexJAllDigNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexJAllDigNonOverlapped"), "J", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexJAllDigNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"31", "g9923AnnexJAllDigOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexJAllDigOverlapped"), "J", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexJAllDigOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"32", "g9924AnnexIAllDigNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9924AnnexIAllDigNonOverlapped"), "I", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9924AnnexIAllDigNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"33", "g9924AnnexIAllDigOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9924AnnexIAllDigOverlapped"), "I", "EC",
        localize.findLocalString("RES_TransMode_Description_g9924AnnexIAllDigOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"34", "g9923AnnexLMode1NonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexLMode1NonOverlapped"), "L", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexLMode1NonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"35", "g9923AnnexLMode2NonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexLMode2NonOverlapped"), "L", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexLMode2NonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"36", "g9923AnnexLMode3Overlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexLMode3Overlapped"), "L", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexLMode3Overlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"37", "g9923AnnexLMode4Overlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexLMode4Overlapped"), "L", "EC",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexLMode4Overlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"38", "g9923AnnexMPotsNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9923AnnexMPotsNonOverlapped"), "M", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9923AnnexMPotsNonOverlapped"), TRANSMODE_CATEGORY_ADSL2}
        ,
        {"39", "g9923AnnexMPotsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9923AnnexMPotsOverlapped"),
        "M", "EC", localize.findLocalString("RES_TransMode_Description_g9923AnnexMPotsOverlapped"),
        TRANSMODE_CATEGORY_ADSL2}
        ,
        {"40", "g9925PotsNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9925PotsNonOverlapped"), "A",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9925PotsNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"41", "g9925PotsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9925PotsOverlapped"), "A", "EC",
        localize.findLocalString("RES_TransMode_Description_g9925PotsOverlapped"), TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"42", "g9925IsdnNonOverlapped", localize.findLocalString("RES_TransMode_Standard_g9925IsdnNonOverlapped"), "B",
        "FDM", localize.findLocalString("RES_TransMode_Description_g9925IsdnNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"43", "g9925IsdnOverlapped", localize.findLocalString("RES_TransMode_Standard_g9925IsdnOverlapped"), "B", "EC",
        localize.findLocalString("RES_TransMode_Description_g9925IsdnOverlapped"), TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"44", "reserved10", localize.findLocalString("RES_TransMode_Standard_reserved10"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved10"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"45", "reserved11", localize.findLocalString("RES_TransMode_Standard_reserved11"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved11"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"46", "g9925AnnexIAllDigNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9925AnnexIAllDigNonOverlapped"), "I", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9925AnnexIAllDigNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"47", "g9925AnnexIAllDigOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9925AnnexIAllDigOverlapped"), "I", "EC",
        localize.findLocalString("RES_TransMode_Description_g9925AnnexIAllDigOverlapped"), TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"48", "g9925AnnexJAllDigNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9925AnnexJAllDigNonOverlapped"), "J", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9925AnnexJAllDigNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"49", "g9925AnnexJAllDigOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9925AnnexJAllDigOverlapped"), "J", "EC",
        localize.findLocalString("RES_TransMode_Description_g9925AnnexJAllDigOverlapped"), TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"50", "g9925AnnexMPotsNonOverlapped",
        localize.findLocalString("RES_TransMode_Standard_g9925AnnexMPotsNonOverlapped"), "M", "FDM",
        localize.findLocalString("RES_TransMode_Description_g9925AnnexMPotsNonOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"51", "g9925AnnexMPotsOverlapped", localize.findLocalString("RES_TransMode_Standard_g9925AnnexMPotsOverlapped"),
        "M", "EC", localize.findLocalString("RES_TransMode_Description_g9925AnnexMPotsOverlapped"),
        TRANSMODE_CATEGORY_ADSL2PLUS}
        ,
        {"52", "reserved12", localize.findLocalString("RES_TransMode_Standard_reserved12"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved12"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"53", "reserved13", localize.findLocalString("RES_TransMode_Standard_reserved13"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved13"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"54", "reserved14", localize.findLocalString("RES_TransMode_Standard_reserved14"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved14"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"55", "reserved15", localize.findLocalString("RES_TransMode_Standard_reserved15"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved15"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"56", "g9932AnnexA", localize.findLocalString("RES_TransMode_Standard_g9932AnnexA"), "A (North America)", "--",
        localize.findLocalString("RES_TransMode_Description_g9932AnnexA"), TRANSMODE_CATEGORY_VDSL2}
        ,
        {"57", "g9932AnnexB", localize.findLocalString("RES_TransMode_Standard_g9932AnnexB"), "B (Europe)", "--",
        localize.findLocalString("RES_TransMode_Description_g9932AnnexB"), TRANSMODE_CATEGORY_VDSL2}
        ,
        {"58", "g9932AnnexC", localize.findLocalString("RES_TransMode_Standard_g9932AnnexC"), "C (Japan)", "--",
        localize.findLocalString("RES_TransMode_Description_g9932AnnexC"), TRANSMODE_CATEGORY_VDSL2}
        ,
        {"59", "reserved16", localize.findLocalString("RES_TransMode_Standard_reserved16"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved16"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"60", "reserved17", localize.findLocalString("RES_TransMode_Standard_reserved17"), "--", "--",
        localize.findLocalString("RES_TransMode_Description_reserved17"), TRANSMODE_CATEGORY_RESERVED}
        ,
        {"61", "g9935AnnexA", localize.findLocalString("RES_TransMode_Standard_g9935AnnexA"), "A (North America)", "--",
        localize.findLocalString("RES_TransMode_Description_g9935AnnexA"), TRANSMODE_CATEGORY_VDSL2}
        ,
        {"62", "g9935AnnexB", localize.findLocalString("RES_TransMode_Standard_g9935AnnexB"), "B (Europe)", "--",
        localize.findLocalString("RES_TransMode_Description_g9935AnnexB"), TRANSMODE_CATEGORY_VDSL2}
        ,
        {"63", "g9935AnnexC", localize.findLocalString("RES_TransMode_Standard_g9935AnnexC"), "C (Japan)", "--",
        localize.findLocalString("RES_TransMode_Description_g9935AnnexC"), TRANSMODE_CATEGORY_VDSL2}
    };

//****** 代码段: 工具方法 *******************************************************************************/

    /**
     * 由一系列传输模式描述字符串得到BitMap
     * @param descSeries String 格式为：Standard1 - Description1,Standard2 － Description2,...
     * @return BitMap
     */
    public static BitMap getBitMapFromDescSeries(String descSeries)
    {
        BitMap bmTransMode = new BitMap(new byte[]
                                        {0, 0, 0, 0, 0, 0, 0, 0});

        if(descSeries == null || descSeries.length() == 0)
        {
            return bmTransMode;
        }

        for(int i = 0; i < TRANSMODES.length; i++)
        {
            if(descSeries.indexOf(TRANSMODES[i][5]) > -1)
            {
                bmTransMode.setBitAt(i + 1);
            }
        }

        return bmTransMode;
    }

    /**
     * 根据传输模式BitMap获取以逗号分隔的传输模式描述字符串
     * @param bmTransMode BitMap
     * @return String 格式为：Standard1 - Description1,Standard2 － Description2,...
     */
    public static String getDescSeriesFromBitMap(BitMap bmTransMode)
    {
        String strTransMode = "";

        if(bmTransMode == null || bmTransMode.getBitMapLen() == 0)
        {
            return strTransMode;
        }

        for(int i = 0; i < bmTransMode.getBitMapLen(); i++)
        {
            if(bmTransMode.getBitAt(i + 1) == 1)
            {
                strTransMode += TRANSMODES[i][2] + " - " + TRANSMODES[i][5] + ";";
            }
        }

        if(!strTransMode.equals(""))
        {
            strTransMode = strTransMode.substring(0, (strTransMode.length() - 1));
        }

        return strTransMode;
    }
    
    public static String[] getAnnexFromBitMap(BitMap bmTransMode)
    {
        if(bmTransMode == null || bmTransMode.getBitMapLen() == 0)
        {
            return new String[0];
        }

        ArrayList<String> arrTransMode = new ArrayList<String>();
        for(int i = 0; i < bmTransMode.getBitMapLen(); i++)
        {
            if(bmTransMode.getBitAt(i + 1) == 1)
            {
                arrTransMode.add(TRANSMODES[i][0]);
            }
        }

        return (String[])arrTransMode.toArray(new String[arrTransMode.size()]);
    }

    /**
     * 由一系列传输模式缩写字符串得到BitMap
     * @param abbrevSeries String 格式为：Abbreviation1,Abbreviation1,...
     * @return BitMap
     */
    public static BitMap getBitMapFromAbbrevSeries(String abbrevSeries)
    {
        BitMap bmTransMode = new BitMap(new byte[]
                                        {0, 0, 0, 0, 0, 0, 0, 0});

        if(abbrevSeries == null || abbrevSeries.length() == 0)
        {
            return bmTransMode;
        }

        for(int i = 0; i < TRANSMODES.length; i++)
        {
            if(abbrevSeries.indexOf(TRANSMODES[i][1]) > -1)
            {
                bmTransMode.setBitAt(i + 1);
            }
        }

        return bmTransMode;
    }

    /**
     * 获取所有非Reserved的传输模式BitMap
     * @return BitMap
     */
    public static BitMap getBitMapOfAllNotReservedTransMode()
    {
        BitMap bmTransMode = new BitMap(new byte[]
                                        {0, 0, 0, 0, 0, 0, 0, 0});

        for(int i = 0; i < TRANSMODES.length; i++)
        {
            if(!TRANSMODES[i][6].equals(TRANSMODE_CATEGORY_RESERVED))
            {
                bmTransMode.setBitAt(i + 1);
            }
        }

        return bmTransMode;
    }
}

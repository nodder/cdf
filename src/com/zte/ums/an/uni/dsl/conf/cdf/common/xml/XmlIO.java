package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>文件名称: XmlIO</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期: 2011年11月23日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi_10118495
 */
public class XmlIO
{
    //****** 代码段: 变量定义 **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private File xmlFileName = null;
    private Document cloneDocument = null;
    private Element configRoot = null;

    //****** 代码段: 公共方法 **********************************************************************/

    public XmlIO(File xmlFile)
    {
        this.xmlFileName = xmlFile;
        
        try
        {
            init();
        }
        catch(Exception ex)
        {
           LogPrint.logError(logger, "", ex);
        }
    }
    
    private void init() throws Exception
    {
        SAXBuilder builder = new SAXBuilder(false);
        Document originalDocument = builder.build(xmlFileName);
        cloneDocument = (Document)originalDocument.clone();
        configRoot = cloneDocument.getRootElement();
    }

    public Element getRootElement()
    {
        return configRoot;
    }

    /**
     * 根据输入的多级段名，获取指定属性组的元素对象
     * 如:getElement(String[]{agent,database})返回database属性组Element
     * @param segName 段名称
     * @return 属性元素,注意:该元素可能还有child或children
     */
    public Element getElement(String[] segNames)
    {
        Element segElement = configRoot;
        int size = segNames.length;
        for(int i = 0; i < size; i++)
        {
            segElement = segElement.getChild(segNames[i]);
            if(segElement == null)
            {
                return null;
            }
        }
        return segElement;
    }

    /**
     * 根据段名和属性名获取属性值
     * 如:getAttribute({"database","host"})
     * @param allNames 段名+属性名
     * @return 注意:allName[size]为属性名,之前都是Element名
     */
    public String getAttribute(String[] allNames)
    {
        String[] segNames = new String[allNames.length - 1];
        System.arraycopy(allNames, 0, segNames, 0, segNames.length);
        Element e = getElement(segNames);
        return (e == null) ? null : e.getAttributeValue(allNames[segNames.length]);
    }

    /**
     * 根据段名和属性名数组设置属性值
     * 如:setAttribute(String[]{"database","host"},"value")
     * @param allNames 段名+属性名
     * @return
     */
    public void setAttributeValue(String[] allNames, String value)
    {
        String[] segNames = new String[allNames.length - 1];
        System.arraycopy(allNames, 0, segNames, 0, segNames.length);
        Element e = getElement(segNames);
        Attribute attribute = e.getAttribute(allNames[segNames.length]);
        attribute.setValue(value);
    }

    public void setAttributeValue(Attribute attribute, String value)
    {
        attribute.setValue(value);
    }

    public void appendAttributeValue(Attribute attribute, String append, String seperator)
    {
        String oldValue = attribute.getValue();

        String newValue = null;
        if(oldValue.equalsIgnoreCase(""))
        {
            newValue = append;
        }
        else
        {
            newValue = oldValue + "," + append;
        }

        attribute.setValue(newValue);

    }

    public boolean save()
    {
        XMLOutputter outputter = new XMLOutputter();
        outputter.setEncoding(System.getProperty("file.encoding"));
        outputter.setTextTrim(false);
        outputter.setIndent("    ");
        outputter.setNewlines(true);
        outputter.setTextNormalize(true);
        outputter.setExpandEmptyElements(false);
        
        FileOutputStream fs = null;

        try
        {
            fs = new FileOutputStream(this.xmlFileName);

            outputter.output(configRoot.getDocument(), fs);;
            
            return true;
        }
        catch(IOException ex)
        {
            LogPrint.logError(logger, "", ex);
        }
        finally
        {
            CdfUtil.closeFileOutputStream(fs);
        }

        return false;
    }
    
    public static ArrayList<Element> getAllElements(Element startElement, String... elementsName)
    {
        Element e = startElement;
        
        for(int i = 0; i < elementsName.length - 1; i++)
        {
            if(e != null)
            {
                e = e.getChild(elementsName[i]);
            }
        }
        
        ArrayList<Element> rtnElements = new ArrayList<Element>();
        
        if(e != null)
        {
            Iterator it = e.getChildren(elementsName[elementsName.length - 1]).iterator();
            while(it.hasNext())
            {
                rtnElements.add((Element)it.next());
            }
        }
        
        return rtnElements;
    }
    
    public static ArrayList<String> getAllElementsValue(String attributeName, Element startElement, String... elementsName)
    {
        ArrayList<Element> elements = getAllElements(startElement, elementsName);
        
        ArrayList<String> attributeValueList = new ArrayList<String>();
        for(Element e : elements)
        {
            attributeValueList.add(e.getAttributeValue(attributeName));
        }
        
        return attributeValueList;
    }
}

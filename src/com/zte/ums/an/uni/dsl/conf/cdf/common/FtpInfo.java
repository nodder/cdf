package com.zte.ums.an.uni.dsl.conf.cdf.common;

import java.io.Serializable;

/**
 * <p>文件名称: FtpInfo.java</p>
 * <p>文件描述: FTP参数消息类</p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月15日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author  jingxueshi_10118495
 */
public class FtpInfo implements Serializable, Cloneable
{
    //****** 代码段: 变量定义 **********************************************************************/

    /** ftp地址 */
    public String ftpServerIp = null;

    /** ftp用户 */
    public String ftpUser = null;

    /** ftp用户密码 */
    public String ftpUserPasswd = null;

    /** ftp用户主目录 */
    public String ftpRootDir = null;

    /** 是否使用SFTP协议，true表示SFTP，false表示FTP */
    public boolean isSFTP;

    //****** 代码段: 公共方法 **********************************************************************/

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("  ftpIp                 = " + this.ftpServerIp + "\n");
        sb.append("  ftpUser               = " + this.ftpUser + "\n");
        sb.append("  ftpUserPasswd         = " + this.ftpUserPasswd + "\n");
        sb.append("  ftpRootDir            = " + this.ftpRootDir + "\n");
        sb.append("  isSFTP                = " + this.isSFTP + "\n");
        return sb.toString();
    }

    public Object clone()
    {
        FtpInfo co = null;
        try
        {
            co = (FtpInfo)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return co;
    }
}

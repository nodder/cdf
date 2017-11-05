package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.IOException;

/**
 * <p>文件名称: FTPClient</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2005-2015</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-12-12</p>
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
public interface FTPClient
{
    public void connect(String serverIP, String userName, String password) throws IOException;
    
    public void disConnect() throws IOException;
    
    /** 显示在FTP客户端(如FileZilla)上的初始目录，始终以"/"结尾 */
    public String getDisplayedRootDir() throws IOException;

    public void cd(String dir) throws IOException;

    /** 获取当前目录，该目录以"/"结尾 */
    public String pwd() throws IOException;

    /** 创建目录,并会检查目录是否成功创建 */
    public void mkDirs(String dir) throws IOException;

    /** 删除空目录，并會檢查目錄是否成功刪除 */
    public void delDir(String dir) throws IOException;
    
    /** 删除单个文件 */
    public void delFile(String dir) throws IOException;

    /** 本地目录下的单个文件，上传到FTP远程文件夹下 */
    public void upload(String remotePath, String localFile) throws IOException;
}

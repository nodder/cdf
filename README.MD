![image](https://github.com/nodder/cdf/blob/master/images/1.jpg)

## 说明
CDF（Collection Dispatch Function）是一套网元性能采集工具，周期性地从指定的多个网元获取性能和配置数据，生成csv数据报表并存放在指定路径。
CDF分为主分发服务器、分采集服务器和报表服务器，三种服务器支持分布式部署，也支持同时部署在一台机子上。

- 主分发服务器（Dispatch Server）：分发采集任务，同时为报表服务器提供分采集服务器信息。
- 分采集服务器（Collect Server，或者Sub-collect Server）：周期性通过FTP从多个网元采集CSV性能文件，筛选、解析、存入数据库。分采集服务器可以有多个。
- 报表服务器（Report Server）：周期性从分采集数据库读取并生成报表。

CDFv1.x版本，将主分发、分采集和报表服务器软件集中为同一个软件，并只部署在一台机器上。

## CDFv1.7
### 版本特性 
CDFv1.7支持SNMP网元设备作为DPU管理的采集需求，包括如下参数（参数根据运营商要求定制，不支持修改）：
Downstream/UpStream Attainable Net Data Rate
Downstream/Upstream Actual Net Data Rate
15 Minutes Full Initializations
Power Consumption
Power Supply Time

其中最后两个参数需要网元支持反向供电，否则数据为空。

### 准备工作
	确保CDF运行的主机上启动FTP服务，FTP路径拥有写权限
	确保CDF和网管数据库联通，网管上创建有SNMP网元设备网元
	确保SNMP网元设备支持反向供电，否则采集到的相关数据为空
### CDF部署和运行
-  Windows环境
目的：
1）	周期性通过FTP从多个网元采集CSV性能文件，筛选、解析、存入数据库；
2）	周期性从数据库读取，翻译、合并为报表。
操作对象：CDF工具
操作步骤：
1）	切换到CDF安装根目录，运行run.bat，打开CDF菜单

	注：可以选择“How to run correctly？”选项，查看help信息。
2）	选择“Configuration”选项，配置CDF参数。
3）	选择“Run Configuration Tests”选项，当测试结果为“Congratulations”（如下图所示），表示参数配置正常，进行下一步。 否则根据错误提示进行自查，并重新执行“Configuration”。
 
4）	选择“Start CDF”，启动程序，等待报表生成。报表的生成路径和生成时间均在第二步“Configuration ”中配置。 CDF分为3个服务器，具体见“附录说明”。
 
输入“1”后回车，CDF启动，启动成功后包含Dispatch Server、Sub-collect Server和Report Server三个窗口，如下所示。
 
- Salaris环境
目的：
1）	周期性通过FTP从多个网元采集CSV性能文件，筛选、解析、存入数据库；
2）	周期性从数据库读取，翻译、合并为报表。
操作对象：CDF工具
操作步骤：
1）	切换到安装根目录，运行run.bat（Solaris下执行run.sh），打开CDF菜单
 
	注：可以选择“How to run correctly？”选项，查看help信息。
2）	选择“Configuration”选项，配置CDF参数。
3）	选择“Run Configuration Tests”选项，当测试结果为“Congratulations”（如下图所示），表示参数配置正常，进行下一步。 否则根据错误提示进行自查，并重新执行“Configuration”。
 
 
4）	选择“Start CDF”，启动程序，等待报表生成。报表的生成路径和生成时间均在第二步“Configuration ”中配置。 CDF分为3个服务器，具体见“附录说明”。

输入“1”后回车，CDF启动，启动后自动退回到命令提示符。此时CDF是远程方式启动，即关闭shell连接也不会导致CDF退出。
 
### 监测

在CDF运行期间，可以通过more命令实时查看CDF各个server的运行状态。
bash-3.00$ more remoteDispatchServer.log   （主分发服务器日志）
bash-3.00$ more remoteSubCollectServer.log  （分采集服务器日志）
bash-3.00$ more remoteReportServer.log	（报表日志）
 
### 运行日志
- 主分发服务器
主分发服务器启动时会从网管服务器获取待采集网元，当分采集服务器启动时会从主分发服务器获取这些网元任务。
 

- 分采集服务器
分采集服务器从网管获取待采集网元后，会检查网元上是否配置了采集xml配置文件，如果没有配置，则在当前采集周期不进行采集动作，而是下发xml配置，如下图所示。
 
xml配置下发成功后，分采集服务器会在下一个周期进行采集动作。

如果xml已经配置，则直接进行采集动作，如下图所示：
 

采集成功后进行解析，解析成功后会有如下提示，表明数据已经保存到CDF数据库。
 

- 报表服务器
报表服务器定时从CDF数据库获取数据并生成报表。报表生成后会有如下提示，可在对应目录中找到生成的报表。
 
### 停止CDF
- Windows环境：
手工关闭Dispatch、Sub-collect、Report Server这三个窗口即可。
- Solaris环境：
	运行sh run.sh打开CDF Main Menu后，选择“2. Shutdown CDF”并回车。
注：“2. Shutdown CDF”只能关闭当前用户运行的CDF，具体参考附录2 “Start CDF失败处理”
### 附录
1.	如何判断CDF程序已经启动？
执行“Run Configuration Tests”，出现下述提示则表示正在运行。
 
2.	Start CDF失败处理
执行“Start CDF”出现如下提示，则需要先执行“Shutdown CDF”，再重新执行“Start CDF”。
 
如果CDF不是由当前用户启动的，那么执行“Shutdown CDF”并不能真正关闭CDF，此时有两种解决办法：
	切换到启动CDF的用户，再执行“Shutdown”
	先执行ps -ef | grep uni.dsl.conf命令查看cdf进程，再执行kill -9命令强制杀死进行。如下。


### 完成时间：2013

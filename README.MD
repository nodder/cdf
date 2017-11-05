![image](https://github.com/nodder/cdf/blob/master/images/1.jpg)

CDF（Collection Dispatch Function）是一套网元性能采集工具，周期性地从指定的多个网元获取性能和配置数据，生成csv数据报表并存放在指定路径。
CDF分为主分发服务器、分采集服务器和报表服务器，三种服务器支持分布式部署，也支持同时部署在一台机子上。

- 主分发服务器（Dispatch Server）：分发采集任务，同时为报表服务器提供分采集服务器信息。
- 分采集服务器（Collect Server，或者Sub-collect Server）：周期性通过FTP从多个网元采集CSV性能文件，筛选、解析、存入数据库。分采集服务器可以有多个。
- 报表服务器（Report Server）：周期性从分采集数据库读取并生成报表。

CDFv1.x版本，将主分发、分采集和报表服务器软件集中为同一个软件，并只部署在一台机器上。

完成时间：2013

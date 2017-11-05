@echo off
title CDF Sub-Collect Server

set JDK_RELATIVE=jdk\jdk\windows

set JAVA_HOME=..\%JDK_RELATIVE%
if not EXIST %JAVA_HOME% (
    set JAVA_HOME=..\..\..\%JDK_RELATIVE%
)

set JAVA=%JAVA_HOME%\bin\java

set MAIN_LIB=..\lib
set classpath=%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%2\lib\tools.jar;%MAIN_LIB%\jdom.jar;%MAIN_LIB%\jtds-1.2.jar;%MAIN_LIB%\log4j.jar;%MAIN_LIB%\msbase.jar;%MAIN_LIB%\ojdbc14.jar;%MAIN_LIB%\mssqlserver.jar;%MAIN_LIB%\msutil.jar;%MAIN_LIB%\mysql-connector-java-5.0.3-bin.jar;%MAIN_LIB%\zxnm01-uni-dsl-cdf-svr.jar;%MAIN_LIB%\backport-util-concurrent.jar;%MAIN_LIB%\ums-n3common-api.jar;%MAIN_LIB%\uep-api.jar;%MAIN_LIB%\uep-protocol-snmp.jar;%MAIN_LIB%\jboss-j2ee.jar;%MAIN_LIB%\uep-util-toolkit.jar;%MAIN_LIB%\JSQLConnect.jar;%MAIN_LIB%\ums-an-dsl-base-base.jar;%MAIN_LIB%\ums-an-uni-framework-common.jar;%MAIN_LIB%\ums-an-commonsh-base-emf.jar;%MAIN_LIB%\dx.jar;%MAIN_LIB%\jdsserver.jar;%MAIN_LIB%\jds.jar;%MAIN_LIB%\ojdbc14.jar;%MAIN_LIB%\ums-an-commonsh-base-entity-common.jar;%MAIN_LIB%\usf-api.jar;%MAIN_LIB%\cdf-svr-mock.jar;

rem set JAVA_OPTS=-classic -Xdebug -Xnoagent -XX:+PrintGCDetails -verbose:gc -Xloggc:..\log\gc.log -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8789,server=y,suspend=n %JAVA_OPTS%

@echo ===========CDF Sub-Collect Server is running... ============= 

%JAVA% %JAVA_OPTS% -Xms384m -Xmx768m  com.zte.ums.an.uni.dsl.conf.cdf.collect.CollectServer 1
pause
# !/bin/sh
JDK_RELATIVE=./../jdk/jdk/

if [ -d "$JDK_RELATIVE" ]; then
    JDK_RELATIVE=`cd $JDK_RELATIVE; pwd`
else
    JDK_RELATIVE=`cd ../../$JDK_RELATIVE; pwd`
fi 

OSNAME=`uname`
if [ $OSNAME = "Linux" ] ; then
    JAVA_HOME=`cd "$JDK_RELATIVE"/linux/; pwd`
fi

if [ $OSNAME = "SunOS" ] ; then
    JAVA_HOME=`cd "$JDK_RELATIVE"/solaris/; pwd`
fi

if [ $OSNAME = "AIX" ] ; then
    JAVA_HOME=`cd "$JDK_RELATIVE"/aix/; pwd`
fi

MAIN_LIB=../../lib
export MAIN_LIB 

CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/dt.jar
CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/tools.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/jdom.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/jtds-1.2.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/log4j.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/msbase.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ojdbc14.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/mssqlserver.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/msutil.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/mysql-connector-java-5.0.3-bin.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/zxnm01-uni-dsl-cdf-svr.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/backport-util-concurrent.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/uep-api.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/jboss-j2ee.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/uep-util-toolkit.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/JSQLConnect.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/dx.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/jdsserver.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/jds.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ojdbc14.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ums-an-commonsh-base-entity-common.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ums-an-commonsh-base-emf.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ums-an-uni-framework-common.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ums-an-dsl-base-base.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/usf-api.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/ums-n3common-api.jar
CLASSPATH=$CLASSPATH:$MAIN_LIB/uep-protocol-snmp.jar

export CLASSPATH


echo =============== Update DB for EMS CDF GUI ===================

$JAVA_HOME/bin/java -Xms256m -Xmx512m com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb.KPNInitDbTables4EmsGui


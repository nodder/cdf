# !/bin/sh

PROCLIST=`ps -aux | grep com.zte.ums.an.uni.dsl.conf.cdf.collect.CollectServer | grep java | awk '{print $2}'`

for PID in ${PROCLIST} ; 
do
    echo "kill -9 ${PID}"
    kill -9 ${PID}
done

echo Sub-collect Server is stopped.

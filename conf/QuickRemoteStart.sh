# !/bin/sh

cd server_dispatch

sh RemoteDispatchServer.sh
echo wait...
sleep 5

cd ../server_subcollect
sh RemoteSubCollectServer.sh

cd ../server_report
sh RemoteReportServer.sh

sleep 5
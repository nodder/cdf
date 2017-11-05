# !/bin/sh

cd server_dispatch
sh RemoteStopDispatchServer.sh

cd ../server_subcollect
sh RemoteStopSubCollectServer.sh

cd ../server_report
sh RemoteStopReportServer.sh

# !/bin/sh

cd server_dispatch
sh RemoteStopDispatchServer_RedHat.sh

cd ../server_subcollect
sh RemoteStopSubCollectServer_RedHat.sh

cd ../server_report
sh RemoteStopReportServer_RedHat.sh

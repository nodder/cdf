@echo off

cd server_dispatch
start DispatchServer.bat

ping /n 8 127.0.0.1 >nul

cd ..\server_subcollect
start SubCollectServer.bat

cd ..\server_report
start ReportServer.bat
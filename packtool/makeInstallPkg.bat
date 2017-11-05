@echo off

set UMS_SERVER=..\..\..\ums-server
set UMS_BIN=..\..\..\bin

set PARERENT_DIR=packtool
set OPERATOR_DIR=operators
set TMP_TXT=temp.txt
set ZIP_FILE=
set VERSION=
set SELECT_OPERATOR=

set DEFAULT_OPRATOR=KPN

if EXIST %UMS_SERVER% (
    if EXIST %UMS_BIN% (
       goto select_operator
    )  
) 


echo Error: Invalid directory.
goto endByError

:endByError
pause
exit

:select_operator
setlocal enabledelayedexpansion

set operators=%DEFAULT_OPRATOR%
for /f  %%a in ('dir /b "%OPERATOR_DIR%"') do (
set "operators=!operators!,%%a"
)

set /p SELECT_OPERATOR= Please input operator (%operators%) [%DEFAULT_OPRATOR%] :

if "%SELECT_OPERATOR%"=="" (
set SELECT_OPERATOR=%DEFAULT_OPRATOR%
goto show_select_operator)

if "%SELECT_OPERATOR%"=="%DEFAULT_OPRATOR%" (
goto show_select_operator)

:check_select_operator
for /f  %%i in ('dir /b %OPERATOR_DIR%') do (
    if "%SELECT_OPERATOR%"=="%%i" goto show_select_operator
)

echo Operator "%SELECT_OPERATOR%" is invalid.
goto endByError

:show_select_operator
echo Selected operator is %SELECT_OPERATOR%
echo.

:input_version
set /p VERSION=<..\conf\release

:get_date_str
set dateStr=
VER | find "Version"
if "%errorlevel%"=="0" (goto setDateStr_En) else (goto setDateStr_Chi)

:setDateStr_Chi
set dateStr=%date:~0,4%%date:~5,2%%date:~8,2%
goto get_zip_name

:setDateStr_En
set dateStr=%date:~10,4%%date:~4,2%%date:~7,2%
goto get_zip_name

:get_zip_name
set ZIP_FILE=CDF-%VERSION%-%SELECT_OPERATOR%-%dateStr%.zip

echo The CDF installation packet name is "%ZIP_FILE%"

echo.
echo Press any key to start packet ...& pause > nul

:del_exist_zip
if exist %ZIP_FILE% (
    del %ZIP_FILE% >nul 2>nul    
    
    if exist %ZIP_FILE% (
        echo Cannot delete %ZIP_FILE%
        goto endByError
    ) else (
        echo.
        echo Existed zip file "%ZIP_FILE%" has been deleted.
    )
)

echo.

:start_packet
echo ==========Start packeting CDF version to "%ZIP_FILE%"============
cd ..
for /f  %%i in ('dir /B') do (
   if not %%i == %PARERENT_DIR% ( 
        if not "%%i" == "testsrc" (
            if not "%%i" == "src" (
               echo zipping %%i ...
               .\%PARERENT_DIR%\zip.exe -q %PARERENT_DIR%\%ZIP_FILE% -r %%i
   )))
)
cd %PARERENT_DIR%

:import_operator
if not "%SELECT_OPERATOR%"=="%DEFAULT_OPRATOR%" (
    echo.
    echo ==========Import data for operator [%SELECT_OPERATOR%]============
    cd %OPERATOR_DIR%\%SELECT_OPERATOR%
    for /f  %%i in ('dir /B ') do (
       echo import %%i ...
       ..\..\zip.exe -q ..\..\%ZIP_FILE% -r %%i
    )
)

echo ==========Finished.============
echo.

echo Complete to make CDF installation packet "%ZIP_FILE%".
echo.
pause


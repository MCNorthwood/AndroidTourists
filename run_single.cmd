@echo off
if [%1]==[] goto usage
if not exist %1 goto usage
:: start emulator
call emulator_start
:: build app
call build_single %1
:: wait for emulator to be on
call emulator_wait
echo ----------------------------
echo running %~n1
echo ----------------------------
set ACTIVITY=.MainActivity
set APK_LOCATION=%1/app/build/outputs/apk/app-debug.apk
:: retrive package name from manifest file
%AAPT% dump badging %APK_LOCATION% | findstr/r "package: name='[a-z]*' versionCode=" > package.tmp
for /f "tokens=1-3 delims='" %%A in (package.tmp) do SET PACKAGE=%%B
del /q package.tmp
:: echo %PACKAGE%
:: install app
%ADB% push %APK_LOCATION% /data/local/tmp/%PACKAGE%
%ADB% shell pm install -r "/data/local/tmp/%PACKAGE%"
:: launch default activity
%ADB% shell monkey -p "%PACKAGE%" -c android.intent.category.LAUNCHER 1
:: run monkey (check config.cmd to enable/disabled it)
if %RUNMONKEY%==Y %ADB% shell monkey -p "%PACKAGE%" -v %MONKEYRUNS%
:: other option if you want to start a specific ACTIVITY
:: %ADB% shell am start -n "%PACKAGE%/%PACKAGE%%ACTIVITY%" 
:: add the following line to start a specific activity
::-a android.intent.action.MAIN -c android.intent.category.LAUNCHER
goto:eof
:usage
echo usage: %0 projectname
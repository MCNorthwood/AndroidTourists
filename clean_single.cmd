@echo off
if [%1]==[] goto usage
if not exist %1 goto usage
call config
set GRADLE="%GRADLE_HOME%\bin\gradle"
if not exist %GRADLE% goto gradle-not-found
:: delete all versions except GRADLE_VER
for /D %%d in (%1\.gradle\*) do if "%%d" neq "%1\.gradle\%GRADLE_VER%" rmdir /s/q "%%d"
:: remove previous build folders
if exist %1\app\build rmdir /s/q %1\app\build
if exist %1\build rmdir /s/q %1\build
pushd %1
echo ----------------------------
echo cleaning %~n1
echo ----------------------------
call %GRADLE% clean
popd	
goto:eof
:usage
echo usage: %0 projectname
goto:eof
:gradle-not-found
echo Gradle was expected but not found on this location
echo GRADLE_HOME=%GRADLE_HOME%
echo Check your path and set the variable
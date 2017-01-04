@echo off
if [%1]==[] goto usage
if not exist %1 goto usage
set GRADLE="%GRADLE_HOME%\bin\gradle"
if not exist %GRADLE%.bat goto gradle-not-found
pushd %1
echo ----------------------------
echo building %~n1
echo ----------------------------
call %GRADLE% build
popd
goto:eof
:usage
echo usage: %0 projectname
:gradle-not-found
echo Gradle was expected but not found on this location
echo GRADLE_HOME=%GRADLE_HOME%
echo Check your path and set the variable
@echo off
if [%1]==[] goto usage
if not exist %1 goto usage
:: clean project
call clean_single %1
set USE7ZIP=N
:: use 7zip or jar (from JDK)
if %USE7ZIP%==Y (
set ZIPCMD="%ProgramW6432%\7-Zip\7z"
set ZIPARG1=u
set ZIPARG2=-tzip
) else (
set ZIPCMD="%JAVA_HOME%\bin\jar"
set ZIPARG1=-cMf
set ZIPARG2=
)
if exist "%1.zip" del/q "%1.zip"
%ZIPCMD% %ZIPARG1% %ZIPARG2% "%1.zip" "%1"
goto:eof
:usage
echo usage: %0 projectname
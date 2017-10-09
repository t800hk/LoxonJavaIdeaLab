@echo off

set JAVA_HOME=C:\Software\jdk18
set JPS_BIN=%JAVA_HOME%\bin\jps -lv

for /f "tokens=1" %%G IN ('%JPS_BIN% ^| grep JavaIdeaLab') DO set JAVA_PID=%%G

taskkill /F /PID %JAVA_PID%

del e:\java\JavaIdeaLab\wildfly-10.1.0.Final\standalone\log\server.log

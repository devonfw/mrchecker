@ECHO OFF

set SCRIPT_NAME=decrypt.bat
set EXECUTABLE_CLASS=org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI
set EXEC_CLASSPATH=jasypt-1.9.3.jar
if "%JASYPT_CLASSPATH%" == "" goto computeclasspath
set EXEC_CLASSPATH=%EXEC_CLASSPATH%;%JASYPT_CLASSPATH%

:computeclasspath
IF "%OS%" == "Windows_NT" setlocal ENABLEDELAYEDEXPANSION
FOR %%c in (%~dp0..\lib\*.jar) DO set EXEC_CLASSPATH=!EXEC_CLASSPATH!;%%c
IF "%OS%" == "Windows_NT" setlocal DISABLEDELAYEDEXPANSION

set JAVA_EXECUTABLE=java
if "%JAVA_HOME%" == "" goto execute
set JAVA_EXECUTABLE="%JAVA_HOME%\bin\java"

:execute
%JAVA_EXECUTABLE% -classpath %EXEC_CLASSPATH% %EXECUTABLE_CLASS% %SCRIPT_NAME% %*
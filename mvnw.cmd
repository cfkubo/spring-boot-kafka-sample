@echo off
rem Maven wrapper script for Windows

setlocal

set MAVEN_HOME=%~dp0\.mvn\wrapper\maven-wrapper.jar
set MAVEN_OPTS=%MAVEN_OPTS% -Dmaven.home=%MAVEN_HOME%

if not exist "%MAVEN_HOME%" (
    echo "Maven wrapper jar not found."
    exit /b 1
)

java -cp "%MAVEN_HOME%" org.apache.maven.wrapper.MavenWrapperMain %*
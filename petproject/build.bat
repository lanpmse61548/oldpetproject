call mvn clean install

@RD /S /Q "D:\apache-tomcat-8.5.20\webapps\petproject"
del "D:\apache-tomcat-8.5.20\webapps\petproject.war"

xcopy /s D:\MainProject\petproject\target\petproject.war  D:\apache-tomcat-8.5.20\webapps



@echo off
cd D:\apache-tomcat-8.5.20\bin\
start debug.bat

@echo off
cd D:\MainProject\petproject\

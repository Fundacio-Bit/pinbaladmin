

cmd /C mvn clean install 


if %errorlevel% EQU 0 (
	  xcopy /Y .\target\pinbaladmin-selenium.war D:\programs\tomcat9\webapps
)


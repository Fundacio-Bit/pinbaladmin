@echo off

echo optional parameters -Dcaib -Psqlgen

cmd /C mvn clean install -DskipTests %* 

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED PINBALADMIN_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set PINBALADMIN_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT EAR %PINBALADMIN_VERSIO% ---------

	  xcopy /Y pinbaladmin-ear\target\pinbaladmin.ear %PINBALADMIN_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn PINBALADMIN_DEPLOY_DIR apuntant al
	  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
	  echo    l'ear generat.
	  echo  =================================================================
	) 

)
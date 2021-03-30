#!/usr/bin/env bash

cat help.txt

env mvn $@ -DskipTests install 

if [ $? == 0 ]; then
  if [ "$PINBALADMIN_DEPLOY_DIR" == "" ];  then
    echo  =================================================================
    echo    Definex la variable d\'entorn PINBALADMIN_DEPLOY_DIR apuntant al
    echo    directori de deploy del JBOSS  i automaticament s\'hi copiara
    echo    l\'ear generat.
    echo  =================================================================  
  else
    if [ -f 'versio.txt' ]; then
	echo --------- COPIANT EAR `cat versio.txt` ---------
    else
	echo --------- COPIANT EAR ---------
    fi
    if [ -f './ear/target/pinbaladmin.ear' ]; then
      cp ./ear/target/pinbaladmin.ear $PINBALADMIN_DEPLOY_DIR
    else
      echo NO S\'HA TROBAT pinbaladmin.ear!
    fi
  fi
fi


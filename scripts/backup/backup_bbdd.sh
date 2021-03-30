
PGPASSWORD="pinbaladmin" pg_dump --inserts -U pinbaladmin -d pinbaladmin -p 5432 -h localhost > pinbaladmin_all.sql

sshpass -p otae.sistra scp pinbaladmin_all.sql  sistra@192.168.35.5:/dades/webs/sistra/BackupPinbalAdmin/$1

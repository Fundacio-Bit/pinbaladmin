

   



--SELECT PER PODER AFEGIR EL CAI A UNA INCIDENCIA

UPDATE
   pad_incidenciatecnica t1
SET
   caididentificadorconsulta = t2.caid
FROM
   (SELECT incidenciatecnicaid, substring(split_part(titol, 'CAI- ', 2),1,7) caid
   FROM pad_incidenciatecnica WHERE titol like '%CAI- %') AS t2
WHERE
   t1.incidenciatecnicaid = t2.incidenciatecnicaid
   and t1.incidenciatecnicaid = 50017

/*
SELECT 
   incidenciatecnicaid, 
   titol, 
   caididentificadorconsulta, 
   caidnumeroseguiment, 
   substring(split_part(titol, 'CAI- ', 2),1,7) caid
FROM 
   pad_incidenciatecnica 
WHERE 
   titol like '%CAI- %'
*/


---
--- 24/03/2023 -  Afegir data de tancament a la taula IncidenciaTecnica #105
---

ALTER TABLE pad_incidenciatecnica ADD COLUMN datafi timestamp without time zone;

UPDATE
     pad_incidenciatecnica AS t1
SET
    datafi = t2.dateLastEvent
FROM
    (SELECT it.incidenciatecnicaid, max(ev.dataevent) dateLastEvent
	FROM pad_incidenciatecnica it LEFT JOIN pad_event ev 
	ON (it.incidenciatecnicaid = ev.incidenciatecnicaid)
	WHERE it.estat = 2
	GROUP BY it.incidenciatecnicaid) as t2
WHERE
    t1.incidenciatecnicaid = t2.incidenciatecnicaid;





UPDATE
     pad_solicitud AS t1
SET
    datafi = t2.dateLastEvent
FROM
    (SELECT sol.solicitudid, max(ev.dataevent) dateLastEvent
	FROM pad_solicitud sol LEFT JOIN pad_event ev 
	ON (sol.solicitudid = ev.solicitudid)
	WHERE sol.estatid = 60
	AND sol.datafi is null
	GROUP BY sol.solicitudid) as t2
WHERE
    t1.solicitudid = t2.solicitudid;




---
--- 27/03/2023 -  Afegir un camp per saber qui ha estat el creador d'una incidencia o solicitud #86
---

-- INCIDENCIA
ALTER TABLE pad_incidenciatecnica
   ADD COLUMN operador character varying(100);

UPDATE pad_incidenciatecnica SET operador = creador;

ALTER TABLE pad_incidenciatecnica
   ALTER COLUMN operador SET NOT NULL;

-- SOLICITUD
ALTER TABLE pad_solicitud
   ADD COLUMN operador character varying(100);

UPDATE pad_solicitud SET operador = creador;

ALTER TABLE pad_solicitud
   ALTER COLUMN operador SET NOT NULL;




---
--- 29/03/2023 - Afegir camp a taula Event per guardar el destinatari en comentaris tramitadors publics #103
---

ALTER TABLE pad_event
   ADD COLUMN destinatari character varying(255);

ALTER TABLE pad_event
   ADD COLUMN destinatarimail character varying(255);


UPDATE 
   pad_event t0
SET 
   persona = t1.de,
   destinatari = t1.a,
   destinatarimail = t1.mail
FROM 
   (SELECT eventid, persona,
	split_part(split_part(persona, 'De ', 2), ' a ', 1) de, 
	split_part(split_part(persona, ' a ', 2), ' (', 1) a, 
	split_part(split_part(persona, '(', 2), ')', 1) mail,
	destinatari, destinatarimail
	FROM pad_event 
	WHERE tipus=1 
	AND persona like 'De %') AS t1
WHERE
   t0.eventid = t1.eventid;

   



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


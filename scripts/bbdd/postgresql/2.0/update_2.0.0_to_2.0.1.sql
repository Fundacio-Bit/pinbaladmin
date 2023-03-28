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
--INCIDENCIA
ALTER TABLE pad_incidenciatecnica
   ADD COLUMN operador character varying(100);

UPDATE pad_incidenciatecnica SET operador = creador;

ALTER TABLE pad_incidenciatecnica
   ALTER COLUMN operador SET NOT NULL;

--SOLICITUD
ALTER TABLE pad_solicitud
   ADD COLUMN operador character varying(100);

UPDATE pad_solicitud SET operador = creador;

ALTER TABLE pad_solicitud
   ALTER COLUMN operador SET NOT NULL;


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




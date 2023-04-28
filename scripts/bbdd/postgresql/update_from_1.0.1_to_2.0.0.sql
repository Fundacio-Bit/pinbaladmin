


INSERT INTO pad_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', true, 0);
INSERT INTO pad_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', true, 1);
INSERT INTO pad_idioma(idiomaid, nom, suportat, ordre) VALUES ('en', 'English', false, 2);




CREATE SEQUENCE pad_area_seq INCREMENT 1 START 50000;
ALTER TABLE pad_area ALTER COLUMN areaid SET DEFAULT nextval('pad_area_seq');

CREATE SEQUENCE pad_campformulari_seq INCREMENT 1 START 50000;
ALTER TABLE pad_campformulari ALTER COLUMN campformulariid SET DEFAULT nextval('pad_campformulari_seq');

CREATE SEQUENCE pad_campsolicitud_seq INCREMENT 1 START 50000;
ALTER TABLE pad_campsolicitud ALTER COLUMN campsolicitudid SET DEFAULT nextval('pad_campsolicitud_seq');

CREATE SEQUENCE pad_departament_seq INCREMENT 1 START 50000;
ALTER TABLE pad_departament ALTER COLUMN departamentid SET DEFAULT nextval('pad_departament_seq');

CREATE SEQUENCE pad_document_seq INCREMENT 1 START 50000;
ALTER TABLE pad_document ALTER COLUMN documentid SET DEFAULT nextval('pad_document_seq');

CREATE SEQUENCE pad_documentcedent_seq INCREMENT 1 START 50000;
ALTER TABLE pad_documentcedent ALTER COLUMN documentcedentid SET DEFAULT nextval('pad_documentcedent_seq');

CREATE SEQUENCE pad_documententitat_seq INCREMENT 1 START 50000;
ALTER TABLE pad_documententitat ALTER COLUMN documententitatid SET DEFAULT nextval('pad_documententitat_seq');

CREATE SEQUENCE pad_documentsolicitud_seq INCREMENT 1 START 50000;
ALTER TABLE pad_documentsolicitud ALTER COLUMN documentsolicitudid SET DEFAULT nextval('pad_documentsolicitud_seq');

CREATE SEQUENCE pad_email_seq INCREMENT 1 START 50000;
ALTER TABLE pad_email ALTER COLUMN emailid SET DEFAULT nextval('pad_email_seq');

CREATE SEQUENCE pad_entitat_seq INCREMENT 1 START 50000;
ALTER TABLE pad_entitat ALTER COLUMN entitatid SET DEFAULT nextval('pad_entitat_seq');

CREATE SEQUENCE pad_entitatservei_seq INCREMENT 1 START 50000;
ALTER TABLE pad_entitatservei ALTER COLUMN entitatserveiid SET DEFAULT nextval('pad_entitatservei_seq');

CREATE SEQUENCE pad_event_seq INCREMENT 1 START 50000;
ALTER TABLE pad_event ALTER COLUMN eventid SET DEFAULT nextval('pad_event_seq');

CREATE SEQUENCE pad_fitxer_seq INCREMENT 1 START 50000;
ALTER TABLE pad_fitxer ALTER COLUMN fitxerid SET DEFAULT nextval('pad_fitxer_seq');

CREATE SEQUENCE pad_formulari_seq INCREMENT 1 START 50000;
ALTER TABLE pad_formulari ALTER COLUMN formulariid SET DEFAULT nextval('pad_formulari_seq');

CREATE SEQUENCE pad_grupentitat_seq INCREMENT 1 START 50000;
ALTER TABLE pad_grupentitat ALTER COLUMN grupentitatid SET DEFAULT nextval('pad_grupentitat_seq');

CREATE SEQUENCE pad_grupentitatcedent_seq INCREMENT 1 START 50000;
ALTER TABLE pad_grupentitatcedent ALTER COLUMN grupentitatcedentid SET DEFAULT nextval('pad_grupentitatcedent_seq');

CREATE SEQUENCE pad_incidenciatecnica_seq INCREMENT 1 START 50000;
ALTER TABLE pad_incidenciatecnica ALTER COLUMN incidenciatecnicaid SET DEFAULT nextval('pad_incidenciatecnica_seq');

CREATE SEQUENCE pad_operador_seq INCREMENT 1 START 50000;
ALTER TABLE pad_operador ALTER COLUMN operadorid SET DEFAULT nextval('pad_operador_seq');

CREATE SEQUENCE pad_servei_seq INCREMENT 1 START 50000;
ALTER TABLE pad_servei ALTER COLUMN serveiid SET DEFAULT nextval('pad_servei_seq');

CREATE SEQUENCE pad_solicitud_seq INCREMENT 1 START 50000;
ALTER TABLE pad_solicitud ALTER COLUMN solicitudid SET DEFAULT nextval('pad_solicitud_seq');

CREATE SEQUENCE pad_solicitudservei_seq INCREMENT 1 START 50000;
ALTER TABLE pad_solicitudservei ALTER COLUMN id SET DEFAULT nextval('pad_solicitudservei_seq');

CREATE SEQUENCE pad_tiquet_seq INCREMENT 1 START 50000;
ALTER TABLE pad_tiquet ALTER COLUMN tiquetid SET DEFAULT nextval('pad_tiquet_seq');

CREATE SEQUENCE pad_traduccio_seq INCREMENT 1 START 50000;
ALTER TABLE pad_traduccio ALTER COLUMN traduccioid SET DEFAULT nextval('pad_traduccio_seq');
 
 
 DROP SEQUENCE pad_pinbaladmin_seq;
 
 
 
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
 
 
---
---  Aplicar Select multiple en entrades COMBOBOX #123
---

ALTER TABLE pad_solicitud  DROP CONSTRAINT pad_solicitud_estatsoli_fk;
ALTER TABLE pad_solicitudservei DROP CONSTRAINT pad_soliservei_estsolserv_fk;
ALTER TABLE pad_servei DROP CONSTRAINT pad_servei_estatserv_fk;


DROP TABLE pad_estatservei;
DROP TABLE pad_estatsolicitud;
DROP TABLE pad_estatsolicitudservei;

 
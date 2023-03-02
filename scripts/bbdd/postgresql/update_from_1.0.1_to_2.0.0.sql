


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
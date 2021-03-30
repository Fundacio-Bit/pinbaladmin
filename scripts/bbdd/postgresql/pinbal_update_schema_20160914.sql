CREATE TABLE pad_documententitat
(
  documententitatid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'::regclass),
  titol character varying(255) NOT NULL,
  descripcio text,
  entitatid bigint NOT NULL,
  fitxerid bigint,
  dataalta timestamp without time zone NOT NULL,
  CONSTRAINT pad_documententitat_pk PRIMARY KEY (documententitatid),
  CONSTRAINT pad_docent_entitat_fk FOREIGN KEY (entitatid)
      REFERENCES pad_entitat (entitatid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pad_docent_fitxer_fitxe_fk FOREIGN KEY (fitxerid)
      REFERENCES pad_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pad_documententitat
  OWNER TO pinbaladmin;

-- Index: pad_docent_entitat_fk_i

-- DROP INDEX pad_docent_entitat_fk_i;

CREATE INDEX pad_docent_entitat_fk_i
  ON pad_documententitat
  USING btree
  (entitatid);

-- Index: pad_docent_fitxerid_fk_i

-- DROP INDEX pad_docent_fitxerid_fk_i;

CREATE INDEX pad_docent_fitxerid_fk_i
  ON pad_documententitat
  USING btree
  (fitxerid);

-- Index: pad_documententitat_pk_i

-- DROP INDEX pad_documententitat_pk_i;

CREATE INDEX pad_documententitat_pk_i
  ON pad_documententitat
  USING btree
  (documententitatid);
--CANVIS PER ACTUALITZAR ORGANS GESTORS DE PROCEDIMENTS

-- Modificar taula entitat per afegir el camp dir3
ALTER TABLE pad_entitat ADD COLUMN dir3 character varying(30);



-- Sequence: pad_organ_seq
-- DROP SEQUENCE pad_organ_seq;

CREATE SEQUENCE public.pad_organ_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 50136
  CACHE 1;
ALTER TABLE pad_organ_seq
  OWNER TO pinbaladmin2;


-- CREACIO TAULA ORGAN
CREATE TABLE public.pad_organ
(
  organid bigint NOT NULL DEFAULT nextval('public.pad_organ_seq'::regclass),
  nom character varying(255) NOT NULL,
  dir3 character varying(255) NOT NULL,
  dir3pare character varying(255),
  entitatid bigint,
  cif character varying(30),
  CONSTRAINT pad_organ_pk PRIMARY KEY (organid),
  CONSTRAINT pad_organ_entitat_fk FOREIGN KEY (entitatid)
      REFERENCES pad_entitat (entitatid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pad_organ
  OWNER TO pinbaladmin2;

CREATE INDEX pad_organ_entitatid_fk_i
  ON pad_organ
  USING btree
  (entitatid);

CREATE INDEX pad_organ_pk_i
  ON pad_organ
  USING btree
  (organid);


-- AFEGIR ORGANID A TAULA SOLICITUD
ALTER TABLE pad_solicitud ADD COLUMN organid bigint;

ALTER TABLE pad_solicitud
  ADD CONSTRAINT pad_solicitud_organ_fk FOREIGN KEY (organid)
      REFERENCES pad_organ (organid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

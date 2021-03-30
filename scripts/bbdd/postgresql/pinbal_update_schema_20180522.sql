


CREATE TABLE pad_area
(
   areaid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   nom character varying(255) NOT NULL, 
   entitatid bigint NOT NULL, 
   CONSTRAINT pad_area_pk PRIMARY KEY (areaid), 
   CONSTRAINT pad_area_entitat_fk FOREIGN KEY (entitatid) REFERENCES pad_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Es recomanable tenir un index de la clau primaria.
create index pad_area_pk_i on pad_area (areaid);

-- Es recomanable tenir un index de la clau forania.
create index pad_area_entitatid_fk_i on pad_area (entitatid);

-- Emplenam l'area amb l'entitat
INSERT INTO pad_area(areaid, nom, entitatid) select entitatid, nom, entitatid from pad_entitat;


-- Departament
ALTER TABLE pad_departament  ADD COLUMN areaid bigint;

ALTER TABLE pad_departament  ADD CONSTRAINT pad_depart_area_fk FOREIGN KEY (areaid) REFERENCES pad_area (areaid) ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE pad_departament SET areaid=entitatid;

ALTER TABLE pad_departament DROP COLUMN entitatid;
ALTER TABLE pad_departament ALTER COLUMN areaid SET NOT NULL;


-- Es recomanable tenir un index de la clau forania.
create index pad_departament_areaid_fk_i on pad_departament (areaid);

-- Solicitud
ALTER TABLE pad_solicitud
  ADD COLUMN areaid bigint;
ALTER TABLE pad_solicitud
  ADD CONSTRAINT pad_solicitud_area_fk FOREIGN KEY (areaid) REFERENCES pad_area (areaid) ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE pad_solicitud SET areaid=entitatlocalid WHERE departamentid is not null;

-- Es recomanable tenir un index de la clau forania.
create index pad_solicitud_areaid_fk_i on pad_solicitud (areaid);
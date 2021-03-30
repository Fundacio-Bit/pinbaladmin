
-- GRUP ENTITAT
CREATE TABLE pad_grupentitat
(
   grupentitatid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   nom character varying(256) NOT NULL, 
   descripcio character varying(256), 
   CONSTRAINT pad_grupentitat_pk PRIMARY KEY (grupentitatid)
);

INSERT INTO pad_grupentitat VALUES (0, 'Ajuntaments', 'Ajuntaments');
INSERT INTO pad_grupentitat VALUES (1, 'Consells', 'Consells');
INSERT INTO pad_grupentitat VALUES (2, 'Govern', 'Govern');

-- Es recomanable tenir un index de la clau primaria.
create index pad_grupentitat_pk_i on pad_grupentitat (grupentitatid);


-- ENTITAT

ALTER TABLE pad_entitat
  ADD COLUMN cif character varying(10);
ALTER TABLE pad_entitat
  ADD COLUMN grupentitatid bigint NOT NULL DEFAULT 0;
ALTER TABLE pad_entitat
  ADD CONSTRAINT pad_entitat_grupent_fk FOREIGN KEY (grupentitatid) REFERENCES pad_grupentitat (grupentitatid) ON UPDATE NO ACTION ON DELETE NO ACTION;


-- Es recomanable tenir un index de la clau forania.
create index pad_entitat_grupentitatid_fk_i on pad_entitat (grupentitatid);

-- FALTA fer Clau UNICA el NIF !!!!
  

-- GRUPENTITAT -CEDENT (Cedents a eliminar de cert grup)

CREATE TABLE pad_grupentitatcedent  
(
   grupentitatcedentid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   grupentitatid bigint NOT NULL, 
   cedentid bigint NOT NULL, 
   CONSTRAINT pad_grupentitatcedent_pk PRIMARY KEY (grupentitatcedentid), 
   CONSTRAINT pad_grupentced_entiservei_fk FOREIGN KEY (cedentid) REFERENCES pad_entitatservei (entitatserveiid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pad_grupentced_grupent_fk FOREIGN KEY (grupentitatid) REFERENCES pad_grupentitat (grupentitatid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pad_grupentced_grp_ced_uk UNIQUE (grupentitatid, cedentid)
);
COMMENT ON TABLE pad_grupentitatcedent
  IS 'Cedents a eliminar de cert grup';


-- Es recomanable tenir un index de la clau primaria.
create index pad_grupentitatcedent_pk_i on pad_grupentitatcedent (grupentitatcedentid);

-- Es recomanable tenir un index de la clau forania.
create index pad_grupentced_grpentid_fk_i on pad_grupentitatcedent (grupentitatid);

-- Es recomanable tenir un index de la clau forania.
create index pad_grupentced_cedentid_fk_i on pad_grupentitatcedent (cedentid);
  
  
  
  
  


CREATE TABLE pad_departament
(
   departamentid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   nom character varying(255) NOT NULL, 
   entitatid bigint NOT NULL, 
   CONSTRAINT pad_departament_pk PRIMARY KEY (departamentid), 
   CONSTRAINT pad_depart_entitat_nom_uk UNIQUE (nom, entitatid),
   CONSTRAINT pad_depart_entitat_fk FOREIGN KEY (entitatid) REFERENCES pad_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);


create index pad_depart_pk_i on pad_departament (departamentid);



create index pad_departament_entitatid_fk_i on pad_departament (entitatid);




ALTER TABLE pad_solicitud
  ADD COLUMN departamentid bigint;
ALTER TABLE pad_solicitud
  ADD CONSTRAINT pad_solicitud_depart_fk FOREIGN KEY (departamentid) REFERENCES pad_departament (departamentid) ON UPDATE NO ACTION ON DELETE NO ACTION;

 -- Es recomanable tenir un index de la clau forania.
create index pad_solicitud_departid_fk_i on pad_solicitud (departamentid);
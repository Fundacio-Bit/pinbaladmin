

ALTER TABLE pad_solicitud RENAME entitatid  TO entitatlocalid;
ALTER TABLE pad_solicitud
   ALTER COLUMN entitatlocalid DROP NOT NULL;
ALTER TABLE pad_solicitud
  ADD COLUMN entitatestatal character varying(255);
  
  
  
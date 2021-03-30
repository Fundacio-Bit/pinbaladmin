
ALTER TABLE pad_servei
  ADD COLUMN tipusconsentiment integer NOT NULL DEFAULT 0;

ALTER TABLE pad_servei
  ADD CONSTRAINT pad_servei_codi_uk UNIQUE (codi);
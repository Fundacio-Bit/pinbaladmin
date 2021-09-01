
-- Afegir creador i tipus incidència a Incidències

ALTER TABLE pad_incidenciatecnica
  ADD COLUMN tipus integer NOT NULL DEFAULT 1;
ALTER TABLE pad_incidenciatecnica
  ADD COLUMN creador character varying(255) NOT NULL DEFAULT 'mcapo';
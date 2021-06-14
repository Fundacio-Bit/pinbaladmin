

-- IMPORTANT: Actualitzar plantilla [pinbaladmin]\scripts\templates\plantilla_formulari.odt
-- IMPORTANT: Actualitzar plantilla [pinbaladmin]\scripts\templates\Plantilla-Procedimientos.xlsx

ALTER TABLE pad_solicitud
  ADD COLUMN denominacio character varying(255);
ALTER TABLE pad_solicitud
  ADD COLUMN dir3 character varying(50);
ALTER TABLE pad_solicitud
  ADD COLUMN nif character varying(40);
COMMENT ON COLUMN pad_solicitud.denominacio IS 'és l''entitat';



ALTER TABLE pad_incidenciatecnica
  ADD COLUMN caididentificadorconsulta character varying(100);
ALTER TABLE pad_incidenciatecnica
  ADD COLUMN caidnumeroseguiment character varying(100);
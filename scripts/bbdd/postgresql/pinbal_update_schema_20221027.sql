
-- Gestió automàtica de Incidències a partir de Correus #46 

ALTER TABLE pad_incidenciatecnica ALTER COLUMN descripcio TYPE text;
ALTER TABLE pad_event ALTER COLUMN comentari TYPE text;
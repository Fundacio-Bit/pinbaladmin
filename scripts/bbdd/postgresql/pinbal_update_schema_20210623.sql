
-- Múltiples "Identificador de Consulta" i "Número de Seguimiento" en Sol·licituds #12

ALTER TABLE pad_event ADD COLUMN caididentificadorconsulta character varying(100);
ALTER TABLE pad_event ADD COLUMN caidnumeroseguiment character varying(100);


-- Nou camp per permetre marcar serveis com a ocults des dels dominis de SISTRA
ALTER TABLE pad_servei
    ADD COLUMN ocult boolean NOT NULL DEFAULT false;

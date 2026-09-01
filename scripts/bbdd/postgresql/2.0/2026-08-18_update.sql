

ALTER TABLE pad_entitat ADD COLUMN gestionatpergoverndigital boolean NOT NULL DEFAULT false;

UPDATE pad_entitat SET  gestionatpergoverndigital=true WHERE codipinbal is not null and codipinbal <> 'PARLAMENT';

UPDATE pad_entitat SET gestionatpergoverndigital=false WHERE nom like 'Ajuntament%';




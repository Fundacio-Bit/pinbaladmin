
--  Gestió d'Operadors (usuaris) i poder assignar incidència a un Operador #52 

CREATE TABLE pad_operador
(
   operadorid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   username character varying(255) NOT NULL, 
   nom character varying(255) NOT NULL, 
   email character varying(255) NOT NULL, 
   CONSTRAINT pad_operador_pk PRIMARY KEY (operadorid)
);

create index pad_operador_pk_i on pad_operador (operadorid);



INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (1, 'anadal', 'Antoni Nadal', 'anadal@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (2, 'pvico', 'Pilar Vico', 'pvico@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (3, 'atrobat', 'Antoni Trobat', 'atrobat@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (4, 'gdeignacio', 'Guillermo de Ignacio', 'gdeignacio@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (5, 'mgonzalez', 'Marilen Gonzalez', 'mgonzalez@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (6, 'mcapo', 'Maria Antonia Capo', 'mcapo@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (7, 'fsalas', 'Felip Salas', 'fsalas@fundaciobit.org');
INSERT INTO pad_operador(operadorid, username, nom, email) VALUES (8, 'ptrias', 'Pau Trias', 'ptrias@fundaciobit.org');


CREATE TABLE pad_incidenciatecnica
(
  incidenciatecnicaid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'),
  titol character varying(255) NOT NULL,
  descripcio character varying(4000) NOT NULL,
  datainici timestamp without time zone NOT NULL,
  nomentitat character varying(255),
  contactenom character varying(255) NOT NULL,
  contacteemail character varying(100) NOT NULL,
  contactetelefon character varying(100),
  estat integer NOT NULL,
  CONSTRAINT pad_incidenciatecnica_pk PRIMARY KEY (incidenciatecnicaid)
);


ALTER TABLE pad_event RENAME tascatecnicaid  TO incidenciatecnicaid;
ALTER TABLE pad_event
  ADD CONSTRAINT pad_event_inctecnica_it_fk FOREIGN KEY (incidenciatecnicaid) REFERENCES pad_incidenciatecnica (incidenciatecnicaid) ON UPDATE NO ACTION ON DELETE NO ACTION;


create index pad_event_inctecnicaid_fk_i on pad_event (incidenciatecnicaid);
create index pad_incidenciatecnica_pk_i on pad_incidenciatecnica (incidenciatecnicaid);


ALTER TABLE public.pad_event DROP COLUMN privat;
 
 
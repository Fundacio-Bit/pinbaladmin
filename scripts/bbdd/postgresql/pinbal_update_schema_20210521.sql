

CREATE TABLE public.pad_event
(
   eventid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'::regclass), 
   solicitudid bigint, 
   tascatecnicaid bigint, 
   dataevent timestamp without time zone NOT NULL, 
   tipus integer NOT NULL, 
   privat boolean NOT NULL, 
   persona character varying(255) NOT NULL, 
   comentari character varying(4000), 
   fitxerid bigint, 
   CONSTRAINT pad_event_pk PRIMARY KEY (eventid), 
   CONSTRAINT pad_event_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pad_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE pad_event
  ADD COLUMN nollegit boolean NOT NULL DEFAULT false;

ALTER TABLE pad_event
  ADD CONSTRAINT pad_event_solicitud_soli_fk FOREIGN KEY (solicitudid) REFERENCES pad_solicitud (solicitudid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index pad_event_pk_i on pad_event (eventid);
create index pad_event_fitxerid_fk_i on pad_event (fitxerid);
create index pad_event_solicitudid_fk_i on pad_event (solicitudid);
 
 
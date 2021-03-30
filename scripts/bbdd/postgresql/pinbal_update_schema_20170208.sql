

CREATE TABLE pad_email
(
   emailid bigint NOT NULL DEFAULT nextval('pad_pinbaladmin_seq'), 
   destinataris character varying(6000) NOT NULL, 
   subject character varying(255) NOT NULL, 
   message character varying(6000) NOT NULL, 
   dataenviament timestamp without time zone NOT NULL, 
   enviador character varying(255) NOT NULL, 
   CONSTRAINT pad_email_pk PRIMARY KEY (emailid)
);


create index pad_email_pk_i on pad_email (emailid);

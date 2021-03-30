ALTER TABLE pad_solicitud
  ADD COLUMN solicitudxmlid bigint;
ALTER TABLE pad_solicitud
  ADD CONSTRAINT pad_solicitud_fitxer_xml_fk FOREIGN KEY (solicitudxmlid) REFERENCES pad_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;
  
create index pad_solicitud_solixml_fk_i on pad_solicitud (solicitudxmlid);

ALTER TABLE pad_solicitudservei
  ADD COLUMN articles character varying(255);
ALTER TABLE pad_solicitudservei
  ADD COLUMN consentiment character varying(255);
ALTER TABLE pad_solicitudservei
  ADD COLUMN enllazconsentiment character varying(255);
ALTER TABLE pad_solicitudservei
  ADD COLUMN enllaznormalegal character varying(255);
ALTER TABLE pad_solicitudservei
  ADD COLUMN tipusconsentiment character varying(255);

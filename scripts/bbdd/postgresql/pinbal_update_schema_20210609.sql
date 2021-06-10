

-- IMPORTANT: Afegida Propietat org.fundaciobit.pinbaladmin.template_serveis_excel=D:/pinbaladmin/scripts/templates/Plantilla-Procedimientos.xlsx


ALTER TABLE pad_solicitud
  ADD COLUMN procedimenttipus character varying(255);
ALTER TABLE pad_solicitud
  ADD COLUMN responsableprocnom character varying(255);
ALTER TABLE pad_solicitud
  ADD COLUMN responsableprocemail character varying(255);
  
  
ALTER TABLE pad_solicitudservei
  ADD COLUMN caduca character varying(255);
ALTER TABLE pad_solicitudservei
  ADD COLUMN fechacaduca character varying(255);
  
  
  
ALTER TABLE pad_fitxer
   ALTER COLUMN mime TYPE character varying(100);
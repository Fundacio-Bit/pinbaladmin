
    alter table pad_area 
       drop constraint pad_area_entitat_fk;

    alter table pad_campformulari 
       drop constraint pad_campform_formulari_fk;

    alter table pad_campsolicitud 
       drop constraint pad_campsoli_campform_fk;

    alter table pad_campsolicitud 
       drop constraint pad_campsoli_soliservei_fk;

    alter table pad_departament 
       drop constraint pad_depart_area_fk;

    alter table pad_document 
       drop constraint pad_document_fitxer_firm_fk;

    alter table pad_document 
       drop constraint pad_document_fitxer_orig_fk;

    alter table pad_documentcedent 
       drop constraint pad_doccedent_entiservei_fk;

    alter table pad_documentcedent 
       drop constraint pad_doccedent_fitxer_fitxe_fk;

    alter table pad_documententitat 
       drop constraint pad_docent_entitat_fk;

    alter table pad_documententitat 
       drop constraint pad_docent_fitxer_fitxe_fk;

    alter table pad_documentsolicitud 
       drop constraint pad_docsoli_document_fk;

    alter table pad_documentsolicitud 
       drop constraint pad_docsoli_solicitud_fk;

    alter table pad_entitat 
       drop constraint pad_entitat_grupent_fk;

    alter table pad_event 
       drop constraint pad_event_fitxer_fitxer_fk;

    alter table pad_event 
       drop constraint pad_event_inctecnica_it_fk;

    alter table pad_event 
       drop constraint pad_event_solicitud_soli_fk;

    alter table pad_formulari 
       drop constraint pad_formulari_fitxer_fk;

    alter table pad_grupentitatcedent 
       drop constraint pad_grupentced_entiservei_fk;

    alter table pad_grupentitatcedent 
       drop constraint pad_grupentced_grupent_fk;

    alter table pad_servei 
       drop constraint pad_servei_entiservei_fk;

    alter table pad_servei 
       drop constraint pad_servei_estatserv_fk;

    alter table pad_servei 
       drop constraint pad_servei_formulari_fk;

    alter table pad_solicitud 
       drop constraint pad_solicitud_depart_fk;

    alter table pad_solicitud 
       drop constraint pad_solicitud_fitxer_fk;

    alter table pad_solicitud 
       drop constraint pad_solicitud_estatsoli_fk;

    alter table pad_solicitud 
       drop constraint pad_solicitud_fitxer_xml_fk;

    alter table pad_solicitudservei 
       drop constraint pad_soliservei_estsolserv_fk;

    alter table pad_solicitudservei 
       drop constraint pad_soliservei_servei_fk;

    alter table pad_solicitudservei 
       drop constraint pad_soliservei_solicitud_fk;

    alter table pad_tiquet 
       drop constraint pad_tiquet_fitxer_adj1_fk;

    alter table pad_tiquet 
       drop constraint pad_tiquet_fitxer_adj2_fk;

    alter table pad_tiquet 
       drop constraint pad_tiquet_estattique_estat_fk;

    alter table pad_tiquet 
       drop constraint pad_tiquet_tiptiquet_fk;

    alter table pad_traducciomap 
       drop constraint pad_traducmap_traduccio_fk;

    drop table if exists pad_area cascade;

    drop table if exists pad_campformulari cascade;

    drop table if exists pad_campsolicitud cascade;

    drop table if exists pad_departament cascade;

    drop table if exists pad_document cascade;

    drop table if exists pad_documentcedent cascade;

    drop table if exists pad_documententitat cascade;

    drop table if exists pad_documentsolicitud cascade;

    drop table if exists pad_email cascade;

    drop table if exists pad_entitat cascade;

    drop table if exists pad_entitatservei cascade;

    drop table if exists pad_estatservei cascade;

    drop table if exists pad_estatsolicitud cascade;

    drop table if exists pad_estatsolicitudservei cascade;

    drop table if exists pad_estattiquet cascade;

    drop table if exists pad_event cascade;

    drop table if exists pad_fitxer cascade;

    drop table if exists pad_formulari cascade;

    drop table if exists pad_grupentitat cascade;

    drop table if exists pad_grupentitatcedent cascade;

    drop table if exists pad_idioma cascade;

    drop table if exists pad_incidenciatecnica cascade;

    drop table if exists pad_operador cascade;

    drop table if exists pad_servei cascade;

    drop table if exists pad_solicitud cascade;

    drop table if exists pad_solicitudservei cascade;

    drop table if exists pad_tipustiquet cascade;

    drop table if exists pad_tiquet cascade;

    drop table if exists pad_traduccio cascade;

    drop table if exists pad_traducciomap cascade;

    drop sequence if exists pad_area_seq;

    drop sequence if exists pad_campformulari_seq;

    drop sequence if exists pad_campsolicitud_seq;

    drop sequence if exists pad_departament_seq;

    drop sequence if exists pad_document_seq;

    drop sequence if exists pad_documentcedent_seq;

    drop sequence if exists pad_documententitat_seq;

    drop sequence if exists pad_documentsolicitud_seq;

    drop sequence if exists pad_email_seq;

    drop sequence if exists pad_entitat_seq;

    drop sequence if exists pad_entitatservei_seq;

    drop sequence if exists pad_event_seq;

    drop sequence if exists pad_fitxer_seq;

    drop sequence if exists pad_formulari_seq;

    drop sequence if exists pad_grupentitat_seq;

    drop sequence if exists pad_grupentitatcedent_seq;

    drop sequence if exists pad_incidenciatecnica_seq;

    drop sequence if exists pad_operador_seq;

    drop sequence if exists pad_servei_seq;

    drop sequence if exists pad_solicitud_seq;

    drop sequence if exists pad_solicitudservei_seq;

    drop sequence if exists pad_tiquet_seq;

    drop sequence if exists pad_traduccio_seq;

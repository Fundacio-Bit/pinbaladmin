create sequence pad_area_seq start 1000 increment 1;
create sequence pad_campformulari_seq start 1000 increment 1;
create sequence pad_campsolicitud_seq start 1000 increment 1;
create sequence pad_departament_seq start 1000 increment 1;
create sequence pad_document_seq start 1000 increment 1;
create sequence pad_documentcedent_seq start 1000 increment 1;
create sequence pad_documententitat_seq start 1000 increment 1;
create sequence pad_documentsolicitud_seq start 1000 increment 1;
create sequence pad_email_seq start 1000 increment 1;
create sequence pad_entitat_seq start 1000 increment 1;
create sequence pad_entitatservei_seq start 1000 increment 1;
create sequence pad_event_seq start 1000 increment 1;
create sequence pad_fitxer_seq start 1000 increment 1;
create sequence pad_formulari_seq start 1000 increment 1;
create sequence pad_grupentitat_seq start 1000 increment 1;
create sequence pad_grupentitatcedent_seq start 1000 increment 1;
create sequence pad_incidenciatecnica_seq start 1000 increment 1;
create sequence pad_operador_seq start 1000 increment 1;
create sequence pad_servei_seq start 1000 increment 1;
create sequence pad_solicitud_seq start 1000 increment 1;
create sequence pad_solicitudservei_seq start 1000 increment 1;
create sequence pad_tiquet_seq start 1000 increment 1;
create sequence pad_traduccio_seq start 1000 increment 1;

    create table pad_area (
       areaid int8 not null,
        entitatid int8 not null,
        nom varchar(255) not null,
        primary key (areaid)
    );

    create table pad_campformulari (
       campformulariid int8 not null,
        camppdf varchar(100) not null,
        formulariid int8 not null,
        nom varchar(100) not null,
        primary key (campformulariid)
    );

    create table pad_campsolicitud (
       campsolicitudid int8 not null,
        campformulariid int8 not null,
        solicitudserveiid int8 not null,
        valor varchar(2000) not null,
        primary key (campsolicitudid)
    );

    create table pad_departament (
       departamentid int8 not null,
        areaid int8 not null,
        nom varchar(255) not null,
        primary key (departamentid)
    );

    create table pad_document (
       documentid int8 not null,
        fitxerfirmatid int8,
        fitxeroriginalid int8 not null,
        nom varchar(255) not null,
        notes varchar(255),
        primary key (documentid)
    );

    create table pad_documentcedent (
       documentcedentid int8 not null,
        dataalta timestamp not null,
        descripcio text,
        entitatserveiid int8 not null,
        fitxerid int8,
        titol varchar(255) not null,
        primary key (documentcedentid)
    );

    create table pad_documententitat (
       documententitatid int8 not null,
        dataalta timestamp not null,
        descripcio text,
        entitatid int8 not null,
        fitxerid int8,
        titol varchar(255) not null,
        primary key (documententitatid)
    );

    create table pad_documentsolicitud (
       documentsolicitudid int8 not null,
        documentid int8 not null,
        solicitudid int8 not null,
        primary key (documentsolicitudid)
    );

    create table pad_email (
       emailid int8 not null,
        dataenviament timestamp not null,
        destinataris varchar(6000) not null,
        enviador varchar(255) not null,
        message varchar(6000) not null,
        subject varchar(255) not null,
        primary key (emailid)
    );

    create table pad_entitat (
       entitatid int8 not null,
        cif varchar(10) not null,
        convenipmsbae boolean not null,
        grupentitatid int8 default 0 not null,
        nom varchar(500) not null,
        personacontacte varchar(255),
        primary key (entitatid)
    );

    create table pad_entitatservei (
       entitatserveiid int8 not null,
        balears boolean not null,
        descripcio varchar(255),
        nom varchar(255) not null,
        primary key (entitatserveiid)
    );

    create table pad_estatservei (
       estatserveiid int8 not null,
        descripcio varchar(255),
        nom varchar(100) not null,
        primary key (estatserveiid)
    );

    create table pad_estatsolicitud (
       estatsolicitudid int8 not null,
        descripcio varchar(255),
        nom varchar(100) not null,
        primary key (estatsolicitudid)
    );

    create table pad_estatsolicitudservei (
       estatsolicitudserveiid int8 not null,
        descripcio varchar(255),
        nom varchar(100) not null,
        primary key (estatsolicitudserveiid)
    );

    create table pad_estattiquet (
       estattiquetid int8 not null,
        nom varchar(100) not null,
        primary key (estattiquetid)
    );

    create table pad_event (
       eventid int8 not null,
        caididentificadorconsulta varchar(100),
        caidnumeroseguiment varchar(100),
        comentari text,
        dataevent timestamp not null,
        fitxerid int8,
        incidenciatecnicaid int8,
        nollegit boolean not null,
        persona varchar(255) not null,
        solicitudid int8,
        tipus int4 not null,
        primary key (eventid)
    );

    create table pad_fitxer (
       fitxerid int8 not null,
        descripcio varchar(1000),
        mime varchar(100) not null,
        nom varchar(255) not null,
        tamany int8 not null,
        primary key (fitxerid)
    );

    create table pad_formulari (
       formulariid int8 not null,
        descripcio varchar(255),
        fitxerid int8 not null,
        nom varchar(255) not null,
        primary key (formulariid)
    );

    create table pad_grupentitat (
       grupentitatid int8 not null,
        descripcio varchar(256),
        nom varchar(256) not null,
        primary key (grupentitatid)
    );

    create table pad_grupentitatcedent (
       grupentitatcedentid int8 not null,
        cedentid int8 not null,
        grupentitatid int8 not null,
        primary key (grupentitatcedentid)
    );

    create table pad_idioma (
       idiomaid varchar(5) not null,
        nom varchar(50) not null,
        ordre int4 default 0 not null,
        suportat boolean not null,
        primary key (idiomaid)
    );

    create table pad_incidenciatecnica (
       incidenciatecnicaid int8 not null,
        caididentificadorconsulta varchar(100),
        caidnumeroseguiment varchar(100),
        contacteemail varchar(100) not null,
        contactenom varchar(255) not null,
        contactetelefon varchar(100),
        creador varchar(255) default 'mcapo' not null,
        datainici timestamp not null,
        descripcio text not null,
        estat int4 not null,
        nomentitat varchar(255),
        tipus int4 not null,
        titol varchar(255) not null,
        primary key (incidenciatecnicaid)
    );

    create table pad_operador (
       operadorid int8 not null,
        email varchar(255) not null,
        nom varchar(255) not null,
        username varchar(255) not null,
        primary key (operadorid)
    );

    create table pad_servei (
       serveiid int8 not null,
        codi varchar(255) not null,
        descripcio varchar(1000),
        entitatserveiid int8 not null,
        estatserveiid int8 not null,
        formulariid int8,
        nom varchar(1000) not null,
        ocult boolean not null,
        tipusconsentiment int4 default 0 not null,
        primary key (serveiid)
    );

    create table pad_solicitud (
       solicitudid int8 not null,
        codidescriptiu varchar(256),
        creador varchar(100),
        datafi timestamp,
        datainici timestamp not null,
        denominacio varchar(255),
        departamentid int8,
        dir3 varchar(50),
        documentsolicitudid int8,
        entitatestatal varchar(255),
        estatid int8 not null,
        firmatdocsolicitud boolean not null,
        nif varchar(40),
        estat varchar(2550),
        personacontacte varchar(255),
        personacontacteemail varchar(100),
        pinfo varchar(255),
        procedimentcodi varchar(255) not null,
        procedimentnom varchar(2000) not null,
        procedimenttipus varchar(255),
        produccio boolean not null,
        responsableprocemail varchar(255),
        responsableprocnom varchar(255),
        solicitudxmlid int8,
        ticketassociat varchar(255),
        ticketnumeroseguiment varchar(255),
        primary key (solicitudid)
    );

    create table pad_solicitudservei (
       id int8 not null,
        articles varchar(255),
        caduca varchar(255),
        consentiment varchar(255),
        enllazconsentiment varchar(255),
        enllaznormalegal varchar(255),
        estatsolicitudserveiid int8 not null,
        fechacaduca varchar(255),
        normalegal varchar(3000),
        notes varchar(2000),
        serveiid int8 not null,
        solicitudid int8 not null,
        tipusconsentiment varchar(255),
        primary key (id)
    );

    create table pad_tipustiquet (
       tipustiquetid int8 not null,
        nom varchar(100) not null,
        primary key (tipustiquetid)
    );

    create table pad_tiquet (
       tiquetid int8 not null,
        adjunt1id int8,
        adjunt2id int8,
        dataalta timestamp not null,
        dataincidencia timestamp,
        datainici timestamp,
        datafi timestamp,
        descripcio varchar(3000) not null,
        entorn int4 default 1 not null,
        estattiquetid int8 not null,
        informador varchar(100) not null,
        notes varchar(1000),
        solucionatper varchar(100),
        tipustiquetid int8 not null,
        titol varchar(256) not null,
        versiopinbal varchar(100) not null,
        primary key (tiquetid)
    );

    create table pad_traduccio (
       traduccioid int8 not null,
        primary key (traduccioid)
    );

    create table pad_traducciomap (
       traducciomapid int8 not null,
        valor varchar(4000),
        idiomaid varchar(255) not null,
        primary key (traducciomapid, idiomaid)
    );
create index pad_area_pk_i on pad_area (areaid);
create index pad_area_entitatid_fk_i on pad_area (entitatid);
create index pad_campformulari_pk_i on pad_campformulari (campformulariid);
create index pad_campform_formulariid_fk_i on pad_campformulari (formulariid);
create index pad_campsolicitud_pk_i on pad_campsolicitud (campsolicitudid);
create index pad_campsoli_campformid_fk_i on pad_campsolicitud (campformulariid);
create index pad_campsoli_soliservid_fk_i on pad_campsolicitud (solicitudserveiid);
create index pad_depart_pk_i on pad_departament (departamentid);
create index pad_departament_areaid_fk_i on pad_departament (areaid);
create index pad_document_pk_i on pad_document (documentid);
create index pad_document_fitxer_orig_fk_i on pad_document (fitxeroriginalid);
create index pad_document_fitxer_firm_fk_i on pad_document (fitxerfirmatid);
create index pad_documentcedent_pk_i on pad_documentcedent (documentcedentid);
create index pad_doccedent_entservei_fk_i on pad_documentcedent (entitatserveiid);
create index pad_doccedent_fitxerid_fk_i on pad_documentcedent (fitxerid);
create index pad_documententitat_pk_i on pad_documententitat (documententitatid);
create index pad_docent_entitat_fk_i on pad_documententitat (entitatid);
create index pad_docent_fitxerid_fk_i on pad_documententitat (fitxerid);
create index pad_documentsolicitud_pk_i on pad_documentsolicitud (documentsolicitudid);
create index pad_docsoli_documentid_fk_i on pad_documentsolicitud (documentid);
create index pad_docsoli_solicitudid_fk_i on pad_documentsolicitud (solicitudid);
create index pad_email_pk_i on pad_email (emailid);
create index pad_entitat_pk_i on pad_entitat (entitatid);
create index pad_entitat_grupentitatid_fk_i on pad_entitat (grupentitatid);
create index pad_entitatservei_pk_i on pad_entitatservei (entitatserveiid);
create index pad_estatservei_pk_i on pad_estatservei (estatserveiid);
create index pad_estatsolicitud_pk_i on pad_estatsolicitud (estatsolicitudid);
create index pad_estatsolicitudservei_pk_i on pad_estatsolicitudservei (estatsolicitudserveiid);
create index pad_estattiquet_pk_i on pad_estattiquet (estattiquetid);
create index pad_event_pk_i on pad_event (eventid);
create index pad_event_solicitudid_fk_i on pad_event (solicitudid);
create index pad_event_inctecnicaid_fk_i on pad_event (incidenciatecnicaid);
create index pad_event_fitxerid_fk_i on pad_event (fitxerid);
create index pad_fitxer_pk_i on pad_fitxer (fitxerid);
create index pad_formulari_pk_i on pad_formulari (formulariid);
create index pad_formulari_fitxerid_fk_i on pad_formulari (fitxerid);
create index pad_grupentitat_pk_i on pad_grupentitat (grupentitatid);
create index pad_grupentitatcedent_pk_i on pad_grupentitatcedent (grupentitatcedentid);
create index pad_grupentced_grpentid_fk_i on pad_grupentitatcedent (grupentitatid);
create index pad_grupentced_cedentid_fk_i on pad_grupentitatcedent (cedentid);

    alter table pad_grupentitatcedent 
       add constraint pad_grupentced_grp_ced_uk unique (grupentitatid, cedentid);
create index pad_idioma_pk_i on pad_idioma (idiomaid);
create index pad_incidenciatecnica_pk_i on pad_incidenciatecnica (incidenciatecnicaid);
create index pad_operador_pk_i on pad_operador (operadorid);
create index pad_servei_pk_i on pad_servei (serveiid);
create index pad_servei_formulariid_fk_i on pad_servei (formulariid);
create index pad_servei_entservid_fk_i on pad_servei (entitatserveiid);
create index pad_servei_estatserveiid_fk_i on pad_servei (estatserveiid);

    alter table pad_servei 
       add constraint UK_fse5vmq36me4nrq4dmlnurjn unique (codi);
create index pad_solicitud_pk_i on pad_solicitud (solicitudid);
create index pad_solicitud_estatid_fk_i on pad_solicitud (estatid);
create index pad_solicitud_departid_fk_i on pad_solicitud (departamentid);
create index pad_solicitud_docsoli_fk_i on pad_solicitud (documentsolicitudid);
create index pad_solicitud_solixml_fk_i on pad_solicitud (solicitudxmlid);
create index pad_solicitudservei_pk_i on pad_solicitudservei (id);
create index pad_soliservei_soliid_fk_i on pad_solicitudservei (solicitudid);
create index pad_soliservei_serveiid_fk_i on pad_solicitudservei (serveiid);
create index pad_soliservei_estsolserv_fk_i on pad_solicitudservei (estatsolicitudserveiid);

    alter table pad_solicitudservei 
       add constraint pad_soliservei_multiple_uk unique (serveiid, solicitudid);
create index pad_tipustiquet_pk_i on pad_tipustiquet (tipustiquetid);
create index pad_tiquet_pk_i on pad_tiquet (tiquetid);
create index pad_tiquet_estattiquetid_fk_i on pad_tiquet (estattiquetid);
create index pad_tiquet_tipustiquetid_fk_i on pad_tiquet (tipustiquetid);
create index pad_tiquet_adjunt1id_fk_i on pad_tiquet (adjunt1id);
create index pad_tiquet_adjunt2id_fk_i on pad_tiquet (adjunt2id);
create index pad_traduccio_pk_i on pad_traduccio (traduccioid);

    alter table pad_area 
       add constraint pad_area_entitat_fk 
       foreign key (entitatid) 
       references pad_entitat;

    alter table pad_campformulari 
       add constraint pad_campform_formulari_fk 
       foreign key (formulariid) 
       references pad_formulari;

    alter table pad_campsolicitud 
       add constraint pad_campsoli_campform_fk 
       foreign key (campformulariid) 
       references pad_campformulari;

    alter table pad_campsolicitud 
       add constraint pad_campsoli_soliservei_fk 
       foreign key (solicitudserveiid) 
       references pad_solicitudservei;

    alter table pad_departament 
       add constraint pad_depart_area_fk 
       foreign key (areaid) 
       references pad_area;

    alter table pad_document 
       add constraint pad_document_fitxer_firm_fk 
       foreign key (fitxerfirmatid) 
       references pad_fitxer;

    alter table pad_document 
       add constraint pad_document_fitxer_orig_fk 
       foreign key (fitxeroriginalid) 
       references pad_fitxer;

    alter table pad_documentcedent 
       add constraint pad_doccedent_entiservei_fk 
       foreign key (entitatserveiid) 
       references pad_entitatservei;

    alter table pad_documentcedent 
       add constraint pad_doccedent_fitxer_fitxe_fk 
       foreign key (fitxerid) 
       references pad_fitxer;

    alter table pad_documententitat 
       add constraint pad_docent_entitat_fk 
       foreign key (entitatid) 
       references pad_entitat;

    alter table pad_documententitat 
       add constraint pad_docent_fitxer_fitxe_fk 
       foreign key (fitxerid) 
       references pad_fitxer;

    alter table pad_documentsolicitud 
       add constraint pad_docsoli_document_fk 
       foreign key (documentid) 
       references pad_document;

    alter table pad_documentsolicitud 
       add constraint pad_docsoli_solicitud_fk 
       foreign key (solicitudid) 
       references pad_solicitud;

    alter table pad_entitat 
       add constraint pad_entitat_grupent_fk 
       foreign key (grupentitatid) 
       references pad_grupentitat;

    alter table pad_event 
       add constraint pad_event_fitxer_fitxer_fk 
       foreign key (fitxerid) 
       references pad_fitxer;

    alter table pad_event 
       add constraint pad_event_inctecnica_it_fk 
       foreign key (incidenciatecnicaid) 
       references pad_incidenciatecnica;

    alter table pad_event 
       add constraint pad_event_solicitud_soli_fk 
       foreign key (solicitudid) 
       references pad_solicitud;

    alter table pad_formulari 
       add constraint pad_formulari_fitxer_fk 
       foreign key (fitxerid) 
       references pad_fitxer;

    alter table pad_grupentitatcedent 
       add constraint pad_grupentced_entiservei_fk 
       foreign key (cedentid) 
       references pad_entitatservei;

    alter table pad_grupentitatcedent 
       add constraint pad_grupentced_grupent_fk 
       foreign key (grupentitatid) 
       references pad_grupentitat;

    alter table pad_servei 
       add constraint pad_servei_entiservei_fk 
       foreign key (entitatserveiid) 
       references pad_entitatservei;

    alter table pad_servei 
       add constraint pad_servei_estatserv_fk 
       foreign key (estatserveiid) 
       references pad_estatservei;

    alter table pad_servei 
       add constraint pad_servei_formulari_fk 
       foreign key (formulariid) 
       references pad_formulari;

    alter table pad_solicitud 
       add constraint pad_solicitud_depart_fk 
       foreign key (departamentid) 
       references pad_departament;

    alter table pad_solicitud 
       add constraint pad_solicitud_fitxer_fk 
       foreign key (documentsolicitudid) 
       references pad_fitxer;

    alter table pad_solicitud 
       add constraint pad_solicitud_estatsoli_fk 
       foreign key (estatid) 
       references pad_estatsolicitud;

    alter table pad_solicitud 
       add constraint pad_solicitud_fitxer_xml_fk 
       foreign key (solicitudxmlid) 
       references pad_fitxer;

    alter table pad_solicitudservei 
       add constraint pad_soliservei_estsolserv_fk 
       foreign key (estatsolicitudserveiid) 
       references pad_estatsolicitudservei;

    alter table pad_solicitudservei 
       add constraint pad_soliservei_servei_fk 
       foreign key (serveiid) 
       references pad_servei;

    alter table pad_solicitudservei 
       add constraint pad_soliservei_solicitud_fk 
       foreign key (solicitudid) 
       references pad_solicitud;

    alter table pad_tiquet 
       add constraint pad_tiquet_fitxer_adj1_fk 
       foreign key (adjunt1id) 
       references pad_fitxer;

    alter table pad_tiquet 
       add constraint pad_tiquet_fitxer_adj2_fk 
       foreign key (adjunt2id) 
       references pad_fitxer;

    alter table pad_tiquet 
       add constraint pad_tiquet_estattique_estat_fk 
       foreign key (estattiquetid) 
       references pad_estattiquet;

    alter table pad_tiquet 
       add constraint pad_tiquet_tiptiquet_fk 
       foreign key (tipustiquetid) 
       references pad_tipustiquet;

    alter table pad_traducciomap 
       add constraint pad_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references pad_traduccio;

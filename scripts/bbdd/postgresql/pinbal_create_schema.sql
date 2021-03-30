--
-- PostgreSQL database dump
--


--
-- Name: pad_pinbaladmin_seq; Type: SEQUENCE; Schema: public; Owner: pinbaladmin
--

CREATE SEQUENCE pad_pinbaladmin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pad_pinbaladmin_seq OWNER TO pinbaladmin;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: pad_campformulari; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_campformulari (
    campformulariid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    nom character varying(100) NOT NULL,
    camppdf character varying(100) NOT NULL,
    formulariid bigint NOT NULL
);


ALTER TABLE public.pad_campformulari OWNER TO pinbaladmin;

--
-- Name: pad_campsolicitud; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_campsolicitud (
    campsolicitudid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    campformulariid bigint NOT NULL,
    solicitudserveiid bigint NOT NULL,
    valor character varying(2000) NOT NULL
);


ALTER TABLE public.pad_campsolicitud OWNER TO pinbaladmin;

--
-- Name: pad_document; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_document (
    documentid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    fitxeroriginalid bigint NOT NULL,
    fitxerfirmatid bigint,
    notes character varying(255)
);


ALTER TABLE public.pad_document OWNER TO pinbaladmin;

--
-- Name: pad_documentsolicitud; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_documentsolicitud (
    documentsolicitudid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    documentid bigint NOT NULL,
    solicitudid bigint NOT NULL
);


ALTER TABLE public.pad_documentsolicitud OWNER TO pinbaladmin;

--
-- Dependencies: 187
-- Name: TABLE pad_documentsolicitud; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_documentsolicitud IS 'falta unique de solidi i docid';


--
-- Name: pad_entitat; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_entitat (
    entitatid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    nom character varying(500) NOT NULL,
    personacontacte character varying(255)
);


ALTER TABLE public.pad_entitat OWNER TO pinbaladmin;

--
-- Dependencies: 170
-- Name: TABLE pad_entitat; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_entitat IS '(1) persona contacte hauria de ser not null';


--
-- Name: pad_entitatservei; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_entitatservei (
    entitatserveiid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255),
    balears boolean DEFAULT false NOT NULL
);


ALTER TABLE public.pad_entitatservei OWNER TO pinbaladmin;

--
-- Dependencies: 180
-- Name: TABLE pad_entitatservei; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_entitatservei IS 'Això és un Cedent';


--
-- Name: pad_estatservei; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_estatservei (
    estatserveiid bigint NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE public.pad_estatservei OWNER TO pinbaladmin;

--
-- Name: pad_estatsolicitud; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_estatsolicitud (
    estatsolicitudid bigint NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE public.pad_estatsolicitud OWNER TO pinbaladmin;

--
-- Name: pad_estatsolicitudservei; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_estatsolicitudservei (
    estatsolicitudserveiid bigint NOT NULL,
    nom character varying(100) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE public.pad_estatsolicitudservei OWNER TO pinbaladmin;

--
-- Name: pad_estattiquet; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_estattiquet (
    estattiquetid bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.pad_estattiquet OWNER TO pinbaladmin;

--
-- Name: pad_fitxer; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_fitxer (
    fitxerid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.pad_fitxer OWNER TO pinbaladmin;

--
-- Name: pad_formulari; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_formulari (
    formulariid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255),
    fitxerid bigint NOT NULL
);


ALTER TABLE public.pad_formulari OWNER TO pinbaladmin;

--
-- Name: pad_idioma; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.pad_idioma OWNER TO pinbaladmin;

--
-- Name: pad_servei; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_servei (
    serveiid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    codi character varying(255) NOT NULL,
    nom character varying(1000) NOT NULL,
    descripcio character varying(1000),
    formulariid bigint,
    entitatserveiid bigint NOT NULL,
    estatserveiid bigint NOT NULL
);


ALTER TABLE public.pad_servei OWNER TO pinbaladmin;

--
-- Dependencies: 171
-- Name: TABLE pad_servei; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_servei IS 'NOTA:
  (1) Formulari ID not poden ser null
';


--
-- Name: pad_solicitud; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_solicitud (
    solicitudid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    personacontacte character varying(255),
    estatid bigint NOT NULL,
    estat character varying(2550),
    ticketassociat character varying(255),
    procedimentcodi character varying(255) NOT NULL,
    procedimentnom character varying(2000) NOT NULL,
    entitatid bigint NOT NULL,
    datainici timestamp without time zone NOT NULL,
    datafi timestamp without time zone,
    documentsolicitudid bigint,
    produccio boolean DEFAULT true NOT NULL,
    firmatdocsolicitud boolean DEFAULT false NOT NULL,
    pinfo character varying(255),
    personacontacteemail character varying(100),
    creador character varying(100),
    codidescriptiu character varying(256)
);


ALTER TABLE public.pad_solicitud OWNER TO pinbaladmin;

--
-- Dependencies: 172
-- Name: TABLE pad_solicitud; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_solicitud IS 'NOTA:

 documentsolicitud hauria de ser camp NOT NULL';


--
-- Name: pad_solicitudservei; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_solicitudservei (
    id bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    serveiid bigint NOT NULL,
    solicitudid bigint NOT NULL,
    estatsolicitudserveiid bigint,
    notes character varying(2000),
    normalegal character varying(3000)
);


ALTER TABLE public.pad_solicitudservei OWNER TO pinbaladmin;

--
-- Dependencies: 179
-- Name: TABLE pad_solicitudservei; Type: COMMENT; Schema: public; Owner: pinbaladmin
--

COMMENT ON TABLE pad_solicitudservei IS '(1) estatsolicitudserveiid hauria de ser not null';


--
-- Name: pad_tipustiquet; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_tipustiquet (
    tipustiquetid bigint NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.pad_tipustiquet OWNER TO pinbaladmin;

--
-- Name: pad_tiquet; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_tiquet (
    tiquetid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL,
    titol character varying(256) NOT NULL,
    descripcio character varying(3000) NOT NULL,
    dataalta timestamp without time zone NOT NULL,
    datainici timestamp without time zone,
    datafi timestamp without time zone,
    estattiquetid bigint NOT NULL,
    tipustiquetid bigint NOT NULL,
    versiopinbal character varying(100) NOT NULL,
    informador character varying(100) NOT NULL,
    solucionatper character varying(100),
    notes character varying(1000),
    entorn integer DEFAULT 1 NOT NULL,
    adjunt1id bigint,
    adjunt2id bigint,
    dataincidencia timestamp without time zone
);


ALTER TABLE public.pad_tiquet OWNER TO pinbaladmin;

--
-- Name: pad_traduccio; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_traduccio (
    traduccioid bigint DEFAULT nextval('pad_pinbaladmin_seq'::regclass) NOT NULL
);


ALTER TABLE public.pad_traduccio OWNER TO pinbaladmin;

--
-- Name: pad_traducciomap; Type: TABLE; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE TABLE pad_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.pad_traducciomap OWNER TO pinbaladmin;

--
-- Name: pad_campformulari_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_campformulari
    ADD CONSTRAINT pad_campformulari_pk PRIMARY KEY (campformulariid);


--
-- Name: pad_campsolicitud_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_campsolicitud
    ADD CONSTRAINT pad_campsolicitud_pk PRIMARY KEY (campsolicitudid);


--
-- Name: pad_document_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_document
    ADD CONSTRAINT pad_document_pk PRIMARY KEY (documentid);


--
-- Name: pad_documentsolicitud_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_documentsolicitud
    ADD CONSTRAINT pad_documentsolicitud_pk PRIMARY KEY (documentsolicitudid);


--
-- Name: pad_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_entitat
    ADD CONSTRAINT pad_entitat_pk PRIMARY KEY (entitatid);


--
-- Name: pad_entitatservei_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_entitatservei
    ADD CONSTRAINT pad_entitatservei_pk PRIMARY KEY (entitatserveiid);


--
-- Name: pad_estatservei_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_estatservei
    ADD CONSTRAINT pad_estatservei_pk PRIMARY KEY (estatserveiid);


--
-- Name: pad_estatsolicitud_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_estatsolicitud
    ADD CONSTRAINT pad_estatsolicitud_pk PRIMARY KEY (estatsolicitudid);


--
-- Name: pad_estatsolicitudservei_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_estatsolicitudservei
    ADD CONSTRAINT pad_estatsolicitudservei_pk PRIMARY KEY (estatsolicitudserveiid);


--
-- Name: pad_estattiquet_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_estattiquet
    ADD CONSTRAINT pad_estattiquet_pk PRIMARY KEY (estattiquetid);


--
-- Name: pad_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_fitxer
    ADD CONSTRAINT pad_fitxer_pk PRIMARY KEY (fitxerid);


--
-- Name: pad_formulari_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_formulari
    ADD CONSTRAINT pad_formulari_pk PRIMARY KEY (formulariid);


--
-- Name: pad_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_idioma
    ADD CONSTRAINT pad_idioma_pk PRIMARY KEY (idiomaid);


--
-- Name: pad_servei_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_servei
    ADD CONSTRAINT pad_servei_pk PRIMARY KEY (serveiid);


--
-- Name: pad_solicitud_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_solicitud
    ADD CONSTRAINT pad_solicitud_pk PRIMARY KEY (solicitudid);


--
-- Name: pad_solicitudservei_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_solicitudservei
    ADD CONSTRAINT pad_solicitudservei_pk PRIMARY KEY (id);


--
-- Name: pad_soliservei_multiple_uk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_solicitudservei
    ADD CONSTRAINT pad_soliservei_multiple_uk UNIQUE (serveiid, solicitudid);


--
-- Name: pad_tipustiquet_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_tipustiquet
    ADD CONSTRAINT pad_tipustiquet_pk PRIMARY KEY (tipustiquetid);


--
-- Name: pad_tiquet_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_tiquet
    ADD CONSTRAINT pad_tiquet_pk PRIMARY KEY (tiquetid);


--
-- Name: pad_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_traduccio
    ADD CONSTRAINT pad_traduccio_pk PRIMARY KEY (traduccioid);


--
-- Name: pad_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin; Tablespace: 
--

ALTER TABLE ONLY pad_traducciomap
    ADD CONSTRAINT pad_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- Name: pad_campform_formulariid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_campform_formulariid_fk_i ON pad_campformulari USING btree (formulariid);


--
-- Name: pad_campformulari_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_campformulari_pk_i ON pad_campformulari USING btree (campformulariid);


--
-- Name: pad_campsoli_campformid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_campsoli_campformid_fk_i ON pad_campsolicitud USING btree (campformulariid);


--
-- Name: pad_campsoli_soliservid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_campsoli_soliservid_fk_i ON pad_campsolicitud USING btree (solicitudserveiid);


--
-- Name: pad_campsolicitud_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_campsolicitud_pk_i ON pad_campsolicitud USING btree (campsolicitudid);


--
-- Name: pad_docsoli_documentid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_docsoli_documentid_fk_i ON pad_documentsolicitud USING btree (documentid);


--
-- Name: pad_docsoli_solicitudid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_docsoli_solicitudid_fk_i ON pad_documentsolicitud USING btree (solicitudid);


--
-- Name: pad_document_fitxer_firm_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_document_fitxer_firm_fk_i ON pad_document USING btree (fitxerfirmatid);


--
-- Name: pad_document_fitxer_orig_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_document_fitxer_orig_fk_i ON pad_document USING btree (fitxeroriginalid);


--
-- Name: pad_document_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_document_pk_i ON pad_document USING btree (documentid);


--
-- Name: pad_documentsolicitud_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_documentsolicitud_pk_i ON pad_documentsolicitud USING btree (documentsolicitudid);


--
-- Name: pad_entitat_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_entitat_pk_i ON pad_entitat USING btree (entitatid);


--
-- Name: pad_entitatservei_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_entitatservei_pk_i ON pad_entitatservei USING btree (entitatserveiid);


--
-- Name: pad_estatservei_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_estatservei_pk_i ON pad_estatservei USING btree (estatserveiid);


--
-- Name: pad_estatsolicitud_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_estatsolicitud_pk_i ON pad_estatsolicitud USING btree (estatsolicitudid);


--
-- Name: pad_estatsolicitudservei_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_estatsolicitudservei_pk_i ON pad_estatsolicitudservei USING btree (estatsolicitudserveiid);


--
-- Name: pad_estattiquet_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_estattiquet_pk_i ON pad_estattiquet USING btree (estattiquetid);


--
-- Name: pad_fitxer_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_fitxer_pk_i ON pad_fitxer USING btree (fitxerid);


--
-- Name: pad_formulari_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_formulari_fitxerid_fk_i ON pad_formulari USING btree (fitxerid);


--
-- Name: pad_formulari_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_formulari_pk_i ON pad_formulari USING btree (formulariid);


--
-- Name: pad_idioma_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_idioma_pk_i ON pad_idioma USING btree (idiomaid);


--
-- Name: pad_servei_entservid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_servei_entservid_fk_i ON pad_servei USING btree (entitatserveiid);


--
-- Name: pad_servei_estatserveiid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_servei_estatserveiid_fk_i ON pad_servei USING btree (estatserveiid);


--
-- Name: pad_servei_formulariid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_servei_formulariid_fk_i ON pad_servei USING btree (formulariid);


--
-- Name: pad_servei_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_servei_pk_i ON pad_servei USING btree (serveiid);


--
-- Name: pad_solicitud_docsoli_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_solicitud_docsoli_fk_i ON pad_solicitud USING btree (documentsolicitudid);


--
-- Name: pad_solicitud_entitatid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_solicitud_entitatid_fk_i ON pad_solicitud USING btree (entitatid);


--
-- Name: pad_solicitud_estatid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_solicitud_estatid_fk_i ON pad_solicitud USING btree (estatid);


--
-- Name: pad_solicitud_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_solicitud_pk_i ON pad_solicitud USING btree (solicitudid);


--
-- Name: pad_solicitudservei_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_solicitudservei_pk_i ON pad_solicitudservei USING btree (id);


--
-- Name: pad_soliservei_estsolserv_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_soliservei_estsolserv_fk_i ON pad_solicitudservei USING btree (estatsolicitudserveiid);


--
-- Name: pad_soliservei_serveiid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_soliservei_serveiid_fk_i ON pad_solicitudservei USING btree (serveiid);


--
-- Name: pad_soliservei_soliid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_soliservei_soliid_fk_i ON pad_solicitudservei USING btree (solicitudid);


--
-- Name: pad_tipustiquet_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tipustiquet_pk_i ON pad_tipustiquet USING btree (tipustiquetid);


--
-- Name: pad_tiquet_adjunt1id_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tiquet_adjunt1id_fk_i ON pad_tiquet USING btree (adjunt1id);


--
-- Name: pad_tiquet_adjunt2id_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tiquet_adjunt2id_fk_i ON pad_tiquet USING btree (adjunt2id);


--
-- Name: pad_tiquet_estattiquetid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tiquet_estattiquetid_fk_i ON pad_tiquet USING btree (estattiquetid);


--
-- Name: pad_tiquet_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tiquet_pk_i ON pad_tiquet USING btree (tiquetid);


--
-- Name: pad_tiquet_tipustiquetid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_tiquet_tipustiquetid_fk_i ON pad_tiquet USING btree (tipustiquetid);


--
-- Name: pad_traduccio_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_traduccio_pk_i ON pad_traduccio USING btree (traduccioid);


--
-- Name: pad_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_traducciomap_idiomaid_fk_i ON pad_traducciomap USING btree (idiomaid);


--
-- Name: pad_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin; Tablespace: 
--

CREATE INDEX pad_traducciomap_pk_i ON pad_traducciomap USING btree (traducciomapid);


--
-- Name: pad_campform_formulari_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_campformulari
    ADD CONSTRAINT pad_campform_formulari_fk FOREIGN KEY (formulariid) REFERENCES pad_formulari(formulariid);


--
-- Name: pad_campsoli_campform_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_campsolicitud
    ADD CONSTRAINT pad_campsoli_campform_fk FOREIGN KEY (campformulariid) REFERENCES pad_campformulari(campformulariid);


--
-- Name: pad_campsoli_soliservei_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_campsolicitud
    ADD CONSTRAINT pad_campsoli_soliservei_fk FOREIGN KEY (solicitudserveiid) REFERENCES pad_solicitudservei(id);


--
-- Name: pad_docsoli_document_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_documentsolicitud
    ADD CONSTRAINT pad_docsoli_document_fk FOREIGN KEY (documentid) REFERENCES pad_document(documentid);


--
-- Name: pad_docsoli_solicitud_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_documentsolicitud
    ADD CONSTRAINT pad_docsoli_solicitud_fk FOREIGN KEY (solicitudid) REFERENCES pad_solicitud(solicitudid);


--
-- Name: pad_document_fitxer_firm_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_document
    ADD CONSTRAINT pad_document_fitxer_firm_fk FOREIGN KEY (fitxerfirmatid) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_document_fitxer_orig_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_document
    ADD CONSTRAINT pad_document_fitxer_orig_fk FOREIGN KEY (fitxeroriginalid) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_formulari_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_formulari
    ADD CONSTRAINT pad_formulari_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_servei_entiservei_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_servei
    ADD CONSTRAINT pad_servei_entiservei_fk FOREIGN KEY (entitatserveiid) REFERENCES pad_entitatservei(entitatserveiid);


--
-- Name: pad_servei_estatserv_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_servei
    ADD CONSTRAINT pad_servei_estatserv_fk FOREIGN KEY (estatserveiid) REFERENCES pad_estatservei(estatserveiid);


--
-- Name: pad_servei_formulari_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_servei
    ADD CONSTRAINT pad_servei_formulari_fk FOREIGN KEY (formulariid) REFERENCES pad_formulari(formulariid);


--
-- Name: pad_solicitud_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitud
    ADD CONSTRAINT pad_solicitud_entitat_fk FOREIGN KEY (entitatid) REFERENCES pad_entitat(entitatid);


--
-- Name: pad_solicitud_estatsoli_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitud
    ADD CONSTRAINT pad_solicitud_estatsoli_fk FOREIGN KEY (estatid) REFERENCES pad_estatsolicitud(estatsolicitudid);


--
-- Name: pad_solicitud_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitud
    ADD CONSTRAINT pad_solicitud_fitxer_fk FOREIGN KEY (documentsolicitudid) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_soliservei_estsolserv_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitudservei
    ADD CONSTRAINT pad_soliservei_estsolserv_fk FOREIGN KEY (estatsolicitudserveiid) REFERENCES pad_estatsolicitudservei(estatsolicitudserveiid);


--
-- Name: pad_soliservei_servei_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitudservei
    ADD CONSTRAINT pad_soliservei_servei_fk FOREIGN KEY (serveiid) REFERENCES pad_servei(serveiid);


--
-- Name: pad_soliservei_solicitud_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_solicitudservei
    ADD CONSTRAINT pad_soliservei_solicitud_fk FOREIGN KEY (solicitudid) REFERENCES pad_solicitud(solicitudid);


--
-- Name: pad_tiquet_estattique_estat_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_tiquet
    ADD CONSTRAINT pad_tiquet_estattique_estat_fk FOREIGN KEY (estattiquetid) REFERENCES pad_estattiquet(estattiquetid);


--
-- Name: pad_tiquet_fitxer_adj1_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_tiquet
    ADD CONSTRAINT pad_tiquet_fitxer_adj1_fk FOREIGN KEY (adjunt1id) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_tiquet_fitxer_adj2_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_tiquet
    ADD CONSTRAINT pad_tiquet_fitxer_adj2_fk FOREIGN KEY (adjunt2id) REFERENCES pad_fitxer(fitxerid);


--
-- Name: pad_tiquet_tiptiquet_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_tiquet
    ADD CONSTRAINT pad_tiquet_tiptiquet_fk FOREIGN KEY (tipustiquetid) REFERENCES pad_tipustiquet(tipustiquetid);


--
-- Name: pad_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin
--

ALTER TABLE ONLY pad_traducciomap
    ADD CONSTRAINT pad_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pad_traduccio(traduccioid);


--
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-12-11 12:53:00

--
-- PostgreSQL database dump complete
--


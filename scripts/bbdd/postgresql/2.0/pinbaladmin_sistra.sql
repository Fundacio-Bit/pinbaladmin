﻿
-- TOC entry 224 (class 1259 OID 262970)
-- Name: pad_tramit_a_pers_aut; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_a_pers_aut (
    persautid bigint DEFAULT nextval('public.pad_tramit_a_pers_aut_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nif character varying(30) NOT NULL,
    mail character varying(100) NOT NULL,
    telefon character varying(10) NOT NULL,
    nom character varying(40) NOT NULL,
    llinatge1 character varying(40) NOT NULL,
    llinatge2 character varying(40),
    datatramit timestamp without time zone NOT NULL
);


ALTER TABLE public.pad_tramit_a_pers_aut OWNER TO pinbaladmin2;

--
-- TOC entry 226 (class 1259 OID 262982)
-- Name: pad_tramit_b_dades_soli; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_b_dades_soli (
    dadessoliid bigint DEFAULT nextval('public.pad_tramit_b_dades_soli_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    tipussolicitud bigint NOT NULL,
    entorn character varying(20) DEFAULT false NOT NULL
);


ALTER TABLE public.pad_tramit_b_dades_soli OWNER TO pinbaladmin2;

--
-- TOC entry 228 (class 1259 OID 262997)
-- Name: pad_tramit_c_dades_cesi; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_c_dades_cesi (
    dadescesiid bigint DEFAULT nextval('public.pad_tramit_c_dades_cesi_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    denominacio character varying(240) NOT NULL,
    nif character varying(30) NOT NULL,
    responsable character varying(240) NOT NULL,
    dir3responsable character varying(30) NOT NULL,
    dir3arrel character varying(30) NOT NULL,
    direccio character varying(240) NOT NULL,
    codipostal character varying(10) NOT NULL,
    municipi character varying(40) NOT NULL
);


ALTER TABLE public.pad_tramit_c_dades_cesi OWNER TO pinbaladmin2;

--
-- TOC entry 230 (class 1259 OID 263015)
-- Name: pad_tramit_d_cte_aut; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_d_cte_aut (
    cteautid bigint DEFAULT nextval('public.pad_tramit_d_cte_aut_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nif character varying(30) NOT NULL,
    nom character varying(40) NOT NULL,
    llinatge1 character varying(40) NOT NULL,
    llinatge2 character varying(40),
    carrec character varying(100) NOT NULL,
    telefon character varying(10) NOT NULL,
    mail character varying(100) NOT NULL
);


ALTER TABLE public.pad_tramit_d_cte_aut OWNER TO pinbaladmin2;

--
-- TOC entry 232 (class 1259 OID 263030)
-- Name: pad_tramit_e_cte_aud; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_e_cte_aud (
    cteaudid bigint DEFAULT nextval('public.pad_tramit_e_cte_aud_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nif character varying(30) NOT NULL,
    nom character varying(40) NOT NULL,
    llinatge1 character varying(40) NOT NULL,
    llinatge2 character varying(40),
    carrec character varying(100) NOT NULL,
    telefon character varying(10) NOT NULL,
    mail character varying(100) NOT NULL
);


ALTER TABLE public.pad_tramit_e_cte_aud OWNER TO pinbaladmin2;

--
-- TOC entry 234 (class 1259 OID 263045)
-- Name: pad_tramit_f_cte_tec; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_f_cte_tec (
    ctetecid bigint DEFAULT nextval('public.pad_tramit_f_cte_tec_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nif character varying(30) NOT NULL,
    nom character varying(40) NOT NULL,
    llinatge1 character varying(40) NOT NULL,
    llinatge2 character varying(40),
    carrec character varying(100),
    telefon character varying(10),
    mail character varying(100)
);


ALTER TABLE public.pad_tramit_f_cte_tec OWNER TO pinbaladmin2;

--
-- TOC entry 236 (class 1259 OID 263060)
-- Name: pad_tramit_g_dades_tit; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_g_dades_tit (
    dadestitid bigint DEFAULT nextval('public.pad_tramit_g_dades_tit_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nif character varying(30) NOT NULL,
    nom character varying(40) NOT NULL,
    llinatge1 character varying(40) NOT NULL,
    llinatge2 character varying(40),
    carrec character varying(100) NOT NULL
);


ALTER TABLE public.pad_tramit_g_dades_tit OWNER TO pinbaladmin2;

--
-- TOC entry 238 (class 1259 OID 263142)
-- Name: pad_tramit_h_proc; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_h_proc (
    procid bigint DEFAULT nextval('public.pad_tramit_h_proc_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    tipus character varying(240) NOT NULL,
    nom character varying(240) NOT NULL,
    codi character varying(30) NOT NULL,
    urlseu character varying(240) NOT NULL,
    caducitat boolean NOT NULL,
    caducitatdata timestamp without time zone,
    descripcio character varying(240) NOT NULL,
    peticionsaldia bigint NOT NULL,
    peticionsalmes bigint NOT NULL,
    periodico boolean NOT NULL,
    automatizado boolean NOT NULL
);


ALTER TABLE public.pad_tramit_h_proc OWNER TO pinbaladmin2;

--
-- TOC entry 240 (class 1259 OID 263171)
-- Name: pad_tramit_i_serv; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_i_serv (
    servid bigint DEFAULT nextval('public.pad_tramit_i_serv_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    nom character varying(240) NOT NULL,
    codi character varying(100) NOT NULL,
    norma character varying(240) NOT NULL,
    articles character varying(60) NOT NULL,
    consentiment character varying(30) NOT NULL,
    urlnorma character varying(240) NOT NULL,
    consentimentpublicat character varying(30) NOT NULL,
    urlconsentiment character varying(240) NOT NULL
);


ALTER TABLE public.pad_tramit_i_serv OWNER TO pinbaladmin2;

--
-- TOC entry 242 (class 1259 OID 271276)
-- Name: pad_tramit_j_consent; Type: TABLE; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE TABLE public.pad_tramit_j_consent (
    consentid bigint DEFAULT nextval('public.pad_tramit_j_consent_seq'::regclass) NOT NULL,
    tramitid bigint NOT NULL,
    adjuntid bigint NOT NULL
);


ALTER TABLE public.pad_tramit_j_consent OWNER TO pinbaladmin2;

--
-- TOC entry 2034 (class 2606 OID 262976)
-- Name: pad_tramit_a_pers_aut_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_a_pers_aut
    ADD CONSTRAINT pad_tramit_a_pers_aut_pk PRIMARY KEY (persautid);


--
-- TOC entry 2039 (class 2606 OID 262987)
-- Name: pad_tramit_b_dades_soli_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_b_dades_soli
    ADD CONSTRAINT pad_tramit_b_dades_soli_pk PRIMARY KEY (dadessoliid);


--
-- TOC entry 2043 (class 2606 OID 263005)
-- Name: pad_tramit_c_dades_cesi_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_c_dades_cesi
    ADD CONSTRAINT pad_tramit_c_dades_cesi_pk PRIMARY KEY (dadescesiid);


--
-- TOC entry 2047 (class 2606 OID 263020)
-- Name: pad_tramit_d_cte_aut_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_d_cte_aut
    ADD CONSTRAINT pad_tramit_d_cte_aut_pk PRIMARY KEY (cteautid);


--
-- TOC entry 2051 (class 2606 OID 263035)
-- Name: pad_tramit_e_cte_aud_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_e_cte_aud
    ADD CONSTRAINT pad_tramit_e_cte_aud_pk PRIMARY KEY (cteaudid);


--
-- TOC entry 2055 (class 2606 OID 263050)
-- Name: pad_tramit_f_cte_tec_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_f_cte_tec
    ADD CONSTRAINT pad_tramit_f_cte_tec_pk PRIMARY KEY (ctetecid);


--
-- TOC entry 2059 (class 2606 OID 263065)
-- Name: pad_tramit_g_dades_tit_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_g_dades_tit
    ADD CONSTRAINT pad_tramit_g_dades_tit_pk PRIMARY KEY (dadestitid);


--
-- TOC entry 2063 (class 2606 OID 263150)
-- Name: pad_tramit_h_proc_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_h_proc
    ADD CONSTRAINT pad_tramit_h_proc_pk PRIMARY KEY (procid);


--
-- TOC entry 2067 (class 2606 OID 263179)
-- Name: pad_tramit_i_serv_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_i_serv
    ADD CONSTRAINT pad_tramit_i_serv_pk PRIMARY KEY (servid);


--
-- TOC entry 2071 (class 2606 OID 271281)
-- Name: pad_tramit_j_consent_pk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_j_consent
    ADD CONSTRAINT pad_tramit_j_consent_pk PRIMARY KEY (consentid);


--
-- TOC entry 2037 (class 2606 OID 262978)
-- Name: pad_tramita_tramitid_uk; Type: CONSTRAINT; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

ALTER TABLE ONLY public.pad_tramit_a_pers_aut
    ADD CONSTRAINT pad_tramita_tramitid_uk UNIQUE (tramitid);


--
-- TOC entry 2035 (class 1259 OID 262979)
-- Name: pad_tramit_a_pers_aut_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_a_pers_aut_pk_i ON public.pad_tramit_a_pers_aut USING btree (persautid);


--
-- TOC entry 2040 (class 1259 OID 262993)
-- Name: pad_tramit_b_dades_soli_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_b_dades_soli_pk_i ON public.pad_tramit_b_dades_soli USING btree (dadessoliid);


--
-- TOC entry 2044 (class 1259 OID 263011)
-- Name: pad_tramit_c_dades_cesi_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_c_dades_cesi_pk_i ON public.pad_tramit_c_dades_cesi USING btree (dadescesiid);


--
-- TOC entry 2048 (class 1259 OID 263026)
-- Name: pad_tramit_d_cte_aut_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_d_cte_aut_pk_i ON public.pad_tramit_d_cte_aut USING btree (cteautid);


--
-- TOC entry 2052 (class 1259 OID 263041)
-- Name: pad_tramit_e_cte_aud_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_e_cte_aud_pk_i ON public.pad_tramit_e_cte_aud USING btree (cteaudid);


--
-- TOC entry 2056 (class 1259 OID 263056)
-- Name: pad_tramit_f_cte_tec_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_f_cte_tec_pk_i ON public.pad_tramit_f_cte_tec USING btree (ctetecid);


--
-- TOC entry 2060 (class 1259 OID 263071)
-- Name: pad_tramit_g_dades_tit_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_g_dades_tit_pk_i ON public.pad_tramit_g_dades_tit USING btree (dadestitid);


--
-- TOC entry 2064 (class 1259 OID 263156)
-- Name: pad_tramit_h_proc_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_h_proc_pk_i ON public.pad_tramit_h_proc USING btree (procid);


--
-- TOC entry 2068 (class 1259 OID 263185)
-- Name: pad_tramit_i_serv_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_i_serv_pk_i ON public.pad_tramit_i_serv USING btree (servid);


--
-- TOC entry 2072 (class 1259 OID 271292)
-- Name: pad_tramit_j_consent_pk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramit_j_consent_pk_i ON public.pad_tramit_j_consent USING btree (consentid);


--
-- TOC entry 2041 (class 1259 OID 262994)
-- Name: pad_tramitb_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitb_tramitid_fk_i ON public.pad_tramit_b_dades_soli USING btree (tramitid);


--
-- TOC entry 2045 (class 1259 OID 263012)
-- Name: pad_tramitc_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitc_tramitid_fk_i ON public.pad_tramit_c_dades_cesi USING btree (tramitid);


--
-- TOC entry 2049 (class 1259 OID 263027)
-- Name: pad_tramitd_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitd_tramitid_fk_i ON public.pad_tramit_d_cte_aut USING btree (tramitid);


--
-- TOC entry 2053 (class 1259 OID 263042)
-- Name: pad_tramite_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramite_tramitid_fk_i ON public.pad_tramit_e_cte_aud USING btree (tramitid);


--
-- TOC entry 2057 (class 1259 OID 263057)
-- Name: pad_tramitf_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitf_tramitid_fk_i ON public.pad_tramit_f_cte_tec USING btree (tramitid);


--
-- TOC entry 2061 (class 1259 OID 263072)
-- Name: pad_tramitg_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitg_tramitid_fk_i ON public.pad_tramit_g_dades_tit USING btree (tramitid);


--
-- TOC entry 2065 (class 1259 OID 263157)
-- Name: pad_tramith_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramith_tramitid_fk_i ON public.pad_tramit_h_proc USING btree (tramitid);


--
-- TOC entry 2069 (class 1259 OID 263186)
-- Name: pad_tramiti_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramiti_tramitid_fk_i ON public.pad_tramit_i_serv USING btree (tramitid);


--
-- TOC entry 2073 (class 1259 OID 271294)
-- Name: pad_tramitj_adjuntid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitj_adjuntid_fk_i ON public.pad_tramit_j_consent USING btree (adjuntid);


--
-- TOC entry 2074 (class 1259 OID 271293)
-- Name: pad_tramitj_tramitid_fk_i; Type: INDEX; Schema: public; Owner: pinbaladmin2; Tablespace: 
--

CREATE INDEX pad_tramitj_tramitid_fk_i ON public.pad_tramit_j_consent USING btree (tramitid);


--
-- TOC entry 2075 (class 2606 OID 262988)
-- Name: pad_tramitb_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_b_dades_soli
    ADD CONSTRAINT pad_tramitb_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2076 (class 2606 OID 263006)
-- Name: pad_tramitc_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_c_dades_cesi
    ADD CONSTRAINT pad_tramitc_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2077 (class 2606 OID 263021)
-- Name: pad_tramitd_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_d_cte_aut
    ADD CONSTRAINT pad_tramitd_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2078 (class 2606 OID 263036)
-- Name: pad_tramite_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_e_cte_aud
    ADD CONSTRAINT pad_tramite_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2079 (class 2606 OID 263051)
-- Name: pad_tramitf_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_f_cte_tec
    ADD CONSTRAINT pad_tramitf_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2080 (class 2606 OID 263066)
-- Name: pad_tramitg_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_g_dades_tit
    ADD CONSTRAINT pad_tramitg_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2081 (class 2606 OID 263151)
-- Name: pad_tramith_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_h_proc
    ADD CONSTRAINT pad_tramith_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2082 (class 2606 OID 263180)
-- Name: pad_tramiti_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_i_serv
    ADD CONSTRAINT pad_tramiti_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


--
-- TOC entry 2084 (class 2606 OID 271287)
-- Name: pad_tramitj_fitxer_adj_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_j_consent
    ADD CONSTRAINT pad_tramitj_fitxer_adj_fk FOREIGN KEY (adjuntid) REFERENCES public.pad_fitxer(fitxerid);


--
-- TOC entry 2083 (class 2606 OID 271282)
-- Name: pad_tramitj_tramita_fk; Type: FK CONSTRAINT; Schema: public; Owner: pinbaladmin2
--

ALTER TABLE ONLY public.pad_tramit_j_consent
    ADD CONSTRAINT pad_tramitj_tramita_fk FOREIGN KEY (tramitid) REFERENCES public.pad_tramit_a_pers_aut(tramitid);


-- Completed on 2023-10-04 12:40:08

--
-- PostgreSQL database dump complete
--

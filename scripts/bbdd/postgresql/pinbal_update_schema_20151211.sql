



ALTER TABLE pad_tiquet ADD COLUMN entorn integer DEFAULT 1 NOT NULL;
ALTER TABLE pad_tiquet ADD COLUMN adjunt1id bigint;
ALTER TABLE pad_tiquet ADD COLUMN adjunt2id bigint;
ALTER TABLE pad_tiquet ADD COLUMN dataincidencia timestamp without time zone;


    
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


    
    
    

ALTER TABLE pad_tiquet DROP CONSTRAINT pad_tiquet_estattiq_fk;
ALTER TABLE pad_tiquet ADD CONSTRAINT pad_tiquet_estattique_estat_fk  FOREIGN KEY (estattiquetid)  REFERENCES pad_estattiquet (estattiquetid);


 -- Es recomanable tenir un index de la clau forania.
 create index pad_tiquet_adjunt1id_fk_i on pad_tiquet (adjunt1id);

 -- Es recomanable tenir un index de la clau forania.
 create index pad_tiquet_adjunt2id_fk_i on pad_tiquet (adjunt2id);
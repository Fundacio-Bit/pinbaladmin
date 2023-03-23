
-- ==== ERRORS EN TAULA ]EstatTiquet[:

 -- Es recomanable tenir un index de la clau primaria.
 create index pad_estattiquet_pk_i on pad_estattiquet (estattiquetid);


-- ==== ERRORS EN TAULA ]TipusTiquet[:

 -- Es recomanable tenir un index de la clau primaria.
 create index pad_tipustiquet_pk_i on pad_tipustiquet (tipustiquetid);


-- ==== ERRORS EN TAULA ]Tiquet[:

 -- Es recomanable tenir un index de la clau primaria.
 create index pad_tiquet_pk_i on pad_tiquet (tiquetid);
 -- Es recomanable tenir un index de la clau forania.
 create index pad_tiquet_estattiquetid_fk_i on pad_tiquet (estattiquetid);
 -- Es recomanable tenir un index de la clau forania.
 create index pad_tiquet_tipustiquetid_fk_i on pad_tiquet (tipustiquetid);


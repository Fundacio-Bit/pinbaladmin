--Parche per soliciutds fusionades que tenen missatges no llegits a les originals.

-- Cercam les que estan en estar fusionades, i tenen events sense llegir. Aquestes solicituds les hem de pasar a un altre estat per a que surtin al llistat.
UPDATE PAD_SOLICITUD 
SET estatid = -4 -- 'SOLI_ESTAT_FUSIONADA_REVISAR'
WHERE solicitudid IN (
    SELECT s.solicitudid FROM PAD_SOLICITUD s, PAD_EVENT e
    WHERE s.solicitudid = e.solicitudid
    AND s.estatid = -5 -- 'SOLI_ESTAT_FUSIONADA'
    AND e.nollegit = true 
)

-- Una vegada s'han processat, les podem pasar a l'estat original, fusionada.
UPDATE PAD_SOLICITUD
SET estatid = -5 -- 'SOLI_ESTAT_FUSIONADA'
WHERE estatid = -4 -- 'SOLI_ESTAT_FUSIONADA_REVISAR'


-- Antes el estado de las solicitudes fusionadas era -5, ahora es 4, por lo que hay que actualizar el estado de las solicitudes fusionadas a -5 para que sigan apareciendo en el listado.
UPDATE PAD_SOLICITUD
SET estatid = -4 -- 'SOLI_ESTAT_FUSIONADA_REVISAR'
WHERE estatid = 5 -- 'ANTIGO_ESTADO_REVISAR_FUSIONADA'

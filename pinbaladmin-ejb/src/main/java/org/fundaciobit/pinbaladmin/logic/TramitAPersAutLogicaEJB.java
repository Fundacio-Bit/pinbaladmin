package org.fundaciobit.pinbaladmin.logic;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.ejb.TramitAPersAutEJB;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitAPersAutLogicaEJB")
public class TramitAPersAutLogicaEJB extends TramitAPersAutEJB implements TramitAPersAutLogicaService {

    @EJB(mappedName = TramitBDadesSoliLogicaService.JNDI_NAME)
    protected TramitBDadesSoliLogicaService tramitBEjb;
    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    protected TramitCDadesCesiLogicaService tramitCEjb;
    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDEjb;
    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    protected TramitECteAudLogicaService tramitEEjb;
    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFEjb;
    @EJB(mappedName = TramitGDadesTitLogicaService.JNDI_NAME)
    protected TramitGDadesTitLogicaService tramitGEjb;
    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    protected TramitHProcLogicaService tramitHEjb;
    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIEjb;
    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJEjb;

    public static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    @PermitAll
    public TramitAPersAutJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitAPersAut create(TramitAPersAut instance) throws I18NException {
        log.info("TramitID: " + instance.getTramitid());
        TramitAPersAut tramitA = super.create(instance);
        tramitA.setTramitid(tramitA.getPersautid());
        this.update(tramitA);
        log.info("TramitID: " + tramitA.getTramitid());
        return tramitA;
    }

    @Override
    public void deleteFull(Long tramitID) throws I18NException {
        tramitBEjb.delete(TramitBDadesSoliFields.TRAMITID.equal(tramitID));
        tramitCEjb.delete(TramitCDadesCesiFields.TRAMITID.equal(tramitID));
        tramitDEjb.delete(TramitDCteAutFields.TRAMITID.equal(tramitID));
        tramitEEjb.delete(TramitECteAudFields.TRAMITID.equal(tramitID));
        tramitFEjb.delete(TramitFCteTecFields.TRAMITID.equal(tramitID));
        tramitGEjb.delete(TramitGDadesTitFields.TRAMITID.equal(tramitID));
        tramitHEjb.delete(TramitHProcFields.TRAMITID.equal(tramitID));
        tramitIEjb.delete(TramitIServFields.TRAMITID.equal(tramitID));
        tramitJEjb.delete(TramitJConsentFields.TRAMITID.equal(tramitID));

        super.delete(TramitAPersAutFields.TRAMITID.equal(tramitID));
    }

    @Override
    public void generaXml(Long tramitID) throws I18NException {

        try {
            String plantilla = FileUtils.readFileToString(new File(Configuracio.getTemplateTramitSistraXml()));

            Map<String, Object> map = new HashMap<String, Object>();

            List<List<?>> listas = Arrays.asList(this.select(TRAMITID.equal(tramitID)),
                    tramitBEjb.select(TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                    tramitCEjb.select(TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                    tramitDEjb.select(TramitDCteAutFields.TRAMITID.equal(tramitID)),
                    tramitEEjb.select(TramitECteAudFields.TRAMITID.equal(tramitID)),
                    tramitFEjb.select(TramitFCteTecFields.TRAMITID.equal(tramitID)),
                    tramitGEjb.select(TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                    tramitHEjb.select(TramitHProcFields.TRAMITID.equal(tramitID)),
                    tramitIEjb.select(TramitIServFields.TRAMITID.equal(tramitID)));

            List<String> keys = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");

            for (int i = 0; i < listas.size(); i++) {
                List<?> lista = listas.get(i);
                if (!lista.isEmpty()) {
                    String letra = keys.get(i);
                    Object obj = lista.get(0);
                    map.put(letra, obj);

                    switch (letra) {
                        case "A":
                            TramitAPersAut A = (TramitAPersAut) obj;
                            if (A.getLlinatge2() == null) {
                                A.setLlinatge2("");
                            }
                            String fullNameA = toFullName(A.getNom(), A.getLlinatge1(), A.getLlinatge2());
                            map.put("fullNameA", fullNameA);
                        break;
                        case "B":
                            TramitBDadesSoli B = (TramitBDadesSoli) obj;
                            String tipussolicitudNom = tramitBEjb.getTipussolicitudValue(B.getTipussolicitud());
                            map.put("tipussolicitudNom", tipussolicitudNom);

                            map.put("pre", "Preproducció");
                            map.put("pro", "Producció");
                        break;
                        case "C":
                            TramitCDadesCesi C = (TramitCDadesCesi) obj;
                            String denominacioNom = tramitCEjb.getDenominacioValue(C.getDenominacio());
                            map.put("denominacioNom", denominacioNom);

                            String municipiNom = tramitCEjb.getMunicipiValue(C.getMunicipi());
                            map.put("municipiNom", municipiNom);
                        break;
                        case "D":
                            TramitDCteAut D = (TramitDCteAut) obj;
                            String fullNameD = toFullName(D.getNom(), D.getLlinatge1(), D.getLlinatge2());
                            map.put("fullNameD", fullNameD);
                        break;
                        case "E":
                            TramitECteAud E = (TramitECteAud) obj;
                            String fullNameE = toFullName(E.getNom(), E.getLlinatge1(), E.getLlinatge2());
                            map.put("fullNameE", fullNameE);

                        break;
                        case "F":
                            TramitFCteTec F = (TramitFCteTec) obj;
                            String fullNameF = toFullName(F.getNom(), F.getLlinatge1(), F.getLlinatge2());
                            map.put("fullNameF", fullNameF);

                        break;
                        case "G":
                            TramitGDadesTit G = (TramitGDadesTit) obj;
                            String fullNameG = toFullName(G.getNom(), G.getLlinatge1(), G.getLlinatge2());
                            map.put("fullNameG", fullNameG);
                        break;
                        case "H":
                            TramitHProc H = (TramitHProc) obj;

                            Long tipus = Long.parseLong(H.getTipus());
                            String tipusProcedimentNom = getTipusProcediment(tipus);
                            map.put("tipusProcedimentNom", tipusProcedimentNom);

                            String dataCaducitat;
                            if (H.isCaducitat()) {
                                dataCaducitat = SDF.format(H.getCaducitatdata());
                            } else {
                                dataCaducitat = "";
                            }
                            map.put("dataCaducitat", dataCaducitat);
                        break;
                        case "I":
                            map.put("noop", "No Oposició");
                            map.put("llei", "Llei");

                            map.put("servicios", lista);
                            String[] codis = new String[lista.size()];
                            String[] noms = new String[lista.size()];
                            for (int j = 0; j < lista.size(); j++) {
                                TramitIServ I = (TramitIServ) lista.get(j);
                                codis[j] = I.getCodi();
                                noms[j] =  tramitIEjb.getServeiValue(I.getNom());
                            }

                            /*
                            CONSENTIMIENTO:
                            noop -> No oposició
                            llei -> Llei
                            
                            LDECONSENTIMIENTO:
                            0 -> ...
                            1 -> Publicat
                            2 -> Adjunt
                             */

                            String codisServeisString = String.join(",", codis);
                            map.put("codisServeisString", codisServeisString);
                            map.put("nomServeis", noms);
                        break;
                    }
                }
            }
            String result = TemplateEngine.processExpressionLanguage(plantilla, map);
            FileUtils.writeStringToFile(new File("D:/Projectes/pinbaladmin-files/formulario.xml"), result,
                    StandardCharsets.UTF_8, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long[] getPartsTramitIDs(long tramitID) throws I18NException {

        Long[] tramitIDs = {
                this.executeQueryOne(TramitAPersAutFields.PERSAUTID, TramitAPersAutFields.TRAMITID.equal(tramitID)),
                tramitBEjb.executeQueryOne(TramitBDadesSoliFields.DADESSOLIID,
                        TramitBDadesSoliFields.TRAMITID.equal(tramitID)),
                tramitCEjb.executeQueryOne(TramitCDadesCesiFields.DADESCESIID,
                        TramitCDadesCesiFields.TRAMITID.equal(tramitID)),
                tramitDEjb.executeQueryOne(TramitDCteAutFields.CTEAUTID, TramitDCteAutFields.TRAMITID.equal(tramitID)),
                tramitEEjb.executeQueryOne(TramitECteAudFields.CTEAUDID, TramitECteAudFields.TRAMITID.equal(tramitID)),
                tramitFEjb.executeQueryOne(TramitFCteTecFields.CTETECID, TramitFCteTecFields.TRAMITID.equal(tramitID)),
                tramitGEjb.executeQueryOne(TramitGDadesTitFields.DADESTITID,
                        TramitGDadesTitFields.TRAMITID.equal(tramitID)),
                tramitHEjb.executeQueryOne(TramitHProcFields.PROCID, TramitHProcFields.TRAMITID.equal(tramitID)),
                tramitIEjb.count(TRAMITID.equal(tramitID)), 
                tramitJEjb.executeQueryOne(TramitJConsentFields.CONSENTID, TramitJConsentFields.TRAMITID.equal(tramitID)),
        };

        return tramitIDs;
    }

    public String toFullName(String nom, String l1, String l2) {
        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
        return fullName;
    }

    public String getTipusProcediment(Long key) {
        String tp = null;
        String lang = "es";

        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            if (tipusProcediment.id == key) {
                if (lang.equals("es")) {
                    tp = tipusProcediment.castella;
                } else {
                    tp = tipusProcediment.catala;
                }

                break;
            }
        }

        return tp;
    }
}
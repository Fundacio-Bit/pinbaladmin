package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiEJB;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;

/**
 * 
 * @author anadal
 */
@Stateless(name = "TramitCDadesCesiLogicaEJB")
public class TramitCDadesCesiLogicaEJB extends TramitCDadesCesiEJB implements TramitCDadesCesiLogicaService {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService tramitBDadesSoliLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

    //    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    //    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @Override
    @PermitAll
    public TramitCDadesCesiJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public TramitCDadesCesi create(TramitCDadesCesi instance) throws I18NException {
        log.info("TramitCDadesCesi::TramitID: " + instance.getTramitid());
        return super.create(instance);
    }

    @Override
    public void deleteFull(Long tramitid) throws I18NException {
        //Borrar B i llamar a borrarFullB
        log.info("Borramos con TRAMITID = " + tramitid);
        //        List<TramitBDadesSoli> list = tramitBDadesSoliLogicaEjb.select(TramitBDadesSoliFields.TRAMITID.equal(tramitid));

        Long pk = tramitBDadesSoliLogicaEjb.executeQueryOne(TramitBDadesSoliFields.DADESSOLIID,
                TramitBDadesSoliFields.TRAMITID.equal(tramitid));
        tramitBDadesSoliLogicaEjb.delete(pk);
        log.info("Borramos TramitB");

        tramitBDadesSoliLogicaEjb.deleteFull(tramitid);
    }

    @Override
    public String getMunicipiValue(String municipi) {

        List<StringKeyValue> list = getReferenceListForMunicipi();
        log.info("municipi: " + municipi);

        for (StringKeyValue elem : list) {
            if (elem.getKey().equals(municipi)) {
                return elem.getValue();
            }
        }
        log.info("Salio de acá vacio");
        return null;
    }

    @Override
    public List<StringKeyValue> getReferenceListForMunicipi() {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue("2", "Alaior"));
        __tmp.add(new StringKeyValue("1", "Alaró"));
        __tmp.add(new StringKeyValue("3", "Alcúdia"));
        __tmp.add(new StringKeyValue("4", "Algaida"));
        __tmp.add(new StringKeyValue("5", "Andratx"));
        __tmp.add(new StringKeyValue("901", "Ariany"));
        __tmp.add(new StringKeyValue("6", "Artà"));
        __tmp.add(new StringKeyValue("7", "Banyalbufar"));
        __tmp.add(new StringKeyValue("8", "Binissalem"));
        __tmp.add(new StringKeyValue("9", "Búger"));
        __tmp.add(new StringKeyValue("10", "Bunyola"));
        __tmp.add(new StringKeyValue("11", "Calvià"));
        __tmp.add(new StringKeyValue("12", "Campanet"));
        __tmp.add(new StringKeyValue("13", "Campos"));
        __tmp.add(new StringKeyValue("14", "Capdepera"));
        __tmp.add(new StringKeyValue("15", "Ciutadella de Menorca"));
        __tmp.add(new StringKeyValue("16", "Consell"));
        __tmp.add(new StringKeyValue("17", "Costitx"));
        __tmp.add(new StringKeyValue("18", "Deià"));
        __tmp.add(new StringKeyValue("26", "Eivissa"));
        __tmp.add(new StringKeyValue("64", "Es Castell"));
        __tmp.add(new StringKeyValue("37", "Es Mercadal"));
        __tmp.add(new StringKeyValue("902", "Es Migjorn Gran"));
        __tmp.add(new StringKeyValue("19", "Escorca"));
        __tmp.add(new StringKeyValue("20", "Esporles"));
        __tmp.add(new StringKeyValue("21", "Estellencs"));
        __tmp.add(new StringKeyValue("22", "Felanitx"));
        __tmp.add(new StringKeyValue("23", "Ferreries"));
        __tmp.add(new StringKeyValue("24", "Formentera"));
        __tmp.add(new StringKeyValue("25", "Fornalutx"));
        __tmp.add(new StringKeyValue("27", "Inca"));
        __tmp.add(new StringKeyValue("28", "Lloret de Vistalegre"));
        __tmp.add(new StringKeyValue("29", "Lloseta"));
        __tmp.add(new StringKeyValue("30", "Llubí"));
        __tmp.add(new StringKeyValue("31", "Llucmajor"));
        __tmp.add(new StringKeyValue("33", "Manacor"));
        __tmp.add(new StringKeyValue("34", "Mancor de la Vall"));
        __tmp.add(new StringKeyValue("32", "Maó"));
        __tmp.add(new StringKeyValue("35", "Maria de la Salut"));
        __tmp.add(new StringKeyValue("36", "Marratxí"));
        __tmp.add(new StringKeyValue("38", "Montuïri"));
        __tmp.add(new StringKeyValue("39", "Muro"));
        __tmp.add(new StringKeyValue("40", "Palma"));
        __tmp.add(new StringKeyValue("41", "Petra"));
        __tmp.add(new StringKeyValue("42", "Pollença"));
        __tmp.add(new StringKeyValue("43", "Porreres"));
        __tmp.add(new StringKeyValue("45", "Puigpunyent"));
        __tmp.add(new StringKeyValue("44", "Sa Pobla"));
        __tmp.add(new StringKeyValue("46", "Sant Antoni de Portmany"));
        __tmp.add(new StringKeyValue("49", "Sant Joan"));
        __tmp.add(new StringKeyValue("50", "Sant Joan de Labritja"));
        __tmp.add(new StringKeyValue("48", "Sant Josep de sa Talaia"));
        __tmp.add(new StringKeyValue("51", "Sant Llorenç des Cardassar"));
        __tmp.add(new StringKeyValue("52", "Sant Lluís"));
        __tmp.add(new StringKeyValue("53", "Santa Eugènia"));
        __tmp.add(new StringKeyValue("54", "Santa Eulària des Riu"));
        __tmp.add(new StringKeyValue("55", "Santa Margalida"));
        __tmp.add(new StringKeyValue("56", "Santa Maria del Camí"));
        __tmp.add(new StringKeyValue("57", "Santanyí"));
        __tmp.add(new StringKeyValue("58", "Selva"));
        __tmp.add(new StringKeyValue("47", "Sencelles"));
        __tmp.add(new StringKeyValue("59", "Ses Salines"));
        __tmp.add(new StringKeyValue("60", "Sineu"));
        __tmp.add(new StringKeyValue("61", "Sóller"));
        __tmp.add(new StringKeyValue("62", "Son Servera"));
        __tmp.add(new StringKeyValue("63", "Valldemossa"));
        __tmp.add(new StringKeyValue("65", "Vilafranca de Bonany"));
        return __tmp;
    }
    
    
    
    @Override
    public String getDenominacioValue(String denominacio) throws I18NException {

        List<StringKeyValue> list = getReferenceListForDenominacio();
        log.info("denominacio: " + denominacio);

        for (StringKeyValue elem : list) {
            if (elem.getKey().equals(denominacio)) {
                return elem.getValue();
            }
        }
        log.info("Salio de acá vacio");
        return null;
    }

    @Override
    public List<StringKeyValue> getReferenceListForDenominacio() throws I18NException  {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        List<Entitat> entitats =  entitatEjb.select();
        
        __tmp.add(new StringKeyValue("", "..."));
        for (Entitat entitat : entitats) {
            __tmp.add(new StringKeyValue(entitat.getCIF(), entitat.getNom()));
        }
        return __tmp;
    }
}
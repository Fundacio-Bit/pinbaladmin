package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaEJB;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;

/**
 * 
 * @author anadal
 */
@Stateless(name = "IncidenciaTecnicaLogicaEJB")
public class IncidenciaTecnicaLogicaEJB extends IncidenciaTecnicaEJB implements IncidenciaTecnicaLogicaService {

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.FitxerService fitxerEjb;

    @Override
    @PermitAll
    public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public IncidenciaTecnica create(IncidenciaTecnica instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    public void deleteFull(Long _ID) throws I18NException {

        eventLogicaEjb.delete(EventFields.INCIDENCIATECNICAID.equal(_ID));

        this.delete(_ID);
    }

    @Override
    public IncidenciaTecnica createFromEmail(EmailMessageInfo emi, String creador, String operador, int tipus) throws I18NException {

        java.lang.String subject = emi.getSubject();
        java.lang.String missatge = emi.getBody(); // TODO limit tamany
        
        java.sql.Timestamp data= new Timestamp(System.currentTimeMillis());
        java.sql.Timestamp dataFi = null;
        int estat = Constants.ESTAT_INCIDENCIA_OBERTA;
        java.lang.String nomEntitat = "";
        java.lang.String contacteNom = emi.getNameFrom();
        java.lang.String contacteEmail = emi.getDisplayFrom();
        java.lang.String contacteTelefon = null;
        java.lang.String caidIdentificadorConsulta = null;
        java.lang.String caidNumeroSeguiment = null;
        java.lang.String destinatari = null;
        java.lang.String destinatariEmail = null;

        log.info(destinatariEmail);
        
        if (contacteEmail.contains("governdigital.pinbal@fundaciobit.org") && subject.contains("CAI-")) {
            contacteEmail = "suport@caib.es";
        }
        
        IncidenciaTecnicaJPA itJPA = new IncidenciaTecnicaJPA(subject, missatge, data, dataFi, estat, tipus, nomEntitat,
                contacteNom, contacteEmail, contacteTelefon, caidIdentificadorConsulta, caidNumeroSeguiment, creador, operador);

        IncidenciaTecnica it = (IncidenciaTecnica) this.create(itJPA);
        java.lang.Long incidenciaTecnicaID = it.getIncidenciaTecnicaID();
        java.lang.Long solicitudID = null;
        
        // Afegir peticio
        {
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            Long _fitxerID_ = null;
            if (missatge.startsWith("<") && missatge.endsWith(">")) {
                missatge = "<div>" + missatge + "</div>";
            }

            eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                    destinatariEmail, subject, missatge, _fitxerID_, _noLlegit_, caidIdentificadorConsulta, caidNumeroSeguiment);
        }

        // Afgegir fitxers
        {
            java.lang.String _missatge_ = "Afegit fitxer";
            java.lang.String _asumpte_ = "Afegit fitxer";
            
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            
            log.info("attachements " + emi.getAttachments() + " " + emi.getAttachments().size());
            for (EmailAttachmentInfo ads : emi.getAttachments()) {
            	log.info("attachement " + ads);
            	log.info("getFileName " + ads.getFileName());
            	log.info("getData " + ads.getData());
            	log.info("getContentType " + ads.getContentType());
            	
            	if (ads.getFileName() == null || ads.getFileName().equals("null")) {
            		continue;
				}
            	
                FitxerJPA fitxer = new FitxerJPA(ads.getFileName(), ads.getData().length, ads.getContentType(), null);
                fitxerEjb.create(fitxer);
                FileSystemManager.crearFitxer(new ByteArrayInputStream(ads.getData()), fitxer.getFitxerID());

                java.lang.Long _fitxerID_ = fitxer.getFitxerID();

                eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                        destinatariEmail, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, caidIdentificadorConsulta,
                        caidNumeroSeguiment);
            }
        }
        
//        //Enviar correu a suport si es necessari
//        {
//            if (titol.indexOf("CAI-") > 0) {
//                String nomIncidencia = titol;
//                String CAI = "2328810" ;
//                
//                int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC;
//                boolean _noLlegit_ = true;
//                Long _fitxerID_ = null;
//                String _contacteNom_ = "PinbalAdmin"; //Quien envia el mensaje
//                String _destinatari_ = "Suport DGMAD"; //Quien recibe el mensaje
//                String _destinatariEmail_ = "ptrias@fundaciobit.org"; //Correo de quien lo recibe
//                String _missatge_ = "A la atenció de suport CAID de la DGMAD. S'ha creat la incidencia numero " + incidenciaTecnicaID + " a PinbalAdmin (CAI-" + CAI + "). <br><br>" + nomIncidencia + " <br><br>" + "Salutacions. FBIT";
//
//                eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, _contacteNom_, _destinatari_,
//                        _destinatariEmail_, _missatge_, _fitxerID_, _noLlegit_, caidIdentificadorConsulta,
//                        caidNumeroSeguiment);
//            }
//        }


        
        return it;

    }
    
    
    @Override
    public IncidenciaTecnica afegirMailAIncidencia(EmailMessageInfo emi, Long incidenciaID) throws I18NException {

    	java.lang.String asumpte = emi.getSubject();
        java.lang.String missatge = emi.getBody(); // TODO limit tamany

        java.sql.Timestamp data= new Timestamp(System.currentTimeMillis());
        java.lang.String contacteNom = emi.getNameFrom();
        java.lang.String caidIdentificadorConsulta = null;
        java.lang.String caidNumeroSeguiment = null;
        java.lang.String destinatari = null;
        java.lang.String destinatariEmail = null;

        IncidenciaTecnica it = this.findByPrimaryKey(incidenciaID);
        
		if (it == null) {
			throw new I18NException("Incidencia no trobada");
		}
        
        java.lang.Long incidenciaTecnicaID = it.getIncidenciaTecnicaID();
        java.lang.Long solicitudID = null;
        
        // Afegir event de peticio
        {
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            Long _fitxerID_ = null;
            if (missatge.startsWith("<") && missatge.endsWith(">")) {
                missatge = "<div>" + missatge + "</div>";
            }

            eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                    destinatariEmail, asumpte, missatge, _fitxerID_, _noLlegit_, caidIdentificadorConsulta, caidNumeroSeguiment);
        }

        // Si el correu te fitxers, afegir-los
        {
            java.lang.String _missatge_ = "Afegit fitxer";
            java.lang.String _asumpte_ = "Afegit fitxer";
            
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            
            log.info("attachements " + emi.getAttachments() + " " + emi.getAttachments().size());
            for (EmailAttachmentInfo ads : emi.getAttachments()) {
            	
            	if (ads.getFileName() == null || ads.getFileName().equals("null")) {
            		continue;
				}
            	
                FitxerJPA fitxer = new FitxerJPA(ads.getFileName(), ads.getData().length, ads.getContentType(), null);
                fitxerEjb.create(fitxer);
                FileSystemManager.crearFitxer(new ByteArrayInputStream(ads.getData()), fitxer.getFitxerID());

                java.lang.Long _fitxerID_ = fitxer.getFitxerID();

                eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                        destinatariEmail, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, caidIdentificadorConsulta,
                        caidNumeroSeguiment);
            }
        }
        return it;
    }
}
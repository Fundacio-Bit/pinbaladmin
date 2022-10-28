package org.fundaciobit.pinbaladmin.logic;


import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaEJB;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 */
@Stateless(name = "IncidenciaTecnicaLogicaEJB")
@SecurityDomain("seycon")
public class IncidenciaTecnicaLogicaEJB extends IncidenciaTecnicaEJB implements IncidenciaTecnicaLogicaLocal {

  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;
  

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FitxerLocal fitxerEjb;
  
  @Override
  @PermitAll
  public IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_) {
     return super.findByPrimaryKey(_ID_);
  }

  
  @Override
  public IncidenciaTecnica createFromEmail(EmailMessageInfo emi, String creador) throws I18NException {
    
    java.lang.String titol = emi.getSubject();
    java.lang.String descripcio = emi.getBody(); // TODO limit tamany
    java.sql.Timestamp dataInici = new Timestamp(System.currentTimeMillis());
    int estat = Constants.ESTAT_INCIDENCIA_OBERTA;
    java.lang.String nomEntitat = "";
    java.lang.String contacteNom = emi.getNameFrom();
    java.lang.String contacteEmail = emi.getDisplayFrom();
    java.lang.String contacteTelefon = null;
    java.lang.String caidIdentificadorConsulta = null;
    java.lang.String caidNumeroSeguiment = null;

    int tipus = Constants.INCIDENCIA_TIPUS_TECNICA;

    IncidenciaTecnica it = new IncidenciaTecnicaJPA(titol, descripcio, dataInici, estat,
        creador, tipus, nomEntitat, contacteNom, contacteEmail, contacteTelefon,
        caidIdentificadorConsulta, caidNumeroSeguiment);
    
    it = this.create(it);
    
    java.lang.Long _incidenciaTecnicaID_ = it.getIncidenciaTecnicaID();
    java.lang.Long _solicitudID_ = null;
    java.sql.Timestamp _dataEvent_ = dataInici;
    int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
    java.lang.String _persona_ = contacteNom;
    boolean _noLlegit_ = true;
    
    
    java.lang.String _caidIdentificadorConsulta_ = caidIdentificadorConsulta;
    java.lang.String _caidNumeroSeguiment_ = caidNumeroSeguiment;
    
    // Afegir peticio
    if (descripcio.startsWith("<") && descripcio.endsWith(">")) {
      descripcio = "<div>" + descripcio + "</div>";
    }
    
    eventLogicaEjb.create( _solicitudID_,_incidenciaTecnicaID_,  _dataEvent_,  _tipus_,  _persona_,  descripcio,  null,  _noLlegit_,  _caidIdentificadorConsulta_,  _caidNumeroSeguiment_);
    
    // Afgegir fitxers
    java.lang.String _comentari_ = "Afegit fitxer";
    for (EmailAttachmentInfo ads : emi.getAttachments()) {

      FitxerJPA fitxer = new FitxerJPA(ads.getFileName(), ads.getData().length, ads.getContentType(), null);
      
      fitxerEjb.create(fitxer);
      
      FileSystemManager.crearFitxer(new ByteArrayInputStream(ads.getData()), fitxer.getFitxerID());
      
      java.lang.Long _fitxerID_ = fitxer.getFitxerID();
     

      eventLogicaEjb.create( _solicitudID_,_incidenciaTecnicaID_,  _dataEvent_,  _tipus_,  _persona_,  _comentari_,  _fitxerID_,  _noLlegit_,  _caidIdentificadorConsulta_,  _caidNumeroSeguiment_);

    }

    return it;  

  }
}
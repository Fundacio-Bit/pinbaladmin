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
public class IncidenciaTecnicaLogicaEJB extends IncidenciaTecnicaEJB
    implements IncidenciaTecnicaLogicaService {

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
  public IncidenciaTecnica createFromEmail(EmailMessageInfo emi, String creador, int tipus)
      throws I18NException {

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

    eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_,
        _persona_, descripcio, null, _noLlegit_, _caidIdentificadorConsulta_,
        _caidNumeroSeguiment_);

    // Afgegir fitxers
    java.lang.String _comentari_ = "Afegit fitxer";
    for (EmailAttachmentInfo ads : emi.getAttachments()) {

      FitxerJPA fitxer = new FitxerJPA(ads.getFileName(), ads.getData().length,
          ads.getContentType(), null);

      fitxerEjb.create(fitxer);

      FileSystemManager.crearFitxer(new ByteArrayInputStream(ads.getData()),
          fitxer.getFitxerID());

      java.lang.Long _fitxerID_ = fitxer.getFitxerID();

      eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_,
          _persona_, _comentari_, _fitxerID_, _noLlegit_, _caidIdentificadorConsulta_,
          _caidNumeroSeguiment_);

    }

    return it;

  }
}
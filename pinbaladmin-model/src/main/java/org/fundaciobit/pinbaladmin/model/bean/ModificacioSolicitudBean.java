
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;


public class ModificacioSolicitudBean implements ModificacioSolicitud {



	long modsoliID;// PK
	long solicitudID;
	java.lang.String procedimentCodi;
	java.lang.String procedimentNom;
	java.lang.String codiSiaNou;
	java.lang.Long estatID;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	java.lang.String procedimentTipus;
	java.lang.String notes;
	java.lang.Long organID;
	java.lang.String responsableProcNom;
	java.lang.String responsableProceMail;
	java.lang.String consentiment;
	java.lang.Long doCconsentimentID;
	java.lang.String solicitantNom;
	java.lang.String solicitantNif;
	java.lang.String solicitantMail;
	java.lang.String solicitantUsername;
	java.lang.Long estatModificacio;
	java.lang.String contactenom;
	java.lang.String contactemail;


  /** Constructor Buit */
  public ModificacioSolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public ModificacioSolicitudBean(long modsoliID , long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String codiSiaNou , java.lang.Long estatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String procedimentTipus , java.lang.String notes , java.lang.Long organID , java.lang.String responsableProcNom , java.lang.String responsableProceMail , java.lang.String consentiment , java.lang.Long doCconsentimentID , java.lang.String solicitantNom , java.lang.String solicitantNif , java.lang.String solicitantMail , java.lang.String solicitantUsername , java.lang.Long estatModificacio , java.lang.String contactenom , java.lang.String contactemail) {
    this.modsoliID=modsoliID;
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.codiSiaNou=codiSiaNou;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.procedimentTipus=procedimentTipus;
    this.notes=notes;
    this.organID=organID;
    this.responsableProcNom=responsableProcNom;
    this.responsableProceMail=responsableProceMail;
    this.consentiment=consentiment;
    this.doCconsentimentID=doCconsentimentID;
    this.solicitantNom=solicitantNom;
    this.solicitantNif=solicitantNif;
    this.solicitantMail=solicitantMail;
    this.solicitantUsername=solicitantUsername;
    this.estatModificacio=estatModificacio;
    this.contactenom=contactenom;
    this.contactemail=contactemail;
}
  /** Constructor sense valors autoincrementals */
  public ModificacioSolicitudBean(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String codiSiaNou , java.lang.Long estatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String procedimentTipus , java.lang.String notes , java.lang.Long organID , java.lang.String responsableProcNom , java.lang.String responsableProceMail , java.lang.String consentiment , java.lang.Long doCconsentimentID , java.lang.String solicitantNom , java.lang.String solicitantNif , java.lang.String solicitantMail , java.lang.String solicitantUsername , java.lang.Long estatModificacio , java.lang.String contactenom , java.lang.String contactemail) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.codiSiaNou=codiSiaNou;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.procedimentTipus=procedimentTipus;
    this.notes=notes;
    this.organID=organID;
    this.responsableProcNom=responsableProcNom;
    this.responsableProceMail=responsableProceMail;
    this.consentiment=consentiment;
    this.doCconsentimentID=doCconsentimentID;
    this.solicitantNom=solicitantNom;
    this.solicitantNif=solicitantNif;
    this.solicitantMail=solicitantMail;
    this.solicitantUsername=solicitantUsername;
    this.estatModificacio=estatModificacio;
    this.contactenom=contactenom;
    this.contactemail=contactemail;
}
  /** Constructor dels valors Not Null */
  public ModificacioSolicitudBean(long modsoliID , long solicitudID) {
    this.modsoliID=modsoliID;
    this.solicitudID=solicitudID;
}
  public ModificacioSolicitudBean(ModificacioSolicitud __bean) {
    this.setModsoliID(__bean.getModsoliID());
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setCodiSiaNou(__bean.getCodiSiaNou());
    this.setEstatID(__bean.getEstatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setProcedimentTipus(__bean.getProcedimentTipus());
    this.setNotes(__bean.getNotes());
    this.setOrganID(__bean.getOrganID());
    this.setResponsableProcNom(__bean.getResponsableProcNom());
    this.setResponsableProceMail(__bean.getResponsableProceMail());
    this.setConsentiment(__bean.getConsentiment());
    this.setDoCconsentimentID(__bean.getDoCconsentimentID());
    this.setSolicitantNom(__bean.getSolicitantNom());
    this.setSolicitantNif(__bean.getSolicitantNif());
    this.setSolicitantMail(__bean.getSolicitantMail());
    this.setSolicitantUsername(__bean.getSolicitantUsername());
    this.setEstatModificacio(__bean.getEstatModificacio());
    this.setContactenom(__bean.getContactenom());
    this.setContactemail(__bean.getContactemail());
    // Fitxer
    this.setDoCconsentiment(FitxerBean.toBean(__bean.getDoCconsentiment()));
	}

	public long getModsoliID() {
		return(modsoliID);
	};
	public void setModsoliID(long _modsoliID_) {
		this.modsoliID = _modsoliID_;
	};

	public long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};

	public java.lang.String getProcedimentCodi() {
		return(procedimentCodi);
	};
	public void setProcedimentCodi(java.lang.String _procedimentCodi_) {
		this.procedimentCodi = _procedimentCodi_;
	};

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getCodiSiaNou() {
		return(codiSiaNou);
	};
	public void setCodiSiaNou(java.lang.String _codiSiaNou_) {
		this.codiSiaNou = _codiSiaNou_;
	};

	public java.lang.Long getEstatID() {
		return(estatID);
	};
	public void setEstatID(java.lang.Long _estatID_) {
		this.estatID = _estatID_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public java.lang.String getProcedimentTipus() {
		return(procedimentTipus);
	};
	public void setProcedimentTipus(java.lang.String _procedimentTipus_) {
		this.procedimentTipus = _procedimentTipus_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.Long getOrganID() {
		return(organID);
	};
	public void setOrganID(java.lang.Long _organID_) {
		this.organID = _organID_;
	};

	public java.lang.String getResponsableProcNom() {
		return(responsableProcNom);
	};
	public void setResponsableProcNom(java.lang.String _responsableProcNom_) {
		this.responsableProcNom = _responsableProcNom_;
	};

	public java.lang.String getResponsableProceMail() {
		return(responsableProceMail);
	};
	public void setResponsableProceMail(java.lang.String _responsableProceMail_) {
		this.responsableProceMail = _responsableProceMail_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.Long getDoCconsentimentID() {
		return(doCconsentimentID);
	};
	public void setDoCconsentimentID(java.lang.Long _doCconsentimentID_) {
		this.doCconsentimentID = _doCconsentimentID_;
	};

	public java.lang.String getSolicitantNom() {
		return(solicitantNom);
	};
	public void setSolicitantNom(java.lang.String _solicitantNom_) {
		this.solicitantNom = _solicitantNom_;
	};

	public java.lang.String getSolicitantNif() {
		return(solicitantNif);
	};
	public void setSolicitantNif(java.lang.String _solicitantNif_) {
		this.solicitantNif = _solicitantNif_;
	};

	public java.lang.String getSolicitantMail() {
		return(solicitantMail);
	};
	public void setSolicitantMail(java.lang.String _solicitantMail_) {
		this.solicitantMail = _solicitantMail_;
	};

	public java.lang.String getSolicitantUsername() {
		return(solicitantUsername);
	};
	public void setSolicitantUsername(java.lang.String _solicitantUsername_) {
		this.solicitantUsername = _solicitantUsername_;
	};

	public java.lang.Long getEstatModificacio() {
		return(estatModificacio);
	};
	public void setEstatModificacio(java.lang.Long _estatModificacio_) {
		this.estatModificacio = _estatModificacio_;
	};

	public java.lang.String getContactenom() {
		return(contactenom);
	};
	public void setContactenom(java.lang.String _contactenom_) {
		this.contactenom = _contactenom_;
	};

	public java.lang.String getContactemail() {
		return(contactemail);
	};
	public void setContactemail(java.lang.String _contactemail_) {
		this.contactemail = _contactemail_;
	};



  // ======================================

  public static ModificacioSolicitudBean toBean(ModificacioSolicitud __bean) {
    if (__bean == null) { return null;}
    ModificacioSolicitudBean __tmp = new ModificacioSolicitudBean();
    __tmp.setModsoliID(__bean.getModsoliID());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setCodiSiaNou(__bean.getCodiSiaNou());
    __tmp.setEstatID(__bean.getEstatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setProcedimentTipus(__bean.getProcedimentTipus());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setOrganID(__bean.getOrganID());
    __tmp.setResponsableProcNom(__bean.getResponsableProcNom());
    __tmp.setResponsableProceMail(__bean.getResponsableProceMail());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setDoCconsentimentID(__bean.getDoCconsentimentID());
    __tmp.setSolicitantNom(__bean.getSolicitantNom());
    __tmp.setSolicitantNif(__bean.getSolicitantNif());
    __tmp.setSolicitantMail(__bean.getSolicitantMail());
    __tmp.setSolicitantUsername(__bean.getSolicitantUsername());
    __tmp.setEstatModificacio(__bean.getEstatModificacio());
    __tmp.setContactenom(__bean.getContactenom());
    __tmp.setContactemail(__bean.getContactemail());
    // Fitxer
    __tmp.setDoCconsentiment(FitxerBean.toBean(__bean.getDoCconsentiment()));
		return __tmp;
	}

  protected FitxerBean doCconsentiment;
  public FitxerBean getDoCconsentiment() {
    return doCconsentiment;
  }
  public void setDoCconsentiment(FitxerBean __field) {
    this. doCconsentiment = __field;
  }


}

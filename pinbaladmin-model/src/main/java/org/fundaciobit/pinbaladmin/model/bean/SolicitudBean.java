
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Solicitud;


public class SolicitudBean implements Solicitud {



	long solicitudID;// PK
	java.lang.String procedimentCodi;
	java.lang.String codiDescriptiu;
	java.lang.String procedimentNom;
	java.lang.String procedimentTipus;
	java.lang.String expedientPid;
	java.lang.Long estatID;
	java.lang.Long organid;
	java.lang.String entitatEstatal;
	java.lang.String pinfo;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	java.lang.String personaContacte;
	java.lang.String personaContacteEmail;
	java.lang.String responsableProcNom;
	java.lang.String responsableProcEmail;
	java.lang.String notes;
	java.lang.Long documentSolicitudID;
	java.lang.Long solicitudXmlID;
	boolean firmatDocSolicitud;
	boolean produccio;
	java.lang.String denominacio;
	java.lang.String dir3;
	java.lang.String nif;
	java.lang.String creador;
	java.lang.String operador;
	java.lang.Integer estatpinbal;
	java.lang.String consentiment;
	java.lang.String urlconsentiment;
	java.lang.String consentimentadjunt;
	java.lang.Long portafibID;


  /** Constructor Buit */
  public SolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudBean(long solicitudID , java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.String expedientPid , java.lang.Long estatID , java.lang.Long organid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Integer estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.expedientPid=expedientPid;
    this.estatID=estatID;
    this.organid=organid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.personaContacte=personaContacte;
    this.personaContacteEmail=personaContacteEmail;
    this.responsableProcNom=responsableProcNom;
    this.responsableProcEmail=responsableProcEmail;
    this.notes=notes;
    this.documentSolicitudID=documentSolicitudID;
    this.solicitudXmlID=solicitudXmlID;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
    this.creador=creador;
    this.operador=operador;
    this.estatpinbal=estatpinbal;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.portafibID=portafibID;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudBean(java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.String expedientPid , java.lang.Long estatID , java.lang.Long organid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Integer estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID) {
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.expedientPid=expedientPid;
    this.estatID=estatID;
    this.organid=organid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.personaContacte=personaContacte;
    this.personaContacteEmail=personaContacteEmail;
    this.responsableProcNom=responsableProcNom;
    this.responsableProcEmail=responsableProcEmail;
    this.notes=notes;
    this.documentSolicitudID=documentSolicitudID;
    this.solicitudXmlID=solicitudXmlID;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
    this.creador=creador;
    this.operador=operador;
    this.estatpinbal=estatpinbal;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.portafibID=portafibID;
}
  /** Constructor dels valors Not Null */
  public SolicitudBean(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.Long estatID , java.sql.Timestamp dataInici , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String operador) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.creador=creador;
    this.operador=operador;
}
  public SolicitudBean(Solicitud __bean) {
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setCodiDescriptiu(__bean.getCodiDescriptiu());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentTipus(__bean.getProcedimentTipus());
    this.setExpedientPid(__bean.getExpedientPid());
    this.setEstatID(__bean.getEstatID());
    this.setOrganid(__bean.getOrganid());
    this.setEntitatEstatal(__bean.getEntitatEstatal());
    this.setPinfo(__bean.getPinfo());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setPersonaContacte(__bean.getPersonaContacte());
    this.setPersonaContacteEmail(__bean.getPersonaContacteEmail());
    this.setResponsableProcNom(__bean.getResponsableProcNom());
    this.setResponsableProcEmail(__bean.getResponsableProcEmail());
    this.setNotes(__bean.getNotes());
    this.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    this.setSolicitudXmlID(__bean.getSolicitudXmlID());
    this.setFirmatDocSolicitud(__bean.isFirmatDocSolicitud());
    this.setProduccio(__bean.isProduccio());
    this.setDenominacio(__bean.getDenominacio());
    this.setDir3(__bean.getDir3());
    this.setNif(__bean.getNif());
    this.setCreador(__bean.getCreador());
    this.setOperador(__bean.getOperador());
    this.setEstatpinbal(__bean.getEstatpinbal());
    this.setConsentiment(__bean.getConsentiment());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
    this.setConsentimentadjunt(__bean.getConsentimentadjunt());
    this.setPortafibID(__bean.getPortafibID());
    // Fitxer
    this.setDocumentSolicitud(FitxerBean.toBean(__bean.getDocumentSolicitud()));
    // Fitxer
    this.setSolicitudXml(FitxerBean.toBean(__bean.getSolicitudXml()));
	}

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

	public java.lang.String getCodiDescriptiu() {
		return(codiDescriptiu);
	};
	public void setCodiDescriptiu(java.lang.String _codiDescriptiu_) {
		this.codiDescriptiu = _codiDescriptiu_;
	};

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getProcedimentTipus() {
		return(procedimentTipus);
	};
	public void setProcedimentTipus(java.lang.String _procedimentTipus_) {
		this.procedimentTipus = _procedimentTipus_;
	};

	public java.lang.String getExpedientPid() {
		return(expedientPid);
	};
	public void setExpedientPid(java.lang.String _expedientPid_) {
		this.expedientPid = _expedientPid_;
	};

	public java.lang.Long getEstatID() {
		return(estatID);
	};
	public void setEstatID(java.lang.Long _estatID_) {
		this.estatID = _estatID_;
	};

	public java.lang.Long getOrganid() {
		return(organid);
	};
	public void setOrganid(java.lang.Long _organid_) {
		this.organid = _organid_;
	};

	public java.lang.String getEntitatEstatal() {
		return(entitatEstatal);
	};
	public void setEntitatEstatal(java.lang.String _entitatEstatal_) {
		this.entitatEstatal = _entitatEstatal_;
	};

	public java.lang.String getPinfo() {
		return(pinfo);
	};
	public void setPinfo(java.lang.String _pinfo_) {
		this.pinfo = _pinfo_;
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

	public java.lang.String getPersonaContacte() {
		return(personaContacte);
	};
	public void setPersonaContacte(java.lang.String _personaContacte_) {
		this.personaContacte = _personaContacte_;
	};

	public java.lang.String getPersonaContacteEmail() {
		return(personaContacteEmail);
	};
	public void setPersonaContacteEmail(java.lang.String _personaContacteEmail_) {
		this.personaContacteEmail = _personaContacteEmail_;
	};

	public java.lang.String getResponsableProcNom() {
		return(responsableProcNom);
	};
	public void setResponsableProcNom(java.lang.String _responsableProcNom_) {
		this.responsableProcNom = _responsableProcNom_;
	};

	public java.lang.String getResponsableProcEmail() {
		return(responsableProcEmail);
	};
	public void setResponsableProcEmail(java.lang.String _responsableProcEmail_) {
		this.responsableProcEmail = _responsableProcEmail_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.Long getDocumentSolicitudID() {
		return(documentSolicitudID);
	};
	public void setDocumentSolicitudID(java.lang.Long _documentSolicitudID_) {
		this.documentSolicitudID = _documentSolicitudID_;
	};

	public java.lang.Long getSolicitudXmlID() {
		return(solicitudXmlID);
	};
	public void setSolicitudXmlID(java.lang.Long _solicitudXmlID_) {
		this.solicitudXmlID = _solicitudXmlID_;
	};

	public boolean isFirmatDocSolicitud() {
		return(firmatDocSolicitud);
	};
	public void setFirmatDocSolicitud(boolean _firmatDocSolicitud_) {
		this.firmatDocSolicitud = _firmatDocSolicitud_;
	};

	public boolean isProduccio() {
		return(produccio);
	};
	public void setProduccio(boolean _produccio_) {
		this.produccio = _produccio_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getCreador() {
		return(creador);
	};
	public void setCreador(java.lang.String _creador_) {
		this.creador = _creador_;
	};

	public java.lang.String getOperador() {
		return(operador);
	};
	public void setOperador(java.lang.String _operador_) {
		this.operador = _operador_;
	};

	public java.lang.Integer getEstatpinbal() {
		return(estatpinbal);
	};
	public void setEstatpinbal(java.lang.Integer _estatpinbal_) {
		this.estatpinbal = _estatpinbal_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};

	public java.lang.String getConsentimentadjunt() {
		return(consentimentadjunt);
	};
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_) {
		this.consentimentadjunt = _consentimentadjunt_;
	};

	public java.lang.Long getPortafibID() {
		return(portafibID);
	};
	public void setPortafibID(java.lang.Long _portafibID_) {
		this.portafibID = _portafibID_;
	};



  // ======================================

  public static SolicitudBean toBean(Solicitud __bean) {
    if (__bean == null) { return null;}
    SolicitudBean __tmp = new SolicitudBean();
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setCodiDescriptiu(__bean.getCodiDescriptiu());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setProcedimentTipus(__bean.getProcedimentTipus());
    __tmp.setExpedientPid(__bean.getExpedientPid());
    __tmp.setEstatID(__bean.getEstatID());
    __tmp.setOrganid(__bean.getOrganid());
    __tmp.setEntitatEstatal(__bean.getEntitatEstatal());
    __tmp.setPinfo(__bean.getPinfo());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setPersonaContacte(__bean.getPersonaContacte());
    __tmp.setPersonaContacteEmail(__bean.getPersonaContacteEmail());
    __tmp.setResponsableProcNom(__bean.getResponsableProcNom());
    __tmp.setResponsableProcEmail(__bean.getResponsableProcEmail());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    __tmp.setSolicitudXmlID(__bean.getSolicitudXmlID());
    __tmp.setFirmatDocSolicitud(__bean.isFirmatDocSolicitud());
    __tmp.setProduccio(__bean.isProduccio());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setDir3(__bean.getDir3());
    __tmp.setNif(__bean.getNif());
    __tmp.setCreador(__bean.getCreador());
    __tmp.setOperador(__bean.getOperador());
    __tmp.setEstatpinbal(__bean.getEstatpinbal());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
    __tmp.setConsentimentadjunt(__bean.getConsentimentadjunt());
    __tmp.setPortafibID(__bean.getPortafibID());
    // Fitxer
    __tmp.setDocumentSolicitud(FitxerBean.toBean(__bean.getDocumentSolicitud()));
    // Fitxer
    __tmp.setSolicitudXml(FitxerBean.toBean(__bean.getSolicitudXml()));
		return __tmp;
	}

  protected FitxerBean documentSolicitud;
  public FitxerBean getDocumentSolicitud() {
    return documentSolicitud;
  }
  public void setDocumentSolicitud(FitxerBean __field) {
    this. documentSolicitud = __field;
  }
  protected FitxerBean solicitudXml;
  public FitxerBean getSolicitudXml() {
    return solicitudXml;
  }
  public void setSolicitudXml(FitxerBean __field) {
    this. solicitudXml = __field;
  }


}

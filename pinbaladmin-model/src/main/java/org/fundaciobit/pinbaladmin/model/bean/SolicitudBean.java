
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Solicitud;


public class SolicitudBean implements Solicitud {



	long solicitudID;// PK
	java.lang.String procedimentCodi;
	java.lang.String codiDescriptiu;
	java.lang.String notes;
	java.lang.String codiSiaConv;
	java.lang.String procedimentNom;
	java.lang.String procedimentTipus;
	java.lang.Long organid;
	java.lang.Long estatSolicitud;
	java.lang.String expedientPid;
	java.lang.String entitatEstatal;
	java.lang.String pinfo;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	java.lang.Long documentSolicitudID;
	java.lang.Long solicitudXmlID;
	boolean firmatDocSolicitud;
	boolean produccio;
	java.lang.String denominacio;
	java.lang.String dir3;
	java.lang.String nif;
	java.lang.String creador;
	java.lang.String operador;
	java.lang.Long estatpinbal;
	java.lang.String consentiment;
	java.lang.String urlconsentiment;
	java.lang.String consentimentadjunt;
	java.lang.Long portafibID;
	java.lang.Long infomadridid;
	java.sql.Timestamp dataCaducitat;
	java.lang.Long fitxerConsentimentID;
	java.lang.Long contacteTitularID;
	java.lang.Long solicitudFusionadaID;
	java.lang.Long contactePersonaID;
	java.lang.Long contacteResponsableID;
	java.lang.Long contacteSolicitantID;
	java.lang.Long contacteGestAutID;
	java.lang.Long contacteAuditoriaID;
	java.lang.Long contacteTecnicID;
	java.lang.String titularFirmaNifOld;
	java.lang.String personacontacteold;
	java.lang.String personacontacteemailold;
	java.lang.String responsableprocnomold;
	java.lang.String responsableprocemailold;
	java.lang.String titularfirmanomold;
	java.lang.String titularfirmaemailold;


  /** Constructor Buit */
  public SolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudBean(long solicitudID , java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String notes , java.lang.String codiSiaConv , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long organid , java.lang.Long estatSolicitud , java.lang.String expedientPid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Long estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID , java.lang.Long infomadridid , java.sql.Timestamp dataCaducitat , java.lang.Long fitxerConsentimentID , java.lang.Long contacteTitularID , java.lang.Long solicitudFusionadaID , java.lang.Long contactePersonaID , java.lang.Long contacteResponsableID , java.lang.Long contacteSolicitantID , java.lang.Long contacteGestAutID , java.lang.Long contacteAuditoriaID , java.lang.Long contacteTecnicID , java.lang.String titularFirmaNifOld , java.lang.String personacontacteold , java.lang.String personacontacteemailold , java.lang.String responsableprocnomold , java.lang.String responsableprocemailold , java.lang.String titularfirmanomold , java.lang.String titularfirmaemailold) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.notes=notes;
    this.codiSiaConv=codiSiaConv;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.organid=organid;
    this.estatSolicitud=estatSolicitud;
    this.expedientPid=expedientPid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
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
    this.infomadridid=infomadridid;
    this.dataCaducitat=dataCaducitat;
    this.fitxerConsentimentID=fitxerConsentimentID;
    this.contacteTitularID=contacteTitularID;
    this.solicitudFusionadaID=solicitudFusionadaID;
    this.contactePersonaID=contactePersonaID;
    this.contacteResponsableID=contacteResponsableID;
    this.contacteSolicitantID=contacteSolicitantID;
    this.contacteGestAutID=contacteGestAutID;
    this.contacteAuditoriaID=contacteAuditoriaID;
    this.contacteTecnicID=contacteTecnicID;
    this.titularFirmaNifOld=titularFirmaNifOld;
    this.personacontacteold=personacontacteold;
    this.personacontacteemailold=personacontacteemailold;
    this.responsableprocnomold=responsableprocnomold;
    this.responsableprocemailold=responsableprocemailold;
    this.titularfirmanomold=titularfirmanomold;
    this.titularfirmaemailold=titularfirmaemailold;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudBean(java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String notes , java.lang.String codiSiaConv , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long organid , java.lang.Long estatSolicitud , java.lang.String expedientPid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Long estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID , java.lang.Long infomadridid , java.sql.Timestamp dataCaducitat , java.lang.Long fitxerConsentimentID , java.lang.Long contacteTitularID , java.lang.Long solicitudFusionadaID , java.lang.Long contactePersonaID , java.lang.Long contacteResponsableID , java.lang.Long contacteSolicitantID , java.lang.Long contacteGestAutID , java.lang.Long contacteAuditoriaID , java.lang.Long contacteTecnicID , java.lang.String titularFirmaNifOld , java.lang.String personacontacteold , java.lang.String personacontacteemailold , java.lang.String responsableprocnomold , java.lang.String responsableprocemailold , java.lang.String titularfirmanomold , java.lang.String titularfirmaemailold) {
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.notes=notes;
    this.codiSiaConv=codiSiaConv;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.organid=organid;
    this.estatSolicitud=estatSolicitud;
    this.expedientPid=expedientPid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
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
    this.infomadridid=infomadridid;
    this.dataCaducitat=dataCaducitat;
    this.fitxerConsentimentID=fitxerConsentimentID;
    this.contacteTitularID=contacteTitularID;
    this.solicitudFusionadaID=solicitudFusionadaID;
    this.contactePersonaID=contactePersonaID;
    this.contacteResponsableID=contacteResponsableID;
    this.contacteSolicitantID=contacteSolicitantID;
    this.contacteGestAutID=contacteGestAutID;
    this.contacteAuditoriaID=contacteAuditoriaID;
    this.contacteTecnicID=contacteTecnicID;
    this.titularFirmaNifOld=titularFirmaNifOld;
    this.personacontacteold=personacontacteold;
    this.personacontacteemailold=personacontacteemailold;
    this.responsableprocnomold=responsableprocnomold;
    this.responsableprocemailold=responsableprocemailold;
    this.titularfirmanomold=titularfirmanomold;
    this.titularfirmaemailold=titularfirmaemailold;
}
  /** Constructor dels valors Not Null */
  public SolicitudBean(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.Long estatSolicitud , java.sql.Timestamp dataInici , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String operador) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.estatSolicitud=estatSolicitud;
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
    this.setNotes(__bean.getNotes());
    this.setCodiSiaConv(__bean.getCodiSiaConv());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentTipus(__bean.getProcedimentTipus());
    this.setOrganid(__bean.getOrganid());
    this.setEstatSolicitud(__bean.getEstatSolicitud());
    this.setExpedientPid(__bean.getExpedientPid());
    this.setEntitatEstatal(__bean.getEntitatEstatal());
    this.setPinfo(__bean.getPinfo());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
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
    this.setInfomadridid(__bean.getInfomadridid());
    this.setDataCaducitat(__bean.getDataCaducitat());
    this.setFitxerConsentimentID(__bean.getFitxerConsentimentID());
    this.setContacteTitularID(__bean.getContacteTitularID());
    this.setSolicitudFusionadaID(__bean.getSolicitudFusionadaID());
    this.setContactePersonaID(__bean.getContactePersonaID());
    this.setContacteResponsableID(__bean.getContacteResponsableID());
    this.setContacteSolicitantID(__bean.getContacteSolicitantID());
    this.setContacteGestAutID(__bean.getContacteGestAutID());
    this.setContacteAuditoriaID(__bean.getContacteAuditoriaID());
    this.setContacteTecnicID(__bean.getContacteTecnicID());
    this.setTitularFirmaNifOld(__bean.getTitularFirmaNifOld());
    this.setPersonacontacteold(__bean.getPersonacontacteold());
    this.setPersonacontacteemailold(__bean.getPersonacontacteemailold());
    this.setResponsableprocnomold(__bean.getResponsableprocnomold());
    this.setResponsableprocemailold(__bean.getResponsableprocemailold());
    this.setTitularfirmanomold(__bean.getTitularfirmanomold());
    this.setTitularfirmaemailold(__bean.getTitularfirmaemailold());
    // Fitxer
    this.setDocumentSolicitud(FitxerBean.toBean(__bean.getDocumentSolicitud()));
    // Fitxer
    this.setSolicitudXml(FitxerBean.toBean(__bean.getSolicitudXml()));
    // Fitxer
    this.setFitxerConsentiment(FitxerBean.toBean(__bean.getFitxerConsentiment()));
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

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.String getCodiSiaConv() {
		return(codiSiaConv);
	};
	public void setCodiSiaConv(java.lang.String _codiSiaConv_) {
		this.codiSiaConv = _codiSiaConv_;
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

	public java.lang.Long getOrganid() {
		return(organid);
	};
	public void setOrganid(java.lang.Long _organid_) {
		this.organid = _organid_;
	};

	public java.lang.Long getEstatSolicitud() {
		return(estatSolicitud);
	};
	public void setEstatSolicitud(java.lang.Long _estatSolicitud_) {
		this.estatSolicitud = _estatSolicitud_;
	};

	public java.lang.String getExpedientPid() {
		return(expedientPid);
	};
	public void setExpedientPid(java.lang.String _expedientPid_) {
		this.expedientPid = _expedientPid_;
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

	public java.lang.Long getEstatpinbal() {
		return(estatpinbal);
	};
	public void setEstatpinbal(java.lang.Long _estatpinbal_) {
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

	public java.lang.Long getInfomadridid() {
		return(infomadridid);
	};
	public void setInfomadridid(java.lang.Long _infomadridid_) {
		this.infomadridid = _infomadridid_;
	};

	public java.sql.Timestamp getDataCaducitat() {
		return(dataCaducitat);
	};
	public void setDataCaducitat(java.sql.Timestamp _dataCaducitat_) {
		this.dataCaducitat = _dataCaducitat_;
	};

	public java.lang.Long getFitxerConsentimentID() {
		return(fitxerConsentimentID);
	};
	public void setFitxerConsentimentID(java.lang.Long _fitxerConsentimentID_) {
		this.fitxerConsentimentID = _fitxerConsentimentID_;
	};

	public java.lang.Long getContacteTitularID() {
		return(contacteTitularID);
	};
	public void setContacteTitularID(java.lang.Long _contacteTitularID_) {
		this.contacteTitularID = _contacteTitularID_;
	};

	public java.lang.Long getSolicitudFusionadaID() {
		return(solicitudFusionadaID);
	};
	public void setSolicitudFusionadaID(java.lang.Long _solicitudFusionadaID_) {
		this.solicitudFusionadaID = _solicitudFusionadaID_;
	};

	public java.lang.Long getContactePersonaID() {
		return(contactePersonaID);
	};
	public void setContactePersonaID(java.lang.Long _contactePersonaID_) {
		this.contactePersonaID = _contactePersonaID_;
	};

	public java.lang.Long getContacteResponsableID() {
		return(contacteResponsableID);
	};
	public void setContacteResponsableID(java.lang.Long _contacteResponsableID_) {
		this.contacteResponsableID = _contacteResponsableID_;
	};

	public java.lang.Long getContacteSolicitantID() {
		return(contacteSolicitantID);
	};
	public void setContacteSolicitantID(java.lang.Long _contacteSolicitantID_) {
		this.contacteSolicitantID = _contacteSolicitantID_;
	};

	public java.lang.Long getContacteGestAutID() {
		return(contacteGestAutID);
	};
	public void setContacteGestAutID(java.lang.Long _contacteGestAutID_) {
		this.contacteGestAutID = _contacteGestAutID_;
	};

	public java.lang.Long getContacteAuditoriaID() {
		return(contacteAuditoriaID);
	};
	public void setContacteAuditoriaID(java.lang.Long _contacteAuditoriaID_) {
		this.contacteAuditoriaID = _contacteAuditoriaID_;
	};

	public java.lang.Long getContacteTecnicID() {
		return(contacteTecnicID);
	};
	public void setContacteTecnicID(java.lang.Long _contacteTecnicID_) {
		this.contacteTecnicID = _contacteTecnicID_;
	};

	public java.lang.String getTitularFirmaNifOld() {
		return(titularFirmaNifOld);
	};
	public void setTitularFirmaNifOld(java.lang.String _titularFirmaNifOld_) {
		this.titularFirmaNifOld = _titularFirmaNifOld_;
	};

	public java.lang.String getPersonacontacteold() {
		return(personacontacteold);
	};
	public void setPersonacontacteold(java.lang.String _personacontacteold_) {
		this.personacontacteold = _personacontacteold_;
	};

	public java.lang.String getPersonacontacteemailold() {
		return(personacontacteemailold);
	};
	public void setPersonacontacteemailold(java.lang.String _personacontacteemailold_) {
		this.personacontacteemailold = _personacontacteemailold_;
	};

	public java.lang.String getResponsableprocnomold() {
		return(responsableprocnomold);
	};
	public void setResponsableprocnomold(java.lang.String _responsableprocnomold_) {
		this.responsableprocnomold = _responsableprocnomold_;
	};

	public java.lang.String getResponsableprocemailold() {
		return(responsableprocemailold);
	};
	public void setResponsableprocemailold(java.lang.String _responsableprocemailold_) {
		this.responsableprocemailold = _responsableprocemailold_;
	};

	public java.lang.String getTitularfirmanomold() {
		return(titularfirmanomold);
	};
	public void setTitularfirmanomold(java.lang.String _titularfirmanomold_) {
		this.titularfirmanomold = _titularfirmanomold_;
	};

	public java.lang.String getTitularfirmaemailold() {
		return(titularfirmaemailold);
	};
	public void setTitularfirmaemailold(java.lang.String _titularfirmaemailold_) {
		this.titularfirmaemailold = _titularfirmaemailold_;
	};



  // ======================================

  public static SolicitudBean toBean(Solicitud __bean) {
    if (__bean == null) { return null;}
    SolicitudBean __tmp = new SolicitudBean();
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setCodiDescriptiu(__bean.getCodiDescriptiu());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setCodiSiaConv(__bean.getCodiSiaConv());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setProcedimentTipus(__bean.getProcedimentTipus());
    __tmp.setOrganid(__bean.getOrganid());
    __tmp.setEstatSolicitud(__bean.getEstatSolicitud());
    __tmp.setExpedientPid(__bean.getExpedientPid());
    __tmp.setEntitatEstatal(__bean.getEntitatEstatal());
    __tmp.setPinfo(__bean.getPinfo());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
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
    __tmp.setInfomadridid(__bean.getInfomadridid());
    __tmp.setDataCaducitat(__bean.getDataCaducitat());
    __tmp.setFitxerConsentimentID(__bean.getFitxerConsentimentID());
    __tmp.setContacteTitularID(__bean.getContacteTitularID());
    __tmp.setSolicitudFusionadaID(__bean.getSolicitudFusionadaID());
    __tmp.setContactePersonaID(__bean.getContactePersonaID());
    __tmp.setContacteResponsableID(__bean.getContacteResponsableID());
    __tmp.setContacteSolicitantID(__bean.getContacteSolicitantID());
    __tmp.setContacteGestAutID(__bean.getContacteGestAutID());
    __tmp.setContacteAuditoriaID(__bean.getContacteAuditoriaID());
    __tmp.setContacteTecnicID(__bean.getContacteTecnicID());
    __tmp.setTitularFirmaNifOld(__bean.getTitularFirmaNifOld());
    __tmp.setPersonacontacteold(__bean.getPersonacontacteold());
    __tmp.setPersonacontacteemailold(__bean.getPersonacontacteemailold());
    __tmp.setResponsableprocnomold(__bean.getResponsableprocnomold());
    __tmp.setResponsableprocemailold(__bean.getResponsableprocemailold());
    __tmp.setTitularfirmanomold(__bean.getTitularfirmanomold());
    __tmp.setTitularfirmaemailold(__bean.getTitularfirmaemailold());
    // Fitxer
    __tmp.setDocumentSolicitud(FitxerBean.toBean(__bean.getDocumentSolicitud()));
    // Fitxer
    __tmp.setSolicitudXml(FitxerBean.toBean(__bean.getSolicitudXml()));
    // Fitxer
    __tmp.setFitxerConsentiment(FitxerBean.toBean(__bean.getFitxerConsentiment()));
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
  protected FitxerBean fitxerConsentiment;
  public FitxerBean getFitxerConsentiment() {
    return fitxerConsentiment;
  }
  public void setFitxerConsentiment(FitxerBean __field) {
    this. fitxerConsentiment = __field;
  }


}

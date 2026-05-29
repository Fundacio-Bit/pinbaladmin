
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "SolicitudJPA")
@Table(name = "pad_solicitud" , indexes = { 
        @Index(name="pad_solicitud_pk_i", columnList = "solicitudid"),
        @Index(name="pad_solicitud_organid_fk_i", columnList = "organid"),
        @Index(name="pad_solicitud_estatid_fk_i", columnList = "estatid"),
        @Index(name="pad_solicitud_docsoli_fk_i", columnList = "documentsolicitudid"),
        @Index(name="pad_solicitud_solixml_fk_i", columnList = "solicitudxmlid"),
        @Index(name="pad_solicitud_infomadid_fk_i", columnList = "infomadridid"),
        @Index(name="pad_solicitud_consent_fk_i", columnList = "fitxerconsentimentid"),
        @Index(name="pad_solicitud_titularid_fk_i", columnList = "contactetitularid"),
        @Index(name="pad_solicitud_personaid_fk_i", columnList = "contactepersonaid"),
        @Index(name="pad_solicitud_responsid_fk_i", columnList = "contacteresponsableid"),
        @Index(name="pad_solicitud_solicitid_fk_i", columnList = "contactesolicitantid"),
        @Index(name="pad_solicitud_gestautid_fk_i", columnList = "contactegestautid"),
        @Index(name="pad_solicitud_auditid_fk_i", columnList = "contacteauditoriaid"),
        @Index(name="pad_solicitud_tecnicid_fk_i", columnList = "contactetecnicid")})
@SequenceGenerator(name="SOLICITUD_SEQ", sequenceName="pad_solicitud_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SolicitudJPA implements Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SOLICITUD_SEQ")
    @Column(name="solicitudid",nullable = false,length = 19)
    long solicitudID;

    @Column(name="procedimentcodi",nullable = false,length = 255)
    java.lang.String procedimentCodi;

    @Column(name="codidescriptiu",length = 256)
    java.lang.String codiDescriptiu;

    @Column(name="codisiaconv",length = 255)
    java.lang.String codiSiaConv;

    @Column(name="procedimentnom",nullable = false,length = 2000)
    java.lang.String procedimentNom;

    @Column(name="procedimenttipus",length = 255)
    java.lang.String procedimentTipus;

    @Column(name="organid",length = 19)
    java.lang.Long organid;

    @Column(name="estatid",nullable = false,length = 19)
    java.lang.Long estatSolicitud;

    @Column(name="expedientpid",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String expedientPid;

    @Column(name="entitatestatal",length = 255)
    java.lang.String entitatEstatal;

    @Column(name="pinfo",length = 255)
    java.lang.String pinfo;

    @Column(name="datainici",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataInici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp dataFi;

    @Column(name="estat",length = 2550)
    java.lang.String notes;

    @Column(name="documentsolicitudid",length = 19)
    java.lang.Long documentSolicitudID;

    @Column(name="solicitudxmlid",length = 19)
    java.lang.Long solicitudXmlID;

    @Column(name="firmatdocsolicitud",nullable = false,length = 1)
    boolean firmatDocSolicitud = false;

    @Column(name="produccio",nullable = false,length = 1)
    boolean produccio = true;

  /** És l'entitat */
    @Column(name="denominacio",length = 255)
    java.lang.String denominacio;

    @Column(name="dir3",length = 50)
    java.lang.String dir3;

    @Column(name="nif",length = 40)
    java.lang.String nif;

    @Column(name="creador",nullable = false,length = 100)
    java.lang.String creador;

    @Column(name="operador",nullable = false,length = 100)
    java.lang.String operador;

    @Column(name="estatpinbal",length = 19)
    java.lang.Long estatpinbal;

    @Column(name="consentiment",length = 80)
    java.lang.String consentiment;

    @Column(name="urlconsentiment",length = 255)
    java.lang.String urlconsentiment;

    @Column(name="consentimentadjunt",length = 200)
    java.lang.String consentimentadjunt;

    @Column(name="portafibid",length = 19)
    java.lang.Long portafibID;

    @Column(name="infomadridid",length = 19)
    java.lang.Long infomadridid;

    @Column(name="datacaducitat",length = 29,precision = 6)
    java.sql.Timestamp dataCaducitat;

    @Column(name="fitxerconsentimentid",length = 19)
    java.lang.Long fitxerConsentimentID;

    @Column(name="contactetitularid",length = 19)
    java.lang.Long contacteTitularID;

    @Column(name="solicitudfusionadaid",length = 19)
    java.lang.Long solicitudFusionadaID;

    @Column(name="contactepersonaid",length = 19)
    java.lang.Long contactePersonaID;

    @Column(name="contacteresponsableid",length = 19)
    java.lang.Long contacteResponsableID;

    @Column(name="contactesolicitantid",length = 19)
    java.lang.Long contacteSolicitantID;

    @Column(name="contactegestautid",length = 19)
    java.lang.Long contacteGestAutID;

    @Column(name="contacteauditoriaid",length = 19)
    java.lang.Long contacteAuditoriaID;

    @Column(name="contactetecnicid",length = 19)
    java.lang.Long contacteTecnicID;

    @Column(name="titularfirmanifold",length = 255)
    java.lang.String titularFirmaNifOld;

    @Column(name="personacontacteold",length = 255)
    java.lang.String personacontacteold;

    @Column(name="personacontacteemailold",length = 100)
    java.lang.String personacontacteemailold;

    @Column(name="responsableprocnomold",length = 255)
    java.lang.String responsableprocnomold;

    @Column(name="responsableprocemailold",length = 255)
    java.lang.String responsableprocemailold;

    @Column(name="titularfirmanomold",length = 255)
    java.lang.String titularfirmanomold;

    @Column(name="titularfirmaemailold",length = 255)
    java.lang.String titularfirmaemailold;



  /** Constructor Buit */
  public SolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String codiSiaConv , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long organid , java.lang.Long estatSolicitud , java.lang.String expedientPid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Long estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID , java.lang.Long infomadridid , java.sql.Timestamp dataCaducitat , java.lang.Long fitxerConsentimentID , java.lang.Long contacteTitularID , java.lang.Long solicitudFusionadaID , java.lang.Long contactePersonaID , java.lang.Long contacteResponsableID , java.lang.Long contacteSolicitantID , java.lang.Long contacteGestAutID , java.lang.Long contacteAuditoriaID , java.lang.Long contacteTecnicID , java.lang.String titularFirmaNifOld , java.lang.String personacontacteold , java.lang.String personacontacteemailold , java.lang.String responsableprocnomold , java.lang.String responsableprocemailold , java.lang.String titularfirmanomold , java.lang.String titularfirmaemailold) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
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
  public SolicitudJPA(java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String codiSiaConv , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long organid , java.lang.Long estatSolicitud , java.lang.String expedientPid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Long estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID , java.lang.Long infomadridid , java.sql.Timestamp dataCaducitat , java.lang.Long fitxerConsentimentID , java.lang.Long contacteTitularID , java.lang.Long solicitudFusionadaID , java.lang.Long contactePersonaID , java.lang.Long contacteResponsableID , java.lang.Long contacteSolicitantID , java.lang.Long contacteGestAutID , java.lang.Long contacteAuditoriaID , java.lang.Long contacteTecnicID , java.lang.String titularFirmaNifOld , java.lang.String personacontacteold , java.lang.String personacontacteemailold , java.lang.String responsableprocnomold , java.lang.String responsableprocemailold , java.lang.String titularfirmanomold , java.lang.String titularfirmaemailold) {
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
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
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.Long estatSolicitud , java.sql.Timestamp dataInici , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String operador) {
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
  public SolicitudJPA(Solicitud __bean) {
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setCodiDescriptiu(__bean.getCodiDescriptiu());
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
    this.setDocumentSolicitud(FitxerJPA.toJPA(__bean.getDocumentSolicitud()));
    // Fitxer
    this.setSolicitudXml(FitxerJPA.toJPA(__bean.getSolicitudXml()));
    // Fitxer
    this.setFitxerConsentiment(FitxerJPA.toJPA(__bean.getFitxerConsentiment()));
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



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Solicitud) {
            Solicitud __instance = (Solicitud)__obj;
            __result = true;
            __result = __result && (this.getSolicitudID() == __instance.getSolicitudID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:solicitudid | Table: pad_documentsolicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<DocumentSolicitudJPA> documentSolicituds = new HashSet<DocumentSolicitudJPA>(0);
    public  Set<DocumentSolicitudJPA> getDocumentSolicituds() {
    return this.documentSolicituds;
  }

    public void setDocumentSolicituds(Set<DocumentSolicitudJPA> documentSolicituds) {
      this.documentSolicituds = documentSolicituds;
    }


// EXP  Field:solicitudid | Table: pad_event | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<EventJPA> events = new HashSet<EventJPA>(0);
    public  Set<EventJPA> getEvents() {
    return this.events;
  }

    public void setEvents(Set<EventJPA> events) {
      this.events = events;
    }


// EXP  Field:solicitudid | Table: pad_mod_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<ModificacioSolicitudJPA> modificacioSolicituds = new HashSet<ModificacioSolicitudJPA>(0);
    public  Set<ModificacioSolicitudJPA> getModificacioSolicituds() {
    return this.modificacioSolicituds;
  }

    public void setModificacioSolicituds(Set<ModificacioSolicitudJPA> modificacioSolicituds) {
      this.modificacioSolicituds = modificacioSolicituds;
    }


// EXP  Field:procedimentid | Table: pad_pinfodata | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<PinfoDataJPA> pinfoDatas = new HashSet<PinfoDataJPA>(0);
    public  Set<PinfoDataJPA> getPinfoDatas() {
    return this.pinfoDatas;
  }

    public void setPinfoDatas(Set<PinfoDataJPA> pinfoDatas) {
      this.pinfoDatas = pinfoDatas;
    }


// EXP  Field:solicitudid | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<SolicitudServeiJPA> solicitudServeis = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServeis() {
    return this.solicitudServeis;
  }

    public void setSolicitudServeis(Set<SolicitudServeiJPA> solicitudServeis) {
      this.solicitudServeis = solicitudServeis;
    }


// IMP Field:organid | Table: pad_organ | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organid", referencedColumnName ="organid", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_organ_fk"))
    private OrganJPA organ;

    public OrganJPA getOrgan() {
    return this.organ;
  }

    public  void setOrgan(OrganJPA organ) {
    this.organ = organ;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentsolicitudid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_fitxer_fk"))
    private FitxerJPA documentSolicitud;

    public FitxerJPA getDocumentSolicitud() {
    return this.documentSolicitud;
  }

    public  void setDocumentSolicitud(FitxerJPA documentSolicitud) {
    this.documentSolicitud = documentSolicitud;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitudxmlid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_fitxer_xml_fk"))
    private FitxerJPA solicitudXml;

    public FitxerJPA getSolicitudXml() {
    return this.solicitudXml;
  }

    public  void setSolicitudXml(FitxerJPA solicitudXml) {
    this.solicitudXml = solicitudXml;
  }

// IMP Field:infomadridid | Table: pad_infomadrid | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infomadridid", referencedColumnName ="infoMadridID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_infomad_id_fk"))
    private InfoMadridJPA infoMadrid;

    public InfoMadridJPA getInfoMadrid() {
    return this.infoMadrid;
  }

    public  void setInfoMadrid(InfoMadridJPA infoMadrid) {
    this.infoMadrid = infoMadrid;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerconsentimentid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_fitxer_cons_fk"))
    private FitxerJPA fitxerConsentiment;

    public FitxerJPA getFitxerConsentiment() {
    return this.fitxerConsentiment;
  }

    public  void setFitxerConsentiment(FitxerJPA fitxerConsentiment) {
    this.fitxerConsentiment = fitxerConsentiment;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactetitularid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_tit_fk"))
    private ContacteJPA contacteTitular;

    public ContacteJPA getContacteTitular() {
    return this.contacteTitular;
  }

    public  void setContacteTitular(ContacteJPA contacteTitular) {
    this.contacteTitular = contacteTitular;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactepersonaid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_pers_fk"))
    private ContacteJPA contactePersona;

    public ContacteJPA getContactePersona() {
    return this.contactePersona;
  }

    public  void setContactePersona(ContacteJPA contactePersona) {
    this.contactePersona = contactePersona;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contacteresponsableid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_resp_fk"))
    private ContacteJPA contacteResponsable;

    public ContacteJPA getContacteResponsable() {
    return this.contacteResponsable;
  }

    public  void setContacteResponsable(ContacteJPA contacteResponsable) {
    this.contacteResponsable = contacteResponsable;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactesolicitantid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_solic_fk"))
    private ContacteJPA contacteSolicitant;

    public ContacteJPA getContacteSolicitant() {
    return this.contacteSolicitant;
  }

    public  void setContacteSolicitant(ContacteJPA contacteSolicitant) {
    this.contacteSolicitant = contacteSolicitant;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactegestautid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_gestaut_fk"))
    private ContacteJPA contacteGestAut;

    public ContacteJPA getContacteGestAut() {
    return this.contacteGestAut;
  }

    public  void setContacteGestAut(ContacteJPA contacteGestAut) {
    this.contacteGestAut = contacteGestAut;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contacteauditoriaid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_audit_fk"))
    private ContacteJPA contacteAuditoria;

    public ContacteJPA getContacteAuditoria() {
    return this.contacteAuditoria;
  }

    public  void setContacteAuditoria(ContacteJPA contacteAuditoria) {
    this.contacteAuditoria = contacteAuditoria;
  }

// IMP Field:contacteid | Table: pad_contacte | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactetecnicid", referencedColumnName ="ContacteID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_cte_tecnic_fk"))
    private ContacteJPA contacteTecnic;

    public ContacteJPA getContacteTecnic() {
    return this.contacteTecnic;
  }

    public  void setContacteTecnic(ContacteJPA contacteTecnic) {
    this.contacteTecnic = contacteTecnic;
  }


 // ---------------  STATIC METHODS ------------------
  public static SolicitudJPA toJPA(Solicitud __bean) {
    if (__bean == null) { return null;}
    SolicitudJPA __tmp = new SolicitudJPA();
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setCodiDescriptiu(__bean.getCodiDescriptiu());
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
    __tmp.setDocumentSolicitud(FitxerJPA.toJPA(__bean.getDocumentSolicitud()));
    // Fitxer
    __tmp.setSolicitudXml(FitxerJPA.toJPA(__bean.getSolicitudXml()));
    // Fitxer
    __tmp.setFitxerConsentiment(FitxerJPA.toJPA(__bean.getFitxerConsentiment()));
		return __tmp;
	}


  public static SolicitudJPA copyJPA(SolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SolicitudJPA> copyJPA(java.util.Set<SolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SolicitudJPA> __tmpSet = (java.util.Set<SolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SolicitudJPA copyJPA(SolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SolicitudJPA __tmp = (SolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ModificacioSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modificacioSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getModificacioSolicituds())) ) {
      __tmp.setModificacioSolicituds(ModificacioSolicitudJPA.copyJPA(__jpa.getModificacioSolicituds(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"EventJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.events) || org.hibernate.Hibernate.isInitialized(__jpa.getEvents())) ) {
      __tmp.setEvents(EventJPA.copyJPA(__jpa.getEvents(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServeis) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServeis())) ) {
      __tmp.setSolicitudServeis(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServeis(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"PinfoDataJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pinfoDatas) || org.hibernate.Hibernate.isInitialized(__jpa.getPinfoDatas())) ) {
      __tmp.setPinfoDatas(PinfoDataJPA.copyJPA(__jpa.getPinfoDatas(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"DocumentSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentSolicituds())) ) {
      __tmp.setDocumentSolicituds(DocumentSolicitudJPA.copyJPA(__jpa.getDocumentSolicituds(), __alreadyCopied,"SolicitudJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteTecnic) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteTecnic()) ) ) {
      __tmp.setContacteTecnic(ContacteJPA.copyJPA(__jpa.getContacteTecnic(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"InfoMadridJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.infoMadrid) || org.hibernate.Hibernate.isInitialized(__jpa.getInfoMadrid()) ) ) {
      __tmp.setInfoMadrid(InfoMadridJPA.copyJPA(__jpa.getInfoMadrid(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteResponsable) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteResponsable()) ) ) {
      __tmp.setContacteResponsable(ContacteJPA.copyJPA(__jpa.getContacteResponsable(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contactePersona) || org.hibernate.Hibernate.isInitialized(__jpa.getContactePersona()) ) ) {
      __tmp.setContactePersona(ContacteJPA.copyJPA(__jpa.getContactePersona(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteSolicitant) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteSolicitant()) ) ) {
      __tmp.setContacteSolicitant(ContacteJPA.copyJPA(__jpa.getContacteSolicitant(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteAuditoria) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteAuditoria()) ) ) {
      __tmp.setContacteAuditoria(ContacteJPA.copyJPA(__jpa.getContacteAuditoria(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteGestAut) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteGestAut()) ) ) {
      __tmp.setContacteGestAut(ContacteJPA.copyJPA(__jpa.getContacteGestAut(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"ContacteJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.contacteTitular) || org.hibernate.Hibernate.isInitialized(__jpa.getContacteTitular()) ) ) {
      __tmp.setContacteTitular(ContacteJPA.copyJPA(__jpa.getContacteTitular(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"OrganJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.organ) || org.hibernate.Hibernate.isInitialized(__jpa.getOrgan()) ) ) {
      __tmp.setOrgan(OrganJPA.copyJPA(__jpa.getOrgan(), __alreadyCopied,"SolicitudJPA"));
    }

    return __tmp;
  }




}

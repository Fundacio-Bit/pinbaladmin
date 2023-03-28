
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;


public class IncidenciaTecnicaBean implements IncidenciaTecnica {



	long incidenciaTecnicaID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	int estat;
	int tipus;
	java.lang.String nomEntitat;
	java.lang.String contacteNom;
	java.lang.String contacteEmail;
	java.lang.String contacteTelefon;
	java.lang.String caidIdentificadorConsulta;
	java.lang.String caidNumeroSeguiment;
	java.lang.String creador;
	java.lang.String operador;


  /** Constructor Buit */
  public IncidenciaTecnicaBean() {
  }

  /** Constructor amb tots els camps  */
  public IncidenciaTecnicaBean(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int estat , int tipus , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment , java.lang.String creador , java.lang.String operador) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.estat=estat;
    this.tipus=tipus;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
    this.creador=creador;
    this.operador=operador;
}
  /** Constructor sense valors autoincrementals */
  public IncidenciaTecnicaBean(java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int estat , int tipus , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment , java.lang.String creador , java.lang.String operador) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.estat=estat;
    this.tipus=tipus;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
    this.creador=creador;
    this.operador=operador;
}
  /** Constructor dels valors Not Null */
  public IncidenciaTecnicaBean(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , int tipus , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String creador , java.lang.String operador) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.tipus=tipus;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.creador=creador;
    this.operador=operador;
}
  public IncidenciaTecnicaBean(IncidenciaTecnica __bean) {
    this.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setEstat(__bean.getEstat());
    this.setTipus(__bean.getTipus());
    this.setNomEntitat(__bean.getNomEntitat());
    this.setContacteNom(__bean.getContacteNom());
    this.setContacteEmail(__bean.getContacteEmail());
    this.setContacteTelefon(__bean.getContacteTelefon());
    this.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    this.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
    this.setCreador(__bean.getCreador());
    this.setOperador(__bean.getOperador());
	}

	public long getIncidenciaTecnicaID() {
		return(incidenciaTecnicaID);
	};
	public void setIncidenciaTecnicaID(long _incidenciaTecnicaID_) {
		this.incidenciaTecnicaID = _incidenciaTecnicaID_;
	};

	public java.lang.String getTitol() {
		return(titol);
	};
	public void setTitol(java.lang.String _titol_) {
		this.titol = _titol_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
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

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getNomEntitat() {
		return(nomEntitat);
	};
	public void setNomEntitat(java.lang.String _nomEntitat_) {
		this.nomEntitat = _nomEntitat_;
	};

	public java.lang.String getContacteNom() {
		return(contacteNom);
	};
	public void setContacteNom(java.lang.String _contacteNom_) {
		this.contacteNom = _contacteNom_;
	};

	public java.lang.String getContacteEmail() {
		return(contacteEmail);
	};
	public void setContacteEmail(java.lang.String _contacteEmail_) {
		this.contacteEmail = _contacteEmail_;
	};

	public java.lang.String getContacteTelefon() {
		return(contacteTelefon);
	};
	public void setContacteTelefon(java.lang.String _contacteTelefon_) {
		this.contacteTelefon = _contacteTelefon_;
	};

	public java.lang.String getCaidIdentificadorConsulta() {
		return(caidIdentificadorConsulta);
	};
	public void setCaidIdentificadorConsulta(java.lang.String _caidIdentificadorConsulta_) {
		this.caidIdentificadorConsulta = _caidIdentificadorConsulta_;
	};

	public java.lang.String getCaidNumeroSeguiment() {
		return(caidNumeroSeguiment);
	};
	public void setCaidNumeroSeguiment(java.lang.String _caidNumeroSeguiment_) {
		this.caidNumeroSeguiment = _caidNumeroSeguiment_;
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



  // ======================================

  public static IncidenciaTecnicaBean toBean(IncidenciaTecnica __bean) {
    if (__bean == null) { return null;}
    IncidenciaTecnicaBean __tmp = new IncidenciaTecnicaBean();
    __tmp.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNomEntitat(__bean.getNomEntitat());
    __tmp.setContacteNom(__bean.getContacteNom());
    __tmp.setContacteEmail(__bean.getContacteEmail());
    __tmp.setContacteTelefon(__bean.getContacteTelefon());
    __tmp.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    __tmp.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
    __tmp.setCreador(__bean.getCreador());
    __tmp.setOperador(__bean.getOperador());
		return __tmp;
	}



}

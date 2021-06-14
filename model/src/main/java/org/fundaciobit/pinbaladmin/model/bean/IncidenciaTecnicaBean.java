
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;


public class IncidenciaTecnicaBean implements IncidenciaTecnica {



private static final long serialVersionUID = 301195586L;

	long incidenciaTecnicaID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	java.sql.Timestamp dataInici;
	int estat;
	java.lang.String nomEntitat;
	java.lang.String contacteNom;
	java.lang.String contacteEmail;
	java.lang.String contacteTelefon;
	java.lang.String caidIdentificadorConsulta;
	java.lang.String caidNumeroSeguiment;


  /** Constructor Buit */
  public IncidenciaTecnicaBean() {
  }

  /** Constructor amb tots els camps  */
  public IncidenciaTecnicaBean(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
}
  /** Constructor sense valors autoincrementals */
  public IncidenciaTecnicaBean(java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
}
  /** Constructor dels valors Not Null */
  public IncidenciaTecnicaBean(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String contacteNom , java.lang.String contacteEmail) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
}
  public IncidenciaTecnicaBean(IncidenciaTecnica __bean) {
    this.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setDataInici(__bean.getDataInici());
    this.setEstat(__bean.getEstat());
    this.setNomEntitat(__bean.getNomEntitat());
    this.setContacteNom(__bean.getContacteNom());
    this.setContacteEmail(__bean.getContacteEmail());
    this.setContacteTelefon(__bean.getContacteTelefon());
    this.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    this.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
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

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
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



  // ======================================

  public static IncidenciaTecnicaBean toBean(IncidenciaTecnica __bean) {
    if (__bean == null) { return null;}
    IncidenciaTecnicaBean __tmp = new IncidenciaTecnicaBean();
    __tmp.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setNomEntitat(__bean.getNomEntitat());
    __tmp.setContacteNom(__bean.getContacteNom());
    __tmp.setContacteEmail(__bean.getContacteEmail());
    __tmp.setContacteTelefon(__bean.getContacteTelefon());
    __tmp.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    __tmp.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
		return __tmp;
	}



}

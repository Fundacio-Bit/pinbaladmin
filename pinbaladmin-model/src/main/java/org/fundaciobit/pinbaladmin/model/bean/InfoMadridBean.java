
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;


public class InfoMadridBean implements InfoMadrid {



	long infoMadridID;// PK
	java.lang.String codi;
	long estatProcediment;
	long estatAutoritzacio;
	java.lang.String missatge;
	java.lang.String consulta;
	java.lang.String titularNom;
	java.lang.String titularNif;
	java.sql.Timestamp dataAutoritzacio;
	java.sql.Timestamp dataEnviament;
	long intents;


  /** Constructor Buit */
  public InfoMadridBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoMadridBean(long infoMadridID , java.lang.String codi , long estatProcediment , long estatAutoritzacio , java.lang.String missatge , java.lang.String consulta , java.lang.String titularNom , java.lang.String titularNif , java.sql.Timestamp dataAutoritzacio , java.sql.Timestamp dataEnviament , long intents) {
    this.infoMadridID=infoMadridID;
    this.codi=codi;
    this.estatProcediment=estatProcediment;
    this.estatAutoritzacio=estatAutoritzacio;
    this.missatge=missatge;
    this.consulta=consulta;
    this.titularNom=titularNom;
    this.titularNif=titularNif;
    this.dataAutoritzacio=dataAutoritzacio;
    this.dataEnviament=dataEnviament;
    this.intents=intents;
}
  /** Constructor sense valors autoincrementals */
  public InfoMadridBean(java.lang.String codi , long estatProcediment , long estatAutoritzacio , java.lang.String missatge , java.lang.String consulta , java.lang.String titularNom , java.lang.String titularNif , java.sql.Timestamp dataAutoritzacio , java.sql.Timestamp dataEnviament , long intents) {
    this.codi=codi;
    this.estatProcediment=estatProcediment;
    this.estatAutoritzacio=estatAutoritzacio;
    this.missatge=missatge;
    this.consulta=consulta;
    this.titularNom=titularNom;
    this.titularNif=titularNif;
    this.dataAutoritzacio=dataAutoritzacio;
    this.dataEnviament=dataEnviament;
    this.intents=intents;
}
  /** Constructor dels valors Not Null */
  public InfoMadridBean(long infoMadridID , java.lang.String codi , long intents) {
    this.infoMadridID=infoMadridID;
    this.codi=codi;
    this.intents=intents;
}
  public InfoMadridBean(InfoMadrid __bean) {
    this.setInfoMadridID(__bean.getInfoMadridID());
    this.setCodi(__bean.getCodi());
    this.setEstatProcediment(__bean.getEstatProcediment());
    this.setEstatAutoritzacio(__bean.getEstatAutoritzacio());
    this.setMissatge(__bean.getMissatge());
    this.setConsulta(__bean.getConsulta());
    this.setTitularNom(__bean.getTitularNom());
    this.setTitularNif(__bean.getTitularNif());
    this.setDataAutoritzacio(__bean.getDataAutoritzacio());
    this.setDataEnviament(__bean.getDataEnviament());
    this.setIntents(__bean.getIntents());
	}

	public long getInfoMadridID() {
		return(infoMadridID);
	};
	public void setInfoMadridID(long _infoMadridID_) {
		this.infoMadridID = _infoMadridID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public long getEstatProcediment() {
		return(estatProcediment);
	};
	public void setEstatProcediment(long _estatProcediment_) {
		this.estatProcediment = _estatProcediment_;
	};

	public long getEstatAutoritzacio() {
		return(estatAutoritzacio);
	};
	public void setEstatAutoritzacio(long _estatAutoritzacio_) {
		this.estatAutoritzacio = _estatAutoritzacio_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.lang.String getConsulta() {
		return(consulta);
	};
	public void setConsulta(java.lang.String _consulta_) {
		this.consulta = _consulta_;
	};

	public java.lang.String getTitularNom() {
		return(titularNom);
	};
	public void setTitularNom(java.lang.String _titularNom_) {
		this.titularNom = _titularNom_;
	};

	public java.lang.String getTitularNif() {
		return(titularNif);
	};
	public void setTitularNif(java.lang.String _titularNif_) {
		this.titularNif = _titularNif_;
	};

	public java.sql.Timestamp getDataAutoritzacio() {
		return(dataAutoritzacio);
	};
	public void setDataAutoritzacio(java.sql.Timestamp _dataAutoritzacio_) {
		this.dataAutoritzacio = _dataAutoritzacio_;
	};

	public java.sql.Timestamp getDataEnviament() {
		return(dataEnviament);
	};
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_) {
		this.dataEnviament = _dataEnviament_;
	};

	public long getIntents() {
		return(intents);
	};
	public void setIntents(long _intents_) {
		this.intents = _intents_;
	};



  // ======================================

  public static InfoMadridBean toBean(InfoMadrid __bean) {
    if (__bean == null) { return null;}
    InfoMadridBean __tmp = new InfoMadridBean();
    __tmp.setInfoMadridID(__bean.getInfoMadridID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setEstatProcediment(__bean.getEstatProcediment());
    __tmp.setEstatAutoritzacio(__bean.getEstatAutoritzacio());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setConsulta(__bean.getConsulta());
    __tmp.setTitularNom(__bean.getTitularNom());
    __tmp.setTitularNif(__bean.getTitularNif());
    __tmp.setDataAutoritzacio(__bean.getDataAutoritzacio());
    __tmp.setDataEnviament(__bean.getDataEnviament());
    __tmp.setIntents(__bean.getIntents());
		return __tmp;
	}



}

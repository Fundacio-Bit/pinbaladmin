
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Tiquet;


public class TiquetBean implements Tiquet {



	long tiquetID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	java.lang.String informador;
	java.sql.Timestamp dataAlta;
	long estatTiquetID;
	long tipusTiquetID;
	java.lang.String versioPinbal;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataIncidencia;
	java.lang.String solucionatPer;
	java.sql.Timestamp datafi;
	java.lang.String notes;
	int entorn;
	java.lang.Long adjunt1ID;
	java.lang.Long adjunt2ID;


  /** Constructor Buit */
  public TiquetBean() {
  }

  /** Constructor amb tots els camps  */
  public TiquetBean(long tiquetID , java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , java.sql.Timestamp dataInici , java.sql.Timestamp dataIncidencia , java.lang.String solucionatPer , java.sql.Timestamp datafi , java.lang.String notes , int entorn , java.lang.Long adjunt1ID , java.lang.Long adjunt2ID) {
    this.tiquetID=tiquetID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.dataInici=dataInici;
    this.dataIncidencia=dataIncidencia;
    this.solucionatPer=solucionatPer;
    this.datafi=datafi;
    this.notes=notes;
    this.entorn=entorn;
    this.adjunt1ID=adjunt1ID;
    this.adjunt2ID=adjunt2ID;
}
  /** Constructor sense valors autoincrementals */
  public TiquetBean(java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , java.sql.Timestamp dataInici , java.sql.Timestamp dataIncidencia , java.lang.String solucionatPer , java.sql.Timestamp datafi , java.lang.String notes , int entorn , java.lang.Long adjunt1ID , java.lang.Long adjunt2ID) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.dataInici=dataInici;
    this.dataIncidencia=dataIncidencia;
    this.solucionatPer=solucionatPer;
    this.datafi=datafi;
    this.notes=notes;
    this.entorn=entorn;
    this.adjunt1ID=adjunt1ID;
    this.adjunt2ID=adjunt2ID;
}
  /** Constructor dels valors Not Null */
  public TiquetBean(long tiquetID , java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , int entorn) {
    this.tiquetID=tiquetID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.entorn=entorn;
}
  public TiquetBean(Tiquet __bean) {
    this.setTiquetID(__bean.getTiquetID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setInformador(__bean.getInformador());
    this.setDataAlta(__bean.getDataAlta());
    this.setEstatTiquetID(__bean.getEstatTiquetID());
    this.setTipusTiquetID(__bean.getTipusTiquetID());
    this.setVersioPinbal(__bean.getVersioPinbal());
    this.setDataInici(__bean.getDataInici());
    this.setDataIncidencia(__bean.getDataIncidencia());
    this.setSolucionatPer(__bean.getSolucionatPer());
    this.setDatafi(__bean.getDatafi());
    this.setNotes(__bean.getNotes());
    this.setEntorn(__bean.getEntorn());
    this.setAdjunt1ID(__bean.getAdjunt1ID());
    this.setAdjunt2ID(__bean.getAdjunt2ID());
    // Fitxer
    this.setAdjunt1(FitxerBean.toBean(__bean.getAdjunt1()));
    // Fitxer
    this.setAdjunt2(FitxerBean.toBean(__bean.getAdjunt2()));
	}

	public long getTiquetID() {
		return(tiquetID);
	};
	public void setTiquetID(long _tiquetID_) {
		this.tiquetID = _tiquetID_;
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

	public java.lang.String getInformador() {
		return(informador);
	};
	public void setInformador(java.lang.String _informador_) {
		this.informador = _informador_;
	};

	public java.sql.Timestamp getDataAlta() {
		return(dataAlta);
	};
	public void setDataAlta(java.sql.Timestamp _dataAlta_) {
		this.dataAlta = _dataAlta_;
	};

	public long getEstatTiquetID() {
		return(estatTiquetID);
	};
	public void setEstatTiquetID(long _estatTiquetID_) {
		this.estatTiquetID = _estatTiquetID_;
	};

	public long getTipusTiquetID() {
		return(tipusTiquetID);
	};
	public void setTipusTiquetID(long _tipusTiquetID_) {
		this.tipusTiquetID = _tipusTiquetID_;
	};

	public java.lang.String getVersioPinbal() {
		return(versioPinbal);
	};
	public void setVersioPinbal(java.lang.String _versioPinbal_) {
		this.versioPinbal = _versioPinbal_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataIncidencia() {
		return(dataIncidencia);
	};
	public void setDataIncidencia(java.sql.Timestamp _dataIncidencia_) {
		this.dataIncidencia = _dataIncidencia_;
	};

	public java.lang.String getSolucionatPer() {
		return(solucionatPer);
	};
	public void setSolucionatPer(java.lang.String _solucionatPer_) {
		this.solucionatPer = _solucionatPer_;
	};

	public java.sql.Timestamp getDatafi() {
		return(datafi);
	};
	public void setDatafi(java.sql.Timestamp _datafi_) {
		this.datafi = _datafi_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public int getEntorn() {
		return(entorn);
	};
	public void setEntorn(int _entorn_) {
		this.entorn = _entorn_;
	};

	public java.lang.Long getAdjunt1ID() {
		return(adjunt1ID);
	};
	public void setAdjunt1ID(java.lang.Long _adjunt1ID_) {
		this.adjunt1ID = _adjunt1ID_;
	};

	public java.lang.Long getAdjunt2ID() {
		return(adjunt2ID);
	};
	public void setAdjunt2ID(java.lang.Long _adjunt2ID_) {
		this.adjunt2ID = _adjunt2ID_;
	};



  // ======================================

  public static TiquetBean toBean(Tiquet __bean) {
    if (__bean == null) { return null;}
    TiquetBean __tmp = new TiquetBean();
    __tmp.setTiquetID(__bean.getTiquetID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setInformador(__bean.getInformador());
    __tmp.setDataAlta(__bean.getDataAlta());
    __tmp.setEstatTiquetID(__bean.getEstatTiquetID());
    __tmp.setTipusTiquetID(__bean.getTipusTiquetID());
    __tmp.setVersioPinbal(__bean.getVersioPinbal());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataIncidencia(__bean.getDataIncidencia());
    __tmp.setSolucionatPer(__bean.getSolucionatPer());
    __tmp.setDatafi(__bean.getDatafi());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setEntorn(__bean.getEntorn());
    __tmp.setAdjunt1ID(__bean.getAdjunt1ID());
    __tmp.setAdjunt2ID(__bean.getAdjunt2ID());
    // Fitxer
    __tmp.setAdjunt1(FitxerBean.toBean(__bean.getAdjunt1()));
    // Fitxer
    __tmp.setAdjunt2(FitxerBean.toBean(__bean.getAdjunt2()));
		return __tmp;
	}

  protected FitxerBean adjunt1;
  public FitxerBean getAdjunt1() {
    return adjunt1;
  }
  public void setAdjunt1(FitxerBean __field) {
    this. adjunt1 = __field;
  }
  protected FitxerBean adjunt2;
  public FitxerBean getAdjunt2() {
    return adjunt2;
  }
  public void setAdjunt2(FitxerBean __field) {
    this. adjunt2 = __field;
  }


}

package org.fundaciobit.pinbaladmin.model.entity;

public interface Tiquet extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getTiquetID();
	public void setTiquetID(long _tiquetID_);

	public java.lang.String getTitol();
	public void setTitol(java.lang.String _titol_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.String getInformador();
	public void setInformador(java.lang.String _informador_);

	public java.sql.Timestamp getDataAlta();
	public void setDataAlta(java.sql.Timestamp _dataAlta_);

	public long getEstatTiquetID();
	public void setEstatTiquetID(long _estatTiquetID_);

	public long getTipusTiquetID();
	public void setTipusTiquetID(long _tipusTiquetID_);

	public java.lang.String getVersioPinbal();
	public void setVersioPinbal(java.lang.String _versioPinbal_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataIncidencia();
	public void setDataIncidencia(java.sql.Timestamp _dataIncidencia_);

	public java.lang.String getSolucionatPer();
	public void setSolucionatPer(java.lang.String _solucionatPer_);

	public java.sql.Timestamp getDatafi();
	public void setDatafi(java.sql.Timestamp _datafi_);

	public java.lang.String getNotes();
	public void setNotes(java.lang.String _notes_);

	public int getEntorn();
	public void setEntorn(int _entorn_);

	public java.lang.Long getAdjunt1ID();
	public void setAdjunt1ID(java.lang.Long _adjunt1ID_);

	public java.lang.Long getAdjunt2ID();
	public void setAdjunt2ID(java.lang.Long _adjunt2ID_);

  // Fitxer
  public <F extends Fitxer> F getAdjunt1();
  // Fitxer
  public <F extends Fitxer> F getAdjunt2();


  // ======================================

}

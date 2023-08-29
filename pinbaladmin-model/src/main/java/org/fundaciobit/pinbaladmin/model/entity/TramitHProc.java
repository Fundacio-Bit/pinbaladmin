package org.fundaciobit.pinbaladmin.model.entity;

public interface TramitHProc extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getProcid();
	public void setProcid(long _procid_);

	public long getTramitid();
	public void setTramitid(long _tramitid_);

	public java.lang.String getTipus();
	public void setTipus(java.lang.String _tipus_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public java.lang.String getUrlseu();
	public void setUrlseu(java.lang.String _urlseu_);

	public boolean isCaducitat();
	public void setCaducitat(boolean _caducitat_);

	public java.sql.Timestamp getCaducitatdata();
	public void setCaducitatdata(java.sql.Timestamp _caducitatdata_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public long getPeticionsaldia();
	public void setPeticionsaldia(long _peticionsaldia_);

	public long getPeticionsalmes();
	public void setPeticionsalmes(long _peticionsalmes_);

	public boolean isPeriodico();
	public void setPeriodico(boolean _periodico_);

	public boolean isAutomatizado();
	public void setAutomatizado(boolean _automatizado_);



  // ======================================

}

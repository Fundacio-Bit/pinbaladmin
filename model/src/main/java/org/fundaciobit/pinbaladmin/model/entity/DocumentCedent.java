package org.fundaciobit.pinbaladmin.model.entity;

public interface DocumentCedent extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getDocumentCedentID();
	public void setDocumentCedentID(long _documentCedentID_);

	public java.lang.String getTitol();
	public void setTitol(java.lang.String _titol_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public long getEntitatServeiID();
	public void setEntitatServeiID(long _entitatServeiID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}

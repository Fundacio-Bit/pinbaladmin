package org.fundaciobit.pinbaladmin.model.entity;

public interface DocumentEntitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getDocumentEntitatID();
	public void setDocumentEntitatID(long _documentEntitatID_);

	public java.lang.String getTitol();
	public void setTitol(java.lang.String _titol_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

	public java.sql.Timestamp getDataAlta();
	public void setDataAlta(java.sql.Timestamp _dataAlta_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}

package org.fundaciobit.pinbaladmin.model.entity;

public interface Formulari extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getFormulariid();
	public void setFormulariid(long _formulariid_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}

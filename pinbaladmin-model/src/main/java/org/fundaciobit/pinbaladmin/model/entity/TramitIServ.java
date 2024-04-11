package org.fundaciobit.pinbaladmin.model.entity;

public interface TramitIServ extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getServid();
	public void setServid(long _servid_);

	public long getTramitid();
	public void setTramitid(long _tramitid_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public java.lang.String getNorma();
	public void setNorma(java.lang.String _norma_);

	public java.lang.String getUrlnorma();
	public void setUrlnorma(java.lang.String _urlnorma_);

	public java.lang.String getArticles();
	public void setArticles(java.lang.String _articles_);

	public java.lang.String getNorma2();
	public void setNorma2(java.lang.String _norma2_);

	public java.lang.String getUrlnorma2();
	public void setUrlnorma2(java.lang.String _urlnorma2_);

	public java.lang.String getArticles2();
	public void setArticles2(java.lang.String _articles2_);

	public java.lang.Long getFitxernormaID();
	public void setFitxernormaID(java.lang.Long _fitxernormaID_);

	public java.lang.Long getFitxernorma2ID();
	public void setFitxernorma2ID(java.lang.Long _fitxernorma2ID_);

  // Fitxer
  public <F extends Fitxer> F getFitxernorma();
  // Fitxer
  public <F extends Fitxer> F getFitxernorma2();


  // ======================================

}

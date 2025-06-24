package org.fundaciobit.pinbaladmin.model.entity;

public interface ModificacioSoliServ extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getModsoliservid();
	public void setModsoliservid(long _modsoliservid_);

	public long getSoliServID();
	public void setSoliServID(long _soliServID_);

	public long getModSoliID();
	public void setModSoliID(long _modSoliID_);

	public java.lang.String getEstat();
	public void setEstat(java.lang.String _estat_);

	public java.lang.String getNorma1();
	public void setNorma1(java.lang.String _norma1_);

	public java.lang.String getArticles1();
	public void setArticles1(java.lang.String _articles1_);

	public java.lang.Long getFitxerNorma1ID();
	public void setFitxerNorma1ID(java.lang.Long _fitxerNorma1ID_);

	public java.lang.String getNorma2();
	public void setNorma2(java.lang.String _norma2_);

	public java.lang.String getArticles2();
	public void setArticles2(java.lang.String _articles2_);

	public java.lang.Long getFitxerNorma2ID();
	public void setFitxerNorma2ID(java.lang.Long _fitxerNorma2ID_);

	public java.lang.String getNorma3();
	public void setNorma3(java.lang.String _norma3_);

	public java.lang.String getArticles3();
	public void setArticles3(java.lang.String _articles3_);

	public java.lang.Long getFitxerNorma3ID();
	public void setFitxerNorma3ID(java.lang.Long _fitxerNorma3ID_);

  // Fitxer
  public <F extends Fitxer> F getFitxerNorma1();
  // Fitxer
  public <F extends Fitxer> F getFitxerNorma2();
  // Fitxer
  public <F extends Fitxer> F getFitxerNorma3();


  // ======================================

}

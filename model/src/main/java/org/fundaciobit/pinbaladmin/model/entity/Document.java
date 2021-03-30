package org.fundaciobit.pinbaladmin.model.entity;

public interface Document extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getDocumentID();
	public void setDocumentID(long _documentID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public long getFitxerOriginalID();
	public void setFitxerOriginalID(long _fitxerOriginalID_);

	public java.lang.Long getFitxerFirmatID();
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_);

	public java.lang.String getNotes();
	public void setNotes(java.lang.String _notes_);

  // Fitxer
  public <F extends Fitxer> F getFitxerOriginal();
  // Fitxer
  public <F extends Fitxer> F getFitxerFirmat();


  // ======================================

}

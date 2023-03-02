
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Operador;


public class OperadorBean implements Operador {



	long operadorID;// PK
	java.lang.String username;
	java.lang.String nom;
	java.lang.String email;


  /** Constructor Buit */
  public OperadorBean() {
  }

  /** Constructor amb tots els camps  */
  public OperadorBean(long operadorID , java.lang.String username , java.lang.String nom , java.lang.String email) {
    this.operadorID=operadorID;
    this.username=username;
    this.nom=nom;
    this.email=email;
}
  /** Constructor sense valors autoincrementals */
  public OperadorBean(java.lang.String username , java.lang.String nom , java.lang.String email) {
    this.username=username;
    this.nom=nom;
    this.email=email;
}
  public OperadorBean(Operador __bean) {
    this.setOperadorID(__bean.getOperadorID());
    this.setUsername(__bean.getUsername());
    this.setNom(__bean.getNom());
    this.setEmail(__bean.getEmail());
	}

	public long getOperadorID() {
		return(operadorID);
	};
	public void setOperadorID(long _operadorID_) {
		this.operadorID = _operadorID_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};



  // ======================================

  public static OperadorBean toBean(Operador __bean) {
    if (__bean == null) { return null;}
    OperadorBean __tmp = new OperadorBean();
    __tmp.setOperadorID(__bean.getOperadorID());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNom(__bean.getNom());
    __tmp.setEmail(__bean.getEmail());
		return __tmp;
	}



}

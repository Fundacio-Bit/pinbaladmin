
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.GrupEntitatCedent;


public class GrupEntitatCedentBean implements GrupEntitatCedent {



private static final long serialVersionUID = -1047308452L;

	long grupEntitatCedentID;// PK
	long grupEntitatID;
	long cedentID;


  /** Constructor Buit */
  public GrupEntitatCedentBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatCedentBean(long grupEntitatCedentID , long grupEntitatID , long cedentID) {
    this.grupEntitatCedentID=grupEntitatCedentID;
    this.grupEntitatID=grupEntitatID;
    this.cedentID=cedentID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatCedentBean(long grupEntitatID , long cedentID) {
    this.grupEntitatID=grupEntitatID;
    this.cedentID=cedentID;
}
  public GrupEntitatCedentBean(GrupEntitatCedent __bean) {
    this.setGrupEntitatCedentID(__bean.getGrupEntitatCedentID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setCedentID(__bean.getCedentID());
	}

	public long getGrupEntitatCedentID() {
		return(grupEntitatCedentID);
	};
	public void setGrupEntitatCedentID(long _grupEntitatCedentID_) {
		this.grupEntitatCedentID = _grupEntitatCedentID_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public long getCedentID() {
		return(cedentID);
	};
	public void setCedentID(long _cedentID_) {
		this.cedentID = _cedentID_;
	};



  // ======================================

  public static GrupEntitatCedentBean toBean(GrupEntitatCedent __bean) {
    if (__bean == null) { return null;}
    GrupEntitatCedentBean __tmp = new GrupEntitatCedentBean();
    __tmp.setGrupEntitatCedentID(__bean.getGrupEntitatCedentID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setCedentID(__bean.getCedentID());
		return __tmp;
	}



}

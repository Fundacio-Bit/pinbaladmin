
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "OperadorJPA")
@Table(name = "pad_operador" , indexes = { 
        @Index(name="pad_operador_pk_i", columnList = "operadorid")})
@SequenceGenerator(name="OPERADOR_SEQ", sequenceName="pad_operador_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class OperadorJPA implements Operador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="OPERADOR_SEQ")
    @Column(name="operadorid",nullable = false,length = 19)
    long operadorID;

    @Column(name="username",nullable = false,length = 255)
    java.lang.String username;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="email",nullable = false,length = 255)
    java.lang.String email;



  /** Constructor Buit */
  public OperadorJPA() {
  }

  /** Constructor amb tots els camps  */
  public OperadorJPA(long operadorID , java.lang.String username , java.lang.String nom , java.lang.String email) {
    this.operadorID=operadorID;
    this.username=username;
    this.nom=nom;
    this.email=email;
}
  /** Constructor sense valors autoincrementals */
  public OperadorJPA(java.lang.String username , java.lang.String nom , java.lang.String email) {
    this.username=username;
    this.nom=nom;
    this.email=email;
}
  public OperadorJPA(Operador __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Operador) {
      Operador __instance = (Operador)__obj;
      __result = true;
      __result = __result && (this.getOperadorID() == __instance.getOperadorID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static OperadorJPA toJPA(Operador __bean) {
    if (__bean == null) { return null;}
    OperadorJPA __tmp = new OperadorJPA();
    __tmp.setOperadorID(__bean.getOperadorID());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNom(__bean.getNom());
    __tmp.setEmail(__bean.getEmail());
		return __tmp;
	}


  public static OperadorJPA copyJPA(OperadorJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<OperadorJPA> copyJPA(java.util.Set<OperadorJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<OperadorJPA> __tmpSet = (java.util.Set<OperadorJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<OperadorJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (OperadorJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static OperadorJPA copyJPA(OperadorJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    OperadorJPA __tmp = (OperadorJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}

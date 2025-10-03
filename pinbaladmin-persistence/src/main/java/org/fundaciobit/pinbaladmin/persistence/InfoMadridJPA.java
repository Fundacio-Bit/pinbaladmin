
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "InfoMadridJPA")
@Table(name = "pad_infomadrid" , indexes = { 
        @Index(name="pad_infomadrid_pk_i", columnList = "infomadridid")})
@SequenceGenerator(name="INFOMADRID_SEQ", sequenceName="pad_infomadrid_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoMadridJPA implements InfoMadrid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOMADRID_SEQ")
    @Column(name="infomadridid",nullable = false,length = 19)
    long infoMadridID;

    @Column(name="codi",nullable = false,length = 20)
    java.lang.String codi;

    @Column(name="estatprocediment",length = 19)
    java.lang.Long estatProcediment;

    @Column(name="estatautoritzacio",length = 19)
    java.lang.Long estatAutoritzacio;

    @Column(name="missatge",length = 1024)
    java.lang.String missatge;

    @Column(name="consulta",length = 240)
    java.lang.String consulta;

    @Column(name="titularnom",length = 240)
    java.lang.String titularNom;

    @Column(name="titularnif",length = 20)
    java.lang.String titularNif;

    @Column(name="dataautoritzacio",length = 29,precision = 6)
    java.sql.Timestamp dataAutoritzacio;

    @Column(name="dataenviament",length = 29,precision = 6)
    java.sql.Timestamp dataEnviament;

    @Column(name="intents",nullable = false,length = 19)
    java.lang.Long intents;

    @Column(name="dataconsulta",length = 29,precision = 6)
    java.sql.Timestamp dataConsulta;



  /** Constructor Buit */
  public InfoMadridJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoMadridJPA(long infoMadridID , java.lang.String codi , java.lang.Long estatProcediment , java.lang.Long estatAutoritzacio , java.lang.String missatge , java.lang.String consulta , java.lang.String titularNom , java.lang.String titularNif , java.sql.Timestamp dataAutoritzacio , java.sql.Timestamp dataEnviament , java.lang.Long intents , java.sql.Timestamp dataConsulta) {
    this.infoMadridID=infoMadridID;
    this.codi=codi;
    this.estatProcediment=estatProcediment;
    this.estatAutoritzacio=estatAutoritzacio;
    this.missatge=missatge;
    this.consulta=consulta;
    this.titularNom=titularNom;
    this.titularNif=titularNif;
    this.dataAutoritzacio=dataAutoritzacio;
    this.dataEnviament=dataEnviament;
    this.intents=intents;
    this.dataConsulta=dataConsulta;
}
  /** Constructor sense valors autoincrementals */
  public InfoMadridJPA(java.lang.String codi , java.lang.Long estatProcediment , java.lang.Long estatAutoritzacio , java.lang.String missatge , java.lang.String consulta , java.lang.String titularNom , java.lang.String titularNif , java.sql.Timestamp dataAutoritzacio , java.sql.Timestamp dataEnviament , java.lang.Long intents , java.sql.Timestamp dataConsulta) {
    this.codi=codi;
    this.estatProcediment=estatProcediment;
    this.estatAutoritzacio=estatAutoritzacio;
    this.missatge=missatge;
    this.consulta=consulta;
    this.titularNom=titularNom;
    this.titularNif=titularNif;
    this.dataAutoritzacio=dataAutoritzacio;
    this.dataEnviament=dataEnviament;
    this.intents=intents;
    this.dataConsulta=dataConsulta;
}
  /** Constructor dels valors Not Null */
  public InfoMadridJPA(long infoMadridID , java.lang.String codi , java.lang.Long intents) {
    this.infoMadridID=infoMadridID;
    this.codi=codi;
    this.intents=intents;
}
  public InfoMadridJPA(InfoMadrid __bean) {
    this.setInfoMadridID(__bean.getInfoMadridID());
    this.setCodi(__bean.getCodi());
    this.setEstatProcediment(__bean.getEstatProcediment());
    this.setEstatAutoritzacio(__bean.getEstatAutoritzacio());
    this.setMissatge(__bean.getMissatge());
    this.setConsulta(__bean.getConsulta());
    this.setTitularNom(__bean.getTitularNom());
    this.setTitularNif(__bean.getTitularNif());
    this.setDataAutoritzacio(__bean.getDataAutoritzacio());
    this.setDataEnviament(__bean.getDataEnviament());
    this.setIntents(__bean.getIntents());
    this.setDataConsulta(__bean.getDataConsulta());
	}

	public long getInfoMadridID() {
		return(infoMadridID);
	};
	public void setInfoMadridID(long _infoMadridID_) {
		this.infoMadridID = _infoMadridID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.Long getEstatProcediment() {
		return(estatProcediment);
	};
	public void setEstatProcediment(java.lang.Long _estatProcediment_) {
		this.estatProcediment = _estatProcediment_;
	};

	public java.lang.Long getEstatAutoritzacio() {
		return(estatAutoritzacio);
	};
	public void setEstatAutoritzacio(java.lang.Long _estatAutoritzacio_) {
		this.estatAutoritzacio = _estatAutoritzacio_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.lang.String getConsulta() {
		return(consulta);
	};
	public void setConsulta(java.lang.String _consulta_) {
		this.consulta = _consulta_;
	};

	public java.lang.String getTitularNom() {
		return(titularNom);
	};
	public void setTitularNom(java.lang.String _titularNom_) {
		this.titularNom = _titularNom_;
	};

	public java.lang.String getTitularNif() {
		return(titularNif);
	};
	public void setTitularNif(java.lang.String _titularNif_) {
		this.titularNif = _titularNif_;
	};

	public java.sql.Timestamp getDataAutoritzacio() {
		return(dataAutoritzacio);
	};
	public void setDataAutoritzacio(java.sql.Timestamp _dataAutoritzacio_) {
		this.dataAutoritzacio = _dataAutoritzacio_;
	};

	public java.sql.Timestamp getDataEnviament() {
		return(dataEnviament);
	};
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_) {
		this.dataEnviament = _dataEnviament_;
	};

	public java.lang.Long getIntents() {
		return(intents);
	};
	public void setIntents(java.lang.Long _intents_) {
		this.intents = _intents_;
	};

	public java.sql.Timestamp getDataConsulta() {
		return(dataConsulta);
	};
	public void setDataConsulta(java.sql.Timestamp _dataConsulta_) {
		this.dataConsulta = _dataConsulta_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof InfoMadrid) {
            InfoMadrid __instance = (InfoMadrid)__obj;
            __result = true;
            __result = __result && (this.getInfoMadridID() == __instance.getInfoMadridID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:infomadridid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "infoMadrid")
    private Set<SolicitudJPA> solicituds = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicituds() {
    return this.solicituds;
  }

    public void setSolicituds(Set<SolicitudJPA> solicituds) {
      this.solicituds = solicituds;
    }



 // ---------------  STATIC METHODS ------------------
  public static InfoMadridJPA toJPA(InfoMadrid __bean) {
    if (__bean == null) { return null;}
    InfoMadridJPA __tmp = new InfoMadridJPA();
    __tmp.setInfoMadridID(__bean.getInfoMadridID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setEstatProcediment(__bean.getEstatProcediment());
    __tmp.setEstatAutoritzacio(__bean.getEstatAutoritzacio());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setConsulta(__bean.getConsulta());
    __tmp.setTitularNom(__bean.getTitularNom());
    __tmp.setTitularNif(__bean.getTitularNif());
    __tmp.setDataAutoritzacio(__bean.getDataAutoritzacio());
    __tmp.setDataEnviament(__bean.getDataEnviament());
    __tmp.setIntents(__bean.getIntents());
    __tmp.setDataConsulta(__bean.getDataConsulta());
		return __tmp;
	}


  public static InfoMadridJPA copyJPA(InfoMadridJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<InfoMadridJPA> copyJPA(java.util.Set<InfoMadridJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<InfoMadridJPA> __tmpSet = (java.util.Set<InfoMadridJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<InfoMadridJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (InfoMadridJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static InfoMadridJPA copyJPA(InfoMadridJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    InfoMadridJPA __tmp = (InfoMadridJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicituds())) ) {
      __tmp.setSolicituds(SolicitudJPA.copyJPA(__jpa.getSolicituds(), __alreadyCopied,"InfoMadridJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}

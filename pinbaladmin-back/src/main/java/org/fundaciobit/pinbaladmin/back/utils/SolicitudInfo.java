package org.fundaciobit.pinbaladmin.back.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author anadal
 *
 */
public class SolicitudInfo {

  protected String entitat;
  protected String expedientPID;

  protected final Map<String, ProcedimentInfo> procediments = new HashMap<String, ProcedimentInfo>();

  /**
   * @param entitat
   */
  public SolicitudInfo(String entitat) {
    super();
    this.entitat = entitat;
    this.expedientPID = null;
  }

  public String getEntitat() {
    return entitat;
  }

  public void setEntitat(String entitat) {
    this.entitat = entitat;
  }

  public String getExpedientPID() {
      return expedientPID;
    }

    public void setExpedientPID(String expedientPID) {
      this.expedientPID = expedientPID;
    }

  public Map<String, ProcedimentInfo> getProcediments() {
    return procediments;
  }


  public void addProcediment(ProcedimentInfo proc) {
    this.procediments.put(proc.getCodi(), proc);
  }

  public ProcedimentInfo getProcediment(String codi) {
    return procediments.get(codi);
  }

}

package org.fundaciobit.pinbaladmin.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
public class TipusProcediments {

  public static class TipusProcediment {
    final String castella;
    final String catala;

    /**
     * @param castella
     * @param catala
     */
    public TipusProcediment(String castella, String catala) {
      super();
      this.castella = castella;
      this.catala = catala;
    }

  }

  public static Map<String, TipusProcediment> castellaMap = new HashMap<String, TipusProcediment>();

  public static Map<String, TipusProcediment> catalaMap = new HashMap<String, TipusProcediment>();

  static {

    List<TipusProcediment> __tmp = new java.util.ArrayList<TipusProcediment>();
    __tmp.add(new TipusProcediment("Aduanero", "Duaner"));
    __tmp.add(new TipusProcediment("Afiliación y cotización a la Seguridad Social",
        " Afiliació i cotització a la Seguretat Social"));
    __tmp.add(new TipusProcediment("Autorizaciones, licencias y homologaciones",
        "Autoritzacions, llicències i homologacions"));
    __tmp.add(
        new TipusProcediment("Ayudas, Becas y Subvenciones", "Ajudes, Beques i Subvencions"));
    __tmp.add(new TipusProcediment("Certificados", "Certificats"));
    __tmp.add(new TipusProcediment("Contratación pública", "Contractació pública"));
    __tmp
        .add(new TipusProcediment("Convenios de colaboración y comunicaciones administrativas",
            "Convenis de col·laboració i comunicacions administratives"));
    __tmp.add(new TipusProcediment("Gestión Económica y Patrimonial",
        "Gestió Econòmica i Patrimonial"));
    __tmp.add(new TipusProcediment("Declaraciones y comunicaciones de los interesados",
        "Declaracions i comunicacions dels interessats"));
    __tmp.add(new TipusProcediment("Inspector", "Inspector"));
    __tmp.add(new TipusProcediment("Premios", "Premis"));
    __tmp.add(new TipusProcediment("Prestaciones", "Prestacions"));
    __tmp.add(new TipusProcediment("Recursos Humanos", "Recursos Humans"));
    __tmp.add(new TipusProcediment("Registros y Censos", "Registres i Censos"));
    __tmp.add(new TipusProcediment(
        "Responsabilidad patrimonial y otras solicitudes de indemnización",
        "Responsabilitat patrimonial i altres sol·licituds d'indemnització"));
    __tmp.add(new TipusProcediment("Revisión de Actos administrativos y Recursos",
        "Revisió d'Actes administratius i Recursos"));
    __tmp.add(new TipusProcediment("Sancionador", "Sancionador"));
    __tmp.add(new TipusProcediment("Sugerencias, Quejasy Denuncias",
        "Suggeriments, Queixes i Denúncies"));
    __tmp.add(new TipusProcediment("Tributario", "Tributari"));

    for (TipusProcediment tipusProcediment : __tmp) {
      castellaMap.put(tipusProcediment.castella, tipusProcediment);
      catalaMap.put(tipusProcediment.catala, tipusProcediment);
    }

  }

  /**
   * 
   * @param tprocCastCatala
   * @return
   */
  public static String getTipusProcedimentByLabel(String tprocCastCatala) {
    TipusProcediment tipusProcediment;
    tipusProcediment = castellaMap.get(tprocCastCatala);

    if (tipusProcediment != null) {
      return tipusProcediment.castella;
    }

    tipusProcediment = catalaMap.get(tprocCastCatala);

    if (tipusProcediment != null) {
      return tipusProcediment.castella;
    }

    return null;
  }

  /**
   * 
   * @return
   */
  public static Set<String> getAllTipusDeProcediment() {
    return castellaMap.keySet();
  }

}

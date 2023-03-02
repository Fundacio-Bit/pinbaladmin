package org.fundaciobit.pinbaladmin.commons.utils;

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
    
    
    public final long id;
    public final String castella;
    public final String catala;

    /**
     * @param castella
     * @param catala
     */
    public TipusProcediment(long id, String castella, String catala) {
      super();
      this.id = id;
      this.castella = castella;
      this.catala = catala;
    }

  }
  
  private static final List<TipusProcediment> tipusProcediments = new java.util.ArrayList<TipusProcediment>();

  private static final Map<String, TipusProcediment> castellaMap = new HashMap<String, TipusProcediment>();

  private static final Map<String, TipusProcediment> catalaMap = new HashMap<String, TipusProcediment>();

  static {

    
    tipusProcediments.add(new TipusProcediment(1, "Aduanero", "Duaner"));
    tipusProcediments.add(new TipusProcediment(2, "Afiliación y cotización a la Seguridad Social",
        " Afiliació i cotització a la Seguretat Social"));
    tipusProcediments.add(new TipusProcediment(3, "Autorizaciones, licencias y homologaciones",
        "Autoritzacions, llicències i homologacions"));
    tipusProcediments.add(
        new TipusProcediment(4, "Ayudas, Becas y Subvenciones", "Ajudes, Beques i Subvencions"));
    tipusProcediments.add(new TipusProcediment(5, "Certificados", "Certificats"));
    tipusProcediments.add(new TipusProcediment(6, "Contratación pública", "Contractació pública"));
    tipusProcediments
        .add(new TipusProcediment(7, "Convenios de colaboración y comunicaciones administrativas",
            "Convenis de col·laboració i comunicacions administratives"));
    tipusProcediments.add(new TipusProcediment(8, "Gestión Económica y Patrimonial",
        "Gestió Econòmica i Patrimonial"));
    tipusProcediments.add(new TipusProcediment(9, "Declaraciones y comunicaciones de los interesados",
        "Declaracions i comunicacions dels interessats"));
    tipusProcediments.add(new TipusProcediment(10, "Inspector", "Inspector"));
    tipusProcediments.add(new TipusProcediment(11, "Premios", "Premis"));
    tipusProcediments.add(new TipusProcediment(12, "Prestaciones", "Prestacions"));
    tipusProcediments.add(new TipusProcediment(13, "Recursos Humanos", "Recursos Humans"));
    tipusProcediments.add(new TipusProcediment(14, "Registros y Censos", "Registres i Censos"));
    tipusProcediments.add(new TipusProcediment(15, 
        "Responsabilidad patrimonial y otras solicitudes de indemnización",
        "Responsabilitat patrimonial i altres sol·licituds d'indemnització"));
    tipusProcediments.add(new TipusProcediment(16, "Revisión de Actos administrativos y Recursos",
        "Revisió d'Actes administratius i Recursos"));
    tipusProcediments.add(new TipusProcediment(17, "Sancionador", "Sancionador"));
    
    TipusProcediment queixes = new TipusProcediment(18, "Sugerencias, Quejas y Denuncias",
        "Suggeriments, Queixes i Denúncies");
    
    tipusProcediments.add(queixes);
    
    tipusProcediments.add(new TipusProcediment(19, "Tributario", "Tributari"));

    for (TipusProcediment tipusProcediment : tipusProcediments) {
      castellaMap.put(tipusProcediment.castella.toLowerCase(), tipusProcediment);
      catalaMap.put(tipusProcediment.catala.toLowerCase(), tipusProcediment);
    }
    
    castellaMap.put("Sugerencias, Quejas  y Denuncias".toLowerCase(), queixes);
    castellaMap.put("Sugerencias, Quejasy Denuncias".toLowerCase(), queixes);

  }
  
  
  public static List<TipusProcediment> getAllTipusProcediments() {
    return tipusProcediments;
  }
  

  /**
   * 
   * @param tprocCastCatala
   * @return
   */
  public static String getTipusProcedimentByLabel(String tprocCastCatala) {
    
    if (tprocCastCatala == null) {
      return null;
    }
    
    tprocCastCatala = tprocCastCatala.trim().toLowerCase();
    
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

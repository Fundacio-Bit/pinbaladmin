package org.fundaciobit.pinbaladmin.logic.utils;


import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pinbaladmin.ejb.FitxerLocal;
import org.fundaciobit.pinbaladmin.utils.Configuracio;
import org.fundaciobit.pinbaladmin.versio.Versio;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

  protected static Logger log = Logger.getLogger(LogicUtils.class);
  
  
  public static String getVersio() {
    return Versio.VERSIO + (Configuracio.isCAIB()?"-caib" : "");
  }

  
  public static void deleteFiles(Set<Long> files, FitxerLocal fitxerEjb) {
    for (Long _fitxerID_ : files) {
      fitxerEjb.delete(_fitxerID_);
    }
    FileSystemManager.eliminarArxius(files);
  }
  
}

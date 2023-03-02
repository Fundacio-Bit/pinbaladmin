package org.fundaciobit.pinbaladmin.logic.utils;

import java.util.Set;

//import java.util.Locale;

import org.apache.log4j.Logger;
//import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.StaticVersion;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

    protected static Logger log = Logger.getLogger(LogicUtils.class);

    public static String getVersio() {
        return StaticVersion.VERSION + (Configuracio.isCAIB() ? "-caib" : "");
    }

    public static void deleteFiles(Set<Long> files, FitxerService fitxerEjb) throws I18NException {
        for (Long _fitxerID_ : files) {
            fitxerEjb.delete(_fitxerID_);
        }
        FileSystemManager.eliminarArxius(files);
    }

}

package org.fundaciobit.pinbaladmin.logic.startup;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.utils.UsersWithPfiUserCache;
import org.jboss.logging.Logger;

/**
 * EJB Singleton que s'inicialitza automàticament quan el servidor JBoss/WildFly
 * arrenca (desplegament de l'aplicació) gràcies a l'anotació {@link Startup}.
 *
 * El mètode anotat amb {@link PostConstruct} s'executa un únic cop, just després
 * de crear la instància del Singleton durant l'arrencada.
 *
 * @author anadal
 * 24 ago 2026
 */
@Singleton
@Startup
public class StartupEJB {

    protected final Logger log = Logger.getLogger(getClass());

    /**
     * Mètode que s'executa automàticament a l'arrencada del servidor.
     * Aquí es pot posar la lògica d'inicialització (càrrega de caches,
     * comprovacions de configuració, etc.).
     */
    @PostConstruct
    // No volem transacció associada a la inicialització.
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void init() {
        try {
            log.info("=== StartupEJB: INICI de l'aplicació (arrencada del servidor) ===");

            // TODO: afegir aquí la lògica que s'ha d'executar a l'arrencada.
            executarTasquesInicials();

            log.info("=== StartupEJB: Inicialització finalitzada correctament ===");
        } catch (Throwable th) {
            // No propaguem l'excepció per no impedir el desplegament de l'aplicació.
            log.error("StartupEJB: Error durant la inicialització a l'arrencada: " + th.getMessage(), th);
        }
    }

    /**
     * Mètode que s'executa quan l'aplicació es desplega/atura (opcional).
     */
    @PreDestroy
    public void shutdown() {
        log.info("=== StartupEJB: ATURADA de l'aplicació ===");
    }

    /**
     * Lògica concreta a executar a l'arrencada.
     */
    private void executarTasquesInicials() {
        // Implementar aquí les tasques d'inicialització.
        
        
        // Aquest tasca tarda molt ... la ficam en un Thread ...
        new Thread(() -> {
            try {
                log.info("Iniciant la càrrega de Usuaris amb rol PFI_USER del UserInformation per emplenar la cache.");
                UsersWithPfiUserCache.getLlistaResponsables();
                log.info(
                        "Finalitzada la càrrega de Usuaris amb rol PFI_USER del UserInformation per emplenar la cache.");

            } catch (I18NException e) {

                String msg = I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));

                log.error("Error durant l'execució de les tasques inicials: " + msg, e);

            }
        }).start();
    }

}

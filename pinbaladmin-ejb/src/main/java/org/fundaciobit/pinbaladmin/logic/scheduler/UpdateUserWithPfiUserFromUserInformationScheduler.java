package org.fundaciobit.pinbaladmin.logic.scheduler;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fundaciobit.pinbaladmin.logic.utils.UsersWithPfiUserCache;
import org.fundaciobit.pluginsib.utils.ejb.AbstractScheduler;

/**
 * 
 * @author anadal (u80067)
 * 24 ago 2026 12:14:56
 */
@Singleton
@Startup
public class UpdateUserWithPfiUserFromUserInformationScheduler extends AbstractScheduler {

    @Override
    public String getSchedulerName() {
        return "Actualitzador de Cache d'Usuaris amb PFI_USER";
    }

    /**
     * 
     * @return Si val null significa que no s'ha d'executar
     */
    @Override
    public String getCronExpression() {
        // Valor per defecte = cada dia a les 5:00 
        // Seconds - Minutes - Hourly - Daily - Weekly - Monthly - Yearly
        String cron = null; // PropietatGlobalUtil.getBitacolaCleanerCronExpression();
        if (cron == null || cron.trim().length() == 0) {
            cron = "0 0 5 * * ? *";
        }
        return cron;
    }

    @Override
    public void executeTask(ControlOfExecution coe) {
        try {

            UsersWithPfiUserCache.getLlistaResponsables();

        } catch (Throwable e) {
            log.error("Error Actualitzant Cache d'Usuaris amb PFI_USER: " + e.getMessage(), e);
        }
    }

}

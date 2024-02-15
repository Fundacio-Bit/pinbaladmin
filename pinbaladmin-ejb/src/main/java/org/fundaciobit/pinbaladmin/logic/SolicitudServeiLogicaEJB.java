package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiEJB;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "SolicitudServeiLogicaEJB")
public class SolicitudServeiLogicaEJB extends SolicitudServeiEJB implements SolicitudServeiLogicaService {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampSolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.CampSolicitudService campSolicitudEjb;

    @Override
    @PermitAll
    public SolicitudServei create(SolicitudServei instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    public Set<Long> deleteFull(Long serveiId, Long solicitudId, boolean deleteFiles) throws I18NException {

        Set<Long> files = new HashSet<Long>();

        SolicitudServei ss;

        {
            List<SolicitudServei> list = this.select(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(solicitudId),
                    SolicitudServeiFields.SERVEIID.equal(serveiId)));
            if (list == null || list.isEmpty()) {
                return files;
            }
            ss = list.get(0);
        }

        campSolicitudEjb.delete(Where.AND(CampSolicitudFields.SOLICITUDSERVEIID.equal(ss.getId())));

        delete(ss);

        if (deleteFiles) {
            for (Long fitxerid : files) {
                FileSystemManager.eliminarArxiu(fitxerid);
            }
        }

        return files;
    }

}

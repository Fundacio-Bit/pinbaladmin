package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiEJB;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "SolicitudServeiLogicaEJB")
@SecurityDomain("seycon")
public class SolicitudServeiLogicaEJB extends SolicitudServeiEJB implements SolicitudServeiLogicaLocal {
  
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.CampSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.CampSolicitudLocal campSolicitudEjb;

 
  
  @Override
  public Set<Long> deleteFull(Long serveiId, Long solicitudId, boolean deleteFiles)
      throws I18NException {

    Set<Long> files = new HashSet<Long>();
    
    SolicitudServei ss;

    {
      List<SolicitudServei> list = this.select( Where.AND(
          SolicitudServeiFields.SOLICITUDID.equal(solicitudId),
          SolicitudServeiFields.SERVEIID.equal(serveiId)
          ));
      if (list == null || list.isEmpty()) {
        return files;
      }
      ss = list.get(0);
    }
    
   
    campSolicitudEjb.delete(
        Where.AND(
            CampSolicitudFields.SOLICITUDSERVEIID.equal(ss.getId())
            ));
    
    delete(ss);
    
    
    if (deleteFiles) {
      for (Long fitxerid : files) {
        FileSystemManager.eliminarArxiu(fitxerid);
      }
    }

    return files;
  }

}

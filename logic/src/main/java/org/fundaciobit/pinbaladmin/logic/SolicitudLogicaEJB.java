package org.fundaciobit.pinbaladmin.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * @author areus
 */
@Stateless(name = "SolicitudLogicaEJB")
@SecurityDomain("seycon")
public class SolicitudLogicaEJB extends SolicitudEJB implements SolicitudLogicaLocal {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FitxerLocal fitxerEjb;
  
  @EJB(mappedName = DocumentSolicitudLogicaLocal.JNDI_NAME)
  protected DocumentSolicitudLogicaLocal documentSolicitudLogicaLocal;
  
  @EJB(mappedName = SolicitudServeiLogicaLocal.JNDI_NAME)
  protected SolicitudServeiLogicaLocal solicitudServeiLogicaEJB;

  @Override
  public Map<Long, List<SolicitudDTO>> getSolicitudsByServei(Collection<Long> serveiIds) {

    if (serveiIds.isEmpty()) {
      return Collections.emptyMap();
    }

    Query query = __em.createQuery("select " +
              "s.serveiID, " +
              "solser.solicitudID, " +
              "solser.solicitud.procedimentCodi,  " +
              "solser.solicitud.procedimentNom,  " +
              "solser.solicitud.departamentID " +
            "from ServeiJPA s join s.solicitudServeis solser " +
            "where s.serveiID in (:serveiIds) " +
            "order by s.serveiID, solser.solicitud.dataInici DESC");
    query.setParameter("serveiIds", serveiIds);
    List<Object[]> resultList = (List<Object[]>) query.getResultList();

    Map<Long, List<SolicitudDTO>> resultMap = new HashMap<Long, List<SolicitudDTO>>();
    for (Long serveiId : serveiIds) {
      resultMap.put(serveiId, new ArrayList<SolicitudDTO>());
    }

    for (Object[] result : resultList) {
      Long serveiId = (Long) result[0];
      SolicitudDTO solicitudDTO =
              new SolicitudDTO((Long)result[1], (String)result[2], (String)result[3], (Long)result[4]);
      resultMap.get(serveiId).add(solicitudDTO);
    }

    return resultMap;
  }
  
  @Override
  public Set<Long> deleteFull(Long solicitudId, boolean deleteFiles)
      throws I18NException {

    Set<Long> files = new HashSet<Long>();

    SolicitudJPA solicitud = this.findByPrimaryKey(solicitudId);
    
    if (solicitud == null) {
      return files;
    }
    
    // Borram Solicituds Serveis
    {
      List<Long> list = solicitudServeiLogicaEJB.executeQuery(SolicitudServeiFields.SERVEIID.select,
          SolicitudServeiFields.SOLICITUDID.equal(solicitudId));
      
      for (Long ss : list) {
        files.addAll(solicitudServeiLogicaEJB.deleteFull(ss, solicitudId, false));
      }
      
    }
    
    // Borram Documents de Solicitud
    {
      List<Long> documentsIds = documentSolicitudLogicaLocal.executeQuery(
          DocumentSolicitudFields.DOCUMENTID.select,
            DocumentSolicitudFields.SOLICITUDID.equal(solicitudId)
            );
      
      for (Long ds : documentsIds) {
        files.addAll(documentSolicitudLogicaLocal.deleteFull(ds, solicitudId, false));
      }
    
    }
    
    // Borram solicitud
    if (solicitud.getDocumentSolicitudID() != null) {
      files.add(solicitud.getDocumentSolicitudID());
    }
    
    this.delete(solicitud);

    // Si to ha anat be llavors borram els fitxers
    if (deleteFiles) {
      LogicUtils.deleteFiles(files, fitxerEjb);
    }

    return files;
  }
  
  @Override
  @PermitAll
  public SolicitudJPA findByPrimaryKey(Long _ID_) {
     return super.findByPrimaryKey(_ID_);
  }

}
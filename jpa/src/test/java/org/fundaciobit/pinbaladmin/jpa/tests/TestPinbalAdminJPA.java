package org.fundaciobit.pinbaladmin.jpa.tests;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.jpa.PinbalAdminJPADaoManagers;
import org.fundaciobit.pinbaladmin.model.PinbalAdminDaoManager;
import org.junit.Test;


/**
 * 
 * @author anadal
 *
 */
public class TestPinbalAdminJPA {

  public static final Logger log = Logger.getLogger(TestPinbalAdminJPA.class);
  
  
  @Test
  public void main() {
    try {
      log.info(">>>>>>>>>>>>  Hello World!");
      
      // USING GENAPP
      // ============
      
      Properties prop = new Properties();

//    
//    prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
//    prop.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
//    prop.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.35.151:3306/portasib");
//    prop.put("javax.persistence.jdbc.user","portasib");
//    prop.put("javax.persistence.jdbc.password","portasib");
//    

      prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
      prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
      // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/pinbaladmin");
      prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/pinbaladmin");
      prop.put("javax.persistence.jdbc.user", "pinbaladmin");
      prop.put("javax.persistence.jdbc.password", "pinbaladmin");
      

      prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
      // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/pinbaladmin");
      prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/pinbaladmin");
      prop.put("hibernate.connection.username", "pinbaladmin");
      prop.put("hibernate.connection.password", "pinbaladmin");
      
      prop.put("hibernate.show_sql", "true");
      prop.put("hibernate.format_sql", "true");


      
      
      

      EntityManagerFactory emf;

      // Veure persistence.xml
      emf = Persistence.createEntityManagerFactory("pinbaladminDBStandalone", prop);
      
      
      
      EntityManager em = emf.createEntityManager();  
      
      em.setFlushMode(FlushModeType.AUTO);
      
      EntityTransaction tx = em.getTransaction();

      tx.begin();
      
      boolean f = false;
      if (f) {
        throw new I18NException("werrrtt");
      }
      
      PinbalAdminDaoManager.setDaoManagers(new PinbalAdminJPADaoManagers(em)); // firmesDaoManagers

      testServeisSolicituds(em);

      
      tx.commit();
      log.info("<<<<<<<<<<<  Good Bye!");
      
      

    } catch (I18NException e) {
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
  
  
  @SuppressWarnings("unused")
  private void testServeisSolicituds(EntityManager em) {
    
    
    Query query = em.createQuery("select s.serveiID, " +
            "solser.solicitudID, " +
            "solser.solicitud.procedimentCodi,  " +
            "solser.solicitud.procedimentNom,  " +
            "solser.solicitud.departamentID " +
            "from ServeiJPA s join s.solicitudServeis solser " +
            "where s.serveiID in (13, 25, 63, 64) " +
            "order by s.serveiID ");
    List<Object[]> resultList = (List<Object[]>) query.getResultList();

    for (Object[] result: resultList) {
      System.out.println("ServeiID " + result[0]);
      System.out.println("SolicitudID " + result[1]);
    }
  }
}

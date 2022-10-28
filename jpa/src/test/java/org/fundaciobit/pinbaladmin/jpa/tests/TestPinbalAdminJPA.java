package org.fundaciobit.pinbaladmin.jpa.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.jpa.PinbalAdminJPADaoManagers;
import org.fundaciobit.pinbaladmin.model.PinbalAdminDaoManager;
import org.fundaciobit.pinbaladmin.model.dao.IEntitatManager;
import org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager;
import org.fundaciobit.pinbaladmin.model.dao.IServeiManager;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.GrupEntitatCedentFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
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
      // prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
      // prop.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
      // prop.put("javax.persistence.jdbc.url",
      // "jdbc:mysql://192.168.35.151:3306/portasib");
      // prop.put("javax.persistence.jdbc.user","portasib");
      // prop.put("javax.persistence.jdbc.password","portasib");
      //

      // String url = "jdbc:postgresql://localhost:5432/pinbaladmin";
      String url = "jdbc:postgresql://192.168.35.155:5432/pinbaladmin";
      String usr = "pinbaladmin";
      String pwd = "pinbaladmin";

      prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
      prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
      prop.put("javax.persistence.jdbc.url", url);
      prop.put("javax.persistence.jdbc.user", usr);
      prop.put("javax.persistence.jdbc.password", pwd);

      prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
      prop.put("hibernate.connection.url", url);
      prop.put("hibernate.connection.username", usr);
      prop.put("hibernate.connection.password", pwd);

      prop.put("hibernate.show_sql", "true");
      prop.put("hibernate.format_sql", "true");

      EntityManagerFactory emf;

      // Veure persistence.xml
      emf = Persistence.createEntityManagerFactory("pinbaladminDBStandalone", prop);

      EntityManager em = emf.createEntityManager();

      em.setFlushMode(FlushModeType.AUTO);

      PinbalAdminDaoManager.setDaoManagers(new PinbalAdminJPADaoManagers(em));

      EntityTransaction tx = em.getTransaction();

      tx.begin();
      /*
       * boolean f = false; if (f) { throw new I18NException("werrrtt"); }
       * 
       * // firmesDaoManagers
       */

      testServeisPerNif(em);

      // testServeisSolicituds(em);

      tx.commit();
      log.info("<<<<<<<<<<<  Good Bye!");

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  private void testServeisPerNif(EntityManager em) {
    // CIF CAIB =>
    //String cif = "S0711001H";
    
    // IMAS
    String cif = "Q0700448D";

System.out.println(" CIF = > " + cif) ;
    try {

      IEntitatManager entitatLocalEjb = PinbalAdminDaoManager.getDaoManagers()
          .getEntitatManager();

      IGrupEntitatCedentManager grupEntitatCedentEjb = PinbalAdminDaoManager.getDaoManagers()
          .getGrupEntitatCedentManager();

      IServeiManager serveiEjb = PinbalAdminDaoManager.getDaoManagers().getServeiManager();

      log.info(" Rebut CIF = ]" + cif + "[");

      Long entitatID = entitatLocalEjb.executeQueryOne(EntitatFields.ENTITATID,
          EntitatFields.CIF.equal(cif));

      log.info(" entitatID = ]" + entitatID + "[");

      if (entitatID == null) {
        throw new Exception("No existeix cap entitat amb el CIF " + cif);
      }

      // Entitat entitat = entitatLocalEjb.findByPrimaryKey(entitatID);
      // long grupEntitat = entitat.getGrupEntitatID();

      long grupEntitat = entitatLocalEjb.executeQueryOne(EntitatFields.GRUPENTITATID,
          EntitatFields.ENTITATID.equal(entitatID));

      List<Long> cedentsAExcloure = grupEntitatCedentEjb.executeQuery(
          GrupEntitatCedentFields.CEDENTID,
          GrupEntitatCedentFields.GRUPENTITATID.equal(grupEntitat));

      // CEDENT == EntitatServei
      final Where w;
      if (cedentsAExcloure == null || cedentsAExcloure.size() == 0) {
        log.info("NO hi ha CEDENTS a Excloure");
        w = Where.AND(
            // 20 == Estat Producció
            ServeiFields.ESTATSERVEIID.equal(20L), ServeiFields.OCULT.equal(false));
      } else {
        Long[] cedentsIDs = cedentsAExcloure.toArray(new Long[0]);
        log.info("S'han d'excloure els següents cendents: " + Arrays.toString(cedentsIDs));

        w = Where.AND(ServeiFields.ESTATSERVEIID.equal(20L), ServeiFields.OCULT.equal(false),
            ServeiFields.ENTITATSERVEIID.notIn(cedentsIDs));
      }

      List<Servei> list = serveiEjb.select(w, new OrderBy(ServeiFields.NOM));

      for (Servei servei : list) {
        System.out.println("codi => " + String.valueOf(servei.getCodi()) + " | nom => ("
            + servei.getCodi() + ") " + servei.getNom());

      }

    } catch (I18NException ex) {

      String msg = I18NCommonUtils.getMessage(ex, new Locale("ca"));

      log.error(msg, ex);

    } catch (java.lang.Exception ex) {

      log.error(ex.getMessage(), ex);

    }

  }

  @SuppressWarnings("unused")
  private void testServeisSolicituds(EntityManager em) {

    Query query = em.createQuery(
        "select s.serveiID, " + "solser.solicitudID, " + "solser.solicitud.procedimentCodi,  "
            + "solser.solicitud.procedimentNom,  " + "solser.solicitud.departamentID "
            + "from ServeiJPA s join s.solicitudServeis solser "
            + "where s.serveiID in (13, 25, 63, 64) " + "order by s.serveiID ");
    List<Object[]> resultList = (List<Object[]>) query.getResultList();

    for (Object[] result : resultList) {
      System.out.println("ServeiID " + result[0]);
      System.out.println("SolicitudID " + result[1]);
    }
  }
}

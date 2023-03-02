package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class ServeiActualitzarOperadorController {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

  @RequestMapping(value = "/operador/actualitzarserveis", method = RequestMethod.GET)
  public ModelAndView actualitzarServeisGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    return new ModelAndView("actualitzarserveis");

  }

  @RequestMapping(value = "/operador/actualitzarserveis", method = RequestMethod.POST)
  public String actualitzarServeisPost(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    String serveis = request.getParameter("serveis");

    if (serveis == null || serveis.trim().length() == 0) {
      HtmlUtils.saveMessageError(request, "El camp Serveis esta buit");
      return "actualitzarserveis";
    }

    // CsvTransfer csvTransfer = new CsvTransfer();
    // ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
    // ms.setType(clazz);

    Reader reader = new StringReader(serveis);
    CsvToBean<NouServei> cb = new CsvToBeanBuilder<NouServei>(reader).withType(NouServei.class)
        .withSeparator('\t')
        // .withMappingStrategy(ms)
        .build();

    List<NouServei> serveisList = cb.parse();
    try {
      reader.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    int noCedent = 0;

    for (NouServei nouServei : serveisList) {

      String cedentNom = nouServei.getCedent();

      Long id = entitatServeiEjb.executeQueryOne(EntitatServeiFields.ENTITATSERVEIID,
          EntitatServeiFields.NOM.equal(cedentNom));

      if (id == null) {
        HtmlUtils.saveMessageError(request,
            "El cedent amb nom ]" + cedentNom + "[ no existeix. L'ha de crear.");
        noCedent++;
      }

    }

    if (noCedent != 0) {

      HtmlUtils.saveMessageInfo(request, "SENSE CEDENT : " + noCedent);

      return "actualitzarserveis";
    }

    int actualitzatServei = 0;
    int creatServei = 0;

    for (NouServei nouServei : serveisList) {

      String cedentNom = nouServei.getCedent();

      Long id = entitatServeiEjb.executeQueryOne(EntitatServeiFields.ENTITATSERVEIID,
          EntitatServeiFields.NOM.equal(cedentNom));

      if (id != null) {

        // EntitatServei cedent = entitatServeiEjb.findByPrimaryKey(id);
        Long serveiID = serveiEjb.executeQueryOne(ServeiFields.SERVEIID,
            ServeiFields.CODI.equal(nouServei.getCodi()));

        if (serveiID == null) {

          int tipusConsentiment = 0;
          Long form = null;
          serveiEjb.create(nouServei.getCodi(), nouServei.getNom(), nouServei.getDescripcio(),
              form, id, 20L /* produccio */ , tipusConsentiment, false);

          creatServei++;
        } else {

          ServeiJPA s = serveiEjb.findByPrimaryKey(serveiID);

          s.setNom(nouServei.getNom());
          s.setDescripcio(nouServei.getDescripcio());

          serveiEjb.update(s);

          actualitzatServei++;
        }

      }

    }

    HtmlUtils.saveMessageSuccess(request,
        "CREAT SERVEI: " + creatServei + " | ACTUALITZAT SERVEI: " + actualitzatServei);

    return "actualitzarserveis";

  }

  public static class NouServei {
    @CsvBindByPosition(position = 0)
    private String cedent;

    @CsvBindByPosition(position = 1)
    private String nom;

    @CsvBindByPosition(position = 2)
    private String descripcio;

    @CsvBindByPosition(position = 3)
    private String codi;

    public String getCedent() {
      return cedent;
    }

    public void setCedent(String cedent) {
      this.cedent = cedent;
    }

    public String getNom() {
      return nom;
    }

    public void setNom(String nom) {
      this.nom = nom;
    }

    public String getDescripcio() {
      return descripcio;
    }

    public void setDescripcio(String descripcio) {
      this.descripcio = descripcio;
    }

    public String getCodi() {
      return codi;
    }

    public void setCodi(String codi) {
      this.codi = codi;
    }

  }

}

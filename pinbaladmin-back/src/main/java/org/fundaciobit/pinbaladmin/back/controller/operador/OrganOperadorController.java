package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.OrganController;
import org.fundaciobit.pinbaladmin.back.form.webdb.OrganFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.OrganForm;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/organ")
@SessionAttributes(types = { OrganForm.class, OrganFilterForm.class })
public class OrganOperadorController extends OrganController {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;
    
    @Override
    public String getTileForm() {
        return "organFormOperador";
    }

    @Override
    public String getTileList() {
        return "organListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "organOperador_FilterForm";
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public OrganFilterForm getOrganFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        OrganFilterForm organFilterForm = super.getOrganFilterForm(pagina, mav, request);

        if (organFilterForm.isNou()) {
            organFilterForm.addHiddenField(ORGANID);
            organFilterForm.addHiddenField(ENTITATID);
            
            organFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_RELOAD, "actualitzar.dir3",
                    getContextWeb() + "/updateDir3", AdditionalButtonStyle.PRIMARY));

            organFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-code-branch fa-rotate-90",
                    "mostrar.jerarquia", "javascript:mostrarJerarquia({0})", AdditionalButtonStyle.INFO));

            organFilterForm.setAttachedAdditionalJspCode(true);
            organFilterForm.setVisibleFilterBy(true);

        }
        return organFilterForm;
    }

    @RequestMapping(value = "/updateDir3", method = RequestMethod.GET)
    public String actualitzarDir3(HttpServletRequest request, HttpServletResponse response) {
        log.info("Actualitzarem els dir 3 de tots");

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/mostrarJerarquia/{organid}", method = RequestMethod.GET)
    public void mostrarJerarquia(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("organid") Long organid) throws I18NException, IOException {
        
        log.info("Aqui s'ha de veure la jerarquia de " + organid);

        Organ aux = organEjb.findByPrimaryKey(organid);
        List<String> jerarquia = new ArrayList<String>();

        jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());

        while (aux.getCif() == null && aux.getDir3pare() != null) {
            List<Organ> listAux = organEjb.select(OrganFields.DIR3.equal(aux.getDir3pare()));
            aux = listAux.get(0);
//            log.info("pare: " + "(" + aux.getDir3() + ") " + aux.getNom());
            jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());
        }
        String str = String.join("", jerarquia);
        
        response.getWriter().write(str);
        response.getWriter().flush();
        response.getWriter().close();
    }

}

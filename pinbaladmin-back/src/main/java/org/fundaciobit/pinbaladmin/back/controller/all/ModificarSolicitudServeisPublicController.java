package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudsServeiOnlyContentOperadorControlador;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = ModificarSolicitudServeisPublicController.CONTEXT)
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class ModificarSolicitudServeisPublicController extends SolicitudsServeiOnlyContentOperadorControlador {

	public static final String CONTEXT = "/public/modificarsolicitudservei";
	
    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileList() {
        return "solicitudServeiListWebDB_onlycontent_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudServeiWebDB_FilterForm_OnlyContent_Operador";
    }
    
    
    @Override
    public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        SolicitudServeiFilterForm solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(pagina, mav, request);

        if (solicitudServeiFilterForm.isNou()) {
            solicitudServeiFilterForm.setItemsPerPage(-1);
            solicitudServeiFilterForm.addHiddenField(ARTICLES);
            solicitudServeiFilterForm.addHiddenField(ENLLAZNORMALEGAL);

            //XXX CONSENT: Esborrar camps
            solicitudServeiFilterForm.addHiddenField(CONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(ENLLAZCONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(TIPUSCONSENTIMENT);

            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.ID);
            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.SERVEIID);

            AdditionalField<Long, String> SolSerIDField = new AdditionalField<Long, String>();
            SolSerIDField.setCodeName("=ID");
            SolSerIDField.setPosition(SOLSERID);
            SolSerIDField.setValueMap(new HashMap<Long, String>());
            SolSerIDField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(SolSerIDField);

            AdditionalField<Long, String> codiServeiField = new AdditionalField<Long, String>();
            codiServeiField.setCodeName("=Codi Servei");
            codiServeiField.setPosition(CODISERVEI);
            codiServeiField.setValueMap(new HashMap<Long, String>());
            codiServeiField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(codiServeiField);

            AdditionalField<Long, String> nomServeiField = new AdditionalField<Long, String>();
            nomServeiField.setCodeName("=Nom Servei");
            nomServeiField.setPosition(NOMSERVEI);
            nomServeiField.setValueMap(new HashMap<Long, String>());
            nomServeiField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(nomServeiField);

            
            //Canvis tramit sistra:
           // solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.NOTES);
            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.FECHACADUCA);
            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.CADUCA);
        }

        return solicitudServeiFilterForm;
    }

}

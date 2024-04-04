package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.back.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitAPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAPublicController extends TramitAOperadorController {

    public static final String CONTEXT_WEB = "/public/tramita";
    public static final String RETURN_URL = "/public/tramita/list";
    public static final String RETURN_TO_SISTRA = RETURN_URL;
    public static final String CONTEXT_WEB_NEXT = TramitBPublicController.CONTEXT_WEB;

    @Override
    public boolean isPublic() {
        return true;
    }
    
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    @Override
    public String getTileForm() {
        return "tramitAFormPublic";
    }

    @Override
    public String getTileList() {
        return "tramitSistraListPublic";
    }

    @Override
    public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitAPersAutForm tramitForm = super.getTramitAPersAutForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);
        tramitForm.addHiddenField(URLSISTRA);
        tramitForm.addHiddenField(IDSESIONFORMULARIO);
        tramitForm.addHiddenField(DATATRAMIT);

        TramitAPersAutJPA tramitA = tramitForm.getTramitAPersAut();
        
        if (tramitForm.isNou()) {
            tramitA.setNif("45186147W");
            tramitA.setNom("Pau");
            tramitA.setTelefon("971123132");
            tramitA.setMail("mail@fbit.org");
            tramitA.setLlinatge1("Trias");
            tramitA.setLlinatge2("Segura");
            tramitA.setDatatramit(new Timestamp(System.currentTimeMillis()));
        }else {
            tramitA.setTelefon("telf");
            tramitA.setMail("mail@fbit.org");
        }
        
        return tramitForm;
    }

    @RequestMapping(value = "/finalitzarTramit/{uuid}", method = RequestMethod.GET)
    public ModelAndView getRedirectToFinishTramit(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("uuid") java.lang.String uuid) {

        try {
        	Long tramitID = HibernateFileUtil.decryptFileID(uuid);
            crearSolicitudAmbTramitID(tramitID);
        } catch (Exception e) {
            log.error(e);
            HtmlUtils.saveMessageError(request, "Error creant incidencia amb tramit Sistra: " + e.getMessage());
        }
        
        return returnToSistraPost(uuid);
    }

	private ModelAndView returnToSistraPost(String uuid) {
		
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);
        TramitAPersAut tramitA = tramitAPersAutLogicEjb.findByPrimaryKey(tramitID);

        ModelAndView mav = new ModelAndView("returnToSistraPost");

        String urlCallbackSistra = tramitA.getUrlsistra(); // "https://se.caib.es/sistramitfront/asistente/retornoGestorFormularioExterno.html";
        String idSesionFormulario = tramitA.getIdsesionformulario();
        String ticketGFE = uuid;
        String ticket = idSesionFormulario + ":" + ticketGFE;
        
        mav.addObject("urlCallbackSistra", urlCallbackSistra);
        mav.addObject("ticket", ticket);
        
        log.info("URL Callback: " + urlCallbackSistra);
        log.info("ticket:" + ticket);
        
        return mav;
	}
    
    @RequestMapping(value = "/cancelarTramit/{uuid}", method = RequestMethod.GET)
    public ModelAndView getRedirectToCancelTramit(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("uuid") java.lang.String uuid) {
    	
        ModelAndView mav = returnToSistraPost(uuid);
        
		try {
			Long tramitID = HibernateFileUtil.decryptFileID(uuid);
			request.getSession().removeAttribute("tramitid");
			tramitAPersAutLogicEjb.deleteFull(tramitID);
		} catch (Exception e) {
			log.error(e);
			HtmlUtils.saveMessageError(request, "Error cancelant incidencia amb tramit Sistra: " + e.getMessage());
		}
        return mav;
    }
    
    public void crearSolicitudAmbTramitID(Long tramitID) throws Exception {
        log.info("Generador del fitxer XML amb tramitID=" + tramitID);
        SolicitudJPA soli = tramitAPersAutLogicEjb.crearSolicitudAmbTramit(tramitID);
        log.info("Solicitud Creada a BBDD: " + soli.getSolicitudID());
    }
}

package org.fundaciobit.pinbaladmin.back.controller.common;

import java.sql.Timestamp;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.all.EventIncidenciaTecnicaPublicController;
import org.fundaciobit.pinbaladmin.back.controller.operador.IncidenciaTecnicaOperadorController;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventIncidenciaTecnicaController extends AbstractEventController<IncidenciaTecnica> {

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @Override
    public boolean isSolicitud() {
        return false;
    }

    @Override
    public String redirectWhenSessionItemIDNotDefined() {
        return "/operador/incidencia/list";
    }

    @Override
    public Timestamp getDataCreacio(IncidenciaTecnica item) {
        return item.getDataInici();
    }

    @Override
    public String getOperador(IncidenciaTecnica item) {
        return item.getOperador();
    }

    @Override
    public String getCreador(IncidenciaTecnica item) {
        return item.getCreador();
    }

    @Override
    public String getPersonaContacteNom(IncidenciaTecnica item) {
        return item.getContacteNom();
    }

    @Override
    public String getPersonaContacteEmail(IncidenciaTecnica item) {
        return item.getContacteEmail();
    }

    @Override
    public Long getItemID(IncidenciaTecnica item) {
        return item.getIncidenciaTecnicaID();
    }

    @Override
    public String getUrlToEditItem(IncidenciaTecnica item) {
        return IncidenciaTecnicaOperadorController.WEBCONTEXT + "/" + item.getIncidenciaTecnicaID() + "/edit";
    }

    @Override
    public String getUrlToCloseItem(IncidenciaTecnica item) {
        return IncidenciaTecnicaOperadorController.WEBCONTEXT + "/close/" + item.getIncidenciaTecnicaID();
    }

    @Override
    public String getUrlToChangeOperadorItem(IncidenciaTecnica item) {
        return IncidenciaTecnicaOperadorController.WEBCONTEXT + "/changeOperador/" + item.getIncidenciaTecnicaID();
    }

    @Override
    public boolean isClosed(IncidenciaTecnica item) {
        return item.getEstat() == Constants.ESTAT_INCIDENCIA_TANCADA;
    }

    @Override
    public IncidenciaTecnica findItemByPrimaryKey(Long itemID) {
        return incidenciaTecnicaLogicaEjb.findByPrimaryKey(itemID);
    }

    @Override
    public String getTitol(IncidenciaTecnica item) {
        return item.getTitol();
    }

    @Override
    public String getPersonaContacteEmailByItemID(Long itemID) throws I18NException {
        return incidenciaTecnicaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.CONTACTEEMAIL,
                IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(itemID));
    }

    @Override
    public String getPersonaContacteByItemID(Long itemID) throws I18NException {
        return incidenciaTecnicaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.CONTACTENOM,
                IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(itemID));
    }

    @Override
    public String getPublicContextPath() {
        return EventIncidenciaTecnicaPublicController.CONTEXT_PATH;
    }

    @Override
    public String getEstat(IncidenciaTecnica item) throws I18NException {

        switch (item.getEstat()) {
            case ESTAT_INCIDENCIA_OBERTA:
                return "Oberta";
            case ESTAT_INCIDENCIA_TANCADA:
                return "Tancada";
            case ESTAT_INCIDENCIA_PENDENT_DE_TERCER:
                return "Pendent de Tercer";

        }
        return "Estat desconegut " + item.getEstat();
    }

}

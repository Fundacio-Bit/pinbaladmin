package org.fundaciobit.pinbaladmin.api.externa.secure.tramitsistra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.I18NLogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;

@Path("/secure/TramitSistraService/v1")
@OpenAPIDefinition(tags = { @Tag(name = TramitSistraService.TAG, description = "TramitSistraService"), })
@SecurityScheme(type = SecuritySchemeType.HTTP, name = TramitSistraService.SEC, scheme = "basic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TramitSistraService {

    protected static final String TAG = "TramitSistraService";

    protected static final String SEC = "BasicAuth";

    protected static final Logger log = Logger.getLogger(TramitSistraService.class);

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAEjb;
    
    @Path("/formulario")
    @RolesAllowed({ Constants.PAD_WS })
    @SecurityRequirement(name = SEC)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getUrlFormulari", summary = "Retorna la URL per iniciar el tramit", tags = { TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(responseCode = "401", description = "No Autenticat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Error no controlat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }) })

    public String getUrlFormulari(
            @RequestBody(description = "Dades de la petició", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(name = "requestTransaction", required = true, implementation = TramitSistraPojo.class))) TramitSistraPojo parametrosFormulario,
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        final String methodName = "getUrlFormulari";
        final String language = parametrosFormulario.getIdioma();

        try {
            Timestamp datatramit = new Timestamp(System.currentTimeMillis());

            Usuario u =parametrosFormulario.getUsuario();

            String nif = u.getNif();
            String nom = u.getNombre();
            String llinatge1 = u.getApellido1();
            String llinatge2 = u.getApellido2();

            String mail = "mail@test.com";
            String telefon = "telf";
            
            TramitAPersAutJPA tramitA = new TramitAPersAutJPA(); 
            
            tramitA.setNif(nif);
            tramitA.setNom(nom);
            tramitA.setLlinatge1(llinatge1);
            tramitA.setLlinatge2(llinatge2);
            
            tramitA.setMail(mail);
            tramitA.setTelefon(telefon);
            
            tramitA.setDatatramit(datatramit);
            
            
            tramitAEjb.create(tramitA);
            
            String uuid =  HibernateFileUtil.encryptFileID(tramitA.getTramitid());
            
            //Amb les dades d'entrada, crear un TramitA, obtenir el tramitID, i retornar la URL amb el tramitB i parametre TramitID.
            
            //Crear un métode per saber si el TramitB que es vol crear ja existeix o no.
            
            //Cada vegada que es faci una cridada a aquest métode rest, tornarà una URL distinta perque es crea un TramitA i tramitID nous.
            
            //Mostrar durante el tramite la información de la persona autenticada, que estará en TramitA
            
            //Cuando acabe el tramite, crear solicitud i devolver control a sistra. Luego sistra llamará al método /resultado para obtener los datos que necesita.
            
            
            //S'hauria de canviar el tramitid de un Long a un uuid. Per a que ningú pugui modificar la URL. perque a la url, si canvies el tramitid, pots accedir a altres tramits, creats o no.
            //Així nomes el que te la url original pot fer canvis al tramit.
            
            //I si es perd el tramit o es deixa a mitges, serà recuperamble amb la URL amb  uuid.
            
            String toReturn = "http://ptrias:8080/pinbaladmin/operador/tramitb/new?tramitid=" + uuid;
            Gson gson = new GsonBuilder().create();

            return gson.toJson(toReturn);
        } catch (Throwable th) {
            return processException(methodName, language, th);
        }

    }

    @Path("/resultado")
    @RolesAllowed({ Constants.PAD_WS })
    @SecurityRequirement(name = SEC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getDatosFormularioFromTicket", summary = "Retorna les dades del formulari", tags = {
            TAG })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operació realitzada correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Resultado.class))),
            @ApiResponse(responseCode = "400", description = "Paràmetres incorrectes", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(responseCode = "401", description = "No Autenticat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "No Autoritzat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "500", description = "Error no controlat", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(responseCode = "510", description = "Només s'utilitza per crear fitxer de constants...", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Resultado.class)) }) })

    public Resultado getDatosFormularioFromTicket(
            @Parameter(description = "Ticket del fomulario", required = true, example = "CFDEKWNL-UHMZR8T8-T8WTFOOR:MhufBxRLLomvdOjC9nZhPA==", schema = @Schema(implementation = String.class)) @QueryParam("ticket") String ticket)
            throws RestException {

        final String methodName = "getDatosFormularioFromTicket";
        final String language = "ca";

        try {

            String[] splitTicket = ticket.split(":");

            String idSesionFormulario = splitTicket[0];
            String ticketGFE = splitTicket[1];//tramitid

            Solicitud soli = solicitudLogicaEjb.getSolicitudFromTramitID(ticketGFE);

            // Si no troba cap solicitud, s'hauria de veure si el tramit està a mitges i indicar cancelado = true, o veure que fer
                        
            
            String id = idSesionFormulario;
            String pdf = fileToBase64(soli.getDocumentSolicitudID());
            String xml = fileToBase64(soli.getSolicitudXmlID());
            Boolean cancelado = false;

            Resultado resultado = new Resultado();
            resultado.setCancelado(cancelado);
            resultado.setIdSesionFormulario(id);
            resultado.setPdf(pdf);
            resultado.setXml(xml);

            return resultado;

        } catch (Throwable th) {
            return processException(methodName, language, th);
        }

    }

    protected <T> T processException(final String methodName, String language, Throwable th) throws RestException {
        RestException oae;
        String msg;
        if (th instanceof RestException) {
            oae = (RestException) th;
            msg = th.getMessage();
        } else {
            if (th instanceof I18NException) {
                msg = I18NLogicUtils.getMessage((I18NException) th, new Locale(language));
            } else {
                msg = th.getMessage();
            }
            oae = new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }

        log.error("Error en " + methodName + ": " + msg, th);
        throw oae;
    }

    protected String fileToBase64(Long fileID) throws FileNotFoundException, IOException {
        byte[] solicitudXML = FileSystemManager.getFileContent(fileID);
        String base64 = Base64.getEncoder().encodeToString(solicitudXML);
        return base64;
    }
}

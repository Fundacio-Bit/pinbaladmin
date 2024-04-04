package org.fundaciobit.pinbaladmin.api.externa.secure.tramitsistra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.I18NLogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

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

			Usuario u = parametrosFormulario.getUsuario();

			String nif = u.getNif();
			String nom = u.getNombre();
			String llinatge1 = u.getApellido1();
			String llinatge2 = u.getApellido2();

			TramitAPersAutJPA tramitA = new TramitAPersAutJPA();

			tramitA.setNif(nif);
			tramitA.setNom(nom);
			tramitA.setLlinatge1(llinatge1);
			tramitA.setLlinatge2(llinatge2);

			log.info("CallBack URL: " + parametrosFormulario.getUrlCallback());
			request.getSession().setAttribute("urlCallbackSistra", parametrosFormulario.getUrlCallback());

			log.info("IdSesionFormulario: " + parametrosFormulario.getIdSesionFormulario());
			log.info("XML Actuales: " + parametrosFormulario.getXmlDatosActuales());

			String callbackUrl = parametrosFormulario.getUrlCallback();
			String idSesionFormulario = parametrosFormulario.getIdSesionFormulario();

			tramitA.setUrlsistra(callbackUrl);
			tramitA.setIdsesionformulario(idSesionFormulario);

			tramitA.setDatatramit(datatramit);

			tramitAEjb.create(tramitA);

			String uuid = HibernateFileUtil.encryptFileID(tramitA.getTramitid());

			// Amb les dades d'entrada, crear un TramitA, obtenir el tramitID, i retornar la
			// URL amb el tramitB i parametre TramitID.

			// Crear un métode per saber si el TramitB que es vol crear ja existeix o no.

			// Cada vegada que es faci una cridada a aquest métode rest, tornarà una URL
			// distinta perque es crea un TramitA i tramitID nous.

			// Mostrar durante el tramite la información de la persona autenticada, que
			// estará en TramitA

			// Cuando acabe el tramite, crear solicitud i devolver control a sistra. Luego
			// sistra llamará al método /resultado para obtener los datos que necesita.

			// S'hauria de canviar el tramitid de un Long a un uuid. Per a que ningú pugui
			// modificar la URL. perque a la url, si canvies el tramitid, pots accedir a
			// altres tramits, creats o no.
			// Així nomes el que te la url original pot fer canvis al tramit.

			// I si es perd el tramit o es deixa a mitges, serà recuperamble amb la URL amb
			// uuid.

			String toReturn = Configuracio.getUrlFormulariToSistra() + uuid;
			Gson gson = new GsonBuilder().create();

//            return gson.toJson(toReturn);
			return toReturn;
		} catch (Throwable th) {
			return processException(methodName, language, th);
		}

	}

    @Path("/resultado/{ticket}")
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
			@Parameter(in = ParameterIn.PATH, name = "ticket", description = "Ticket del fomulario", allowReserved = true, required = true, example = "CFDEKWNL-UHMZR8T8-T8WTFOOR:C9LgPcvVJMkv7a0DmkliUA==", schema = @Schema(implementation = String.class)) @PathParam("ticket") String ticket)
			throws RestException {

		final String methodName = "getDatosFormularioFromTicket";
		final String language = "ca";

		log.info("Metode REST: " + methodName);
		log.info("ticket: " + ticket);
		String[] splitTicket = ticket.split(":");

		String idSesionFormulario = splitTicket[0];
		String ticketGFE = splitTicket[1];// uuid

		log.info("ticketGFE: " + ticketGFE);

		String id = idSesionFormulario;
		Boolean cancelado = true;
		String pdf = "";
		String xml = "";

		// Si no troba cap solicitud, es perque no s'ha cancelat el tramit.
		List<Solicitud> llista = solicitudLogicaEjb.getSolicitudFromTramitID(ticketGFE);
		if (llista != null) {
			if (llista.size() == 1) {
				Solicitud soli = llista.get(0);

				try {
					pdf = fileToBase64(soli.getDocumentSolicitudID());
					xml = fileToBase64(soli.getSolicitudXmlID());

					cancelado = false;
				} catch (Exception e) {
					log.error("Error obtenint els fitxers de la solicitud: " + e.getMessage(), e);
				}
			} else {
				log.error("Error: S'han trobat " + llista.size() + " solicituds");
			}
		} else {
			log.error("Error obtenint llistat de solicituds amb uuid " + ticketGFE);
		}

		Resultado resultado = new Resultado();
		resultado.setCancelado(cancelado);
		resultado.setIdSesionFormulario(id);
		resultado.setPdf(pdf);
		resultado.setXml(xml);

		return resultado;
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
    
    /*
                  String XMLTest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                    + "<FORMULARIO xmlns=\"urn:es:caib:sistra2:xml:formulario:v1:model\" accion=\"submit\">\r\n"
                    + "    <CAMPO id=\"DATOS_REGISTRO\" tipo=\"compuesto\" xmlns=\"\">\r\n"
                    + "        <VALOR codigo=\"NIF\">45186147W</VALOR>\r\n"
                    + "        <VALOR codigo=\"EMAIL\">V4IZQZEY-1GW0CET8-T8NPS4ZE</VALOR>\r\n"
                    + "        <VALOR codigo=\"TELEFONO\">telf</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRE\">JUAN PABLO</VALOR>\r\n"
                    + "        <VALOR codigo=\"APELLIDO1\">TRIAS</VALOR>\r\n"
                    + "        <VALOR codigo=\"APELLIDO2\">SEGURA</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRECOMPLETO\">JUAN PABLO TRIAS SEGURA</VALOR>\r\n"
                    + "    </CAMPO>\r\n"
                    + "    <CAMPO id=\"DATOS_SOLICITUD\" tipo=\"compuesto\" xmlns=\"\">\r\n"
                    + "        <VALOR codigo=\"LDATIPOSOL\">Alta</VALOR>\r\n"
                    + "        <VALOR codigo=\"LDAENTORNO\">Pro</VALOR>\r\n"
                    + "        <VALOR codigo=\"DENOMINACION\">Ajuntament d'Andratx</VALOR>\r\n"
                    + "        <VALOR codigo=\"CIF\">P0700500B</VALOR>\r\n"
                    + "        <VALOR codigo=\"UNIDAD\">Dirección General de Primera Infancia, Innovación y Comunidad Educativa</VALOR>\r\n"
                    + "        <VALOR codigo=\"CODIUR\">A04026925</VALOR>\r\n"
                    + "        <VALOR codigo=\"CODIOA\">A04026923</VALOR>\r\n"
                    + "        <VALOR codigo=\"DIRECCION\">Carrer de la dirección</VALOR>\r\n"
                    + "        <VALOR codigo=\"CP\">07003</VALOR>\r\n"
                    + "        <VALOR codigo=\"MUNICIPIO\">Búger</VALOR>\r\n"
                    + "        <VALOR codigo=\"NIFSECD\">11111111A</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRESECD\">Toni</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE1SECD\">Mesquida</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE2SECD\">Mestre</VALOR>\r\n"
                    + "        <VALOR codigo=\"CARGOSECD\">Gestio: Profesor de Gestió</VALOR>\r\n"
                    + "        <VALOR codigo=\"TELEFONOSECD\">971456789</VALOR>\r\n"
                    + "        <VALOR codigo=\"MAILSECD\">gestio@fbit.org</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMOCULSECD\">Toni Mesquida Mestre</VALOR>\r\n"
                    + "        <VALOR codigo=\"LDAENTORNOCULTO\">- Producción</VALOR>\r\n"
                    + "        <VALOR codigo=\"LDAENTOCULTOEXCEL\">'- Entornos: Producción</VALOR>\r\n"
                    + "        <VALOR codigo=\"IDIOMA\">ca</VALOR>\r\n"
                    + "        <VALOR codigo=\"COPIARDATOS\">false</VALOR>\r\n"
                    + "        <VALOR codigo=\"NIFSECE\">22222222E</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRESECE\">Paco</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE1SECE\">Gaita</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE2SECE\">Sureda</VALOR>\r\n"
                    + "        <VALOR codigo=\"CARGOSECE\">Auditor: Petats Gaita's Leader</VALOR>\r\n"
                    + "        <VALOR codigo=\"TELEFONOSECE\">971213458</VALOR>\r\n"
                    + "        <VALOR codigo=\"MAILSECE\">auditor@fbit.org</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMOCULSECE\">Paco Gaita Sureda</VALOR>\r\n"
                    + "        <VALOR codigo=\"NIFSECF\">33333333I</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRESECF\">Toni</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE1SECF\">Nadal</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE2SECF\">Bennassar</VALOR>\r\n"
                    + "        <VALOR codigo=\"CARGOSECF\">Tecnic: L'amo dels Tecnics</VALOR>\r\n"
                    + "        <VALOR codigo=\"TELEFONOSECF\">971745318</VALOR>\r\n"
                    + "        <VALOR codigo=\"MAILSECF\">tecnic@fbit.org</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMOCULSECF\">Toni Nadal Bennassar</VALOR>\r\n"
                    + "        <VALOR codigo=\"NIFSECG\">444444444O</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBRESECG\">Isi</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE1SECG\">Palazón</VALOR>\r\n"
                    + "        <VALOR codigo=\"APE2SECG\">Rayo</VALOR>\r\n"
                    + "        <VALOR codigo=\"CARGOSECG\">Titular: Jugador del Rayo titularisimo</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMOCULSECG\">Isi Palazón Rayo</VALOR>\r\n"
                    + "        <VALOR codigo=\"TIPOPROCEDIMIENTO\">1</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMTIPUSPROCEDIMIENT\">Aduanero</VALOR>\r\n"
                    + "        <VALOR codigo=\"NOMBREPROC\">Subvencions que m'acab d'inventar</VALOR>\r\n"
                    + "        <VALOR codigo=\"CODIPROC\">2874870</VALOR>\r\n"
                    + "        <VALOR codigo=\"DIRFICHA\">https://www.google.es</VALOR>\r\n"
                    + "        <VALOR codigo=\"CADUCA\">N</VALOR>\r\n"
                    + "        <VALOR codigo=\"FECHACAD\"></VALOR>\r\n"
                    + "        <VALOR codigo=\"DESCRIPCION\">Proceso ayudas para Asociaciones de Familias de Alumnos</VALOR>\r\n"
                    + "        <VALOR codigo=\"PETICIONESDIA\">12</VALOR>\r\n"
                    + "        <VALOR codigo=\"PETICIONESMES\">450</VALOR>\r\n"
                    + "        <VALOR codigo=\"AUTOMATIZADO\">S</VALOR>\r\n"
                    + "        <VALOR codigo=\"PERIODICO\">N</VALOR>\r\n"
                    + "        \r\n"
                    + "        <VALOR codigo=\"CONTCONSADJ\">false</VALOR>\r\n"
                    + "        <VALOR codigo=\"ELEMREPT\">SVDMUFAFIWS01</VALOR>\r\n"
                    + "        <VALOR codigo=\"VALIDAELEMREPT\"></VALOR>\r\n"
                    + "    </CAMPO>\r\n"
                    + "</FORMULARIO>\r\n";
     */
}

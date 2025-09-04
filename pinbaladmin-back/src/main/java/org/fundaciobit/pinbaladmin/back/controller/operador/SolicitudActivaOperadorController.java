package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SolicitudActivaOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaOperadorController extends SolicitudOperadorController {

	public static final String CONTEXTWEB = "/operador/solicitudactiva";

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;
	
	
	@Override
	public Where getAdditionalConditionFine(HttpServletRequest request) throws I18NException {
		return super.getAdditionaConditionAdvancedFilter(request);
//		return SolicitudFields.ESTATSOLICITUD.lessThan(60L); // 60 == ESTAT TANCAT
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "SolicitudWebDB_Activa_FilterForm_Operador";
	}

	@Override
	public String getEntityNameCode() {
		return "solicitud.solicitudactiva";
	}

	@Override
	public String getEntityNameCodePlural() {
		return "solicitud.solicitudactiva.plural";
	}

	@Override
	public Boolean isEstatal() {
		return null; // Significa que gestiona els dos tipus
	}

	@Override
	public boolean showAdvancedFilter() {
		return true;
	}

	@Override
	public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		// TODO Auto-generated method stub
		SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

		if (solicitudFilterForm.isNou()) {
			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "solicitud.actualitzarestats",
					getContextWeb() + "/actualitzarEstats", AdditionalButtonStyle.WARNING));
			
//			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Consulta Estat PID",
//					getContextWeb() + "/consultaMadrid", AdditionalButtonStyle.WARNING));

			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Crear Info Madrid",
					getContextWeb() + "/crearInfoMadrid", AdditionalButtonStyle.WARNING));
		}

		return solicitudFilterForm;
	}

	// crearInfoMadrid
	@RequestMapping(value = "/crearInfoMadrid", method = RequestMethod.GET)
	public String crearInfoMadrid(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		version1CrearInfoMad();
		
		version2CrearInfoMad();
		
		HtmlUtils.saveMessageSuccess(request, "Estat de les sol·licituds actualitzat correctament.");
		return "redirect:" + getContextWeb() + "/list";

	}
	
	private void version2CrearInfoMad() throws Exception {
		
		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals)); // , wEstatPinbal , wEstatSoli));
		
		log.info("Solicituds: " + solicituds.size());
		
		
		for (Solicitud soli : solicituds) {
			
			log.info("Solicitud: " + soli.getSolicitudID());
			
			long reintents = 0;
			int estatAutNou = -1000;

			boolean crear;
			Timestamp dataEnviament;
			String missatge;
			Timestamp dataAuth;

			int estatID = Long.valueOf(soli.getEstatSolicitud()).intValue();

			if (estatID == Constants.SOLI_ESTAT_SENSE_ESTAT
				|| estatID == Constants.SOLI_ESTAT_PENDENT_DISTRIBUCIO	
				|| estatID == Constants.SOLI_ESTAT_PENDENT_Enviar_Director
				|| estatID == Constants.SOLI_ESTAT_PENDENT_Firma_Director) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
				
			}else if (estatID == Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID) {
				//Encara no s'han fet modificacions. pendent d'enviar es que no s'ha enviat.
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
				
			}else if (estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual) {
				crear = true;
				
				//Crear amb dades manuals.
				dataEnviament = null;
				dataAuth = soli.getDataFi();
				missatge = "Solicitud Enviada a Madrid manualment.";
			
			}else if  (estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR) {
				crear = true;
				//Cridar CONSULTA per saber si ha estat manual o no.
				
				Retorno ret = consulta(soli);
				
				if (ret == null) {
					//Ha sido Manual.
					dataEnviament = null;
					dataAuth = null;
					missatge = "Solicitud Enviada a Madrid manualment.";
				}else {
					dataEnviament = new Timestamp(System.currentTimeMillis());
					dataAuth = null;
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
				}

			}else if (estatID == Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID) {
				crear = true;

				dataEnviament = null;
				dataAuth = null;
				reintents = 1;
				missatge = null;
				
			}else if  (estatID == Constants.SOLI_ESTAT_AUTORITZAT
					|| estatID == Constants.SOLI_ESTAT_TANCAT) {
				crear = true;
				
				//Cridar CONSULTA per saber si ha estat manual o no.
				Retorno ret = consulta(soli);
				
				if (ret == null) {
					//Ha sido Manual.
					dataEnviament = null;
					dataAuth = soli.getDataFi();
					missatge = "Solicitud Enviada a Madrid manualment, i AUTORITZADA.";
				}else {
					dataEnviament = soli.getDataFi();
					dataAuth = soli.getDataFi();
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();

				}
				
			}else if (estatID == Constants.SOLI_ESTAT_AUTORITZAT_Manual) {
				crear = true;

				dataEnviament = null;
				dataAuth = soli.getDataFi();
				missatge = "Solicitud Enviada a Madrid manualment, i AUTORITZADA.";
			}else if (estatID == Constants.SOLI_ESTAT_AUTORITZAT_Parcial) {
				crear = true;
				
				dataEnviament = null;
				dataAuth = soli.getDataFi();
				missatge = "Solicitud Enviada a Madrid manualment, i autoritzada PARCIALMENT.";
			} else if (estatID == Constants.SOLI_ESTAT_PENDENT_REVISAR_MODIFICACIO
					|| estatID == Constants.SOLI_ESTAT_PENDENT_ENVIAR_MODIFICACIO_MADRID
					|| estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO
					|| estatID == Constants.SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID) {
				//Ja les han autoritzat abans. I totes les de modificacions també. CONSULTA per veure estat.
				crear = true;

				//Cridar CONSULTA per saber si ha estat manual o no.
				Retorno ret = consulta(soli);
				
				if (ret == null) {
					//Ha sido Manual.
					dataEnviament = null;
					dataAuth = soli.getDataFi();
					missatge = "Solicitud Enviada a Madrid manualment. Pendent Esmena";
				}else {
					dataEnviament = soli.getDataFi();
					dataAuth = soli.getDataFi();
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
				}
				
			} else if (

			estatID == Constants.SOLI_ESTAT_ESMENES || estatID == Constants.SOLI_ESTAT_ESMENA_ENVIAR_CONTACTE
					|| estatID == Constants.SOLI_ESTAT_ESMENA_PENDENT_RESPOSTA
					|| estatID == Constants.SOLI_ESTAT_ESMENA_PENDENT_REVISAR
					|| estatID == Constants.SOLI_ESTAT_AUTORITZAT_ESMENES) {
				crear = true;
			
				//En tots els casos de esmenes, cridar consulta per si hi algun missatge, i guardar estat real.
				//Cridar CONSULTA 
				Retorno ret = consulta(soli);
				
				if (ret == null) {
					//Ha sido Manual.
					dataEnviament = null;
					dataAuth = null;
					missatge = "Solicitud Enviada a Madrid manualment. Pendent Esmena";
				}else {
					dataEnviament = null;
					dataAuth = null;
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
				}
				
			}else if (estatID == Constants.SOLI_ESTAT_REVISIO) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			}else if  (estatID == Constants.SOLI_ESTAT_DENEGADA) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			}else if (estatID == Constants.SOLI_ESTAT_PENDENT_PINFO) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			}else {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			}

			
			log.info("Missatge: " + missatge);
			
			log.info("EstatSoli: " + estatID + ". crear=" + crear + ". InfoMadrid: " + soli.getInfomadridid());
			
			if (crear) {
				String codi = soli.getProcedimentCodi();
				if (soli.getInfomadridid() == null) {

					log.info("Intentarem Crear InfoMad");


					String consultaTexto = "Buenos días,\n"
							+ "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
							+ "Quedamos a la espera de su respuesta.\n" + "Un saludo.";

					long estatProc = estatID;

					String titularNom = null;
					String titularNif = null;

					String[] datosTitular = dadesTitular(soli);
					log.info("Datos Titular: " + datosTitular);
					if (datosTitular != null) {

						titularNom = datosTitular[0];
						titularNif = datosTitular[1];
					}

					long estatAut;

					if (estatAutNou == -1000) {
						if (soli.getEstatpinbal() != null) {
							estatAut = soli.getEstatpinbal();
						} else {
							estatAut = Constants.ESTAT_PINBAL_null;
						}
					} else {
						estatAut = (long) estatAutNou;
					}

					InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto,
							titularNom, titularNif, dataAuth, dataEnviament, reintents);

					log.info("CREAM InfoMad per solicitud: " + codi);
					InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
					
					log.info("Creat InfoMad amb id = " + infoMad.getInfoMadridID());
					soli.setInfomadridid(infoMad.getInfoMadridID());
					solicitudLogicaEjb.update(soli);

				} else {
					log.info("Ja tenim InfoMadrid. Asssignam a tots els procediments d'aquest codi. " + codi);
					List<Solicitud> solicitudIguals = solicitudLogicaEjb.select(SolicitudFields.PROCEDIMENTCODI.equal(codi));
					for (Solicitud solicitud : solicitudIguals) {
						solicitud.setInfomadridid(soli.getInfomadridid());
						log.info("UPDATE soli " + solicitud.getSolicitudID());
						solicitudLogicaEjb.update(solicitud);
					}

				}
			}
		}		
	}
	
	private String[] dadesTitular(Solicitud soli) throws Exception {
		
		
		
		Long fitxerID = soli.getSolicitudXmlID();

		if (fitxerID == null) {
			log.info("fitxerID: " + fitxerID);
			return null;
		}

		Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
		if (prop == null) {
			log.info("prop: " + prop);
			return null;
		}
		
		ScspTitular titular = getTitular(prop);

		String[] datosTitular = new String[2];
		
		datosTitular[0] = titular.getNombreCompleto();
		datosTitular[1] = titular.getDocumentacion();
		
		return datosTitular;
	}
	
	private Retorno consulta(Solicitud soli) throws Exception {
		final String SOLICITUD_TROBADA = "0";
		final String SOLICITUD_ENVIADA_MANUALMENTE = "2";
		
		Long fitxerID = soli.getSolicitudXmlID();

		if (fitxerID == null) {
			log.info("fitxerID: " + fitxerID);
			return null;
		}

		Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
		if (prop == null) {
			log.info("prop: " + prop);
			return null;
		}
		
		ScspTitular titular = getTitular(prop);
		if (titular == null) {
			log.info("titular: " + titular);
			return null;
		}
		ScspFuncionario funcionario = getFuncionari();
		if (funcionario == null) {
			log.info("funcionario: " + funcionario);
			return null;
		}

		Consulta consulta = new Consulta();
		consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

		log.info("Cridam métode CONSULTA");

		Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
		
		if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_TROBADA)) {
			return retorno;
			
		} else if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
			log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
			return null;
		}else {
			return null;
		}
		
	}
	
	
	
	
	private void version1CrearInfoMad() throws Exception {
		// Actualitzar estat de les sol·licituds
		log.info("crearInfoMadrid:: HOLA");
		final String SOLICITUD_TROBADA = "0";
		final String SOLICITUD_ENVIADA_MANUALMENTE = "2";

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wEstatPinbal = Where.AND(SolicitudFields.ESTATPINBAL.isNotNull(),
				SolicitudFields.ESTATPINBAL.notEqual(Constants.ESTAT_PINBAL_NO_SOLICITAT));

//			Long[] estatsSoliOk =  {Constants.soli_estat_};
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.in(estatsSoliOk );
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_REVISIO);

		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals)); // , wEstatPinbal , wEstatSoli));

		log.info(solicituds.size() + " solicituds");

		int solis = 0;
		for (Solicitud soli : solicituds) {

			log.info("Volem procesar la solicitud " + soli.getProcedimentCodi() + " [" + soli.getSolicitudID() + "]");

			if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_MANUAL) {
				log.info("Fichada Manual (" + soli.getProcedimentCodi() + ")");
				continue;
			}

			if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_null) {
				log.info("Fichada Null (" + soli.getProcedimentCodi() + ")");
				continue;
			}

			if (soli.getProcedimentCodi().length() > 20) {
				log.info("Procediment Llarg. Descartat");
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Long solisProc = infoMadridLogicaEjb.count(InfoMadridFields.CODI.equal(soli.getProcedimentCodi()));
			if (solisProc > 0) {
				log.info("Solicitud ja te un InfoMad");
				continue;
			}

			// Si llega aquí la vamos a procesar.

			Long fitxerID = soli.getSolicitudXmlID();

			if (fitxerID == null) {
				log.info("fitxerID: " + fitxerID);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
			if (prop == null) {
				log.info("prop: " + prop);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			ScspTitular titular = getTitular(prop);
			if (titular == null) {
				log.info("titular: " + titular);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}
			ScspFuncionario funcionario = getFuncionari();
			if (funcionario == null) {
				log.info("funcionario: " + funcionario);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Consulta consulta = new Consulta();
			consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

			log.info("Cridam métode CONSULTA");

			Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
			if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
				log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
				continue;
			} else if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_TROBADA)) {
				log.info("Solicitud Trobada. S'hauria d'haver creat InfoMad");
			}

//				String codi = soli.getProcedimentCodi();
//				long estatProc = soli.getEstatSolicitud();
			//
//				EstadoProcedimiento estadoProc = retorno.getProcedimiento().getEstadoProcedimiento();
//				
//				int estatAut = estadoProc.getEstado();
//				String missatge = estadoProc.getObservaciones();
//				
//				String consultaTexto = "Buenos días,\n"
//						+ "Enviamos solicitud para dar servicios de alta en el procedimiento "
//						+ codi + "\n\n" + "Quedamos a la espera de su respuesta.\n"
//						+ "Un saludo.";
//				
//				String titularNom = titular.getNombreCompleto();
//				String titularNif = titular.getDocumentacion();
			//
//				Timestamp now = new Timestamp(System.currentTimeMillis());
//				
//				Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
//				Timestamp dataEnviament = now;
//				
//				long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;
//				
//				InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
//						titularNif, dataAuth, dataEnviament, reintents);
			//
//				log.info("Crearem InfoMad");
//				
//				InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//				log.info("InfoMad creado: " + infoMad.getInfoMadridID());
			//
//				soli.setInfomadridid(infoMad.getInfoMadridID());
//				solicitudLogicaEjb.update(soli);
//				
			solis++;

			log.info("Final");
//				if (solis == 5) {
//					break;
		}
	}
	

	  private ScspTitular getTitular(Properties prop) {

	        ScspTitular titular = new ScspTitular();

	        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
	        String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECE");
	        String nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECE");
	        String ape1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECE");
	        String ape2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECE");
	        String fullName = toFullName(nombre, ape1, ape2);

	        titular.setTipoDocumentacion(tipoDocumentacion);
	        titular.setDocumentacion(documentacion);
	        titular.setNombre(nombre);
	        titular.setApellido1(ape1);
	        titular.setApellido2(ape2);
	        titular.setNombreCompleto(fullName);

	        return titular;
	    }

	    private ScspFuncionario getFuncionari() {

	        ScspFuncionario funcionario = new ScspFuncionario();

//	        UserInfo ui = LoginInfo.getInstance().getUserInfo();

			String nif = null;
			String fullName = null;
	        
//	        if (ui != null) {
//	            nif = ui.getAdministrationID();
//	            fullName = ui.getFullName();
//	        	if (fullName == null) {
//					fullName = ui.getName() + " " + ui.getSurname1() + " " + ui.getSurname2();
//				}
//	        }else {
//	        	String username = LoginInfo.getInstance().getUsername();
//	        	
//	        	switch (username) {
//	        	case "ptrias":
//	        		nif = "45186147W";
//	        		fullName = "Juan Pablo Trias";
//	        		break;
//	        	case "pvico":
//	        		nif = "43084402C";
//	        		fullName = "Pilar Vico Hervas";
//	        		break;
//	        	case "atrobat":
//	        		nif = "43120476F";
//	        		fullName = "Toni Trobat Obrador";
//	        		break;
//				default:
//					nif = "00000000T";
//					fullName = "Usuari Anonim 00000000T";
//	        	}
//	        	
//	        }
	        
	    	String username = LoginInfo.getInstance().getUsername();
	    	switch (username) {
	    	case "ptrias":
	    		nif = "45186147W";
	    		fullName = "Juan Pablo Trias";
	    		break;
	    	case "pvico":
	    		nif = "43084402C";
	    		fullName = "Pilar Vico Hervas";
	    		break;
	    	case "atrobat":
	    		nif = "43120476F";
	    		fullName = "Toni Trobat Obrador";
	    		break;
			default:
				nif = "00000000T";
				fullName = "Usuari Anonim 00000000T";
	    	}

	        
	        log.info("NIF: " + nif);
	        log.info("Nombre completo: " + fullName);

	        funcionario.setNifFuncionario(nif);
	        funcionario.setNombreCompletoFuncionario(fullName);
	        return funcionario;
	    }
	    
	    private String toFullName(String nom, String l1, String l2) {
	        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
	        return fullName;
	    }

	    
	    
//	    @RequestMapping(value = "/consultaMadrid", method = RequestMethod.GET)
//		public String consultaMadrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//			// Actualitzar estat de les sol·licituds
//			log.info("crearInfoMadrid:: HOLA");
//			final String SOLICITUD_TROBADA = "0";
//			final String SOLICITUD_ENVIADA_MANUALMENTE = "2";
//			
//			Where wLocals = SolicitudFields.ORGANID.isNotNull();
//			Where wEstatPinbal = Where.AND(SolicitudFields.ESTATPINBAL.isNotNull(),
//					SolicitudFields.ESTATPINBAL.notEqual(Constants.ESTAT_PINBAL_NO_SOLICITAT));
//			
//			
////			Long[] estatsSoliOk =  {Constants.soli_estat_};
////			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.in(estatsSoliOk );
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual);
//			
//			
//			List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wEstatPinbal, wEstatSoli));
//
//			log.info(solicituds.size() + " solicituds");
//			
//			
//			int solis = 0;
//			for (Solicitud soli : solicituds) {
//				
//				Long solisProc = infoMadridLogicaEjb.count(InfoMadridFields.CODI.equal(soli.getProcedimentCodi()));
//				if (solisProc > 0) {
//					log.info("Solicitud ja te un InfoMad");
//					continue;
//				}
//				
//				
//				Long fitxerID = soli.getSolicitudXmlID();
//				Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
//
//				ScspTitular titular = getTitular(prop);
//				ScspFuncionario funcionario = getFuncionari();
//
//				Consulta consulta = new Consulta();
//				consulta.setCodigoProcedimiento(soli.getProcedimentCodi());
//
//				Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
//				if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
//					log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
//					continue;
//				}
//				
//				String codi = soli.getProcedimentCodi();
//				long estatProc = soli.getEstatSolicitud();
//
//				EstadoProcedimiento estadoProc = retorno.getProcedimiento().getEstadoProcedimiento();
//				
//				int estatAut = estadoProc.getEstado();
//				String missatge = estadoProc.getObservaciones();
//				
//				String consultaTexto = "Buenos días,\n"
//						+ "Enviamos solicitud para dar servicios de alta en el procedimiento "
//						+ codi + "\n\n" + "Quedamos a la espera de su respuesta.\n"
//						+ "Un saludo.";
//				
//				String titularNom = titular.getNombreCompleto();
//				String titularNif = titular.getDocumentacion();
//
//				Timestamp now = new Timestamp(System.currentTimeMillis());
//				
//				Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
//				Timestamp dataEnviament = now;
//				
//				long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;
//				
//				InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
//						titularNif, dataAuth, dataEnviament, reintents);
//
//				log.info("Crearem InfoMad");
//				
//				InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//				log.info("InfoMad creado: " + infoMad.getInfoMadridID());
//
//				soli.setInfomadridid(infoMad.getInfoMadridID());
//				solicitudLogicaEjb.update(soli);
//				
//				solis++;
//				
//				log.info("Final");
////				if (solis == 5) {
////					break;
////				}
//			}
//			
//			HtmlUtils.saveMessageSuccess(request, "Estat de les sol·licituds actualitzat correctament.");
//			return "redirect:" + getContextWeb() + "/list";
//
//		}
	    
	    
////  /actualitzarEstats
//	@RequestMapping(value = "/actualitzarEstats", method = RequestMethod.GET)
//	public String actualitzarEstats(HttpServletRequest request, HttpServletResponse response) throws I18NException {
//
//		// Actualitzar estat de les sol·licituds
//		log.info("Actualizaremos el estado de las solicitudes pendientes");
//
//		long anticEstatPendent = 10;
//
//		Long[] estatsAProcesar = {Constants.SOLICITUD_ESTAT_PENDENT_DISTRIBUCIO, Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director,
//				Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID, Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director,
//				Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR, Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents,
//				Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent, anticEstatPendent };
//
//		List<Solicitud> list = solicitudLogicaEjb.select(SolicitudFields.ESTATID.in(estatsAProcesar));
//
//		log.info("Sol·licituds a actualitzar: " + list.size());
//		int updates = 0;
//		for (Solicitud soli : list) {
//			Long nouEstat;
//			Long soliID = soli.getSolicitudID();
//			if (soli.getOrganid() != null) {
//				// Si es local, veure si está pendent d'enviar a DG, pendent de DG, pendent
//				// d'enviar a Madrid, o pendent d'autoritzar
//
//				if (isFirmatPelDirector(soli.getSolicitudID())) {
//					// Vuere si ja s'ha enviat a Madrid. Utilitzar l'estat Pinbal
//					Integer estatPinbal = soli.getEstatpinbal();
//					if (estatPinbal == null) {
//						soli.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
//					}
//
//					if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_NO_SOLICITAT) {
//						log.info("LOCAL - SoliID :" + soliID + " firmada director i no enviada a Madrid");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID;
//					} else if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR) {
//						log.info("LOCAL - SoliID :" + soliID + " enviada a Madrid amb ERROR");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//					} else {
//						log.info("LOCAL - SoliID :" + soliID + " firmada director i enviada a Madrid");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//					}
//				} else {
//					// No te el document firmat. Comprovar si s'ha enviat o no.
//					if (isEnviatAFirmar(soliID)) {
//						log.info("LOCAL - SoliID :" + soliID + " no firmada director, pero enviada a firmar");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director;
//					} else {
//						log.info("LOCAL - SoliID :" + soliID + " no enviada a firmar al director");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director;
//					}
//				}
//
//			} else {
//				// Solicituts estatals
//				// S'ha de veure si s'han enviat correus de consulta a cedents. Si no n'hi ha,
//				// pendent d'enviar a cedents. Si n'hi ha, pendent firma_cedents. Si hi ha
//				// tantes consultes a cedents com respostes, penent autoritzar
//
//				List<Event> eventsSoliEstatal = eventLogicaEjb.select(EventFields.SOLICITUDID.equal(soliID));
//				int numConsultes = 0;
//				int numRespostes = 0;
//
//				for (Event event : eventsSoliEstatal) {
//					if (event.getTipus() == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT) {
//						numConsultes++;
//					} else if (event.getTipus() == Constants.EVENT_TIPUS_CEDENT_RESPOSTA) {
//						numRespostes++;
//					}
//				}
//
//				if (numConsultes == 0) {
//					log.info("ESTATAL - SoliID :" + soliID + " no hi ha consultes a cedents");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents;
//				} else if (numConsultes == numRespostes) {
//					log.info("ESTATAL - SoliID :" + soliID + " Totes les consultes a cedents respostes (" + numRespostes
//							+ "/" + numConsultes + ")");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//				} else {
//					log.info("ESTATAL - SoliID :" + soliID + " Consultes a cedents pendents de resposta ("
//							+ numRespostes + "/" + numConsultes + ")");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent;
//				}
//			}
//
//			if (nouEstat != soli.getEstatID()) {
//				updates ++;
//                soli.setEstatID(nouEstat);
//                solicitudLogicaEjb.update(soli);
//			}
//		}
//
//		HtmlUtils.saveMessageSuccess(request, "Estat de les " + updates + " sol·licituds actualitzat correctament.");
//		return "redirect:" + getContextWeb() + "/list";
//
//	}

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

	public boolean isFirmatPelDirector(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getFitxerFirmatID() != null) {
				return true;
			}
		}

		return false;
	}

	public boolean isEnviatAFirmar(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getNotes() != null && document.getNotes().trim().length() > 0) {
				return true;
			}
		}

		return false;
	}
}

package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import com.google.gson.Gson;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.SearchUsersResult;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.comu.Page;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.recobriment.v2.ClientRecobriment;
import es.caib.pinbal.client.recobriment.v2.Entitat;
import es.caib.pinbal.client.usuaris.FiltreUsuaris;
import es.caib.pinbal.client.usuaris.PermisosServei;
import es.caib.pinbal.client.usuaris.UsuariClient;
import es.caib.pinbal.client.usuaris.UsuariEntitat;

/**
 * 
 * @author ptrias
 * 12 dic 2025 13:15:25
 */
@Controller
@RequestMapping(value = "/operador/dadespinbal")
public class DadesPinbalController {
	
    protected static final Logger log = Logger.getLogger(DadesPinbalController.class);
    
    private static final String DEFAULT_ENTITAT_CODI = "GOVERN";
    
    private static List<Entitat> cachedEntitats = null;
    
    // Cachés para optimizar búsquedas de procedimientos y servicios
    private static Map<Long, Solicitud> cachedProcedimentsAmbPinfos = null;
    private static Map<Long, Servei> cachedServeisAmbPinfos = null;
    private static long cacheTimestamp = 0;
    private static final long CACHE_TTL_MS = 5 * 60 * 1000; // 5 minutos
    
    // Plugin LDAP cacheado para evitar reinicializaciones
    private static IUserInformationPlugin cachedPlugin = null;
    
    @EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
    private PinfoDataLogicaService pinfoDataLogicaEjb;
    
    @EJB(mappedName = PinfoLogicaService.JNDI_NAME)
    private PinfoLogicaService pinfoLogicaEjb;
    
    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    private SolicitudLogicaService solicitudLogicaEjb;
    
    @EJB(mappedName = ServeiLogicaService.JNDI_NAME)
    private ServeiLogicaService serveiLogicaEjb;

    @RequestMapping(value = "/permisos", method = RequestMethod.GET)
    public ModelAndView getPermisos(
            @RequestParam("usuariCodi") String usuariCodi,
            @RequestParam("entitatCodi") String entitatCodi) {
        
        ModelAndView mav = new ModelAndView("operador/modalPermisos");
        
        final String baseUrl = Configuracio.getApiPinbalClientUrl();
        final String username = Configuracio.getApiPinbalClientUsername();
        final String password = Configuracio.getApiPinbalClientPassword();
        final LogLevel logLevel = LogLevel.INFO;
        
        try {
            UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
            PermisosServei permisos = usuariClient.getUserPermissions(usuariCodi, entitatCodi);
            
            if (permisos != null) {
                mav.addObject("permisos", permisos.getProcedimentServei());
                mav.addObject("usuariCodi", usuariCodi);
                mav.addObject("entitatCodi", entitatCodi);
            }
        } catch (Exception e) {
            log.error("Error obteniendo permisos", e);
            mav.addObject("error", "Error obteniendo permisos: " + e.getMessage());
            mav.addObject("usuariCodi", usuariCodi);
            mav.addObject("entitatCodi", entitatCodi);
        }
        
        return mav;
    }

    @RequestMapping(value = "/serveis", method = RequestMethod.GET)
    public ModelAndView getServeis(
            @RequestParam("procedimentCodi") String procedimentCodi,
            @RequestParam("entitatCodi") String entitatCodi) {
        
        ModelAndView mav = new ModelAndView("operador/modalServeis");
        mav.addObject("procedimentCodi", procedimentCodi);
        mav.addObject("entitatCodi", entitatCodi);
        return mav;
    }

    @RequestMapping(value = "/procediments", method = RequestMethod.GET)
    public ModelAndView listProcediments (
            HttpServletRequest request, 
            HttpServletResponse response,
            Integer pageNum,
            Integer pageSize,
            String searchCodi,
            String searchNom,
            String entitatCodi) {

        ModelAndView mav = new ModelAndView("procedimentspinbal");

        final String baseUrl = Configuracio.getApiPinbalClientUrl();
        final String username = Configuracio.getApiPinbalClientUsername();
        final String password = Configuracio.getApiPinbalClientPassword();
        final LogLevel logLevel = LogLevel.INFO;

        ProcedimentClient procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);
        
        // Obtener lista de entidades (reutilizando lógica)
        List<Entitat> entitats = new ArrayList<Entitat>();
        String selectedEntitat = entitatCodi;
        
        try {
            if (cachedEntitats == null) {
                ClientRecobriment clientRecobriment = new ClientRecobriment(baseUrl, username, password, logLevel);
                clientRecobriment.enableLogginFilter();
                cachedEntitats = clientRecobriment.getEntitats();
            }
            entitats = cachedEntitats;
            mav.addObject("entitats", entitats);
            
            if (selectedEntitat == null || selectedEntitat.trim().isEmpty()) {
                if (entitats != null && !entitats.isEmpty()) {
                    selectedEntitat = entitats.get(0).getCodi();
                } else {
                    selectedEntitat = DEFAULT_ENTITAT_CODI;
                }
            }
        } catch (Exception e) {
            log.error("Error obteniendo entidades", e);
            if (selectedEntitat == null || selectedEntitat.trim().isEmpty()) {
                selectedEntitat = DEFAULT_ENTITAT_CODI;
            }
        }
        
        mav.addObject("selectedEntitat", selectedEntitat);
        
        // Valores de paginación
        int page = (pageNum != null && pageNum >= 0) ? pageNum : 0;
        int size = (pageSize != null && pageSize > 0) ? pageSize : 20;
        //String sort = null;
        
        mav.addObject("currentPage", page);
        mav.addObject("pageSize", size);
        mav.addObject("searchCodi", searchCodi);
        mav.addObject("searchNom", searchNom);
        
        try {
            // Último intento: Cadenas vacías para textos y null para sort (comportamiento estándar en otros clientes)
            String codi = (searchCodi != null) ? searchCodi.trim() : "";
            String nom = (searchNom != null) ? searchNom.trim() : "";
            String organGestor = ""; 
            String sortParam = null;
            
            log.info("Obteniendo procediments de la entidad: " + selectedEntitat + ", página: " + page + 
                    ", codi: '" + codi + "', nom: '" + nom + "', organGestor: '" + organGestor + "', sort: " + sortParam);
            
            Page<Procediment> procedimentPage = procedimentClient.getProcediments(selectedEntitat, codi, nom, organGestor, page, size, sortParam);

            if (procedimentPage != null) {
                log.info("Total procediments encontrados: " + procedimentPage.getTotalElements());
                
                List<Procediment> procediments = procedimentPage.getContent();
                mav.addObject("procediments", procediments);
                mav.addObject("totalElements", procedimentPage.getTotalElements());
                mav.addObject("totalPages", procedimentPage.getTotalPages());
            } else {
                log.warn("No se obtuvieron resultados del cliente de procediments");
            }
        } catch (Exception e) {
            log.error("Error al obtener procediments de Pinbal", e);
            mav.addObject("error", "Error al conectar con el servicio de procediments: " + e.getMessage());
        }

        return mav;
    }

    @RequestMapping(value = "/usuaris", method = RequestMethod.GET)
    public ModelAndView listUsuaris (
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		Integer pageNum,
    		Integer pageSize,
    		String searchNif,
    		String searchCodi,
    		String searchNom,
    		String entitatCodi) {

        ModelAndView mav = new ModelAndView("usuarispinbal");

		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();
		final LogLevel logLevel = LogLevel.INFO;

		log.info("Inicializando UsuariClient con baseUrl: " + baseUrl);
		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
        // usuariClient.getUserPermissions(usuariCodi, entitatCodi);
		
		// Obtener lista de entidades
		List<Entitat> entitats = new ArrayList<Entitat>();
		String selectedEntitat = entitatCodi;
		
		try {
			if (cachedEntitats == null) {
				ClientRecobriment clientRecobriment = new ClientRecobriment(baseUrl, username, password, logLevel);
				clientRecobriment.enableLogginFilter();
				cachedEntitats = clientRecobriment.getEntitats();
			}
			entitats = cachedEntitats;
			mav.addObject("entitats", entitats);
			
			// Si no se ha seleccionado entidad, usar la primera disponible o GOVERN por defecto
			if (selectedEntitat == null || selectedEntitat.trim().isEmpty()) {
				if (entitats != null && !entitats.isEmpty()) {
					selectedEntitat = entitats.get(0).getCodi();
				} else {
					selectedEntitat = DEFAULT_ENTITAT_CODI;
				}
			}
		} catch (Exception e) {
			log.error("Error obteniendo entidades", e);
			if (selectedEntitat == null || selectedEntitat.trim().isEmpty()) {
				selectedEntitat = DEFAULT_ENTITAT_CODI;
			}
		}
		
		mav.addObject("selectedEntitat", selectedEntitat);
		
		FiltreUsuaris filtreUsuaris = null;

        // Valores de paginación
		int page = (pageNum != null && pageNum >= 0) ? pageNum : 0;
		int size = (pageSize != null && pageSize > 0) ? pageSize : 20;
		String sort = null;
		
		// Pasar parámetros al JSP para mantener el estado
		mav.addObject("currentPage", page);
		mav.addObject("pageSize", size);
		mav.addObject("searchNif", searchNif);
		mav.addObject("searchCodi", searchCodi);
		mav.addObject("searchNom", searchNom);
		
		// Verificar si hay algún criterio de búsqueda
		boolean hasSearch = (searchNif != null && !searchNif.trim().isEmpty()) ||
		                     (searchCodi != null && !searchCodi.trim().isEmpty()) ||
		                     (searchNom != null && !searchNom.trim().isEmpty());
		
		try {
			// Si hay filtro de búsqueda, buscar usuarios con ese filtro
			if (hasSearch) {
				log.info("Buscando usuarios por NIF:" + searchNif + ", Codi:" + searchCodi + ", Nom:" + searchNom + ", Entitat:" + selectedEntitat);
				Page<UsuariEntitat> usuariPage = buscarUsuaris(searchNif, searchCodi, searchNom, selectedEntitat, usuariClient, page, size, sort);
				
				if (usuariPage != null && usuariPage.getContent() != null && !usuariPage.getContent().isEmpty()) {
					log.info("Total usuarios encontrados: " + usuariPage.getTotalElements());
					List<UsuariEntitat> usuaris = usuariPage.getContent();
					mav.addObject("usuaris", usuaris);
					mav.addObject("totalElements", usuariPage.getTotalElements());
					mav.addObject("totalPages", usuariPage.getTotalPages());
				} else {
					mav.addObject("error", "No s'ha trobat cap usuari amb els criteris especificats");
				}
			} else {
				// Listar todos los usuarios con paginación
				log.info("Obteniendo usuarios de la entidad: " + selectedEntitat + ", página: " + page);
	            Page<UsuariEntitat> usuariPage = usuariClient.getUsuaris(selectedEntitat, filtreUsuaris, page, size, sort);

	            if (usuariPage != null) {
	                log.info("Total usuarios encontrados: " + usuariPage.getTotalElements());
	                log.info("Página actual: " + usuariPage.getNumber());
	                log.info("Total páginas: " + usuariPage.getTotalPages());
	                
	                List<UsuariEntitat> usuaris = usuariPage.getContent();
	                mav.addObject("usuaris", usuaris);
	                mav.addObject("totalElements", usuariPage.getTotalElements());
	                mav.addObject("totalPages", usuariPage.getTotalPages());
	                
	                if (usuaris != null && !usuaris.isEmpty()) {
	                    log.info("Primer usuario: " + usuaris.get(0).getNom());
	                }
	            } else {
	                log.warn("No se obtuvieron resultados del cliente de usuarios");
	            }
			}
        } catch (Exception e) {
            log.error("Error al obtener usuarios de Pinbal", e);
            mav.addObject("error", "Error al conectar con el servicio de usuarios: " + e.getMessage());
        }

		return mav;
	}
	
	/**
	 * Busca usuarios por NIF, Nom o Codi usando FiltreUsuaris de Pinbal
	 * Devuelve una página con todos los usuarios que coincidan con los filtros especificados
	 */
	private Page<UsuariEntitat> buscarUsuaris(String searchNif, String searchCodi, String searchNom, String entitatCodi, UsuariClient usuariClient, int page, int size, String sort) {
		try {
			log.info("Buscando usuarios con filtros - NIF:" + searchNif + ", Codi:" + searchCodi + ", Nom:" + searchNom);
			
			// Crear filtro de usuarios aplicando solo los campos con valor
			FiltreUsuaris filtre = new FiltreUsuaris();
			if (searchNif != null && !searchNif.trim().isEmpty()) {
				filtre.setNif(searchNif.trim());
			}
			if (searchCodi != null && !searchCodi.trim().isEmpty()) {
				filtre.setCodi(searchCodi.trim());
			}
			if (searchNom != null && !searchNom.trim().isEmpty()) {
				filtre.setNom(searchNom.trim());
			}
			
			// Buscar en Pinbal usando el filtro con paginación
			Page<UsuariEntitat> usuariPage = usuariClient.getUsuaris(entitatCodi, filtre, page, size, sort);
			
			if (usuariPage != null && usuariPage.getContent() != null && !usuariPage.getContent().isEmpty()) {
				// Encontrados usuarios en Pinbal
				log.info("Total usuarios encontrados en Pinbal: " + usuariPage.getTotalElements());
				return usuariPage;
			}
			
			// Si no se encuentra en Pinbal, intentar buscar en LDAP/UserInfo
			log.info("No encontrado en Pinbal, buscando en LDAP/UserInfo...");
			
			final boolean debug = true;
			IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);
			
			// Buscar usuarios por los campos especificados en el sistema de autenticación
			// Usar el primer valor no nulo como criterio de búsqueda
			String searchValue = searchNif != null ? searchNif : (searchCodi != null ? searchCodi : searchNom);
			SearchUsersResult searchResult = plugin.getUsersByPartialValuesOr(searchValue, searchValue, searchValue, searchValue, searchValue);
			List<UserInfo> usuarisInfo = searchResult.getUsers();

			if (usuarisInfo != null && !usuarisInfo.isEmpty()) {
				log.info("Usuarios encontrados en LDAP: " + usuarisInfo.size());
				
				// Buscar cada usuario en Pinbal por su código
				List<UsuariEntitat> usuarisEncontrados = new ArrayList<>();
				for (UserInfo userInfo : usuarisInfo) {
					String usuariCodi = userInfo.getUsername();
					try {
						UsuariEntitat usuari = usuariClient.getUsuari(usuariCodi, entitatCodi);
						if (usuari != null) {
							usuarisEncontrados.add(usuari);
							log.info("Usuario encontrado en Pinbal: " + usuari.getNom());
						}
					} catch (Exception e) {
						log.warn("Error al buscar usuario " + usuariCodi + " en Pinbal: " + e.getMessage());
					}
				}
				
				if (!usuarisEncontrados.isEmpty()) {
					// Crear una página con los resultados encontrados
					// Nota: como no tenemos paginación real aquí, devolvemos todos los resultados
					log.info("Total usuarios encontrados vía LDAP: " + usuarisEncontrados.size());
					return crearPageDesdeList(usuarisEncontrados);
				}
			} else {
				log.warn("No se encontró ningún usuario con los criterios especificados");
			}
			
		} catch (Exception e) {
			log.error("Error buscando usuarios por NIF", e);
		}
		
		return null;
	}
	
	/**
	 * Crea un objeto Page a partir de una lista de usuarios
	 */
	private Page<UsuariEntitat> crearPageDesdeList(List<UsuariEntitat> usuaris) {
		Page<UsuariEntitat> page = new Page<>();
		page.setContent(usuaris);
		page.setNumber(0);
		page.setTotalElements(usuaris.size());
		page.setTotalPages(1);
		return page;
	}
	
	/**
	 * Consulta de permisos en PINFOs: permite buscar qué usuarios, procedimientos y servicios
	 * tienen permisos asignados en documentos PINFO. Solo muestra	 elementos con permisos activos.
	 */
	// Clase interna para JSON
	static class Item {
		public String id;
		public String key;
		public String value;
		
		public Item(String id, String key, String value) {
			this.id = id;
			this.key = key;
			this.value = value;
		}
	}
	
	@RequestMapping(value = "/jsonUsuaris", method = RequestMethod.GET)
	public void obtenirJsonUsuaris(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("nom");
		log.info("Buscando usuarios: " + search);
		
		try {
			List<Item> items = new ArrayList<>();
			
			// Si la búsqueda es muy corta, no devolver nada
			if (search == null || search.trim().length() < 3) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("[]");
				response.getWriter().flush();
				return;
			}
			
			// Buscar en LDAP con asteriscos
			IUserInformationPlugin plugin = getPluginUserInfo();
			String searchTerm = "*" + search.trim() + "*";
			SearchUsersResult result = plugin.getUsersByPartialValuesOr(searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
			
			if (result != null && result.getUsers() != null) {
				List<UserInfo> users = result.getUsers();
				log.info("LDAP devuelve " + users.size() + " usuarios");
				
				// Limitar a 500 resultados
				if (users.size() > 500) {
					users = users.subList(0, 500);
				}
				
				// Convertir a Items con formato: "Nombre Completo - NIF - ID"
				for (UserInfo user : users) {
					String adminId = user.getAdministrationID() != null ? user.getAdministrationID() : user.getId();
					String nombre = user.getName() != null ? user.getName() : "";
					String apellido1 = user.getSurname1() != null ? user.getSurname1() : "";
					String apellido2 = user.getSurname2() != null ? user.getSurname2() : "";
					
					String nombreCompleto = (nombre + " " + apellido1 + " " + apellido2).trim();
					String nif = user.getAdministrationID() != null ? user.getAdministrationID() : "";
					String username = user.getUsername() != null ? user.getUsername() : "";
					
					// Formato: "Antonio Trobat Obrador - 43120476F - 43120476F"
					String displayValue = username + " - " + nombreCompleto + " - " + nif ;
					
					// El ID que se usará para buscar en PinfoData es el administrationID
					Item item = new Item(adminId, username, displayValue);
					items.add(item);
				}
			}
			
			log.info("Devolviendo " + items.size() + " usuarios");
			
			// Devolver JSON
			Gson g = new Gson();
			String usuarisJson = g.toJson(items);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(usuarisJson);
			response.getWriter().flush();
		} catch (Exception e) {
			log.error("Error buscando usuarios: " + e.getMessage(), e);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("[]");
			response.getWriter().flush();
		}
	}
	
	@RequestMapping(value = "/jsonProcediments", method = RequestMethod.GET)
	public void obtenirJsonProcediments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("query");
		log.info("Buscando procedimientos con PINFOs: " + search);
		
		try {
			// Obtener todos los procedimientos con PINFOs desde caché
			List<Solicitud> todosProcediments = obtenirProcedimentsAmbPinfosDeCache();
			
			// Filtrar en memoria según el texto buscado
			List<Solicitud> filtered = new ArrayList<>();
			if (search != null && search.trim().length() >= 2) {
				String searchLower = search.trim().toLowerCase();
				for (Solicitud soli : todosProcediments) {
					if (coincideixProcedimentAmbCerca(soli, searchLower)) {
						filtered.add(soli);
					}
				}
			} else if (search == null || search.trim().length() < 2) {
				filtered = new ArrayList<>();
			}
			
			// Limitar resultados
			if (filtered.size() > 500) {
				filtered = filtered.subList(0, 500);
			}
			
			log.info("Procedimientos en caché: " + todosProcediments.size() + ", filtrados: " + filtered.size());
			
			// Convertir a Items
			List<Item> items = new ArrayList<>();
			for (Solicitud soli : filtered) {
				String id = String.valueOf(soli.getSolicitudID());
				String key = soli.getProcedimentCodi();
				String value = soli.getProcedimentNom();
				Item item = new Item(id, key, value);
				items.add(item);
			}
			
			// Devolver JSON
			Gson g = new Gson();
			String procedimentsJson = g.toJson(items);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(procedimentsJson);
			response.getWriter().flush();
		} catch (Exception e) {
			log.error("Error obteniendo procedimientos: " + e.getMessage(), e);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("[]");
			response.getWriter().flush();
		}
	}
	
	@RequestMapping(value = "/jsonServeis", method = RequestMethod.GET)
	public void obtenirJsonServeis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("query");
		log.info("Buscando servicios con PINFOs: " + search);
		
		try {
			// Obtener todos los servicios con PINFOs desde caché
			List<Servei> todosServeis = obtenirServeisAmbPinfosDeCache();
			
			// Filtrar en memoria según el texto buscado
			List<Servei> filtered = new ArrayList<>();
			if (search != null && search.trim().length() >= 2) {
				String searchLower = search.trim().toLowerCase();
				for (Servei serv : todosServeis) {
					if (coincideixServeiAmbCerca(serv, searchLower)) {
						filtered.add(serv);
					}
				}
			} else if (search == null || search.trim().length() < 2) {
				filtered = new ArrayList<>();
			}
			
			// Limitar resultados
			if (filtered.size() > 500) {
				filtered = filtered.subList(0, 500);
			}
			
			log.info("Servicios en caché: " + todosServeis.size() + ", filtrados: " + filtered.size());
			
			// Convertir a Items
			List<Item> items = new ArrayList<>();
			for (Servei serv : filtered) {
				String id = String.valueOf(serv.getServeiID());
				String key = serv.getCodi();
				String value = serv.getNom();
				Item item = new Item(id, key, value);
				items.add(item);
			}
			
			// Devolver JSON
			Gson g = new Gson();
			String serveisJson = g.toJson(items);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(serveisJson);
			response.getWriter().flush();
		} catch (Exception e) {
			log.error("Error obteniendo servicios: " + e.getMessage(), e);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("[]");
			response.getWriter().flush();
		}
	}
	
	private IUserInformationPlugin getPluginUserInfo() throws Exception {
		if (cachedPlugin == null) {
			synchronized (DadesPinbalController.class) {
				if (cachedPlugin == null) {
					final boolean debug = true;
					log.info("Inicializando plugin LDAP (solo una vez)...");
					cachedPlugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);
				}
			}
		}
		return cachedPlugin;
	}
	
	/**
	 * Regenera las cachés de procedimientos y servicios.
	 */
	private synchronized void regenerarTotesCaches() throws Exception {
		long now = System.currentTimeMillis();
		
		// Solo regenerar si ha expirado
		if (cachedProcedimentsAmbPinfos != null && cachedServeisAmbPinfos != null && 
		    (now - cacheTimestamp) <= CACHE_TTL_MS) {
			return; // Caché aún válida
		}
		
		log.info("Regenerando cachés de procedimientos y servicios...");
		
		// 1. Obtener TODOS los PinfoData en una sola query
		Where wPinfoDatasTramitats = null;
		List<PinfoData> allPinfoDatas = pinfoDataLogicaEjb.select(wPinfoDatasTramitats);
		
		// 2. Extraer IDs únicos de procedimientos y servicios
		Set<Long> procedimentIds = new HashSet<>();
		Set<Long> serveiIds = new HashSet<>();
		
		for (PinfoData pd : allPinfoDatas) {
			if (pd.getProcedimentID() != null) {
				procedimentIds.add(pd.getProcedimentID());
			}
			if (pd.getServeiID() != null) {
				serveiIds.add(pd.getServeiID());
			}
		}
		
		// 3. Regenerar caché de procedimientos desde BD en BATCH (1 query con IN)
		Map<Long, Solicitud> newProcedimentsCache = new HashMap<>();
		if (!procedimentIds.isEmpty()) {
			Where whereProcediments = Where.AND(
				SolicitudFields.SOLICITUDID.in(procedimentIds.toArray(new Long[0])),
				SolicitudFields.NIF.equal("S0711001H")
			);
			List<Solicitud> solicituds = solicitudLogicaEjb.select(whereProcediments);
			for (Solicitud s : solicituds) {
				newProcedimentsCache.put(s.getSolicitudID(), s);
			}
		}
		
		// 4. Regenerar caché de servicios desde BD en BATCH (1 query con IN)
		Map<Long, Servei> newServeisCache = new HashMap<>();
		if (!serveiIds.isEmpty()) {
			Where whereServeis = ServeiFields.SERVEIID.in(serveiIds.toArray(new Long[0]));
			List<Servei> serveis = serveiLogicaEjb.select(whereServeis);
			for (Servei s : serveis) {
				newServeisCache.put(s.getServeiID(), s);
			}
		}
		
		// 5. Actualizar cachés y timestamp
		cachedProcedimentsAmbPinfos = newProcedimentsCache;
		cachedServeisAmbPinfos = newServeisCache;
		cacheTimestamp = now;
		
		log.info("Cachés regeneradas - Procedimientos: " + cachedProcedimentsAmbPinfos.size() + 
		         ", Servicios: " + cachedServeisAmbPinfos.size());
	}
	
	/**
	 * Obtiene la lista de procedimientos con PINFOs desde caché.
	 * La caché se regenera automáticamente cada 5 minutos.
	 */
	private List<Solicitud> obtenirProcedimentsAmbPinfosDeCache() throws Exception {
		regenerarTotesCaches();
		return cachedProcedimentsAmbPinfos != null ? new ArrayList<>(cachedProcedimentsAmbPinfos.values()) : new ArrayList<>();
	}
	
	/**
	 * Verifica si un procedimiento coincide con el criterio de búsqueda.
	 * Busca en: código y nombre de procedimiento.
	 */
	private boolean coincideixProcedimentAmbCerca(Solicitud soli, String searchLower) {
		if (soli == null || searchLower == null) {
			return false;
		}
		
		// Buscar en código
		if (soli.getProcedimentCodi() != null && soli.getProcedimentCodi().toLowerCase().contains(searchLower)) {
			return true;
		}
		
		// Buscar en nombre
		if (soli.getProcedimentNom() != null && soli.getProcedimentNom().toLowerCase().contains(searchLower)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Obtiene la lista de servicios con PINFOs desde caché.
	 * La caché se regenera automáticamente cada 5 minutos.
	 */
	private List<Servei> obtenirServeisAmbPinfosDeCache() throws Exception {
		regenerarTotesCaches();
		return cachedServeisAmbPinfos != null ? new ArrayList<>(cachedServeisAmbPinfos.values()) : new ArrayList<>();
	}
	
	/**
	 * Verifica si un servicio coincide con el criterio de búsqueda.
	 * Busca en: código y nombre de servicio.
	 */
	private boolean coincideixServeiAmbCerca(Servei serv, String searchLower) {
		if (serv == null || searchLower == null) {
			return false;
		}
		
		// Buscar en código
		if (serv.getCodi() != null && serv.getCodi().toLowerCase().contains(searchLower)) {
			return true;
		}
		
		// Buscar en nombre
		if (serv.getNom() != null && serv.getNom().toLowerCase().contains(searchLower)) {
			return true;
		}
		
		return false;
	}
	
	// private List<UserInfo> getUsuarisParam(String entrada) throws Exception {
	// 	if (entrada == null || entrada.trim().length() < 3) {
	// 		return new ArrayList<UserInfo>();
	// 	}
		
	// 	try {
	// 		IUserInformationPlugin plugin = getPluginUserInfo();
	// 		SearchUsersResult result = plugin.searchUsersByPartialNifNomLlinatges(entrada.trim());
			
	// 		if (result != null && result.getUsers() != null) {
	// 			List<UserInfo> users = result.getUsers();
	// 			if (users.size() > 500) {
	// 				return null; // Demasiados resultados
	// 			}
	// 			return users;
	// 		}
	// 		return new ArrayList<UserInfo>();
	// 	} catch (Exception e) {
	// 		log.error("Error buscando usuarios: " + e.getMessage(), e);
	// 		return new ArrayList<UserInfo>();
	// 	}
	// }
	
	@RequestMapping(value = "/buscadorPinfo", method = RequestMethod.GET)
    public ModelAndView buscadorPinfo(
            String searchUsuari,
            String searchProcediment,
            String searchServei) {

        ModelAndView mav = new ModelAndView("buscadorPinfo");

		mav.addObject("searchUsuari", searchUsuari);
		mav.addObject("searchProcediment", searchProcediment);
		mav.addObject("searchServei", searchServei);
		
		// Solo buscar si hay algún criterio
		boolean hasSearch = (searchUsuari != null && !searchUsuari.trim().isEmpty()) ||
		                     (searchProcediment != null && !searchProcediment.trim().isEmpty()) ||
		                     (searchServei != null && !searchServei.trim().isEmpty());
		
		if (hasSearch) {
			try {
				Where whereProcediment = null;
				Where whereServei = null;
				Where whereUsuari = null;
				
				// 1. Buscar procedimientos (Solicitudes) si se especifica
				if (searchProcediment != null && !searchProcediment.trim().isEmpty()) {
					String searchText = searchProcediment.trim();
					List<Long> procedimentIDs = new ArrayList<>();
					
					// Intentar buscar por ID numérico
					try {
						Long id = Long.parseLong(searchText);
						Solicitud sol = solicitudLogicaEjb.findByPrimaryKey(id);
						if (sol != null) {
							procedimentIDs.add(id);
						}
					} catch (NumberFormatException e) {
						// No es un ID, buscar por código o nombre
					}
					
					// Buscar por código o nombre
					Where whereSearchSol = Where.OR(
							SolicitudFields.PROCEDIMENTCODI.like("%" + searchText + "%"),
							SolicitudFields.PROCEDIMENTNOM.like("%" + searchText + "%")
					);
					List<Solicitud> solicituds = solicitudLogicaEjb.select(whereSearchSol);
					for (Solicitud sol : solicituds) {
						if (!procedimentIDs.contains(sol.getSolicitudID())) {
							procedimentIDs.add(sol.getSolicitudID());
						}
					}
					
					// Construir Where con los IDs encontrados
					if (!procedimentIDs.isEmpty()) {
						if (procedimentIDs.size() == 1) {
							whereProcediment = PinfoDataFields.PROCEDIMENTID.equal(procedimentIDs.get(0));
						} else {
							whereProcediment = PinfoDataFields.PROCEDIMENTID.in(procedimentIDs.toArray(new Long[0]));
						}
					}
				}
				
				// 2. Buscar servicios si se especifica
				if (searchServei != null && !searchServei.trim().isEmpty()) {
					String searchText = searchServei.trim();
					List<Long> serveiIDs = new ArrayList<>();
					
					// Intentar buscar por ID numérico
					try {
						Long id = Long.parseLong(searchText);
						Servei serv = serveiLogicaEjb.findByPrimaryKey(id);
						if (serv != null) {
							serveiIDs.add(id);
						}
					} catch (NumberFormatException e) {
						// No es un ID, buscar por código o nombre
					}
					
					// Buscar por código o nombre
					Where whereSearchServ = Where.OR(
							ServeiFields.CODI.like("%" + searchText + "%"),
							ServeiFields.NOM.like("%" + searchText + "%")
					);
					List<Servei> serveis = serveiLogicaEjb.select(whereSearchServ);
					for (Servei serv : serveis) {
						if (!serveiIDs.contains(serv.getServeiID())) {
							serveiIDs.add(serv.getServeiID());
						}
					}
					
					// Construir Where con los IDs encontrados
					if (!serveiIDs.isEmpty()) {
						if (serveiIDs.size() == 1) {
							whereServei = PinfoDataFields.SERVEIID.equal(serveiIDs.get(0));
						} else {
							whereServei = PinfoDataFields.SERVEIID.in(serveiIDs.toArray(new Long[0]));
						}
					}
				}
				
				// 3. Buscar por usuario si se especifica
				if (searchUsuari != null && !searchUsuari.trim().isEmpty()) {
					whereUsuari = PinfoDataFields.USUARIID.like("%" + searchUsuari.trim() + "%");
				}
				
				// 4. Combinar todos los Where (aprovechando que null se ignora)
				Where whereFinal = whereProcediment;
				if (whereServei != null) {
					whereFinal = (whereFinal == null) ? whereServei : Where.AND(whereFinal, whereServei);
				}
				if (whereUsuari != null) {
					whereFinal = (whereFinal == null) ? whereUsuari : Where.AND(whereFinal, whereUsuari);
				}
				
				// 5. Buscar PinfoData y obtener PINFOs únicos
				if (whereFinal != null) {
					OrderBy orderBy = new OrderBy(PinfoDataFields.PINFOID, OrderType.DESC);
					List<PinfoData> pinfoDataList = pinfoDataLogicaEjb.select(whereFinal, orderBy);
					
					if (pinfoDataList != null && !pinfoDataList.isEmpty()) {
						// Obtener IDs únicos de PINFOs
						Set<Long> pinfoIDs = new LinkedHashSet<>();
						for (PinfoData pd : pinfoDataList) {
							if (pd.getPinfoID() != null) {
								pinfoIDs.add(pd.getPinfoID());
							}
						}
						
						// OPTIMIZACIÓN: Caché de usuarios LDAP para evitar consultas repetidas
						Map<String, UserInfo> userCache = new HashMap<>();
						IUserInformationPlugin plugin = getPluginUserInfo();
						
						// Obtener los PINFOs completos con información de usuarios
						List<PinfoWithUserInfo> pinfos = new ArrayList<>();
						for (Long pinfoID : pinfoIDs) {
							Pinfo pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
							if (pinfo != null) {
								PinfoWithUserInfo pwu = new PinfoWithUserInfo();
								pwu.pinfo = pinfo;
								
								// Obtener nombre del solicitante (solo si no está en caché)
								if (pinfo.getSolicitantNIF() != null && !pinfo.getSolicitantNIF().isEmpty()) {
									String nifSolicitant = pinfo.getSolicitantNIF().trim();
									UserInfo user = userCache.get(nifSolicitant);
									
									if (user == null) {
										// No está en caché, consultar LDAP
										try {
											user = plugin.getUserInfoByAdministrationID(nifSolicitant);
											if (user != null) {
												userCache.put(nifSolicitant, user);
											}
										} catch (Exception e) {
											log.warn("No se pudo obtener info del solicitante " + nifSolicitant + ": " + e.getMessage());
										}
									}
									
									if (user != null) {
										pwu.solicitantNom = (user.getName() != null ? user.getName() : "") + " " + 
											(user.getSurname1() != null ? user.getSurname1() : "") + " " + 
											(user.getSurname2() != null ? user.getSurname2() : "");
										pwu.solicitantNom = pwu.solicitantNom.trim();
									}
								}
								
								// Obtener nombre del destinatario (solo si no viene en el Pinfo)
								if (pinfo.getDestinatariNIF() != null && !pinfo.getDestinatariNIF().isEmpty()) {
									// Si ya tiene nombre en el Pinfo, usarlo
									if (pinfo.getDestinatariNom() != null && !pinfo.getDestinatariNom().trim().isEmpty()) {
										pwu.destinatariNom = pinfo.getDestinatariNom();
									} else {
										// No tiene nombre, buscar en LDAP (con caché)
										String nifDestinatari = pinfo.getDestinatariNIF().trim();
										UserInfo user = userCache.get(nifDestinatari);
										
										if (user == null) {
											// No está en caché, consultar LDAP
											try {
												user = plugin.getUserInfoByAdministrationID(nifDestinatari);
												if (user != null) {
													userCache.put(nifDestinatari, user);
												}
											} catch (Exception e) {
												log.warn("No se pudo obtener info del destinatario " + nifDestinatari + ": " + e.getMessage());
											}
										}
										
										if (user != null) {
											pwu.destinatariNom = (user.getName() != null ? user.getName() : "") + " " + 
												(user.getSurname1() != null ? user.getSurname1() : "") + " " + 
												(user.getSurname2() != null ? user.getSurname2() : "");
											pwu.destinatariNom = pwu.destinatariNom.trim();
										}
									}
								}
								
								pinfos.add(pwu);
							}
						}
						
						log.info("PINFOs encontrados: " + pinfos.size() + ", consultas LDAP: " + userCache.size());
						mav.addObject("pinfos", pinfos);
					}
				} else {
					// No se encontraron procedimientos o servicios con ese criterio
					mav.addObject("info", "No se encontraron resultados con los criterios especificados");
				}
				
			} catch (Exception e) {
				log.error("Error buscando PINFOs", e);
				mav.addObject("error", "Error: " + e.getMessage());
			}
		}
		
		return mav;
	}
	
	// Clase auxiliar para pasar información adicional a la vista
    public static class PinfoWithUserInfo {
    	public Pinfo pinfo;
    	public String solicitantNom;
    	public String destinatariNom;
    	
    	public Pinfo getPinfo() {
    		return pinfo;
    	}
    	
    	public String getSolicitantNom() {
    		return solicitantNom != null ? solicitantNom : "";
    	}
    	
    	public String getDestinatariNom() {
    		// Si tenemos el nombre enriquecido, usarlo; si no, usar el del pinfo
    		if (destinatariNom != null && !destinatariNom.isEmpty()) {
    			return destinatariNom;
    		}
    		return pinfo.getDestinatariNom() != null ? pinfo.getDestinatariNom() : "";
    	}
    	
    	// Métodos delegados para acceso directo desde JSP
    	public Long getPinfoID() { return pinfo.getPinfoID(); }
    	public String getEntitat() { return pinfo.getEntitat(); }
    	public String getSolicitantNIF() { return pinfo.getSolicitantNIF(); }
    	public String getDestinatariNIF() { return pinfo.getDestinatariNIF(); }
    	public Long getEstat() { return pinfo.getEstat(); }
    	public Long getFitxerfirmatID() { return pinfo.getFitxerfirmatID(); }
    	public Long getFitxerID() { return pinfo.getFitxerID(); }
    }

}

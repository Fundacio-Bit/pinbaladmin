package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
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

}

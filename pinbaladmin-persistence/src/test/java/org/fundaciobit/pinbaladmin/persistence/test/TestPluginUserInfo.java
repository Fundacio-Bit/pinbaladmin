package org.fundaciobit.pinbaladmin.persistence.test;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import org.fundaciobit.pinbaladmin.persistence.PinbalAdminJPADaoManagers;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.PinbalAdminDaoManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.SearchUsersResult;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;

/*
 * IMPORTANT - NO MODIFICAR - DERIVA AQUESTA CLASSE SI VOLS FER UN TEST 
 * IMPORTANT - DO NOT MODIFY - EXTENDS THIS CLASS IF YOU WANT DO A TEST
 *
 */

/**
 * Test completo para explorar todos los métodos del IUserInformationPlugin
 * Permite probar diferentes parámetros de búsqueda y ver qué métodos son más
 * útiles
 * 
 * @author anadal
 */
public class TestPluginUserInfo {

	public static final Logger log = Logger.getLogger(TestPluginUserInfo.class);

	public static final void main(String[] args) {

		TestPluginUserInfo test = new TestPluginUserInfo();

		try {
			log.info(">>>>>>>>>>>>  Iniciando Test Completo de PluginUserInfo!");

			test.test();

			log.info("<<<<<<<<<<<  Test Completado!");
			System.exit(0);
		} catch (Exception e) {
			log.error("Error en el test: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void test() {
		log.info("=== INICIANDO TEST COMPLETO DE IUserInformationPlugin ===");

		try {
			// Configurar plugin directamente
			log.info("Intentando crear plugin de User Information...");
			IUserInformationPlugin plugin = getUserInformationPluginInstance();

			log.info("Plugin obtenido: " + plugin.getClass().getName());
			log.info("");

			// Parámetros de prueba - MODIFICA ESTOS VALORES SEGÚN TUS NECESIDADES
			String searchParam = "admin"; // Cambia por el nombre/usuario que quieras buscar
			String searchNIF = "12345678"; // Cambia por parte de un NIF que buscar
			String searchUsername = "admin"; // Username específico
			String searchRol = "PFI_USER"; // Rol a buscar

			log.info("=== PARÁMETROS DE BÚSQUEDA ===");
			log.info("searchParam: " + searchParam);
			log.info("searchNIF: " + searchNIF);
			log.info("searchUsername: " + searchUsername);
			log.info("searchRol: " + searchRol);
			log.info("");

			// TEST 1: Método principal - getUsersByPartialValuesOr
			testGetUsersByPartialValuesOr(plugin, searchParam, searchNIF);

			// TEST 2: Búsqueda por username específico
			testGetUserInfoByUserName(plugin, searchUsername);

			// TEST 3: Búsqueda por NIF/AdministrationID específico
			testGetUserInfoByAdministrationID(plugin, searchNIF);

			// TEST 4: Búsqueda por rol
			testGetUserInfoByRol(plugin, searchRol);

			// TEST 5: Otros métodos de búsqueda parcial
			testOtherSearchMethods(plugin, searchParam, searchNIF);

		} catch (Exception e) {
			log.error("Error durante el test: " + e.getMessage(), e);
		}
	}

	/**
	 * Prueba el método principal getUsersByPartialValuesOr Este es el método más
	 * útil para búsquedas generales
	 */
	private void testGetUsersByPartialValuesOr(IUserInformationPlugin plugin, String searchParam, String searchNIF) {
		try {
			log.info("=== TEST 1: getUsersByPartialValuesOr ===");
			log.info("Método PRINCIPAL - busca en múltiples campos con OR");

			// Preparar parámetros
			String username = searchParam;
			String nombre = searchParam;
			String apellidos = searchParam;
			String email = null; // Lo dejamos null normalmente
			String nif = searchNIF;

			log.info("Llamando: getUsersByPartialValuesOr(username='" + username + "', nombre='" + nombre
					+ "', apellidos='" + apellidos + "', email=" + email + ", nif='" + nif + "')");

			SearchUsersResult resultado = plugin.getUsersByPartialValuesOr(username, nombre, apellidos, email, nif);

			if (resultado != null && resultado.getUsers() != null) {
				List<UserInfo> usuarios = resultado.getUsers();
				log.info("✓ ENCONTRADOS " + usuarios.size() + " usuarios");

				for (int i = 0; i < Math.min(5, usuarios.size()); i++) { // Mostrar máximo 5
					UserInfo user = usuarios.get(i);
					log.info("  Usuario " + (i + 1) + ":");
					log.info("    - Username: " + user.getUsername());
					log.info("    - Nombre completo: " + user.getFullName());
					log.info("    - NIF: " + user.getAdministrationID());
					log.info("    - Email: " + user.getEmail());
					log.info("    - Teléfono: " + user.getPhoneNumber());
				}

				if (usuarios.size() > 5) {
					log.info("  ... y " + (usuarios.size() - 5) + " usuarios más");
				}
			} else {
				log.info("✗ No se encontraron usuarios");
			}

		} catch (Exception e) {
			log.error("✗ Error en getUsersByPartialValuesOr: " + e.getMessage());
		}
		log.info("");
	}

	/**
	 * Prueba búsqueda por username exacto
	 */
	private void testGetUserInfoByUserName(IUserInformationPlugin plugin, String searchUsername) {
		try {
			log.info("=== TEST 2: getUserInfoByUserName ===");
			log.info("Busca por username EXACTO");
			log.info("Llamando: getUserInfoByUserName('" + searchUsername + "')");

			UserInfo usuario = plugin.getUserInfoByUserName(searchUsername);

			if (usuario != null) {
				log.info("✓ Usuario encontrado:");
				mostrarDetallesUsuario(usuario);
			} else {
				log.info("✗ Usuario no encontrado con username: " + searchUsername);
			}

		} catch (Exception e) {
			log.error("✗ Error en getUserInfoByUserName: " + e.getMessage());
		}
		log.info("");
	}

	/**
	 * Prueba búsqueda por NIF/AdministrationID exacto
	 */
	private void testGetUserInfoByAdministrationID(IUserInformationPlugin plugin, String searchNIF) {
		try {
			log.info("=== TEST 3: getUserInfoByAdministrationID ===");
			log.info("Busca por NIF/AdministrationID EXACTO");
			log.info("Llamando: getUserInfoByAdministrationID('" + searchNIF + "')");

			UserInfo usuario = plugin.getUserInfoByAdministrationID(searchNIF);

			if (usuario != null) {
				log.info("✓ Usuario encontrado:");
				mostrarDetallesUsuario(usuario);
			} else {
				log.info("✗ Usuario no encontrado con NIF: " + searchNIF);
			}

		} catch (Exception e) {
			log.error("✗ Error en getUserInfoByAdministrationID: " + e.getMessage());
		}
		log.info("");
	}

	/**
	 * Prueba búsqueda por rol
	 */
	private void testGetUserInfoByRol(IUserInformationPlugin plugin, String searchRol) {
		try {
			log.info("=== TEST 4: getUserInfoByRol ===");
			log.info("Busca todos los usuarios con un rol específico");
			log.info("Llamando: getUserInfoByRol('" + searchRol + "')");

			UserInfo[] usuarios = plugin.getUserInfoByRol(searchRol);

			if (usuarios != null && usuarios.length > 0) {
				log.info("✓ ENCONTRADOS " + usuarios.length + " usuarios con rol '" + searchRol + "'");

				for (int i = 0; i < Math.min(3, usuarios.length); i++) { // Mostrar máximo 3
					UserInfo user = usuarios[i];
					log.info("  Usuario " + (i + 1) + ": " + user.getUsername() + " - " + user.getFullName());
				}

				if (usuarios.length > 3) {
					log.info("  ... y " + (usuarios.length - 3) + " usuarios más");
				}
			} else {
				log.info("✗ No se encontraron usuarios con rol: " + searchRol);
			}

		} catch (Exception e) {
			log.error("✗ Error en getUserInfoByRol: " + e.getMessage());
		}
		log.info("");
	}

	/**
	 * Prueba otros métodos de búsqueda parcial disponibles
	 */
	private void testOtherSearchMethods(IUserInformationPlugin plugin, String searchParam, String searchNIF) {
		log.info("=== TEST 5: Otros métodos de búsqueda ===");

		// Test getUsersByPartialUserName
		try {
			log.info("Probando: getUsersByPartialUserName('" + searchParam + "')");
			SearchUsersResult resultado = plugin.getUsersByPartialUserName(searchParam);

			if (resultado != null && resultado.getUsers() != null) {
				log.info("✓ Encontrados " + resultado.getUsers().size() + " usuarios por username parcial");
			} else {
				log.info("✗ No hay resultados por username parcial");
			}
		} catch (Exception e) {
			log.error("✗ Error en getUsersByPartialUserName: " + e.getMessage());
		}

		// Test getUsersByPartialAdministrationID
		try {
			log.info("Probando: getUsersByPartialAdministrationID('" + searchNIF + "')");
			SearchUsersResult resultado = plugin.getUsersByPartialAdministrationID(searchNIF);

			if (resultado != null && resultado.getUsers() != null) {
				log.info("✓ Encontrados " + resultado.getUsers().size() + " usuarios por NIF parcial");
			} else {
				log.info("✗ No hay resultados por NIF parcial");
			}
		} catch (Exception e) {
			log.error("✗ Error en getUsersByPartialAdministrationID: " + e.getMessage());
		}

		log.info("");
	}

	/**
	 * Muestra todos los detalles disponibles de un usuario
	 */
	private void mostrarDetallesUsuario(UserInfo user) {
		log.info("    - Username: " + user.getUsername());
		log.info("    - Nombre completo: " + user.getFullName());
		log.info("    - Nombre: " + user.getName());
		log.info("    - Apellido 1: " + user.getSurname1());
		log.info("    - Apellido 2: " + user.getSurname2());
		log.info("    - NIF/ID: " + user.getAdministrationID());
		log.info("    - Email: " + user.getEmail());
		log.info("    - Teléfono: " + user.getPhoneNumber());
		log.info("    - Dirección: " + user.getAddress());
		log.info("    - Empresa: " + user.getCompany());
		log.info("    - Área empresa: " + user.getCompanyArea());
		log.info("    - Departamento: " + user.getCompanyDepartment());
		log.info("    - DIR3: " + user.getDir3());
		log.info("    - ID interno: " + user.getId());
		log.info("    - Notas: " + user.getNotes());
		log.info("    - Fecha nacimiento: " + user.getBirthDate());
		log.info("    - Fecha creación: " + user.getCreationDate());
		log.info("    - Género: " + user.getGender());
	}

	public static IUserInformationPlugin userInfoPluginCaib = null;
	public static IUserInformationPlugin userInfoPluginOtae = null;
	public static final String USERINFO_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "userinformationplugin";

	public IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {

		boolean debug = true;
		boolean caib = true;

		log.info("Plugin UserInformation: caib=" + caib);
		IUserInformationPlugin userInfoPlugin = userInfoPluginCaib;

		log.info("userInfoPlugin inicial: " + userInfoPlugin);

		if (userInfoPlugin == null) {
//            final String propertyPlugin = LOGIN_PLUGIN_KEY;

			Properties propTmp = getProperties();

			if (debug) {
				log.info("Propietats de sistema i fitxer de configuració:");
				Set<Object> set = propTmp.keySet();
				for (Object object : set) {
					String key = (String) object;
					String value = propTmp.getProperty(key);
					log.info(key + ": " + value);
				}
			}

			String className = propTmp.getProperty(USERINFO_PLUGIN_KEY + (caib ? ".caib" : ".otae"));

			log.info("className: " + className);
			Object pluginInstance = PluginsManager.instancePluginByClassName(className,
					Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

//            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

			if (pluginInstance == null) {
				throw new I18NException("plugin.donotinstantiateplugin.userinfo");
			}
			if (caib) {
				userInfoPluginCaib = (IUserInformationPlugin) pluginInstance;
				userInfoPlugin = userInfoPluginCaib;
			} else {
				userInfoPluginOtae = (IUserInformationPlugin) pluginInstance;
				userInfoPlugin = userInfoPluginOtae;
			}
		} else {
			log.info("userInfoPlugin ja existeix. " + userInfoPlugin.getClass().getName());
		}
		return userInfoPlugin;
	}
	
	public Properties getProperties() {
		
		String propStr = "\r\n"
				+ "org.fundaciobit.pinbaladmin.userinformationplugin.caib=org.fundaciobit.pluginsib.userinformation.ldap.LdapUserInformationPlugin\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.host_url=ldap\\://sdesauthlin1.caib.es\\:389\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.security_principal=cn=lectorenviafib,dc=caib,dc=es\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.security_authentication=simple\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.security_credentials=fib$2803\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.users_context_dn=dc\\=caib,dc\\=es\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.search_scope=subtree\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.search_filter=\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.username=cn\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.mail=mail\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.administration_id=nif\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.name=givenName\r\n"
				+ "#Has de triar:\r\n"
				+ "#      - \"surname1\" i \"surname2\"\r\n"
				+ "#      - \"surname\"\r\n"
				+ "# org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.surname=sn\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.surname1=sn1\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.surname2=sn2\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.department=departmentNumber\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.telephone=\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.attribute.memberof=memberOf\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.prefix_role_match_memberof=cn=\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.userinformation.ldap.suffix_role_match_memberof=,dc=caib,dc=es\r\n"
				+ "";
		
		Properties props = new Properties();
		try {
			props.load(new java.io.StringReader(propStr));

		} catch (Exception e) {
			log.error("Error carregant propietats de test: " + e.getMessage(), e);
		}
		
		return props;
		
	}
}

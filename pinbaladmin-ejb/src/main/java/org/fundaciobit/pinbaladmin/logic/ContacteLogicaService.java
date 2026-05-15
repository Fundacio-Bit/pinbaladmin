package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.ejb.ContacteService;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ContacteLogicaService extends ContacteService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/ContacteLogicaEJB!org.fundaciobit.pinbaladmin.logic.ContacteLogicaService";

	Contacte crearContacteTitular(Solicitud soli);

	/**
	 * Busca un contacto exacto con todos los campos. Si no existe, lo crea.
	 * Todos los campos deben coincidir exactamente (incluyendo nulls).
	 * 
	 * @param nif NIF del contacto
	 * @param nom Nombre
	 * @param llinatge1 Primer apellido
	 * @param llinatge2 Segundo apellido
	 * @param carrec Cargo
	 * @param telefon Teléfono
	 * @param mail Email
	 * @return El contacto encontrado o creado
	 */
	Contacte buscarOCrearContacte(String nif, String nom, String llinatge1, String llinatge2, String carrec, String telefon, String mail, String username);

}

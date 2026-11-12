package org.fundaciobit.pinbaladmin.logic.dto;

import org.fundaciobit.pinbaladmin.model.entity.Contacte;

/**
 * 
 * @author anadal (u80067)
 * 14 ago 2026 10:22:35
 */
public class ContactePortaFIB {

    public static final boolean ENVIAR_COM_USUARI_EXTERN = true; // Indica que s'ha d'enviar com a usuari extern
    public static final boolean ENVIAR_COM_USUARI_INTERN = false; // Indica que s'ha d'enviar com a usuari intern

    private final Contacte contacte;

    private final boolean enviarComUsuariExtern;

    public ContactePortaFIB(Contacte contacte, boolean enviarComUsuariExtern) {
        this.contacte = contacte;
        this.enviarComUsuariExtern = enviarComUsuariExtern;
    }

    public Contacte getContacte() {
        return contacte;
    }

    public boolean isEnviarComUsuariExtern() {
        return enviarComUsuariExtern;
    }

}

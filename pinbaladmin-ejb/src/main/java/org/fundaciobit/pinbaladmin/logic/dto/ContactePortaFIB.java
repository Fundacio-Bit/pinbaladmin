package org.fundaciobit.pinbaladmin.logic.dto;

import org.fundaciobit.pinbaladmin.model.entity.Contacte;

/**
 * 
 * @author anadal (u80067)
 * 14 ago 2026 10:22:35
 */
public class ContactePortaFIB {

    public final Contacte contacte;

    public final boolean enviarComUsuariExtern;

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

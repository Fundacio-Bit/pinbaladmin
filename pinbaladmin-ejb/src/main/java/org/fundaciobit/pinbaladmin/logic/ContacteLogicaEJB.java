package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.ContacteEJB;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.fields.ContacteFields;
import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ContacteLogicaEJB")
public class ContacteLogicaEJB extends ContacteEJB implements ContacteLogicaService {

    @Override
    @PermitAll
    public Contacte create(Contacte instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @PermitAll
    public ContacteJPA findByPrimaryKey(Long _ID_) {
        return (ContacteJPA) super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public Contacte buscarOCrearContacte(String nif, String nom, String llinatge1, String llinatge2, String carrec, String telefon, String mail, String username, String fullName) {
        try {
            // Construir el WHERE manejando correctamente los campos null
            int campsNulls = 0;
            
            Where wNIF;
            if (nif != null) {
                wNIF = ContacteFields.NIF.equal(nif);
            } else {
                wNIF = ContacteFields.NIF.isNull();
                campsNulls++;
            }
            
            Where wNom;
            if (nom != null) {
                wNom = ContacteFields.NOM.equal(nom);
            } else {
                wNom = ContacteFields.NOM.isNull();
                campsNulls++;
            }
            
            Where wLlinatge1;
            if (llinatge1 != null) {
                wLlinatge1 = ContacteFields.LLINATGE1.equal(llinatge1);
            } else {
                wLlinatge1 = ContacteFields.LLINATGE1.isNull();
                campsNulls++;
            }
            
            Where wLlinatge2;
            if (llinatge2 != null) {
                wLlinatge2 = ContacteFields.LLINATGE2.equal(llinatge2);
            } else {
                wLlinatge2 = ContacteFields.LLINATGE2.isNull();
                campsNulls++;
            }
            
            Where wCarrec;
            if (carrec != null) {
                wCarrec = ContacteFields.CARREC.equal(carrec);
            } else {
                wCarrec = ContacteFields.CARREC.isNull();
                campsNulls++;
            }
            
            Where wTelefon;
            if (telefon != null) {
                wTelefon = ContacteFields.TELEFON.equal(telefon);
            } else {
                wTelefon = ContacteFields.TELEFON.isNull();
                campsNulls++;
            }
            
            Where wMail;
            if (mail != null) {
                wMail = ContacteFields.MAIL.equal(mail);
            } else {
                wMail = ContacteFields.MAIL.isNull();
                campsNulls++;
            }

            Where wUsername;
            if (username != null) {
                wUsername = ContacteFields.USERNAME.equal(username);
            } else {
                wUsername = ContacteFields.USERNAME.isNull();
                campsNulls++;
            }
            
            Where wFullName;
            if (fullName != null) {
                wFullName = ContacteFields.NOMBRECOMPLETO.equal(fullName);
            } else {
                wFullName = ContacteFields.NOMBRECOMPLETO.isNull();
                campsNulls++;
            }

            Where wContacte = Where.AND(wNIF, wNom, wLlinatge1, wLlinatge2, wCarrec, wTelefon, wMail, wUsername, wFullName);
            
            if(campsNulls == 9) {
                // Si todos los campos son null, no tiene sentido buscar un contacto con todos los campos null, así que devolvemos null directamente.
                log.warn("Se ha intentado buscar o crear un contacto con todos los campos null. No se realizará la búsqueda y se devolverá null.");
                return null;
            }

            // Buscar contactos con exactamente los mismos datos
            List<Contacte> contactesExistents = this.select(wContacte);
            
            // Este nos debería devolver el contacto exacto. Basta que no coincida un campo para que sea otro contacto diferente. 
            // Si hay más de uno, es que hay datos duplicados, pero en ese caso nos quedamos con el primero.
            if (contactesExistents != null && !contactesExistents.isEmpty()) {
                return contactesExistents.get(0);
            }
            
            // Si no existe, crear uno nuevo con exactamente los mismos datos (incluyendo nulls)
            ContacteJPA nouContacte = new ContacteJPA();
            nouContacte.setNif(nif);
            nouContacte.setNom(nom);
            nouContacte.setLlinatge1(llinatge1);
            nouContacte.setLlinatge2(llinatge2);
            nouContacte.setCarrec(carrec);
            nouContacte.setTelefon(telefon);
            nouContacte.setMail(mail);
            nouContacte.setUsername(username);
            nouContacte.setNombrecompleto(fullName);
            
            Contacte contacteCreado = this.create(nouContacte);
            return contacteCreado;
            
        } catch (I18NException e) {
            log.error("Error al buscar o crear contacto con estos datos: NIF=" + nif + ", Nom=" + nom + ", Llinatge1=" + llinatge1 + ", Llinatge2=" + llinatge2 + ", Carrec=" + carrec + ", Telefon=" + telefon + ", Mail=" + mail + ", FullName=" + fullName, e);
        }
        return null;
    }
}
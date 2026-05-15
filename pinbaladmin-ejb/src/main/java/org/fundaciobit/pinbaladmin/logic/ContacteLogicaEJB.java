package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.ContacteEJB;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
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
    public Contacte crearContacteTitular(Solicitud soli) {
        ContacteJPA contacte = new ContacteJPA();

        contacte.setNif(soli.getTitularFirmaNif());
        contacte.setNom(soli.getTitularFirmaNom());
        contacte.setLlinatge1(soli.getTitularFirmaLlinatges());
        contacte.setMail(soli.getTitularFirmaEmail());

        return contacte;
    }

    @Override
    @PermitAll
    public Contacte buscarOCrearContacte(String nif, String nom, String llinatge1, String llinatge2, String carrec, String telefon, String mail, String username) {
        try {
            // Construir el WHERE manejando correctamente los campos null
            // Para cada campo: si es null, buscar isNull(), si no, buscar equal(valor)
            
            Where wNIF = (nif != null) 
                ? ContacteFields.NIF.equal(nif) 
                : ContacteFields.NIF.isNull();
            
            Where wNom = (nom != null) 
                ? ContacteFields.NOM.equal(nom) 
                : ContacteFields.NOM.isNull();
            
            Where wLlinatge1 = (llinatge1 != null) 
                ? ContacteFields.LLINATGE1.equal(llinatge1) 
                : ContacteFields.LLINATGE1.isNull();
            
            Where wLlinatge2 = (llinatge2 != null) 
                ? ContacteFields.LLINATGE2.equal(llinatge2) 
                : ContacteFields.LLINATGE2.isNull();
            
            Where wCarrec = (carrec != null) 
                ? ContacteFields.CARREC.equal(carrec) 
                : ContacteFields.CARREC.isNull();
            
            Where wTelefon = (telefon != null) 
                ? ContacteFields.TELEFON.equal(telefon) 
                : ContacteFields.TELEFON.isNull();
            
            Where wMail = (mail != null) 
                ? ContacteFields.MAIL.equal(mail) 
                : ContacteFields.MAIL.isNull();

            Where wUsername = (username != null) 
                ? ContacteFields.USERNAME.equal(username) 
                : ContacteFields.USERNAME.isNull();
            
            // Combinar todas las condiciones con AND
            Where wContacte = Where.AND(wNIF, wNom, wLlinatge1, wLlinatge2, wCarrec, wTelefon, wMail, wUsername);
            
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
            
            Contacte contacteCreado = this.create(nouContacte);
            return contacteCreado;
            
        } catch (I18NException e) {
            log.error("Error al buscar o crear contacto con estos datos: NIF=" + nif + ", Nom=" + nom + ", Llinatge1=" + llinatge1 + ", Llinatge2=" + llinatge2 + ", Carrec=" + carrec + ", Telefon=" + telefon + ", Mail=" + mail, e);
        }
        return null;
    }
}
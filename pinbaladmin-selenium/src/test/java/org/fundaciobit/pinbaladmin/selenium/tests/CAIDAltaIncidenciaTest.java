package org.fundaciobit.pinbaladmin.selenium.tests;

import java.io.File;

import org.fundaciobit.pinbaladmin.selenium.AltaIncidenciaInfo;
import org.fundaciobit.pinbaladmin.selenium.PinbalAdminSelenium;


/**
 * 
 * @author anadal
 *
 */
public class CAIDAltaIncidenciaTest {

    public static void main(String[] args) {

        String organisme = "Consejería de Fondos Europeos, Universidad y Cultura » (A04027052) Fundación Bit";
        String nom = "Antoni";
        String llinatge1 = "Nadal";
        String llinatge2 = "Bennasar";
        String telefon = "971176060";
        String email = "anadal@fundaciobit.org";
        String asunto = "Información soporte Autofirma NFC";
        String tipus = "Plataforma de Intermediación de Datos: Servicios Web";
        String entorn = "Producción";
        File fitxer = new File(
                "D:\\dades\\dades\\CarpetesPersonals\\ProgramacioPortaFIB3\\genapp-2.0\\genapp-sqltutorial\\versio.txt");
        String consulta = "Me podrian informar si la aplicacion Autofirma  (o Cliente Firma Movil) ya incluye soporte para conexion con el DNI Electronico mediante NFC";

        AltaIncidenciaInfo alta = new AltaIncidenciaInfo(organisme, nom, llinatge1, llinatge2, telefon, email, asunto,
                tipus, entorn, fitxer, consulta);

        
        
        String pageSource = PinbalAdminSelenium.altaIncidencia(alta);

        System.out.println("PAGESOURCE:\n" + pageSource);
        System.out.println("PAGESOURCE:\n" + pageSource);

    }

}

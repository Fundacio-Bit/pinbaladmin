package org.fundaciobit.pinbaladmin.selenium.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.fundaciobit.pinbaladmin.selenium.AltaIncidenciaInfo;
import org.fundaciobit.pinbaladmin.selenium.Incidencia;
import org.fundaciobit.pinbaladmin.selenium.PinbalAdminSelenium;


/**
 * 
 * @author anadal
 *
 */
public class CAIDAltaIncidenciaTest {

    public static void main2(String[] args) {

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
        String comentari = "Me podrian informar si la aplicacion Autofirma  (o Cliente Firma Movil) ya incluye soporte para conexion con el DNI Electronico mediante NFC";

        AltaIncidenciaInfo alta = new AltaIncidenciaInfo(organisme, nom, llinatge1, llinatge2, telefon, email, asunto,
                tipus, entorn, fitxer, comentari);

        
        
        String pageSource = PinbalAdminSelenium.altaIncidencia(alta, false);

        System.out.println("PAGESOURCE:\n" + pageSource);

    }

    
    public static void main(String[] args) {
        
        try {
            File file = new File("resultat.html");
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String st;
            StringBuilder str = new StringBuilder();
            while ((st = br.readLine()) != null) {
              str.append(st).append('\n');
            }
            
            String html = str.toString();
            br.close();
            
            Incidencia i = PinbalAdminSelenium.extractIncidencia(html);
            
            System.out.println("Inci: ]" + i.getIncidencia() + "[");
            System.out.println("Segui: ]" + i.getSeguiment() + "[");
            System.out.println("Email: ]" + i.getEmail() + "[");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    
}

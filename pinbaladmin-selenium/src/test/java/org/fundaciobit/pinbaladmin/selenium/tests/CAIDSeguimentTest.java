package org.fundaciobit.pinbaladmin.selenium.tests;

import org.fundaciobit.pinbaladmin.selenium.PinbalAdminSelenium;

/**
 *
 * @author anadal
 *
 */
public class CAIDSeguimentTest {

    public static void main(String[] args) {

        String email = "governdigital.carpeta@fundaciobit.org";
        String incidencia = "948067";
        String seguimiento = "04827020210706";

        String pageSource = PinbalAdminSelenium.consultaIncidencia(email, incidencia, seguimiento);

        System.out.println("PAGESOURCE:\n" + pageSource);

    }

}

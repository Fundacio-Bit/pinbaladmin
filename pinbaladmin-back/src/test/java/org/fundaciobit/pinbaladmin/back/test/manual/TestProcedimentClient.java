package org.fundaciobit.pinbaladmin.back.test.manual;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.comu.Page;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import org.junit.Test;

import java.util.List;

public class TestProcedimentClient {

    // Configuración (sacada de test.properties)
    private static final String URL_BASE = "https://proves.caib.es/pinbal";
    private static final String USERNAME = "$pinbal_pinbal";
    private static final String PASSWORD = "pinbal_pinbal";
    
    private static final String ENTITAT = "GOVERN";

    @Test
    public void runManualTest() {
        System.out.println("Iniciando Test de ProcedimentClient...");
        System.out.println("URL: " + URL_BASE);
        System.out.println("User: " + USERNAME);

        try {
            ProcedimentClient client = new ProcedimentClient(URL_BASE, USERNAME, PASSWORD, LogLevel.INFO);

            // Test 1: Todos null (excepto entidad y paginación)
            testCall(client, "Test 1 (Nulls)", null, null, null, null);

            // Test 2: Cadenas vacías
            testCall(client, "Test 2 (Empty Strings)", "", "", "", "");

            // Test 3: Cadenas vacías para texto, null para organGestor y sort
            testCall(client, "Test 3 (Empty/Null Mixed)", "", "", null, null);
            
            // Test 4: Sort por defecto
            testCall(client, "Test 4 (Sort Default)", "", "", null, "codi,asc");
            
            // Test 5: Solo entidad (si el método lo permite, aunque la firma requiere argumentos)
            // No aplicable directamente sin cambiar la llamada, pero probamos nulls explícitos.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testCall(ProcedimentClient client, String testName, String codi, String nom, String organGestor, String sort) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Ejecutando: " + testName);
        System.out.println("Params -> Codi: '" + codi + "', Nom: '" + nom + "', OrganGestor: '" + organGestor + "', Sort: '" + sort + "'");
        
        try {
            Page<Procediment> page = client.getProcediments(ENTITAT, codi, nom, organGestor, 0, 10, sort);
            
            if (page != null) {
                System.out.println("RESULTADO: ÉXITO");
                System.out.println("Total elementos: " + page.getTotalElements());
                List<Procediment> list = page.getContent();
                if (list != null && !list.isEmpty()) {
                    System.out.println("Primer elemento: " + list.get(0).getCodi() + " - " + list.get(0).getNom());
                }
            } else {
                System.out.println("RESULTADO: Page es NULL");
            }
        } catch (Exception e) {
            System.out.println("RESULTADO: ERROR");
            System.out.println("Excepción: " + e.getMessage());
            // e.printStackTrace(); // Descomentar para ver stacktrace completo si es necesario
        }
    }
}

package org.fundaciobit.pinbaladmin.selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author anadal
 * Servlet implementation class RemoteSeleniumAlta
 */
public class RemoteSeleniumAlta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoteSeleniumAlta() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Writer w = response.getWriter();


        try {
            
            String organisme = "Consejería de Fondos Europeos, Universidad y Cultura » (A04027052) Fundación Bit";
            String nom = request.getParameter("nom");
            String llinatge1 = request.getParameter("llinatge1");
            String llinatge2 = request.getParameter("llinatge2");
            String telefon = "971176060";
            String email = request.getParameter("email");
            
            String asunto = request.getParameter("asunto");
            String tipus = "Plataforma de Intermediación de Datos: Servicios Web";
            
            
            String entornBool = request.getParameter("entorn"); // String entorn = "Producción";
            String entorn;
            if (entornBool != null && "false".equals(entornBool) ) {
                entorn = "Desarrollo";
            } else {
                entorn = "Producción";
            }
            

            String callback = request.getParameter("callback");

            String comentari = request.getParameter("comentari");

            String fitxerB64 = request.getParameter("fitxer");

            byte[] fitxerBytes = Base64.getDecoder().decode(fitxerB64);

            File fitxer = File.createTempFile("pinbaladmin_", "_Plantilla-Procedimientos.xlsx");
            fitxer.deleteOnExit();

            FileOutputStream fos = new FileOutputStream(fitxer);
            fos.write(fitxerBytes);
            fos.close();

            AltaIncidenciaInfo alta = new AltaIncidenciaInfo(organisme, nom, llinatge1, llinatge2, telefon, email,
                    asunto, tipus, entorn, fitxer, comentari);
            
            
            System.out.println(" VALOR DE DEBUG => ]" +request.getParameter("debug") + "[");
            
            boolean debug = "true".equals(request.getParameter("debug"));

            String pageSource = PinbalAdminSelenium.altaIncidencia(alta, debug);

            pageSource = pageSource.replace("/ayuda/", "https://ssweb.seap.minhap.es/ayuda/");

            
            
            w.append(pageSource);
            w.flush();

            fitxer.delete();

            try {
                Incidencia i = PinbalAdminSelenium.extractIncidencia(pageSource);
                String fullurl = callback + "/" + i.getIncidencia() + "/" + i.getSeguiment();
                
                System.out.println("PREPARNT CRIDADA CALLBACK a  => " + fullurl);

                URL url = new URL(fullurl);
                InputStream is = url.openStream();
                int c;
                while((c = is.read()) != -1) {
                    System.out.println((char)c);
                }
                
                Thread.sleep(2000);
                
            } catch (Exception e) {
                System.out.println("Error enviant CALLBACK !!!!! => " + e.getMessage());
                e.printStackTrace(System.out);
            }

        } catch (Exception e) {
            e.printStackTrace(new PrintWriter(w));
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

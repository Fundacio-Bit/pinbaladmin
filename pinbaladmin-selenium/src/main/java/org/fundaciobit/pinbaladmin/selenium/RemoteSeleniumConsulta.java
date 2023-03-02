package org.fundaciobit.pinbaladmin.selenium;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoteSelenium
 */
public class RemoteSeleniumConsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RemoteSeleniumConsulta() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    
	    response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
	    
	    
	    String email = request.getParameter("email"); //"governdigital.carpeta@fundaciobit.org";
        String incidencia = request.getParameter("incidencia"); //"948067";
        String seguimiento = request.getParameter("seguimiento"); //"04827020210706";
	    
        String pageSource = PinbalAdminSelenium.consultaIncidencia(email, incidencia, seguimiento);


        pageSource = pageSource.replace("/ayuda/", "https://ssweb.seap.minhap.es/ayuda/");
        
        Writer w =  response.getWriter();
        
        
        
        w.append(pageSource);
        w.flush();
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

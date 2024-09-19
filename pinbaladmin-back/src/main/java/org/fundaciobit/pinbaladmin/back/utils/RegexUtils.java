package org.fundaciobit.pinbaladmin.back.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RegexUtils {
	
	  protected static final Logger log = Logger.getLogger(Utils.class);


		public static String getPIDFromSubject(String input) {
	        // Definir el patrón regex para detectar PID y el número entre corchetes
	        String regex = "(\\[PID\\]|PID).*?\\[(\\d+)\\]";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        // Verificar si el patrón fue encontrado
	        if (matcher.find()) {
	            // Obtener el primer número entre corchetes después de PID
	            String pid = matcher.group(2);
	            return pid;
	        } else {
	        	return null;
	        }
	    }
		
	    public static List<String> extractAllNumbers(String input) {
	        // Definir el patrón regex para capturar todos los números
	        String regex = "\\d+";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        // Lista para almacenar los números encontrados
	        List<String> numbers = new ArrayList<>();
	        
	        // Buscar todos los números en la cadena de entrada
	        while (matcher.find()) {
	            numbers.add(matcher.group());
	        }
	        
	        return numbers;
	    }
	    
	    public static String extractCAI(String input) {
	        // Definir el patrón regex para capturar CAI-numero o CAI- numero
	        String regex = "CAI- ?(\\d+)";

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        // Verificar si el patrón fue encontrado
	        if (matcher.find()) {
	            // Obtener el CAI completo
	            return matcher.group(1);
	        } else {
	            return null;
	        }
	    }

	    public static boolean findCAIInText(String cai, String input) {
	        // Definir el patrón regex para buscar CAI-numero o CAI- numero en el texto
	        String regex = "CAI- ?\\d+";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        // Buscar todas las coincidencias
	        while (matcher.find()) {
	            if (matcher.group().replace(" ", "").equals(cai.replace(" ", ""))) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public static String[] testRespostaAutomaticaSuport(String input) {
	        // Definir el patrón regex
	        String regex = "Re: PINBAL \\[(\\d+)] - (ALTA|MODIFICACIO) (INCIDENCIA|SOLICITUD) - \\[governdigital\\.pinbal]";

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        // Verificar si el patrón fue encontrado
	        if (matcher.find()) {
	            // Obtener los detalles del mensaje
	            String id = matcher.group(1);
	            String tipoIncidencia = matcher.group(3);
	            
	            return new String[]{id, tipoIncidencia};
	        } else {
	            return null;
	        }
	    }

	    

}

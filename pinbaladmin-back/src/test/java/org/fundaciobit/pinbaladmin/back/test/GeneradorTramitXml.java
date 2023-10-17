package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

public class GeneradorTramitXml {

    public static void main(String[] args) {
        
        try {
            String plantilla = FileUtils.readFileToString(new File("formulario_plantilla.xml"), Charset.defaultCharset());
            
            Map<String, Object> map = new HashMap<String, Object>();
            TramitAPersAutJPA A = new TramitAPersAutJPA();
            
            A.setNif("12345678Z");
            map.put("A", A);
            
            List<String> list = new ArrayList<String>();
            list.add("Pep");
            list.add("Toni");
            
            map.put("llista",list);
            
            String result = TemplateEngine.processExpressionLanguage(plantilla, map);
            FileUtils.write(new File("formulario.xml"), result, Charset.defaultCharset());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}

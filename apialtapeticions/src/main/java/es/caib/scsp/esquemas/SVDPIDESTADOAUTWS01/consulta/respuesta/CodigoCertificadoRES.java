//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:56 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.respuesta;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CodigoCertificadoRES
    extends JAXBElement<String>
{

    protected final static QName NAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta", "CodigoCertificado");

    public CodigoCertificadoRES(String value) {
        super(NAME, String.class, null, value);
    }

    public CodigoCertificadoRES() {
        super(NAME, String.class, null, null);
    }

}

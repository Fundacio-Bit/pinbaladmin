//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.15 a las 12:17:55 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.peticion;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CodigoCertificadoPET
    extends JAXBElement<String>
{

    protected final static QName NAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", "CodigoCertificado");

    public CodigoCertificadoPET(String value) {
        super(NAME, ((Class) String.class), null, value);
    }

    public CodigoCertificadoPET() {
        super(NAME, ((Class) String.class), null, null);
    }

}
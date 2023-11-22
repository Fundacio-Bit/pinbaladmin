//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.2 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2023.11.22 a las 02:37:00 PM CET 
//


package es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.peticion;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class NombreEmisorPET
    extends JAXBElement<String>
{

    protected final static QName NAME = new QName("http://intermediacion.redsara.es/scsp/esquemas/V3/peticion", "NombreEmisor");

    public NombreEmisorPET(String value) {
        super(NAME, ((Class) String.class), null, value);
    }

    public NombreEmisorPET() {
        super(NAME, ((Class) String.class), null, null);
    }

}

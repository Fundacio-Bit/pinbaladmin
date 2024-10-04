package org.fundaciobit.pinbaladmin.api.interna.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 *
 * @author anadal
 *
 */
@OpenAPIDefinition(
        servers = { @Server(url = "/pinbaladminapi/interna"),
                @Server(url = "http://localhost:8080/pinbaladminapi/interna"),
                @Server(url = "https://dev.caib.es/pinbaladminapi/interna"),
                @Server(url = "https://proves.caib.es/pinbaladminapi/interna"),
                @Server(url = "https://se.caib.es/pinbaladminapi/interna"),
                @Server(url = "https://www.caib.es/pinbaladminapi/interna") }
)
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    protected Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public JAXRSConfiguration() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        log.info("Iniciant API REST INTERNA de PinbalAdmin");
    }

}

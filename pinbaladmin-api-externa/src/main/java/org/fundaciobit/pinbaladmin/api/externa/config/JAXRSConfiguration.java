package org.fundaciobit.pinbaladmin.api.externa.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

/**
 * 
 * @author anadal
 *
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API REST EXTERNA de PinbalAdmin",
                description = "Conjunt de Serveis REST de PinbalAdmin per ser accedits des de l'exterior", 
                version = "1.0.0",
                license = @License(
                        name = "License Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "otae@fundaciobit.org",
                        url = "http://otae.fundaciobit.org")

        ),
        servers = {
                @Server(url = "/pinbaladminapi/externa"),
                @Server(url = "http://localhost:8080/pinbaladminapi/externa"),
                @Server(url = "https://dev.caib.es/pinbaladminapi/externa"),
                @Server(url = "https://proves.caib.es/pinbaladminapi/externa"),
                @Server(url = "https://se.caib.es/pinbaladminapi/externa"),
                @Server(url = "https://www.caib.es/pinbaladminapi/externa") },
        externalDocs = @ExternalDocumentation(
                description = "Java Client (GovernIB Github)",
                url = "https://github.com/GovernIB/pinbaladmin/tree/pinbaladmin-1.0/pinbaladmin-api-externa-client")

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
        log.info("Iniciant API REST EXTERNA de PinbalAdmin");
    }

}

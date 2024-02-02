
package org.fundaciobit.pinbaladmin.api.externa.secure.tramitsistra;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "nif", "nombre", "apellido1", "apellido2" })
@Generated("jsonschema2pojo")
public class Usuario {

    @JsonProperty("nif")
    @Schema(description = "nif", example = "11111111H")
    private String nif;

    @JsonProperty("nombre")
    @Schema(description = "Nombre", example = "Nombre")
    private String nombre;
    
    @JsonProperty("apellido1")
    @Schema(description = "Primer apellido", example = "Apellido 1")
    private String apellido1;
    
    @JsonProperty("apellido2")
    @Schema(description = "Segundo apellido", example = "Apellido 2")
    private String apellido2;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("nif")
    public String getNif() {
        return nif;
    }

    @JsonProperty("nif")
    public void setNif(String nif) {
        this.nif = nif;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("apellido1")
    public String getApellido1() {
        return apellido1;
    }

    @JsonProperty("apellido1")
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    @JsonProperty("apellido2")
    public String getApellido2() {
        return apellido2;
    }

    @JsonProperty("apellido2")
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

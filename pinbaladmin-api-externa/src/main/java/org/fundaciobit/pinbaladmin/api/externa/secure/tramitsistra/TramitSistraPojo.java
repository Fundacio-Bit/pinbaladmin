
package org.fundaciobit.pinbaladmin.api.externa.secure.tramitsistra;

import java.util.LinkedHashMap;
import java.util.List;
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
@JsonPropertyOrder({ "entidad", "idSesionFormulario", "idFormularioExterno", "idioma", "usuario", "xmlDatosActuales",
        "parametrosApertura", "urlCallback" })
@Generated("jsonschema2pojo")
public class TramitSistraPojo {

    @JsonProperty("entidad")
    @Schema(description = "DIR3 de la entidad", example = "A04003003")
    private String entidad;

    @JsonProperty("idSesionFormulario")
    @Schema(description = "Id sesión formulario de SISTRA2", example = "CFDEKWNL-UHMZR8T8-T8WTFOOR")
    private String idSesionFormulario;
    
    @JsonProperty("idFormularioExterno")
    @Schema(description = "Id formulario externo a ejecutar", example = "FOR-EXT-1")
    private String idFormularioExterno;
    
    @JsonProperty("idioma")
    @Schema(description = "Idioma (es, ca, en)", example = "es")
    private String idioma;
    
    @JsonProperty("usuario")
    @Schema(description = "Usuario autenticado")
    private Usuario usuario;
    
    @JsonProperty("xmlDatosActuales")
    @Schema(description = "XML con los datos actuales del formulario en Base64", example = "< XML B64>")
    private String xmlDatosActuales;
    
    @JsonProperty("parametrosApertura")
    @Schema(description = "Lista parámetros (opcional)")
    private List<ParametrosApertura> parametrosApertura;
    
    @JsonProperty("urlCallback")
    @Schema(description = "Url callback SISTRA2", example = "<URL CALLBACK SISTRA2>")
    private String urlCallback;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entidad")
    public String getEntidad() {
        return entidad;
    }

    @JsonProperty("entidad")
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @JsonProperty("idSesionFormulario")
    public String getIdSesionFormulario() {
        return idSesionFormulario;
    }

    @JsonProperty("idSesionFormulario")
    public void setIdSesionFormulario(String idSesionFormulario) {
        this.idSesionFormulario = idSesionFormulario;
    }

    @JsonProperty("idFormularioExterno")
    public String getIdFormularioExterno() {
        return idFormularioExterno;
    }

    @JsonProperty("idFormularioExterno")
    public void setIdFormularioExterno(String idFormularioExterno) {
        this.idFormularioExterno = idFormularioExterno;
    }

    @JsonProperty("idioma")
    public String getIdioma() {
        return idioma;
    }

    @JsonProperty("idioma")
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @JsonProperty("usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    @JsonProperty("usuario")
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @JsonProperty("xmlDatosActuales")
    public String getXmlDatosActuales() {
        return xmlDatosActuales;
    }

    @JsonProperty("xmlDatosActuales")
    public void setXmlDatosActuales(String xmlDatosActuales) {
        this.xmlDatosActuales = xmlDatosActuales;
    }

    @JsonProperty("parametrosApertura")
    public List<ParametrosApertura> getParametrosApertura() {
        return parametrosApertura;
    }

    @JsonProperty("parametrosApertura")
    public void setParametrosApertura(List<ParametrosApertura> parametrosApertura) {
        this.parametrosApertura = parametrosApertura;
    }

    @JsonProperty("urlCallback")
    public String getUrlCallback() {
        return urlCallback;
    }

    @JsonProperty("urlCallback")
    public void setUrlCallback(String urlCallback) {
        this.urlCallback = urlCallback;
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

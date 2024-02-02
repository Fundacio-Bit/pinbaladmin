
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "idSesionFormulario", "cancelado", "xml", "pdf" })
@Generated("jsonschema2pojo")
public class Resultado {

    @JsonProperty("idSesionFormulario")
    private String idSesionFormulario;
    @JsonProperty("cancelado")
    private Boolean cancelado;
    @JsonProperty("xml")
    private String xml;
    @JsonProperty("pdf")
    private String pdf;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("idSesionFormulario")
    public String getIdSesionFormulario() {
        return idSesionFormulario;
    }

    @JsonProperty("idSesionFormulario")
    public void setIdSesionFormulario(String idSesionFormulario) {
        this.idSesionFormulario = idSesionFormulario;
    }

    @JsonProperty("cancelado")
    public Boolean getCancelado() {
        return cancelado;
    }

    @JsonProperty("cancelado")
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    @JsonProperty("xml")
    public String getXml() {
        return xml;
    }

    @JsonProperty("xml")
    public void setXml(String xml) {
        this.xml = xml;
    }

    @JsonProperty("pdf")
    public String getPdf() {
        return pdf;
    }

    @JsonProperty("pdf")
    public void setPdf(String pdf) {
        this.pdf = pdf;
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
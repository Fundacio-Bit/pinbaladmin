/*
 * API REST EXTERNA de PinbalAdmin
 * Conjunt de Serveis REST de PinbalAdmin per ser accedits des de l'exterior
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: otae@fundaciobit.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.fundaciobit.pinbaladmin.apiexterna.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fundaciobit.pinbaladmin.apiexterna.client.model.ParametrosApertura;
import org.fundaciobit.pinbaladmin.apiexterna.client.model.Usuario;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * TramitSistraPojo
 */
@JsonPropertyOrder({
  TramitSistraPojo.JSON_PROPERTY_ENTIDAD,
  TramitSistraPojo.JSON_PROPERTY_ID_SESION_FORMULARIO,
  TramitSistraPojo.JSON_PROPERTY_ID_FORMULARIO_EXTERNO,
  TramitSistraPojo.JSON_PROPERTY_IDIOMA,
  TramitSistraPojo.JSON_PROPERTY_USUARIO,
  TramitSistraPojo.JSON_PROPERTY_XML_DATOS_ACTUALES,
  TramitSistraPojo.JSON_PROPERTY_PARAMETROS_APERTURA,
  TramitSistraPojo.JSON_PROPERTY_URL_CALLBACK
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TramitSistraPojo {
  public static final String JSON_PROPERTY_ENTIDAD = "entidad";
  private String entidad;

  public static final String JSON_PROPERTY_ID_SESION_FORMULARIO = "idSesionFormulario";
  private String idSesionFormulario;

  public static final String JSON_PROPERTY_ID_FORMULARIO_EXTERNO = "idFormularioExterno";
  private String idFormularioExterno;

  public static final String JSON_PROPERTY_IDIOMA = "idioma";
  private String idioma;

  public static final String JSON_PROPERTY_USUARIO = "usuario";
  private Usuario usuario;

  public static final String JSON_PROPERTY_XML_DATOS_ACTUALES = "xmlDatosActuales";
  private String xmlDatosActuales;

  public static final String JSON_PROPERTY_PARAMETROS_APERTURA = "parametrosApertura";
  private List<ParametrosApertura> parametrosApertura;

  public static final String JSON_PROPERTY_URL_CALLBACK = "urlCallback";
  private String urlCallback;

  public TramitSistraPojo() {
  }

  public TramitSistraPojo entidad(String entidad) {
    
    this.entidad = entidad;
    return this;
  }

   /**
   * DIR3 de la entidad
   * @return entidad
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ENTIDAD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEntidad() {
    return entidad;
  }


  @JsonProperty(JSON_PROPERTY_ENTIDAD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEntidad(String entidad) {
    this.entidad = entidad;
  }


  public TramitSistraPojo idSesionFormulario(String idSesionFormulario) {
    
    this.idSesionFormulario = idSesionFormulario;
    return this;
  }

   /**
   * Id sesión formulario de SISTRA2
   * @return idSesionFormulario
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_SESION_FORMULARIO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIdSesionFormulario() {
    return idSesionFormulario;
  }


  @JsonProperty(JSON_PROPERTY_ID_SESION_FORMULARIO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdSesionFormulario(String idSesionFormulario) {
    this.idSesionFormulario = idSesionFormulario;
  }


  public TramitSistraPojo idFormularioExterno(String idFormularioExterno) {
    
    this.idFormularioExterno = idFormularioExterno;
    return this;
  }

   /**
   * Id formulario externo a ejecutar
   * @return idFormularioExterno
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID_FORMULARIO_EXTERNO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIdFormularioExterno() {
    return idFormularioExterno;
  }


  @JsonProperty(JSON_PROPERTY_ID_FORMULARIO_EXTERNO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdFormularioExterno(String idFormularioExterno) {
    this.idFormularioExterno = idFormularioExterno;
  }


  public TramitSistraPojo idioma(String idioma) {
    
    this.idioma = idioma;
    return this;
  }

   /**
   * Idioma (es, ca, en)
   * @return idioma
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_IDIOMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getIdioma() {
    return idioma;
  }


  @JsonProperty(JSON_PROPERTY_IDIOMA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }


  public TramitSistraPojo usuario(Usuario usuario) {
    
    this.usuario = usuario;
    return this;
  }

   /**
   * Get usuario
   * @return usuario
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_USUARIO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Usuario getUsuario() {
    return usuario;
  }


  @JsonProperty(JSON_PROPERTY_USUARIO)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }


  public TramitSistraPojo xmlDatosActuales(String xmlDatosActuales) {
    
    this.xmlDatosActuales = xmlDatosActuales;
    return this;
  }

   /**
   * XML con los datos actuales del formulario en Base64
   * @return xmlDatosActuales
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_XML_DATOS_ACTUALES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getXmlDatosActuales() {
    return xmlDatosActuales;
  }


  @JsonProperty(JSON_PROPERTY_XML_DATOS_ACTUALES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setXmlDatosActuales(String xmlDatosActuales) {
    this.xmlDatosActuales = xmlDatosActuales;
  }


  public TramitSistraPojo parametrosApertura(List<ParametrosApertura> parametrosApertura) {
    
    this.parametrosApertura = parametrosApertura;
    return this;
  }

  public TramitSistraPojo addParametrosAperturaItem(ParametrosApertura parametrosAperturaItem) {
    if (this.parametrosApertura == null) {
      this.parametrosApertura = new ArrayList<>();
    }
    this.parametrosApertura.add(parametrosAperturaItem);
    return this;
  }

   /**
   * Lista parámetros (opcional)
   * @return parametrosApertura
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PARAMETROS_APERTURA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<ParametrosApertura> getParametrosApertura() {
    return parametrosApertura;
  }


  @JsonProperty(JSON_PROPERTY_PARAMETROS_APERTURA)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setParametrosApertura(List<ParametrosApertura> parametrosApertura) {
    this.parametrosApertura = parametrosApertura;
  }


  public TramitSistraPojo urlCallback(String urlCallback) {
    
    this.urlCallback = urlCallback;
    return this;
  }

   /**
   * Url callback SISTRA2
   * @return urlCallback
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_URL_CALLBACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getUrlCallback() {
    return urlCallback;
  }


  @JsonProperty(JSON_PROPERTY_URL_CALLBACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setUrlCallback(String urlCallback) {
    this.urlCallback = urlCallback;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TramitSistraPojo tramitSistraPojo = (TramitSistraPojo) o;
    return Objects.equals(this.entidad, tramitSistraPojo.entidad) &&
        Objects.equals(this.idSesionFormulario, tramitSistraPojo.idSesionFormulario) &&
        Objects.equals(this.idFormularioExterno, tramitSistraPojo.idFormularioExterno) &&
        Objects.equals(this.idioma, tramitSistraPojo.idioma) &&
        Objects.equals(this.usuario, tramitSistraPojo.usuario) &&
        Objects.equals(this.xmlDatosActuales, tramitSistraPojo.xmlDatosActuales) &&
        Objects.equals(this.parametrosApertura, tramitSistraPojo.parametrosApertura) &&
        Objects.equals(this.urlCallback, tramitSistraPojo.urlCallback);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entidad, idSesionFormulario, idFormularioExterno, idioma, usuario, xmlDatosActuales, parametrosApertura, urlCallback);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TramitSistraPojo {\n");
    sb.append("    entidad: ").append(toIndentedString(entidad)).append("\n");
    sb.append("    idSesionFormulario: ").append(toIndentedString(idSesionFormulario)).append("\n");
    sb.append("    idFormularioExterno: ").append(toIndentedString(idFormularioExterno)).append("\n");
    sb.append("    idioma: ").append(toIndentedString(idioma)).append("\n");
    sb.append("    usuario: ").append(toIndentedString(usuario)).append("\n");
    sb.append("    xmlDatosActuales: ").append(toIndentedString(xmlDatosActuales)).append("\n");
    sb.append("    parametrosApertura: ").append(toIndentedString(parametrosApertura)).append("\n");
    sb.append("    urlCallback: ").append(toIndentedString(urlCallback)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


package org.fundaciobit.pinbaladmin.apiexterna.client.api;

import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiException;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiClient;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.Configuration;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import org.fundaciobit.pinbaladmin.apiexterna.client.model.RestExceptionInfo;
import org.fundaciobit.pinbaladmin.apiexterna.client.model.Resultado;
import org.fundaciobit.pinbaladmin.apiexterna.client.model.TramitSistraPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TramitSistraServiceApi {
  private ApiClient apiClient;

  public TramitSistraServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TramitSistraServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna les dades del formulari
   * 
   * @param ticket Ticket del fomulario (required)
   * @return a {@code Resultado}
   * @throws ApiException if fails to make API call
   */
  public Resultado getDatosFormularioFromTicket(String ticket) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'ticket' is set
    if (ticket == null) {
      throw new ApiException(400, "Missing the required parameter 'ticket' when calling getDatosFormularioFromTicket");
    }
    
    // create path and map variables
    String localVarPath = "/secure/TramitSistraService/v1/resultado".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "ticket", ticket));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Resultado> localVarReturnType = new GenericType<Resultado>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la URL per iniciar el tramit
   * 
   * @param tramitSistraPojo Dades de la petició (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String getUrlFormulari(TramitSistraPojo tramitSistraPojo) throws ApiException {
    Object localVarPostBody = tramitSistraPojo;
    
    // create path and map variables
    String localVarPath = "/secure/TramitSistraService/v1/formulario".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}

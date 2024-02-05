# TramitSistraServiceApi

All URIs are relative to */pinbaladminapi/externa*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getDatosFormularioFromTicket**](TramitSistraServiceApi.md#getDatosFormularioFromTicket) | **GET** /secure/TramitSistraService/v1/resultado | Retorna les dades del formulari |
| [**getUrlFormulari**](TramitSistraServiceApi.md#getUrlFormulari) | **POST** /secure/TramitSistraService/v1/formulario | Retorna la URL per iniciar el tramit |



## getDatosFormularioFromTicket

> Resultado getDatosFormularioFromTicket(ticket)

Retorna les dades del formulari

### Example

```java
// Import classes:
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiClient;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiException;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.Configuration;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.auth.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.models.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.api.TramitSistraServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/pinbaladminapi/externa");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        TramitSistraServiceApi apiInstance = new TramitSistraServiceApi(defaultClient);
        String ticket = "CFDEKWNL-UHMZR8T8-T8WTFOOR:MhufBxRLLomvdOjC9nZhPA=="; // String | Ticket del fomulario
        try {
            Resultado result = apiInstance.getDatosFormularioFromTicket(ticket);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TramitSistraServiceApi#getDatosFormularioFromTicket");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **ticket** | **String**| Ticket del fomulario | |

### Return type

[**Resultado**](Resultado.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **510** | Només s&#39;utilitza per crear fitxer de constants... |  -  |


## getUrlFormulari

> String getUrlFormulari(tramitSistraPojo)

Retorna la URL per iniciar el tramit

### Example

```java
// Import classes:
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiClient;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiException;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.Configuration;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.auth.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.models.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.api.TramitSistraServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/pinbaladminapi/externa");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        TramitSistraServiceApi apiInstance = new TramitSistraServiceApi(defaultClient);
        TramitSistraPojo tramitSistraPojo = new TramitSistraPojo(); // TramitSistraPojo | Dades de la petició
        try {
            String result = apiInstance.getUrlFormulari(tramitSistraPojo);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TramitSistraServiceApi#getUrlFormulari");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **tramitSistraPojo** | [**TramitSistraPojo**](TramitSistraPojo.md)| Dades de la petició | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


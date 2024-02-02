# ExempleSeguritzatApi

All URIs are relative to */pinbaladminapi/externa*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**echo**](ExempleSeguritzatApi.md#echo) | **GET** /secure/exempleseguritzat/echo | Fa un ECHO |



## echo

> String echo(echoInput)

Fa un ECHO

### Example

```java
// Import classes:
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiClient;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.ApiException;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.Configuration;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.auth.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.services.models.*;
import org.fundaciobit.pinbaladmin.apiexterna.client.api.ExempleSeguritzatApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/pinbaladminapi/externa");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        ExempleSeguritzatApi apiInstance = new ExempleSeguritzatApi(defaultClient);
        String echoInput = "hola caracola"; // String | Cadena a retornar
        try {
            String result = apiInstance.echo(echoInput);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExempleSeguritzatApi#echo");
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
| **echoInput** | **String**| Cadena a retornar | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Respon el valor enviat per paràmetre |  -  |
| **510** | Només s&#39;utilitza per crear fitxer de constants... |  -  |


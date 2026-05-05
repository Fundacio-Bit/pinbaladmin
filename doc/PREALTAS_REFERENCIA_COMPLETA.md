# 📘 PREALTAS - REFERENCIA COMPLETA DEL SISTEMA

**Documento Maestro Consolidado**  
**Última actualización:** 5 mayo 2026  
**Versión:** 3.0 - Consolidación completa

---

## 📋 ÍNDICE

1. [¿Qué es PREALTAS?](#qué-es-prealtas)
2. [Actores del Sistema](#actores-del-sistema)
3. [Conceptos Fundamentales](#conceptos-fundamentales)
4. [Estados del Sistema](#estados-del-sistema)
5. [Ciclo de Vida Completo](#ciclo-de-vida-completo)
6. [Servicios SOAP con Madrid](#servicios-soap-con-madrid)
7. [Respuestas de Madrid](#respuestas-de-madrid)
8. [Procesos Automáticos](#procesos-automáticos)
9. [Notificaciones y Emails](#notificaciones-y-emails)
10. [Entidades de Base de Datos](#entidades-de-base-de-datos)
11. [Clases Principales del Código](#clases-principales-del-código)
12. [Casos Especiales](#casos-especiales)
13. [Tiempos y Duraciones](#tiempos-y-duraciones)
14. [Diferencias PREALTAS vs PINFO](#diferencias-prealtas-vs-pinfo)

---

## 🎯 QUÉ ES PREALTAS

**PREALTAS** es el sistema de **autorización de procedimientos administrativos** para que entidades locales de las Islas Baleares (ayuntamientos, consejos insulares) puedan consultar datos personales de ciudadanos a través de la **Plataforma de Intermediación de Datos (PID)** del Estado español (Madrid).

### Ejemplo Práctico

Un ayuntamiento tramita "Ayudas al alquiler de vivienda 2026":
- Necesita verificar: DNI, nivel de renta, situación familiar del ciudadano
- **NO puede** consultar estos datos sin autorización previa de Madrid
- **PREALTAS** gestiona todo el proceso de solicitud y autorización
- Una vez autorizado → puede consultar los datos en producción

### Ámbito

- **Entidades locales:** Ayuntamientos, Consejos Insulares
- **Datos consultables:** DNI, Residencia, Vida Laboral, Renta, Discapacidad, Titulaciones, etc.
- **Marco legal:** RGPD, LOPDGDD, Ley 39/2015, Ley 40/2015
- **Sistema central:** PID (Plataforma Intermediación Datos) del Estado
- **Protocolo:** Web Services SOAP

---

## 👥 ACTORES DEL SISTEMA

1. **Entidad Local (Solicitante)**
   - Ayuntamiento, Consejo Insular
   - Crea la solicitud inicial en portal CAIB
   - Recibe notificaciones de autorización/desestimación

2. **Tramitador PinbalAdmin**
   - Operador del sistema PinbalAdmin
   - Revisa solicitudes, valida documentación
   - Envía a firmar, envía a Madrid
   - Estados donde actúa: 5, 11, 19, 33

3. **Director General**
   - Titular del órgano gestor
   - Firma digitalmente las solicitudes
   - Vía PortaFIB (plataforma firma CAIB)
   - Estado donde actúa: 15

4. **Sistema PinbalAdmin**
   - Automatiza envíos a Madrid
   - Consultas periódicas cada 30 minutos
   - Reintentos automáticos de errores
   - Notificaciones por email
   - Estados automáticos: 19→20, 20→40/30, 44→19

5. **Madrid (PID del Estado)**
   - Validación automática (robot)
   - Revisión manual (operadores humanos)
   - Autoriza o desestima
   - Duración: 1-4 semanas típicamente

---

## 🧠 CONCEPTOS FUNDAMENTALES

### ALTA vs MODIFICACIÓN

| Concepto | ALTA | MODIFICACIÓN |
|----------|------|--------------|
| **Cuándo** | Primera vez que se solicita | Sobre procedimiento ya autorizado |
| **Campo clave** | `dataAutoritzacio` = NULL | `dataAutoritzacio` != NULL |
| **Servicio SOAP** | `SolicitudAutorizacion` | `ActualizacionProcedimiento` |
| **XML enviado** | Completo (todos los datos) | Solo cambios (servicios añadidos/quitados) |
| **Estado inicial** | 5 (desde CAIB) | 33 (cambio desde CAIB) |
| **Flujo** | 5→11→15→19→20→40 | 33→19→20→40 |

### Campo Crítico: `dataAutoritzacio`

**Reglas absolutas:**
- Se establece la **primera vez** que Madrid autoriza (Estado 20 → 40)
- **NO se vuelve a cambiar NUNCA** (aunque haya 100 modificaciones)
- Sirve para detectar tipo de envío:
  ```java
  if (infoMadrid.getDataAutoritzacio() == null) {
      // ALTA - Primera vez
      enviarAltaAMadrid();
  } else {
      // MODIFICACIÓN - Ya autorizado antes
      enviarModificacionAMadrid();
  }
  ```

### Procedimiento "Dado de Alta"

**Estado 40 = AUTORITZAT** significa:
- ✅ El procedimiento **está en producción en Madrid**
- ✅ La entidad **puede consultar datos de ciudadanos**
- ✅ Funciona indefinidamente (hasta `dataCaducitat` si existe)
- ✅ Se pueden hacer **MODIFICACIONES** cuando se quiera

**Estados que NO son "de alta":**
- Estado 20: Esperando respuesta (solo solicitud pendiente)
- Estado 30: Desestimado (rechazado, no está de alta)
- Estado 44: Error técnico (no se envió correctamente)

### InfoMadrid - Registro Maestro

La entidad `InfoMadrid` es **CRÍTICA** - contiene TODO el histórico de comunicación con Madrid:

```java
InfoMadridJPA {
    infoMadridID         // ID único
    codi                 // Código procedimiento
    estatProcediment     // Estado PinbalAdmin (19, 20, 30, 40, 44)
    estatAutoritzacio    // Estado Madrid (0-8)
    missatge             // Observaciones/errores de Madrid
    dataEnviament        // Cuándo se envió a Madrid
    dataConsulta         // Última consulta realizada
    dataAutoritzacio     // ⭐ CRÍTICO: Primera autorización
    intents              // Reintentos si error
}
```

---

## 📊 ESTADOS DEL SISTEMA

### Tabla Completa de Estados

| Código | Nombre | Tipo | Quién Actúa | Duración |
|--------|--------|------|-------------|----------|
| **5** | PENDENT_DISTRIBUCIO | Entrada | 👤 Tramitador | 5-10 min |
| **11** | PENDENT_Enviar_Director | Revisión | 👤 Tramitador | 30 min - 2h |
| **15** | PENDENT_Firma_Director | Firma | ✍️ Director | Horas - días |
| **19** | PENDENT_ENVIAR_MADRID | Pre-envío | 👤 Tramitador | 1 click |
| **20** | PENDENT_AUTORITZAR | Espera | 🤖 Sistema | 1-4 semanas |
| **30** | ESMENES | Desestimado | - | - |
| **33** | CANVI_PENDENT_REVISAR | Modificación | 👤 Tramitador | 15-30 min |
| **39** | AUTORITZAT_Parcial | Parcial | - | Informativo |
| **40** | AUTORITZAT ✅ | **DE ALTA** | - | Indefinido |
| **44** | ERROR_ENVIANT_MADRID | Error | 🤖 Sistema | Reintento 02:00 |
| **60** | TANCAT | Cerrado | 👤 Manual | Archivo |

### Constantes en Código

```java
// Archivo: Constants.java
public static final long SOLI_ESTAT_PENDENT_DISTRIBUCIO = 5;
public static final long SOLI_ESTAT_PENDENT_Enviar_Director = 11;
public static final long SOLI_ESTAT_PENDENT_Firma_Director = 15;
public static final long SOLI_ESTAT_PENDENT_ENVIAR_MADRID = 19;
public static final long SOLI_ESTAT_PENDENT_AUTORITZAR = 20;
public static final long SOLI_ESTAT_ESMENES = 30;
public static final long SOLI_ESTAT_CANVI_PENDENT_REVISAR = 33;
public static final long SOLI_ESTAT_AUTORITZAT_Parcial = 39;
public static final long SOLI_ESTAT_AUTORITZAT = 40;
public static final long SOLI_ESTAT_ERROR_ENVIANT_MADRID = 44;
public static final long SOLI_ESTAT_TANCAT = 60;
```

### Estados de Madrid (estatAutoritzacio)

| Código | Significado | Estado PinbalAdmin |
|--------|-------------|--------------------|
| **0, 2, 6** | Pendiente de tramitar | 20 |
| **7** | ✅ Autorizado | 40 |
| **3, 8** | ❌ Desestimado | 30 |

```java
public static final Long ESTAT_PINBAL_PENDENT_TRAMITAR = 0L;
public static final Long ESTAT_PINBAL_AUTORITZAT = 7L;
public static final Long ESTAT_PINBAL_DESESTIMAT = 8L;
```

---

## 🎨 LEYENDA DE ICONOS

### 👤 Acciones Manuales (Tramitador/Director)
| Icono | Acción |
|-------|--------|
| 👤 | Acción manual del tramitador |
| ✅ | Marcar como recibido/aprobado |
| 📋 | Revisar documentación/datos |
| 📨 | Enviar a firmar al Director |
| ✍️ | Firmar documento (Director) |
| 🔍 | Revisar/Consultar manualmente |

### 🤖 Acciones Automáticas (Sistema)
| Icono | Acción |
|-------|--------|
| 🤖 | Acción automática del sistema |
| 📤 | Enviar a Madrid (web service SOAP) |
| 🔍 | Consultar estado a Madrid (automático cada 30 min) |
| 📧 | Enviar email a la entidad |
| 🔄 | Cambiar estado automáticamente |
| ⏱️ | Programar reintento (02:00) |
| 🔁 | Reintentar envío |
| 💾 | Guardar datos/logs |

### 🏢 Acciones de Madrid
| Icono | Acción |
|-------|--------|
| 🏢 | Madrid procesa (humano) |
| 🤖🏢 | Madrid valida (automático/robot) |
| 📨 | Madrid responde |

---

## 🔄 CICLO DE VIDA COMPLETO

### ⚠️ IMPORTANTE: Cuándo está "Dado de Alta"

**Estado 40 = AUTORITZAT** significa que el procedimiento **ESTÁ DADO DE ALTA en Madrid:**
- ✅ La entidad **puede consultar datos de ciudadanos en producción**
- ✅ Los servicios autorizados están activos
- ✅ Funciona indefinidamente (hasta `dataCaducitat` si existe)
- ✅ Se pueden hacer **MODIFICACIONES** cuando se quiera

**Estados que NO son "de alta":**
- **Estado 5-19:** En proceso interno, aún no enviado o en preparación
- **Estado 20:** **Solo una solicitud pendiente** - Madrid aún no autorizó
- **Estado 30:** Desestimado - **NO está de alta**, rechazado
- **Estado 44:** Error técnico - **NO se envió correctamente**

**Estado 39 (Parcial):**
- Es un **estado informativo especial**
- Significa que **hay servicios autorizados** (se pueden usar) **y otros no**
- **Aparece principalmente en MODIFICACIONES**
- El procedimiento **SÍ está de alta** (con los servicios autorizados)

### FLUJO ALTA (Primera Solicitud)

```
┌──────────────────────────────────────────────────────────┐
│           ENTRADA DESDE CAIB                             │
└──────────────────────────────────────────────────────────┘
                        │
                        ↓ (Automático)
                  ┌──────────┐
                  │ Estado 5 │ PENDENT_DISTRIBUCIO
                  └─────┬────┘
                        │
         👤 Tramitador: ✅ Marcar recibida (5-10 min)
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 11│ PENDENT_Enviar_Director
                  └─────┬────┘
                        │
    👤 Tramitador: 📋 Revisar documentación
                   📋 Verificar normativa
                   📋 Comprobar justificación
                   📨 Enviar a firmar (30 min - 2h)
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 15│ PENDENT_Firma_Director
                  └─────┬────┘
                        │
         ✍️ Director: Firma digital (horas - días)
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 19│ PENDENT_ENVIAR_MADRID
                  └─────┬────┘
                        │
         👤 Tramitador: 📤 Click "Enviar a Madrid"
                        │
              🤖 Sistema: 💾 Generar XML
                         📤 Enviar SOAP
                         🔄 Procesar respuesta
                        │
        ┌───────────────┼───────────────┐
        │               │               │
      "0" OK        "0228"          "2"/"1"
    Registrada    Desest.Auto      Analizar
        │               │               │
        ↓               ↓               ↓
   ┌─────────┐    ┌─────────┐    ┌─────────┐
   │Estado 20│    │Estado 30│    │Estado 44│
   └────┬────┘    └─────────┘    └────┬────┘
        │         Desestimado         │
        │         📧 Email            │ Error
        │         Nueva soli          │ Reintento 02:00
        │
🤖 Consultas c/30 min
        │
        ↓ (1-4 semanas)
        │
  ¿Respuesta Madrid?
        │
    ┌───┴────────────┐
    │                │
Autorizado    Desestimado
    │                │
    ↓                ↓
┌─────────┐    ┌─────────┐
│Estado 40│    │Estado 30│
└─────────┘    └─────────┘
✅ DE ALTA     ❌ NO ALTA
📧 Email       📧 Email
               Nueva soli
```

### FLUJO MODIFICACIÓN (Sobre Autorizado)

```
┌──────────────────────────────────────────────────────────┐
│  PROCEDIMIENTO YA AUTORIZADO (Estado 40)                 │
└──────────────────────────────────────────────────────────┘
                        │
        🤖 Llega cambio desde CAIB (automático)
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 33│ CANVI_PENDENT_REVISAR
                  └─────┬────┘
                        │
    👤 Tramitador: 📋 Revisar cambios
                   📋 Verificar normativa nueva
                   ✅ Aprobar (15-30 min)
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 19│ PENDENT_ENVIAR_MADRID
                  └─────┬────┘
                        │
         👤 Tramitador: 📤 Click "Enviar a Madrid"
                        │
         🤖 Sistema: 🔍 Detecta dataAutoritzacio != NULL
                    💾 Genera XML MODIFICACIÓN (solo cambios)
                    📤 Envía ActualizacionProcedimiento
                        │
                        ↓
                  ┌──────────┐
                  │ Estado 20│ PENDENT_AUTORITZAR
                  └─────┬────┘
                        │
              🤖 Consultas c/30 min
                        │
                        ↓ (días/semanas)
                        │
              ¿Respuesta Madrid?
                        │
            ┌───────────┴───────────┐
            │                       │
        Autorizado              Rechazado
        (cambios OK)           (cambios KO)
            │                       │
            ↓                       ↓
       ┌─────────┐             ┌─────────┐
       │Estado 40│             │Estado 40│ ⚠️ Se mantiene
       └─────────┘             └─────────┘
       ✅ TODO DE ALTA         ✅ ANTERIOR de alta
       📧 Email éxito          📧 Email rechazo
                               ⚠️ Servicios anteriores
                                  siguen funcionando
```

---

## 🌐 SERVICIOS SOAP CON MADRID

### 1. SolicitudAutorizacion (ALTA)

**Cuándo:** Primera vez que se solicita un procedimiento (`dataAutoritzacio` = NULL)

**XML enviado:**
- Procedimiento completo (código, nombre, tipo, descripción)
- Todos los servicios solicitados
- Normativa legal
- Documentos en Base64 (PDF firmado, convenios, etc.)
- Contactos
- Tipo de consentimiento

**Clase:** `PinbalUtilsAltaLogicaEJB`

**Método:** `altaSolicitudApiPinbal()`

### 2. ConsultaEstadoSolicitud (CONSULTA)

**Cuándo:** Automáticamente cada 30 minutos para solicitudes en estado 20

**XML enviado:**
- Código procedimiento
- CIF entidad

**Respuesta:**
- Estado actual en Madrid (0-8)
- Observaciones si las hay

**Clase:** `PinbalUtilsConsultaLogicaEJB`

**Método:** `consultaEstatApiPinbal()`

### 3. ActualizacionProcedimiento (MODIFICACIÓN)

**Cuándo:** Cambios sobre procedimiento ya autorizado (`dataAutoritzacio` != NULL)

**XML enviado:**
- Código procedimiento
- **Solo los cambios:**
  - Servicios añadidos
  - Servicios eliminados
  - Nueva normativa (si cambió)
  - Nuevos documentos (si hay)

**Clase:** `PinbalUtilsModificacioLogicaEJB`

**Método:** `modificacioProcedimentApiPinbal()`

### Cliente SOAP

**Clase:** `PinbalAdminSolicitudsApi`

**Configuración:**
- Endpoint: Configurado en properties
- Certificados: PKI para autenticación
- Timeout: 30 segundos
- Retry: Gestionado por PinbalAdmin (no por SOAP)

---

## 📨 RESPUESTAS DE MADRID

### RESPUESTAS INMEDIATAS (Al enviar ALTA/MODIFICACIÓN)

#### ✅ Código "0" - Registrada OK (90% de casos)

**Significado:** Solicitud aceptada, en cola para revisión humana

**Sistema hace:**
- Estado 19 → 20
- Crea/actualiza InfoMadrid:
  - `estatProcediment` = 20
  - `estatAutoritzacio` = 0
  - `dataEnviament` = ahora
- Registra evento: "Solicitud enviada correctament"
- Inicia consultas automáticas cada 30 min

**Código:**
```java
case "0": // ESTAT_REGISTRADA_OK
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
    solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR);
    afegirEventSolicitudEnviada(solicitud, "Solicitud Enviada a Madrid correctament");
```

---

#### 🔴 Código "0228" - Desestimación Automática

**Significado:** Robot de Madrid detectó errores, rechaza automáticamente

**Errores típicos:**
- Nombre fichero duplicado
- Servicio no existe en catálogo PID
- Código procedimiento duplicado
- Campos obligatorios vacíos

**Sistema hace:**
- Estado 19 → 30
- InfoMadrid:
  - `estatAutoritzacio` = 8 (Desestimado)
  - `missatge` = Error de Madrid
- 📧 Email a entidad (NO legible, solo notificación web)
- Registra evento: "Desestimación automática"
- Marca servicios estado 60 (DESESTIMAT)

**Código:**
```java
case "0228": // ESTAT_VALIDACION_KO
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ESMENES);
    solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_DESESTIMAT);
    avisarContacteSolicitudDesestimada(solicitud, respostaMadrid, "AUTORITZACIÓ");
```

**Solución:** Entidad debe crear NUEVA solicitud corrigiendo el error

---

#### ⚠️ Código "2" Error "01" - Procedimiento Duplicado

**Significado:** Madrid ya tiene este procedimiento

**Sistema hace (AUTOMÁTICO):**
- ✅ Lo acepta como válido
- Estado 19 → 20
- Registra: "Procediment duplicat. Estat actualitzat."
- Continúa con consultas normales

**Código:**
```java
if (ERROR_PROCEDIMIENTO_DUPLICADO.equals(error.getCodigo())) {
    duplicat = true;
}
if (duplicat) {
    estatSoli = Constants.SOLI_ESTAT_PENDENT_AUTORITZAR;
    estatAuth = Constants.ESTAT_PINBAL_PENDENT_TRAMITAR;
    afegirEventSolicitudEnviada(solicitud, "Procediment duplicat. Estat actualitzat.");
}
```

---

#### ⚠️ Código "2" Error "27" - Ya Dado de Alta

**Significado:** Madrid dice que este procedimiento YA está autorizado

**Sistema hace (AUTOMÁTICO):**
- Vuelve a Estado 19
- **Marca `dataAutoritzacio` = ahora**
- Registra: "Procediment ja donat d'alta. Cal fer MODIFICACIÓ."

**Código:**
```java
if (ERROR_PROCEDIMIENTO_YA_DE_ALTA.equals(error.getCodigo())) {
    dadoDeAlta = true;
}
if (dadoDeAlta) {
    estatSoli = Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID;
    estatAuth = Constants.ESTAT_PINBAL_AUTORITZAT;
    infoMadrid.setDataAutoritzacio(new Timestamp(System.currentTimeMillis()));
    afegirEventSolicitudEnviada(solicitud, "Procediment ja donat d'alta. Cal fer MODIFICACIÓ.");
}
```

**Resultado:** Próxima vez se enviará como MODIFICACIÓN

---

#### ⚡ Código "2" Otros / Timeout - Error Técnico

**Significado:** Problema técnico (XML, comunicación, certificado, etc.)

**Sistema hace:**
- Estado 19 → 44
- InfoMadrid: `estatAutoritzacio` = -2 (ERROR)
- Guarda error completo en logs
- **Programa reintento automático 02:00 AM**

**Código:**
```java
default:
    log.warn("Codi d'estat no controlat: " + codiEstat);
    estatSoli = Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID;
    estatAuth = Constants.ESTAT_PINBAL_ERROR;
    respostaMadrid = "Error no controlat: " + codiEstat;
```

---

### RESPUESTAS EN CONSULTAS (Cada 30 minutos)

#### 📊 "Pendiente" (Estados 0, 2, 6 de Madrid)

**Significado:** Madrid aún no lo revisó

**Sistema hace:**
- Mantiene Estado 20
- Actualiza `dataConsulta` = ahora
- Guarda en logs
- Seguirá consultando

**Duración típica:** 1-4 semanas

---

#### ✅ "Autorizado" (Estado 7 de Madrid)

**Significado:** ¡Madrid aprobó! Procedimiento DADO DE ALTA

**Sistema hace:**
1. Estado 20 → 40
2. InfoMadrid:
   - `estatAutoritzacio` = 7
   - `dataAutoritzacio` = ahora (SOLO si es NULL, primera vez)
3. Cada servicio:
   - `autoritzat` = true
   - `estatSolicitudServeiID` = 50 (AUTORITZAT)
   - `dataAutoritzacio` = ahora
4. 📧 Email a entidad: "Procediment Autoritzat"
5. Registra evento: "Procedimiento autorizado por Madrid"

**Código:**
```java
if (estatMadrid == 7) { // ESTAT_PINBAL_AUTORITZAT
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
    if (infoMadrid.getDataAutoritzacio() == null) {
        infoMadrid.setDataAutoritzacio(new Timestamp(System.currentTimeMillis()));
    }
    marcarServeisAutoritzats(solicitud);
    enviarEmailAutoritzacio(solicitud);
}
```

---

#### ⚠️ "Autorizado Parcialmente" (Estado 39)

**Significado:** Algunos servicios autorizados, otros no

**Sistema hace:**
1. Estado 20 → 39 (informativo)
2. Marca servicios individualmente:
   - Autorizados → estado 50 ✅
   - Rechazados → estado 60 ❌
3. Guarda motivos en `missatge`
4. 📧 Email informativo con detalles

**Ejemplo:**
- Solicitud: DNI + Residencia + Vida Laboral
- Resultado: DNI ✅ + Residencia ✅ + Vida Laboral ❌

**Solución:** Entidad puede usar los autorizados, hacer MODIFICACIÓN para los rechazados

---

#### ❌ "Desestimado" (Estados 3, 8 de Madrid)

**Significado:** Operador humano revisó y rechazó

**Motivos típicos:**
- Normativa insuficiente
- Consentimiento incorrecto
- Nombre procedimiento genérico
- Justificación insuficiente
- Fuera de competencias

**Sistema hace:**
1. Estado 20 → 30
2. InfoMadrid:
   - `estatAutoritzacio` = 8
   - `missatge` = Motivo detallado
3. Servicios → estado 60 (DESESTIMAT)
4. Registra evento: "Desestimado por Madrid"
5. 📧 Notificación web (NO email, mensajes de Madrid ilegibles)

**Código:**
```java
if (estatMadrid == 3 || estatMadrid == 8) { // DESESTIMAT
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ESMENES);
    infoMadrid.setMissatge(observacionesMadrid);
    // NO se envía email, solo notificación web
    enviarMissatgeAlSolicitant(solicitud, asumpte, missatge, false); // false = no email
}
```

**Solución:** Entidad debe crear NUEVA solicitud con correcciones

---

## 🤖 PROCESOS AUTOMÁTICOS

### Scheduler Consultas Estado (Cada 30 minutos)

**Clase:** `SchedulerConsultaEstatSolicitudPID`

**Configuración:**
```java
@PostConstruct
public void init() {
    String horaStr = "14";  // Cada hora desde las 14
    String minuteStr = "00"; // En punto
    scheduleTask(horaStr, minuteStr);
}
```

**Funcionamiento:**
```java
@Timeout
public void onTimeout(Timer timer) {
    // 1. Buscar solicitudes en estado 20
    Where where = SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
    List<Solicitud> solicitudes = solicitudLogicaEjb.select(where);
    
    // 2. Para cada una
    for (Solicitud solicitud : solicitudes) {
        // Consultar a Madrid
        Retorno retorno = consultaEstatApiPinbal(titular, funcionario, solicitud);
        EstadoProcedimiento estat = retorno.getProcedimiento().getEstadoProcedimiento();
        
        // 3. Procesar respuesta
        informarContacteCanvisEstat(solicitud, estat);
    }
}
```

**Acciones según respuesta:**
- Pendiente (0,2,6) → Mantiene estado 20, actualiza `dataConsulta`
- Autorizado (7) → Cambia a 40, email, marca servicios
- Desestimado (3,8) → Cambia a 30, guarda motivo

---

### Scheduler Reintentos (02:00 AM cada día)

**Clase:** `SchedulerReintentarEnviamentsMadrid`

**Configuración:**
```java
@PostConstruct
public void init() {
    String horaStr = "2";   // 02:00 AM
    String minuteStr = "0";
    scheduleTask(horaStr, minuteStr);
}
```

**Funcionamiento:**
```java
@Timeout
public void onTimeout(Timer timer) {
    // 1. Buscar solicitudes en error
    Where where = SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
    List<Solicitud> solicitudes = solicitudLogicaEjb.select(where);
    
    // 2. Para cada una
    for (Solicitud solicitud : solicitudes) {
        InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(solicitud.getInfomadridid());
        
        // 3. Verificar reintentos
        if (infoMad.getIntents() < 3) {
            // Volver a estado 19 para reenviar
            solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID);
            infoMad.setIntents(infoMad.getIntents() + 1);
            
            // Se reenviará en el próximo ciclo normal
        } else {
            // 3 intentos fallidos → Alertar administrador
            log.error("Solicitud " + solicitud.getSolicitudID() + " falló 3 veces. Requiere intervención manual.");
        }
    }
}
```

**Máximo reintentos:** 3

---

## 📧 NOTIFICACIONES Y EMAILS

### Tipos de Eventos

```java
public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // Solo interno
public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1;  // Con email
public static final int EVENT_TIPUS_COMENTARI_CONTACTE = 2;           // Sin email (web)
public static final int EVENT_TIPUS_COMENTARI_SUPORT = -2;            // Soporte
public static final int EVENT_TIPUS_CONSULTA_A_CEDENT = -3;           // Cedentes
public static final int EVENT_TIPUS_CEDENT_RESPOSTA = 3;              // Respuesta cedente
```

### Emails Automáticos a la Entidad

**✅ SE ENVÍA EMAIL:**

1. **Autorizado (Estado 40)**
   ```java
   Tipo: EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC (1)
   Asunto: "PROCÉS AUTORITZACIÓ PROCEDIMENT [código]. Procediment Autoritzat."
   Mensaje: "La seva sol·licitud ha estat autoritzada. Ja pot procedir a realitzar els tràmits."
   Email: solicitud.getPersonaContacteEmail()
   ```

**❌ NO SE ENVÍA EMAIL (solo notificación web):**

1. **Desestimado (Estado 30)**
   ```java
   Tipo: EVENT_TIPUS_COMENTARI_CONTACTE (2)
   Asunto: "PROCÉS AUTORITZACIÓ PROCEDIMENT [código]. Requereix esmenes."
   Mensaje: HTML con motivo + enlace
   Email: null // NO se envía
   Destinatario: null
   ```
   
   **Razón:** Mensajes de Madrid son muy técnicos/ilegibles
   
   **Solución:** Tramitador ve notificación web y contacta manualmente con la entidad

### Parámetro `enviarEmailAlContacte`

```java
// Método común para enviar mensajes
void enviarMissatgeAlSolicitant(Solicitud solicitud, String asunto, String mensaje, 
                                 boolean enviarEmailAlContacte) {
    if (enviarEmailAlContacte) {
        // Tipo 1: Se envía email
        event.setTipusEvent(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);
        event.setDestinatariEmail(solicitud.getPersonaContacteEmail());
    } else {
        // Tipo 2: Solo web (notificación para tramitadores)
        event.setTipusEvent(Constants.EVENT_TIPUS_COMENTARI_CONTACTE);
        event.setDestinatariEmail(null);
        event.setNoLlegit(true);
    }
}
```

**Uso actual:**
- Autorizaciones: `enviarEmailAlContacte = true` ✅
- Desestimaciones: `enviarEmailAlContacte = false` ❌

### Eventos Internos (Sin Email)

**Todos los cambios de estado se registran:**

1. **Solicitud creada** (Estado 5)
2. **Enviada a firmar** (Estado 11 → 15)
3. **Firma recibida** (Estado 15 → 19)
4. **Enviada a Madrid** (Estado 19 → 20)
5. **Consultas periódicas** (logs)
6. **Cambios de estado** (20 → 40/30)
7. **Reintentos** (44 → 19)

---

## 🗄️ ENTIDADES DE BASE DE DATOS

### Solicitud

**Tabla:** `pad_solicitud`

**Campos clave:**
```java
solicitudID              // PK
procedimentCodi          // ej: "AYUDALQ2026"
procedimentNom           // ej: "Ayudas al alquiler vivienda 2026"
procedimentTipus         // Tipo (ayudas, becas, etc.)
codiDescriptiu           // Descripción larga

estatSolicitud           // 5, 11, 15, 19, 20, 30, 33, 39, 40, 44, 60
estatpinbal              // Estado Madrid (0-8)
infomadridid             // FK a InfoMadrid ⭐

organid                  // FK a Organ (entidad local)
denominacio              // Nombre entidad
nif                      // CIF entidad
dir3                     // Código DIR3

dataInici                // Fecha creación
dataFi                   // Fecha cierre (opcional)
dataCaducitat            // Fecha caducidad procedimiento
dataAutoritzacio         // ⭐ CRÍTICO: Primera autorización (NO cambia)

operador                 // Tramitador asignado
creador                  // Quién creó la solicitud

personaContacte          // Nombre contacto entidad
personaContacteEmail     // Email contacto

consentiment             // Tipo: "si" / "no-oposicio"
normativa                // Normativa legal

notes                    // Observaciones internas
```

### InfoMadrid ⭐ CRÍTICO

**Tabla:** `pad_infomadrid`

**Campos:**
```java
infoMadridID             // PK
codi                     // Código procedimiento (igual que Solicitud)

estatProcediment         // Estado PinbalAdmin (19, 20, 30, 40, 44)
estatAutoritzacio        // Estado Madrid (0-8)
                         //   0,2,6 = Pendiente
                         //   7 = Autorizado
                         //   3,8 = Desestimado

missatge                 // Observaciones/errores de Madrid (max 1024 chars)
consulta                 // Texto consulta enviado (opcional)

dataEnviament            // Cuándo se envió a Madrid
dataConsulta             // Última consulta realizada
dataAutoritzacio         // ⭐ CRÍTICO: Primera autorización

intents                  // Número de reintentos si error

titularNom               // Quien firmó (Director)
titularNif               // NIF firmante
```

**Relación:** 1 Solicitud → 1 InfoMadrid

**Uso:**
- Se crea al enviar a Madrid (Estado 19 → 20)
- Se actualiza en cada consulta
- `dataAutoritzacio` se establece SOLO la primera vez que autoriza
- `missatge` guarda errores, observaciones, motivos de desestimación

### SolicitudServei

**Tabla:** `pad_solicitudservei`

**Campos:**
```java
solicitudServeiID        // PK
solicitudID              // FK a Solicitud
serveiID                 // FK a Servei (DNI, Residencia, etc.)

estatSolicitudServeiID   // Estado del servicio:
                         //   10 = REBUT
                         //   30 = FIRMAT
                         //   40 = PENDENT_AUTORITZAR
                         //   50 = AUTORITZAT ✅
                         //   60 = DESESTIMAT ❌

autoritzat               // Boolean: ¿está autorizado?
dataAutoritzacio         // Cuándo se autorizó este servicio específico

notes                    // Observaciones del servicio
```

**Relación:** 1 Solicitud → N SolicitudServei

**Uso:**
- Al autorizar (Estado 20 → 40): Todos pasan a estado 50
- En autorización parcial: Algunos 50, otros 60
- En desestimación: Todos pasan a 60

### Servei (Catálogo)

**Tabla:** `pad_servei`

**Servicios típicos:**
- SVCDGP - Datos de identidad (DNI)
- SVCTGSS - Vida laboral
- SVCTGSS2 - Afiliación Seguridad Social
- SVCAEAT - Datos fiscales
- SVCSEC - Residencia
- etc.

### Event (Trazabilidad)

**Tabla:** `pad_event`

**Campos:**
```java
eventID                  // PK
solicitudID              // FK a Solicitud (puede ser NULL)
tipusEvent               // -1, 1, 2, -2, -3, 3
comentari                // Texto del evento
dataCreacio              // Timestamp
persona                  // Quién generó el evento
noLlegit                 // Boolean: ¿sin leer?
destinatariEmail         // Email destino (si se envía)
```

---

## 💻 CLASES PRINCIPALES DEL CÓDIGO

### Lógica de Negocio (EJBs)

#### PinbalUtilsAltaLogicaEJB
**Paquete:** `org.fundaciobit.pinbaladmin.logic.utils.pinbalutils`

**Responsabilidad:** Gestión envío ALTA a Madrid

**Métodos clave:**
```java
// Enviar ALTA a Madrid
Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario, 
                                  Solicitud solicitud, String CIF)

// Generar datos para XML ALTA
Solicitud getDadesSolicitudApiPinbalAlta(SolicitudJPA soli)

// Procesar respuesta inmediata de Madrid
void processarRespostaPinbalAlta(Solicitud solicitud, Respuesta resposta, 
                                  ScspTitular titular, ScspFuncionario funcionario, 
                                  InfoMadridJPA infoMadrid)
```

**Lógica interna:**
```java
// Detectar código respuesta
switch (codiEstat) {
    case "0":  // OK
        solicitud.setEstatSolicitud(SOLI_ESTAT_PENDENT_AUTORITZAR);
        break;
    case "0228":  // Desestimación automática
        solicitud.setEstatSolicitud(SOLI_ESTAT_ESMENES);
        avisarContacteSolicitudDesestimada(solicitud);
        break;
    case "2":  // Analizar error
        if (duplicat) { /* Aceptar */ }
        else if (dadoDeAlta) { /* Marcar autorizado */ }
        else { /* Error técnico */ }
        break;
    default:  // Error técnico
        solicitud.setEstatSolicitud(SOLI_ESTAT_ERROR_ENVIANT_MADRID);
}
```

---

#### PinbalUtilsConsultaLogicaEJB
**Responsabilidad:** Consultas periódicas estado a Madrid

**Métodos clave:**
```java
// Consultar estado actual
Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, 
                                Long solicitudID)

// Procesar respuesta consulta
void processarRespostaConsulta(Solicitud solicitud, EstadoProcedimiento estat)

// Informar cambios de estado a entidad
void informarContacteCanvisEstat(Solicitud solicitud, Long estatNou)
```

**Lógica estados Madrid:**
```java
Long estatMadrid = estat.getEstado();

if (estatMadrid == 0 || estatMadrid == 2 || estatMadrid == 6) {
    // Pendiente → mantener estado 20
    log.info("Aún pendiente en Madrid");
    
} else if (estatMadrid == 7) {
    // Autorizado → cambiar a 40
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT);
    marcarServeisAutoritzats(solicitud);
    enviarEmailAutoritzacio(solicitud);
    
} else if (estatMadrid == 3 || estatMadrid == 8) {
    // Desestimado → cambiar a 30
    solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ESMENES);
    guardarMotivoDesestimacion(solicitud, estat.getObservaciones());
}
```

---

#### PinbalUtilsModificacioLogicaEJB
**Responsabilidad:** Gestión MODIFICACIONES sobre autorizados

**Métodos clave:**
```java
// Enviar MODIFICACIÓN a Madrid
Respuesta modificacioProcedimentApiPinbal(ScspTitular titular, ScspFuncionario funcionario,
                                           Solicitud solicitud, String CIF)

// Generar XML con solo cambios
Solicitud getDadesSolicitudApiPinbalModificacio(SolicitudJPA soli)

// Procesar respuesta MODIFICACIÓN
void processarRespostaPinbalModificacio(Solicitud solicitud, Respuesta resposta)
```

**Diferencia con ALTA:**
```java
// XML MODIFICACIÓN solo incluye:
- Servicios añadidos (nuevos)
- Servicios eliminados (discontinuados)
- Nueva normativa (si cambió)
- Nuevos documentos (si hay)

// NO incluye:
- Servicios que ya estaban autorizados y no cambian
```

---

#### PinbalUtilsCommon
**Responsabilidad:** Métodos comunes a ALTA y MODIFICACIÓN

**Métodos clave:**
```java
// Actualizar InfoMadrid
void actualizarInfoMadrid(Solicitud solicitud, InfoMadridJPA infoMadrid, 
                          Long estatSoli, Long estatAuth, String missatge)

// Enviar mensaje a solicitante
void enviarMissatgeAlSolicitant(Solicitud solicitud, String asunto, String mensaje,
                                 boolean enviarEmailAlContacte)

// Registrar evento envío
void afegirEventSolicitudEnviada(Solicitud solicitud, String mensaje, String tipus)

// Marcar servicios como autorizados
void marcarServeisAutoritzats(Solicitud solicitud)
```

---

#### NotificacionLogicaEJB
**Responsabilidad:** Centraliza envío de notificaciones

**Métodos clave:**
```java
// Enviar email autorización
void notificarAutoritzacio(Solicitud solicitud)

// Enviar email desestimación (actualmente desactivado)
void notificarDesestimacio(Solicitud solicitud, String motivo)
```

---

### Schedulers

#### SchedulerConsultaEstatSolicitudPID
**Configuración:**
```java
@Singleton
@Startup
public class SchedulerConsultaEstatSolicitudPID {
    
    @PostConstruct
    public void init() {
        // Cada 30 minutos (configurable)
        scheduleTask("*", "*/30");
    }
    
    @Timeout
    public void onTimeout(Timer timer) {
        obtenirEstatsSolicitudsPinbal();
    }
}
```

---

#### SchedulerReintentarEnviamentsMadrid
**Configuración:**
```java
@Singleton
@Startup
public class SchedulerReintentarEnviamentsMadrid {
    
    @PostConstruct
    public void init() {
        // A las 02:00 AM cada día
        scheduleTask("2", "0");
    }
    
    @Timeout
    public void onTimeout(Timer timer) {
        enviarSolicitudsAmbErrorMadrid();
    }
}
```

---

### API Client

#### PinbalAdminSolicitudsApi
**Paquete:** `org.fundaciobit.pinbaladmin.apiclientpeticions`

**Responsabilidad:** Cliente SOAP para Madrid

**Métodos:**
```java
// ALTA
Respuesta altaSolicitudPinbalApi(Solicitud solicitud, ScspTitular titular, 
                                  ScspFuncionario funcionario)

// CONSULTA
Retorno consultaEstatSolicitud(String codigoProcedimiento, String CIF)

// MODIFICACIÓN
Respuesta actualizacionProcedimiento(Solicitud solicitud, ScspTitular titular,
                                      ScspFuncionario funcionario)
```

---

### Controllers (Back)

#### SolicitudOperadorController
**Ruta:** `/operador/solicitud`

**Acciones:**
- Listado solicitudes
- Ver/editar solicitud
- Marcar como recibida (Estado 5 → 11)
- Enviar a firmar (Estado 11 → 15)
- Cerrar solicitud (→ Estado 60)

#### AltaSolicitudPinbalOperadorController
**Ruta:** `/operador/altasolicitudpinbal`

**Acciones:**
- Enviar a Madrid (Estado 19 → 20/30/44)
- Reintentar envío (Estado 44 → 19)

#### SolicitudFullViewController
**Ruta:** `/operador/solicitudfullview`

**Vista completa de solicitud con:**
- Datos completos
- Servicios solicitados
- Estado actual
- Histórico de eventos
- InfoMadrid

---

## 📊 TABLA COMPARATIVA: ERROR TÉCNICO vs DESESTIMACIÓN

| Aspecto | Error Técnico ⚡ | Desestimación Automática 🔴 | Desestimación Manual 🔴 |
|---------|-----------------|---------------------------|------------------------|
| **¿Qué es?** | Problema tecnológico | Robot Madrid rechaza | Humano Madrid rechaza |
| **Estado** | 44 (ERROR) | 30 (ESMENES) | 30 (ESMENES) |
| **¿Llegó a Madrid?** | ❌ NO | ✅ SÍ | ✅ SÍ |
| **Cuándo ocurre** | Al enviar (19→44) | Al enviar (19→30) | En consultas (20→30) |
| **Causas** | API caída, timeout, certificados, XML mal formado | Ficheros duplicados, servicios no existen, formato incorrecto | Normativa incorrecta, justificación insuficiente, fuera competencias |
| **Tratamiento** | Reintento automático 02:00 | Email notificación web (NO email) | Email notificación web (NO email) |
| **Solución** | Automática (hasta 3 reintentos) | Corregir dato técnico, nueva solicitud | Subsanar contenido, nueva solicitud |
| **¿Grave?** | ⚠️ No grave (se reintenta) | 🔴 Grave (requiere corrección) | 🔴 Grave (requiere corrección) |
| **¿Está de alta?** | - | ❌ NO | ❌ NO |
| **Máximo reintentos** | 3 | - | - |

**⚠️ DIFERENCIA CLAVE:**
- **Error Técnico (44):** El problema es NUESTRO (tecnología, infraestructura) - NO llegó bien a Madrid
- **Desestimación (30):** El problema es del CONTENIDO - Llegó bien pero Madrid lo rechazó

---

## 🎭 CASOS ESPECIALES

### 1. Modificación Rechazada

**Situación:**
- Procedimiento en Estado 40 (autorizado)
- Se hace MODIFICACIÓN (añadir servicios)
- Madrid rechaza los cambios

**Resultado:**
- ⚠️ **El procedimiento SE MANTIENE en Estado 40**
- ✅ Los servicios anteriores siguen funcionando
- ❌ Los servicios nuevos NO se autorizan

**Ejemplo:**
```
Estado inicial: Procedimiento 40 con DNI + Residencia
Modificación: Añadir Vida Laboral
Madrid rechaza: Vida Laboral (normativa insuficiente)

Resultado final:
- Procedimiento: Estado 40 ✅
- DNI: Autorizado ✅
- Residencia: Autorizado ✅
- Vida Laboral: NO autorizado ❌

La entidad puede seguir consultando DNI y Residencia sin problemas
```

---

### 2. Estado 39 - Autorizado Parcial

**NO es un estado de transición, es INFORMATIVO**

**Significado:**
- El procedimiento **está de alta**
- Algunos servicios autorizados, otros no
- Es un caso especial que se considera "DE ALTA"

**Cuándo ocurre:**
- Al hacer ALTA o MODIFICACIÓN
- Madrid autoriza algunos servicios pero rechaza otros

**Gestión:**
```java
// Marcar servicios individualmente
for (SolicitudServei servei : servicios) {
    if (servei en lista autorizados) {
        servei.setEstatSolicitudServeiID(50); // AUTORITZAT
        servei.setAutoritzat(true);
    } else {
        servei.setEstatSolicitudServeiID(60); // DESESTIMAT
        servei.setAutoritzat(false);
    }
}
```

**Email enviado:**
- Detalla servicios autorizados ✅
- Detalla servicios pendientes/rechazados ❌
- Sugiere hacer MODIFICACIÓN para completar

---

### 3. Procedimiento Fusionado (Estado -5)

**Situación:**
- Dos o más solicitudes del mismo procedimiento
- Se fusionan en una sola

**Estados:**
- `-4` = FUSIONADA_REVISAR (pendiente revisión)
- `-5` = FUSIONADA (ya fusionada)

**Filtrado:**
```java
// Se ocultan del listado normal
Where wNoFusionades = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_FUSIONADA);
```

---

### 4. Solicitud Manual (Estado 21/41)

**Situación:**
- Solicitudes gestionadas fuera del sistema
- Enviadas manualmente a Madrid (sin SOAP)

**Estados:**
- `21` = PENDENT_AUTORITZAR_Manual
- `41` = AUTORITZAT_Manual

**Uso:**
- Para procedimientos históricos
- Cuando hubo problemas técnicos
- NO se consultan automáticamente

---

### 5. Estados de Cedentes (12, 16)

**Para solicitudes que requieren autorización de cedentes adicionales**

**Estados:**
- `12` = PENDENT_Enviar_Cedents
- `16` = PENDENT_Firma_Cedent

**Proceso:**
- Similar a firma Director
- Pero para entidades externas (INE, Padrón, etc.)
- Menos común

---

### 6. Caducidad Automática

**Campo:** `dataCaducitat`

**Comportamiento:**
- Si se establece, el procedimiento caduca automáticamente
- NO se implementa revocación en Madrid
- Solo informativo en PinbalAdmin

**Gestión:**
```java
if (solicitud.getDataCaducitat() != null) {
    if (new Date().after(solicitud.getDataCaducitat())) {
        // Procedimiento caducado
        // Se puede marcar como TANCAT (60)
    }
}
```

---

## ⏱️ TIEMPOS Y DURACIONES

### Por Estado

| Estado | Duración | Quién |
|--------|----------|-------|
| **5** | 5-10 minutos | Tramitador marca recibida |
| **11** | 30 min - 2 horas | Tramitador revisa |
| **15** | Horas - días | Director firma |
| **19** | Segundos | Click + envío automático |
| **20** | **1-4 semanas** | Madrid procesa (lo más largo) |
| **30** | - | Estado final (desestimado) |
| **33** | 15-30 minutos | Tramitador revisa modificación |
| **40** | Indefinido | Estado final (autorizado) |
| **44** | 1 día | Reintento automático 02:00 |

### Promedio Proceso Completo

```
Entrada (5): Automático desde CAIB
    ↓
Revisión (5→11): 5-10 min
    ↓
Preparación (11): 30 min - 2 horas
    ↓
Firma (11→15): 1 hora - 3 días
    ↓
Espera firma (15): Horas - días
    ↓
Envío (15→19): Automático al recibir firma
    ↓
SOAP Madrid (19→20): Segundos
    ↓
Espera Madrid (20): 1-4 semanas ⏰ ← LO MÁS LARGO
    ↓
Autorización (20→40): Automático
    
TOTAL: 2-6 semanas
PROMEDIO: 3 semanas
```

### Tiempos de Consulta

- **Frecuencia consultas:** Cada 30 minutos (configurable)
- **Timeout SOAP:** 30 segundos
- **Hora reintentos:** 02:00 AM cada día
- **Máximo reintentos:** 3

---

## 🔄 DIFERENCIAS PREALTAS VS PINFO

| Aspecto | PREALTAS | PINFO |
|---------|----------|-------|
| **Objetivo** | Autorizar procedimientos ante Madrid | Gestionar permisos usuarios internos |
| **Ámbito** | Procedimientos administrativos | Usuarios de la API Pinbal |
| **Comunicación** | Web Services SOAP con Madrid | API REST interna |
| **Duración** | Semanas (revisión Madrid) | Inmediato |
| **Estados** | 5, 11, 15, 19, 20, 30, 40, etc. | Estados propios de PINFO |
| **Documentación** | Este documento | `TRAMITE_PINFO_REFERENCIA.md` |

**⚠️ IMPORTANTE:** Son dos sistemas completamente diferentes. NO confundir.

---

## 📋 CHECKLIST DEL TRAMITADOR

### Al Recibir Solicitud Nueva (Estado 5)
- [ ] ✅ Marcar como recibida (click botón)
- [ ] 📋 Verificar que tiene todos los documentos
- [ ] 📋 Revisar que el formulario esté completo
- [ ] 📋 Comprobar que los ficheros se abren correctamente

### Antes de Enviar al Director (Estado 11)
- [ ] 📋 **Normativa legal:**
  - [ ] Existe y está vigente (no derogada)
  - [ ] Rango legal suficiente (Ley > RD > Reglamento > Ordenanza)
  - [ ] Apropiada para el tipo de datos solicitados
- [ ] 📋 **Nombre procedimiento:**
  - [ ] Descriptivo y claro (ej: "Ayudas al alquiler 2026" ✅)
  - [ ] NO genérico (ej: "Ayudas 2026" ❌)
- [ ] 📋 **Justificación:**
  - [ ] Explica POR QUÉ necesita cada dato personal
  - [ ] Es proporcional (no pide más datos de los necesarios)
  - [ ] Está dentro de las competencias de la entidad
- [ ] 📋 **Ficheros adjuntos:**
  - [ ] Nombres únicos (NO "FORMULARIO.PDF" repetido)
  - [ ] Todos los obligatorios presentes
  - [ ] Formato correcto (PDF legibles)
  - [ ] Sin ficheros corruptos
- [ ] 📋 **Servicios solicitados:**
  - [ ] Todos tienen normativa asociada
  - [ ] Todos están justificados
  - [ ] El tipo de consentimiento es correcto para cada servicio
- [ ] 📨 **Enviar a firmar al Director**

### Al Revisar Modificación (Estado 33)
- [ ] 📋 Verificar qué servicios quiere añadir/quitar
- [ ] 📋 Si añade servicios, verificar normativa para cada uno
- [ ] 📋 Justificación de los cambios es correcta
- [ ] 📋 Comprobar que el procedimiento ya está autorizado (estado 40)
- [ ] ✅ Aprobar para envío

### Al Enviar a Madrid (Estado 19)
- [ ] 📤 Click en botón "Enviar a Madrid"
- [ ] 🔍 Verificar que aparece confirmación de envío
- [ ] 📋 Revisar que cambia a estado 20 (o verificar si hay error)

---

## 💡 CONSEJOS PRÁCTICOS PARA TRAMITADORES

### ✅ Cosas a Revisar con Especial Atención

1. **Normativa Legal (causa #1 de rechazo):**
   - ¿La ley/ordenanza permite acceder a ese dato específico?
   - ¿El rango es suficiente? (Datos fiscales → necesitan Ley o RD)
   - ¿Está vigente o fue derogada?

2. **Nombre del Procedimiento (causa #2 de rechazo):**
   - ✅ Correcto: "Ayudas al alquiler de vivienda 2026 para familias vulnerables"
   - ❌ Incorrecto: "Ayudas 2026" (demasiado genérico)
   - ❌ Incorrecto: "Trámites varios" (rechazado siempre)

3. **Justificación (causa #3 de rechazo):**
   - Para cada dato, explicar POR QUÉ es necesario
   - Ejemplo ✅: "Necesitamos el DNI para identificar inequívocamente al beneficiario"
   - Ejemplo ❌: "Necesitamos el DNI" (sin explicar por qué)

4. **Nombres de Ficheros (causa #1 de desestimación automática):**
   - ✅ "CONVENIO_AYUDAS_ALQUILER_2026.PDF"
   - ✅ "FORMULARIO_SOLICITUD_20260315.PDF"
   - ❌ "FORMULARIO.PDF" (si ya existe otro con ese nombre → rechazado)

### ❌ Errores Comunes y Cómo Evitarlos

| Error | Por qué pasa | Cómo evitarlo |
|-------|-------------|---------------|
| Fichero duplicado | Mismo nombre que otra solicitud | Añadir fecha o código: "FORM_20260315.PDF" |
| Normativa insuficiente | Ordenanza para datos fiscales | Buscar Ley o RD que permita el acceso |
| Nombre genérico | "Ayudas 2026" | Ser específico: "Ayudas comedor escolar 2026" |
| Falta justificación | No explica por qué necesita el dato | Explicar la finalidad concreta de cada dato |
| Servicio no existe | Código servicio incorrecto | Verificar en catálogo de servicios PID |

### 🕐 Tiempos de Respuesta Esperados

| Estado | Duración Esperada | ¿Es Normal Si Dura Más? |
|--------|-------------------|------------------------|
| Estado 5 → 11 | 5-10 minutos | NO (avisar si > 1 hora) |
| Estado 11 (tu revisión) | 30 min - 2 horas | Depende de complejidad |
| Estado 15 (firma DG) | Horas - 3 días | SÍ (depende del Director) |
| Estado 19 → 20 | Segundos | NO (avisar si > 5 minutos) |
| Estado 20 (Madrid) | 1-4 semanas | SÍ hasta 6 semanas |

**Si Estado 20 dura más de 6 semanas:** Contactar con Madrid para preguntar

### 📞 Cuándo Pedir Ayuda

**Al Desarrollador (JP):**
- Error en estado 44 que persiste más de 3 días
- Quieres cambiar el flujo o añadir validaciones
- Necesitas cambiar configuración (frecuencias, horarios, emails)
- Bugs o comportamientos extraños
- El sistema no consulta automáticamente (estado 20 estancado sin consultas)

**A Madrid (PID):**
- Solicitud en estado 20 más de 6 semanas sin respuesta
- Dudas sobre desestimaciones (estado 30) - ¿por qué rechazaron?
- Aclaraciones sobre normativa específica
- Dudas sobre competencias (¿es local o estatal?)

**Al Jefe/Responsable:**
- Dudas sobre si aprobar una solicitud (normativa poco clara)
- Casos excepcionales o fuera de lo habitual
- Conflictos con la entidad solicitante
- Decisiones que requieren criterio superior

### ⚠️ Casos Especiales - Qué Hacer

**La entidad quiere cambiar algo en estado 20:**
- ❌ NO se puede modificar (ya está en Madrid)
- ✅ Esperar al resultado
- ✅ Si es urgente: Contactar con Madrid por email/teléfono
- ✅ Si hay cambios tras autorización → Hacer MODIFICACIÓN

**Una modificación se rechaza (estado 33→19→20→40):**
- ✅ El procedimiento **SIGUE AUTORIZADO** con servicios anteriores
- ✅ Solo se rechazan los cambios nuevos
- ✅ La entidad puede seguir usando lo que ya tenía
- ✅ Puede volver a solicitar los cambios con mejor documentación

**Solicitud lleva más de 1 mes en estado 20:**
- 🔍 Revisar logs de consultas (¿el sistema está consultando?)
- 📞 Contactar con Madrid para preguntar estado
- 🔍 Verificar que no haya errores en las consultas

---

## 🎯 RESUMEN EJECUTIVO

### Para Tramitadores

**Solo actúas en 4 momentos:**
1. Estado 5: ✅ Marcar recibida (5-10 min)
2. Estado 11: 📋 Revisar + 📨 Enviar a DG (30 min - 2h)
3. Estado 19: 📤 Click "Enviar a Madrid" (1 click)
4. Estado 33: 📋 Revisar modificación + ✅ Aprobar (15-30 min)

**El resto es automático:** Sistema consulta, reintenta, notifica solo.

---

### Para Desarrolladores

**Clases clave:**
- `PinbalUtilsAltaLogicaEJB` - Envío ALTA
- `PinbalUtilsConsultaLogicaEJB` - Consultas periódicas
- `PinbalUtilsModificacioLogicaEJB` - Modificaciones
- `SchedulerConsultaEstatSolicitudPID` - Scheduler consultas
- `SchedulerReintentarEnviamentsMadrid` - Scheduler reintentos

**Tabla crítica:** `pad_infomadrid`

**Campo crítico:** `dataAutoritzacio` (NO cambiar nunca tras primera asignación)

---

### Para Directores

**Solo actúas en 1 momento:**
- Estado 15: ✍️ Firmar digitalmente vía PortaFIB

**Todo lo demás:** Gestión automática del sistema.

---

## 📚 REFERENCIAS

### Documentos Relacionados

- `DIAGRAMA_ESTADOS_PREALTAS.md` - Diagramas visuales detallados
- `FLUJO_AUTORIZACION_PREALTAS.md` - Guía ejecutiva del flujo
- `GUIA_RAPIDA_PREALTAS.md` - Guía para tramitadores
- `MENSAJES_AUTOMATICOS_PREALTAS.md` - Detalle completo notificaciones
- `TRAMITE_PINFO_REFERENCIA.md` - Sistema PINFO (diferente)

### Código Fuente

**Paquetes principales:**
- `org.fundaciobit.pinbaladmin.logic.utils.pinbalutils` - Lógica PREALTAS
- `org.fundaciobit.pinbaladmin.apiclientpeticions` - Cliente SOAP
- `org.fundaciobit.pinbaladmin.persistence` - Entidades JPA

### Configuración

**Archivos:**
- `config.properties` - Endpoints Madrid, certificados
- `Constants.java` - Estados, constantes del sistema

---

**Fin del Documento Maestro PREALTAS**

---

*Este documento consolida toda la información sobre el sistema PREALTAS en un único lugar para consulta rápida y completa. Actualizar cuando haya cambios significativos en el sistema.*

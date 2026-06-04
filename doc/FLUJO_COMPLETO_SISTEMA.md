# 🔄 FLUJO COMPLETO DEL SISTEMA PINBALADMIN

**Documento Maestro del Sistema Completo**  
**Última actualización:** 14 mayo 2026  
**Versión:** 1.0

---

## 📋 ÍNDICE

1. [Visión General](#visión-general)
2. [Los 3 Procesos del Sistema](#los-3-procesos-del-sistema)
3. [Flujo Completo: De SISTRA a PINFO](#flujo-completo-de-sistra-a-pinfo)
4. [Plataformas y Sistemas Externos](#plataformas-y-sistemas-externos)
5. [Relación entre Entidades](#relación-entre-entidades)
6. [Diferencias Clave entre Procesos](#diferencias-clave-entre-procesos)

---

## 🎯 VISIÓN GENERAL

PinbalAdmin es el sistema central que gestiona **3 procesos distintos pero relacionados**:

```
┌─────────────────────────────────────────────────────────────┐
│                    FLUJO COMPLETO                           │
└─────────────────────────────────────────────────────────────┘

1️⃣ TRÁMITE SISTRA
   │ Crear SOLICITUDES (procedimientos nuevos)
   │ Tablas: tramit_a, tramit_b, tramit_c, etc.
   ↓
   [Solicitud creada en BBDD]
   
2️⃣ GESTIÓN Y FIRMA
   │ Distribució → Revisión → Firma → Envío
   │ Estados: 5 → 11 → 15 → 19
   ↓
   
3️⃣ PREALTAS (Autorización Madrid)
   │ Comunicación SOAP con Madrid
   │ Estados: 19 → 20 → 40
   ↓
   [Solicitud AUTORIZADA = Procedimiento disponible]
   
4️⃣ PINFOS (Permisos de usuarios)
   │ Usuarios piden acceso a procedimientos YA autorizados
   │ Estados: 0 → 1 → 2 → 3 → 4
   ↓
   [Permisos aplicados en PINBAL]
```

---

## 🔢 LOS 3 PROCESOS DEL SISTEMA

### 1️⃣ TRÁMITE SISTRA → Crear Solicitudes/Procedimientos

**¿Qué es?**  
Sistema de tramitación externa que permite a entidades locales crear nuevas solicitudes de procedimientos administrativos.

**Objetivo:**  
Iniciar el proceso de dar de alta un nuevo procedimiento que necesite acceder a datos personales de ciudadanos.

**Tablas en BBDD:**
- `pad_tramit_a` - Datos generales
- `pad_tramit_b` - Cesión datos terceros
- `pad_tramit_c` - Datos cesionarios
- `pad_tramit_d` - Contacto autorización
- `pad_tramit_e` - Contacto auditoría
- `pad_tramit_f` - Contacto técnico
- `pad_tramit_g` - Datos del titular
- ... y más

**Resultado:**  
Se crea un objeto **`Solicitud`** en la tabla `pad_solicitud` con todos los datos del futuro procedimiento.

**Documentación:** Ver carpeta `doc/Sistra/`

---

### 2️⃣ PREALTAS → Autorización en Madrid

**¿Qué es?**  
Sistema de comunicación SOAP con la **Plataforma de Intermediación de Datos (PID)** del Estado para autorizar procedimientos.

**Objetivo:**  
Obtener autorización de Madrid para que un procedimiento pueda consultar datos personales de ciudadanos.

**Prerequisito:**  
Necesita una **Solicitud YA CREADA** (mediante trámite SISTRA).

**Fases:**
1. **Gestión Interna:**
   - Llega a Distribució (plataforma externa)
   - Operador marca como recibida (estado 5 → 11)
   - Operador revisa documentación
   - Operador envía documento resumen al titular vía PortaFIB para firma
   - Titular firma digitalmente (estado 11 → 15 → 19)

2. **Envío a Madrid:**
   - Operador hace click "Enviar a Madrid" (estado 19 → 20)
   - Sistema envía servicio SOAP `SolicitudAutorizacion` (ALTA)
   - O `ActualizacionProcedimiento` (MODIFICACIÓN)

3. **Espera Autorización:**
   - Sistema consulta estado cada 30 minutos (estado 20)
   - Madrid responde: Autorizado (40) o Desestimado (30)

**Servicios SOAP:**
- **ALTA**: Primera solicitud (`dataAutoritzacio` = NULL)
- **CONSULTA**: Verificar estado (automático)
- **MODIFICACIÓN**: Cambios sobre autorizado (`dataAutoritzacio` != NULL)

**Estados Clave:**
- **5**: PENDENT_DISTRIBUCIO (llegada desde CAIB)
- **11**: PENDENT_Enviar_Director (revisión)
- **15**: PENDENT_Firma_Director (firma titular)
- **19**: PENDENT_ENVIAR_MADRID (listo para enviar)
- **20**: PENDENT_AUTORITZAR (esperando Madrid)
- **30**: ESMENES (desestimado)
- **40**: AUTORITZAT ✅ (DADO DE ALTA)
- **60**: TANCAT (cerrado - fin del proceso)

**Resultado:**  
Solicitud pasa a estado **40 (AUTORITZAT)** = Procedimiento dado de alta en Madrid = Puede consultar datos personales en producción.

**Documentación:** [PREALTAS_REFERENCIA_COMPLETA.md](PREALTAS_REFERENCIA_COMPLETA.md)

---

### 3️⃣ PINFOS → Permisos de Usuarios

**¿Qué es?**  
Sistema de gestión de permisos para que usuarios específicos puedan acceder a procedimientos y servicios en PINBAL.

**Objetivo:**  
Dar permisos a usuarios internos para acceder a procedimientos **YA AUTORIZADOS** en Madrid.

**Prerequisito:**  
Necesita **procedimientos en estado 40 (AUTORITZAT)** - es decir, procedimientos que YA pasaron por PREALTAS.

**Flujo:**
1. **Usuario externo** solicita permisos mediante wizard
2. Selecciona usuarios, procedimientos y servicios
3. Genera PDF con la solicitud (PINFO)
4. Envía a **PortaFIB** para firma digital
5. **Responsable** (rol PFI_USER) firma el documento
6. **Operador interno** procesa la solicitud
7. Sistema aplica permisos en **PINBAL API REST**
8. Email al solicitante con resultado

**Estados:**
- **0**: CREANT (creando)
- **1**: PENDENT_FIRMA (en PortaFIB)
- **2**: PENDENT_TRAMITAR (firmado)
- **3**: TRAMITAT (permisos aplicados)
- **4**: NOTIFICAT (email enviado) ✅

**Entidades:**
- `pad_incidenciatecnica` - Incidencia base
- `pad_pinfo` - Solicitud de permisos (el PDF)
- `pad_pinfodata` - Permisos individuales (usuario × servicio)

**Resultado:**  
Usuarios pueden acceder a servicios de procedimientos en PINBAL para consultar datos personales.

**Documentación:** [TRAMITE_PINFO_REFERENCIA.md](TRAMITE_PINFO_REFERENCIA.md)

---

## 🔄 FLUJO COMPLETO: DE SISTRA A PINFO

### Secuencia Temporal Completa

```
┌──────────────────────────────────────────────────────────────────────┐
│ PASO 1: INICIAR TRÁMITE (SISTRA)                                    │
└──────────────────────────────────────────────────────────────────────┘

Entidad Local (Ayuntamiento)
    ↓
Accede a portal CAIB → Inicia trámite SISTRA
    ↓
Rellena formulario completo:
    - Datos procedimiento
    - Servicios a solicitar (DNI, Renta, etc.)
    - Contactos (autorización, auditoría, técnico)
    - Titular del procedimiento
    - Normativa legal
    - Documentos adjuntos
    ↓
Envía trámite
    ↓
Se crea registro en: pad_tramit_a, pad_tramit_b, ...
    ↓
Se crea objeto SOLICITUD en pad_solicitud
    └─ solicitudID, procedimentCodi, procedimentNom
    └─ estatSolicitud = 5 (PENDENT_DISTRIBUCIO)
    └─ dataAutoritzacio = NULL (primera vez)


┌──────────────────────────────────────────────────────────────────────┐
│ PASO 2: GESTIÓN INTERNA (DISTRIBUCIÓ + PINBALADMIN)                 │
└──────────────────────────────────────────────────────────────────────┘

Solicitud llega a DISTRIBUCIÓ (plataforma externa CAIB)
    ↓
Queda visible para operadores de PinbalAdmin
    ↓
Estado: 5 PENDENT_DISTRIBUCIO
    ↓
👤 OPERADOR: Marca como recibida
    ↓
Estado: 11 PENDENT_Enviar_Director
    ↓
👤 OPERADOR: Revisa documentación
    - Verifica que esté completa
    - Comprueba normativa legal
    - Valida justificación
    ↓
👤 OPERADOR: Envía a firmar al titular
    - Genera documento resumen PDF
    - Envía vía PortaFIB para firma digital
    ↓
Estado: 15 PENDENT_Firma_Director
    ↓
✍️ TITULAR (Director General): Firma digitalmente
    - Recibe notificación en PortaFIB
    - Firma con certificado digital
    ↓
PortaFIB devuelve documento firmado
    ↓
Estado: 19 PENDENT_ENVIAR_MADRID


┌──────────────────────────────────────────────────────────────────────┐
│ PASO 3: ENVÍO A MADRID (PREALTAS - ALTA)                            │
└──────────────────────────────────────────────────────────────────────┘

👤 OPERADOR: Click "Enviar a Madrid"
    ↓
🤖 SISTEMA: Detecta dataAutoritzacio = NULL → Es ALTA
    ↓
🤖 SISTEMA: Genera XML completo con todos los datos
    ↓
🤖 SISTEMA: Envía servicio SOAP SolicitudAutorizacion
    ↓
🏢 MADRID (robot): Validación automática
    ├─ Código "0" → Registrada OK
    ├─ Código "0228" → Desestimación automática
    └─ Código "2"/"1" → Errores específicos
    ↓
Si "0" (OK):
    Estado: 20 PENDENT_AUTORITZAR
    Se crea registro InfoMadrid:
        └─ estatProcediment = 20
        └─ estatAutoritzacio = 0 (pendiente)
        └─ dataEnviament = ahora


┌──────────────────────────────────────────────────────────────────────┐
│ PASO 4: ESPERA AUTORIZACIÓN MADRID (1-4 SEMANAS)                    │
└──────────────────────────────────────────────────────────────────────┘

🤖 SISTEMA: Consulta estado cada 30 minutos
    └─ Scheduler: SchedulerConsultaEstatSolicitudPID
    └─ Servicio SOAP: ConsultaEstadoSolicitud
    ↓
🏢 MADRID (humano): Revisión manual
    - Analiza documentación
    - Verifica normativa
    - Comprueba justificación
    - Valida servicios solicitados
    ↓
DECISIÓN:
    ├─ AUTORIZAR → estatAutoritzacio = 7
    └─ DESESTIMAR → estatAutoritzacio = 8
    ↓
🤖 SISTEMA: Detecta cambio en consulta
    ↓
Si AUTORIZADO (7):
    Estado: 40 AUTORITZAT ✅
    InfoMadrid.dataAutoritzacio = ahora (⭐ PRIMERA VEZ)
    InfoMadrid.estatAutoritzacio = 7
    📧 Email al contacto de la entidad
    ↓
    PROCEDIMIENTO DADO DE ALTA EN MADRID
    ✅ Puede consultar datos personales en producción
    ✅ Servicios activos e indefinidamente disponibles

Si DESESTIMADO (8):
    Estado: 30 ESMENES
    InfoMadrid.estatAutoritzacio = 8
    InfoMadrid.missatge = motivo rechazo
    📧 Notificación a tramitadores
    ↓
    Entidad debe crear NUEVA solicitud corrigiendo errores


┌──────────────────────────────────────────────────────────────────────┐
│ PASO 5: CIERRE DEL PROCESO DE SOLICITUD                             │
└──────────────────────────────────────────────────────────────────────┘

👤 OPERADOR: Cuando procedimiento está en estado 40 (AUTORITZAT)
    ↓
👤 OPERADOR: Marca solicitud como TANCADA
    ↓
Estado: 60 TANCAT
    ↓
📧 Se avisa al solicitante original (el que inició trámite SISTRA)
    ↓
FIN DEL PROCESO DE SOLICITUD
    ↓
Procedimiento ahora disponible para uso en PINBAL


┌──────────────────────────────────────────────────────────────────────┐
│ PASO 6: GESTIÓN DE PERMISOS (PINFOS)                                │
└──────────────────────────────────────────────────────────────────────┘

[Tiempo después, cuando procedimiento ya está en estado 40]

Usuario interno necesita acceso al procedimiento
    ↓
Accede a sistema PINFO (con token)
    ↓
Crea solicitud de permisos:
    - Selecciona usuarios (NIF)
    - Selecciona procedimientos (solo AUTORIZADOS, estado 40)
    - Selecciona servicios del procedimiento
    - Tipo: ALTA (nuevos) o BAJA (quitar)
    ↓
Genera PDF (PINFO) con la solicitud
    ↓
Envía a PortaFIB para firma de RESPONSABLE (rol PFI_USER)
    ↓
Responsable firma (estados: 0 → 1 → 2)
    ↓
👤 OPERADOR INTERNO: Procesa la solicitud (estado 2 → 3)
    ↓
🤖 SISTEMA: Conecta con PINBAL API REST
    └─ Llama a: enableServeiToProcediment()
    └─ Aplica permisos: usuario × servicio
    ↓
Estado: 3 TRAMITAT (permisos aplicados)
    ↓
📧 Email al solicitante con resultado (estado 3 → 4)
    ↓
Estado: 4 NOTIFICAT ✅
    ↓
USUARIOS PUEDEN ACCEDER A SERVICIOS EN PINBAL
```

---

## 🌐 PLATAFORMAS Y SISTEMAS EXTERNOS

### SISTRA
- **Tipo**: Sistema de tramitación externa CAIB
- **Función**: Iniciar trámites administrativos
- **Integración**: Crea registros en tablas `pad_tramit_*`
- **Resultado**: Objeto `Solicitud` en PinbalAdmin

### DISTRIBUCIÓ
- **Tipo**: Plataforma de distribución CAIB
- **Función**: Bandeja de entrada de solicitudes
- **Acceso**: Operadores de PinbalAdmin
- **Estado**: Solicitudes en estado 5 (PENDENT_DISTRIBUCIO)

### PortaFIB
- **Tipo**: Plataforma de firma digital CAIB
- **Función**: Firma electrónica de documentos
- **Uso en Solicitudes**: Firma del titular (estado 15)
- **Uso en PINFOs**: Firma del responsable (estado 1)
- **Integración**: Callback REST al recibir firma

### PID Madrid
- **Tipo**: Plataforma de Intermediación de Datos del Estado
- **Función**: Autorizar consultas a datos personales
- **Protocolo**: Web Services SOAP
- **Servicios**: SolicitudAutorizacion, ConsultaEstadoSolicitud, ActualizacionProcedimiento
- **Respuesta**: 1-4 semanas típicamente

### PINBAL
- **Tipo**: Sistema de gestión de procedimientos
- **Función**: Control de acceso a servicios
- **Integración**: API REST
- **Uso**: Aplicar permisos de PINFOs

---

## 🗄️ RELACIÓN ENTRE ENTIDADES

### Base de Datos

```
TRÁMITE SISTRA:
┌────────────────┐
│ pad_tramit_a   │ (Datos generales)
│ pad_tramit_b   │ (Cesión datos)
│ pad_tramit_c   │ (Cesionarios)
│ ...            │
└────────┬───────┘
         │ Genera
         ↓
┌────────────────────────┐
│ pad_solicitud          │ (Solicitud/Procedimiento)
│ - solicitudID          │
│ - procedimentCodi      │
│ - procedimentNom       │
│ - estatSolicitud       │ (5, 11, 15, 19, 20, 30, 40, 60)
│ - dataAutoritzacio     │ (⭐ CRÍTICO)
│ - contactetitularid    │ (FK)
└────────┬───────────────┘
         │
         │ 1:N
         ↓
┌────────────────────────┐
│ pad_solicitudservei    │ (Servicios solicitados)
│ - solicitudServeiID    │
│ - solicitudID          │ (FK)
│ - serveiCodi           │
│ - autoritzat           │
└────────────────────────┘

PREALTAS (Comunicación Madrid):
┌────────────────────────┐
│ pad_infomadrid         │ (⭐ Registro comunicación Madrid)
│ - infoMadridID         │
│ - codi                 │ (= procedimentCodi)
│ - estatProcediment     │ (19, 20, 30, 40, 44)
│ - estatAutoritzacio    │ (0-8, estados de Madrid)
│ - dataAutoritzacio     │ (⭐ Primera autorización)
│ - dataEnviament        │
│ - dataConsulta         │
│ - missatge             │ (observaciones Madrid)
└────────────────────────┘

PINFOS (Permisos usuarios):
┌────────────────────────┐
│ pad_incidenciatecnica  │ (Incidencia base)
│ - id                   │
│ - estat                │ (11-14)
└────────┬───────────────┘
         │ 1:1
         ↓
┌────────────────────────┐
│ pad_pinfo              │ (Solicitud permisos - el PDF)
│ - pinfoID              │
│ - incidenciaID         │ (FK)
│ - estat                │ (0-4)
│ - solicitantNIF        │
│ - destinatariNIF       │
│ - fitxerID             │ (PDF generado)
│ - fitxerfirmatID       │ (PDF firmado)
└────────┬───────────────┘
         │ 1:N
         ↓
┌────────────────────────┐
│ pad_pinfodata          │ (Permisos individuales)
│ - pinfodataID          │
│ - pinfoID              │ (FK)
│ - usuariid             │ (usuario Pinbal)
│ - procedimentID        │ (FK solicitud)
│ - serveiID             │ (FK servicio)
│ - alta                 │ (1=alta, 0=baja)
└────────────────────────┘
```

### Relación entre Solicitud y PINFO

```
pad_solicitud (Estado 40 = AUTORITZAT)
    │
    │ Es usado por
    ↓
pad_pinfodata.procedimentID (FK)
    │
    │ "Dame permisos para usuarios en procedimientos YA autorizados"
```

---

## 🔍 DIFERENCIAS CLAVE ENTRE PROCESOS

| Aspecto | TRÁMITE SISTRA | PREALTAS | PINFOS |
|---------|----------------|----------|---------|
| **Objetivo** | Crear solicitud | Autorizar solicitud | Dar permisos |
| **Prerequisito** | Ninguno | Solicitud creada | Procedimiento autorizado (estado 40) |
| **Quién inicia** | Entidad local | Operador interno | Usuario externo |
| **Documento** | Formulario SISTRA | XML SOAP | PDF PINFO |
| **Firma** | Titular (PortaFIB) | - | Responsable PFI_USER |
| **Destino** | BBDD PinbalAdmin | Madrid PID | PINBAL API |
| **Protocolo** | Web forms | SOAP | REST |
| **Tiempo** | Minutos | 2-6 semanas | 30 min - 2 días |
| **Estados** | 5→11→15→19 | 19→20→40/30→60 | 0→1→2→3→4 |
| **Resultado** | Solicitud pendiente | Procedimiento autorizado | Usuarios con acceso |
| **Tabla principal** | `pad_solicitud` | `pad_infomadrid` | `pad_pinfo` |

### Dependencias

```
SISTRA (crea) → SOLICITUD
    ↓
PREALTAS (autoriza) → SOLICITUD estado 40
    ↓
PINFOS (usa) → Procedimientos autorizados
```

### Campo Crítico: `dataAutoritzacio`

**En `pad_solicitud`:**
- `NULL` = Primera vez, es ALTA → envía `SolicitudAutorizacion`
- `NOT NULL` = Ya autorizado antes, es MODIFICACIÓN → envía `ActualizacionProcedimiento`
- **Se establece una sola vez** cuando Madrid autoriza por primera vez
- **NO se cambia nunca más**, aunque haya 100 modificaciones

---

## 📌 RESUMEN EJECUTIVO

**FLUJO RESUMIDO:**

1. **Entidad local** inicia trámite en **SISTRA** → Crea **Solicitud**
2. Solicitud llega a **Distribució** → **Operador** la marca como recibida
3. **Operador** revisa y envía documento al **Titular** vía **PortaFIB**
4. **Titular** firma digitalmente → Solicitud lista para Madrid
5. **Operador** envía a **Madrid** mediante **PREALTAS** (SOAP)
6. **Madrid** revisa (1-4 semanas) → Autoriza o desestima
7. Si autorizada → Solicitud pasa a estado **40 AUTORITZAT** ✅
8. **Operador** marca como **TANCADA** (60) → Avisa al solicitante original
9. Procedimiento disponible en PINBAL
10. **Usuarios** solicitan permisos mediante **PINFOS**
11. **Operador** procesa PINFOs → Aplica permisos en PINBAL
12. Usuarios pueden acceder a servicios ✅

**3 DOCUMENTOS DE REFERENCIA:**
- **SISTRA**: Ver carpeta `doc/Sistra/`
- **PREALTAS**: [PREALTAS_REFERENCIA_COMPLETA.md](PREALTAS_REFERENCIA_COMPLETA.md)
- **PINFOS**: [TRAMITE_PINFO_REFERENCIA.md](TRAMITE_PINFO_REFERENCIA.md)

---

**Última actualización:** 14 mayo 2026

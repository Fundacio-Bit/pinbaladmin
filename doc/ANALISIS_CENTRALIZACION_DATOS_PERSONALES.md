# Análisis de Centralización de Datos Personales

## Resumen Ejecutivo

Este documento analiza la **normalización de datos personales** para simplificar la gestión en código y base de datos.

**Objetivo Real**: 
- **EN LUGAR DE**: 8 campos dispersos por cada persona (`nom`, `llinatge1`, `llinatge2`, `nif`, `email`, `telefon`, `carrec`, etc.)
- **TENER**: 1 campo FK por persona (`titularid`, `responsableprocid`, `contacteid`)

**Estructura consistente de persona**:
- Todos los campos separados correctamente (nom, llinatge1, llinatge2, nif, email, telefon, carrec)
- Campo calculado `nomComplet` cuando se necesite: "Nombre Apellido1 Apellido2"
- Permite NULLs donde corresponda (no todos tienen NIF, no todos tienen teléfono)

**Casos de uso principales**:
- **Fusión de solicitudes**: Elegir `titularid` en lugar de copiar 8+ campos individuales
- **Solicitud**: 3 personas (`titularid`, `responsableprocid`, `contacteid`) en lugar de 15+ campos
- **InfoMadrid**: `titularid` con acceso a nom/llinatge1/llinatge2 separados, sin concatenar
- **PINFOs**: `solicitantid`, `destinatariid` con todos los datos estructurados

**Funcionamiento automático**:
1. Se introducen datos de una persona en formulario (como ahora)
2. Sistema busca si ya existe persona con **datos exactos**
3. Si existe → usar ese `personaid`
4. Si NO existe o **cualquier campo difiere** → crear nueva persona
5. Guardar solo FK en tabla principal

**NO se trata de**: Catálogo con selector manual, autocompletado en formularios.

## 1. Estado Actual: Problema de Estructura Inconsistente

### 1.1. Ejemplo: Tabla `pad_solicitud` (15+ campos de personas)

**Actualmente tienes 3 personas con datos mezclados**:

```sql
-- TITULAR (parcial)
denominacio VARCHAR(255)  -- ¿Nombre completo? ¿Razón social?
nif VARCHAR(40)
contactetitularid BIGINT  -- FK a contacte (¡ya existe!)
titularfirmanif VARCHAR(255)

-- PERSONA DE CONTACTO
personacontacte VARCHAR(255)       -- ¿Nombre completo?
personacontacteemail VARCHAR(100)  -- Solo email

-- RESPONSABLE PROCEDIMIENTO  
responsableprocnom VARCHAR(255)    -- ¿Nombre completo?
responsableprocemail VARCHAR(255)  -- Solo email
```

**Problemas**:
- ❌ Titular: usa FK pero también tiene campos sueltos
- ❌ Contacto: solo 2 campos (nombre, email), sin apellidos separados
- ❌ Responsable: solo 2 campos (nombre, email)
- ❌ No hay consistencia en qué datos guardas de cada persona
- ❌ No puedes acceder a apellidos separados cuando los necesitas
- ❌ Fusionar solicitudes = copiar 15+ campos individualmente

### 1.2. Tabla de Operadores

**`pad_operador`** - Usuarios del sistema:
- `operadorid` (PK)
- `username` (NOT NULL)
- `nom` (NOT NULL)
- `email` (NOT NULL)

**Campos sin equivalente en Contacte**: `username`

### 1.3. Tablas de Trámites con Datos Personales

Existen **MÚLTIPLES** tablas de trámites que duplican información personal:

#### **`pad_tramit_d_cte_aut`** - Contacto de Autorización
- `cteautid` (PK)
- `tramitid` (FK)
- `nif` (NOT NULL)
- `nom` (NOT NULL)
- `llinatge1` (NOT NULL)
- `llinatge2` (NOT NULL)
- `carrec` (NOT NULL)
- `telefon` (NOT NULL)
- `mail` (NOT NULL)

#### **`pad_tramit_e_cte_aud`** - Contacto de Auditoría
Campos idénticos a `pad_tramit_d_cte_aut`:
- `cteaudid` (PK)
- `tramitid` (FK)
- `nif`, `nom`, `llinatge1`, `llinatge2`, `carrec`, `telefon`, `mail` (todos NOT NULL)

#### **`pad_tramit_f_cte_tec`** - Contacto Técnico
- `ctetecid` (PK)
- `tramitid` (FK)
- `nif` (NOT NULL)
- `nom` (NOT NULL)
- `llinatge1` (NOT NULL)
- `llinatge2` (NOT NULL)
- `carrec`
- `telefon`
- `mail`

#### **`pad_tramit_g_dades_tit`** - Datos del Titular
- `dadestitid` (PK)
- `tramitid` (FK)
- `nif` (NOT NULL)
- `nom` (NOT NULL)
- `llinatge1` (NOT NULL)
- `llinatge2`
- `carrec` (NOT NULL)
- `telefon`
- `mail`

### 1.4. Tabla de Solicitudes

**`pad_solicitud`** - Contiene campos de personas embebidos:
- `personacontacte` - Nombre de la persona de contacto
- `personacontacteemail` - Email de la persona de contacto
- `responsableprocnom` - Nombre del responsable del procedimiento
- `responsableprocemail` - Email del responsable del procedimiento
- `nif` - NIF de la entidad (pero también usado para personas)
- `creador` - Usuario que creó (string, no FK)
- `operador` - Usuario operador (string, no FK)
- `contactetitularid` - **Ya tiene FK a pad_contacte**
- `titularfirmanif` - NIF del titular firmante

### 1.5. Tabla de Modificaciones de Solicitud

**`pad_mod_solicitud`** - Duplica información del solicitante:
- `responsableprocnom`
- `responsableprocemail`
- `solicitantnom`
- `solicitantnif`
- `solicitantmail`
- `solicitantusername`
- `contactenom`
- `contactemail`

### 1.6. Tabla de PINFOs

**`pad_pinfo`** - Información de Pinbal:
- `solicitantnif`
- `solicitantnom`
- `destinatarinif`
- `destinatarinom`

### 1.7. Tabla de InfoMadrid

**`pad_infomadrid`** - Información de Madrid:
- `titularnom`
- `titularnif`

### 1.8. Tabla de Incidencias Técnicas

**`pad_incidenciatecnica`** - Contacto de la incidencia:
- `contactenom` (NOT NULL)
- `contacteemail` (NOT NULL)
- `contactetelefon`
- `creador` (NOT NULL, username string)
- `operador` (NOT NULL, username string)

### 1.9. Tabla de Trámite C - Datos Cesionarios

**`pad_tramit_c_dades_cesi`** - Datos del cesionario:
- `denominacio` - Nombre/denominación
- `nif` - NIF
- `responsable` - Nombre del responsable
- `direccio` (NOT NULL)
- `codipostal` (NOT NULL)
- `municipi` (NOT NULL)

**Nota**: Esta tabla mezcla datos de entidad (denominació, direcció) con datos de persona (responsable).

## 2. Análisis de Duplicación de Datos

### 2.1. Campos Comunes Duplicados

Los siguientes conjuntos de campos se repiten en múltiples tablas:

**Patrón 1: Identificación Personal Completa**
- `nif`, `nom`, `llinatge1`, `llinatge2`, `carrec`, `telefon`, `mail`
- **Duplicado en**: 
  - `pad_contacte`
  - `pad_tramit_d_cte_aut`
  - `pad_tramit_e_cte_aud`
  - `pad_tramit_f_cte_tec`
  - `pad_tramit_g_dades_tit`

**Patrón 2: Identificación Mínima (Nombre + NIF)**
- `nom`, `nif`
- **Duplicado en**:
  - `pad_pinfo` (solicitantNom/NIF, destinatariNom/NIF)
  - `pad_infomadrid` (titularNom/Nif)
  - `pad_solicitud` (varios campos)
  - `pad_mod_solicitud`
  - Todas las tablas del Patrón 1

**Patrón 3: Contacto (Nombre + Email)**
- `nom`, `email` o `mail`
- **Duplicado en**:
  - `pad_operador` (username, nom, email)
  - `pad_incidenciatecnica` (contacteNom, contacteEmail)
  - `pad_solicitud` (personaContacte, personaContacteEmail)
  - `pad_mod_solicitud` (responsableProcNom/Email, contactenom/email)

### 2.2. Estadísticas de Duplicación

- **Tablas con datos personales**: 11 tablas principales
- **Campos NIF duplicados**: Al menos 13 campos en diferentes tablas
- **Campos de nombre duplicados**: Al menos 18 campos
- **Campos de email duplicados**: Al menos 10 campos
- **Campos de teléfono duplicados**: Al menos 7 campos

## 3. Impacto en el Código

### 3.1. Clases Afectadas

**Entidades JPA** (45 archivos en `pinbaladmin-persistence`):
- `ContacteJPA` - **Ya existe como tabla central potencial**
- `OperadorJPA`
- `SolicitudJPA`
- `ModificacioSolicitudJPA`
- `PinfoJPA`
- `InfoMadridJPA`
- `IncidenciaTecnicaJPA`
- `TramitDCteAutJPA`
- `TramitECteAudJPA`
- `TramitFCteTecJPA`
- `TramitGDadesTitJPA`
- `TramitCDadesCesiJPA`

**Beans de Modelo** (en `pinbaladmin-model`):
- Correspondientes Bean para cada JPA
- Fields y QueryPath para cada entidad

**Controladores** (en `pinbaladmin-back`):
- Más de 100 referencias en controladores
- Principalmente en:
  - `DadesPinbalController`
  - `AltaSolicitudPinbalOperadorController`
  - `TramitXPublicController` (varios)
  - `TramitXOperadorController` (varios)

**Formularios y Validadores**:
- Filtros de búsqueda para cada entidad
- Formularios de alta/modificación

### 3.2. Uso en Lógica de Negocio

Los campos personales se utilizan para:
1. **Búsqueda y filtrado**: Búsquedas por NIF, nombre, email
2. **Visualización**: Mostrar información de contactos en vistas
3. **Creación de documentos**: Generación de PDFs con datos personales
4. **Notificaciones**: Envío de emails usando datos de contacto
5. **Auditoría**: Campos `creador` y `operador` en múltiples tablas
6. **Integraciones externas**: Envío de datos a PINBAL, InfoMadrid, etc.

## 4. Propuesta: Tabla de Personas Normalizada

### 4.1. Usar y Extender `pad_contacte` (ya existe)

**La tabla `pad_contacte` YA EXISTE con buena estructura:**

```sql
-- TABLA ACTUAL:
CREATE TABLE pad_contacte (
    contacteid BIGSERIAL PRIMARY KEY,
    nif VARCHAR(30) NOT NULL,
    nom VARCHAR(60) NOT NULL,
    llinatge1 VARCHAR(60),
    llinatge2 VARCHAR(60),
    carrec VARCHAR(120),
    telefon VARCHAR(12),
    mail VARCHAR(120) NOT NULL
);
```

**Modificaciones necesarias**:

```sql
-- 1. Permitir NIF NULL (no todas las personas tienen NIF)
ALTER TABLE pad_contacte ALTER COLUMN nif DROP NOT NULL;

-- 2. Permitir mail NULL (no todas las personas tienen email)
ALTER TABLE pad_contacte ALTER COLUMN mail DROP NOT NULL;

-- 3. Agregar índice para deduplicación rápida
CREATE INDEX idx_contacte_dedup ON pad_contacte(nif, nom, llinatge1, llinatge2, mail);

-- 4. Opcional: Agregar campos adicionales si necesarios
ALTER TABLE pad_contacte ADD COLUMN adreca VARCHAR(255);
ALTER TABLE pad_contacte ADD COLUMN codipostal VARCHAR(10);
ALTER TABLE pad_contacte ADD COLUMN municipi VARCHAR(100);
```

### 4.2. Vista/Método para Nombre Completo

**Opción A: Columna calculada en BD** (PostgreSQL 12+):
```sql
ALTER TABLE pad_contacte 
ADD COLUMN nomcomplet VARCHAR(200) 
GENERATED ALWAYS AS (
    TRIM(CONCAT(nom, ' ', COALESCE(llinatge1, ''), ' ', COALESCE(llinatge2, '')))
) STORED;
```

**Opción B: Método en entidad Java** (más flexible):
```java
public class ContacteJPA {
    // ... campos existentes ...
    
    @Transient
    public String getNomComplet() {
        StringBuilder sb = new StringBuilder();
        if (nom != null) sb.append(nom);
        if (llinatge1 != null) sb.append(" ").append(llinatge1);
        if (llinatge2 != null) sb.append(" ").append(llinatge2);
        return sb.toString().trim();
    }
}
```

### 4.3. Enfoque: Agregar FK Opcional SIN Eliminar Campos

**Estrategia HÍBRIDA y PRAGMÁTICA**:

1. **AGREGAR** columna FK opcional a `pad_contacte` en cada tabla
2. **MANTENER** todos los campos actuales (nif, nom, llinatge1, etc.)
3. **En el código**:
   - Si hay FK → mostrar datos del contacto
   - Si no hay FK → usar campos individuales (legacy/manual)
4. **En formularios**:
   - Selector de persona (autocompletado) 
   - O introducción manual como ahora
   - Al seleccionar persona → se copian automáticamente todos los campos

**Ventajas de este enfoque**:
- ✅ **Cero riesgo**: Los datos siempre están en ambos lugares
- ✅ **Migración gradual**: Se puede implementar tabla por tabla
- ✅ **Rollback fácil**: No rompe nada existente
- ✅ **Flexibilidad**: Permite entrada manual si la persona no existe en catálogo
- ✅ **Auditoría**: Los datos quedan guardados incluso si se borra del catálogo

**Ejemplo de cambio en tabla**:

```sql
-- Para cualquier tabla que necesite persona
ALTER TABLE pad_infomadrid 
    ADD COLUMN contactetitularid BIGINT,
    ADD CONSTRAINT fk_titular 
        FOREIGN KEY (contactetitularid) 
        REFERENCES pad_contacte(contacteid);

-- MAN4. Población Inicial del Catálogo

**Paso 1**: Poblar `pad_contacte` con personas únicas de todas las tablas:

```sql
-- Insertar personas únicas de InfoMadrid (si no existen)
INSERT INTO pad_contacte (nif, nom, llinatge1, llinatge2, mail, telefon, carrec)
SELECT DISTINCT 
    titularnif as nif,
    SPLIT_PART(titularnom, ' ', 1) as nom,  -- Primer palabra = nombre
    SPLIT_PART(titularnom, ' ', 2) as llinatge1,  -- Segunda = apellido1
    SPLIT_PART(titularnom, ' ', 3) as llinatge2,  -- Tercera = apellido2
    'pendiente@example.com' as mail,  -- Email temporal
    '' as telefon,
    '' as carrec
FROM pad_infomadrid
WHERE titularnif IS NOT NULL 
  AND NOT EXISTS (
      SELECT 1 FROM pad_contacte c WHERE c.nif = pad_infomadrid.titularnif
  );

-- Insertar personas de PINFOs
INSERT INTO pad_contacte (nif, nom, llinatge1, mail)
SELECT DISTINCT l Catálogo de Personas
```

### 5.1. Ventajas de UX (Experiencia de Usuario)

✅ **Simplificación de Formularios**
- **Antes**: Introducir 8 campos individuales (nif, nom, llinatge1, llinatge2, carrec, telefon, mail, etc.)
- **Después**: Seleccionar de lista o buscar por NIF/nombre → autocompletado

✅ **Fusión de Solicitudes Simplificada**
- **Antes**: Copiar campo por campo manualmente
- **Después**: "Seleccionar titular: [Dropdown con personas]"

✅ **Menos Errores de Tipeo**
- Datos consistentes porque se reutilizan
- Validación en un solo punto (al crear contacto)

✅ **Ahorro de Tiempo**
- No reescribir datos de personas conocidas
- Búsqueda rápida por NIF o nombre

### 5.2. Ventajas de Código

✅ **Menos Campos en Formularios**
- **Antes**: 8 campos HTML + validadores para cada uno
- **Después**: 1 selector + campos ocultos autocompletados

✅ **Código Más Limpio**
```java
// Antes
solicitud.setPersonaContacte(form.getNom() + " " + form.getLlinatge1());
solicitud.setPersonaContacteEmail(form.getEmail());
// ... 6 líneas más

// Después
Contacte contacte = contacteManager.findById(form.getContacteId());
solicitud.setContacteTitularID(contacte.getContacteID());
solicitud.copiarDadesContacte(contacte); // Helper que copia todos los campos
```

✅ **Gestión de Cambios Simplificada**
- Si una persona cambia email → actualizar en catálogo
- Los registros nuevos tendrán el email actualizado automáticamente
- Los registros antiguos mantienen el email del momento (snapshot)
- Un solo punto de verdad para datos personales
- No hay discrepancias entre tablas
- Validaciones centralizadas

✅ **Facilidad de Mantenimiento**
- Cambios en estructura solo afectan una tabla
- Más fácil agregar nuevos campos (ej: móvil, dirección)
- Simplifica migraciones futuras

✅ **Mejora en Búsquedas**
- Búsqueda unificada de personas
- Índices más eficientes
- Menos JOINs complejos en algunas consultas

✅ **Cumplimiento GDPR**
- Más fácil localizar todos los datos de una persona
- Simplifica el "derecho al olvido"
- Facilita auditorías de datos personales

### 5.2. Ventajas de Negocio

✅ **Historial Unificado**
- Ver todas las interacciones de una persona
- Seguimiento de actividad completo
- Mejor soporte al cliente

✅ **Deduplicación**
- Evita crear múltiples registros de la misma persona
- Detecta contactos duplicados

✅ **Reutilización de Datos**
- Autocompletar datos en formularios
- No reintroducir datos cada vez

## 6. Consideraciones Importantes

### 6.1. Esfuerzo de Desarrollo (MODERADO-ALTO)

⚠️ **Impacto Real**
- **11 entidades JPA** a refactorizar
- **Eliminar ~50-80 campos** individuales en total
- **Migración de datos** con lógica compleja (parsing de nombres)
- **Modificar formularios** HTML/JSP
- **Actualizar controladores** y servicios
- **Pruebas exhaustivas** necesarias
- **Estimación realista: 120-200 horas** (3-5 semanas)

### 6.2. Riesgos de Migración de Datos

⚠️ **Parsing de Nombres Concatenados**
```sql
-- Problema: "Juan García López" → ¿Cómo dividir?
SPLIT_PART('Juan García López', ' ', 1) → 'Juan' (nom)
SPLIT_PART('Juan García López', ' ', 2) → 'García' (llinatge1)
SPLIT_PART('Juan García López', ' ', 3) → 'López' (llinatge2)

-- ¿Pero qué pasa con?
'María del Carmen Fernández de la Rosa'
'José Luis García'
'Wang Wei'  -- Nombres chinos
```

**Solución**: Script de migración con revisión manual de casos complejos.

⚠️ **Deduplicación Puede Fallar**
- Misma persona con email diferente → crea 2 registros (correcto según tu lógica)
- Misma persona con NIF con/sin espacios → pueden crearse duplicados
- Nombres con mayúsculas/minúsculas diferentes

**Solución**: Normalización de datos antes de comparar (trim, lowercase, etc.)

### 6.3. Cambios en Lógica de Negocio

⚠️ **Creación de Personas Automática**

Cada vez que se guarda una solicitud/PINFO/etc:
```java
// Nuevo código necesario
ContacteJPA responsable = contacteManager.buscarOCrear(
    form.getResponsableNif(),
    form.getResponsableNom(),
    form.getResponsableLlinatge1(),
    form.getResponsableLlinatge2(),
    form.getResponsableEmail(),
    form.getResponsableTelefon(),
    form.getResponsableCarrec()
);
solicitud.setResponsableProc(responsable);
```

**Implicaciones**:
- Tabla `pad_contacte` crecerá mucho más rápido
- Necesario gestión de personas "inactivas" o borrado lógico
- Posible necesidad de limpieza periódica de personas no usadas

⚠️ **Formularios: Más Campos o Lógica Compleja**

**Opción A**: Formulario sigue con 8 campos, backend crea persona:
- Usuario ve mismos campos de siempre
- Backend hace la magia de crear/buscar persona
- ✅ Menos cambio en UX
- ❌ Formularios no se simplifican visualmente

**Opción B**: Formulario simplificado (¿futuro?):
- Buscar persona por NIF → autocompletar
- Si no existe → introducir datos
- ✅ UX mejorada a largo plazo
- ❌ Requiere JavaScript, AJAX, más desarrollo

### 6.4. Performance: JOINs Adicionales

⚠️ **Más JOINs en Queries Comunes**
```sql
-- Antes: Query simple
SELECT solicitudid, personacontacte, personacontacteemail 
FROM pad_solicitud;

-- Después: Necesitas JOINs
SELECT s.solicitudid, pc.nom, pc.llinatge1, pc.llinatge2, pc.mail
FROM pad_solicitud s
LEFT JOIN pad_contacte pc ON s.personacontacteid = pc.contacteid;
```

**Mitigación**:
- Índices en FKs
- Fetch strategy LAZY en JPA (cargar solo cuando necesario)
- DTOs para listados (no cargar relaciones completas)

### 6.5. Rollback Difícil

❌ **Una vez migrado, difícil volver atrás**
- Datos ya parseados y separados
- Nombres concatenados se pierden si no se guarda "snapshot"
- Necesitarías reconstruir campos concatenados

**Recomendación**: Implementar en entorno de pruebas primero, validar a fondo.

## 7. Análisis de Tablas Específicas

### 7.1. Tablas de Trámites: **SÍ vale la pena**

**Tablas**: `pad_tramit_d_cte_aut`, `pad_tramit_e_cte_aud`, `pad_tramit_f_cte_tec`, `pad_tramit_g_dades_tit`

**Justificación**:
- ✅ Estructura **IDÉNTICA** entre ellas
- ✅ Datos **exactamente repetidos** (7-8 campos iguales)
- ✅ Mismo propósito: representar una persona de contacto
- ✅ Beneficio inmediato en mantenimiento
- ⚠️ **PERO**: ¿Son snapshots históricos? Verificar requisitos de auditoría

**Recomendación**: **CONSOLIDAR** estas 4 tablas en FKs a `pad_persona`

### 7.2. Tabla `pad_solicitud`: **Valor medio**

**Campos embebidos**:
- `personacontacte`, `personacontacteemail`
- `responsableprocnom`, `responsable (Casos de Uso Reales)

### 7.1. ⭐ **`pad_infomadrid`**: **MÁXIMA PRIORIDAD**

**Problema actual**:
```sql
titularnom VARCHAR(240)  -- Guardando "Juan García López"
titularnif VARCHAR(20)
```
- Concatenando nombre + apellidos en un campo
- No hay separación entre nombre/apellido1/apellido2
- Difícil de usar en documentos que necesitan campos separados

**Solución**:
```sql
ALTER TABLE pad_infomadrid ADD COLUMN contactetitularid BIGINT;
-- MANTENER titularnom, titularnif como snapshot
```

**Beneficio**: 
- Seleccionar titular de lista en formulario
- Autocompletar nombre + apellidos separados
- Al guardar: copiar datos completos de contacte a campos individuales

### 7.2. ⭐ **`pad_pinfo`**: **ALTA PRIORIDAD**

**Problema actual**:
```sql
solicitantnif VARCHAR(100)
solicitantnom VARCHAR(255)
destinatarinif VARCHAR(100)
destinatarinom VARCHAR(255)
```

**Solución**:
```sql
ALTER TABLE pad_pinfo 
    ADD COLUMN solicitantid BIGINT,
    ADD COLUMN destinatariid BIGINT;
```

**Beneficio**:
- Formulario: "Seleccionar solicitante" + "Seleccionar destinatario"
- No reescribir NIF/nombre cada vez

### 7.3. ⭐ **`pad_solicitud`**: **ALTA PRIORIDAD - Fusión de Solicitudes**

**Problema actual (caso de uso crítico)**:
```java
// Al fusionar solicitudes, copiar titular campo por campo
solicitudFusionada.setNif(solicitudOrigen.getNif());
solicitudFusionada.setDenominacio(solicitudOrigen.getDenominacio());
// ... etc
```

**Solución**:
```sql
-- Ya tiene contactetitularid, USARLO más
ALTER TABLE pad_solicitud 
    ADD COLUMN personacontacteid BIGINT,  -- Para persona contacto
    ADD COLUMN responsableprocid BIGINT;   -- Para responsable procedimiento
```

**Beneficio**:
- Formulario fusión: Dropdown "Seleccionar titular: [Entidad A | Entidad B]"
- Un clic en lugar de copiar 5+ campos

### 7.4. ⭐ **Tablas de Trámites**: **ALTA PRIORIDAD**
de Implementación Pragmática

### 8.1. Implementación por Tabla (Independiente)

**Cada tabla puede hacerse de forma INDEPENDIENTE sin afectar otras:**

#### **Tabla 1: `pad_infomadrid`** ⏱️ **4-6 horas**
1. Agregar columna `contactetitularid`
2. Poblar catálogo con titulares únicos
3. Vincular registros existentes
4. Modificar formulario: agregar selector de persona
5. Helper: `copiarDadesContacteATitular(contacte, infomadrid)`

#### **Tabla 2: `pad_pinfo`** ⏱️ **4-6 horas**
1. Agregar columnas `solicitantid`, `destinatariid`
2. Poblar catálogo
3. Vincular
4. Modificar formulario (2 selectores)

#### **Tabla 3: `pad_solicitud` (Fusión)** ⏱️ **8-12 horas**
1. Agregar `personacontacteid`, `responsableprocid`
2. Modificar página de fusión:
   - Dropdown "Titular de fusionada: [Solicitud A | Solicitud B]"
   - Al seleccionar → `copiarDadesTitular(solicitudOrigen, solicitudDestino)`
3. Modificar formulario normal: selectores opcionales
### 8.2. Servicio de Gestión de Personas (Capa Java)

**Crear servicio centralizado para buscar/crear personas**:

```java
@Stateless
public class ContacteService {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Busca persona con datos exactos o crea nueva si no existe.
     * Deduplicación automática.
     */
    public ContacteJPA buscarOCrear(
            String nif,
            String nom,
            String llinatge1,
            String llinatge2,
            String email,
            String telefon,
            String carrec) {
        
        // Normalizar datos para búsqueda
        String nifNorm = normalizar(nif);
        String emailNorm = normalizar(email);
        
        // Buscar persona existente (todos los campos deben coincidir)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ContacteJPA> cq = cb.createQuery(ContacteJPA.class);
        Root<ContacteJPA> root = cq.from(ContacteJPA.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.or(
            cb.and(cb.isNull(root.get("nif")), nifNorm == null),
            cb.equal(root.get("nif"), nifNorm)
        ));
        predicates.add(cb.equal(root.get("nom"), normalizar(nom)));
        predicates.add(cb.or(
            cb.and(cb.isNull(root.get("llinatge1")), llinatge1 == null),
            cb.equal(root.get("llinatge1"), normalizar(llinatge1))
        ));
        predicates.add(cb.or(
            cb.and(cb.isNull(root.get("mail")), emailNorm == null),
            cb.equal(root.get("mail"), emailNorm)
        ));
        // ... similar para otros campos
        
        cq.where(predicates.toArray(new Predicate[0]));
        
        try {
            // Si existe, retornar
            return em.createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            // No existe, crear nuevo
            ContacteJPA nuevo = new ContacteJPA();
            nuevo.setNif(nifNorm);
            nuevo.setNom(normalizar(nom));
            nuevo.setLlinatge1(normalizar(llinatge1));
            nuevo.setLlinatge2(normalizar(llinatge2));
            nuevo.setMail(emailNorm);
            nuevo.setTelefon(normalizar(telefon));
            nuevo.setCarrec(normalizar(carrec));
            em.persist(nuevo);
            return nuevo;
        }
    }
    
    /**
     * Normaliza string para comparación/almacenamiento
     */
    private String normalizar(String str) {
        if (str == null || str.trim().isEmpty()) return null;
        return str.trim().toLowerCase();
    }
    
    /**
     * Crea persona desde nombre completo concatenado (para migración)
     */
    public ContacteJPA crearDeNomComplet(String nomComplet, String nif, String email) {
        String[] parts = parseNomComplet(nomComplet);
        return buscarOCrear(
            nif,
            parts[0],  // nom
            parts[1],  // llinatge1
            parts[2],  // llinatge2
            email,
            null,
            null
        );
    }
    
    /**
     * Intenta parsear nombre completo en partes.
     * Heurística simple: primera palabra = nombre, resto = apellidos.
     */
    private String[] parseNomComplet(String nomComplet) {
        if (nomComplet == null || nomComplet.trim().isEmpty()) {
            return new String[]{null, null, null};
        }
        
        String[] words = nomComplet.trim().split("\\s+");
        String nom = words.length > 0 ? words[0] : null;
        String llinatge1 = words.length > 1 ? words[1] : null;
        String llinatge2 = words.length > 2 ? 
            String.join(" ", Arrays.copyOfRange(words, 2, words.length)) : null;
        
        return new String[]{nom, llinatge1, llinatge2};
    }
}
```

### 8.3. Actualización de Controladores

**Ejemplo: Al guardar solicitud**:

```java
@PostMapping("/solicitud/save")
public String saveSolicitud(@ModelAttribute SolicitudForm form) {
    
    SolicitudJPA solicitud = new SolicitudJPA();
    
    // ANTES: Guardar campos individuales
    // solicitud.setPersonaContacte(form.getPersonaContacteNom());
    // solicitud.setPersonaContacteEmail(form.getPersonaContacteEmail());
    
    // DESPUÉS: Buscar/crear persona y asignar FK
    ContacteJPA personaContacte = contacteService.buscarOCrear(
        null,  // NIF (no disponible para persona contacto)
        form.getPersonaContacteNom(),
        form.getPersonaContacteLlinatge1(),
        form.getPersonaContacteLlinatge2(),
        form.getPersonaContacteEmail(),
        form.getPersonaContacteTelefon(),
        null   // carrec
    );
    solicitud.setPersonaContacte(personaContacte);
    
    // Similar para responsable
    ContacteJPA responsable = contacteService.buscarOCrear(
        null,
        form.getResponsableNom(),
        form.getResponsableLlinatge1(),
        form.getResponsableLlinatge2(),
        form.getResponsableEmail(),
        form.getResponsableTelefon(),
        form.getResponsableCarrec()
    );
    solicitud.setResponsableProc(responsable);
    
    // Titular
    ContacteJPA titular = contacteService.buscarOCrear(
        form.getTitularNif(),
        form.getTitularNom(),
        form.getTitularLlinatge1(),
        form.getTitularLlinatge2(),
        form.getTitularEmail(),
        form.getTitularTelefon(),
        form.getTitularCarrec()
    );
    solicitud.setTitular(titular);
    
    // Guardar solicitud
    solicitudManager.save(solicitud);
    
    return "redirect:/solicitud/list";
}
```

### 8.4. Actualización de Formularios

**Opción A: Mantener campos separados (RECOMENDADO para empezar)**:

```jsp
<!-- formulario sigue igual para usuario -->
<h3>Persona de Contacto</h3>
<input type="text" name="personaContacteNom" placeholder="Nombre" />
<input type="text" name="personaContacteLlinatge1" placeholder="Primer Apellido" />
<input type="text" name="personaContacteLlinatge2" placeholder="Segundo Apellido" />
<input type="email" name="personaContacteEmail" placeholder="Email" />
<input type="tel" name="personaContacteTelefon" placeholder="Teléfono" />

<!-- Backend hace la magia de buscar/crear persona -->
```

**Opción B: Futuro - Autocompletado** (más trabajo):

```jsp
<h3>Persona de Contacto</h3>
<input type="text" id="buscarContacte" 
       placeholder="Buscar por NIF o nombre..." 
       onkeyup="buscarPersona()" />
<div id="resultados"></div>

<!-- Si se selecciona → autocompleta campos -->
<!-- Si no → introducir manual -->
```

#### **2. SQL: Población Inicial**
```sql
-- Insertar personas únicas al catálogo
INSERT INTO pad_contacte (nif, nom, llinatge1, llinatge2, mail, telefon, carrec)
SELECT DISTINCT [campos_persona]
FROM pad_[tabla]
WHERE [nif] IS NOT NULL
  AND NOT EXISTS (SELECT 1 FROM pad_contacte WHERE nif = pad_[tabla].[nif])
ON CONFLICT (nif) DO NOTHING;

-- Vincular FK
UPDATE pad_[tabla] t
SET contacte[rol]id = c.contacteid
FROM pad_contacte c
WHERE t.[nif] = c.nif;
```

#### **3. Java: Helper de Copia**
```java
public void copiarDadesContacte(Contacte contacte, [Tabla] entitat) {
    if (contacte != null) {
        entitat.setContacte[Rol]ID(contacte.getContacteID());
        entitat.setNif(contacte.getNif());
        entitat.setNom(contacte.getNom());
        entitat.setLlinatge1(contacte.getLlinatge1());
        entitat.setLlinatge2(contacte.getLlinatge2());
        entitat.setReales Mejorados

### 9.1. 🎯 Fusión de Solicitudes (Tu Caso Principal)

**ANTES**:
```java
// Copiar 15+ campos manualmente
SolicitudJPA fusionada = new SolicitudJPA();
if (form.usarDatosSolicitudA()) {
    // Copiar titular
    fusionada.setNif(solicitudA.getNif());
    fusionada.setDenominacio(solicitudA.getDenominacio());
    fusionada.setDir3(solicitudA.getDir3());
    // ... más campos titular
    
    // Copiar persona contacto
    fusionada.setPersonaContacte(solicitudA.getPersonaContacte());
    fusionada.setPersonaContacteEmail(solicitudA.getPersonaContacteEmail());
    
    // Copiar responsable
    fusionada.setResponsableProcNom(solicitudA.getResponsableProcNom());
    fusionada.setResponsableProcEmail(solicitudA.getResponsableProcEmail());
    
    // ... 8 líneas más
} else {
    // Copiar de B - otras 15+ líneas
}
```

**DESPUÉS**:
```java
// Copiar 3 referencias
SolicitudJPA fusionada = new SolicitudJPA();
if (form.usarDatosSolicitudA()) {
    fusionada.setTitular(solicitudA.getTitular());
    fusionada.setPersonaContacte(solicitudA.getPersonaContacte());
    fusionada.setResponsableProc(solicitudA.getResponsableProc());
} else {
    fusionada.setTitular(solicitudB.getTitular());
    fusionada.setPersonaContacte(solicitudB.getPersonaContacte());
    fusionada.setResponsableProc(solicitudB.getResponsableProc());
}

// Acceso a todos los datos:
String titularNomComplet = fusionada.getTitular().getNomComplet();
String titularNif = fusionada.getTitular().getNif();
// ... etc
```

### 9.2. 🎯 Crear InfoMadrid (Tu Caso del Campo Concatenado)

**ANTES**:
```jsp
<input type="text" name="titularNom" placeholder="Nombre Apellido1 Apellido2" />
<input type="text" name="titularNif" />
```

**DESPUÉS**:
```jspDEFINITIVAMENTE VALE LA PENA**

Implementar catálogo de contactos reutilizable con enfoque híbrido (FK + campos duplicados) es:

### **Beneficios Principales:**

1. ✅ **Simplificación de UX**: Menos campos → menos errores → más rápido
2. ✅ **Bajo riesgo**: No rompe nada (campos se mantienen)
3. ✅ **Esfuerzo razonable**: 40-80 horas total (no 200-400)
4. ✅ **Implementación gradual**: Tabla por tabla, independiente
5. ✅ **ROI inmediato**: Cada tabla mejorada aporta valor directo
6. ✅ **Cumple tu objetivo**: Soluciona casos específicos (fusión, InfoMadrid, PINFOs)

### **Prioridad de Implementación (Según tus Casos de Uso):**

| Prioridad | Tabla | Caso de Uso | Esfuerzo | Valor UX |
|-----------|-------|-------------|----------|----------|
| 🔴 **1** | `pad_solicitud` | **Fusión: seleccionar titular con 1 click** | 10h | ⭐⭐⭐⭐⭐ |
| 🔴 **2** | `pad_infomadrid` | **No concatenar nombre+apellidos** | 6h | ⭐⭐⭐⭐⭐ |
| 🔴 **3** | `pad_pinfo` | **Seleccionar solicitante/destinatario** | 6h | ⭐⭐⭐⭐ |
| 🟡 **4** | `pad_tramit_f_cte_tec` | Reutilizar contactos técnicos | 8h | ⭐⭐⭐⭐ |
| 🟡 **5** | `pad_tramit_d_cte_aut` | Reutilizar contactos autorización | 8h | ⭐⭐⭐ |
| 🟡 **6** | `pad_tramit_e_cte_aud` | Reutilizar contactos auditoría | 8h | ⭐⭐⭐ |
| 🟡 **7** | `pad_tramit_g_dades_tit` | Reutilizar datos titular | 8h | ⭐⭐⭐ |
| 🟢 **8** | `pad_mod_solicitud` | Similar a solicitud | 10h | ⭐⭐ |

**Total estimado: 64 horas** (1.5 semanas de desarrollo)

### **Plan de Acción Inmediato:**

#### **Semana 1: Quick Wins**
1. ✅ Implementar selector en **fusión de solicitudes** (10h)
   - Máximo impacto en tu workflow diario
   - Caso de uso crítico y específico
2. ✅ Implementar `pad_infomadrid` (6h)
   - Soluciona problema de campos concatenados
3. ✅ Implementar `pad_pinfo` (6h)
   - Casos frecuentes mejorados

**Total Semana 1: 22 horas → 3 casos de uso mejorados**

#### **Semana 2: Trámites**
4-7. Implementar las 4 tablas de trámites (32h)
   - Reutilización de contactos técnicos
   - Menos reintroducción de datos

**Total Semana 2: 32 horas → 4 tablas adicionales**

#### **Opcional: Semana 3**
8. `pad_mod_solicitud` si se usa frecuentemente (10h)

### **Decisión Final:**

**EMPEZAR INMEDIATAMENTE** con las 3 primeras tablas (Semana 1).

**Razones**:
- Solucionan TUS casos de uso específicos
- Esfuerzo muy razonable (22h = 3 días)
- Beneficio inmediato visible
- Sin riesgo (no eliminas campos)
- Validarás el enfoque antes de continuar

Si la Semana 1 es exitosa (y lo será) → Continuar con trámites en Semana 2.

### **Próximos Pasos:**

1. [ ] Revisar y aprobar este análisis
2. [ ] Decidir orden de implementación
3. [ ] Crear issues/tasks en sistema de gestión
4. [ ] Implementar tabla por tabla
5. [ ] Testing tras cada tabla
6. [ ] Deploy gradual

---

**Fecha de Análisis**: 12 Mayo 2026  
**Analista**: GitHub Copilot  
**Estado**: ✅ **Recomendación: PROCEDER CON IMPLEMENTACIÓN**  
**Revisado**: Ajustado según objetivo real del usuario (UX, no optimización BD)
**ANTES**: Reintroducir NIF, nombre, apellidos, email, teléfono en cada trámite.

**DESPUÉS**: Seleccionar "Juan García" de la lista → autocompletado.
                    data-nom="${c.nom}"
                    data-llinatge1="${c.llinatge1}">
                ${c.nif} - ${c.nom} ${c.llinatge1}
            </option>
        </c:forEach>
    </select>
</div>

<!-- Opción 2: Campos manuales (autocompletados si se selecciona) -->
<input type="text" id="nif" name="nif" />
<input type="text" id="nom" name="nom" />
<!-- ... resto de campos ... -->

<script>
function autocompletarDades() {
    var select = document.getElementById('contacteSelector');
    var option = select.options[select.selectedIndex];
    if (option.value) {
        document.getElementById('nif').value = option.dataset.nif;
        document.getElementById('nom').value = option.dataset.nom;
        // ... resto de campos
    }
}
</script>
```

#### **5. Controller: Lógica de Guardado**
```java
@PostMapping("/save")
public String save(@ModelAttribute [Tabla]Form form) {
    [Tabla] entitat = new [Tabla]();
    
    // Si seleccionó contacto del catálogo
    if (form.getContacte[Rol]ID() != null) {
        Contacte contacte = contacteManager.findById(form.getContacte[Rol]ID());
        copiarDadesContacte(contacte, entitat);
    } else {
        // Entrada manual - datos vienen del formulario
        entitat.setNif(form.getNif());
        entitat.setNom(form.getNom());
        // ...
    }
    
    // Guardar
    manager.save(entitat);
    return "redirect:/success";
}
```

**IMPORTANTE**: Los campos individuales SIEMPRE se guardan (snapshot). El FK es solo helper.
   - Validar consistencia

4. **Código híbrido**:
   - Leer de FK si existe, sino de campos legacy
   - Nuevos registros usan solo FK
   - Permite rollback gradual

5. **Validación**:
   - Pruebas de regresión completas
   - Validar performance
   - Monitorear producción

6. **Limpieza final** (después de N meses):
   - Eliminar campos legacy
   - Hacer FK obligatorio

## 9. Casos de Uso Beneficiados

### 9.1. Búsqueda Global de Personas
**Antes**: Buscar en 11 tablas diferentes
**Después**: Una consulta a `pad_persona`

### 9.2. Actualización de Email de Contacto
**Antes**: Actualizar en todas las tablas donde aparezca
**Después**: Actualizar en un solo lugar (pero ver nota sobre snapshots)

### 9.3. GDPR - Derecho al Olvido
**Antes**: Buscar y eliminar en todas las tablas
**Después**: Eliminar de `pad_persona` y cascada (o marcar como eliminado)

### 9.4. Deduplicación de Contactos
**Antes**: Imposible detectar duplicados entre tablas
**Después**: Fácil detección y merge de personas

## 10. Conclusión y Recomendación Final

### ✅ **RECOMENDACIÓN: SÍ, PERO CON MATICES**

La centralización de datos personales en una tabla `pad_persona` (o extender `pad_contacte`) **tiene sentido y aportaría valor**, PERO:

### **Vale la pena SI:**

1. ✅ Se hace de forma **gradual por fases**
2. ✅ Se empieza por las **tablas de trámites** (mayor beneficio/esfuerzo)
3. ✅ Se mantiene **código híbrido** durante transición
4. ✅ Se respetan **requisitos de auditoría** (snapshots históricos)
5. ✅ Se dedica tiempo suficiente (**200-300 horas totales**)
6. ✅ Se hace con **pruebas exhaustivas**

### **NO vale la pena SI:**

1. ❌ Necesitas resultados inmediatos (proyecto largo)
2. ❌ No tienes recursos para testing exhaustivo (alto riesgo)
3. ❌ Los datos son principalmente **snapshots históricos** (pierde sentido)
4. ❌ El sistema está en fin de vida (no recuperarás inversión)

### **Prioridad de Cambios:**

| Prioridad | Tablas | Esfuerzo | Valor | Ratio |
|-----------|--------|----------|-------|-------|
| 🔴 ALTA | Tramits D/E/F/G | 70h | ALTO | ⭐⭐⭐⭐⭐ |
| 🟡 MEDIA | Solicitud | 90h | MEDIO | ⭐⭐⭐ |
| 🟡 MEDIA | IncidenciaTecnica | 40h | MEDIO | ⭐⭐⭐⭐ |
| 🟢 BAJA | ModificacioSolicitud | 50h | MEDIO | ⭐⭐ |
| 🔵 EVALUAR | PInfo/InfoMadrid | 30h | BAJO | ⭐ o ⭐⭐⭐ |
| ❌ NO | Operador | N/A | N/A | - |

### **Decisión Sugerida:**

**EJECUTAR FASE 1** (Tablas de Trámites) y **evaluar resultados** antes de continuar.

**Justificación**:
- Mayor impacto con menor esfuerzo
- Permite validar enfoque
- Beneficio inmediato visible
- Riesgo controlado
- Aprendizaje para fases posteriores

Si Fase 1 es exitosa → Continuar con Fase 2 y 3
Si Fase 1 presenta problemas → Reevaluar estrategia

---

**Fecha de Análisis**: Mayo 2026  
**Analista**: GitHub Copilot  
**Estado**: Pendiente de Decisión

# PROPUESTA: Reorganización UI - Aspecto de Tramitación
**Fecha:** 14 de mayo de 2026  
**Versión:** 1.0

---

## 🔴 PROBLEMA ACTUAL

**La pantalla Full View muestra:**
- ❌ **40 campos** visibles simultáneamente
- ❌ **10-15 botones** todos juntos en la parte superior
- ❌ **Sin indicación visual** de la fase de tramitación
- ❌ **Sin guía** de qué hacer según el estado

**Consecuencia:** Confusión del operador sobre qué acción tomar.

---

## ✅ SOLUCIÓN PROPUESTA: "Wizard de Tramitación"

### Concepto Visual

```
┌─────────────────────────────────────────────────────────────┐
│              SOLICITUD #12345 - GD-00001                    │
├─────────────────────────────────────────────────────────────┤
│  [✅]──[🔵]──[⚪]──[⚪]──[⚪]──[⚪]──[⚪]                │
│  REC    REV    FIR   ENV    CON   AUT    CER                │
│                                                             │
│  Estado actual: PENDENT_REVISAR (Fase 2 de 7)               │
└─────────────────────────────────────────────────────────────┘

╔═════════════════════════════════════════════════════════════╗
║ 📝 DATOS BÁSICOS (siempre visible)                          ║
║   • Procedimiento, órgano, estado, fechas                   ║
╚═════════════════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────────────────┐
│ ▼ SERVICIOS SOLICITADOS (3)                  [Expandir ▼]   │
│   [Lista de servicios...]                                   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│ ▶ DOCUMENTOS Y FORMULARIOS                   [Contraer ►]   │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│ ▼ DATOS DE FIRMA                             [Expandir ▼]  │
│   [Titular: Juan Pérez - 12345678A...]                     │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│ 🔒 TRAMITACIÓN MADRID (No disponible aún)                   │
└─────────────────────────────────────────────────────────────┘

╔═════════════════════════════════════════════════════════════╗
║ 🎯 ACCIÓN PRINCIPAL:                                        ║
║                                                              ║
║   [✍️ ENVIAR A FIRMAR TITULAR]  ← Grande, destacado       ║
║                                                              ║
║ Secundarias: [📝 Generar Formulario DG]                    ║
║ Navegación:  [📊 Eventos]  [✏️ Editar]  [⬅️ Volver]       ║
╚═════════════════════════════════════════════════════════════╝
```

---

## 🎨 ELEMENTOS CLAVE

### 1️⃣ **STEPPER (Indicador de Progreso)**

**Locales:** `[REC]─[REV]─[FIR]─[ENV]─[CON]─[AUT]─[CER]`  
**Estatales:** `[REC]─[REV]─[CED]─[CER]`

- ✅ Verde = Completado
- 🔵 Azul = Actual
- ⚪ Gris = Pendiente
- 🔴 Rojo = Error

### 2️⃣ **SECCIONES COLAPSABLES (Accordion)**

1. **📌 DATOS BÁSICOS** - Siempre visible, no colapsable
2. **📋 SERVICIOS SOLICITADOS** - Expandido por defecto
3. **📄 DOCUMENTOS Y FORMULARIOS** - Colapsado
4. **✍️ DATOS DE FIRMA** - Visible solo en fases 2-4
5. **🌐 TRAMITACIÓN MADRID** - Visible solo en fases 4-6
6. **👥 DATOS ADMINISTRATIVOS** - Colapsado
7. **🔧 DATOS TÉCNICOS** - Colapsado

### 3️⃣ **BOTONES EN 3 NIVELES**

- **🎯 Principal:** 1 botón grande (acción de la fase)
- **📋 Secundarias:** 2-3 botones medianos
- **🔧 Navegación:** Eventos, Editar, Volver (pequeños)

---

## 🗺️ VISIBILIDAD POR ESTADO

### Estado 5: PENDENT_MARCAR_REBUDA
**Stepper:** `[🔵]─[⚪]─[⚪]─[⚪]─[⚪]─[⚪]─[⚪]`  
**Secciones:** Básicos + Servicios + Documentos  
**Botón principal:** `[✅ Marcar Recibida]`

---

### Estado 11: PENDENT_REVISAR
**Stepper:** `[✅]─[🔵]─[⚪]─[⚪]─[⚪]─[⚪]─[⚪]`  
**Secciones:** Básicos + Servicios + Documentos + Datos Firma  
**Botón principal:** `[✍️ Enviar a Firmar]`  
**Secundarios:** `[📝 Generar Formulario DG]` `[📄 Añadir Manual]`

---

### Estado 15: PENDENT_FIRMA_DIRECTOR
**Stepper:** `[✅]─[✅]─[🔵]─[⚪]─[⚪]─[⚪]─[⚪]`  
**Mensaje:** `⏳ Esperando firma en PortaFIB (ID: 12345)`  
**Secciones:** Todo en solo lectura, Datos Firma expandido  
**Botón principal:** `[🔄 Refrescar Estado]`  
**Secundarios:** `[📄 Añadir Firmado Manual]`

---

### Estado 19: PENDENT_ENVIAR_MADRID
**Stepper:** `[✅]─[✅]─[✅]─[🔵]─[⚪]─[⚪]─[⚪]`  
**Secciones:** Básicos + Servicios + Tramitación Madrid (¡ACTIVA!)  
**Botón principal:** `[🚀 ALTA MADRID]` o `[🔧 MODIFICACIÓN]`  
**Secundarios:** `[👁️ Vista Previa XML]`

---

### Estado 20: PENDENT_AUTORITZAR
**Stepper:** `[✅]─[✅]─[✅]─[✅]─[🔵]─[⚪]─[⚪]`  
**Mensaje:** `⏳ Esperando autorización Madrid (InfoMad: IM-98765)`  
**Secciones:** Todo solo lectura, Tramitación Madrid expandida  
**Botón principal:** `[🔄 Consultar Estado Madrid]`  
**Secundarios:** `[📄 Ver InfoMadrid]`

---

### Estado 30: DESESTIMADA
**Stepper:** `[✅]─[✅]─[✅]─[✅]─[✅]─[🔴 RECHAZADA]`  
**Mensaje:** `❌ DESESTIMADA - Motivo: [...]`  
**Botón principal:** `[📧 Contactar Entidad]`  
**Secundarios:** `[🔄 Reenviar Corregida]`

---

### Estado 40: AUTORITZAT
**Stepper:** `[✅]─[✅]─[✅]─[✅]─[✅]─[🔵]─[⚪]`  
**Mensaje:** `✅ AUTORIZADA (14/05/2026 09:30)`  
**Secciones:** Básicos + Servicios + Tramitación Madrid  
**Botón principal:** `[📤 Crear/Actualizar PINBAL]`  
**Secundarios:** `[📄 Ver InfoMadrid]` `[🗑️ Cerrar]`

---

### Estado 60: CERRADA
**Stepper:** `[✅]─[✅]─[✅]─[✅]─[✅]─[✅]─[✅]`  
**Mensaje:** `✅ EXPEDIENTE CERRADO (14/05/2026)`  
**Secciones:** Todo colapsado, solo lectura  
**Botones:** Solo navegación

---

### Estado 35: PENDENT_CONTACTAR_CEDENTS (Estatales)
**Stepper:** `[✅]─[✅]─[🔵]─[⚪]`  
**Secciones:** Básicos + Servicios + Datos Administrativos  
**Botón principal:** `[📧 Enviar Correo Cedentes]`

---

## 💻 IMPLEMENTACIÓN

### OPCIÓN A: JavaScript + CSS ⭐ RECOMENDADA

**Tiempo:** 2-3 días  
**Riesgo:** Bajo  
**Ventajas:**
- ✅ Solo modifica `solicitudFormModificable.jsp`
- ✅ No toca lógica Java
- ✅ Fácil de revertir
- ✅ Rápido de implementar

**Cambios:**
```javascript
// En solicitudFormModificable.jsp
1. Añadir stepper HTML en top
2. JavaScript oculta/muestra secciones según estatSolicitud
3. Reorganizar botones en 3 niveles (CSS)
4. Accordion con Bootstrap collapse
```

**Archivo:** `solicitudFormModificable.jsp` (+300 líneas aprox)

---

### OPCIÓN B: Backend + Frontend

**Tiempo:** 5-7 días  
**Riesgo:** Medio  
**Ventajas:**
- ✅ Más mantenible
- ✅ Lógica en Java
- ✅ Reutilizable

**Archivos:**
- `FaseTramitacioHelper.java` (nuevo)
- `SolicitudFullViewOperadorController.java` (modificar)
- `solicitudFormSecciones.jsp` (nuevo)
- `solicitudFormModificable.jsp` (modificar)

---

### OPCIÓN C: Híbrido

**Tiempo:** 3-4 días  
**Riesgo:** Bajo-Medio  
**Mezcla:** Backend define visibilidad + Frontend maneja UI

---

## 🎯 RECOMENDACIÓN

**Empezar con OPCIÓN A:**

1. **Día 1:** Stepper + reorganización botones
2. **Día 2:** Secciones colapsables + lógica visibilidad
3. **Día 3:** Testing + ajustes

Si funciona bien → Migrar a OPCIÓN C para mejor mantenimiento

---

## ✍️ DECISIÓN

- [ ] ✅ **APROBADA - OPCIÓN A** (JavaScript + CSS - 2-3 días)
- [ ] ✅ **APROBADA - OPCIÓN B** (Backend completo - 5-7 días)
- [ ] ✅ **APROBADA - OPCIÓN C** (Híbrido - 3-4 días)
- [ ] ⚠️ **MODIFICAR:** ___________________________
- [ ] ❌ **RECHAZADA:** ___________________________

**Fecha:** _______________  
**Aprobado por:** _______________

---

**FIN DE LA PROPUESTA**

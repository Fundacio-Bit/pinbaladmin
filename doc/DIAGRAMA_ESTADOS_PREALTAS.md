# 🔄 Diagramas Visuales PREALTAS

> **Referencia visual rápida del flujo de solicitudes PREALTAS**  
> Para documentación completa, ver: [PREALTAS_REFERENCIA_COMPLETA.md](PREALTAS_REFERENCIA_COMPLETA.md)

## Leyenda de Iconos

| Tipo | Iconos | Significado |
|------|--------|-------------|
| **👤 Manual** | ✅📋📨✍️🔍 | Acciones de tramitador/director |
| **🤖 Automático** | 📤📧🔄⏱️🔁💾 | Acciones del sistema |
| **🏢 Madrid** | 🤖🏢📨 | Validación/respuesta de Madrid |

---

## FLUJO PRINCIPAL: SOLICITUD NUEVA (ALTA)
### Con Acciones Detalladas

```
┌─────────────────────────────────────────────────────────────────────┐
│                      INICIO - ALTA NUEVA                            │
└─────────────────────────────────────────────────────────────────────┘

                    🤖 Llega desde CAIB (automático)
                              │
                 ↓
                   ┌──────────────────────┐
                   │   Estado 5           │
                   │ PENDENT_DISTRIBUCIO  │
                   └──────────┬───────────┘
                              │
                  👤 ACCIÓN TRAMITADOR:
                  ✅ Marcar como recibida
                  📋 Revisar que esté completa
                              │
                              ↓
                   ┌──────────────────────┐
                   │   Estado 11          │
                   │ PENDENT_Enviar_DG    │
                   └──────────┬───────────┘
                              │
                  👤 ACCIÓN TRAMITADOR:
                  📋 Revisar documentación
                  📋 Verificar normativa
                  📋 Comprobar justificación
                  📨 Enviar a firmar al Director
                              │
                              ↓
                   ┌──────────────────────┐
                   │   Estado 15          │
                   │ PENDENT_Firma_DG     │
                   └──────────┬───────────┘
                              │
                  ✍️ ACCIÓN DIRECTOR:
                  ✍️ Firmar digitalmente
                              │
                              ↓
                   ┌──────────────────────┐
                   │   Estado 19          │
                   │ PENDENT_ENVIAR_MAD   │
                   └──────────┬───────────┘
                              │
                  👤 ACCIÓN TRAMITADOR:
                  📤 Enviar a Madrid (click botón)
                              │
                  🤖 Luego SISTEMA AUTOMÁTICO:
                  💾 Generar XML
                  📤 Enviar a Madrid (SOAP)
                  💾 Guardar log envío
                              │
                     RESPUESTA INMEDIATA
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
        ↓                     ↓                     ↓
   Código "0"           Código "0228"        Código "2"/"1"
   Registrada OK        Desest. Auto          Analizar
        │                     │                     │
        │              🤖🏢 MADRID ROBOT:           │
        │              🤖🏢 Validación AUTO         │
        │              📧 Email a entidad          │
        │              💾 Guardar motivo           │
        │                     │                     │
        │                     ↓                     ↓
        │              ┌──────────────┐       ┌──────────┐
        │              │  Estado 30   │       │ ¿Error?  │
        │              │  ❌ ESMENES  │       └────┬─────┘
        │              └──────────────┘            │
        │                                          ├→ "01" Duplicado
        │                                          │  🤖 Aceptar
        │                                          │  🔄 Estado 20
        │                                          │
        │                                          ├→ "27" Ya de alta
        │                                          │  🤖 Marcar autorizado
        │                                          │  💾 dataAutoritzacio
        │                                          │  🔄 Estado 19
        │                                          │
        │                                          └→ Otros errores
        │                                             ⚡ Estado 44
        ↓
 ┌───────────────┐
 │  Estado 20    │
 │ ⏳ PENDENT_   │
 │  AUTORITZAR   │
 └───────┬───────┘
         │
   🤖 ACCIÓN AUTOMÁTICA:
   🔍 Consultar Madrid cada 30 min
   💾 Guardar respuestas
         │
         ↓
  ¿Qué responde Madrid?
         │
    ┌────┴──────────────────────┐
    │                           │
    ↓                           ↓
 "Pendiente"            "Procesado por humano"
  Sigue en 20                   │
  🔍 Seguir         ┌───────────┴───────────┐
    consultando     │                       │
                    ↓                       ↓
                Autorizado              Desestimado
                    │                       │
            🤖 ACCIONES:            🤖 ACCIONES:
            🔄 Estado 40            🔄 Estado 30
            💾 Fecha autoriz        💾 Guardar motivo
            💾 Marcar servicios     📧 Email motivo
            📧 Email éxito          
            💾 Registrar            
                    │                       │
                    ↓                       ↓
                Estado 40               Estado 30
                  ✅ DE ALTA              ❌ NO ALTA

⚠️ ESTADO 39 (Parcial): Es un caso especial informativo cuando hay
   servicios ya autorizados y otros no. Se considera DE ALTA.
   Para autorizarlo completo se hace una MODIFICACIÓN.


                 ERROR TÉCNICO (timeout, API...)
                              │
                   🤖 ACCIONES AUTOMÁTICAS:
                   💾 Guardar error en logs
                   💾 Registrar evento error
                   ⏱️ Programar reintento 02:00
                              │
                              ↓
                      ┌───────────────┐
                      │  Estado 44    │
                      │  ⚡ ERROR     │
                      └───────┬───────┘
                              │
                      A las 02:00 AM:
                   🤖 ACCIÓN AUTOMÁTICA:
                   🔁 Reintentar envío
                   📤 Volver a enviar a Madrid
```

---

## FLUJO MODIFICACIÓN: CAMBIOS SOBRE AUTORIZADO
### Con Acciones Detalladas

```
┌─────────────────────────────────────────────────────────────────────┐
│              INICIO - MODIFICACIÓN (YA DE ALTA)                     │
└─────────────────────────────────────────────────────────────────────┘

              Procedimiento en Estado 40 ✅
                   (YA DE ALTA)
                        │
           🤖 Llega solicitud de CAMBIO desde CAIB
                        │
                        ↓
             ┌──────────────────────┐
             │   Estado 33          │
             │ CANVI_PENDENT_REV    │
             └──────────┬───────────┘
                        │
             👤 ACCIÓN TRAMITADOR:
             📋 Revisar qué cambios solicita
             📋 Verificar nueva normativa (si hay)
             📋 Comprobar justificación cambios
             ✅ Aprobar para envío
                        │
                        ↓
             ┌──────────────────────┐
             │   Estado 19          │
             │ PENDENT_ENVIAR_MAD   │
             └──────────┬───────────┘
                        │
             👤 ACCIÓN TRAMITADOR:
             📤 Enviar a Madrid (click botón)
                        │
             🤖 Luego SISTEMA AUTOMÁTICO:
             🔍 Detectar que tiene dataAutoritzacio
             💾 Generar XML MODIFICACIÓN (solo cambios)
             📤 Enviar a Madrid (SOAP)
             💾 Guardar log
                        │
                        ↓
             ┌──────────────────────┐
             │   Estado 20          │
             │ PENDENT_AUTORITZAR   │
             └──────────┬───────────┘
                        │
             🤖 ACCIÓN AUTOMÁTICA:
             🔍 Consultar Madrid cada 30 min
                        │
                 Días/semanas...
                 🏢 Madrid procesa
                        │
        ┌───────────────┼───────────────┐
        │               │               │
        ↓               ↓               ↓
   Autorizado       Parcial        Rechazado
   (todos OK)    (algunos OK)    (ninguno OK)
        │               │               │
        │               │               │
 🤖 ACCIONES:    🤖 ACCIONES:    🤖 ACCIONES:
 🔄 Estado 40    🔄 Estado 39    🔄 Estado 40
 💾 Actualizar   💾 Marcar OK    💾 NO cambiar
    servicios       y KO            servicios
 📧 Email OK     📧 Email        📧 Email
                    detalle         rechazo
        │               │               │
        ↓               ↓               ↓
  ┌──────────┐   ┌──────────┐   ┌──────────┐
  │Estado 40 │   │Estado 39 │   │Estado 40 │
  │✅ TODO   │   │⚠️ PARCIAL│   │✅ (OJO!) │
  │  SIGUE   │   │  ANTERIOR│   │  ANTERIOR│
  │  DE ALTA │   │  +ALGUNOS│   │  SIGUE   │
  │  +NUEVOS │   │  NUEVOS  │   │  DE ALTA │
  └──────────┘   └──────────┘   └──────────┘

⚠️ IMPORTANTE: En modificaciones rechazadas, el procedimiento
              SIGUE DE ALTA con los servicios anteriores.
              Solo se rechazan los cambios nuevos.
```

---

## Tabla Resumen de Estados

| Estado | Nombre | Quién | Acción Principal | Siguiente |
|--------|--------|-------|------------------|-----------|
| **5** | PENDENT_DISTRIBUCIO | 👤 | ✅ Marcar + 📋 Revisar | 11 |
| **11** | PENDENT_Enviar_Director | 👤 | 📋 Revisar + 📨 Enviar DG | 15 |
| **15** | PENDENT_Firma_Director | ✍️ | ✍️ Firmar | 19 |
| **19** | PENDENT_ENVIAR_MADRID | 👤🤖 | 📤 Enviar Madrid | 20/30/44 |
| **20** | PENDENT_AUTORITZAR | 🤖 | 🔍 Consultar cada 30min | 40/39/30 |
| **30** | ESMENES | 🤖 | 📧 Email rechazo | - |
| **33** | CANVI_PENDENT_REVISAR | 👤 | 📋 Revisar + ✅ Aprobar | 19 |
| **39** | AUTORITZAT_Parcial | 🤖 | 💾 Marcar OK/KO | - |
| **40** | AUTORITZAT | 🤖 | 💾 Autorizar + 📧 Email | - |
| **44** | ERROR_ENVIANT_MADRID | 🤖 | ⏱️ Reintento 02:00 | 19/20 |

---

## Acciones por Usuario

### 👤 Tramitador
- **Estado 5:** ✅ Marcar recibida + 📋 Revisar completa
- **Estado 11:** 📋 Revisar documentación + 📋 Verificar normativa + 📨 Enviar a Director
- **Estado 19:** 📤 Enviar a Madrid (click botón)
- **Estado 33:** 📋 Revisar cambios + ✅ Aprobar

### ✍️ Director
- **Estado 15:** ✍️ Firmar digitalmente

### 🤖 Sistema
- **Estado 19 →:** 💾 XML + 📤 SOAP + 🔄 Cambio estado
- **Estado 20:** 🔍 Consultar cada 30 min + 💾 Guardar respuestas
- **Estado 30/40/39:** 📧 Email + 💾 Datos
- **Estado 44:** 💾 Error + ⏱️ Reintento 02:00

---

## Resumen Ultra-Rápido

**🎯 TÚ solo trabajas en 4 estados: 5 → 11 → 19 → 33**

**🤖 El resto es AUTOMÁTICO o del Director**

**⏰ Tiempos típicos:**
- Estado 5-19: Horas/días (depende de ti y Director)
- Estado 20: 1-6 semanas (Madrid procesa)
- Estado 44: Reintento automático a las 02:00

---

**Versión:** 2.0 (Optimizado - Solo Diagramas Visuales)  
**Fecha:** Mayo 2026  
**Propósito:** Referencia visual rápida - Ver [PREALTAS_REFERENCIA_COMPLETA.md](PREALTAS_REFERENCIA_COMPLETA.md) para detalles

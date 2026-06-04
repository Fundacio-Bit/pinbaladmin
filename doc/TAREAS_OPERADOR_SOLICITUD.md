# TAREAS DEL OPERADOR - GESTIÓN DE SOLICITUD

**Checklist simple desde recibir hasta notificar**

---

## ✅ LISTA DE TAREAS

### 1. Marcar Solicitud como Recibida
- **Estado**: 5 → 11
- **Dónde**: Lista de solicitudes pendientes de distribución
- **Acción**: Click en la solicitud → Botón "Marcar como recibida"

### 2. Revisar Solicitud
- **Estado**: 11
- **Dónde**: Ver detalle de la solicitud
- **Revisar**:
  - Datos del procedimiento completos
  - Servicios solicitados
  - Normativa legal adjunta
  - Documentos (PDFs)
  - Contactos válidos

### 3. Enviar a Firmar
- **Estado**: 11 → 15
- **Dónde**: Detalle solicitud
- **Acción**: Botón "Enviar a Firmar" (al titular vía PortaFIB)

### 4. Esperar Firma
- **Estado**: 15 → 19
- **Automático**: Cuando el titular firma en PortaFIB
- **No requiere acción del operador**

### 5. Enviar a Madrid
- **Estado**: 19 → 20
- **Dónde**: Detalle solicitud
- **Acción**: Botón "Enviar a Madrid"

### 6. Esperar Autorización Madrid
- **Estado**: 20
- **Automático**: Sistema consulta cada 30 minutos
- **No requiere acción del operador** (solo esperar 2-4 semanas)

### 7. Cuando Madrid Autoriza
- **Estado**: 20 → 40
- **Automático**: Sistema detecta autorización y cambia estado
- **Email automático**: Se envía al contacto de la entidad
- **No requiere acción del operador**

### 8. Cerrar Solicitud (Opcional)
- **Estado**: 40 → 60
- **Dónde**: Detalle solicitud autorizada
- **Acción**: Botón "Marcar como Cerrada"

---

## 🔴 SI DESESTIMAN (Estado 30)

### Contactar con la Entidad
- **Cuando**: Estado pasa de 20 → 30
- **Acción**: 
  - Revisar motivo de desestimación (campo "Mensaje Madrid")
  - Llamar al contacto de la entidad
  - Explicar el motivo en lenguaje claro
  - Indicar que deben crear NUEVA solicitud

---

## ⚡ SI ERROR TÉCNICO (Estado 44)

### Verificar Reintento Automático
- **Cuando**: Error al enviar a Madrid
- **Acción**: Sistema reintenta automáticamente a las 02:00 AM
- **Si persiste**: Revisar log de error y contactar soporte técnico

---

## 📊 ESTADOS RESUMEN

| Estado | Nombre | Acción del Operador |
|--------|--------|---------------------|
| **5** | Distribució | ✅ Marcar recibida |
| **11** | Revisión | ✅ Revisar y enviar a firmar |
| **15** | Firma | ⏳ Esperar (automático) |
| **19** | Pre-Madrid | ✅ Enviar a Madrid |
| **20** | Esperando Madrid | ⏳ Esperar (automático) |
| **30** | Desestimada | ✅ Contactar entidad |
| **40** | Autorizada | ✅ (Opcional) Cerrar |
| **44** | Error | ⏳ Reintento automático |
| **60** | Cerrada | - |

# PoC: Normalización InfoMadrid - Plan de Acción

## 🎯 Objetivo

**Consolidar datos del titular en `pad_infomadrid`:**
- **ANTES**: `titularnom` (concatenado), `titularnif`
- **DESPUÉS**: `titularid` → FK a `pad_contacte`

## 📊 Situación Actual

### Tabla `pad_infomadrid`
```sql
CREATE TABLE pad_infomadrid (
    infomadridid BIGSERIAL PRIMARY KEY,
    titularnom VARCHAR(255),   -- "Juan García López" concatenado ❌
    titularnif VARCHAR(40),    -- NIF aislado ❌
    -- otros campos...
);
```

**Problemas:**
- Nombre concatenado → difícil acceder a apellidos separados
- Si cambias el email → no sabes que es la misma persona
- Al copiar datos entre InfoMadrids → duplicas todos los campos

## ✅ Solución Propuesta

### 1. Modificar `pad_contacte`
```sql
-- Permitir NULLs (no todos tienen NIF/email)
ALTER TABLE pad_contacte 
    ALTER COLUMN nif DROP NOT NULL,
    ALTER COLUMN mail DROP NOT NULL;

-- Agregar campo calculado
ALTER TABLE pad_contacte 
    ADD COLUMN nomcomplet VARCHAR(300) 
    GENERATED ALWAYS AS (
        TRIM(CONCAT_WS(' ', nom, llinatge1, llinatge2))
    ) STORED;
```

### 2. Modificar `pad_infomadrid`
```sql
-- Agregar FK al titular
ALTER TABLE pad_infomadrid 
    ADD COLUMN titularid BIGINT;

ALTER TABLE pad_infomadrid 
    ADD CONSTRAINT fk_infomadrid_titular 
    FOREIGN KEY (titularid) REFERENCES pad_contacte(contacteid);
```

### 3. Función de Deduplicación
```sql
CREATE OR REPLACE FUNCTION crear_o_obtener_persona(
    p_nom VARCHAR(100),
    p_llinatge1 VARCHAR(100),
    p_llinatge2 VARCHAR(100),
    p_nif VARCHAR(40),
    p_mail VARCHAR(100),
    p_telefon VARCHAR(20),
    p_carrec VARCHAR(100)
) RETURNS BIGINT AS $$
DECLARE
    v_contacteid BIGINT;
BEGIN
    -- Buscar persona existente (todos los campos deben coincidir)
    SELECT contacteid INTO v_contacteid
    FROM pad_contacte
    WHERE LOWER(TRIM(COALESCE(nom, ''))) = LOWER(TRIM(COALESCE(p_nom, '')))
      AND LOWER(TRIM(COALESCE(llinatge1, ''))) = LOWER(TRIM(COALESCE(p_llinatge1, '')))
      AND LOWER(TRIM(COALESCE(llinatge2, ''))) = LOWER(TRIM(COALESCE(p_llinatge2, '')))
      AND LOWER(TRIM(COALESCE(nif, ''))) = LOWER(TRIM(COALESCE(p_nif, '')))
      AND LOWER(TRIM(COALESCE(mail, ''))) = LOWER(TRIM(COALESCE(p_mail, '')))
      AND LOWER(TRIM(COALESCE(telefon, ''))) = LOWER(TRIM(COALESCE(p_telefon, '')))
      AND LOWER(TRIM(COALESCE(carrec, ''))) = LOWER(TRIM(COALESCE(p_carrec, '')))
    LIMIT 1;
    
    -- Si no existe, crear nueva
    IF v_contacteid IS NULL THEN
        INSERT INTO pad_contacte (nom, llinatge1, llinatge2, nif, mail, telefon, carrec)
        VALUES (p_nom, p_llinatge1, p_llinatge2, p_nif, p_mail, p_telefon, p_carrec)
        RETURNING contacteid INTO v_contacteid;
    END IF;
    
    RETURN v_contacteid;
END;
$$ LANGUAGE plpgsql;
```

### 4. Migrar Datos Existentes
```sql
-- Script de migración para InfoMadrid
DO $$
DECLARE
    r RECORD;
    v_nom VARCHAR(100);
    v_llinatge1 VARCHAR(100);
    v_llinatge2 VARCHAR(100);
    v_titularid BIGINT;
BEGIN
    FOR r IN SELECT infomadridid, titularnom, titularnif FROM pad_infomadrid WHERE titularid IS NULL
    LOOP
        -- Parsear nombre concatenado: "Juan García López"
        -- Estrategia simple: primera palabra = nom, resto = apellidos
        v_nom := SPLIT_PART(r.titularnom, ' ', 1);
        v_llinatge1 := SPLIT_PART(r.titularnom, ' ', 2);
        v_llinatge2 := SPLIT_PART(r.titularnom, ' ', 3);
        
        -- Crear o buscar persona
        v_titularid := crear_o_obtener_persona(
            v_nom, v_llinatge1, v_llinatge2, 
            r.titularnif, NULL, NULL, NULL
        );
        
        -- Actualizar FK
        UPDATE pad_infomadrid 
        SET titularid = v_titularid 
        WHERE infomadridid = r.infomadridid;
    END LOOP;
END $$;

-- Eliminar columnas antiguas (después de validar)
-- ALTER TABLE pad_infomadrid DROP COLUMN titularnom;
-- ALTER TABLE pad_infomadrid DROP COLUMN titularnif;
```

## 🔧 Cambios en Código Java

### 1. Actualizar Entidad `InfoMadrid.java`
```java
@Entity
@Table(name = "pad_infomadrid")
public class InfoMadrid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infomadridid")
    private Long id;
    
    // NUEVO: Relación con contacte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titularid")
    private Contacte titular;
    
    // DEPRECADO: Mantener temporalmente para migración
    @Column(name = "titularnom")
    @Deprecated
    private String titularNom;
    
    @Column(name = "titularnif")
    @Deprecated
    private String titularNif;
    
    // Getters/Setters...
}
```

### 2. Crear Servicio de Deduplicación
```java
@Stateless
public class ContacteService {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Busca persona existente o crea nueva si no existe.
     * Deduplicación: todos los campos deben coincidir exactamente.
     */
    public Contacte buscarOCrear(ContacteDTO dto) {
        // Normalizar datos
        String nom = normalizar(dto.getNom());
        String llinatge1 = normalizar(dto.getLlinatge1());
        String llinatge2 = normalizar(dto.getLlinatge2());
        String nif = normalizar(dto.getNif());
        String mail = normalizar(dto.getMail());
        String telefon = normalizar(dto.getTelefon());
        String carrec = normalizar(dto.getCarrec());
        
        // Buscar existente
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contacte> query = cb.createQuery(Contacte.class);
        Root<Contacte> root = query.from(Contacte.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(compareField(cb, root.get("nom"), nom));
        predicates.add(compareField(cb, root.get("llinatge1"), llinatge1));
        predicates.add(compareField(cb, root.get("llinatge2"), llinatge2));
        predicates.add(compareField(cb, root.get("nif"), nif));
        predicates.add(compareField(cb, root.get("mail"), mail));
        predicates.add(compareField(cb, root.get("telefon"), telefon));
        predicates.add(compareField(cb, root.get("carrec"), carrec));
        
        query.where(cb.and(predicates.toArray(new Predicate[0])));
        
        List<Contacte> existents = em.createQuery(query).getResultList();
        
        if (!existents.isEmpty()) {
            return existents.get(0); // Usar existente
        }
        
        // Crear nuevo
        Contacte nou = new Contacte();
        nou.setNom(nom);
        nou.setLlinatge1(llinatge1);
        nou.setLlinatge2(llinatge2);
        nou.setNif(nif);
        nou.setMail(mail);
        nou.setTelefon(telefon);
        nou.setCarrec(carrec);
        em.persist(nou);
        
        return nou;
    }
    
    private String normalizar(String valor) {
        return valor == null ? "" : valor.trim().toLowerCase();
    }
    
    private Predicate compareField(CriteriaBuilder cb, Path<String> field, String valor) {
        if (valor == null || valor.isEmpty()) {
            return cb.or(cb.isNull(field), cb.equal(cb.lower(cb.trim(field)), ""));
        }
        return cb.equal(cb.lower(cb.trim(field)), valor);
    }
}
```

### 3. Actualizar Controlador
```java
@Stateless
public class InfoMadridController {
    
    @Inject
    private ContacteService contacteService;
    
    public InfoMadrid crearInfoMadrid(InfoMadridDTO dto) {
        InfoMadrid infomadrid = new InfoMadrid();
        
        // NUEVO: Buscar o crear titular automáticamente
        ContacteDTO titularDTO = new ContacteDTO();
        titularDTO.setNom(dto.getTitularNom());      // del formulario
        titularDTO.setNif(dto.getTitularNif());      // del formulario
        // ... otros campos si los hay
        
        Contacte titular = contacteService.buscarOCrear(titularDTO);
        infomadrid.setTitular(titular);
        
        // ... resto de campos
        
        em.persist(infomadrid);
        return infomadrid;
    }
}
```

## 📋 Plan de Ejecución

### Fase 1: Preparación (2-4h)
- [ ] Backup de base de datos
- [ ] Ejecutar ALTER TABLE en `pad_contacte` (NULLs + nomcomplet)
- [ ] Ejecutar ALTER TABLE en `pad_infomadrid` (agregar titularid)
- [ ] Crear función `crear_o_obtener_persona()`

### Fase 2: Migración de Datos (4-8h)
- [ ] Ejecutar script de migración
- [ ] **Validar parsing de nombres**: revisar casos especiales
  - Nombres compuestos: "María del Carmen"
  - Apellidos compuestos: "García López de la Torre"
  - Nombres con un solo apellido
- [ ] Verificar que todos los InfoMadrids tienen `titularid`
- [ ] Validar que no hay duplicados innecesarios en `pad_contacte`

### Fase 3: Código Java (8-12h)
- [ ] Actualizar `InfoMadrid.java` (agregar relación titular)
- [ ] Crear `ContacteService.buscarOCrear()`
- [ ] Actualizar `InfoMadridController` (usar servicio)
- [ ] Actualizar DTOs si es necesario
- [ ] Pruebas unitarias del servicio de deduplicación

### Fase 4: Testing (4-8h)
- [ ] Crear nuevo InfoMadrid → debe crear nuevo contacte
- [ ] Crear InfoMadrid con mismo titular → debe reusar contacte
- [ ] Cambiar un campo (email) → debe crear nuevo contacte
- [ ] Verificar acceso a `titular.getNom()`, `titular.getLlinatge1()`
- [ ] Verificar acceso a `titular.getNomComplet()`

### Fase 5: Limpieza (2-4h)
- [ ] Eliminar columnas antiguas `titularnom`, `titularnif`
- [ ] Marcar `titularid` como NOT NULL
- [ ] Eliminar código deprecated
- [ ] Actualizar documentación

**Total estimado: 20-36 horas**

## ⚠️ Riesgos Identificados

| Riesgo | Impacto | Mitigación |
|--------|---------|------------|
| Parsing incorrecto de nombres | Alto | Revisar manualmente casos especiales antes de DROP columnas |
| Duplicados falsos (trim/case) | Medio | Normalización en función SQL y Java |
| Performance JOINs | Bajo | Índice en `titularid`, usar LAZY fetch |
| Datos incompletos migrados | Alto | Mantener columnas antiguas temporalmente |

## ✅ Criterios de Éxito

1. ✅ Todos los InfoMadrids tienen `titularid`
2. ✅ Creación automática de contacte funciona
3. ✅ Deduplicación detecta personas iguales
4. ✅ Acceso a campos separados (`nom`, `llinatge1`) y concatenado (`nomComplet`)
5. ✅ No hay regresiones en funcionalidad existente
6. ✅ Tiempo de respuesta aceptable (<200ms adicionales)

## 🚀 Próximos Pasos (Después del PoC)

Si el PoC es exitoso:
1. Aplicar mismo patrón a `pad_solicitud` (3 personas: titular, responsable, contacte)
2. Aplicar a `pad_pinfo` (solicitant, destinatari)
3. Aplicar a trámites (4 tablas con estructura similar)

**Esfuerzo total estimado proyecto completo: 120-200 horas**

---

## 📝 Notas de Implementación

- **Formularios**: NO cambian, siguen mostrando campos separados
- **Deduplicación**: Automática en backend, invisible para el usuario
- **NomComplet**: Calculado automáticamente (columna GENERATED o método Java)
- **Validación**: Todos los campos NULL/vacío se tratan como equivalentes
- **Cascada**: NO usar ON DELETE CASCADE (mantener personas aunque se borre InfoMadrid)

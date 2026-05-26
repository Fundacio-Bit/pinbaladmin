

<c:if test="${isPinfo == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

	<style>
/* ========================================
   FORMULARIO DE PINFO - Dades del Sol·licitant
   ======================================== */

/* Contenedor del formulario - diseño limpio sin bordes */
form {
	background: white;
	padding: 2.5rem;
	padding-top: 1.5rem;
	border-radius: 12px;
	margin: 2rem 7rem;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
	border: none;
}

/* Contenedor principal */
.module_content {
	background: transparent;
	padding: 0;
	margin: 0;
}

/* Título del formulario */
.lead {
	margin-bottom: 0 !important;
	padding-bottom: 0;
	border-bottom: none;
}

.lead label {
	margin-bottom: 0;
}

/* Tabla del formulario */
#incidenciaTecnica_tableid {
	width: 100%;
	max-width: 60rem;
	margin: auto;
	border: none !important;
	background: white;
}

#incidenciaTecnica_tableid tbody tr {
	border: none !important;
	background: transparent !important;
}

#incidenciaTecnica_tableid tbody tr:nth-child(even) {
	background: rgba(77, 186, 121, 0.02) !important;
}

/* Labels de los campos */
#incidenciaTecnica_tableid td[id$='_columnlabelid'] {
	width: 1%;
	padding: 0.7rem 1.2rem !important;
	background: #f8f9fa;
	border: none !important;
	border-left: 4px solid #4DBA79 !important;
	vertical-align: middle !important;
	font-weight: 600;
	color: #265d3c;
	white-space: nowrap;
}

#incidenciaTecnica_tableid td[id$='_columnlabelid'] label {
	margin: 0;
	font-size: 14px;
	color: #265d3c;
	padding-left: 0;
	white-space: nowrap;
}

/* Valores de los campos */
#incidenciaTecnica_tableid td[id$='_columnvalueid'] {
	padding: 0.8rem 1.2rem !important;
	border: none !important;
	background: white;
	vertical-align: middle !important;
	width: 100%;
}

/* Inputs y textareas */
#incidenciaTecnica_tableid input[type="text"],
#incidenciaTecnica_tableid textarea,
#incidenciaTecnica_tableid select,
#incidenciaTecnica_tableid input[type="search"] {
	border: 1px solid #e0e0e0 !important;
	border-radius: 6px !important;
	padding: 8px 12px !important;
	transition: all 0.2s ease !important;
	background: white !important;
}

#incidenciaTecnica_tableid input[type="text"]:focus,
#incidenciaTecnica_tableid textarea:focus,
#incidenciaTecnica_tableid select:focus,
#incidenciaTecnica_tableid input[type="search"]:focus {
	border-color: #4DBA79 !important;
	box-shadow: 0 0 0 3px rgba(77, 186, 121, 0.1) !important;
	outline: none !important;
}

/* Campos readonly - estilo consistente */
#incidenciaTecnica_tableid input[readonly],
#incidenciaTecnica_tableid textarea[readonly] {
	background: #f8f9fa !important;
	color: #6c757d !important;
	cursor: not-allowed !important;
	border-color: #e9ecef !important;
}

/* Textarea específico */
#incidenciaTecnica_tableid textarea {
	min-height: 80px !important;
	resize: vertical !important;
	font-family: inherit !important;
	width: 100% !important;
}

/* Ocultar el dropdown de wrap del textarea */
#dropdownMenuButton_descripcio {
	display: none !important;
}

/* Tabla interna del textarea - ancho completo y sin espaciado */
#incidenciaTecnica_tableid td[id$='_columnvalueid'] table {
	width: 100% !important;
	border: none !important;
	margin: 0 !important;
	padding: 0 !important;
}

#incidenciaTecnica_tableid td[id$='_columnvalueid'] table td {
	padding: 0 !important;
	border: none !important;
}

/* Dropdown del textarea */
#incidenciaTecnica_tableid .dropdown-toggle {
	display: none !important;
}

.dropdown-menu {
	display: none !important;
}

/* Select desplegables */
#incidenciaTecnica_tableid select {
	appearance: auto;
	-webkit-appearance: auto;
	-moz-appearance: auto;
	cursor: pointer;
}

/* Header con botón arriba */
/* .form-header-pinfo {
	display: flex;
  	justify-content: space-between;
  	align-items: center;
  	border-bottom: 1px solid #e9ecef;
  	padding: 0 3rem 1rem 3rem;
  	margin-bottom: 1rem;
} */

/* Botones del formulario */
.navbar-form {
	display: none !important;
}

.navbar-form .btn {
	font-size: 14px !important;
	padding: 10px 28px !important;
	border-radius: 8px !important;
	font-weight: 600 !important;
	transition: all 0.2s ease !important;
	border: none !important;
	margin-left: 12px;
}

/* Botón Continuar arriba */
.btn-continuar-pinfo {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%);
	color: white;
	padding: 10px 28px;
	border-radius: 8px;
	font-weight: 600;
	font-size: 15px;
	border: none;
	cursor: pointer;
	transition: all 0.2s;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.2);
}

.btn-continuar-pinfo:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%);
	transform: translateY(-1px);
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3);
}

/* Mensajes de error */
.errorField {
	margin-top: 8px;
	padding: 10px 14px;
	border-radius: 6px;
	font-size: 13px;
	border-left: 4px solid #e74c3c;
	background: #fef5f5;
}

/* Iconos de ayuda */
.fa-info-circle {
	color: #4DBA79;
	margin-left: 6px;
	cursor: help;
	opacity: 0.8;
}

.fa-info-circle:hover {
	opacity: 1;
}

/* Contenedor de fechas - calendario verde */
.input-group-text {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%) !important;
	border: none !important;
	color: white !important;
	border-radius: 0 6px 6px 0 !important;
	cursor: pointer !important;
	transition: all 0.2s ease !important;
}

.input-group-text:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%) !important;
}

.input-group.date {
	display: flex;
}

/* Autocomplete dropdown para órganos */
.autocomplete-dropdown {
	border: 1px solid #e0e0e0 !important;
	border-radius: 6px !important;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1) !important;
	margin-top: 4px;
}

.autocomplete-dropdown-item {
	padding: 10px 14px;
	font-size: 14px;
	transition: all 0.2s;
}

.autocomplete-dropdown-item:hover {
	background-color: rgba(77, 186, 121, 0.08) !important;
	color: #265d3c;
}

/* Ajustes responsivos */
@media (max-width: 768px) {
	form {
		padding: 1.5rem;
		margin: 1rem;
	}
	
	#incidenciaTecnica_tableid {
		width: 100%;
	}
	
	#incidenciaTecnica_tableid td[id$='_columnlabelid'],
	#incidenciaTecnica_tableid td[id$='_columnvalueid'] {
		display: block;
		width: 100%;
	}
	
	#incidenciaTecnica_tableid td[id$='_columnlabelid'] {
		border-left: none !important;
		border-bottom: 2px solid #4DBA79 !important;
		padding: 8px 12px !important;
	}
	
	.navbar-form .btn {
		display: block;
		width: 100%;
		margin: 8px 0 !important;
	}
}
</style>

<!-- Script para mover el botón Guardar arriba y cambiar texto a Continuar -->
<script>
    // Cargar mensajes de traducción para validación
    var MSG_TITOL_OBLIGATORI = '<fmt:message key="tramit.pinfo.validacio.titol.obligatori"/>';
    var MSG_EMAIL_OBLIGATORI = '<fmt:message key="tramit.pinfo.validacio.email.obligatori"/>';
    var MSG_EMAIL_FORMAT = '<fmt:message key="tramit.pinfo.validacio.email.format"/>';
    
    $(document).ready(function() {
        // Buscar el botón de guardar original
        var saveButton = $('.navbar-form .btn-primary');
        
        if (saveButton.length > 0) {
            // Crear el contenedor header
            var headerDiv = $('<div class="titol-tramit-pinfo-header"></div>');
            
            // Mover el título al header y añadir clase común
            var leadDiv = $('.lead').detach();
            leadDiv.find('label').addClass('titol-tramit-pinfo');
            headerDiv.append(leadDiv);
            
            // Clonar el botón y cambiar su texto y clase
            var continuarButton = saveButton.clone();
            continuarButton.removeClass('btn-primary').addClass('btn-continuar-pinfo');
            continuarButton.val('Continuar');
            
            // Agregar el botón al header
            headerDiv.append(continuarButton);
            
            // Insertar el header al inicio de module_content
            $('.module_content').prepend(headerDiv);
            
            // Copiar el evento submit del botón original con validación
            continuarButton.on('click', function(e) {
                e.preventDefault();
                if (validarFormularioPinfo()) {
                    saveButton.click();
                }
                return false;
            });
        }
        
        // ========================================
        // VALIDACIÓN DEL FORMULARIO
        // ========================================
        
        // Función para crear mensaje de error al estilo genapp
        function crearMissatgeError(element, missatge) {
            var span = document.createElement("span");
            span.id = element.id + ".errors";
            span.className = "errorField alert alert-danger";
            span.innerHTML = missatge;
            element.parentElement.prepend(span);
        }
        
        // Función de validación del formulario
        function validarFormularioPinfo() {
            var validacio = true;
            
            // Eliminar errores previos
            $(".errorField").remove();
            
            // Validar Título (obligatorio)
            var titolInput = document.getElementById("incidenciaTecnica.titol");
            if (!titolInput || !titolInput.value || titolInput.value.trim() === "") {
                if (titolInput) {
                    crearMissatgeError(titolInput, MSG_TITOL_OBLIGATORI);
                }
                validacio = false;
            }
            
            // Validar Email del contacto (obligatorio y formato válido)
            var emailInput = document.getElementById("incidenciaTecnica.contacteEmail");
            if (!emailInput || !emailInput.value || emailInput.value.trim() === "") {
                if (emailInput) {
                    crearMissatgeError(emailInput, MSG_EMAIL_OBLIGATORI);
                }
                validacio = false;
            } else {
                // Validar formato del email
                var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(emailInput.value.trim())) {
                    crearMissatgeError(emailInput, MSG_EMAIL_FORMAT);
                    validacio = false;
                }
            }
            
            return validacio;
        }
        
        // Interceptar el submit del formulario
        var form = $('form[name="incidenciaTecnicaForm"]');
        if (form.length > 0) {
            form.on('submit', function(e) {
                if (!validarFormularioPinfo()) {
                    e.preventDefault();
                    e.stopPropagation();
                    return false;
                }
            });
        }
        
        // Quitar el asterisco obligatorio del campo Observacions (DESCRIPCIO)
        var labelObservacions = $('#incidenciaTecnica_descripcio_columnlabelid label');
        if (labelObservacions.length > 0) {
            var textoLabel = labelObservacions.html();
            // Eliminar el &nbsp;(*) del final
            textoLabel = textoLabel.replace(/\s*&nbsp;\(\*\)\s*$/, '');
            labelObservacions.html(textoLabel);
        }
        
        // Reordenar campos en el orden específico:
        // 1. Nom (readonly), 2. Órgano gestor (readonly), 3. Departament, 4. Correu, 5. Telèfon, 6. Títol, 7. Observacions
        var tbody = $('#incidenciaTecnica_tableid tbody');
        if (tbody.length > 0) {
			tbody = tbody[0]; // Asegurarse de seleccionar solo el primer tbody si hay más de uno
            // Obtener todos los campos
            var contacteNom = $('#incidenciaTecnica_contacteNom_rowid');
            var organid = $('#incidenciaTecnica_organid_rowid');
            var nomEntitat = $('#incidenciaTecnica_nomEntitat_rowid');
            var contacteEmail = $('#incidenciaTecnica_contacteEmail_rowid');
            var contacteTelefon = $('#incidenciaTecnica_contacteTelefon_rowid');
            var titol = $('#incidenciaTecnica_titol_rowid');
            var descripcio = $('#incidenciaTecnica_descripcio_rowid');
            
            // Moverlos al inicio en orden inverso (prepend los pone arriba)
            if (contacteTelefon.length > 0) tbody.prepend(contacteTelefon[0]);
            if (contacteEmail.length > 0) tbody.prepend(contacteEmail[0]);
            if (nomEntitat.length > 0) tbody.prepend(nomEntitat[0]);
            if (descripcio.length > 0) tbody.prepend(descripcio[0]);
            if (titol.length > 0) tbody.prepend(titol[0]);
            if (organid.length > 0) tbody.prepend(organid[0]);
            if (contacteNom.length > 0) tbody.prepend(contacteNom[0]);
        }
    });
</script>

</c:if>

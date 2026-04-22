

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


	<c:if test="${desplegableOrgans == 'true'}">
		<!-- Incluye jQuery desde un CDN -->
		<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	Incluye jQuery UI desde un CDN
	<link rel="stylesheet"
		href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 -->
		<!--   
    
    //1. Coger todos los datos
    var select = document.getElementById("incidenciaTecnica_organid")

    var organs = [];
    for(let i = 0; i< select.children.length; i++){
      var organ = {
        key : select.children[i].value,
        val : select.children[i].innerHTML
      }
       organs.push(organ);
    }

    //2. Cambiar Select por input text
    
    //3. oninput: filtrar i mostrar datos en ul li

 -->
		<script>
			document
					.addEventListener(
							"DOMContentLoaded",
							function() {
								// Obtener el select existente          incidenciaTecnica_organid
								const select = document
										.getElementById("incidenciaTecnica_organid");

								// Crear un campo de entrada de texto
								const input = document.createElement("input");
								input.type = "search";
								input.id = "autocomplete-input";
								input.className = "w-100 form-control";
								input.placeholder = "Escribe para buscar";
								input.autocomplete = "off";
								input.name = "incidenciaTecnica.organid";

								//Asigna valor actual (per si es edici� i no creaci�)
								const selected = select.selectedOptions[0];
								if (selected.value.length > 0) {
									input.value = selected.innerHTML;
								} else {
									input.value = "";
								}

								// Crear un div para el desplegable de opciones
								const dropdown = document.createElement("div");
								dropdown.id = "autocomplete-dropdown";
								dropdown.className = "autocomplete-dropdown";
								dropdown.style.zIndex = 5;

								// Agregar el campo de entrada de texto y el desplegable despu�s del select
								select.parentNode.insertBefore(dropdown,
										select.nextSibling);
								select.parentNode.insertBefore(input,
										select.nextSibling);

								// Ocultar el select original
								select.style.display = "none";

								// Manejar eventos de entrada en el campo de entrada de texto
								input
										.addEventListener(
												"input",
												function() {
													const value = input.value
															.trim()
															.toLowerCase();
													const options = select.options;

													// Limpiar el desplegable
													dropdown.innerHTML = "";

													// Mostrar todas las opciones si el campo de entrada est� vac�o
													if (value === "") {
														for (let i = 0; i < options.length; i++) {
															dropdown
																	.appendChild(createDropdownOption(options[i]));
														}
													} else {
														// Filtrar y mostrar las opciones que coincidan con el valor ingresado
														for (let i = 0; i < options.length; i++) {
															const optionText = options[i].textContent
																	.toLowerCase();
															if (optionText
																	.includes(value)) {
																dropdown
																		.appendChild(createDropdownOption(options[i]));
															}
														}
													}

													// Mostrar u ocultar el desplegable seg�n las opciones disponibles
													if (dropdown.children.length > 0) {
														dropdown.style.display = "block";
													} else {
														dropdown.style.display = "none";
													}
												});

								// Controlador de clic en el desplegable para seleccionar la opci�n
								dropdown
										.addEventListener(
												"click",
												function(e) {
													if (e.target
															&& e.target.tagName === "DIV") {
														const optionText = e.target.textContent;
														input.value = optionText;
														select.value = e.target.value;
														dropdown.style.display = "none";
														select
																.dispatchEvent(new Event(
																		"change")); // Disparar evento "change" en el select
													}
												});

								// Funci�n para crear una opci�n en el desplegable
								function createDropdownOption(option) {
									const div = document.createElement("div");
									div.textContent = option.textContent;
									div.className = "autocomplete-dropdown-item";
									div.value = option.value;
									return div;
								}

								$(document)
										.click(
												function(event) {
													const $target = $(event.target);

													if (!$target.is(input)
															&& $target
																	.closest("#autocomplete-dropdown").length === 0) {

														const selected = select.selectedOptions[0];
														if (selected.value.length > 0) {
															input.value = selected.innerHTML;
														} else {
															input.value = "";
														}
														dropdown.style.display = "none";
													}
												});

								/* 	    input.addEventListener("focusout", function(e) {
								 const selected = select.selectedOptions[0];
								 if (selected.value.length > 0) {
								 console.log("lostfocus: " + selected.value + " - " + selected.innerHTML );
								 input.value = selected.innerHTML;
								 dropdown.dispatchEvent(new Event("click")); // Disparar evento "change" en el select
								 }
								 });
								 */

							});
		</script>

		<style>
.autocomplete-container {
	position: relative;
}

.autocomplete-input {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	position: relative;
}

.autocomplete-dropdown {
	display: none;
	position: absolute;
	z-index: 1;
	border: 1px solid #ccc;
	border-top: none;
	max-height: 170px;
	overflow-y: auto;
	width: 100%;
	background-color: white;
	max-width: 40rem;
}

.autocomplete-dropdown-item {
	padding: 5px;
	cursor: pointer;
}

.autocomplete-dropdown-item:hover {
	background-color: #f0f0f0;
}

.autocomplete-dropdown.active {
	display: block;
}
</style>

	</c:if>



	<!-- AFEGIR DESPLEGABLE ENTITAT DE L'USUARI  -->
	
<!-- Select generado con JSTL y oculto inicialmente -->
<!-- <select id="incidenciaTecnica_entitatid" name="incidenciaTecnica.entitatid" class="w-100 form-control" style="display: none;">
    <option value="">Selecciona una opci�</option>
    <c:forEach var="entitat" items="${entitats}">
        <option value="${entitat.key}">${entitat.value}</option>
    </c:forEach>
</select> -->

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var tbody = document.querySelector("#incidenciaTecnica_tableid tbody");
        var selectTemplate = document.querySelector("#incidenciaTecnica_entitatid");

        if (!tbody || !selectTemplate) return;

        // Crear la fila y celdas
        var tr = document.createElement("tr");
        tr.id = "incidenciaTecnica_entitatid_rowid";

        var tdLabel = document.createElement("td");
        tdLabel.id = "incidenciaTecnica_entitatid_columnlabelid";
        tdLabel.innerHTML = '<label>Entitat (*)</label>';

        var tdSelect = document.createElement("td");
        tdSelect.id = "incidenciaTecnica_entitatid_columnvalueid";

        // Clonar y mostrar el select
        var selectClone = selectTemplate.cloneNode(true);
        selectClone.style.display = "block";

        // Insertar el select en la celda y a�adir la fila a la tabla
        tdSelect.appendChild(selectClone);
        tr.appendChild(tdLabel);
        tr.appendChild(tdSelect);
        tbody.appendChild(tr);
        
        //Controlar onsubmit del form. Si nohay valor seleccionado, no enviar el form.
        
        var form = document.querySelector("form");
        form.addEventListener("submit", function(event){
            var select = document.getElementById("incidenciaTecnica_entitatid");
            if(select.value === ""){
                alert("Selecciona una entitat");
                event.preventDefault();
            }
        });
        
    });
</script>

<!-- Script para mover el botón Guardar arriba y cambiar texto a Continuar -->
<script>
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
            
            // Copiar el evento submit del botón original
            continuarButton.on('click', function() {
                saveButton.click();
            });
        }
    });
</script>

</c:if>



<c:if test="${isPinfo == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

	<style>
form {
	padding: 2rem;
	border-radius: 6px;
	border: 2px solid black;
	margin: 2rem 7rem;
}

#incidenciaTecnica_tableid {
	width: 50rem;
	margin: auto;
}

td {
	vertical-align: middle !important;
}

label {
	padding-left: 1rem;
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

								//Asigna valor actual (per si es edició i no creació)
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

								// Agregar el campo de entrada de texto y el desplegable después del select
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

													// Mostrar todas las opciones si el campo de entrada está vacío
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

													// Mostrar u ocultar el desplegable según las opciones disponibles
													if (dropdown.children.length > 0) {
														dropdown.style.display = "block";
													} else {
														dropdown.style.display = "none";
													}
												});

								// Controlador de clic en el desplegable para seleccionar la opción
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

								// Función para crear una opción en el desplegable
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
<select id="incidenciaTecnica_entitatid" name="incidenciaTecnica.entitatid" class="w-100 form-control" style="display: none;">
    <option value="">Selecciona una opció</option>
    <c:forEach var="entitat" items="${entitats}">
        <option value="${entitat.key}">${entitat.value}</option>
    </c:forEach>
</select>

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

        // Insertar el select en la celda y añadir la fila a la tabla
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

</c:if>

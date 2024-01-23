<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:forEach var="entry" items="${divServeis}">
	<div id="modal_infoservei_${entry.key}" class="modal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document" style="max-width: 45%;">
			<div class="modal-content" style="width: fit-content;">
				<div class="modal-header">
					<h4 id="myModalLabel">
						<fmt:message key="servei.llistat" />
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b><fmt:message key="solicitud.notes" />:
									&nbsp;</b></td>
							<td>${notesSolicitud[entry.key]}</td>
						</tr>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b> <fmt:message
										key="solicitud.codiDescriptiu" />:&nbsp;
							</b></td>
							<td>${codiDescriptiuSolicitud[entry.key]}</td>
						</tr>
					</table>
					${entry.value}
				</div>
			</div>
		</div>
	</div>
</c:forEach>



<style>
.pOrganOpen {
	max-width: fit-content;
}

.pOrganClose {
	max-width: fit-content;
}

.spanOrganOpen {
	font-weight: normal;
}

.spanOrganClose {
	display: none;
}
</style>

<script>
	var organs = $(".elemOrgan");
	for (var i = 0; i < organs.length; i++) {
		var pa = organs[i];
		pa.parentElement.style.width = "30%";
		pa.parentElement.onclick = pa.onclick;
		pa.onclick = null;
	}

	function toggleJerarquia(elem) {

		console.log(elem);
		var myP = elem.getElementsByTagName("p")[0];
		console.log(myP);
		var mySpan = elem.getElementsByTagName("span")[0];
		console.log(mySpan);

		$(myP).toggleClass("pOrganOpen");
		$(myP).toggleClass("pOrganClose");

		$(mySpan).toggleClass("spanOrganOpen");
		$(mySpan).toggleClass("spanOrganClose");
	}
</script>

<c:if test="${desplegableOrgans == 'true'}">

<div class="modal fade" style="display: none" id="jerarquiaModal"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="titolModal">Filtre d'Organs</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div>
					<p>Pot escriure aqui i filtrar per l'organ que vulgui</p>
				</div>
				<div>

					<select id="solicitud_organid" name="solicitud.organid"
						class="form-control col-md-9-optional" style="display: none;">
                        <option value=""></option>
						<c:forEach var="organ" items="${organs}">
						
							<option ${organ.organid == organSelected ? 'selected' : '' } value="${organ.organid}">(${organ.dir3}) ${organ.nom}</option>
						</c:forEach>

					</select> 
					<div id="botons-modal">
						<div id="submit-filtre-organ" class="btn btn-sm btn-primary float-right botoselecciolist">Cercar</div>
						<div id="netejar-filtre-organ" class="btn btn-sm btn-warning float-right botoselecciolist">Netejar</div>
                    </div>
				</div>
			</div>
		</div>
	</div>



	<script>
		$("#submit-filtre-organ").on("click", function() {
			submit();
		});

		$("#netejar-filtre-organ").on("click", function() {
			$("#myInputOrganID").val("");
			$("#solicitud_organid").val("");
		});

		
		
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200) {
	            var optionsTxt = this.responseText;
	            const select = document.getElementById("solicitud_organid");
 	            select.innerHTML = optionsTxt;
 	            
 	           /*
	            //	            var options = optionsTxt.split('|');
	            for (var j = 0; j < options.length; j++) {
	            	select.append(options[j]);
	            }
 */	            createSelectOption();
	         }
	    };

	    var base = '<%=request.getContextPath()%>${contexte}/getAllOrgansGestors';
	    xhttp.open("GET", base, true);

	    xhttp.setRequestHeader("Content-type", "application/json");
	    

		document.addEventListener("DOMContentLoaded", function() {
			 createSelectOption();
/* 		    xhttp.send(''); */
		});

		function createSelectOption() {
			const select = document.getElementById("solicitud_organid");

			// Crear un campo de entrada de texto
			const input = document.createElement("input");
			input.type = "search";
			input.id = "myInputOrganID";
			input.className = "w-100 form-control";
			input.placeholder = "Escribe para buscar";
			input.autocomplete = "off";
			input.name = "solicitud.organid";

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
			select.parentNode.insertBefore(dropdown, select.nextSibling);
			select.parentNode.insertBefore(input, select.nextSibling);

			// Ocultar el select original
			select.style.display = "none";

			// Manejar eventos de entrada en el campo de entrada de texto
			input
					.addEventListener(
							"input",
							function() {
								const value = input.value.trim().toLowerCase();
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
										if (optionText.includes(value)) {
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
			dropdown.addEventListener("click", function(e) {
				if (e.target && e.target.tagName === "DIV") {
					const optionText = e.target.textContent;
					input.value = optionText;
					select.value = e.target.value;
					dropdown.style.display = "none";
					select.dispatchEvent(new Event("change")); // Disparar evento "change" en el select
				}
			});

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

			// Función para crear una opción en el desplegable
			function createDropdownOption(option) {
				const div = document.createElement("div");
				div.textContent = option.textContent;
				div.className = "autocomplete-dropdown-item";
				div.value = option.value;
				return div;
			}
		}

		function openFiltreOrgans() {
			$("#jerarquiaModal").modal();
		}

		function submit() {
			document.getElementById("solicitudFilterForm").submit();
		}
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

#botons-modal div {
	text-align: right;
	margin-top: 1rem;
}
</style>
</c:if>

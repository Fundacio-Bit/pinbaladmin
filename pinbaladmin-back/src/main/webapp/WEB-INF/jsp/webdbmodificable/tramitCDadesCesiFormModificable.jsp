
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.TramitCPublicController"%>
<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>
</c:if>

<!-- CERCADOR AUTOCOMPLETAR ORGAN GESTOR -->

<input id="organ" type="text" autocomplete="off"
	class="w-100 form-control" placeholder="Minim 2 caracters...">
<div id="autocomplete-organ"></div>
<input id="entitat" type="text" autocomplete="off"
	class="w-100 form-control">



<script>
	$(document).ready(function() {
		
		var $organGestor = document.getElementById("tramitCDadesCesi.organID"); 
		$organGestor.style.display = "none";
		
		var $organArrel = document.getElementById("tramitCDadesCesi.organArrelID");
		$organArrel.style.display = "none";
		
		var $organ = document.getElementById("organ");
		var $dir3respo = document.getElementById("tramitCDadesCesi.dir3responsable");
		var $entitat = document.getElementById("entitat");
		$entitat.readOnly = "readOnly";

		$("#tramitCDadesCesi_organID_columnvalueid").append($($organ));
		$("#tramitCDadesCesi_organID_columnvalueid").append($("#autocomplete-organ"));
		
		$("#tramitCDadesCesi_organArrelID_columnvalueid").append($($entitat));

		$("#organ").on("input", function() {
			var organ = $(this).val();
			console.log(organ);
			if (organ.length < 2) {
				$("#autocomplete-organ").empty();
				return;
			}
			$.ajax({
				url : "<%= request.getContextPath() + TramitCPublicController.CONTEXT_WEB %>/jsonOrganList",
				type : "GET",
				data : {
					query : organ
				},
				success : function(data) {
					$("#autocomplete-organ").empty();
					data.forEach(function(organ) {
						afegirOrgan(organ);
					});
				}
			});
		});

		function afegirOrgan(organ) {
			var organDiv = document.createElement("div");
			organDiv.classList.add("organ-item");
			organDiv.innerHTML = organ.nom;
			organDiv.onclick = function() {
				elegirOrgan(organ.id);
			};

			$("#autocomplete-organ").append(organDiv);
		}
		
		function elegirOrgan(organid) {
			
			$.ajax({
				url : "<%= request.getContextPath() + TramitCPublicController.CONTEXT_WEB %>/jsonOrganGestor",
				type : "GET",
				data : {
					organid: organid 
				},
				success : function(data) {
					console.log(data);

					let cadena = "(" + data.dir3 + ") " + data.nom;
					$organGestor.value = data.id;
					$organ.value = cadena;
					
					let cadenaEntitat = "(" + data.entitatCif + ") " + data.entitatNom;
					$organArrel.value = data.entitatID;

					$entitat.readOnly = "";
					$entitat.value = cadenaEntitat;
					$entitat.readOnly = "readOnly";
					
					$("#autocomplete-organ").empty();
				}
			});
		}
		
		testOrgan();
		function testOrgan(){
			var v = $organGestor.value;
			if (v != "") {
				elegirOrgan(v)
            }else {
				$organ.value = "";
			}
		}
 
		//Fer que quan es faci focusout de l'input, es buidi el desplegable, pero si es fa click a sobre del desplegable, faci lo que toca
		$(document).click(function(event) {
            const $target = $(event.target);
            if (!$target.is($organ) && $target.closest("#autocomplete-organ").length === 0) {
                $("#autocomplete-organ").empty();
	            testOrgan();
            }
        });
	});
</script>


<style>
.organ-item {
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.organ-item:hover {
	background-color: #f1f1f1;
}

#autocomplete-organ{
	display: block;
	position: absolute;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 170px;
	overflow-y: auto;
}
</style>



<%-- 
<script type="text/javascript">
	var selectElement = document
			.querySelector('[name="tramitCDadesCesi.denominacio"]');
	var nif = document.querySelector('[name="tramitCDadesCesi.nif"]');
	nif.readOnly = "readOnly";

	selectElement.addEventListener('change', function() {
		nif.readOnly = "";
		var selectedValue = selectElement.value;
		nif.value = selectedValue;
		nif.readOnly = "readOnly";
	});
</script>

<c:if test="${desplegableOrgans == 'true'}">
	<script>
	document.addEventListener("DOMContentLoaded", function() {
	    // Obtener el select existente          solicitud_organid
	    const select = document.getElementById("tramitCDadesCesi_organID");

	    // Crear un campo de entrada de texto
	    const input = document.createElement("input");
	    input.type = "search";
	    input.id = "autocomplete-input";
	    input.className = "w-100 form-control";
	    input.placeholder = "Escribe para buscar";
	    input.autocomplete = "off";
	    input.name = "solicitud.organid";
	    
        //Asigna valor actual (per si es edició i no creació)
        const selected = select.selectedOptions[0];
	    if (selected.value.length > 0) {
            input.value = selected.innerHTML;
        }else{
           input.value = "";
        }

	    // Crear un div para el desplegable de opciones
	    const dropdown = document.createElement("div");
	    dropdown.id = "autocomplete-dropdown";
	    dropdown.className = "autocomplete-dropdown";
	    dropdown.style.zIndex = 5;
	    dropdown.style.width = "25rem";
	    
	    
	    // Agregar el campo de entrada de texto y el desplegable después del select
	    select.parentNode.insertBefore(dropdown, select.nextSibling);
	    select.parentNode.insertBefore(input, select.nextSibling);
	    
        // Ocultar el select original
        select.style.display = "none";

	    // Manejar eventos de entrada en el campo de entrada de texto
	    input.addEventListener("input", function() {
	        const value = input.value.trim().toLowerCase();
	        const options = select.options;

	        // Limpiar el desplegable
	        dropdown.innerHTML = "";

	        // Mostrar todas las opciones si el campo de entrada está vacío
	        if (value === "") {
	            for (let i = 0; i < options.length; i++) {
	                dropdown.appendChild(createDropdownOption(options[i]));
	            }
	        } else {
	            // Filtrar y mostrar las opciones que coincidan con el valor ingresado
	            for (let i = 0; i < options.length; i++) {
	                const optionText = options[i].textContent.toLowerCase();
	                if (optionText.includes(value)) {
	                    dropdown.appendChild(createDropdownOption(options[i]));
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
	            select.value =  e.target.value;
	            console.log("Buenas: " + e.target.value);
	            setValueToOrganArrel(e);
	            dropdown.style.display = "none";
	            select.dispatchEvent(new Event("change")); // Disparar evento "change" en el select
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
	    
	    
	    $(document).click(function(event) {
	        const $target = $(event.target);

	        if (!$target.is(input) && $target.closest("#autocomplete-dropdown").length === 0) {
	            
	        	const selected = select.selectedOptions[0];
	            if (selected.value.length > 0) {
	                 input.value = selected.innerHTML;
	            }else{
	            	input.value = "";
	            }
                dropdown.style.display = "none";
	        }
	    });
	    
	    const inputArrel = document.createElement("input");
	    inputArrel.id = "organidarrel_input";
	    inputArrel.name = "tramitCDadesCesi.organArrelID";
	    inputArrel.className = "w-100 form-control";

	    const selectArrel = document.getElementById("tramitCDadesCesi_organArrelID");
		const selectedArrel = selectArrel.selectedOptions[0];
	    if (selectedArrel.value.length > 0) {
	    	inputArrel.value = selectedArrel.innerHTML;
        }else{
        	inputArrel.value = "";
        }

	    selectArrel.parentNode.insertBefore(inputArrel, selectArrel.nextSibling);
	    selectArrel.style.display = "none";
			
	    var organArrel = document.getElementById('organidarrel_input');
		organArrel.readOnly = "readOnly";
		
		//Asignar valor al select, y recuperra texto de la opción seleccionada
		function setValueToOrganArrel(evt) {
			const value = evt.target.value;
			selectArrel.value = value;
			var selectedArrel = selectArrel.selectedOptions[0];
	
        	organArrel.readOnly = "";
			const text = selectedArrel.innerHTML;
			organArrel.value = text;
			organArrel.readOnly = "readOnly";
		}
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

<script type="text/javascript">

//On ready:
	$(document).ready(function() {
		var w2 = $("#wizard").width();
		var w1 = $(".tab_container").width();
		$("#anotacions").width(w1 + w2 - 16);
	});
</script> --%>

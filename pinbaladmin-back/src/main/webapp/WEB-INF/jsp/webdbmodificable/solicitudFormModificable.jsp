<script>
	$(".fa-bullhorn").parent().insertBefore($("#solicitudForm").prev());
	$(".fa-bullhorn").parent().css("float", "right");
	
    $(".btn-api-pinbal").insertBefore($("#solicitudForm").prev());
    $(".btn-api-pinbal").css("float", "right");
    $(".btn-api-pinbal").css("margin-right", "1rem");

    $(".btn-correu-cedents").insertBefore($("#solicitudForm").prev());
    $(".btn-correu-cedents").css("float", "right");
    $(".btn-correu-cedents").css("margin-right", "1rem");

</script>

<!-- Canvi de tamany de les columnes de la vista d'una sol·licitud -->
<script>
	var tabla = document.getElementById("solicitud_tableid");

	var trs = tabla.children[0].children;

	for (let i = 0; i < trs.length; i++) {
		var tds = trs[i].children;
		tds[0].style.width = "30%";
		//  tds[1].style.width = "80%";
	}
</script>

<!-- Canviar visualitzaci� de la jerarquia -->

<c:if test="${isView == 'true'}">
	<script>
		var inputElement = document.getElementById('solicitud.organid').nextSibling;
		var inputValue = inputElement.value;

		// Dividir el texto en l�neas
		var lineas = inputValue.split('|');

		console.log(inputValue);
		console.log(lineas);
		// Crear un nuevo texto con guiones por jerarquia
		var textoConGuiones = '';
		var nivel = 0;
		for (var i = 0; i < lineas.length; i++) {
			var guionesLinea = "";
			if (i != 0) {
				guionesLinea = "    " + " ".repeat(12).repeat(nivel - 1)
						+ String.fromCharCode(9492)
						+ String.fromCharCode(9472).repeat(2) + " ";
			}

			textoConGuiones += guionesLinea + lineas[lineas.length - 1 - i]
					+ '\n';
			nivel++;
		}

		var textareaElement = document.createElement('textarea');
		textareaElement.value = textoConGuiones;
		textareaElement.id = 'miTextarea';
		textareaElement.readOnly = true;
		textareaElement.classList = inputElement.classList;
		//   textareaElement.style.fontFamily = "Courier";
		textareaElement.rows = lineas.length;

		// Reemplazar el input con el textarea
		inputElement.parentNode.replaceChild(textareaElement, inputElement);
	</script>
</c:if>


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
    var select = document.getElementById("solicitud_organid")

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
	document.addEventListener("DOMContentLoaded", function() {
	    // Obtener el select existente          solicitud_organid
	    const select = document.getElementById("solicitud_organid");

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




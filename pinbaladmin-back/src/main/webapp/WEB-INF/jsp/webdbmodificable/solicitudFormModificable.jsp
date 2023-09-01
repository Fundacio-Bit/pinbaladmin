<script>
	$(".fa-bullhorn").parent().insertBefore($("#solicitudForm").prev());
	$(".fa-bullhorn").parent().css("float", "right");
</script>


<!-- Canviar visualització de la jerarquia -->

<script>
	// Obtener el elemento de entrada (input) y su valor
	var inputElement = document.getElementById('solicitud.organid').nextSibling;
	var inputValue = inputElement.value;

	// Dividir el texto en líneas
	var lineas = inputValue.split(' - ');

    console.log(inputValue);
	console.log(lineas);
	// Crear un nuevo texto con guiones por jerarquía
	var textoConGuiones = '';
	var nivel = 0;
	for (var i = 0; i < lineas.length; i++) {
		var guionesLinea = '+' + '-'.repeat(2*nivel);
		textoConGuiones += guionesLinea + ' ' + lineas[i] + '\n';
		nivel++;
	}

	// Crear un nuevo elemento textarea
	var textareaElement = document.createElement('textarea');

	// Configurar el valor y otros atributos del textarea
	textareaElement.value = textoConGuiones;
	textareaElement.id = 'miTextarea'; // Puedes establecer cualquier ID deseado
	textareaElement.readOnly = true; // Hacer el textarea de solo lectura
	textareaElement.classList = inputElement.classList;
    textareaElement.rows = lineas.length;

	// Reemplazar el input con el textarea
	inputElement.parentNode.replaceChild(textareaElement, inputElement);
</script>
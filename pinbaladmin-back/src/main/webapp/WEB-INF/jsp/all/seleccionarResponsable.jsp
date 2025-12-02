<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Seleccionar responsable</title>

<style>
.container {
	padding: 1rem;
	border-radius: 6px;
	border: 2px solid black;
}

label {
	font-size: 1rem;
	margin: 0.25rem;
}

#backToList-button-container {
	text-align: right;
	margin: 1rem 5rem;
}

#responsables-list {
  height: 15rem;
}

.header-section {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1.5rem;
}

.header-section h3 {
	margin: 0;
	flex: 1;
}

#btn-container {
	text-align: right;
	color: white;
	white-space: nowrap;
	margin-left: 1rem;
}

#btn-container .btn {
	margin: 0;
}

#seleccionarResponsable {
  margin: 1rem;
}

.filter-container {
	padding-top: 0;
  	padding-left: 1rem;
	margin-bottom: 1rem;
}

.responsable-item {
	transition: opacity 0.2s ease;
	margin-bottom: 0.5rem;
}

.responsable-item:hidden {
	display: none;
}

</style>

</head>
<body>
	<div id="backToList-button-container">
		<a id="backToList-button" href="list/1" class="btn btn-secondary">Tornar
			al llistat</a>
	</div>

	<div class="container">
		<div class="header-section">
			<h3>Seleccionar Responsable</h3>
			<div id="btn-container">
				<a class="btn btn-sm btn-primary"
					onclick="submitForm();"
					title="<fmt:message key="generar.pdf"/>"> <i
					class="fas fa-file-pdf"></i> <fmt:message key="generar.pdf" />
				</a>
			</div>
		</div>

		<div class="filter-container" style="margin-bottom: 1rem;">
			<input type="text" id="filtro-responsables" placeholder="Escriba al menos 2 caracteres para filtrar por NIF, nombre, cargo, teléfono o email..." 
				   style="width: 100%; padding: 0.5rem; margin-top: 0.25rem; border: 1px solid #ccc; border-radius: 4px;" />
		</div>

		<form id="seleccionarResponsable" action="seleccionarResponsable"
			method="POST">
			<div id="responsables-list">
				<c:forEach var="responsable" items="${responsables}">
					<div class="element responsable-item">
						<input type="radio" name="responsable"
							id="responsable-${responsable.nif}" value="${responsable.nif}" />
						<label for="responsable-${responsable.nif}">${responsable.nif}
							- ${responsable.nomOcult} - ${responsable.cargo} -
							${responsable.telefon} - ${responsable.mail} </label>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>

	<script>
	
	$(document).ready(function(){
		// Mover el responsable seleccionado al principio
		var selectedItem = null;
		$('.responsable-item').each(function(){
			var input = $(this).find('input[type="radio"]');
			if (input.is(':checked')) {
				selectedItem = this;
				$(this).prependTo('#responsables-list');
			}
		});
		
		// Inicialmente ocultar todos los responsables EXCEPTO el que esté seleccionado
		$('.responsable-item').each(function(){
			var input = $(this).find('input[type="radio"]');
			if (!input.is(':checked')) {
				$(this).hide();
			}
		});
		
		// Funcionalidad de filtrado de responsables
		$('#filtro-responsables').on('input', function(){
			var filtro = $(this).val().toLowerCase();
			var selectedItem = null;
			var count = 0;
			
			// Encontrar el elemento seleccionado
			$('.responsable-item').each(function(){
				var input = $(this).find('input[type="radio"]');
				if (input.is(':checked')) {
					selectedItem = this;
				}
			});
			
			// Solo mostrar elementos si hay al menos 2 caracteres
			if (filtro.length >= 2) {
				$('.responsable-item').each(function(){
					var input = $(this).find('input[type="radio"]');
					var texto = $(this).find('label').text().toLowerCase();
					
					// Si está seleccionado, siempre mostrar
					if (input.is(':checked')) {
						$(this).show();
						$(this).prependTo('#responsables-list');
					} else if (texto.includes(filtro)) {
						if (count < 5) { // Máximo 5 resultados además del seleccionado
							$(this).show();
							count++;
						} else {
							$(this).hide();
						}
					} else {
						$(this).hide();
					}
				});
			} else {
				// Ocultar todos los elementos EXCEPTO el que esté seleccionado
				$('.responsable-item').each(function(){
					var input = $(this).find('input[type="radio"]');
					if (input.is(':checked')) {
						$(this).show();
						$(this).prependTo('#responsables-list');
					} else {
						$(this).hide();
					}
				});
			}
		});
	});
	
	function submitForm() {
		console.log('submitForm');
		var responsables = document.getElementsByName("responsable");
		var seleccionado = false;

		for (var i = 0; i < responsables.length; i++) {
			if (responsables[i].checked) {
				seleccionado = true;
				break;
			}
		}

		if (!seleccionado) {
			alert('Debe seleccionar un responsable antes de enviar el formulario.');
		} else {
			document.getElementById('seleccionarResponsable').submit();
		}
	}
	</script>
</body>
</html>
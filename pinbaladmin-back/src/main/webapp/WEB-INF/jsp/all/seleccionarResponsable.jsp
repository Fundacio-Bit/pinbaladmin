<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>


<html>
<head>
<meta charset="ISO-8859-1">
<title>Seleccionar responsable</title>

<style>
/* Contenedor principal */
.container-responsable {
	max-width: 900px;
	margin: 2rem auto;
	padding: 2rem;
	border-radius: 12px;
	background: white;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
	border: none;
}

/* Headers usan clases estándar de tramitPinfoPublic.jsp */

/* Botón principal - Generar PDF */
.btn-primary-custom {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%) !important;
	color: white !important;
	padding: 10px 24px;
	border: none !important;
	border-radius: 8px;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.2s;
	text-decoration: none !important;
	display: inline-flex;
	align-items: center;
	gap: 8px;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.2) !important;
}

.btn-primary-custom:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%) !important;
	color: white !important;
	text-decoration: none !important;
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3) !important;
	transform: translateY(-1px);
}

/* Campo de búsqueda */
.filter-container {
	margin-bottom: 1.5rem;
}

.filter-container label {
	font-size: 14px;
	font-weight: 600;
	color: #2d3748;
	margin-bottom: 0.5rem;
	display: block;
}

.filter-container label i {
	color: #4DBA79;
	margin-right: 6px;
}

.filter-container input {
	width: 100%;
	padding: 12px 16px;
	border: 1px solid #ddd;
	border-radius: 8px;
	font-size: 14px;
	transition: all 0.2s;
	box-sizing: border-box;
}

.filter-container input:focus {
	border-color: #4DBA79;
	outline: none;
	box-shadow: 0 0 0 3px rgba(77, 186, 121, 0.1);
}

/* Lista de responsables - altura fija */
#responsables-list {
	height: 400px;
	min-height: 400px;
	max-height: 400px;
	overflow-y: auto;
	padding-right: 8px;
}

/* Scrollbar personalizado */
#responsables-list::-webkit-scrollbar {
	width: 8px;
}

#responsables-list::-webkit-scrollbar-track {
	background: #f1f1f1;
	border-radius: 4px;
}

#responsables-list::-webkit-scrollbar-thumb {
	background: #c1c1c1;
	border-radius: 4px;
}

#responsables-list::-webkit-scrollbar-thumb:hover {
	background: #a8a8a8;
}

/* Items de responsable */
.responsable-item {
	transition: all 0.2s ease;
	margin-bottom: 8px;
	padding: 12px 16px;
	border-radius: 8px;
	border: 1px solid #e8e8e8;
	background: white;
	cursor: pointer;
	display: flex;
	align-items: center;
	gap: 12px;
}

.responsable-item:hover {
	background-color: #f8f9fa;
	border-color: #4DBA79;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.1);
}

/* Radio button - estilo nativo con acento verde */
.responsable-item input[type="radio"] {
	cursor: pointer;
	margin: 0;
	accent-color: #4DBA79;
	width: 16px;
	height: 16px;
	min-width: 16px;
}

/* Label del responsable */
.responsable-item label {
	cursor: pointer;
	margin: 0;
	font-size: 14px;
	color: #4a5568;
	flex: 1;
	line-height: 1.5;
}

.responsable-item input[type="radio"]:checked + label {
	font-weight: 600;
	color: #2d3748;
}

.responsable-item:hidden {
	display: none;
}

#seleccionarResponsable {
	margin: 0;
}
</style>

</head>
<body>
	<div class="container-responsable">
		<div class="titol-tramit-pinfo-header">
			<div class="titol-tramit-pinfo-container">
				<h3 class="titol-tramit-pinfo"><i class="fas fa-user-check"></i> <fmt:message key="tramit.pinfo.responsable.seleccionar"/></h3>
			</div>
			<div class="titol-tramit-pinfo-botonera">
				<a class="btn-primary-custom"
					onclick="submitForm();"
					title="<fmt:message key="generar.pdf"/>">
					<i class="fas fa-file-pdf"></i> <fmt:message key="generar.pdf" />
				</a>
			</div>
		</div>

		<!-- Avís sobre el responsable -->
		<div class="aviso-info">
			<h5><i class="fas fa-question-circle"></i> Informació sobre la signatura</h5>
			<p style="margin-bottom: 0;">
				<fmt:message key="tramit.pinfo.responsable.ajuda"/>
			</p>
		</div>

		<div class="filter-container">
			<label for="filtro-responsables">
				<i class="fas fa-search"></i> <fmt:message key="tramit.pinfo.responsable.buscar"/>
			</label>
			<input type="text" id="filtro-responsables" placeholder="<fmt:message key="tramit.pinfo.responsable.placeholder"/>" />
		</div>

		<form id="seleccionarResponsable" action="seleccionarResponsable"
			method="POST">
			<div id="responsables-list">
				<c:forEach var="responsable" items="${responsables}">
					<div class="element responsable-item" data-nif="${responsable.nif}">
						<input type="radio" name="responsable"
							id="responsable-${responsable.nif}" value="${responsable.nif}" />
						<label for="responsable-${responsable.nif}">
							${responsable.nif} - ${responsable.nomOcult} - ${responsable.username} - ${responsable.mail}
							
						</label>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>

	<script>
	
	// Función para ofuscar NIF: 45186147W -> 45****47W
	function ofuscarNIF(nif) {
		if (!nif || nif.length < 4) return nif;
		var nifUpper = nif.toUpperCase();
		var inicio = nifUpper.substring(0, 2);
		var final = nifUpper.substring(nifUpper.length - 3);
		return inicio + "****" + final;
	}
	
	$(document).ready(function(){
		// Ofuscar NIFs al cargar la página
		$('.responsable-item').each(function() {
			var nif = $(this).data('nif');
			if (nif) {
				var label = $(this).find('label');
				var text = label.text();
				var nifOfuscat = ofuscarNIF(nif);
				var newText = text.replace(nif, nifOfuscat);
				label.text(newText);
			}
		});
		
		// Hacer todo el div clickeable para seleccionar el radio
		$('.responsable-item').on('click', function(e){
			// Solo si no se ha clickeado directamente en el radio o label
			if (!$(e.target).is('input[type="radio"]') && !$(e.target).is('label')) {
				var radio = $(this).find('input[type="radio"]');
				radio.prop('checked', true);
			}
		});
		
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
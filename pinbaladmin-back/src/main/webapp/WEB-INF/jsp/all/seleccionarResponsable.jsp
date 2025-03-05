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

#footer {
	position: fixed;
	left: 0;
	right: 0;
	bottom: 1rem;
}

#backToList-button-container {
	text-align: right;
	margin: 1rem 5rem;
}

#btn-container {
	margin-top: 1rem;
	text-align: right;
	color: white;
}

#seleccionarResponsable {
  margin: 1rem;
}

</style>

</head>
<body>
	<div id="backToList-button-container">
		<a id="backToList-button" href="list/1" class="btn btn-secondary">Tornar
			al llistat</a>
	</div>

	<div class="container">
		<h3>Seleccionar Responsable</h3>

		<form id="seleccionarResponsable" action="seleccionarResponsable"
			method="POST">
			<c:forEach var="responsable" items="${responsables}">
				<div class="element">
					<input type="radio" name="responsable"
						id="responsable-${responsable.nif}" value="${responsable.nif}" />
					<label for="responsable-${responsable.nif}">${responsable.nif}
						- ${responsable.nomOcult} - ${responsable.cargo} -
						${responsable.telefon} - ${responsable.mail} </label>
				</div>
			</c:forEach>

				<div class="element">
					<input type="radio" name="responsable" id="responsable-otro" value="otro"/>
					<label for="responsable-otro">Otro</label><br>
					
					<label for="responsable-otro-dni">DNI</label>
					<input type="text" class="respo-otro" id="responsable-otro-dni" name="responsable-otro-dni" />
					<label for="responsable-otro-nom">Nombre</label>
					<input type="text" class="respo-otro" id="responsable-otro-nom" name="responsable-otro-nom"  />
				</div>
				
			<div id="btn-container">
				<a class="btn btn-sm btn-primary"
					onclick="submitForm();"
					title="<fmt:message key="generar.pdf"/>"> <i
					class="fas fa-file-pdf"></i> <fmt:message key="generar.pdf" />
				</a>
			</div>
		</form>
	</div>

	<script>
			
	
	//on input on class class="respo-otro", select the radio button.
	
	$(document).ready(function(){
		$('.respo-otro').on('input', function(){
	        $('#responsable-otro').prop('checked', true);
		});
	});
	
	function submitForm() {
		console.log('submitForm');
		var responsables = document.getElementsByName("responsable");
		var seleccionado = false;

		for (var i = 0; i < responsables.length; i++) {
			if (responsables[i].checked) {
				if (responsables[i].value === 'otro'){
					var responsableOtroNom = document.getElementById("responsable-otro-nom").value;
					console.log(responsableOtroNom);
					var responsableOtroDni = document.getElementById("responsable-otro-dni").value;
					console.log(responsableOtroDni);
                       if (responsableOtroNom === '' || responsableOtroDni === '') {
                           alert('Debe introducir un responsable antes de enviar el formulario.');
                           break;
                       }else{
//                        alert('Se ha seleccionado el responsable: ' + responsableOtroNom + ' con DNI: ' + responsableOtroDni);
                       }
				}
				
				seleccionado = true;
				break;
			}
		}

		if (!seleccionado) {
			alert('Debe seleccionar un responsable antes de enviar el formulario.');
		}else{
			document.getElementById('seleccionarResponsable').submit();
		}
	}
	</script>
</body>
</html>
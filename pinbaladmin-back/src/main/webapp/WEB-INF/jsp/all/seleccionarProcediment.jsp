<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include
	file="/WEB-INF/jsp/all/tramitModificacioSolicitudsPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar solicitud</title>


<style>



.procediment-item{
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.procediment-item:hover:hover {
	background-color: #f1f1f1;
}

#autocomplete-procediments {
	display: block;
	position: absolute;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 170px;
	overflow-y: auto;
}

#autocomplete-procediments div {
	padding: 6px;
}


#llistat-procediments ul {
	margin-top: 1rem;
}

.procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.procediment-li {
	padding-top: 5px;
	padding-right: 1rem;
	padding-bottom: 5px;
	margin: 2px 0px;
	border: 1px solid white;
}

.procediment-li:hover {
	background-color: #f1f1f1;
	border-bottom-color: black;
	border-top-color: black;
}

.procediment-data-text {
	margin-right: 1rem;
}

.procediment-data-delete {
	cursor: pointer;
	color: #ae0808;
}

#backToList-button-container {
	text-align: right;
	margin: 1rem 5rem;
}

#btn-container {
  text-align: right;
}

</style>

</head>
<body>

	<div class="container">
		<h3>Seleccionar Procediment</h3>

		<form id="seleccionarProcediment" action="seleccionarProcediment"
			method="POST">



			<div class="input-container procediment">
				<div id="cercador-procediments">

					<input id="cercadorProcediment" name="cercadorProcediment" type="text"
						autocomplete="off" class="w-100 form-control"
						placeholder="Procediment. Minim 2 caracters...">

					<div id="autocomplete-procediments"></div>
				</div>
				<div id="llistat-procediments">
					<ul></ul>
				</div>
			</div>

			<div id="btn-container">
				<div class="btn btn-sm btn-primary" onclick="submitForm();"
					title="<fmt:message key="genapp.continue"/>"> <i
					class="fas fa-arrow-right"></i> <fmt:message key="genapp.continue" />
				</div>
			</div>
		</form>


		<script type="text/javascript">
			$("#cercadorProcediment").on("input", function() {
				var procediment = $(this).val();
				console.log(procediment);
				if (procediment.length < 2) {
					$("#autocomplete-procediments").empty();
					return;
				}

				$.ajax({
					url : "jsonProcediments",
					type : "GET",
					data : {
						query : procediment
					},
					success : function(data) {
						$("#autocomplete-procediments").empty();
						data.forEach(function(proc) {
							//Si el procediment ja esta a la llista, no el mostri
							afegirProcediment(proc);
						});
					}
				});

			});

			function afegirProcediment(proc) {
				var procedimentDiv = document.createElement("div");
				procedimentDiv.classList.add("procediment-item");
				procedimentDiv.innerHTML = proc.key + " - " + proc.value;
				procedimentDiv.onclick = function() {
					elegirProcediment(proc);
				};
				$("#autocomplete-procediments").append(procedimentDiv);
			}

			function elegirProcediment(proc) {
				let li = $("<li></li>").addClass("procediment-li");
				let container = $("<div></div>").addClass(
						"procediment-data-container");

				let spanText = $("<span></span>").addClass(
						"procediment-data-text").text(
						proc.key + " - " + proc.value);

				let spanDelete = $("<span></span>").addClass(
						"procediment-data-delete").html(
						'<i class="fas fa-times"></i>').click(function() {
					procediments = procediments.filter(function(p) {
						return p.id != proc.id;
					});
					li.remove();
				});


			    let inputValue = document.createElement("input");
			    inputValue.type = "hidden"; // corregido
			    inputValue.value = proc.id;
			    inputValue.name = "solicitudID";

			    container.append(spanText);
			    container.append(spanDelete);

			    li.append(container);
			    li.append(inputValue); // aquí añades el input oculto

			    $("#llistat-procediments ul").html(li); // cuidado: con esto REEMPLAZAS todo el contenido (¿es lo que quieres?)
			    $("input[name='cercadorProcediment']").val("");
			    $("#autocomplete-procediments").empty();
			}
			

			function submitForm() {
				console.log('submitForm');
				document.getElementById('seleccionarProcediment').submit();
			}
		</script>

	</div>


</body>
</html>
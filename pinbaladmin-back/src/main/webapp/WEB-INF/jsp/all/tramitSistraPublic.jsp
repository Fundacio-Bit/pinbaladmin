<!--  Capçalera i peu -->
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController"%>
<div id="header">
	<div id="logo-caib" class="third">
		<img alt="logo-caib"
			src="https://se.caib.es/sistramitfront/resources/1/O1S9IEZ4-ZJLRBRT8-T8SVCTKJ.png"
			width="50px">
		<div id="user-info">
			<div id="user-name">
				<strong>Usuari: </strong>${tramitA.llinatge1}&nbsp;${tramitA.llinatge2},&nbsp;${tramitA.nom}
			</div>
			<div id="user-nif">
				<strong>DNI: </strong>${tramitA.nif}
			</div>
		</div>
	</div>

	<div id="titol-tramit" class="third">Sol·litud d'autorització</div>
	<div id="button-menu" class="third">
		<a href="<%=request.getContextPath() + TramitAPublicController.CONTEXT_WEB %>/cancelarTramit/${uuid}"><fmt:message key="tramit.sistra.cancelar.tramit"/></a>
	</div>
</div>

<div id="footer">
	<p>© 2024 - Fundació BIT</p>
</div>

<script>
	$(document).ready(function() {
		var body = document.body;
		var header = document.getElementById("header");
	    var footer = document.getElementById("footer");
	    
		body.insertBefore(header, body.firstElementChild);
		body.appendChild(footer);
		
		$(".alert.alert-success").hide();
	});
</script>

<style>
#header {
	padding: 1rem 4rem;
	text-align: center;
	background-color: #4DBA79;
	color: white;
	display: flex;
	flex-direction: row;
	flex-wrap: nowrap;
	justify-content: space-between;
}

#titol-tramit {
	font-size: 35px;
	font-weight: lighter;
}

#logo-caib {
    display: flex;
    gap: 10px;
    align-items: center;
}

#user-info {
	text-align: left;
}

#button-menu {
	display: flex;
	align-items: center;
	justify-content: flex-end;
}

#button-menu a {
	background-color: #265d3c;
	color: white;
	border: none;
	padding: 0.5rem 1rem;
	border-radius: 5px;
	font-weight: lighter;
	text-decoration: none;
}

#button-menu a:hover {
	background-color: #000;
}

.third {
	flex: 1;
}

#footer {
	padding: 1rem;
	text-align: center;
	background-color: #white;
	color: black;
}
</style>

<!--  Formulari -->
<style>
form {
	margin: 1rem 5rem;
	border: 2px solid black;
	padding: 1rem 1.5rem 1rem;
	border-radius: 5px;
	/* width: fit-content; */
}

.module_content {
	display: flex;
}

#form-container {
	max-width: 60rem;
}

form table {
	
}

form .lead {
	text-align: center;
}

form .navbar-form {
	
}

form table td label {
	font-size: 0.9rem;
	margin: 0.5rem;
}

form table td:first-child {
  width: min-content;
}

.tab_container {
	/* 	max-width: 50rem;
	margin-left: 2rem;
	min-width: 35rem;
 */
	flex: 4;
}

#wizard {
	padding: 1rem 0;
	border-radius: 4px;
	display: flex;
	flex-direction: column;
	margin-left: 2rem;
	width: fit-content;
	
	flex: 1;
}

.step-container {
	display: flex;
	align-items: flex-start;
	border-left: 2px solid #dee2e6;
	color: black;
}

.step-container:hover {
	color: black;
	text-decoration: none;
}

.dot {
	z-index: 3;
	height: 10px;
	width: 10px;
	border-radius: 50%;
	transition: all .5s ease-in-out;
	background-color: #dee2e6;
	margin-left: -6px;
}

.step {
	padding: 0rem 1rem 2rem 1rem;
	margin-top: -6px;
}

.created.step-container {
	cursor: pointer;
	border-left-color: #4DBA79;
}

.created .dot {
	background-color: #198fd1;
}

.dot.actual {
	box-shadow: 0 0 0 4px rgba(25, 143, 209, 0.2);
	background-color: #198fd1 !important;
	width: 12px;
	height: 12px;
	margin-left: -7px;
}

.step.actual {
	color: #198fd1;
	font-weight: bold;
}

select.form-control {
	max-width: fit-content;
}

.custom-file label {
	margin: 0;
	margin-right: 1rem;
}

.input-group>.custom-file {
	width: 100%;
	margin-bottom: 4px;
}

#anotacions {
	margin-top: 1rem;
	margin-bottom: 1rem;
	margin-left: 2rem;
	font-style: italic;
	border: 1px solid darkgray;
	border-radius: 5px;
	padding: 6px;
	background-color: #d3d3d336;
	
	width: fit-content;
	margin: 1rem auto;
}

.table td {
	vertical-align: middle;
}

input[type="checkbox"] {
	margin: 0.25rem;
}
</style>


<div id="wizard">
	<c:forEach var="tramit" items="${identificadorsTramit}"
		varStatus="status">

        <c:if test="${status.index != 6}">
			<a id="tramitPart${status.index}"
				class="step-container<c:if test="${not empty tramit}"> created</c:if>"
				<c:if test="${status.last}"> style="border-color: transparent;"</c:if>>

				<div id="dot${status.index}" class="dot"></div>
				<div id="step${status.index}" class="step">
					<fmt:message key="tramit.wizard.step.${status.index}" />
				</div>
			</a>
		</c:if>
	</c:forEach>
</div>

<c:if test="${not empty anotacions && empty llistat}">
	<div id="anotacions"></div>
	<script>
		var anotacions = "<c:out value='${anotacions}' />";
		console.log(anotacions);
		anotacions = anotacions.replaceAll("&lt;br&gt;", '<br>');
		anotacions = anotacions.replaceAll("&amp;emsp;", '&emsp;');
		
		console.log(anotacions);

		$("#anotacions").html("Nota: " + anotacions);
	</script>
</c:if>

<script type="text/javascript">

	$(".module_content").append($("#wizard"));
	$(".module_content").append($(".tab_container"));

	$("#anotacions").insertAfter($(".lead"))

	var w2 = $("#wizard").width();
	var w1 = $(".tab_container").width();
	//$("#anotacions").width(w1 + w2 - 16);

	$("#dot${tramitActual}").addClass("actual");
	$("#step${tramitActual}").addClass("actual");

	var steps = $(".step-container");
	var uuid = "${uuid}";

	var letras = "abcdefhij";

	var lastTramit = 0;
	for (var i = 0; i < steps.length; i++) {
		var letra = letras.charAt(i);

		var step = steps[i];

		var context = "/pinbaladmin/public/tramit" + letra;
		if (step.classList.contains("created")) {
			var url = context + "/edit/" + uuid;
			if (letra == "i") {
				url = context + "/list/1?tramitid=" + uuid;
			}
			step.href = url;
			lastTramit++;
		} else {
			if (i == lastTramit) {
				var url = context + "/new?tramitid=" + uuid;
				if (letra == "i") {
					url = context + "/list/1?tramitid=" + uuid;
				}

				step.href = url;
			}
		}
	}

	//setTitolSteps();
	function setTitolSteps() {
		var titols = [ "Persona Autenticada", "Dades Solicitud",
				"Dades Cesionari" ];

		for (var i = 0; i < titols.length; i++) {
			$("#step" + i).html(titols[i]);
		}
	}
</script>
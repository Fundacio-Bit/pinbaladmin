
<script>

    var emailIDCache;
    var tipus; // 1 incidencia     0 solucitud

    function myFunction() {
        var operador = document.getElementById("operador").value;
        var root = "<%=request.getContextPath()%>${contexte}";

        var returnUrl = root;
        
		var checkBox = document.getElementById("checkNewItem");
		var itemJaExistent = checkBox.checked == true;
		
        if (itemJaExistent) {
        	//Update
			if (tipus == 1) {
	            var incidenciaID = document.getElementById("incidenciaID").value;
	            returnUrl += "/incidenciaExistent/" + emailIDCache + "/" + incidenciaID;
			} else {
				var solicitudID = document.getElementById("solicitudID").value;
				returnUrl += "/solicitudExistent/" + emailIDCache + "/" + solicitudID;
			}
        } else {
			//Create 
	        if (tipus == 1) {
	            var tipusIncidencia = document.getElementById("tipusIncidencia").value;
	            returnUrl += "/incidencia/" + emailIDCache + "/" + operador + "/" + tipusIncidencia;
			}else{
	            var estatSoli = document.getElementById("estatSolicitud").value;
				returnUrl += "/solicitud/" + emailIDCache + "/" + operador + "/" + estatSoli;
			}
		}
        
        window.location.href = returnUrl;
    }

    function crearIncidencia(emailID) {
        emailIDCache = emailID;
        tipus = 1;
        
        $(".incidencia-info").show();
        $(".solicitud-info").hide();
        
        $(".modal-title").html("Crear incidencia");
        $("#label-checkNewItem").html("Incidencia existent");

        testExist();
        $("#modelSeleccioTramitador").modal();
    }

    function crearSolicitud(emailID) {
        emailIDCache = emailID;
        tipus = 0;

        $(".incidencia-info").hide();
        $(".solicitud-info").show();

        $(".modal-title").html("Crear solicitud");
        $("#label-checkNewItem").html("Solicitud existent");

        testExist();
        $("#modelSeleccioTramitador").modal();
    }
    
    
    
	function testExist() {
		var checkBox = document.getElementById("checkNewItem");
		if (checkBox.checked == true) {
			$("#nou_element").hide();
			$("#existent_element").show();
		} else {
			$("#nou_element").show();
			$("#existent_element").hide();
		}
	}
</script>


<!-- Modal -->
<% request.setAttribute("currentuser", request.getRemoteUser()); %>

<div class="modal fade" id="modelSeleccioTramitador" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modal-title" class="modal-title"></h4>
				<button type="button" class="close"
					style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
			</div>
			
			
			
			<div class="modal-body">

<!-- 				<label id="label-checkNewItem" for="checkNewItem"></label> 
				<input type="checkbox" id="checkNewItem" onclick="testExist()">		
				<br>
 -->
				<div id="nou_element">
					<label id="label_operador" for="operador"> Selecciona operador: </label> 
					<select id="operador" class="my_select">
						<c:forEach items="${operadors}" var="operador">
							<option value="${operador.key}"
								${(currentuser eq operador.key)?'selected':''}>${operador.value}</option>
						</c:forEach>
					</select> 
	
					<br>
					<div class="incidencia-info">
						<label id="label_tipusIncidencia" for="tipusIncidencia">Tipus d'incidencia:</label> 
						<select id="tipusIncidencia" class="my_select">
							<c:forEach items="${tipusIncidencies}" var="tipusIncidencia">
								<option value="${tipusIncidencia.key}">${tipusIncidencia.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="solicitud-info">
						<label id="label_estatSolicitud" for="estatSolicitud">Estats Solicitud </label> 
						<select id="estatSolicitud" class="my_select">
							<c:forEach items="${estatSolicituds}" var="estatSolicitud">
								<option value="${estatSolicitud.key}">${estatSolicitud.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div id="existent_element">
					<p>Indica la existent</p>

					<div class="incidencia-info">
						<label for="incidenciaid">Incidencia ID:</label>
						<input type="text" id="incidenciaID" />
					</div>
					<div class="solicitud-info">
						<label for="solicitudid">Solicitud ID:</label> 
						<input type="text" id="solicitudID" />
					</div>
				</div>

				<div class="modal-footer">
				<button type="button" class="btn btn-default" onClick="myFunction()"
					data-dismiss="modal">Ok</button>
			</div>
		</div>

	</div>
</div>

<style>
.my_select {
	appearance: none;
	background-color: transparent;
	border: none;
	padding: 0 1em 0 0;
	margin: 0;
	width: 100%;
	font-family: inherit;
	font-size: inherit;
	cursor: inherit;
	line-height: inherit;
	outline: none;
}

.my_select::-ms-expand {
	display: none;
}

:root { -
	-select-border: #777; -
	-select-focus: blue; -
	-select-arrow: var(- -select-border);
}

.my_select {
	width: 100%;
	min-width: 15ch;
	max-width: 30ch;
	border: 1px solid var(- -select-border);
	border-radius: 0.25em;
	padding: 0.25em 0.5em;
	font-size: 1.15rem;
	cursor: pointer;
	line-height: 1.1;
	background-color: #fff;
	background-image: linear-gradient(to top, #f9f9f9, #fff 33%);
	display: grid;
	grid-template-areas: "select";
	align-items: center;
}

.my_select::after {
	content: "";
	width: 0.8em;
	height: 0.5em;
	background-color: var(- -select-arrow);
	clip-path: polygon(100% 0%, 0 0%, 50% 100%);
	justify-self: end;
}

.my_select, .select:after {
	grid-area: select;
}

#label_tipusIncidencia, #label_operador, #label_estatSolicitud {
	font-size: 1.15rem;
}
</style>


<!-- VISTA DEL LLISTAT DE CORREUS -->
<style>
table {
	margin-right: 2rem;
	width: 100% !important;
}
#infoNumRegistres {
	display: none;
}
</style>

<style>
td:nth-child(-n+2) {
	width: 0px;
}
td:last-child {
	width: 0px;
}
</style>

<c:if test="${__mostrarMissatgeArxiu__ == 'true'}">
	<style>
td:nth-child(4) {
	width: min-content;
	max-width: 500px;
}

td:nth-child(5) {
	width: 0px;
}

td:nth-child(6) {
	width: 0px;
}

iframe {
	border: 1px solid;
	width: 400px;
	height: 320px;
	/* 	max-width: 400px; */
}

textarea {
	max-width: 400px;
	max-height: 350px;
}

.adjuntDiv {
	max-width: 230px;
	overflow: auto;
	margin-top: 10px;
}

.adjuntsContainer {
	width: fit-content;
	margin-bottom: 10px;
}
</style>
</c:if>



<script>

    var itemIDCache;
    var tipus; // 1 incidencia     0 solucitud

    function myFunction() {
        var operador = document.getElementById("operador").value;
        var root = "<%=request.getContextPath()%>${contexte}";

        var returnUrl = root;
        if (tipus == 1) {
            var tipusIncidencia = document.getElementById("tipusIncidencia").value;
            returnUrl += "/incidencia/" + itemIDCache + "/" + operador + "/" + tipusIncidencia
		}else{
            var estatSoli = document.getElementById("estatSolicitud").value;
			returnUrl += "/solicitud/" + itemIDCache + "/" + operador + "/" + estatSoli;
		}
        window.location.href = returnUrl;
    }

    function crearIncidencia(itemID) {
        itemIDCache = itemID;
        tipus = 1;
        
        $("#incidencia-info").show();
        $("#solicitud-info").hide();
        
        $("#modelSeleccioTramitador").modal();
    }

    function crearSolicitud(itemID) {
        itemIDCache = itemID;
        tipus = 0;

        $("#incidencia-info").hide();
        $("#solicitud-info").show();

        $("#modelSeleccioTramitador").modal();
    }
</script>


<!-- Modal -->
<div class="modal fade" id="modelSeleccioTramitador" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Crear nova incidencia</h4>
				<button type="button" class="close"
					style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<%  request.setAttribute("currentuser", request.getRemoteUser()); %>

				<label id="label_operador" for="operador"> Selecciona operador: </label> 
				<select id="operador" class="my_select">
					<c:forEach items="${operadors}" var="operador">
						<option value="${operador.key}"
							${(currentuser eq operador.key)?'selected':''}>${operador.value}</option>
					</c:forEach>
				</select> 

				<br>
				<div id="incidencia-info">
					<label id="label_tipusIncidencia" for="tipusIncidencia">Tipus d'incidencia:</label> 
					<select id="tipusIncidencia" class="my_select">
						<c:forEach items="${tipusIncidencies}" var="tipusIncidencia">
							<option value="${tipusIncidencia.key}">${tipusIncidencia.value}</option>
						</c:forEach>
					</select>
				</div>
				<div id="solicitud-info">
					<label id="label_estatSolicitud" for="estatSolicitud">Estats Solicitud </label> 
					<select id="estatSolicitud" class="my_select">
						<c:forEach items="${estatSolicituds}" var="estatSolicitud">
							<option value="${estatSolicitud.key}">${estatSolicitud.value}</option>
						</c:forEach>
					</select>
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

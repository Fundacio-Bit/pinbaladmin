
<script>

    var itemIDCache;
    var tipus; // 1 incidencia     0 solucitud

    function myFunction() {
        var tramitador = document.getElementById("tramitador").value;
        var tipusIncidencia = document.getElementById("tipusIncidencia").value;
        var root = "<%=request.getContextPath()%>${contexte}/"
        window.location.href = root
                + ((tipus == 1) ? "incidencia" : "solicitud") + "/"
                + itemIDCache + "/" + tramitador + "/" + tipusIncidencia;
    }

    function crearIncidencia(itemID) {
        itemIDCache = itemID;
        tipus = 1;
        $("#modelSeleccioTramitador").modal();
    }

    function crearSolicitud(itemID) {
        itemIDCache = itemID;
        tipus = 2;
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

				<label id="label_tramitador" for="tramitador"> Selecciona tramitador: </label> 
				<select id="tramitador" class="my_select">
					<c:forEach items="${tramitadors}" var="tramitador">
						<option value="${tramitador.key}"
							${(currentuser eq tramitador.key)?'selected':''}>${tramitador.value}</option>
					</c:forEach>
				</select> 

				<br> 
				<label id="label_tipusIncidencia" for="tipusIncidencia">Tipus d'incidencia:</label> 
				<select id="tipusIncidencia" class="my_select">
					<c:forEach items="${tipusIncidencies}" var="tipusIncidencia">
						<option value="${tipusIncidencia.key}">${tipusIncidencia.value}</option>
					</c:forEach>
				</select>

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

#label_tipusIncidencia, #label_tramitador {
	font-size: 1.15rem;
}
</style>

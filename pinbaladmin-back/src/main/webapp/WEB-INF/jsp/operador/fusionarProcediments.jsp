<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<!-- Fusionar Procediments fusionarProcediment.jsp -->
<style>
.procediment-li {
	padding: 5px 1rem 5px 0;
	margin: 2px 0;
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

.procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#autocomplete-procediments {
	display: block;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 25rem;
	overflow-y: auto;
}

.procediment-item {
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.procediment-item:hover {
	background-color: #f1f1f1;
}

/* Verde para los ya seleccionados */
.procediment-item.selected {
	background-color: #d4edda;
	color: #155724;
	font-weight: bold;
}

#seleccionatsContainer {
	flex: 1;
}

#container {
	display: flex;
	flex-direction: row;
	gap: 2rem;
}

.input-container procediment {
	flex: 1;
}

#cercadorContainer {
	flex: 1;
	margin-top: 0.75rem;
}

#fusionarContainer {
	display: flex;
	justify-content: space-between;
	margin-right: 0.75rem;
	margin-bottom: 0.75rem;
}

#btnFusionar {
	margin-left: 1rem;
}

#fusionModal .modal-dialog {
	max-width: 80%;
}

.servei-item {
	display: inline-block;
	margin-right: 1rem;
	font-size: 15px;
}

.document-item {
	display: inline-block;
	margin-right: 1rem;
	font-size: 15px;
}

#fusionServiciosContainer {
	display: flex;
	flex-direction: column;
	margin-left: 0.5rem;
}

#fusionDocumentosContainer {
	display: flex;
	flex-direction: column;
	margin-left: 0.5rem;
}

.tdConsentiment {
	max-width: 300px;
	white-space: normal;
	word-wrap: break-word;
}
</style>

<h3>Fusionar Procediments</h3>

<div id="container">
	<div id="cercadorContainer" class="input-container procediment">
		<div id="cercador-procediments">
			<input id="procInput" class="w-100 form-control"
				name="cercadorProcediment" type="text" autocomplete="off"
				placeholder="Codi o nom del procediment. Mínim 2 caracters..." />
			<div id="autocomplete-procediments" aria-hidden="true"></div>
		</div>
	</div>

	<div id="seleccionatsContainer">
		<div id="fusionarContainer">
			<h5>Seleccionats per fusionar:</h5>
			<button id="btnFusionar" class="btn btn-primary">Fusionar</button>
		</div>
		<ul id="seleccionats"></ul>
	</div>
</div>

<form id="fusionForm" method="post" action="<%=request.getContextPath()%>${contexte}/fusionar">
<!-- Modal de revisión de fusión -->
<div class="modal fade" id="fusionModal" tabindex="-1" role="dialog"
	aria-labelledby="fusionModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="fusionModalLabel">Revisar fusión de
					procedimientos</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Cerrar">
					<i class="fas fa-times"></i>
					</button>
			</div>
			<div class="modal-body">

  <input type="hidden" name="fusionados" id="fusionadosInput">
  <input type="hidden" name="servicios" id="serviciosInput">
  <input type="hidden" name="documentos" id="documentosInput">
  

				<!-- Tabla de campos comparativos -->
				<h5>Campos principales</h5>
				<table class="table table-bordered" id="fusionCamposTable">
					<thead>
					</thead>
					<tbody>
						<!-- Se rellena dinámicamente -->
					</tbody>
				</table>

				<!-- Servicios -->
				<h5>Servicios asociados</h5>
				<div id="fusionServiciosContainer" class="mb-3">
					<!-- Se rellena dinámicamente -->
				</div>

				<!-- Documentos -->
				<h5>Documentos asociados</h5>
				<div id="fusionDocumentosContainer">
					<!-- Se rellena dinámicamente -->
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-close"
					data-bs-dismiss="modal">Cancelar</button>
				<button type="button" class="btn btn-primary" id="btnConfirmFusion">Confirmar
					fusión</button>
			</div>
		</div>
	</div>
</div>
</form>

<script>
var seleccionats = [];

// Buscar cuando se escribe
$("#procInput").on("input", function () {
    let param = $(this).val();
    if (param.length >= 2) {
        buscarProcediments(param);
    } else {
        $("#autocomplete-procediments").empty().hide();
    }
});

// Ocultar al hacer clic fuera del input/autocomplete
$(document).on("click", function (e) {
    if (
        !$(e.target).closest("#procInput").length &&
        !$(e.target).closest("#autocomplete-procediments").length
    ) {
        $("#autocomplete-procediments").hide();
    }else{
        $("#autocomplete-procediments").show();
    }
});

function buscarProcediments(param) {
    let url = "<%=request.getContextPath()%>${contexte}/buscar";

    $.ajax({
        url: url,
        type: "GET",
        data: { param: param },
        dataType: "json",
        success: function (data) {
            var container = $("#autocomplete-procediments");
            container.empty();

            if (data && data.length > 0) {
                data.forEach(function (proc) {
                    afegirProcediment(proc);
                });
                container.show();
            } else {
                container.text("No s'han trobat procediments.").show();
            }
        },
        error: function () {
            $("#autocomplete-procediments")
                .text("Error cercant procediments.")
                .show();
        }
    });
}

function afegirProcediment(proc) {
    // Si ya está seleccionado, marcar verde
    var jaSeleccionat = seleccionats.some(p => p.id === proc.id);

    var procedimentDiv = $("<div>")
        .addClass("procediment-item")
        .attr("role", "option")
        .toggleClass("selected", jaSeleccionat)
        .text("[" + proc.id + "] " + proc.codi + " - " + proc.nom)
        .on("click", function () {
            afegirSeleccionat(proc); // ahora toggle
           // $("#procInput").val("");
            $("#autocomplete-procediments").hide();
        });

    $("#autocomplete-procediments").append(procedimentDiv);
}

// Añadir o quitar (toggle) un procedimiento
function afegirSeleccionat(proc) {
    const idx = seleccionats.findIndex(p => p.id === proc.id);

    if (idx >= 0) {
        // ya está -> eliminar
        seleccionats.splice(idx, 1);
    } else {
        // no está -> añadir
        seleccionats.push(proc);
    }

    renderSeleccionats();
    refrescarAutocompleteHighlight();
}

// Pintar la lista de seleccionados
function renderSeleccionats() {
    const $ul = $("#seleccionats").empty();
    seleccionats.forEach(function (proc) {
        const $li = $("<li>")
            .addClass("procediment-li")
            .attr("data-id", proc.id);

        const $container = $("<div>").addClass("procediment-data-container");

        const $text = $("<span>")
            .addClass("procediment-data-text")
            .text("[" + proc.id + "] " + proc.codi + " - " + proc.nom);

        const $delete = $("<span>")
            .addClass("procediment-data-delete")
            .html('<i class="fas fa-times"></i>')
            .on("click", function (e) {
                e.preventDefault();
                eliminarSeleccionat(proc.id);
            });

        $container.append($text).append($delete);
        $li.append($container);
        $ul.append($li);
    });
}

// Eliminar de la lista manualmente
function eliminarSeleccionat(id) {
    const idx = seleccionats.findIndex(p => p.id === id);
    if (idx >= 0) seleccionats.splice(idx, 1);
    renderSeleccionats();
    refrescarAutocompleteHighlight();
}

// Marcar en verde los seleccionados en las sugerencias actuales
function refrescarAutocompleteHighlight() {
    $("#autocomplete-procediments .procediment-item").each(function () {
        const idText = $(this).text().match(/^\[(\d+)\]/);
        if (idText) {
            const id = parseInt(idText[1], 10);
            const selected = seleccionats.some(p => p.id === id);
            $(this).toggleClass("selected", selected);
        }
    });
}

$("#btnFusionar").click(function() {
	    if(seleccionats.length < 2){
	        alert(MSG_SELECCIONAR_MINIMO_DOS);
	        return;
	    }

	    const ids = seleccionats.map(p => p.id);

	    $.ajax({
	        url: "<%=request.getContextPath()%>${contexte}/detallesFusion",
	        type: "POST",
	        data: { ids: ids.join(",") },
	        success: function(data){
	            construirModalFusion(data);
	            $("#fusionModal").modal("show");
	        },
	        error: function(xhr,status,error){
	            console.error("Error al cargar detalles:", error);
	            alert(MSG_ERROR_CARGAR_DETALLES);
	        }
	    });

	});

	const mapLabels = new Map();
	
	mapLabels.set("solicitudID", "<fmt:message key='solicitud.solicitudID'/>");
	mapLabels.set("procedimentCodi", "<fmt:message key='solicitud.procedimentCodi'/>");
	mapLabels.set("codiDescriptiu", "<fmt:message key='solicitud.codiDescriptiu'/>");
	mapLabels.set("procedimentNom", "<fmt:message key='solicitud.procedimentNom'/>");
	mapLabels.set("procedimentTipus", "<fmt:message key='solicitud.procedimentTipus'/>");
	mapLabels.set("dataInici", "<fmt:message key='solicitud.dataInici'/>");
	mapLabels.set("entitatNom", "<fmt:message key='solicitud.denominacio'/>");
	mapLabels.set("entitatCif", "<fmt:message key='solicitud.nif'/>");
	mapLabels.set("entitatDir3", "<fmt:message key='solicitud.dir3'/>");
	mapLabels.set("dataCaducitat", "<fmt:message key='solicitud.dataCaducitat'/>");
	mapLabels.set("creador", "<fmt:message key='solicitud.creador'/>");
	mapLabels.set("operador", "<fmt:message key='solicitud.operador'/>");
	mapLabels.set("consentiment", "<fmt:message key='solicitud.consentiment'/>");
	mapLabels.set("organid", "<fmt:message key='solicitud.organid'/>");
	mapLabels.set("estatSolicitud", "<fmt:message key='solicitud.estatSolicitud'/>");
	mapLabels.set("estatpinbal", "<fmt:message key='solicitud.estatpinbal'/>");
	mapLabels.set("servicios", "<fmt:message key='solicitud.servicios'/>");
	mapLabels.set("documentos", "<fmt:message key='solicitud.documentos'/>");
	
	// Contactos
	mapLabels.set("contacteTitular", "<fmt:message key='solicitud.contacteTitularID'/>");
	mapLabels.set("contacteSolicitant", "<fmt:message key='solicitud.contacteSolicitantID'/>");
	mapLabels.set("contacteGestAut", "<fmt:message key='solicitud.contacteGestAutID'/>");
	mapLabels.set("contacteAuditoria", "<fmt:message key='solicitud.contacteAuditoriaID'/>");
	mapLabels.set("contacteTecnic", "<fmt:message key='solicitud.contacteTecnicID'/>");
	
	const mapLabelsConsentiment = new Map();
	mapLabelsConsentiment.set("tipus", "<fmt:message key='consentiment.tipus'/>");
	mapLabelsConsentiment.set("url", "<fmt:message key='consentiment.url'/>");
	mapLabelsConsentiment.set("nomFitxer", "<fmt:message key='consentiment.nomFitxer'/>");
	
	const ESTAT_PENDENT_REVISAR = "<fmt:message key='solicitud.estat.100'/>";
	const MSG_ERROR_CARGAR_DETALLES = "<fmt:message key='error.fusionar.cargar.detalles'/>";
	const MSG_CONFIRMAR_FUSION = "<fmt:message key='confirmar.fusion.mensaje'/>";
	const MSG_SELECCIONAR_MINIMO_DOS = "<fmt:message key='error.fusionar.minimo.dos'/>";
	const MSG_SENSE_DADES_CONSENTIMENT = "<fmt:message key='consentiment.sense.dades'/>";
	
    function construirModalFusion(procs){
        const tbody = $("#fusionCamposTable tbody").empty();
        const thead = $("#fusionCamposTable thead").empty();
        
        console.log(Object.keys(procs[0]));
        console.log(procs[0]);
        
        var todosLosCampos =[];
        procs.forEach(proc => {
			todosLosCampos = todosLosCampos.concat(Object.keys(proc));
	    });
		
        const campos = [...new Set(todosLosCampos)].filter(k => !["servicios","documentos","solicitudID", "codiSiaConv", "consentiment", 
        	"contacteTitular", "contacteSolicitant", "contacteGestAut", "contacteAuditoria", "contacteTecnic"].includes(k));

        // Cabeceras de procedimientos
        const ths = procs.map(p => p.procedimentCodi + " [" + p.solicitudID + "]");

		const trHeader = $("<tr>");
		trHeader.append($("<th>").text("Campo"));

		ths.forEach(th => {
			trHeader.append($("<th>").text(th));
		});
		trHeader.append($("<th>").text("Resultado final"));

		thead.append(trHeader)
        
        campos.forEach(campo => {
		    const tr = $("<tr>");
		    
		    tr.append($("<td>").text(mapLabels.get(campo)));
			
		    // Mostrar los valores de cada procedimiento (columna informativa)
		    procs.forEach(proc => {
		        tr.append($("<td>").text(proc[campo] || ""));
		    });
		
		    // --- Calcular valores únicos para este campo ---
		    const valoresUnicos = [...new Set(
		        procs
		            .map(p => p[campo])
		    )];
		
		    const tdResult = $("<td class='results'>");
		
	        valoresUnicos.forEach((valor, idx) => {
	            const radio = $("<input type='radio'>")
	                .attr("name", campo)
	                .val(valor);
	            if (idx === 0) radio.prop("checked", true);
	            const label = $("<label class='me-2'>")
	                .append(radio)
	                .append(" " + valor);
	            const div = $("<div class='option'>").append(label);
	            tdResult.append(div);
	        });

	        //Si estamos en el campo estado y hay mas de una opcione, y no es "PENDIENTE REVISAR", añadir la opción de "PENDIENTE REVISAR".
	        
	        if(campo == "estatSolicitud"){
            	if(valoresUnicos.length > 1 && !valoresUnicos.includes(ESTAT_PENDENT_REVISAR)){
            		const radio = $("<input type='radio'>")
                    .attr("name", campo)
                    .val(ESTAT_PENDENT_REVISAR);
                
                    const label = $("<label class='me-2'>")
                        .append(radio)
                        .append(ESTAT_PENDENT_REVISAR);
    	            const div = $("<div class='option'>").append(label);
    	            tdResult.append(div);
            	}
            }
	        
	        
/* 	        if(campo  = estado){
				if(valoresUnicos > 1 && !valoresUnicos.include("PENDIENTE REVISAR")){
		
				}
			}
 */		        
		    tr.append(tdResult);
		    tbody.append(tr);
		});
		
		{
			//Añadir fila para consentimiento completo, a escoger el consentimiento según el procedimiento.
			const tr = $("<tr>");
			tr.append($("<td>").text(mapLabels.get("consentiment")));
			// Mostrar los valores de cada procedimiento (columna informativa)
			
			procs.forEach(proc => {
		
                var consentiment = proc.consentiment;
                var html = "<b>SolicitudID: " + consentiment.solicitudID +  "</b>";
                
                if(consentiment.tipus){
                	html += "<br>Tipus: " + consentiment.tipus;
                }
                
                if(consentiment.url){
                	html += "<br>URL: " + consentiment.url;
                }
                
                if(consentiment.nomFitxer){
                	html += "<br>Fitxer: " + consentiment.nomFitxer;
                }
                
                if(!consentiment.tipus && !consentiment.url && !consentiment.nomFitxer){
                	html += "<br><i>" + MSG_SENSE_DADES_CONSENTIMENT + "</i>";
                }
                
                tr.append($("<td class='tdConsentiment'>").html(html));
            });
			
			// --- consentimientosUnicos será una lista de solicitudID de todos los que tienen procedimento no nulo.
			
			var consentimientosUnicos = [];
			procs.forEach(p=>{
                if(p.consentiment){
                	consentimientosUnicos.push(p.solicitudID);
                }
            });
			
			const tdResult = $("<td>");
			consentimientosUnicos.forEach((valor, idx) => {
                const radio = $("<input type='radio'>")
                    .attr("name", "consentiment")
                    .val(valor);
                if (idx === 0) radio.prop("checked", true);
                const label = $("<label class='me-2'>")
                    .append(radio)
                    .append("Solicitud " + valor);
                tdResult.append(label);
//                tdResult.append($("<br>"));
	            const div = $("<div class='option'>").append(label);
	            tdResult.append(div);

            });
			tr.append(tdResult);
			tbody.append(tr);
			
		}
		
		// CONTACTOS - Función auxiliar para renderizar contactos
		function renderContacteRow(label, fieldName, procs) {
			const tr = $("<tr>");
			tr.append($("<td>").text(label));
			
			// Mostrar los datos de cada contacto por procedimiento
			procs.forEach(proc => {
				const contacte = proc[fieldName];
				let html = "";
				if (contacte && contacte.contacteID) {
					html = "<b>" + (contacte.nombreCompleto || "") + "</b>";
					if (contacte.nif) html += "<br>NIF: " + contacte.nif;
					if (contacte.mail) html += "<br>Email: " + contacte.mail;
					if (contacte.telefon) html += "<br>Tel: " + contacte.telefon;
					if (contacte.carrec) html += "<br>Cargo: " + contacte.carrec;
				} else {
					html = "<i>No definido</i>";
				}
				tr.append($("<td class='tdConsentiment'>").html(html));
			});
			
			// Radios para elegir
			const contacteIDs = [];
			procs.forEach(p => {
				const contacte = p[fieldName];
				if (contacte && contacte.contacteID && !contacteIDs.some(c => c.id === contacte.contacteID)) {
					contacteIDs.push({ id: contacte.contacteID, solicitudID: contacte.solicitudID, nombre: contacte.nombreCompleto || "Sin nombre" });
				}
			});
			
			const tdResult = $("<td>");
			contacteIDs.forEach((contacte, idx) => {
				const radio = $("<input type='radio'>")
					.attr("name", fieldName)
					.val(contacte.id);
				if (idx === 0) radio.prop("checked", true);
				const label = $("<label class='me-2'>")
					.append(radio)
					.append(" [" + contacte.solicitudID + "] " + contacte.nombre);
				const div = $("<div class='option'>").append(label);
				tdResult.append(div);
			});
			
			// Si no hay contactos, poner opción "Ninguno"
			if (contacteIDs.length === 0) {
				tdResult.html("<i>Ningún contacto disponible</i>");
			}
			
			tr.append(tdResult);
			tbody.append(tr);
		}
		
		// Renderizar todos los contactos
		renderContacteRow(mapLabels.get("contacteTitular"), "contacteTitular", procs);
		renderContacteRow(mapLabels.get("contacteSolicitant"), "contacteSolicitant", procs);
		renderContacteRow(mapLabels.get("contacteGestAut"), "contacteGestAut", procs);
		renderContacteRow(mapLabels.get("contacteAuditoria"), "contacteAuditoria", procs);
		renderContacteRow(mapLabels.get("contacteTecnic"), "contacteTecnic", procs);
		
		
		
		
        // Servicios: unión sin repetición
        const serviciosMap = {};
        procs.forEach(proc=>{
			console.log(proc.servicios);
            proc.servicios.forEach(s=>serviciosMap[s.id]=s.nom);
        });
        const serviciosContainer = $("#fusionServiciosContainer").empty();
        for(const [id,nombre] of Object.entries(serviciosMap)){
            const chk = $("<input type='checkbox' checked>").attr("data-id",id);
            serviciosContainer.append($("<label class='me-3 servei-item'>").append(chk).append(" " + nombre));
        }
        
	     // Limpiamos el contenedor principal
        const docsContainer = $("#fusionDocumentosContainer").empty();

        // Agrupamos documentos por solicitudID
        const groupedDocs = {};
        procs.forEach(proc => {
            if (proc.documentos && proc.documentos.length > 0) {

				var key = "Procediment " + proc.procedimentCodi;
                proc.documentos.forEach(d => {
					
                    if (!groupedDocs[key]) groupedDocs[key] = [];
                    groupedDocs[key].push(d);
                });
            }
        });

        console.log("Documentos agrupados:", groupedDocs);
        // Generamos el HTML agrupado
        for (const [key, docs] of Object.entries(groupedDocs)) {

            // Creamos un título de grupo (ejemplo: [50073])
            const groupTitle = $("<h6>").addClass("mt-3 mb-2").text(key);

            // Contenedor para los documentos de ese procedimiento
            const groupDiv = $("<div>").addClass("document-group ms-3");

            // Añadimos cada documento con su checkbox
            docs.forEach(d => {
                const label = $("<label class='me-3 document-item d-block'>");
                const chk = $("<input type='checkbox'>").attr("data-id", d.id);
                label.append(chk).append(" [" + d.solicitudID + "] " + d.nom);
                groupDiv.append(label);
            });

            // Añadimos el grupo completo al contenedor principal
            docsContainer.append(groupTitle).append(groupDiv);
        }

    }
    
    
    $("#btnConfirmFusion").click(function(){
        // Validar servicios seleccionados
        const servicios = [];
        $("#fusionServiciosContainer input:checked").each(function(){
            servicios.push(parseInt($(this).attr("data-id")));
        });
        
        if(servicios.length === 0){
            alert("<fmt:message key='error.fusionar.servicios.requeridos'/>");
            return;
        }
        
        // Validar contactos críticos (titular y solicitante)
        const contacteTitular = $("input[name='contacteTitular']:checked").val();
        const contacteSolicitant = $("input[name='contacteSolicitant']:checked").val();
        
        if(!contacteTitular){
            alert("<fmt:message key='error.fusionar.contacte.titular.requerido'/>");
            return;
        }
        
        if(!contacteSolicitant){
            alert("<fmt:message key='error.fusionar.contacte.solicitante.requerido'/>");
            return;
        }
        
        // Confirmación antes de proceder
        const numProcs = seleccionats.length;
        const mensaje = MSG_CONFIRMAR_FUSION.replace("{0}", numProcs);
        if(!confirm(mensaje)){
            return;
        }
        
        // Recoger IDs de procedimientos fusionados
        $("#fusionadosInput").val(seleccionats.map(p => p.id).join(","));
        
        // Recoger servicios seleccionados
        $("#serviciosInput").val(servicios.join(","));

        // Recoger documentos seleccionados
        const documentos = [];
        $("#fusionDocumentosContainer input:checked").each(function(){
            documentos.push(parseInt($(this).attr("data-id")));
        });
        $("#documentosInput").val(documentos.join(","));

        // Los campos de radio se envían automáticamente por el formulario
        
        $("#fusionForm").submit();
        $("#fusionModal").modal("hide");
    });
    
    $("#fusionModal .btn-close").click(function(){
    	$("#fusionModal").modal("hide");
    });

</script>

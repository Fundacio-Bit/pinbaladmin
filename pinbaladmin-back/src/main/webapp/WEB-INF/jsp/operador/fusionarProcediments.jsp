<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

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

.results {
/* 	display: flex;
	flex-direction: column;
 */}

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
					aria-label="Cerrar"></button>
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
				<button type="button" class="btn btn-secondary"
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
	        alert("Selecciona almenys dos procediments per fusionar");
	        return;
	    }

	    const ids = seleccionats.map(p => p.id);

	    $.ajax({
	        url: "<%=request.getContextPath()%>${contexte}/detallesFusion",
	        type: "POST",
	        data: { ids: ids.join(",") }, // 3333,4444
	        success: function(data){
	            construirModalFusion(data);
	            $("#fusionModal").modal("show");
	        },
	        error: function(xhr,status,error){
	            console.error("Error:", error);
	            alert("No se pudieron cargar los detalles.");
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
	mapLabels.set("personaContacte", "<fmt:message key='solicitud.personaContacte'/>");
	mapLabels.set("personaContacteEmail", "<fmt:message key='solicitud.personaContacteEmail'/>");
	mapLabels.set("responsableProcNom", "<fmt:message key='solicitud.responsableProcNom'/>");
	mapLabels.set("responsableProcEmail", "<fmt:message key='solicitud.responsableProcEmail'/>");
	
	/*
	
			private String titularFirmaNIF;
		private String titularFirmaNom;
		
		private String entitatNom;
		private String entitatCif;
		private String entitatDir3;
		*/
		
	mapLabels.set("titularFirmaNIF", "<fmt:message key='solicitud.titularFirmaNif'/>");
	mapLabels.set("titularFirmaNom", "<fmt:message key='solicitud.titularFirmaNom'/>");
	mapLabels.set("titularFirmaEmail", "<fmt:message key='solicitud.titularFirmaEmail'/>");
	mapLabels.set("titularFirmaLlinatges", "<fmt:message key='solicitud.titularFirmaLlinatges'/>");
	
	
	mapLabels.set("entitatNom", "<fmt:message key='solicitud.denominacio'/>");
	mapLabels.set("entitatCif", "<fmt:message key='solicitud.nif'/>");
	mapLabels.set("entitatDir3", "<fmt:message key='solicitud.dir3'/>");
		
	
	mapLabels.set("creador", "<fmt:message key='solicitud.creador'/>");
	mapLabels.set("operador", "<fmt:message key='solicitud.operador'/>");
	
	
	mapLabels.set("consentiment", "<fmt:message key='solicitud.consentiment'/>");
	mapLabels.set("urlconsentiment", "<fmt:message key='solicitud.urlconsentiment'/>");
	mapLabels.set("consentimentadjunt", "<fmt:message key='solicitud.consentimentadjunt'/>");
	mapLabels.set("organid", "<fmt:message key='solicitud.organid'/>");
	mapLabels.set("estatSolicitud", "<fmt:message key='solicitud.estatSolicitud'/>");
	mapLabels.set("estatpinbal", "<fmt:message key='solicitud.estatpinbal'/>");
	mapLabels.set("servicios", "<fmt:message key='solicitud.servicios'/>");
	mapLabels.set("documentos", "<fmt:message key='solicitud.documentos'/>");
	
	const mapLabelsConsentiment = new Map();
	mapLabelsConsentiment.set("tipus", "<fmt:message key='consentiment.tipus'/>");
	mapLabelsConsentiment.set("url", "<fmt:message key='consentiment.url'/>");
	mapLabelsConsentiment.set("nomFitxer", "<fmt:message key='consentiment.nomFitxer'/>");
	
	const ESTAT_PENDENT_REVISAR = "<fmt:message key='solicitud.estat.100'/>";
	
    function construirModalFusion(procs){
        const tbody = $("#fusionCamposTable tbody").empty();
        const thead = $("#fusionCamposTable thead").empty();
        
        console.log(Object.keys(procs[0]));
        console.log(procs[0]);
        
        var todosLosCampos =[];
        procs.forEach(proc => {
			todosLosCampos = todosLosCampos.concat(Object.keys(proc));
	    });
		
        const campos = [...new Set(todosLosCampos)].filter(k => !["servicios","documentos","solicitudID", "codiSiaConv", "consentiment"].includes(k));
  	      
//        const campos = Object.keys(procs[0]).filter(k => !["servicios","documentos","solicitudID", "codiSiaConv", "consentiment"].includes(k));

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
                var html = "<b>SolicitudID: " + consentiment.solicitudID +  "</b><br> Tipus: " + (consentiment.tipus || "");
                if(consentiment.url){
                	html +=  "<br>" + "URL: " + (consentiment.url || "");
                }
                
                if(consentiment.nomFitxer){
                	html +=  "<br>" + "Fitxer: " + (consentiment.nomFitxer || "");
                }
                
                tr.append($("<td>").html(html));
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
		
		
		
		
		//IDs de solicitud:
		const idsMap = {};
	    procs.forEach(proc=>{
               idsMap[proc.solicitudID]=proc.solicitudID;
           });
           const idsContainer = $("#fusionIdsContainer").empty();
           for(const [id] of Object.entries(idsMap)){
               const chk = $("<input type='checkbox' checked>").attr("data-id",id);
               idsContainer.append($("<label class='me-3 id-item'>").append(chk).append(" " + id));
           }

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
                label.append(chk).append(" [" + d.id + "] " + d.nom);
                groupDiv.append(label);
            });

            // Añadimos el grupo completo al contenedor principal
            docsContainer.append(groupTitle).append(groupDiv);
        }

    }
    
    
    $("#btnConfirmFusion").click(function(){
        const resultado = {
            fusionados: seleccionats.map(p=>p.id),
            campos:{},
            servicios:[],
            documentos:[]
        };

        // Campos finales según radio buttons
        const campos = $("#fusionCamposTable tbody tr td:first-child").map((i,td)=>$(td).text()).get();
        campos.forEach(campo=>{
        	// En tu recogida de campos
        	const val = $("#fusionCamposTable input[name='campo_" + campo + "']:checked").data("valor");
        	resultado.campos[campo] = val || "";

        });

        $("#fusionServiciosContainer input:checked").each(function(){
            resultado.servicios.push(parseInt($(this).attr("data-id")));
        });

        $("#fusionDocumentosContainer input:checked").each(function(){
            resultado.documentos.push(parseInt($(this).attr("data-id")));
        });

        console.log("Datos para enviar al backend:",resultado);
        
//        alert("Simulación de fusión. Aquí enviarías los datos al controlador vía AJAX.");
        
        $("#fusionadosInput").val(resultado.fusionados.join(","));
        $("#serviciosInput").val(resultado.servicios.join(","));
        $("#documentosInput").val(resultado.documentos.join(","));

        $("#fusionForm").submit()

        $("#fusionModal").modal("hide");
    });

/*     $("#fusionForm").on("submit", function(e) {
        // Guardar arrays en los hidden inputs como strings separados por coma
        $("#fusionadosInput").val(seleccionats.map(p => p.id).join(","));
        $("#serviciosInput").val(serviciosSeleccionados.join(","));
        $("#documentosInput").val(documentosSeleccionados.join(","));
    });
 */    

</script>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:forEach var="entry" items="${divServeis}">
	<div id="modal_infoservei_${entry.key}" class="modal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document" style="max-width: 45%;">
			<div class="modal-content" style="width: fit-content;">
				<div class="modal-header">
					<h4 id="myModalLabel">
						<fmt:message key="servei.llistat" />
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b><fmt:message key="solicitud.notes" />:
									&nbsp;</b></td>
							<td>${notesSolicitud[entry.key]}</td>
						</tr>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b> <fmt:message
										key="solicitud.codiDescriptiu" />:&nbsp;
							</b></td>
							<td>${codiDescriptiuSolicitud[entry.key]}</td>
						</tr>
					</table>
					${entry.value}
				</div>
			</div>
		</div>
	</div>
</c:forEach>



<style>
.pOrganOpen {
	max-width: fit-content;
}

.pOrganClose {
	max-width: fit-content;
}

.spanOrganOpen {
	font-weight: normal;
}

.spanOrganClose {
	display: none;
}
</style>

<script>
	var organs = $(".elemOrgan");
	for (var i = 0; i < organs.length; i++) {
		var pa = organs[i];
		pa.parentElement.style.width = "30%";
		pa.parentElement.onclick = pa.onclick;
		pa.onclick = null;
	}

	function toggleJerarquia(elem) {

		console.log(elem);
		var myP = elem.getElementsByTagName("p")[0];
		console.log(myP);
		var mySpan = elem.getElementsByTagName("span")[0];
		console.log(mySpan);

		$(myP).toggleClass("pOrganOpen");
		$(myP).toggleClass("pOrganClose");

		$(mySpan).toggleClass("spanOrganOpen");
		$(mySpan).toggleClass("spanOrganClose");
	}
</script>

<c:if test="${desplegableOrgans == 'true'}">

<div class="modal fade" style="display: none" id="jerarquiaModal"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="titolModal">Filtre d'Organs</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div>
					<p>Pot escriure aqui i filtrar per l'organ que vulgui</p>
				</div>
				<div>

					<select id="solicitud_organid" name="solicitud.organid"
						class="form-control col-md-9-optional" style="display: none;">
                        <option value=""></option>
						<c:forEach var="organ" items="${organs}">
						
							<option ${organ.organid == organSelected ? 'selected' : '' } value="${organ.organid}">(${organ.dir3}) ${organ.nom}</option>
						</c:forEach>

					</select> 
					<div id="botons-modal">
						<div id="submit-filtre-organ" class="btn btn-sm btn-primary float-right botoselecciolist">Cercar</div>
						<div id="netejar-filtre-organ" class="btn btn-sm btn-warning float-right botoselecciolist">Netejar</div>
                    </div>
				</div>
			</div>
		</div>
	</div>
</div>



	<script>
		$("#submit-filtre-organ").on("click", function() {
			submit();
		});

		$("#netejar-filtre-organ").on("click", function() {
			$("#myInputOrganID").val("");
			$("#solicitud_organid").val("");
		});

		
		
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200) {
	            var optionsTxt = this.responseText;
	            const select = document.getElementById("solicitud_organid");
 	            select.innerHTML = optionsTxt;
 	            
 	           /*
	            //	            var options = optionsTxt.split('|');
	            for (var j = 0; j < options.length; j++) {
	            	select.append(options[j]);
	            }
 */	            createSelectOption();
	         }
	    };

	    var base = '<%=request.getContextPath()%>${contexte}/getAllOrgansGestors';
	    xhttp.open("GET", base, true);

	    xhttp.setRequestHeader("Content-type", "application/json");
	    

		document.addEventListener("DOMContentLoaded", function() {
			 createSelectOption();
/* 		    xhttp.send(''); */
		});

		function createSelectOption() {
			const select = document.getElementById("solicitud_organid");

			// Crear un campo de entrada de texto
			const input = document.createElement("input");
			input.type = "search";
			input.id = "myInputOrganID";
			input.className = "w-100 form-control";
			input.placeholder = "Escribe para buscar";
			input.autocomplete = "off";
			input.name = "solicitud.organid";

			//Asigna valor actual (per si es edició i no creació)
			const selected = select.selectedOptions[0];
			if (selected.value.length > 0) {
				input.value = selected.innerHTML;
			} else {
				input.value = "";
			}

			// Crear un div para el desplegable de opciones
			const dropdown = document.createElement("div");
			dropdown.id = "autocomplete-dropdown";
			dropdown.className = "autocomplete-dropdown";
			dropdown.style.zIndex = 5;

			// Agregar el campo de entrada de texto y el desplegable después del select
			select.parentNode.insertBefore(dropdown, select.nextSibling);
			select.parentNode.insertBefore(input, select.nextSibling);

			// Ocultar el select original
			select.style.display = "none";

			// Manejar eventos de entrada en el campo de entrada de texto
			input
					.addEventListener(
							"input",
							function() {
								const value = input.value.trim().toLowerCase();
								const options = select.options;

								// Limpiar el desplegable
								dropdown.innerHTML = "";

								// Mostrar todas las opciones si el campo de entrada está vacío
								if (value === "") {
									for (let i = 0; i < options.length; i++) {
										dropdown
												.appendChild(createDropdownOption(options[i]));
									}
								} else {
									// Filtrar y mostrar las opciones que coincidan con el valor ingresado
									for (let i = 0; i < options.length; i++) {
										const optionText = options[i].textContent
												.toLowerCase();
										if (optionText.includes(value)) {
											dropdown
													.appendChild(createDropdownOption(options[i]));
										}
									}
								}

								// Mostrar u ocultar el desplegable según las opciones disponibles
								if (dropdown.children.length > 0) {
									dropdown.style.display = "block";
								} else {
									dropdown.style.display = "none";
								}
							});

			// Controlador de clic en el desplegable para seleccionar la opción
			dropdown.addEventListener("click", function(e) {
				if (e.target && e.target.tagName === "DIV") {
					const optionText = e.target.textContent;
					input.value = optionText;
					select.value = e.target.value;
					dropdown.style.display = "none";
					select.dispatchEvent(new Event("change")); // Disparar evento "change" en el select
				}
			});

			$(document)
					.click(
							function(event) {
								const $target = $(event.target);

								if (!$target.is(input)
										&& $target
												.closest("#autocomplete-dropdown").length === 0) {
									const selected = select.selectedOptions[0];
									if (selected.value.length > 0) {
										input.value = selected.innerHTML;
									} else {
										input.value = "";
									}
									dropdown.style.display = "none";
								}
							});

			// Función para crear una opción en el desplegable
			function createDropdownOption(option) {
				const div = document.createElement("div");
				div.textContent = option.textContent;
				div.className = "autocomplete-dropdown-item";
				div.value = option.value;
				return div;
			}
		}

		function openFiltreOrgans() {
			$("#jerarquiaModal").modal();
		}

		function submit() {
			document.getElementById("solicitudFilterForm").submit();
		}
	</script>

		<style>
.autocomplete-container {
	position: relative;
}

.autocomplete-input {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	position: relative;
}

.autocomplete-dropdown {
	display: none;
	position: absolute;
	z-index: 1;
	border: 1px solid #ccc;
	border-top: none;
	max-height: 170px;
	overflow-y: auto;
	width: 100%;
	background-color: white;
	max-width: 40rem;
}

.autocomplete-dropdown-item {
	padding: 5px;
	cursor: pointer;
}

.autocomplete-dropdown-item:hover {
	background-color: #f0f0f0;
}

.autocomplete-dropdown.active {
	display: block;
}

#botons-modal div {
	text-align: right;
	margin-top: 1rem;
}
</style>
</c:if>





<!--  RENOVACIÓ ESTETICA FILTRES -->
<style>
.form-inline{
	margin: 0px 2rem;
}

.form-inline div {
    width: 50% !important;
    display: flex;
}

.form-inline span {
    width: 12rem;
    text-align: right;
    margin: 0 1rem;
}
</style>





<!--  MODAL PER CANVIAR ESTAT SOLICITUDS DISTRIBUCIO -->


<script>
	function openModalSolicitudDistribucio(soliID, procediment) {
		//Crear missatges amb les dades de la solicitud.

		let titol = "Revisar Solicitud a Distribució: ";
		let mmissatge = "Si la solicitud està a DISTRIBUCIÓ, fer click a ACCEPTAR per canviar l'estat.";
		
		let result = confirm(titol + "\n\n" + procediment + "\n\n" + mmissatge);
        if (result === true) {
            console.log("User clicked OK");
            window.location.href = '<%=request.getContextPath()%>${contexte}/canviarEstatSoli/'+soliID;
        } else {
            console.log("User clicked Cancel");
        }
        
		//Si accepta, redirigir a /canviarEstatSoli, sino, no fer res.
	}
</script>



<!--  MODAL PER ASSIGNAR ESTATS A SOLICITUDS -->


<div id="modalSolicitudes" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">

        <h5 id=titolModalEstatsSoli class="modal-title">Modal Visible</h5>

        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
      </div>
      <div class="modal-body">
      	<div id="botoneraEstatsSolicitud">
      	

        <button type="button" class="back" onclick="anterior() " >Atras</button>
        <button type="button" class="mostrarUpdates" onclick="mostrarUpdates() " >Finalizar</button>
        
      	
      	</div>
      
					<div style="display: flex;">
						<div id="accionesEstados">
							<button class="btn-estado" data-estado="60">Tancada</button>
							<button class="btn-estado" data-estado="40">Autorizada</button>
							<button class="btn-estado" data-estado="101">Denegada</button>
							
<!-- 							<button class="btn-estado" data-estado="39">Parcialment Autorizada</button> -->
							<button class="btn-estado" data-estado="19">Pendent Enviar Madrid</button>
							<button class="btn-estado" data-estado="21">Pendent Autoritzar (Enviat a Madrid)</button>
							<button class="btn-estado" data-estado="32">Madrid Esmena. Avisar Contacte</button>
							<button class="btn-estado" data-estado="31">Esmena Solicitada. Pendent Contacte</button>
							<button class="btn-estado" data-estado="33">Resposta Contacte. Revisar Esmena</button>

							<button class="btn-estado" data-estado="100">Revisar despres</button>

						</div>
						<div id="detalleEventos">
							<!-- Aquí se mostrarán los eventos -->
						</div>
					</div>
				</div>
    </div>
  </div>
</div>


<!-- Modal -->
	<script type="text/javascript">

    let solicitudes = [];
    let updates = [];
    let currentIndex = 0;

    function checkEstatSolicitudManual() {

	    const contextPath = '<%= request.getContextPath() %>';
	    
	    if (solicitudes.length == 0) {

	    	console.log("No tenemos solicitudes, vamos a buscarlas");
	    	
	    	$.ajax({
	            url: contextPath + '/operador/solicitudlocal/jsonSolicitudEvents',
	            method: 'GET',
	           success: function (data) {
	                if (!data || data.length === 0) {
	                    alert("No hay solicitudes pendientes.");
	                    return;
	                }
	
	                solicitudes = data;
	    			console.log("Tenemos solicitudes");
	    			console.log(solicitudes);
	    			
	    			iniciarModal();
	            },
	            error: function () {
	                alert("Error al obtener las solicitudes.");
	            }
	        });
		}else{
			iniciarModal();
		}
    }
    
    function mostrarModal() {
        $('#modalSolicitudes').modal('show');  // <-- esta línea
    }


    function cargarSolicitudActual() {
        if (currentIndex >= solicitudes.length) {
            // Ya no quedan solicitudes, mostramos los updates en el modal
            mostrarUpdates();
            return;
        }
        $('#accionesEstados').show()

        const solicitud = solicitudes[currentIndex];
        // Aquí cargas la info de la solicitud en el modal, por ejemplo:
        $('#titolModalEstatsSoli').html(solicitud.codi + " - " + solicitud.nom);
        $('#detalleEventos').html(renderizarEventos(solicitud.events));
        
    }
    function renderizarEventos(eventos) {
        if (!eventos || eventos.length === 0) {
            return "<p>No hay eventos.</p>";
        }

        var html = '<div class="chat-container">';

        for (var i = 0; i < eventos.length; i++) {
            var evento = eventos[i];
            var tipo = parseInt(evento.tipus, 10);
            var esTramitador = (tipo === -1 || tipo === 1 || tipo === -2 || tipo === -3);
            var alineacion = esTramitador ? "izquierda" : "derecha";

            var tipoTexto = interpretarTipusComentari(tipo);

            var persona = evento.persona ? evento.persona : "Sin nombre";
            var destinatari = evento.destinatari ? evento.destinatari : "Sin destinatari";
            var comentari = evento.comentari ? evento.comentari : "Sin comentario";
			var fecha = evento.dataEvent;
            
            html += '<div class="mensaje ' + alineacion + '">';
            html += '  <div class="cabecera">';
            html += '    <div class="dataEvent">' + fecha + '</div>';
            html += '    <div class="persona">Persona: ' + persona + '</div>';
            html += '    <div class="destinatari">Destinatari: ' + destinatari + '</div>';
            html += '  </div>';
            html += '  <div class="contenido">' + comentari + '</div>';
            html += '</div>';
        }

        html += '</div>';
        return html;
    }



    function interpretarTipusComentari(tipus) {
        switch (parseInt(tipus, 10)) {
            case -1: return "Comentari privat tramitador";
            case 1:  return "Comentari public tramitador";
            case 2:  return "Comentari public contacte";
            case -2: return "Comentari suport";
            case -3: return "Consulta a cedent";
            case 3:  return "Resposta de cedent";
            default: return "Comentari desconegut";
        }
    }



    function mostrarUpdates() {
        let html = '<h4>Updates realizados:</h4><ul>';
        updates.forEach(u => {
            html += '<li>UPDATE pad_solicitud SET estatid = ' + u.nuevoEstado+ ' WHERE solicitudid= ' + u.solicitudId  + '; </li>';
        });
        html += '</ul>';

        $('#titolModalEstatsSoli').html('Final');
        $('#detalleEventos').html(html);
        $('#accionesEstados').hide();  // Ocultamos los botones
        
    }

    $('.btn-estado').off('click').on('click', function (e) {
        e.preventDefault();  // 👈 Evita que se envíe el formulario
        e.stopPropagation(); // 👈 Evita burbujeo por si acaso

        const nuevoEstado = $(this).data('estado');
        const solicitud = solicitudes[currentIndex];

        console.log(solicitud.soliID +  " " + nuevoEstado );
        
        updates.push({
            solicitudId: solicitud.soliID,
            nuevoEstado: nuevoEstado
        });

        currentIndex++;
        if (currentIndex < solicitudes.length) {
            cargarSolicitudActual();
        } else {
            mostrarUpdates();
        }
    });

    function anterior(){
		if (currentIndex != 0) {
	        currentIndex--;
	        cargarSolicitudActual();
		}
    }
    
    // Al iniciar, llama:
    function iniciarModal() {
        $('#accionesEstados').show();
        cargarSolicitudActual();
        $('#modalSolicitudes').modal('show');
    }



</script>

<style>
.chat-container {
	display: flex;
	flex-direction: column;
	gap: 10px;
}

.mensaje {
	max-width: 70%;
	padding: 10px;
	border-radius: 10px;
	background-color: #f1f1f1;
	position: relative;
}

.mensaje.izquierda {
	align-self: flex-start;
	background-color: #aee4ff;
}

.mensaje.derecha {
	align-self: flex-end;
	background-color: #c8e6c9;
}

.cabecera {
	font-weight: bold;
	font-size: 0.85em;
	margin-bottom: 5px;
	color: #333;
}

.contenido {
	font-size: 0.95em;
	border: 1px solid black;
	padding: 5px;
	border-radius: 5px;
	overflow: auto;
}

.modal-dialog {
	max-width: none;
	margin-left: 5rem;
	margin-right: 5rem;
}

#accionesEstados {
	padding: 8px;
	width: 100%;
	max-width: 12rem;
	min-width: 10rem;
	margin-right: 20px;
}

.btn-estado {
	width: 100%;
	min-height: 3rem;
	margin: 6px 0;
}

#botoneraEstatsSolicitud {
	text-align: right;
	margin: 6px 12px;
}

#botoneraEstatsSolicitud button {
	margin-left: 2rem;
	width: 6rem;
	height: 2rem;
}

#detalleEventos {
	width: -moz-available;
	/*   background-color: #e6e6e663; */
	padding: 10px;
	border: 1px solid gray;
	border-radius: 5px;
}
</style>





<!-- FUSIONAR CONTACTOS  -->
<div id="modalContactos" class="modal fade">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <h5 id="tituloContacto">Duplicados</h5>
      </div>

      <div class="modal-body">

        <div id="listaContactos"></div>

        <div style="margin-top: 10px;">
			<button type="button" onclick="anteriorGrupo()">Anterior</button>
			<button type="button" onclick="siguienteGrupo()">Siguiente</button>
			<button type="button" onclick="mostrarResumen()">Finalizar</button>
			<button type="button" onclick="saltarGrupo()">Revisar después</button>
        </div>

      </div>

    </div>
  </div>
</div>

<script type="text/javascript">

let grupos = [];
let current = 0;
let fusionPlan = [];
let contactoSeleccionado = null;
let pendientes = [];

const contextPath = '<%=request.getContextPath()%>';

function iniciarFusiones() {

    $.ajax({
        url: contextPath + '/admin/solicitudactiva/contactos/duplicados',
        method: 'GET',

        success: function(data) {

            console.log("Grupos de contactos duplicados:");
            console.log(data);

            if (!data || data.length === 0) {
                alert("No hay contactos duplicados.");
                return;
            }

            grupos = data;
            current = 0;
            fusionPlan = [];
            contactoSeleccionado = null;

            mostrarGrupo();

            $('#modalContactos').modal('show');
        },

        error: function(xhr, status, error) {
            console.error("Error obteniendo duplicados", error);
            alert("Error obteniendo contactos duplicados.");
        }
    });
}

function mostrarGrupo() {

    if (current >= grupos.length) {
        mostrarResumen();
        return;
    }

    const grupoActual = grupos[current];
    const contactos = grupoActual.contactos;

    console.log("Mostrando grupo:");
    console.log(grupoActual);

    let html = '';

    html += '<h4>Duplicados (' + (current + 1) + ' de ' + grupos.length + ')</h4>';
    html += '<p><b>' + grupoActual.nif + '</b> - ' + grupoActual.mail + '</p>';

    html += '<table class="table table-bordered table-hover">';
    html += '<thead>';
    html += '<tr>';
    html += '<th>Sel.</th>';
    html += '<th>ID</th>';
    html += '<th>Nombre</th>';
    html += '<th>Apellido 1</th>';
    html += '<th>Apellido 2</th>';
    html += '<th>Teléfono</th>';
    html += '<th>Cargo</th>';
    html += '<th>Username</th>';
    html += '<th>Full Name</th>';
    html += '<th>Solicituds</th>';
    html += '</tr>';
    html += '</thead>';
    html += '<tbody>';

    contactos.forEach(function(c) {

        console.log(c);

        const seleccionado = contactoSeleccionado === c.contacteID;

        html += '<tr '
              + 'onclick="seleccionar(' + c.contacteID + ')" '
              + 'style="cursor:pointer;' 
              + (seleccionado ? 'background-color:#d4edda;' : '')
              + '">';

        html += '<td>' + (seleccionado ? 'X' : '') + '</td>';
        html += '<td>' + (c.contacteID || '') + '</td>';
        html += '<td>' + (c.nom || '') + '</td>';
        html += '<td>' + (c.llinatge1 || '') + '</td>';
        html += '<td>' + (c.llinatge2 || '') + '</td>';
        html += '<td>' + (c.telefon || '') + '</td>';
        html += '<td>' + (c.carrec || '') + '</td>';
        html += '<td>' + (c.username || '') + '</td>';
        html += '<td>' + (c.nombrecompleto || '') + '</td>';
        html += '<td>' + c.numSolicituds + '</td>';

        html += '</tr>';
    });

    html += '</tbody>';
    html += '</table>';

    $('#listaContactos').html(html);
}

function seleccionar(id) {

    console.log("Seleccionado contacto ID:", id);

    contactoSeleccionado = id;

    mostrarGrupo();
}

function siguienteGrupo() {

    console.log("Siguiente grupo");
    console.log("Contacto seleccionado:", contactoSeleccionado);

    if (contactoSeleccionado == null) {

        alert("Selecciona un contacto maestro.");
        return;
    }

    const grupoActual = grupos[current];
    const contactos = grupoActual.contactos;

    fusionPlan.push({
        masterId: contactoSeleccionado,
        mergeIds: contactos
            .map(c => c.contacteID)
            .filter(id => id !== contactoSeleccionado)
    });

    console.log("Plan de fusiones:");
    console.log(fusionPlan);

    contactoSeleccionado = null;

    current++;

    if (current < grupos.length) {

        console.log("Mostrando siguiente grupo");
        mostrarGrupo();

    } else {

        console.log("No quedan grupos");
        mostrarResumen();
    }
}

function anteriorGrupo() {

    if (current > 0) {

        current--;

        contactoSeleccionado = null;

        mostrarGrupo();
    }
}

function saltarGrupo() {

    pendientes.push(grupos[current]);

    current++;

    contactoSeleccionado = null;

    if (current < grupos.length) {
        mostrarGrupo();
    } else {
        mostrarResumen();
    }
}

function mostrarResumen() {

    let html = '';

    html += '<h4>Fusiones a ejecutar</h4>';
    html += '<ul>';

    fusionPlan.forEach(function(f) {

        html += '<li>';
        html += '<b>Master:</b> ' + f.masterId;
        html += ' | ';
        html += '<b>Merge:</b> ' + f.mergeIds.join(', ');
        html += '</li>';
    });

    html += '</ul>';

    html += '<h4>Casos pendientes</h4>';

    html += '<ul>';

    pendientes.forEach(g => {
        html += '<li>'
             + g.nif
             + ' - '
             + g.mail
             + ' ('
             + g.contactos.length
             + ' contactos)'
             + '</li>';
    });

    html += '</ul>';
    
    html += '<button type="button" class="btn btn-success" onclick="ejecutarFusiones()">';
    html += 'Ejecutar fusiones';
    html += '</button>';

    $('#listaContactos').html(html);
}

function ejecutarFusiones() {

    console.log("Enviando plan de fusiones:");
    console.log(fusionPlan);

    $.ajax({

        url: contextPath + '/admin/solicitudactiva/contactes/fusionar',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(fusionPlan),

        success: function(response) {

            console.log("Respuesta:");
            console.log(response);

            alert("Fusiones realizadas");

            $('#modalContactos').modal('hide');
        },

        error: function(xhr, status, error) {

            console.error("Error ejecutando fusiones", error);

            alert("Error realizando las fusiones.");
        }
    });
}

</script>




<%@ include
	file="/WEB-INF/jsp/all/tramitModificacioSolicitudsPublic.jsp"%>

<style>
#modificacioSolicitudForm {
	background: #fff;
	padding: 2rem;
	padding-top: 2rem;
	border-radius: 12px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	position: absolute;
	left: 20rem;
	right: 20rem;
	padding-top: 0;
	margin-top: 2rem;
}

#modificacioSolicitud_tableid {
	margin: 0rem auto;
}

#llistatServeisActuals {
	margin: 2rem auto;
	width: 100%;
}

#llistatServeisActuals table {
	margin: auto;
}

#llistatServeisActuals table th {
	text-align: center;
}

.btn-add-norma {
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 100%;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 1.5rem;
	height: 1.5rem;
	font-size: 12px;
}

.btn-add-norma:hover {
	background-color: #218838;
}

#modalNorma {
	display: none; /* Ya lo tienes */
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	/* Fondo oscuro semitransparente */
	z-index: 999; /* Asegura que esté encima */
}

.modal-content {
	background: white;
	border-radius: 6px;
	max-width: 500px;
	padding: 20px;
	margin: 10% auto;
	position: relative;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
}

.titolSection {
	text-align: left !important;
	padding-top: 2rem !important;
	font-size: 1.25rem !important;
	font-weight: bold !important;
}

.lead:first-child {
	text-align: center;
	margin: 10px;
}

.navbar-form {
	margin-top: 3rem;
}

#titolModifServ {
	font-size: 1.25rem !important;
	font-weight: bold !important;
}

#instructions {
	border: 1px solid darkgray;
	padding: 1rem;
	border-radius: 5px;
	background: #fbfbfb;
	box-shadow: 2px 2px darkgray;
	width: fit-content;
	margin: auto;
	max-width: 65rem;
}
</style>

<script type="text/javascript">
	document.getElementById("modificacioSolicitud_tableid").classList = "tdformlabel table-sm";

	document.getElementById("modificacioSolicitud.procedimentCodi").readOnly = "readOnly";
	document.getElementById("modificacioSolicitud.dataInici").readOnly = "readOnly";
/* 	
	document.getElementById("modificacioSolicitud.dataFi").readOnly = "readOnly";
 */
/* 	document.getElementById("modificacioSolicitud_estatID").setAttribute(
			"disabled", "disabled"); */
	document.getElementById("modificacioSolicitud_organID").setAttribute(
			"disabled", "disabled");

	$(function() {

		$(".module_content").prepend($("#instructions"));

		$(".module_content").append($("#llistatServeisActuals"));
		$(".module_content").append($("#includedContentSolicitudServei"));

		$("#includedContentSolicitudServei").load("<c:url value="/public/solicitudservei/list/1?solicitudID=${modificacioSolicitudForm.modificacioSolicitud.solicitudID}" />");

	});
</script>
	
	<c:if test="${not empty instructions}">
	    <div id="instructions">
	        <c:out value="${instructions}" escapeXml="false"/>
	    </div>
	</c:if>


	<div id="includedContentSolicitudServei"></div>

	<div id="llistatServeisActuals">
		<!-- Tabla de Servicios -->
		<div id="titolModifServ">Modificar Serveis</div>
		<c:if test="${empty serveis}">
			<p>No hi ha serveis disponibles.</p>
		</c:if>
		<c:if test="${not empty serveis}">
			<table border="1" class="tdformlabel table-sm">
				<thead>
					<tr>
						<th>Codi</th>
						<th>Nom</th>
						<th>Estat</th>
						<th>Normes</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="servei" items="${serveis}">
						<tr>
							<td>${servei.codi}</td>
							<td>${servei.nom}</td>
							<td>${servei.estat}</td>
							<td>${servei.normes}</td>
							<td>
								<button class="btn-add-norma" type="button"
									data-servei-id="${servei.id}">
									<i class="fas fa-plus"></i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>



			<table>
				<tr id="titolProcediment" class="titolRow">
					<td colspan="2" class="titolSection">Dades Procediment</td>
				</tr>

				<tr id="titolResponsable" class="titolRow">
					<td colspan="2" class="titolSection">Dades Responsable</td>
				</tr>

				<tr id="titolConsentiment" class="titolRow">
					<td colspan="2" class="titolSection">Dades Consentiment</td>
				</tr>
			</table>


			<script>
  $(document).ready(function () {

	  $("#submitNormaBtn").click(function (e) {
		  e.preventDefault();
		  $("#formNorma")[0].submit(); // Hace submit al form del modal
		});

    $(".btn-add-norma").click(function () {
      const serveiId = $(this).data("servei-id");
      $("#serveiId").val(serveiId);
      $("#modalNorma").fadeIn();
    });

    $("#closeModal").click(function () {
      $("#modalNorma").fadeOut();
    });

    $(window).click(function (event) {
      if ($(event.target).is("#modalNorma")) {
        $("#modalNorma").fadeOut();
      }
    });
    

    $("#titolProcediment").insertBefore("#modificacioSolicitud_procedimentCodi_rowid");
    $("#titolResponsable").insertBefore("#modificacioSolicitud_responsableProcNom_rowid");
    $("#titolConsentiment").insertBefore("#modificacioSolicitud_consentiment_rowid");

  });
  
	function submitForm() {
		console.log('submitForm');

	    const consentiment = document.getElementById('modificacioSolicitud_consentiment').value;

	    if (!consentiment) {
	      alert("S'ha de selecionar un tipus de consentiment.");
	      return false; // evita el submit
	    }else{
		}

	    document.getElementById('modificacioSolicitudForm').submit();
	  }

	function cancelarForm() {
		  console.log('cancelarForm');
		  window.location.href = "https://www.caib.es/sites/interoperabilitat/ca/inici_interoperabilitat/?campa=yes";
		}

</script>

			<script>
/* $(document).ready(function () {
	  const $selectCaduca = $("#solicitudServei_caduca");
	  const $fechaInput = $("#solicitudServei\\.fechaCaduca");

	  function actualizarCampoFecha() {
	    const valor = $selectCaduca.val();

	    console.log(valor);
	    console.log
	    
	    if (valor === "Caduca") {
	      $fechaInput.attr("type", "date");
	      $fechaInput.prop("disabled", false);
	      $fechaInput.show();
	    } else {
	      $fechaInput.val("");
	      $fechaInput.attr("type", "text");
	      $fechaInput.prop("disabled", true);
	      $fechaInput.hide();
	    }
	  }

	  // Asignar función al cambio del select SIN borrar otros handlers
	  $selectCaduca.on("change", actualizarCampoFecha);

	  // Ejecutar al cargar para establecer el estado inicial
	  actualizarCampoFecha();
	});
 */
</script>


		</c:if>
	</div>


	</form>

	<form id="formNorma" method="post" enctype="multipart/form-data"
		action="/pinbaladmin/public/modificarsolicitud/afegirNorma">

		<!-- Modal -->
		<div id="modalNorma" class="modal" style="display: none;">
			<div class="modal-content"
				style="padding: 20px; border: 1px solid #ccc; background: white; width: 400px; margin: 100px auto; position: relative;">
				<span id="closeModal"
					style="position: absolute; top: 10px; right: 10px; cursor: pointer;">×</span>


				<input type="hidden" name="serveiId" id="serveiId" value="">

				<div class="form-group">
					<label for="norma"><fmt:message
							key="solicitudServei.normaLegal" /></label> <input type="text"
						name="norma" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="fitxer"><fmt:message
							key="solicitudServei.fitxernormaID" /></label> <input type="file"
						name="fitxer" class="form-control" required>
				</div>

				<div class="form-group">
					<label for="articles"><fmt:message
							key="solicitudServei.articles" /></label> <input type="text"
						name="articles" class="form-control" required>
				</div>

				<button type="button" id="submitNormaBtn" class="btn btn-primary">
					<fmt:message key="afegirnorma" />
				</button>

			</div>
		</div>
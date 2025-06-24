

<c:if test="${not empty isPublic}">
	<%@ include
		file="/WEB-INF/jsp/all/tramitModificacioSolicitudsPublic.jsp"%>
		
<style>
#solicitudServeiForm {
	background: #fff;
	padding: 2rem;
	padding-top: 2rem;
	border-radius: 12px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	position: absolute;
	left: 20rem;
	right: 20rem;
}

</style>		
		
</c:if>


<!-- AFEGIR NORMES A UN SERVEI -->
<div id="botones-normas">
	<div id="afegirNorma" class="btn-norma btn">Afegir una altra norma</div>
	<div id="eliminarNorma" class="btn-norma btn">Eliminar darrera norma</div>
</div>

<style>
#botones-normas {
	margin: 1rem 0;
	text-align: right;
}

.btn-norma {
	margin-left: 3px;
}

#afegirNorma {
	background-color: #4be16d;
}

#eliminarNorma {
	background-color: #ea8f3e;
}
</style>

<script type="text/javascript">

	$(document).ready(function() {
	
		var totalNormes = 3;
		
		$(".tab_container").append($("#botones-normas"));
		
		$("#seccio_norma1").show();
		$("#seccio_norma2").hide();
		$("#seccio_norma3").hide();

		$("#eliminarNorma").hide();
		
		var normesAfegides = ${normesAfegides};
		
		console.log(normesAfegides);
		testNormes();

		
		
		function testNormes() {
			console.log("añadriermos " + normesAfegides + " normas");
			for (var i = 1; i <= totalNormes; i++) {
				if (i <= normesAfegides) {
					$("#seccio_norma" + i).show();
				} else {
					$("#seccio_norma" + i).hide();
				}
			}
			//Si solo hay una norma, no se puede eliminar, y si hay 3, no se puede añadir
			if (normesAfegides > 1) {
                $("#eliminarNorma").show();
            } else {
                $("#eliminarNorma").hide();
            }
			if (normesAfegides < totalNormes) {
                $("#afegirNorma").show();
            } else {
                $("#afegirNorma").hide();
            }
		}
	
		$("#afegirNorma").click(function() {
			normesAfegides++;
			if (normesAfegides > totalNormes) {
	            normesAfegides = totalNormes;
	        }
			testNormes();
		});
	
		$("#eliminarNorma").click(function() {
			normesAfegides--;
			if (normesAfegides < 1) {
	            normesAfegides = 1;
	            
	        }
			testNormes();
		});
		
		
		function preValidate() {
			  const fileInput = document.getElementById("fitxernormaID");
			  const caducaSelect = document.getElementById("solicitudServei_caduca");
			  const fechaCaducaInput = document.getElementById("solicitudServei.fechaCaduca");
			  const normaLegalTextarea = document.getElementById("solicitudServei.normaLegal");

			  // Verificar si hay un fichero cargado o existente
			  const hasNewFile = fileInput.files.length > 0;
			  const existingFileLink = document.querySelector("#solicitudServei_fitxernormaID_columnvalueid a");
			  const hasExistingFile = existingFileLink !== null && existingFileLink.href !== "";
			  const hasFile = hasNewFile || hasExistingFile;

			  // Verificar si norma legal tiene contenido
			  const hasNormaLegal = normaLegalTextarea.value.trim() !== "";

			  // Verificar campo fecha si caduca = "Caduca"
			  const caducaValue = caducaSelect.value.trim();
			  const hasCaducaDate = caducaValue === "Caduca" ? fechaCaducaInput.value.trim() !== "" : true;

			  // Si falta algo, construir el mensaje de error
			  if (!hasFile || !hasNormaLegal || !hasCaducaDate) {
			    const missingFields = [];
			    if (!hasFile) missingFields.push("Fichero");
			    if (!hasNormaLegal) missingFields.push("Norma legal");
			    if (!hasCaducaDate) missingFields.push("Fecha de caducidad");

			    alert("El formulario está sin rellenar. Faltan: " + missingFields.join(", "));
			    return false;
			  }

			  return true;
			}


		// onsubmit, poner vacíos los campos de normas que no estén visibles
		$("form").submit(function(event) {
			console.log("submit");

			if (!preValidate()) {
				event.preventDefault(); // Detener el envío
				return false;
			}

			for (var i = normesAfegides + 1; i <= totalNormes; i++) {
				document.getElementById("solicitudServei.norma" + i).value = "none";
			}
		});

		  const $selectCaduca = $("#solicitudServei_caduca");
		  const $fechaInput = $("#solicitudServei\\.fechaCaduca");
	
		  function actualizarCampoFecha() {
		    const valor = $selectCaduca.val().trim();
	
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
</script>
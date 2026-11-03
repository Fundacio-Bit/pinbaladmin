<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ page import="org.fundaciobit.pinbaladmin.back.controller.all.PinfoDataPublicController"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<%--
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seleccionar responsable</title>
--%>
<style>
/* Contenedor principal */
.container-responsable {
	max-width: 900px;
	margin: 2rem auto;
	padding: 2rem;
	border-radius: 12px;
	background: white;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
	border: none;
}

/* Headers usan clases estándar de tramitPinfoPublic.jsp */

/* Botón principal - Generar PDF */
.btn-primary-custom {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%) !important;
	color: white !important;
	padding: 10px 24px;
	border: none !important;
	border-radius: 8px;
	font-size: 14px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.2s;
	text-decoration: none !important;
	display: inline-flex;
	align-items: center;
	gap: 8px;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.2) !important;
}

.btn-primary-custom:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%) !important;
	color: white !important;
	text-decoration: none !important;
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3) !important;
	transform: translateY(-1px);
}

/* Campo de búsqueda */
.filter-container {
	margin-bottom: 1.5rem;
}

.filter-container label {
	font-size: 14px;
	font-weight: 600;
	color: #2d3748;
	margin-bottom: 0.5rem;
	display: block;
}

.filter-container label i {
	color: #4DBA79;
	margin-right: 6px;
}

.filter-container input {
	width: 100%;
	padding: 12px 16px;
	border: 1px solid #ddd;
	border-radius: 8px;
	font-size: 14px;
	transition: all 0.2s;
	box-sizing: border-box;
}

.filter-container input:focus {
	border-color: #4DBA79;
	outline: none;
	box-shadow: 0 0 0 3px rgba(77, 186, 121, 0.1);
}

/* Contenidor de resultats de la cerca (autocomplete) */
.filter-container {
	position: relative;
}

#autocomplete-responsables {
	display: block;
	position: absolute;
	z-index: 1000;
	background-color: white;
	border: none;
	border-radius: 12px;
	max-height: 280px;
	overflow-y: auto;
	width: 100%;
	box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
	margin-top: 8px;
}

#autocomplete-responsables.hidden {
	display: none;
}

/* Scrollbar personalizado */
#autocomplete-responsables::-webkit-scrollbar {
	width: 8px;
}

#autocomplete-responsables::-webkit-scrollbar-track {
	background: #f1f1f1;
	border-radius: 4px;
}

#autocomplete-responsables::-webkit-scrollbar-thumb {
	background: #c1c1c1;
	border-radius: 4px;
}

#autocomplete-responsables::-webkit-scrollbar-thumb:hover {
	background: #a8a8a8;
}

/* Item d'un resultat de cerca */
.responsable-item {
	cursor: pointer;
	padding: 14px 18px;
	background-color: white;
	border-radius: 0;
	border: none;
	border-bottom: 1px solid #f8f9fa;
	transition: all 0.2s;
	font-size: 14px;
}

.responsable-item:first-child {
	border-radius: 12px 12px 0 0;
}

.responsable-item:last-child {
	border-radius: 0 0 12px 12px;
	border-bottom: none;
}

.responsable-item:hover {
	background: linear-gradient(90deg, #f0f9f4 0%, #f8f9fa 100%);
	border-left: 3px solid #4DBA79;
	padding-left: 15px;
}

/* Requadre del responsable seleccionat */
#responsable-seleccionat-wrapper {
	margin-bottom: 1.5rem;
}

#responsable-seleccionat-wrapper.hidden {
	display: none;
}

#responsable-seleccionat-wrapper label {
	font-size: 14px;
	font-weight: 600;
	color: #2d3748;
	margin-bottom: 0.5rem;
	display: block;
}

#responsable-seleccionat-wrapper label i {
	color: #4DBA79;
	margin-right: 6px;
}

.responsable-seleccionat-box {
	padding: 12px 16px;
	border-radius: 8px;
	border: 1px solid #4DBA79;
	background: #f0f9f4;
	font-size: 14px;
	color: #2d3748;
	font-weight: 600;
}

.spinner {
	margin: 14px 18px;
	color: #666;
	font-size: 13px;
}
</style>
<%--
</head>
<body>
 --%>
	<div class="container-responsable">
		<div class="titol-tramit-pinfo-header">
			<div class="titol-tramit-pinfo-container">
				<h3 class="titol-tramit-pinfo"><i class="fas fa-user-check"></i> <fmt:message key="tramit.pinfo.responsable.seleccionar"/></h3>
			</div>
			<div class="titol-tramit-pinfo-botonera">
				<a class="btn-primary-custom"
					onclick="submitForm();"
					title="<fmt:message key="generar.pdf"/>">
					<i class="fas fa-file-pdf"></i> <fmt:message key="generar.pdf" />
				</a>
			</div>
		</div>

		<!-- Avís sobre el responsable -->
		<div class="aviso-info">
			<h5><i class="fas fa-question-circle"></i> Informació sobre la signatura</h5>
			<p style="margin-bottom: 0;">
				<fmt:message key="tramit.pinfo.responsable.ajuda"/>
			</p>
		</div>

		<div class="filter-container">
			<label for="filtro-responsables">
				<i class="fas fa-search"></i> <fmt:message key="tramit.pinfo.responsable.buscar"/>
			</label>
            <%
              request.setAttribute("MINIM_CARACTERS_CERCA_RESPONSABLE", PinfoDataPublicController.MINIM_CARACTERS_CERCA_RESPONSABLE);
            %>            
			<input type="text" id="filtro-responsables" autocomplete="off"
				placeholder="<fmt:message key="tramit.pinfo.responsable.placeholder"><fmt:param>${MINIM_CARACTERS_CERCA_RESPONSABLE}</fmt:param></fmt:message>" />
			<div id="autocomplete-responsables" class="hidden"></div>
		</div>

		<div id="responsable-seleccionat-wrapper" class="hidden">
			<label><i class="fas fa-user-check"></i> <fmt:message key="tramit.pinfo.responsable.seleccionat"/></label>
			<div id="responsable-seleccionat-info" class="responsable-seleccionat-box"></div>
		</div>

		<form id="formResponsable" action="seleccionarResponsable" method="POST">
			<input type="hidden" id="responsable" name="responsable" value="" />
		</form>
	</div>

	<script>

	// URL de cerca d'usuaris (ve del controlador, paràmetre GET "nom")
	var urlSearchUser = "${urlSearchUser}";

	// Nombre mínim de caràcters per iniciar la cerca
	var MINIM_CARACTERS_CERCA = <%= PinfoDataPublicController.MINIM_CARACTERS_CERCA_RESPONSABLE %>;

	// Construeix el text a mostrar per un usuari retornat pel servidor
	function textUsuari(usuari) {
		var text = ofuscarNIF(usuari.administrationID) + " - " + usuari.name + " " + usuari.surname1;
		if (usuari.surname2 && usuari.surname2.trim() !== "") {
			text += " " + usuari.surname2;
		}
		text += " - " + usuari.username + " - " + usuari.email;
		return text;
	}
	
	function ofuscarNIF(nif) {
	    if (nif == null || nif.length <= 4) {
	        return nif; // Si es null o muy corto, no ofuscar
	    }
	    return nif.substring(0, 2) + "*".repeat(4) + nif.substring(nif.length - 3);
	}


	// Afegeix un item a la llista de resultats de la cerca
	function afegirResponsableAutocomplete(usuari) {
		var item = $('<div>', { "class": "responsable-item" }).text(textUsuari(usuari));
		item.on('click', function() {
			seleccionarResponsable(usuari);
		});
		$('#autocomplete-responsables').append(item);
	}

	// Guarda el NIF del responsable seleccionat a l'input hidden i el mostra
	function seleccionarResponsable(usuari) {
		$('#responsable').val(usuari.administrationID);
		$('#responsable-seleccionat-info').text(textUsuari(usuari));
		$('#responsable-seleccionat-wrapper').removeClass('hidden');

		// Tancar i buidar la llista de resultats i el camp de cerca
		$('#autocomplete-responsables').empty().addClass('hidden');
		$('#filtro-responsables').val('');
	}

	$(document).ready(function(){

		var debounceTimer;

		$('#filtro-responsables').on('input', function(){
			clearTimeout(debounceTimer);

			var filtro = $(this).val();

			debounceTimer = setTimeout(function(){
				if (filtro.length < MINIM_CARACTERS_CERCA) {
					$('#autocomplete-responsables').empty().addClass('hidden');
					return;
				}

				// Mostrar indicador de càrrega
				$('#autocomplete-responsables').html("<div class='spinner'><fmt:message key="tramit.pinfo.responsable.cercant"/></div>").removeClass('hidden');

				$.ajax({
					url: urlSearchUser,
					method: "GET",
					dataType: "json",
					data: { nom: filtro },
					success: function(usuaris) {
						$('#autocomplete-responsables').empty();

						if (usuaris == null || usuaris.length === 0) {
							$('#autocomplete-responsables').html("<div style='padding: 14px 18px; color: #666;'><fmt:message key="tramit.pinfo.responsable.notrobats"/></div>").removeClass('hidden');
						} else {
							$.each(usuaris, function(i, usuari) {
								afegirResponsableAutocomplete(usuari);
							});
							$('#autocomplete-responsables').removeClass('hidden');
						}
					},
					error: function() {
						$('#autocomplete-responsables').html("<div style='padding: 14px 18px; color: red;'><fmt:message key="tramit.pinfo.responsable.errorcerca"/></div>").removeClass('hidden');
					}
				});
			}, 500);
		});

		// Tancar la llista de resultats si es clica fora
		$(document).on('click', function(e){
			if (!$(e.target).closest('.filter-container').length) {
				$('#autocomplete-responsables').empty().addClass('hidden');
			}
		});
	});

	function submitForm() {
		console.log('submitForm');

		var responsableSeleccionat = $('#responsable').val();

		if (!responsableSeleccionat) {
			alert('Ha de seleccionar un responsable abans d´enviar el formulari.');
		} else {
			document.getElementById('formResponsable').submit();
		}
	}
	</script>
    
    <%--
    
</body>
</html>  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<!-- =============================================
     WIZARD DE TRAMITACIÓN
     ============================================= -->
		<c:if test="${not empty wizardInfo}">
			<div id="wizard-tramitacion" class="wizard-container">
				<div class="wizard-header">
					<div class="wizard-title-block">
						<strong>SOLICITUD: ${procedimentCodi}</strong>
						<c:if test="${not empty procedimentCodi}">
							<span class="wizard-procediment">#${solicitudID}</span>
						</c:if>
					</div>
					<div class="wizard-controls">
						<button type="button" class="btn-wizard-control" id="btnExpandirTot">
							<i class="fas fa-expand-alt"></i>
							<fmt:message key="solicitud.wizard.expandir.tot" />
						</button>
						<button type="button" class="btn-wizard-control" id="btnContraureTot">
							<i class="fas fa-compress-alt"></i>
							<fmt:message key="solicitud.wizard.contraure.tot" />
						</button>
					</div>
				</div>

				<div class="wizard-progress">
					<c:forEach var="estado" items="${wizardInfo.estados}" varStatus="status">
						<c:if test="${status.index > 0}">
							<div class="estado-connector"></div>
						</c:if>
						<div class="estado-item ${estado.cssClass}" data-estat-id="${estado.id}"
							title="${estado.descripcion}">
							<div class="estado-icon"><i class="${estado.icono}"></i></div>
							<div class="estado-label">${estado.label}</div>
						</div>
					</c:forEach>
				</div>

				<div class="wizard-footer">
					<div class="wizard-estado-info">
						<span class="wizard-label">
							<fmt:message key="solicitud.wizard.estat.actual" />:
						</span>
						<span class="wizard-valor">${wizardInfo.estadoActualNombre}</span>
					</div>
					<div class="wizard-fase-info">
						<span class="wizard-label">
							<fmt:message key="solicitud.wizard.fase" />:
						</span>
						<span class="wizard-valor">
							<c:if test="${wizardInfo.faseActual > wizardInfo.totalFases}">
								<fmt:message key="solicitud.wizard.fase.totes.completades" />
							</c:if>
							<c:if test="${wizardInfo.faseActual <= wizardInfo.totalFases}">
								${wizardInfo.faseActual} de ${wizardInfo.totalFases}
							</c:if>
						</span>
					</div>
					<c:if test="${wizardInfo.errorState}">
						<div class="wizard-estado-alert">
							<i class="fas fa-exclamation-triangle"></i>
							<fmt:message key="solicitud.wizard.estado.requiere.atencion" /><br>
							<c:if test="${not empty missatgeError}">${missatgeError}</c:if>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>


		<style>
			/* ===== CONTENEDOR PRINCIPAL ===== */
			.wizard-container {
				background: #ffffff;
				border: 1px solid #e0e0e0;
				border-radius: 8px;
				padding: 24px;
				margin: 20px 0 30px 0;
				box-shadow: 0 1px 3px rgba(0, 0, 0, 0.06);
			}

			/* ===== HEADER ===== */
			.wizard-header {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 24px;
				padding-bottom: 16px;
				border-bottom: 2px solid #007bff;
				flex-wrap: wrap;
				gap: 12px;
			}

			.wizard-title-block {
				display: flex;
				align-items: center;
				gap: 10px;
				font-size: 1.15rem;
				color: #333;
			}

			.wizard-title-block strong {
				font-weight: 600;
				color: #007bff;
			}

			.wizard-procediment {
				background: #007bff;
				color: #fff;
				padding: 3px 10px;
				border-radius: 4px;
				font-size: 0.8rem;
				font-weight: 500;
			}

			.wizard-controls {
				display: flex;
				gap: 8px;
			}

			.btn-wizard-control {
				background: #fff;
				border: 1px solid #007bff;
				color: #007bff;
				padding: 6px 14px;
				border-radius: 4px;
				cursor: pointer;
				font-size: 0.85rem;
				font-weight: 500;
				transition: all 0.2s ease;
				display: flex;
				align-items: center;
				gap: 6px;
			}

			.btn-wizard-control:hover {
				background: #007bff;
				color: #fff;
			}

			.btn-wizard-control i {
				font-size: 0.85rem;
			}

			/* ===== BARRA DE PROGRESO ===== */
			.wizard-progress {
				display: flex;
				align-items: center;
				justify-content: center;
				margin: 28px 0;
				padding: 24px 16px;
				background: #fafafa;
				border-radius: 6px;
				flex-wrap: nowrap;
				overflow-x: auto;
			}

			/* ===== ESTADOS ===== */
			.estado-item {
				display: flex;
				flex-direction: column;
				align-items: center;
				gap: 10px;
				min-width: 90px;
				max-width: 110px;
				text-align: center;
			}

			.estado-icon {
				font-size: 1.5rem;
				width: 3rem;
				height: 3rem;
				display: flex;
				align-items: center;
				justify-content: center;
				background: #fff;
				border-radius: 50%;
				border: 2px solid #dee2e6;
				transition: all 0.2s ease;
				position: relative;
				z-index: 2;
			}

			.estado-icon i {
				font-size: 1.4rem;
			}

			/* ESTADO COMPLETADO (Verde) */
			.estado-item.completado .estado-icon {
				color: #28a745;
				border-color: #28a745;
			}

			/* ESTADO ACTUAL (Azul) */
			.estado-item.actual .estado-icon {
				color: #007bff;
				border-color: #007bff;
				border-width: 3px;
			}

			/* ESTADO ERROR (Rojo) */
			.estado-item.error .estado-icon {
				color: #dc3545;
				border-color: #dc3545;
			}

			/* ESTADO PENDIENTE (Gris) */
			.estado-item.pendiente .estado-icon {
				border-color: #dee2e6;
				color: #adb5bd;
			}

			/* ===== ETIQUETAS ===== */
			.estado-label {
				font-size: 0.7rem;
				font-weight: 600;
				text-transform: uppercase;
				letter-spacing: 0.3px;
				color: #666;
				line-height: 1.3;
				max-width: 95px;
				word-wrap: break-word;
			}

			.estado-item.actual .estado-label {
				color: #007bff;
				font-weight: 700;
			}

			.estado-item.completado .estado-label {
				color: #28a745;
			}

			.estado-item.error .estado-label {
				color: #dc3545;
			}

			.estado-item.pendiente .estado-label {
				color: #999;
			}

			/* ===== CONECTORES ===== */
			.estado-connector {
				width: 36px;
				height: 2px;
				background: #dee2e6;
				margin: 0 -4px;
				align-self: center;
				margin-top: -28px;
				position: relative;
				z-index: 1;
			}

			/* ===== FOOTER ===== */
			.wizard-footer {
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding-top: 16px;
				border-top: 1px solid #e0e0e0;
				flex-wrap: wrap;
				gap: 12px;
				margin-top: 16px;
			}

			.wizard-estado-info,
			.wizard-fase-info {
				display: flex;
				align-items: center;
				gap: 8px;
				font-size: 0.9rem;
				background: #f8f9fa;
				padding: 8px 14px;
				border-radius: 4px;
			}

			.wizard-label {
				color: #666;
				font-weight: 500;
			}

			.wizard-valor {
				color: #007bff;
				font-weight: 600;
			}

			/* ===== ALERTA DE ERROR ===== */
			.wizard-estado-alert {
				width: 100%;
				margin-top: 12px;
				padding: 10px 16px;
				background: #fff3cd;
				border: 1px solid #ffc107;
				border-left: 4px solid #ffc107;
				border-radius: 4px;
				color: #856404;
				font-size: 0.875rem;
				font-weight: 500;
				display: flex;
				align-items: center;
				gap: 8px;
			}

			.wizard-estado-alert i {
				font-size: 1.1rem;
			}

			/* ===== RESPONSIVE ===== */
			@media (max-width: 992px) {
				.wizard-progress {
					padding: 20px 12px;
				}

				.estado-item {
					min-width: 80px;
					max-width: 95px;
				}

				.estado-icon {
					width: 52px;
					height: 52px;
				}

				.estado-icon i {
					font-size: 1.3rem;
				}

				.estado-connector {
					width: 30px;
				}
			}

			@media (max-width: 768px) {
				.wizard-container {
					padding: 18px;
				}

				.wizard-header {
					flex-direction: column;
					align-items: flex-start;
				}

				.wizard-progress {
					overflow-x: auto;
					justify-content: flex-start;
					padding: 18px 10px;
				}

				.estado-item {
					min-width: 75px;
				}

				.estado-icon {
					width: 50px;
					height: 50px;
				}

				.estado-connector {
					min-width: 24px;
				}

				.wizard-footer {
					flex-direction: column;
					align-items: flex-start;
				}
			}
		</style>

		<script>
			$(document).ready(function () {
				//Colocar #wizard-tramitacion antes .module_content
				$('#wizard-tramitacion').insertBefore('.module_content');



				// Botón expandir todo
				$('#btnExpandirTot').on('click', function () {
					$('[id^="seccio_"]').each(function () {
						var $wrapper = $(this).find('.seccio-content-wrapper');
						var $ocultarSpan = $(this).find('.toggle-ocultar');
						var $expandirSpan = $(this).find('.toggle-expandir');

						if ($wrapper.is(':hidden')) {
							$wrapper.slideDown(300);
							$ocultarSpan.show();
							$expandirSpan.hide();
						}
					});
				});

				// Botón contraer todo
				$('#btnContraureTot').on('click', function () {
					$('[id^="seccio_"]').each(function () {
						var $wrapper = $(this).find('.seccio-content-wrapper');
						var $ocultarSpan = $(this).find('.toggle-ocultar');
						var $expandirSpan = $(this).find('.toggle-expandir');

						if ($wrapper.is(':visible')) {
							$wrapper.slideUp(300);
							$ocultarSpan.hide();
							$expandirSpan.show();
						}
					});
				});
			});
		</script>

		<script>
			$(".fa-bullhorn").parent().insertBefore($("#solicitudForm").prev());
			$(".fa-bullhorn").parent().css("float", "right");



			var botonsPerPujar = [
				"/altapinbal/vistaprevia/alta/",
				"/altapinbal/consultaestado/",
				"/altapinbal/vistaprevia/modificacio/",
				"/rebreSolicitud/",
				"/enviarAFirmarTitular/",
				"/afegirFormulariFirmat/",
				"/solicitudestatal/enviarcorreucedents/",
				"/acceptarModificacio",
				"/infoMadrid/view",
				"/crearOActualitzarSolicitud"

			]

			for (var i = 0; i < botonsPerPujar.length; i++) {
				var elem = $('a[href*="' + botonsPerPujar[i] + '"]');

				$(elem).insertBefore($("#solicitudForm").prev());
				$(elem).css("float", "right");
				$(elem).css("margin-right", "1rem");
			}
		</script>

		<!-- Canvi de tamany de les columnes de la vista d'una sol·licitud -->
		<script>
			/* 	var tabla = document.getElementById("solicitud_tableid");
			
				var trs = tabla.children[0].children;
			
				for (let i = 0; i < trs.length; i++) {
					var tds = trs[i].children;
					tds[0].style.width = "30%";
					//  tds[1].style.width = "80%";
				}
			 */
			var textArea = $("textarea")[0];
			textArea.parentElement.style.width = "fit-content";

		</script>

		<style>
			.tdformlabel tbody td:nth-child(1) {

				width: 170px;

			}
		</style>


		<!-- Canviar visualitzacio de la jerarquia -->

		<c:if test="${isView == 'true'}">
			<script>
				var inputElement = document.getElementById('solicitud.organid').nextSibling;
				var inputValue = inputElement.value;

				// Dividir el texto en l�neas
				var lineas = inputValue.split('|');

				console.log(inputValue);
				console.log(lineas);
				// Crear un nuevo texto con guiones por jerarquia
				var textoConGuiones = '';
				var nivel = 0;
				for (var i = 0; i < lineas.length; i++) {
					var guionesLinea = "";
					if (i != 0) {
						guionesLinea = "    " + " ".repeat(12).repeat(nivel - 1)
							+ String.fromCharCode(9492)
							+ String.fromCharCode(9472).repeat(2) + " ";
					}

					textoConGuiones += guionesLinea + lineas[lineas.length - 1 - i]
						+ '\n';
					nivel++;
				}

				var textareaElement = document.createElement('textarea');
				textareaElement.value = textoConGuiones;
				textareaElement.id = 'miTextarea';
				textareaElement.readOnly = true;
				textareaElement.classList = inputElement.classList;
				//   textareaElement.style.fontFamily = "Courier";
				textareaElement.rows = lineas.length;

				// Reemplazar el input con el textarea
				inputElement.parentNode.replaceChild(textareaElement, inputElement);
			</script>
		</c:if>


		<c:if test="${desplegableOrgans == 'true'}">
			<script>
				document.addEventListener("DOMContentLoaded", function () {
					// Obtener el select existente          solicitud_organid
					const select = document.getElementById("solicitud_organid");

					// Crear un campo de entrada de texto
					const input = document.createElement("input");
					input.type = "search";
					input.id = "autocomplete-input";
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
					input.addEventListener("input", function () {
						const value = input.value.trim().toLowerCase();
						const options = select.options;

						// Limpiar el desplegable
						dropdown.innerHTML = "";

						// Mostrar todas las opciones si el campo de entrada está vacío
						if (value === "") {
							for (let i = 0; i < options.length; i++) {
								dropdown.appendChild(createDropdownOption(options[i]));
							}
						} else {
							// Filtrar y mostrar las opciones que coincidan con el valor ingresado
							for (let i = 0; i < options.length; i++) {
								const optionText = options[i].textContent.toLowerCase();
								if (optionText.includes(value)) {
									dropdown.appendChild(createDropdownOption(options[i]));
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
					dropdown.addEventListener("click", function (e) {
						if (e.target && e.target.tagName === "DIV") {
							const optionText = e.target.textContent;
							input.value = optionText;
							select.value = e.target.value;
							dropdown.style.display = "none";
							select.dispatchEvent(new Event("change")); // Disparar evento "change" en el select
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


					$(document).click(function (event) {
						const $target = $(event.target);

						if (!$target.is(input) && $target.closest("#autocomplete-dropdown").length === 0) {

							const selected = select.selectedOptions[0];
							if (selected.value.length > 0) {
								input.value = selected.innerHTML;
							} else {
								input.value = "";
							}
							dropdown.style.display = "none";
						}
					});
				});
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
			</style>

		</c:if>


		<!-- =============================================
     BOTONES EXPANDIR/COLAPSAR SECCIONES
     ============================================= -->
		<script>
			// Textos traducibles
			var txtOcultar = 'Ocultar';
			var txtExpandir = 'Expandir';

			$(document).ready(function () {
				// Buscar todas las secciones creadas por sections.jsp
				$('[id^="seccio_"]').each(function () {
					var $seccio = $(this);
					var seccioId = $seccio.attr('id');

					// Buscar el título de la sección (label o h4/h3/h2)
					var $titol = $seccio.find('> label, > h4, > h3, > h2').first();

					if ($titol.length > 0) {
						// Buscar la tabla de la sección
						var $taula = $seccio.find('table').first();

						if ($taula.length > 0) {
							// Envolver la tabla en un div para que slideToggle funcione correctamente
							// (slideToggle no funciona bien directamente sobre elementos table)
							if (!$taula.parent().hasClass('seccio-content-wrapper')) {
								$taula.wrap('<div class="seccio-content-wrapper"></div>');
							}
							var $wrapper = $taula.parent('.seccio-content-wrapper');

							// Crear contenedor flex para el título
							$titol.css({
								'display': 'flex',
								'justify-content': 'space-between',
								'align-items': 'center',
								'cursor': 'pointer',
								'padding': '5px 10px',
								'margin-bottom': '10px'
							});

							// Crear contenedor para los iconos y textos
							var $toggleContainer = $('<span>', {
								class: 'toggle-container',
								style: 'margin-left: 10px; display: flex; align-items: center; gap: 5px; cursor: pointer;'
							});

							// Crear contenedor para "Ocultar" (visible al inicio)
							var $ocultarSpan = $('<span>', {
								class: 'toggle-ocultar',
								style: 'display: flex; align-items: center; gap: 5px; font-size: 0.9rem;'
							});

							var $iconUp = $('<i>', {
								class: 'fas fa-chevron-up',
								style: 'font-size: 1rem;'
							});

							$ocultarSpan.append($iconUp).append(txtOcultar);

							// Crear contenedor para "Expandir" (oculto al inicio)
							var $expandirSpan = $('<span>', {
								class: 'toggle-expandir',
								style: 'display: none; align-items: center; gap: 5px; font-size: 0.9rem;'
							});

							var $iconDown = $('<i>', {
								class: 'fas fa-chevron-down',
								style: 'font-size: 1rem;'
							});

							$expandirSpan.append($iconDown).append(txtExpandir);

							// Añadir ambos spans al contenedor
							$toggleContainer.append($ocultarSpan).append($expandirSpan);

							// Envolver el texto del título en un span si no está envuelto ya
							var titolText = $titol.html();
							$titol.html('<span style="flex: 1;">' + titolText + '</span>');

							// Añadir el contenedor al título
							$titol.append($toggleContainer);

							// Funcionalidad de toggle
							$titol.on('click', function (e) {
								e.preventDefault();

								$wrapper.slideToggle(300);

								// Alternar visibilidad de los contenedores
								$(this).find('.toggle-ocultar').toggle();
								$(this).find('.toggle-expandir').toggle();
							});
						}
					}
				});
				
				//Al cargar la pagina, ocultar la seccion 'per_esborrar'.
				$('#seccio_per_esborrar .seccio-content-wrapper').hide();
			});
		</script>

		<style>
			/* Estilos para las secciones colapsables */
			[id^="seccio_"]>label,
			[id^="seccio_"]>h4,
			[id^="seccio_"]>h3,
			[id^="seccio_"]>h2 {
				user-select: none;
				-webkit-user-select: none;
				-moz-user-select: none;
				-ms-user-select: none;
			}

			[id^="seccio_"]>label:hover,
			[id^="seccio_"]>h4:hover,
			[id^="seccio_"]>h3:hover,
			[id^="seccio_"]>h2:hover {
				opacity: 0.85;
				background-color: rgba(0, 0, 0, 0.02);
			}

			/* Wrapper para permitir slideToggle en tablas */
			.seccio-content-wrapper {
				overflow: hidden;
			}

			.toggle-expandir {
				display: flex;
			}
		</style>
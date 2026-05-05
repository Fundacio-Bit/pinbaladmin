<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
	.search-panel {
		background: linear-gradient(to bottom, #f8f9fa 0%, #e9ecef 100%);
		border: 1px solid #dee2e6;
		border-radius: 8px;
		box-shadow: 0 2px 4px rgba(0,0,0,0.08);
		margin-bottom: 25px;
	}
	
	.search-panel .panel-heading {
		background: linear-gradient(to bottom, #4a90e2 0%, #357abd 100%);
		border-bottom: 2px solid #2e6da4;
		border-radius: 7px 7px 0 0;
		color: white;
		padding: 12px 20px;
	}
	
	.search-panel .panel-title {
		font-size: 16px;
		font-weight: 600;
		margin: 0;
	}
	
	.search-panel .panel-body {
		padding: 20px;
	}
	
	.search-form .form-group {
		margin-right: 15px;
		position: relative;
	}
	
	.search-form label {
		font-weight: 600;
		color: #495057;
		margin-right: 8px;
		display: block;
		margin-bottom: 5px;
	}
	
	.search-form input[type="text"] {
		border: 2px solid #ced4da;
		border-radius: 4px;
		transition: border-color 0.2s, box-shadow 0.2s;
		padding: 8px 12px;
	}
	
	.search-form input[type="text"]:focus {
		border-color: #4a90e2;
		box-shadow: 0 0 0 0.2rem rgba(74, 144, 226, 0.25);
		outline: none;
	}
	
	.search-form .btn {
		padding: 8px 20px;
		font-weight: 500;
		border-radius: 4px;
		transition: all 0.2s;
	}
	
	.search-form .btn-primary {
		background: linear-gradient(to bottom, #5cb85c 0%, #449d44 100%);
		border-color: #449d44;
		color: white;
	}
	
	.search-form .btn-primary:hover {
		background: linear-gradient(to bottom, #449d44 0%, #398439 100%);
		transform: translateY(-1px);
		box-shadow: 0 2px 4px rgba(0,0,0,0.2);
	}
	
	.search-form .btn-default {
		background: linear-gradient(to bottom, #f8f9fa 0%, #e9ecef 100%);
		border-color: #ced4da;
		color: #495057;
	}
	
	.search-form .btn-default:hover {
		background: linear-gradient(to bottom, #e9ecef 0%, #dee2e6 100%);
		transform: translateY(-1px);
		box-shadow: 0 2px 4px rgba(0,0,0,0.15);
	}
	
	/* Estilos autocomplete */
	.autocomplete-results {
		position: absolute;
		background: white;
		border: 1px solid #ddd;
		border-top: none;
		max-height: 300px;
		overflow-y: auto;
		z-index: 1000;
		width: 100%;
		box-shadow: 0 4px 6px rgba(0,0,0,0.1);
		top: 100%;
		left: 0;
	}
	
	.autocomplete-results.hidden {
		display: none;
	}
	
	.autocomplete-item {
		padding: 10px;
		cursor: pointer;
		border-bottom: 1px solid #f0f0f0;
	}
	
	.autocomplete-item:hover {
		background-color: #f0f0f0;
	}
	
	.autocomplete-item strong {
		display: block;
		color: #333;
		font-size: 14px;
	}
	
	.autocomplete-item small {
		color: #666;
		font-size: 0.85em;
	}
	
	.spinner {
		width: 30px;
		height: 30px;
		margin: 10px auto;
		border: 3px solid #f3f3f3;
		border-top: 3px solid #3498db;
		border-radius: 50%;
		animation: spin 1s linear infinite;
	}
	
	@keyframes spin {
		0% { transform: rotate(0deg); }
		100% { transform: rotate(360deg); }
	}
	
	.results-panel {
		border: 1px solid #dee2e6;
		border-radius: 8px;
		box-shadow: 0 2px 8px rgba(0,0,0,0.1);
		overflow: hidden;
	}
	
	.results-panel .panel-heading {
		background: linear-gradient(to bottom, #ffffff 0%, #f8f9fa 100%);
		border-bottom: 2px solid #e9ecef;
		padding: 15px 20px;
	}
	
	.results-panel .panel-title {
		font-size: 18px;
		font-weight: 600;
		color: #2c3e50;
		margin: 0;
	}
	
	.results-panel .badge {
		background-color: #4a90e2;
		font-size: 13px;
		padding: 5px 10px;
		border-radius: 12px;
		font-weight: 500;
		vertical-align: middle;
		margin-left: 10px;
	}
	
	.results-panel .panel-body {
		padding: 0;
	}
	
	.table-responsive-wrapper {
		padding: 20px;
	}
	
	.pinfos-table {
		margin-bottom: 0;
		background-color: white;
		width: 100%;
	}
	
	.pinfos-table thead {
		background: linear-gradient(to bottom, #34495e 0%, #2c3e50 100%);
	}
	
	.pinfos-table thead th {
		border: none;
		text-transform: uppercase;
		font-size: 13px;
		letter-spacing: 0.5px;
		padding: 12px 10px;
		vertical-align: middle;
		white-space: nowrap;
	}
	
	.pinfos-table tbody tr {
		transition: background-color 0.2s;
	}
	
	.pinfos-table tbody tr:hover {
		background-color: #f1f8ff;
	}
	
	.pinfos-table tbody td {
		padding: 12px 10px;
		vertical-align: middle;
		border-color: #e9ecef;
		font-size: 13px;
	}
	
	.pinfos-table tbody tr:nth-child(even) {
		background-color: #f8f9fa;
	}
	
	.pinfos-table tbody tr:nth-child(even):hover {
		background-color: #e8f4ff;
	}
	
	.pinfos-table td:first-child {
		font-weight: 600;
		color: #2c3e50;
	}
	
	.alert {
		border-radius: 6px;
		padding: 15px 20px;
		margin-bottom: 20px;
		border-left: 4px solid;
		box-shadow: 0 2px 4px rgba(0,0,0,0.08);
	}
	
	.alert-danger {
		background-color: #f8d7da;
		border-left-color: #dc3545;
		color: #721c24;
	}
	
	.alert-info {
		background-color: #d1ecf1;
		border-left-color: #17a2b8;
		color: #0c5460;
	}
	
	.alert-warning {
		background-color: #fff3cd;
		border-left-color: #ffc107;
		color: #856404;
	}
	
	.alert i {
		margin-right: 8px;
		font-size: 16px;
	}
	
	.badge-estat {
		padding: 5px 12px;
		border-radius: 12px;
		font-size: 11px;
		font-weight: 600;
		text-transform: uppercase;
		display: inline-block;
	}
	
	.badge-iniciant {
		background-color: #6c757d;
		color: white;
	}
	
	.badge-error {
		background-color: #dc3545;
		color: white;
	}
	
	.badge-creant {
		background-color: #e9ecef;
		color: #495057;
		border: 1px solid #ced4da;
	}
	
	.badge-pendent-firma {
		background-color: #ffc107;
		color: #856404;
	}
	
	.badge-pendent-tramitar {
		background-color: #fd7e14;
		color: white;
	}
	
	.badge-tramitat {
		background-color: #28a745;
		color: white;
	}
	
	.badge-notificat {
		background-color: #20c997;
		color: white;
	}
	
	.user-info {
		line-height: 1.6;
	}
	
	.user-nif {
		font-weight: 600;
		color: #2c3e50;
		display: block;
	}
	
	.user-nom {
		color: #666;
		font-size: 0.95em;
	}
	
	/* Estilos para botones de acción */
	.action-btn {
		padding: 6px 10px;
		margin: 0 3px;
		border-radius: 4px;
		transition: all 0.2s;
		display: inline-block;
		min-width: 36px;
		text-align: center;
	}
	
	.action-btn:hover {
		transform: translateY(-2px);
		box-shadow: 0 4px 8px rgba(0,0,0,0.2);
	}
	
	.action-btn i {
		font-size: 14px;
		margin: 0;
	}
	
	.btn-warning {
		background-color: #ffc107;
		border-color: #ffc107;
		color: #333;
	}
	
	.btn-warning:hover {
		background-color: #e0a800;
		border-color: #d39e00;
		color: #333;
	}
</style>

<div id="contenido">
	<h3 style="color: #2c3e50; margin-bottom: 25px; font-weight: 600;">
		<i class="fas fa-search" style="margin-right: 10px;"></i>Consulta de Permisos en PINFOs
	</h3>
	
	<!-- Formulario de búsqueda -->
	<div class="panel search-panel">
		<div class="panel-heading">
			<h4 class="panel-title">
				<i class="fas fa-filter" style="margin-right: 8px;"></i>Filtres de Cerca
			</h4>
		</div>
		<div class="panel-body">
			<form method="GET" action="<c:url value="/operador/dadespinbal/buscadorPinfo"/>" class="search-form" id="formBuscador">
				<div class="form-row" style="display: flex; gap: 15px; margin-bottom: 15px;">
					<div class="form-group" style="flex: 1;">
						<label for="searchUsuari">
							<i class="fas fa-user" style="margin-right: 5px;"></i>Usuari:
						</label>
						<input type="text" class="form-control" id="searchUsuari" name="searchUsuari" 
							   value="${searchUsuari}" placeholder="Usuaris amb PINFOs: NIF, nom o cognoms (mín. 3)" 
							   style="width: 100%;" autocomplete="off"/>
						<div id="autocomplete-usuaris" class="autocomplete-results hidden"></div>
					</div>
					<div class="form-group" style="flex: 1;">
						<label for="searchProcediment">
							<i class="fas fa-cogs" style="margin-right: 5px;"></i>Procediment:
						</label>
						<input type="text" class="form-control" id="searchProcediment" name="searchProcediment" 
							   value="${searchProcediment}" placeholder="Escriu codi o nom (mín. 2 caràcters)" 
							   style="width: 100%;" autocomplete="off"/>
						<div id="autocomplete-procediments" class="autocomplete-results hidden"></div>
					</div>
					<div class="form-group" style="flex: 1;">
						<label for="searchServei">
							<i class="fas fa-server" style="margin-right: 5px;"></i>Servei:
						</label>
						<input type="text" class="form-control" id="searchServei" name="searchServei" 
							   value="${searchServei}" placeholder="Escriu codi o nom (mín. 2 caràcters)" 
							   style="width: 100%;" autocomplete="off"/>
						<div id="autocomplete-serveis" class="autocomplete-results hidden"></div>
					</div>
				</div>
				<div class="form-actions" style="text-align: left; margin-top: 10px;">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-search"></i> Cercar
					</button>
					<c:if test="${not empty searchUsuari or not empty searchProcediment or not empty searchServei}">
						<a href="<c:url value="/operador/dadespinbal/buscadorPinfo"/>" class="btn btn-default">
							<i class="fas fa-times"></i> Netejar
						</a>
					</c:if>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Mensaje de ayuda inicial -->
	<c:if test="${empty searchUsuari and empty searchProcediment and empty searchServei}">
		<div class="alert alert-info">
			<i class="fas fa-info-circle"></i>
			<strong>Ajuda:</strong> Aquest buscador permet consultar quins PINFOs tenen permisos assignats. 
			Els camps tenen suggeriments automàtics que només mostren elements amb permisos actius:
			<ul style="margin-top: 10px; margin-bottom: 0;">
				<li><strong>Usuari:</strong> Només usuaris amb PINFOs assignats (escriu almenys 3 caràcters)</li>
				<li><strong>Procediment:</strong> Només procediments del Govern de les Illes Balears (escriu almenys 2 caràcters)</li>
				<li><strong>Servei:</strong> Tots els serveis disponibles (escriu almenys 2 caràcters)</li>
			</ul>
		</div>
	</c:if>
	
	<!-- Mensajes de error -->
	<c:if test="${not empty error}">
		<div class="alert alert-danger">
			<i class="fas fa-exclamation-circle"></i>${error}
		</div>
	</c:if>
	
	<!-- Mensaje de información -->
	<c:if test="${not empty info}">
		<div class="alert alert-warning">
			<i class="fas fa-info-circle"></i>${info}
		</div>
	</c:if>
	
	<!-- Resultados -->
	<c:if test="${not empty pinfos}">
		<div class="panel results-panel">
			<div class="panel-heading">
				<h4 class="panel-title">
					<i class="fas fa-check-circle" style="color: #5cb85c; margin-right: 8px;"></i>Resultats
					<span class="badge">${fn:length(pinfos)}</span>
				</h4>
			</div>
			<div class="panel-body">
				<div class="table-responsive-wrapper">
					<table class="table table-bordered pinfos-table">
						<thead>
							<tr>
								<th style="width: 70px; text-align: center;"><i class="fas fa-hashtag"></i> ID</th>
								<th style="width: 120px; text-align: center;"><i class="fas fa-building"></i> Entitat</th>
								<th style="width: 220px;"><i class="fas fa-user"></i> Sol·licitant</th>
								<th style="width: 220px;"><i class="fas fa-user-check"></i> Destinatari</th>
								<th style="width: 110px; text-align: center;"><i class="fas fa-info-circle"></i> Estat</th>
								<th style="width: 140px; text-align: center;"><i class="fas fa-tools"></i> Accions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pinfos}" var="pinfoData">
								<tr>
									<td style="text-align: center;"><strong>${pinfoData.pinfoID}</strong></td>
									<td style="text-align: center; font-size: 12px; font-weight: 500;">
										<c:choose>
											<c:when test="${not empty pinfoData.entitat}">
												${pinfoData.entitat}
											</c:when>
											<c:otherwise>
												<span style="color: #999;">-</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<div class="user-info">
											<span class="user-nif">${pinfoData.solicitantNIF}</span>
											<c:if test="${not empty pinfoData.solicitantNom}">
												<span class="user-nom">${pinfoData.solicitantNom}</span>
											</c:if>
										</div>
									</td>
									<td>
										<div class="user-info">
											<span class="user-nif">${pinfoData.destinatariNIF}</span>
											<c:if test="${not empty pinfoData.destinatariNom}">
												<span class="user-nom">${pinfoData.destinatariNom}</span>
											</c:if>
										</div>
									</td>
									<td style="text-align: center;">
										<c:choose>
											<c:when test="${pinfoData.estat == -2}">
												<span class="badge-estat badge-iniciant">Iniciant</span>
											</c:when>
											<c:when test="${pinfoData.estat == -1}">
												<span class="badge-estat badge-error">Error</span>
											</c:when>
											<c:when test="${pinfoData.estat == 0}">
												<span class="badge-estat badge-creant">Creant</span>
											</c:when>
											<c:when test="${pinfoData.estat == 1}">
												<span class="badge-estat badge-pendent-firma">Pendent Firma</span>
											</c:when>
											<c:when test="${pinfoData.estat == 2}">
												<span class="badge-estat badge-pendent-tramitar">Pendent Tramitar</span>
											</c:when>
											<c:when test="${pinfoData.estat == 3}">
												<span class="badge-estat badge-tramitat">Tramitat</span>
											</c:when>
											<c:when test="${pinfoData.estat == 4}">
												<span class="badge-estat badge-notificat">Notificat</span>
											</c:when>
											<c:otherwise>
												<span class="badge-estat" style="background-color: #6c757d; color: white;">Estat ${pinfoData.estat}</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td style="text-align: center; white-space: nowrap;">
										<a href="<c:url value='/operador/pinfo/view/${pinfoData.pinfoID}'/>" 
										   class="btn btn-info btn-sm action-btn" 
										   title="Veure detalls del PINFO">
											<i class="fas fa-eye"></i>
										</a>
										<c:if test="${not empty pinfoData.fitxerfirmat}">
											<a href="<c:url value='${pad:fileUrl(pinfoData.fitxerfirmat)}'/>" 
											   class="btn btn-success btn-sm action-btn" 
											   title="Descarregar PDF firmat">
												<i class="fas fa-file-signature"></i>
											</a>
										</c:if>
										<c:if test="${not empty pinfoData.fitxer and empty pinfoData.fitxerfirmat}">
											<a href="<c:url value='${pad:fileUrl(pinfoData.fitxer)}'/>" 
											   class="btn btn-warning btn-sm action-btn" 
											   title="Descarregar PDF (sense firmar)">
												<i class="fas fa-file-pdf"></i>
											</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</c:if>
</div>

<script type="text/javascript">
$(document).ready(function() {
	let debounceTimerUsuari, debounceTimerProc, debounceTimerServei;
	
	// Autocomplete para Usuarios
	$("#searchUsuari").on("input", function() {
		clearTimeout(debounceTimerUsuari);
		
		var usuari = $(this).val();
		if (usuari.length < 3) {
			$("#autocomplete-usuaris").empty().addClass("hidden");
			return;
		}
		
		$("#autocomplete-usuaris").html("<div class='spinner'></div>").removeClass("hidden");
		
		debounceTimerUsuari = setTimeout(function() {
			$.ajax({
				url: "<c:url value='/operador/dadespinbal/jsonUsuaris'/>",
				type: "GET",
				data: { nom: usuari },
				success: function(data) {
					$("#autocomplete-usuaris").empty();
					
					if (data == null) {
						$("#autocomplete-usuaris").html("<div style='padding: 10px; color: red;'>Més de 500 resultats. Afina la cerca.</div>").removeClass("hidden");
					} else if (data.length === 0) {
						$("#autocomplete-usuaris").html("<div style='padding: 10px; color: #666;'>No s'han trobat usuaris</div>").removeClass("hidden");
					} else {
						data.forEach(function(usuari) {
							var item = $("<div class='autocomplete-item'></div>");
							// usuari tiene las propiedades: id, key, value
							// value contiene el formato: "Antonio Trobat Obrador - 43120476F - 43120476F"
							item.html("<strong>" + usuari.value + "</strong>");
							item.on("click", function() {
								// Al hacer clic, usar el ID (administrationID) para buscar
								$("#searchUsuari").val(usuari.key);
								$("#autocomplete-usuaris").empty().addClass("hidden");
							});
							$("#autocomplete-usuaris").append(item);
						});
						$("#autocomplete-usuaris").removeClass("hidden");
					}
				},
				error: function() {
					$("#autocomplete-usuaris").html("<div style='padding: 10px; color: red;'>Error al cercar usuaris</div>").removeClass("hidden");
				}
			});
		}, 500);
	});
	
	// Autocomplete para Procedimientos
	$("#searchProcediment").on("input", function() {
		clearTimeout(debounceTimerProc);
		
		var procediment = $(this).val();
		if (procediment.length < 2) {
			$("#autocomplete-procediments").empty().addClass("hidden");
			return;
		}
		
		$("#autocomplete-procediments").html("<div class='spinner'></div>").removeClass("hidden");
		
		debounceTimerProc = setTimeout(function() {
			$.ajax({
				url: "<c:url value='/operador/dadespinbal/jsonProcediments'/>",
				type: "GET",
				data: { query: procediment },
				success: function(data) {
					$("#autocomplete-procediments").empty();
					
					if (data.length === 0) {
						$("#autocomplete-procediments").html("<div style='padding: 10px; color: #666;'>No s'han trobat procediments</div>").removeClass("hidden");
					} else {
						data.forEach(function(proc) {
							var item = $("<div class='autocomplete-item'></div>");
							item.html("<strong>" + proc.key + "</strong><br/><small>" + proc.value + "</small>");
							item.on("click", function() {
								$("#searchProcediment").val(proc.key);
								$("#autocomplete-procediments").empty().addClass("hidden");
							});
							$("#autocomplete-procediments").append(item);
						});
						$("#autocomplete-procediments").removeClass("hidden");
					}
				},
				error: function() {
					$("#autocomplete-procediments").html("<div style='padding: 10px; color: red;'>Error al cercar procediments</div>").removeClass("hidden");
				}
			});
		}, 500);
	});
	
	// Autocomplete para Servicios
	$("#searchServei").on("input", function() {
		clearTimeout(debounceTimerServei);
		
		var servei = $(this).val();
		if (servei.length < 2) {
			$("#autocomplete-serveis").empty().addClass("hidden");
			return;
		}
		
		$("#autocomplete-serveis").html("<div class='spinner'></div>").removeClass("hidden");
		
		debounceTimerServei = setTimeout(function() {
			$.ajax({
				url: "<c:url value='/operador/dadespinbal/jsonServeis'/>",
				type: "GET",
				data: { query: servei },
				success: function(data) {
					$("#autocomplete-serveis").empty();
					
					if (data.length === 0) {
						$("#autocomplete-serveis").html("<div style='padding: 10px; color: #666;'>No s'han trobat serveis</div>").removeClass("hidden");
					} else {
						data.forEach(function(serv) {
							var item = $("<div class='autocomplete-item'></div>");
							item.html("<strong>" + serv.key + "</strong><br/><small>" + serv.value + "</small>");
							item.on("click", function() {
								$("#searchServei").val(serv.key);
								$("#autocomplete-serveis").empty().addClass("hidden");
							});
							$("#autocomplete-serveis").append(item);
						});
						$("#autocomplete-serveis").removeClass("hidden");
					}
				},
				error: function() {
					$("#autocomplete-serveis").html("<div style='padding: 10px; color: red;'>Error al cercar serveis</div>").removeClass("hidden");
				}
			});
		}, 500);
	});
	
	// Ocultar autocomplete al hacer click fuera
	$(document).on("click", function(e) {
		if (!$(e.target).closest("#searchUsuari, #autocomplete-usuaris").length) {
			$("#autocomplete-usuaris").addClass("hidden");
		}
		if (!$(e.target).closest("#searchProcediment, #autocomplete-procediments").length) {
			$("#autocomplete-procediments").addClass("hidden");
		}
		if (!$(e.target).closest("#searchServei, #autocomplete-serveis").length) {
			$("#autocomplete-serveis").addClass("hidden");
		}
	});
});
</script>

<!-- Sección para visualizar PDF en contexto público (solo cuando estat == 0 CREANT) -->
<c:if test="${pinfo.estat == 0}">
	
	<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js"></script>

	<style>
		#fitxerPDF {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    gap: 10px;
		    width: 100%;
		}
		canvas {
		    border: 1px solid #ccc;
		    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
		}
	</style>

	<div id="fitxerPDF"></div>

	<script>
		document.getElementById("pinfo_tableid").style.display = "none";
		
	    var url = "${urlPinfoPDF}";
	    
	    pdfjsLib.getDocument(url).promise.then(function(pdf) {
	        var container = document.getElementById("fitxerPDF");
	
	        for (let pageNum = 1; pageNum <= pdf.numPages; pageNum++) {
	            pdf.getPage(pageNum).then(function(page) {
	                var scale = 1.5;
	                var viewport = page.getViewport({ scale: scale });
	                var canvas = document.createElement("canvas");
	                var context = canvas.getContext("2d");
	                canvas.height = viewport.height;
	                canvas.width = viewport.width;
	                
	                var renderContext = { canvasContext: context, viewport: viewport };
	                page.render(renderContext);
	
	                container.appendChild(canvas);
	            });
	        }
	    });
	</script>
</c:if>

<%-- ============================================================================
     VISUALIZACIÓN ANTERIOR (comentada para referencia futura)
     Esta estructura mostraba los permisos repetidos para cada usuario
     ============================================================================
<c:if test="${not empty pinfoDataFull and not empty pinfoDataFull.usuaris}">
	<div class="card mt-4">
		<div class="card-header">
			<h4><fmt:message key="pinfo.usuarisipermisos.titol" /></h4>
		</div>
		<div class="card-body">
			<c:forEach items="${pinfoDataFull.usuaris}" var="usuari" varStatus="usuariStatus">
				<div class="mb-4">
					<h5 class="text-primary">
						<i class="fas fa-user"></i> 
						${usuari.usuariNom} (${usuari.usuariNif}) - ${usuari.usuariCodi}
					</h5>
					
					<c:forEach items="${usuari.procediments}" var="procediment" varStatus="procStatus">
						<div class="ml-3 mb-3">
							<h6 class="text-secondary">
								<i class="fas fa-folder-open"></i> 
								${procediment.procediment} (${procediment.codi})
							</h6>
							
							<c:if test="${not empty procediment.serveis}">
								<div class="ml-4">
									<strong class="${pinfoDataFull.tipusAlta == 1 ? 'text-success' : 'text-danger'}">
										<i class="fas ${pinfoDataFull.tipusAlta == 1 ? 'fa-plus-circle' : 'fa-minus-circle'}"></i> 
										<fmt:message key="pinfo.permisos.${pinfoDataFull.tipusAlta == 1 ? 'altes' : 'baixes'}" />:
									</strong>
									<ul class="list-unstyled ml-3">
										<c:forEach items="${procediment.serveis}" var="servei">
											<li>
												<span class="badge badge-${pinfoDataFull.tipusAlta == 1 ? 'success' : 'danger'}">
													<i class="fas ${pinfoDataFull.tipusAlta == 1 ? 'fa-check' : 'fa-times'}"></i>
												</span>
												${servei.nom} (${servei.servei})
											</li>
										</c:forEach>
									</ul>
								</div>
							</c:if>
						</div>
						<c:if test="${not procStatus.last}">
							<hr class="ml-3 mr-5"/>
						</c:if>
					</c:forEach>
				</div>
				<c:if test="${not usuariStatus.last}">
					<hr style="border-top: 2px solid #007bff;"/>
				</c:if>
			</c:forEach>
		</div>
	</div>
</c:if>
============================================================================ --%>

<!-- Tabla de Usuarios y Permisos Solicitados (Nueva estructura optimizada) -->
<c:if test="${not empty pinfoDataFull and not empty pinfoDataFull.usuaris}">
	<style>
		.pinfo-permisos-container {
			border: 2px solid #dee2e6;
			border-radius: 6px;
			overflow: hidden;
			box-shadow: 0 2px 8px rgba(0,0,0,0.08);
		}
		.pinfo-header {
			background-color: #ffffff;
			border-bottom: 2px solid #dee2e6;
			padding: 1.5rem 2rem;
		}
		.pinfo-header .tipo-badge {
			display: inline-block;
			padding: 0.6rem 1.5rem;
			border-radius: 4px;
			font-size: 1.1rem;
			font-weight: 700;
			letter-spacing: 0.5px;
			text-transform: uppercase;
		}
		.pinfo-header .tipo-badge.alta {
			background-color: #28a745;
			color: #ffffff;
			border: none;
		}
		.pinfo-header .tipo-badge.baixa {
			background-color: #dc3545;
			color: #ffffff;
			border: none;
		}
		.pinfo-section {
			padding: 1.25rem 1.5rem;
			background-color: #ffffff;
		}
		.pinfo-section:not(:last-child) {
			border-bottom: 2px solid #f1f3f5;
		}
		.pinfo-section-title {
			font-size: 1.1rem;
			font-weight: 700;
			color: #212529;
			margin-bottom: 1rem;
			display: flex;
			align-items: center;
			gap: 0.5rem;
		}
		.pinfo-section-title i {
			font-size: 1.2rem;
			color: #495057;
		}
		.pinfo-section-title .badge {
			font-size: 0.85rem;
			font-weight: 600;
			padding: 0.35rem 0.65rem;
		}
		.user-item {
			background-color: #ffffff;
			border: 2px solid #dee2e6;
			border-radius: 4px;
			padding: 0.75rem 1rem;
			transition: all 0.2s ease;
		}
		.user-item:hover {
			border-color: #007bff;
			box-shadow: 0 2px 6px rgba(0,123,255,0.15);
		}
		.user-item .user-name {
			font-size: 1.05rem;
			font-weight: 700;
			color: #212529;
			margin-bottom: 0.4rem;
		}
		.user-item .user-info {
			font-size: 0.9rem;
			color: #6c757d;
			margin: 0;
			line-height: 1.5;
		}
		.procediment-block {
			background-color: #f8f9fa;
			border: 2px solid #dee2e6;
			border-radius: 4px;
			padding: 1rem;
			margin-bottom: 1rem;
		}
		.procediment-title {
			font-size: 1.05rem;
			font-weight: 700;
			color: #212529;
			margin-bottom: 0.85rem;
			padding-bottom: 0.5rem;
			border-bottom: 2px solid #dee2e6;
		}
		.procediment-title i {
			color: #007bff;
			margin-right: 0.5rem;
		}
		.procediment-code {
			font-size: 0.95rem;
			color: #6c757d;
			font-weight: 600;
		}
		.servei-item {
			display: flex;
			align-items: center;
			padding: 0.5rem 0;
			border-bottom: 1px solid #e9ecef;
		}
		.servei-item:last-child {
			border-bottom: none;
			padding-bottom: 0;
		}
		.servei-item:first-child {
			padding-top: 0;
		}
		.servei-item .servei-icon {
			flex-shrink: 0;
			width: 20px;
			height: 20px;
			border-radius: 3px;
			display: flex;
			align-items: center;
			justify-content: center;
			margin-right: 0.75rem;
			font-size: 0.7rem;
		}
		.servei-item .servei-icon.alta {
			background-color: #28a745;
			color: #ffffff;
		}
		.servei-item .servei-icon.baixa {
			background-color: #dc3545;
			color: #ffffff;
		}
		.servei-item .servei-name {
			font-size: 0.95rem;
			color: #212529;
			font-weight: 600;
			line-height: 1.3;
		}
		.servei-item .servei-code {
			display: none;
		}
		.pinfo-resum {
			background-color: #d1ecf1;
			border: 2px solid #bee5eb;
			border-radius: 4px;
			padding: 1rem 1.25rem;
			color: #0c5460;
			font-size: 0.95rem;
		}
		.pinfo-resum i {
			font-size: 1.1rem;
			margin-right: 0.5rem;
		}
		.pinfo-resum strong {
			font-weight: 700;
		}
	</style>

	<!-- Usar el tipo del PinfoDataFull -->
	<c:set var="tipoPinfo" value="${pinfoDataFull.tipusAlta}" />

	<div class="pinfo-permisos-container mt-4">
		<!-- Header -->
		<div class="pinfo-header">
			<c:choose>
				<c:when test="${tipoPinfo == '1'}">
					<span class="tipo-badge alta">
						<i class="fas fa-arrow-circle-up"></i> Altes de permisos
					</span>
				</c:when>
				<c:when test="${tipoPinfo == '0'}">
					<span class="tipo-badge baixa">
						<i class="fas fa-arrow-circle-down"></i> Baixes de permisos
					</span>
				</c:when>
			</c:choose>
		</div>
		
		<!-- SECCIÓN 1: Usuarios -->
		<div class="pinfo-section">
			<div class="pinfo-section-title">
				<i class="fas fa-users text-muted"></i>
				<span>Usuaris</span>
				<span class="badge badge-secondary">${pinfoDataFull.usuaris.size()}</span>
			</div>
			<div class="row">
				<c:forEach items="${pinfoDataFull.usuaris}" var="usuari">
					<div class="col-md-6 mb-2">
						<div class="user-item">
							<div class="user-name">${usuari.usuariNom}</div>
							<p class="user-info mb-0">${usuari.usuariNif} - ${usuari.usuariCodi}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- SECCIÓN 2: Permisos -->
		<div class="pinfo-section">
			<div class="pinfo-section-title">
				<i class="fas fa-key text-muted"></i>
				<span>Permisos</span>
			</div>
			
			<!-- Extraer procedimientos del primer usuario -->
			<c:if test="${not empty pinfoDataFull.usuaris}">
				<c:set var="primerUsuari" value="${pinfoDataFull.usuaris[0]}" />
				
				<c:forEach items="${primerUsuari.procediments}" var="procediment">
					<div class="procediment-block">
						<div class="procediment-title">
							<i class="fas fa-folder-open text-muted"></i>
							${procediment.procediment}
							<span class="procediment-code">(${procediment.codi})</span>
						</div>
						
						<div>
							<!-- Serveis -->
							<c:forEach items="${procediment.serveis}" var="servei">
								<div class="servei-item">
									<div class="servei-icon ${pinfoDataFull.tipusAlta == 1 ? 'alta' : 'baixa'}">
										<i class="fas ${pinfoDataFull.tipusAlta == 1 ? 'fa-check' : 'fa-times'}" style="font-size: 0.7rem;"></i>
									</div>
									<div class="flex-grow-1">
										<div class="servei-name">${servei.nom}</div>
										<div class="servei-code">${servei.servei}</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		
		<!-- RESUMEN -->
		<div class="pinfo-section">
			<div class="pinfo-resum">
				<i class="fas fa-info-circle"></i>
				<strong>Resum:</strong>
				Es tramitaran
				<c:choose>
					<c:when test="${tipoPinfo == '1'}">
						<strong>altes de permisos</strong>
					</c:when>
					<c:when test="${tipoPinfo == '0'}">
						<strong>baixes de permisos</strong>
					</c:when>
				</c:choose>
				per a <strong>${pinfoDataFull.usuaris.size()}</strong>
				<c:choose>
					<c:when test="${pinfoDataFull.usuaris.size() == 1}">usuari</c:when>
					<c:otherwise>usuaris</c:otherwise>
				</c:choose>.
			</div>
		</div>
	</div>
</c:if>

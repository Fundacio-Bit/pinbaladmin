<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
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
	}
	
	.search-form label {
		font-weight: 600;
		color: #495057;
		margin-right: 8px;
	}
	
	.search-form input[type="text"] {
		border: 2px solid #ced4da;
		border-radius: 4px;
		transition: border-color 0.2s, box-shadow 0.2s;
	}
	
	.search-form input[type="text"]:focus {
		border-color: #4a90e2;
		box-shadow: 0 0 0 0.2rem rgba(74, 144, 226, 0.25);
		outline: none;
	}
	
	.search-form .btn {
		padding: 6px 16px;
		font-weight: 500;
		border-radius: 4px;
		transition: all 0.2s;
	}
	
	.search-form .btn-primary {
		background: linear-gradient(to bottom, #5cb85c 0%, #449d44 100%);
		border-color: #449d44;
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
	
	.procediments-table {
		margin-bottom: 0;
		background-color: white;
	}
	
	.procediments-table thead {
		background: linear-gradient(to bottom, #34495e 0%, #2c3e50 100%);
	}
	
	.procediments-table thead th {
		border: none;
		/* font-weight: 600; */
		text-transform: uppercase;
		font-size: 14px;
		letter-spacing: 0.5px;
		padding: 12px 15px;
		vertical-align: middle;
	}
	
	.procediments-table tbody tr {
		transition: background-color 0.2s;
	}
	
	.procediments-table tbody tr:hover {
		background-color: #f1f8ff;
		cursor: pointer;
	}
	
	.procediments-table tbody td {
		padding: 12px 15px;
		vertical-align: middle;
		border-color: #e9ecef;
	}
	
	.procediments-table tbody tr:nth-child(even) {
		background-color: #f8f9fa;
	}
	
	.procediments-table tbody tr:nth-child(even):hover {
		background-color: #e8f4ff;
	}
	
	.procediments-table td:first-child {
		font-weight: 600;
		color: #2c3e50;
	}
	
	.pagination-wrapper {
		background-color: #f8f9fa;
		padding: 20px;
		border-top: 1px solid #e9ecef;
		text-align: center;
	}
	
	.pagination {
		margin: 0;
		display: flex;
	}
	
	.pagination > li > a,
	.pagination > li > span {
		color: #4a90e2;
		border: 1px solid #dee2e6;
		padding: 8px 14px;
		margin: 0 2px;
		border-radius: 4px;
		font-weight: 500;
		transition: all 0.2s;
	}
	
	.pagination > li > a:hover {
		background-color: #4a90e2;
		color: white;
		border-color: #4a90e2;
		transform: translateY(-1px);
		box-shadow: 0 2px 4px rgba(74, 144, 226, 0.3);
	}
	
	.pagination > .active > a,
	.pagination > .active > span {
		background: linear-gradient(to bottom, #4a90e2 0%, #357abd 100%);
		border-color: #357abd;
		color: white;
		font-weight: 600;
	}
	
	.pagination > .disabled > a,
	.pagination > .disabled > span {
		color: #adb5bd;
		background-color: #f8f9fa;
		border-color: #dee2e6;
		cursor: not-allowed;
	}
	
	.pagination-info {
		margin-top: 12px;
		color: #6c757d;
		font-size: 13px;
		font-weight: 500;
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
	
	.alert i {
		margin-right: 8px;
		font-size: 16px;
	}
</style>

<div id="contenido">
	<h3 style="color: #2c3e50; margin-bottom: 25px; font-weight: 600;">
		<i class="fas fa-cogs" style="margin-right: 10px;"></i>Procediments Pinbal
	</h3>
	
	<!-- Formulario de búsqueda -->
	<div class="panel search-panel">
		<div class="panel-heading">
			<h4 class="panel-title">
				<i class="fas fa-search" style="margin-right: 8px;"></i>Cerca de procediment
			</h4>
		</div>
		<div class="panel-body">
			<form method="GET" action="<c:url value="/operador/dadespinbal/procediments"/>" class="search-form">
				<div class="form-row" style="display: flex; gap: 15px; margin-bottom: 15px;">
					<div class="form-group" style="flex: 1;">
						<label for="entitatCodi">
							<i class="fas fa-home" style="margin-right: 5px;"></i>Entitat:
						</label>
						<select class="form-control" id="entitatCodi" name="entitatCodi" style="width: 100%;">
							<c:forEach items="${entitats}" var="entitat">
								<option value="${entitat.codi}" ${entitat.codi == selectedEntitat ? 'selected' : ''}>${entitat.nom} (${entitat.codi})</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="flex: 1;">
						<label for="searchCodi">
							<i class="fas fa-tag" style="margin-right: 5px;"></i>Codi:
						</label>
						<input type="text" class="form-control" id="searchCodi" name="searchCodi" 
							   value="${searchCodi}" placeholder="Codi del procediment" style="width: 100%;"/>
					</div>
					<div class="form-group" style="flex: 1;">
						<label for="searchNom">
							<i class="fas fa-font" style="margin-right: 5px;"></i>Nom:
						</label>
						<input type="text" class="form-control" id="searchNom" name="searchNom" 
							   value="${searchNom}" placeholder="Nom del procediment" style="width: 100%;"/>
					</div>
				</div>
				<div class="form-actions" style="text-align: left;">
					<button type="submit" class="btn btn-primary">
						<i class="fas fa-search"></i> Cercar
					</button>
					<c:if test="${not empty searchCodi or not empty searchNom}">
						<a href="<c:url value="/operador/dadespinbal/procediments"/>" class="btn btn-default">
							<i class="fas fa-times"></i> Netejar
						</a>
					</c:if>
				</div>
			</form>
		</div>
	</div>
	
	<c:if test="${not empty error}">
		<div class="alert alert-danger">
			<i class="fas fa-exclamation-circle"></i>${error}
		</div>
	</c:if>
	
	<c:if test="${not empty procediments}">
		<div class="panel results-panel">
			<div class="panel-heading">
				<c:choose>
					<c:when test="${not empty searchCodi or not empty searchNom}">
						<h4 class="panel-title">
							<i class="fas fa-check-circle" style="color: #5cb85c; margin-right: 8px;"></i>Resultat de la cerca
							<c:if test="${not empty searchCodi}"><span style="margin-left: 10px; font-weight: normal; font-size: 0.9em;">(Codi: ${searchCodi})</span></c:if>
							<c:if test="${not empty searchNom}"><span style="margin-left: 10px; font-weight: normal; font-size: 0.9em;">(Nom: ${searchNom})</span></c:if>
						</h4>
					</c:when>
					<c:otherwise>
						<h4 class="panel-title">
							<i class="fas fa-list" style="margin-right: 8px;"></i>Llistat de procediments
							<c:if test="${not empty totalElements}">
								<span class="badge">${totalElements}</span>
							</c:if>
						</h4>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-body">
				<div class="table-responsive-wrapper">
					<table class="table table-bordered procediments-table">
						<thead>
							<tr>
								<th><i class="fas fa-tag"></i> Codi</th>
								<th><i class="fas fa-font"></i> Nom</th>
								<th><i class="fas fa-home"></i> Entitat</th>
								<th><i class="fas fa-cogs"></i> Serveis</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${procediments}" var="procediment">
								<tr>
									<td><strong>${procediment.codi}</strong></td>
									<td>${procediment.nom}</td>
									<td>${procediment.entitatCodi}</td>
									<td style="text-align: center;">
										<button type="button" class="btn btn-info btn-sm btn-serveis" 
												data-codi="${procediment.codi}" 
												data-entitat="${procediment.entitatCodi}"
												title="Veure serveis">
											<i class="fas fa-eye"></i>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<!-- Paginación -->
				<c:if test="${not empty totalPages && totalPages > 1}">
					<div class="pagination-wrapper">
						<nav aria-label="Paginació">
							<ul class="pagination">
								<!-- Botón anterior -->
								<li class="${currentPage == 0 ? 'disabled' : ''}">
									<a href="<c:url value="/operador/dadespinbal/procediments">
										<c:param name="pageNum" value="${currentPage - 1}"/>
										<c:param name="pageSize" value="${pageSize}"/>
										<c:if test="${not empty searchCodi}"><c:param name="searchCodi" value="${searchCodi}"/></c:if>
										<c:if test="${not empty searchNom}"><c:param name="searchNom" value="${searchNom}"/></c:if>
										<c:if test="${not empty selectedEntitat}"><c:param name="entitatCodi" value="${selectedEntitat}"/></c:if>
									</c:url>" aria-label="Anterior">
										<span aria-hidden="true">&laquo; Anterior</span>
									</a>
								</li>
								
								<!-- Páginas -->
								<c:set var="startPage" value="${currentPage - 2 < 0 ? 0 : currentPage - 2}"/>
								<c:set var="endPage" value="${currentPage + 2 >= totalPages ? totalPages - 1 : currentPage + 2}"/>
								
								<c:if test="${startPage > 0}">
									<li>
										<a href="<c:url value="/operador/dadespinbal/procediments">
											<c:param name="pageNum" value="0"/>
											<c:param name="pageSize" value="${pageSize}"/>
											<c:if test="${not empty searchCodi}"><c:param name="searchCodi" value="${searchCodi}"/></c:if>
											<c:if test="${not empty searchNom}"><c:param name="searchNom" value="${searchNom}"/></c:if>
											<c:if test="${not empty selectedEntitat}"><c:param name="entitatCodi" value="${selectedEntitat}"/></c:if>
										</c:url>">1</a>
									</li>
									<c:if test="${startPage > 1}">
										<li class="disabled"><span>...</span></li>
									</c:if>
								</c:if>
								
								<c:forEach begin="${startPage}" end="${endPage}" var="i">
									<li class="${i == currentPage ? 'active' : ''}">
										<a href="<c:url value="/operador/dadespinbal/procediments">
											<c:param name="pageNum" value="${i}"/>
											<c:param name="pageSize" value="${pageSize}"/>
											<c:if test="${not empty searchCodi}"><c:param name="searchCodi" value="${searchCodi}"/></c:if>
											<c:if test="${not empty searchNom}"><c:param name="searchNom" value="${searchNom}"/></c:if>
											<c:if test="${not empty selectedEntitat}"><c:param name="entitatCodi" value="${selectedEntitat}"/></c:if>
										</c:url>">${i + 1}</a>
									</li>
								</c:forEach>
								
								<c:if test="${endPage < totalPages - 1}">
									<c:if test="${endPage < totalPages - 2}">
										<li class="disabled"><span>...</span></li>
									</c:if>
									<li>
										<a href="<c:url value="/operador/dadespinbal/procediments">
											<c:param name="pageNum" value="${totalPages - 1}"/>
											<c:param name="pageSize" value="${pageSize}"/>
											<c:if test="${not empty searchCodi}"><c:param name="searchCodi" value="${searchCodi}"/></c:if>
											<c:if test="${not empty searchNom}"><c:param name="searchNom" value="${searchNom}"/></c:if>
											<c:if test="${not empty selectedEntitat}"><c:param name="entitatCodi" value="${selectedEntitat}"/></c:if>
										</c:url>">${totalPages}</a>
									</li>
								</c:if>
								
								<!-- Botón siguiente -->
								<li class="${currentPage >= totalPages - 1 ? 'disabled' : ''}">
									<a href="<c:url value="/operador/dadespinbal/procediments">
										<c:param name="pageNum" value="${currentPage + 1}"/>
										<c:param name="pageSize" value="${pageSize}"/>
										<c:if test="${not empty searchCodi}"><c:param name="searchCodi" value="${searchCodi}"/></c:if>
										<c:if test="${not empty searchNom}"><c:param name="searchNom" value="${searchNom}"/></c:if>
										<c:if test="${not empty selectedEntitat}"><c:param name="entitatCodi" value="${selectedEntitat}"/></c:if>
									</c:url>" aria-label="Següent">
										<span aria-hidden="true">Següent &raquo;</span>
									</a>
								</li>
							</ul>
						</nav>
						
						<div class="pagination-info">
							Pàgina <strong>${currentPage + 1}</strong> de <strong>${totalPages}</strong> 
							(<strong>${totalElements}</strong> procediments en total)
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</c:if>
	
	<c:if test="${empty procediments && empty error}">
		<div class="alert alert-info">
			<i class="fas fa-info-circle"></i>No hi ha procediments per mostrar. Utilitza el cercador per trobar procediments.
		</div>
	</c:if>
	
	<!-- Modal Serveis -->
	<div class="modal fade" id="serveisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	        <!-- Content loaded via AJAX -->
	    </div>
	  </div>
	</div>

</div>

<script>
$(document).ready(function() {
    $('.btn-serveis').click(function(e) {
    	e.preventDefault();
        var procedimentCodi = $(this).data('codi');
        var entitatCodi = $(this).data('entitat');
        var url = '<c:url value="/operador/dadespinbal/serveis"/>';
        
        $('#serveisModal .modal-content').html('<div class="modal-body"><div class="text-center"><i class="fas fa-sync-alt fa-spin"></i> Carregant serveis...</div></div>');
        $('#serveisModal').modal('show');
        
        $.get(url, { procedimentCodi: procedimentCodi, entitatCodi: entitatCodi })
            .done(function(data) {
                $('#serveisModal .modal-content').html(data);
            })
            .fail(function() {
                $('#serveisModal .modal-content').html('<div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Error</h4></div><div class="modal-body"><div class="alert alert-danger">Error carregant serveis. Si us plau, torna-ho a provar.</div></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Tancar</button></div>');
            });
    });
});
</script>

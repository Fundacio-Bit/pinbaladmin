<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="org.fundaciobit.pinbaladmin.commons.utils.Constants"/>

<style>
	/* Centrar el formulari */
	#pinfoFilterForm {
		margin: 1rem auto;
		width: fit-content;
	}

	/* Estil per a la taula generada automàticament */
	.table-genapp-list {
		background-color: white;
		border-collapse: collapse;
		box-shadow: 0 2px 8px rgba(0,0,0,0.1);
	}
	
	.table-genapp-list thead {
		background: linear-gradient(to bottom, #2c5f47 0%, #1e4433 100%);
	}
	
	.table-genapp-list thead th {
		/* color: #ffffff !important; */
		text-transform: uppercase;
		font-size: 13px;
		font-weight: 600;
		letter-spacing: 0.5px;
		padding: 12px 10px;
		border: none !important;
		text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
	}
	
	.table-genapp-list tbody tr {
		transition: background-color 0.2s;
	}
	
	.table-genapp-list tbody tr:hover {
		background-color: #f1f8ff !important;
	}
	
	.table-genapp-list tbody td {
		padding: 12px 10px;
		vertical-align: middle;
		border-color: #e9ecef;
		font-size: 13px;
	}
	
	.table-genapp-list tbody tr:nth-child(even) {
		background-color: #f8f9fa;
	}
	
	.table-genapp-list tbody tr:nth-child(even):hover {
		background-color: #e8f4ff !important;
	}
	
	/* Primera columna (ID) en negreta */
	.table-genapp-list tbody td:first-child {
		font-weight: 600;
		color: #2c3e50;
		text-align: center;
	}
	
	/* Badges per als estats */
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
	
	/* Botons d'acció millorats */
	.table-genapp-list .btn-group {
		display: flex;
		gap: 4px;
		flex-wrap: nowrap;
	}
	
	.table-genapp-list .btn-group .btn {
		margin: 0;
		transition: all 0.2s;
		padding: 6px 10px;
		min-width: 36px;
	}
	
	.table-genapp-list .btn-group .btn:hover {
		transform: translateY(-2px);
		box-shadow: 0 4px 8px rgba(0,0,0,0.2);
	}
	
	/* Botó view (ull) - blau */
	.table-genapp-list .btn-group .btn-info {
		background-color: #17a2b8;
		border-color: #17a2b8;
	}
	
	.table-genapp-list .btn-group .btn-info:hover {
		background-color: #138496;
	}
	
	/* Botó d'històric d'events - gris */
	.table-genapp-list .btn-group .btn-secondary {
		background-color: #6c757d;
		border-color: #6c757d;
		color: white;
	}
	
	.table-genapp-list .btn-group .btn-secondary:hover {
		background-color: #5a6268;
	}
	
	/* Botó descarregar PDF firmat - verd */
	.table-genapp-list .btn-group .btn-success {
		background-color: #28a745;
		border-color: #28a745;
	}
	
	.table-genapp-list .btn-group .btn-primary {
		background-color: #007bff;
		border-color: #007bff;
		color: white;
	}

	.table-genapp-list .btn-group .btn-success:hover {
		background-color: #218838;
	}
	
	/* Botó descarregar PDF sense firmar - groc */
	.table-genapp-list .btn-group .btn-warning {
		background-color: #ffc107;
		border-color: #ffc107;
		color: #333;
	}
	
	.table-genapp-list .btn-group .btn-warning:hover {
		background-color: #e0a800;
	}
</style>

<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
	// Obtenir totes les files de la taula
	var rows = document.querySelectorAll('.table-genapp-list tbody tr');
	
	rows.forEach(function(row) {
		var cells = row.querySelectorAll('td');
		if (cells.length === 0) return;
		
		// Transformar estats en badges
		cells.forEach(function(cell) {
			var text = cell.textContent.trim();
			
			if (text === 'Iniciant') {
				cell.innerHTML = '<span class="badge-estat badge-iniciant">Iniciant</span>';
			} else if (text === 'Error') {
				cell.innerHTML = '<span class="badge-estat badge-error">Error</span>';
			} else if (text === 'Creant') {
				cell.innerHTML = '<span class="badge-estat badge-creant">Creant</span>';
			} else if (text === 'Pendent Firma') {
				cell.innerHTML = '<span class="badge-estat badge-pendent-firma">Pendent Firma</span>';
			} else if (text === 'Pendent Tramitar') {
				cell.innerHTML = '<span class="badge-estat badge-pendent-tramitar">Pendent Tramitar</span>';
			} else if (text === 'Tramitat') {
				cell.innerHTML = '<span class="badge-estat badge-tramitat">Tramitat</span>';
			} else if (text === 'Notificat') {
				cell.innerHTML = '<span class="badge-estat badge-notificat">Notificat</span>';
			}
		});
	});
});
</script>

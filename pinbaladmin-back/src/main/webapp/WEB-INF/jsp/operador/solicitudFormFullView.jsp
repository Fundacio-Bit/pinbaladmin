
<%@include file="/WEB-INF/jsp/webdb/solicitudForm.jsp"%>
<br />
<br />

<script> 
    $(function(){
      $("#includedContentSolicitudServei").load("<c:url value="/operador/solicitudserveionlycontent/list/1?solicitudID=${solicitudForm.solicitud.solicitudID}" />");
      $("#includedContentSolicitudDocument").load("<c:url value="/operador/solicituddocumentonlycontent/list/1?solicitudID=${solicitudForm.solicitud.solicitudID}" />");
    });
</script>

<style type="text/css">
.pagination {
	display: none;
}

.table_section tbody td:nth-child(1) {
	width: 12rem;
}

.table_section td {
	vertical-align: middle;
	padding: 5px;
}

.table_section {
  margin-top: 10px;
  margin-bottom: 32px;
}

</style>

<script type="text/javascript">
/*     $(document).ready(function() {
	   	document.querySelectorAll('.tdformlabel').forEach(el => {
			el.className = 'tdformlabel';
			el.classList.add('table_section'); // añade la nueva clase
	   	});
    });
 */</script>

<hr />
<div id="includedContentSolicitudServei"></div>
<hr />
<div id="includedContentSolicitudDocument"></div>
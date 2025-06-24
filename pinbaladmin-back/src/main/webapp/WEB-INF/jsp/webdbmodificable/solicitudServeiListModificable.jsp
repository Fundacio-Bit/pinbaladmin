
<style>
.tabla_modificada {
	width: 100% !important;
}

.tabla_modificada td:last-child {
  width: 0px;
}

.tabla_modificada th{
	text-align: center;
}
</style>


<c:if test="${not empty isPublic}">



	<script type="text/javascript">
		$("#solicitudServeiFilterForm table").attr('class',	'tdformlabel table-sm tabla_modificada');
		$("#solicitudServeiFilterForm table").attr('border', '1');
		
		$("#solicitudServeiFilterForm .row").attr('class', '');

	</script>



</c:if>
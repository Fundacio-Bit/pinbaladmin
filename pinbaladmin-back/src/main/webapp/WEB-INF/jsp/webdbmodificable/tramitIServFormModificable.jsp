<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>


</c:if>



<script type="text/javascript">
	var selectElement = document.querySelector('[name="tramitIServ.nom"]');
	var codi = document.querySelector('[name="tramitIServ.codi"]');
	codi.readOnly = "readOnly";

	selectElement.addEventListener('change', function() {
		codi.readOnly = "";
		var selectedValue = selectElement.value;
		codi.value = selectedValue;
		codi.readOnly = "readOnly";
	});
</script>
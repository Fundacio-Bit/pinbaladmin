<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>


</c:if>

<style>
#copiar {
	float: right;
}
</style>

<c:if test="${not empty tramitD}">
	<input type="button" id="copiar" value="Copiar datos" />
</c:if>

<script>

	$("#copiar").insertAfter("#tramitECteAud_tableid");
	$("#copiar").click(function() {
 		document.getElementById("tramitECteAud.nif").value = "${tramitD.nif}";
		document.getElementById("tramitECteAud.nom").value = "${tramitD.nom}";
		document.getElementById("tramitECteAud.llinatge1").value = "${tramitD.llinatge1}";
		document.getElementById("tramitECteAud.llinatge2").value = "${tramitD.llinatge2}";
		document.getElementById("tramitECteAud.carrec").value = "${tramitD.carrec}";
		document.getElementById("tramitECteAud.telefon").value = "${tramitD.telefon}";
		document.getElementById("tramitECteAud.mail").value = "${tramitD.mail}";
	});
</script>
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

	$("#copiar").insertAfter("#tramitFCteTec_tableid");
	$("#copiar").click(function() {
 		document.getElementById("tramitFCteTec.nif").value = "${tramitD.nif}";
		document.getElementById("tramitFCteTec.nom").value = "${tramitD.nom}";
		document.getElementById("tramitFCteTec.llinatge1").value = "${tramitD.llinatge1}";
		document.getElementById("tramitFCteTec.llinatge2").value = "${tramitD.llinatge2}";
		document.getElementById("tramitFCteTec.carrec").value = "${tramitD.carrec}";
		document.getElementById("tramitFCteTec.telefon").value = "${tramitD.telefon}";
		document.getElementById("tramitFCteTec.mail").value = "${tramitD.mail}";
	});
</script>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<h3>Seleccioni els fitxers a adjuntar a la Incid&egrave;ncia del
	MinHAP</h3>

<form action="${action}" method="post">
	<table class="table-condensed table table-bordered table-striped">
		<tbody>
			<c:forEach items="${documents}" var="doc">
				<tr>
					<td><c:set var="fitxer" value="${doc.fitxerOriginal}"></c:set>
						<input type="checkbox" checked name="fitxerID"
						value="${fitxer.fitxerID}" /> ${fitxer.nom} (${fitxer.tamany}) <c:set
							var="fitxerfirmat" value="${doc.fitxerFirmat}"></c:set> <c:if
							test="${not empty fitxerfirmat}">
							<br />
							<input type="checkbox" checked name="fitxerID"
								value="${fitxerfirmat.fitxerID}" /> ${fitxerfirmat.nom} (${fitxerfirmat.tamany})
                    </c:if></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="submit" value="Continuar"></td>
			</tr>
		</tbody>
	</table>
</form>

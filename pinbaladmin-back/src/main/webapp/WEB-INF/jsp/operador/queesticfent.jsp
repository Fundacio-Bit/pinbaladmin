<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h3>Entrades Que Estic Fent</h3>

<form method="POST">
	Data: <input type="text" name="data" value="${data}"> <input
		type="submit" value="Submit" />
</form>


<c:if test="${empty items}">
	<b>NO HI HA ENTRADES !!!!!<b>
</c:if>

<c:if test="${not empty items}">

	<c:forEach var="item" items="${items}">
     ${item} <br />
	</c:forEach>

</c:if>

</body>
</html>
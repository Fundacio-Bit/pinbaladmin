<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<html>
<body onload="document.forms[0].submit()">
	<form action="${urlCallbackSistra}" method="post">
		<input type="hidden" name="ticket" value="${ticket}" />
        <input type="submit" name="button"/>
	</form>
</body>
</html>
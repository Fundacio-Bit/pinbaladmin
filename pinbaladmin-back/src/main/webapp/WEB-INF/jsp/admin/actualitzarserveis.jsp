<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<h1>Actualitzador de Serveis</h1>
<b> Copiar la llista de serveis i aferrar-ho al següent camp
	d'entrada de text</b>
<br />
<i>Columnes: cedent TAB nom servei TAB descripcio servei TAB codi
	servei</i>
<br />

<form action="<c:url value="/operador/actualitzarserveis"/>"
	method="POST">
	<textarea name="serveis" rows="15" cols="160"></textarea>
	<input type="submit" value="Enviar" />
</form>


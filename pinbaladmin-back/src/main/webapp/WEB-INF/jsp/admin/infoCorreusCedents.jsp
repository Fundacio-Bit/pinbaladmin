<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h4>Informació dels correus a cedents</h4>

<style>
#taulaEntrades {
	margin-top: 1rem;
	margin-right: 2rem;
	width: auto;
}

th {
	text-align: center;
}

.itemInfo {
	padding: 5px;
	width: auto;
}

ul {
	padding: 0px;
}

.itemServeis li {
	list-style: none;
	padding-bottom: 10px;
}
</style>

<c:if test="${not empty items}">

	<table id="taulaEntrades" border="1"
		class="table table-sm table-bordered table-striped table-genapp">

		<thead>
			<tr>
				<th>Asumpte</th>
				<th>Serveis</th>
				<th>Destinataris</th>
				<th>Correu</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<c:set var="nServeis" value="${fn:length(item.serveis)}"/>
				<tr>
					<td class="itemInfo itemSubject">${item.subject}</td>
					<td class="itemInfo itemServeis">
						<ul>
						<c:forEach var="servei" items="${item.serveis}">
						 	<li> ${servei.codi} - ${servei.nom} </li>
						</c:forEach>
						</ul>
					</td>
					<td class="itemInfo itemDest">
					<c:forEach var="destinatari" items="${item.dests}">
							${destinatari};<br>
						</c:forEach>
					</td>
					<td class="itemInfo itemMail">${item.message}</td>
					<%-- <td class="itemInfo itemAdd" onclick="afegirUnaEntrada('${item[2]}')"><i class="fas fa-plus"></i></td> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<script type="text/javascript">

	</script>
</c:if>
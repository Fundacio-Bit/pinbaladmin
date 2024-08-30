<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h3>Estadistiques PinbalAdmin</h3>

<form method="POST">
	<table id="panel-filtros">
		<tr>
			<td>Data Inici: <br> <input id="inputDateStart" type="date"
				name="dataInici" value="${dataInici}">
			</td>
			<td>Data Final: <br> <input id="inputDateEnd" type="date"
				name="dataFinal" value="${dataFinal}">
			</td>
			<td><input id="inputSubmit" type="submit" value="Cercar" /></td>
		</tr>
	</table>
</form>

<!-- DATA | SOLICITUDS TOTALS | LOCALS | ESTATALS | INCIDENCIES TOTALS | ROLS I PERMISOS | CONSULTES | TECNIQUES | INTEGRACIONS -->
<table border="1" id="dades">
	<thead>
		<tr>
			<th>DATA</th>
			<th>SOLICITUDS TOTALS</th>
			<th>LOCALS</th>
			<th>ESTATALS</th>
			<th>INCIDENCIES TOTALS</th>
			<th>ROLS I PERMISOS</th>
			<th>CONSULTES</th>
			<th>TECNIQUES</th>
			<th>INTEGRACIONS</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${not empty registres}">
			<tr>
				<td class="head data">Total</td>
				<td class="head soli total">${totals.solicitudsTotals}</td>
				<td class="head soli loc">${totals.locals}</td>
				<td class="head soli est">${totals.estatals}</td>
				<td class="head inc total">${totals.incidenciesTotals}</td>
				<td class="head inc rol">${totals.rolsIPermisos}</td>
				<td class="head inc cons">${totals.consultes}</td>
				<td class="head inc tec">${totals.tecniques}</td>
				<td class="head inc int">${totals.integracions}</td>
			</tr>
	
		<c:forEach var="registre" items="${registres}">
			<tr>
				<td class="reg data">${registre.data}</td>
				<td class="reg soli total">${registre.solicitudsTotals}</td>
				<td class="reg soli loc">${registre.locals}</td>
				<td class="reg soli est">${registre.estatals}</td>
				<td class="reg inc total">${registre.incidenciesTotals}</td>
				<td class="reg inc rol">${registre.rolsIPermisos}</td>
				<td class="reg inc cons">${registre.consultes}</td>
				<td class="reg inc tec">${registre.tecniques}</td>
				<td class="reg inc int">${registre.integracions}</td>
			</tr>
		</c:forEach>
		</c:if>
	</tbody>
</table>

<style>
.soli.total {
	background-color: #03aef0 !important;
	opacity: 1;
}

.inc.total {
	background-color: orange !important;
}

.soli {
 	background-color: #03aef06b;
/*  	background-color: #5ea4ff;
	 opacity: 0.7; */

}

.inc {
	background-color: #ffa50040;
}

.data {
	padding: 0px 1rem;
	width: 12rem;
}

.head {
	font-weight: bold;
}

#panel-filtros {
	width: 35rem;
	margin: 1rem 6rem;
}

th {
	padding: 0 6px;
}

#dades {
	width: 70rem;
	text-align: center;
	margin: 0rem;
}

#dades tr:hover {
  background-color: #DDD;
}
</style>


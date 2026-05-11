<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
	<h5>
		<fmt:message key="menuoperador" />
	</h5>
	<ul class="tree" style="margin: 3px; padding: 0px;">

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/llistacorreus/list"/>"> <span
				style="${(fn:contains(url, '/llistacorreus'))? "font-weight:bold;" : ""}"><fmt:message
						key="llistatdecorreus" /></span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/fusionarprocediments/elegirProcediments"/>"> <span
				style="${(fn:contains(url, '/fusionarprocediments'))? "font-weight:bold;" : ""}"><fmt:message
						key="fusionarprocediments" /></span>
		</a></li>


		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;">
		<a href="<c:url value="/operador/solicitudlocal/list"/>"> <span
				style="${(fn:contains(url, '/solicitudlocal/'))? "font-weight:bold;" : ""}">
					<fmt:message key="solicitud.local.plural" />
			</span>
		</a> <c:if test="${solicitudsLocalsNoMeves ne 0}">
				<a
					href="<c:url value="/operador/solicitudlocalnollegitnomeu/list"/>">
					<span class="badge badge-warning">${solicitudsLocalsNoMeves}</span>
				</a>
			</c:if> <c:if test="${solicitudsLocalsMeves ne 0}">
				<a href="<c:url value="/operador/solicitudlocalnollegitmeu/list"/>">
					<span class="badge badge-danger">${solicitudsLocalsMeves}</span>
				</a>
			</c:if></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/solicitudlocalpendent/kanban"/>"> <span
				style="${(fn:contains(url, '/solicitudlocalpendent'))? "font-weight:bold;" : ""}">
					<fmt:message key="solicitud.local.pendents" />
			</span>
		</a></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/infoMadrid/list/1"/>">
				<span
				style="${(fn:contains(url, '/infoMadrid'))? "font-weight:bold;" : ""}">Info Madrid</span>
		</a></li>


		<hr style="margin-top: 6px; margin-bottom: 6px;" />
		
		<li style="list-style-type: disc; list-style-position: inside;">
		<a href="<c:url value="/operador/solicitudestatal/list"/>"> <span
				style="${(fn:contains(url, '/solicitudestatal/'))? "font-weight:bold;" : ""}"><fmt:message
						key="solicitud.estatal.plural" /></span>
		</a> <c:if test="${solicitudsEstatalNoMeves ne 0}">
				<a
					href="<c:url value="/operador/solicitudestatalnollegitnomeu/list"/>">
					<span class="badge badge-warning">${solicitudsEstatalNoMeves}</span>
				</a>
			</c:if> <c:if test="${solicitudsEstatalMeves ne 0}">
				<a href="<c:url value="/operador/solicitudestatalnollegitmeu/list"/>">
					<span class="badge badge-danger">${solicitudsEstatalMeves}</span>
				</a>
			</c:if></li>



		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/incidencia/list"/>"> <span
				style="${(fn:contains(url, '/operador/incidencia'))? "font-weight:bold;" : ""}">
					<fmt:message key="incidenciaTecnica.incidenciaTecnica.plural" />
			</span>	</a> 
			<c:if test="${incidenciesNoMeves ne 0}">
				<a href="<c:url value="/operador/incidencianollegitnomeu/list"/>">
					<span class="badge badge-warning">${incidenciesNoMeves}</span>
				</a>
			</c:if> <c:if test="${incidenciesMeves ne 0}">
				<a href="<c:url value="/operador/incidencianollegitmeu/list"/>">
					<span class="badge badge-danger">${incidenciesMeves}</span>
				</a>
			</c:if>
		</li>

 
 		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/pinfo/list"/>"> <span
				style="${(fn:contains(url, '/operador/pinfo'))? "font-weight:bold;" : ""}">
					<fmt:message key="pinfo.pinfo.plural" />
			</span>
		</a>
		
		<c:if test="${pinfosPendents ne 0}">
				<a href="<c:url value="/operador/pinfo/pendent/list"/>">
					<span class="badge badge-danger">${pinfosPendents}</span>
				</a>
			</c:if>
		
		</li> 
		
		<%-- <li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/pinfoData/new"/>"> <span
				style="${(fn:contains(url, '/operador/pinfoData/permisosusuari'))? "font-weight:bold;" : ""}">
					<fmt:message key="pinfoData.permisosusuari" />
			</span>
		</a></li> --%>
		
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/dadespinbal/usuaris"/>"> <span
				style="${(fn:contains(url, '/operador/dadespinbal/usuaris'))? "font-weight:bold;" : ""}">Usuaris PINBAL</span>
		</a></li>
		
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/dadespinbal/procediments"/>"> <span
				style="${(fn:contains(url, '/operador/dadespinbal/procediments'))? "font-weight:bold;" : ""}">Procediments PINBAL</span>
		</a></li>
		
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/dadespinbal/buscadorPinfo"/>"> <span
				style="${(fn:contains(url, '/operador/dadespinbal/buscadorPinfo'))? "font-weight:bold;" : ""}">Consulta de Permisos</span>
		</a></li>
		
		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/queesticfent/list"/>"> <span
				style="${(fn:contains(url, '/operador/queesticfent'))? "font-weight:bold;" : ""}">QueEsticFent</span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/eventsenviats/list"/>"> <span
				style="${(fn:contains(url, '/operador/eventsenviats'))? "font-weight:bold;" : ""}">Correus enviats</span>
		</a></li>

	</ul>
</div>


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
			href="<c:url value="/operador/solicitudactiva/list"/>"> <span
				style="${(fn:contains(url, '/solicitudactiva'))? "font-weight:bold;" : ""}"><fmt:message
						key="solicitud.solicitudactiva.plural" /></span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/solicituddesdefitxer/nou"/>"> <span
				style="${(fn:contains(url, '/solicituddesdefitxer/'))? "font-weight:bold;" : ""}">Sol·licitud
					Local des de Fitxer</span>
		</a></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/solicitudlocal/list"/>"> <span
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

		<hr style="margin-top: 6px; margin-bottom: 6px;" />
		<!-- 
        <li style="list-style-type: disc; list-style-position: inside;"><a
            href="<c:url value="/operador/solicitudestataldesdefitxerxlsx/nou"/>"> <span
                style="${(fn:contains(url, '/solicitudestataldesdefitxerxlsx/'))? "font-weight:bold;" : ""}">Sol·licitud
                    Estatal des de Fitxer XLSX</span>
        </a></li>
 -->
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/solicitudestataldesdefitxers/nou"/>">
				<span
				style="${(fn:contains(url, '/solicitudestataldesdefitxers'))? "font-weight:bold;" : ""}">Sol·licitud
					Estatal des de Fitxer</span>
		</a></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/solicitudestatal/list"/>"> <span
				style="${(fn:contains(url, '/solicitudestatal/'))? "font-weight:bold;" : ""}"><fmt:message
						key="solicitud.estatal.plural" /></span>
		</a></li>
		<hr style="margin-top: 6px; margin-bottom: 6px;" />
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/servei/list"/>"> <span
				style="${(fn:contains(url, '/servei/'))? "font-weight:bold;" : ""}"><fmt:message
						key="servei.servei.plural" /></span>
		</a></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/actualitzarserveis"/>"> <span
				style="${(fn:contains(url, '/operador/actualitzarserveis'))? "font-weight:bold;" : ""}">Actualitzar
					Serveis</span>
		</a></li>

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/correucedents/list"/>"> <span
				style="${(fn:contains(url, '/operador/correucedents'))? "font-weight:bold;" : ""}">Correus cedents</span>
		</a></li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/entitat/list"/>"> <span
				style="${(fn:contains(url, 'entitat'))? "font-weight:bold;" : ""}"><fmt:message
						key="entitat.entitat.plural" /></span>
		</a></li>

        <li style="list-style-type: disc; list-style-position: inside;"><a
            href="<c:url value="/operador/organ/list"/>"> <span
                style="${(fn:contains(url, 'organ'))? "font-weight:bold;" : ""}"><fmt:message
                        key="organ.organ.plural" /></span>
        </a></li>
<%-- 
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/area/list"/>"> <span
				style="text-decoration:line-through;${(fn:contains(url, 'area'))? "font-weight:bold;" : ""} "><fmt:message
						key="area.area.plural" />(Deprecat)</span>
		</a></li>
		
		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/departament/list"/>"> <span
				style="text-decoration:line-through;${(fn:contains(url, 'departament'))? "font-weight:bold;" : ""}"><fmt:message
						key="departament.departament.plural" />(Deprecat)</span>
		</a></li>
 --%>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />


		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/cedent/list"/>"> <span
				style="${(fn:contains(url, '/cedent/'))? "font-weight:bold;" : ""}"><fmt:message
						key="entitatServei.entitatServei.plural" /></span>
		</a></li>


		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/incidencia/list"/>"> <span
				style="${(fn:contains(url, '/incidencia'))? "font-weight:bold;" : ""}">
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

<%-- 		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/tiquet/list"/>"> <span
				style="text-decoration:line-through;${(fn:contains(url, '/tiquet/'))? "font-weight:bold;" : ""}"><fmt:message
						key="tiquet.tiquet.plural" />(Deprecat)</span>
		</a></li>

 --%>
<%-- 		<hr style="margin-top: 6px; margin-bottom: 6px;" />


		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/email/list"/>"> <span
				style="${(fn:contains(url, '/email/'))? "font-weight:bold;" : ""}">Gestió
					de Correus</span>
		</a></li>

 --%>
<%-- 
		<hr style="margin-top: 6px; margin-bottom: 6px;" />


		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/borrarcedentdegrup/list"/>"> <span
				style="${(fn:contains(url, '/operador/borrarcedentdegrup'))? "font-weight:bold;" : ""}">Descartar
					Cedent de Grup</span>
		</a></li>

 --%>
		<hr style="margin-top: 6px; margin-bottom: 6px;" />


		<li style="list-style-type: disc; list-style-position: inside;"><a
			href="<c:url value="/operador/queesticfent/list"/>"> <span
				style="${(fn:contains(url, '/operador/queesticfent'))? "font-weight:bold;" : ""}">QueEsticFent</span>
		</a></li>



<%--         <hr style="margin-top: 6px; margin-bottom: 6px;" />


        <li style="list-style-type: disc; list-style-position: inside;"><a
            href="<c:url value="/operador/tramita/list"/>"> <span
                style="${(fn:contains(url, '/operador/tramit'))? "font-weight:bold;" : ""}">Tramit Sistra</span>
        </a></li> --%>



		<%--
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/option1"/>">
        <span style="${(fn:contains(url, 'option1'))? "font-weight: bold;" : ""}">Menú Option 1</span>
      </a>
    </li>
--%>
		<%-- Example with security: virtual roles  --%>
		<%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/common/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>
		<%--
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
        <a target="_blank" href="/common/option2">
        Last Menu Option (other page)
        </a>
    </li>
   --%>
	</ul>
</div>


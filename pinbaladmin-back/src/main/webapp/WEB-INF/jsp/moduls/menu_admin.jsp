<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
	<h5>Menú Administrador</h5>
	<ul class="tree" style="margin: 3px; padding: 0px;">

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/solicitudactiva/list"/>">
				<span style="${(fn:contains(url, '/solicitudactiva'))? "font-weight:bold;" : ""}">
					<fmt:message key="solicitud.solicitudactiva.plural" />
				</span>
			</a>
		</li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/actualitzarserveis"/>">
				<span style="${(fn:contains(url, '/actualitzarserveis'))? "font-weight:bold;" : ""}">
					Actualitzar Serveis
				</span>
			</a>
		</li>

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/estadistiques/list"/>">
				<span style="${(fn:contains(url, '/estadistiques'))? "font-weight:bold;" : ""}">
					Estadístiques
				</span>
			</a>
		</li>

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/correucedents/list"/>">
				<span style="${(fn:contains(url, '/correucedents'))? "font-weight:bold;" : ""}">
					Correus cedents
				</span>
			</a>
		</li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/servei/list"/>">
				<span style="${(fn:contains(url, '/servei/'))? "font-weight:bold;" : ""}">
					<fmt:message key="servei.servei.plural" />
				</span>
			</a>
		</li>

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/fitxer/list"/>">
				<span style="${(fn:contains(url, '/fitxer/'))? "font-weight:bold;" : ""}">
					<fmt:message key="gestio.fitxers" />
				</span>
			</a>
		</li>

		<hr style="margin-top: 6px; margin-bottom: 6px;" />

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/entitat/list"/>">
				<span style="${(fn:contains(url, 'entitat'))? "font-weight:bold;" : ""}">
					<fmt:message key="entitat.entitat.plural" />
				</span>
			</a>
		</li>

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/organ/list"/>">
				<span style="${(fn:contains(url, 'organ'))? "font-weight:bold;" : ""}">
					<fmt:message key="organ.organ.plural" />
				</span>
			</a>
		</li>

		<li style="list-style-type: disc; list-style-position: inside;">
			<a href="<c:url value="/admin/cedent/list"/>">
				<span style="${(fn:contains(url, '/cedent/'))? "font-weight:bold;" : ""}">
					<fmt:message key="entitatServei.entitatServei.plural" />
				</span>
			</a>
		</li>

	</ul>
</div>


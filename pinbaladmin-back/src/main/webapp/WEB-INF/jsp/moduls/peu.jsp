<%@page import="org.fundaciobit.pinbaladmin.logic.utils.LogicUtils"%>
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Configuracio"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
body {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

#footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 1rem 4rem;
	background-color: #f8f9fa;
	/* border-top: 1px solid #dee2e6; */
	font-size: 0.9rem;
	
	box-shadow: 0 -0.3rem 1rem rgba(0, 0, 0, .15);
}

#peu-container {
	margin-top: auto;
	padding-top: 2rem;
}

#peu-centre {
  text-align: center;
  text-transform: uppercase;
}
</style>


<footer id="footer">


	<div id="peu-esquerra">
		<strong class="font-weight-bold h6"> ${versio.projectName}
			v${versio.version}<%=Configuracio.isCAIB() ? "-caib" : ""%>

		</strong> <br /> <small> Build: ${versio.buildTime} <br /> JDK:
			${version.jdkVersion} <br /> <fmt:message key="revisio" />: <c:if
				test="${empty versio.scmRevision}">
				<fmt:message key="scmversion.msg" />
			</c:if> <c:if test="${not empty versio.scmRevision}">${versio.scmRevision}</c:if>
			<br /> <span style="padding-top: 2px"> <i><fmt:message
						key="desenvolupatper" /></i></span>
		</small>
	</div>

	<div id="peu-centre">
		<a styleClass="text-dark linkPeu" href="/mapaweb"> <fmt:message
				key="labels.mapaweb" />
		</a><br /> <a styleClass="text-dark linkPeu" href="/accessibilitat">
			<fmt:message key="labels.accessibilitat" />
		</a><br /> <a styleClass="text-dark linkPeu" href="/protecciodades">
			<fmt:message key="labels.protecciodades" />
		</a><br /> <a styleClass="text-dark linkPeu" href="/avislegal"> <fmt:message
				key="labels.avislegal" />
		</a>
	</div>

	<div id="peu-dreta">
		<a href="http://otaeweb.ibit.org/" style="padding-top: 10px"
			target="_blank"> <img
			src="<c:url value="/img/fundaciobit-logo-peu.png"/>"
			alt="Fundacio Bit" />
		</a> <br />

		<!-- Button to trigger modal -->
		<small><a href="#modalAjuda" role="button" data-toggle="modal"><fmt:message
					key="ajuda.necessitau" /></a></small>
	</div>


	<!-- Modal -->
	<div id="modalAjuda" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<div class="modal-title h5">
						<fmt:message key="ajuda.titol" />
					</div>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						<fmt:message key="ajuda.missatge" />
					</p>
					<ul>
						<li><fmt:message key="ajuda.viatelefon" />123456789</li>
						<li><fmt:message key="ajuda.viaweb" />http://www.help.hl/help</li>
						<li><fmt:message key="ajuda.viaemail" /> <a
							href="mailto: help@help.hl"> help@help.hl</a></li>
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">
						<fmt:message key="tancar" />
					</button>
				</div>
			</div>
		</div>
	</div>
</footer>
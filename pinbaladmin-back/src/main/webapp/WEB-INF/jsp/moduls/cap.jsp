<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="java.util.Locale"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%><%@ taglib prefix="tiles"
	uri="http://tiles.apache.org/tags-tiles"%>

<style>
#header-nav {
	box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15);
	height: 6rem;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0rem 5rem;
	
	position: relative;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1030;
}

header {
	z-index: 10;
	background-color: #fff;
	margin-bottom: 2rem;
}

#logo-container {
	display: flex;
	align-items: center;
	gap: 0px;
}

#logo-app {
	height: 5.5rem;
	  margin-right: .5rem;
}

#titol-app {
	font-size: 1.6rem;
	color: black;
}

#usuari-info {
	color: black;
	font-size: 20px;
}

.rol-item {
	padding: 0.25rem 1.5rem;
	display: block;
}
</style>

<header>
	<!-- Header -->
	<nav id="header-nav" class="navbar navbar-expand-md navbar-dark fixed-top">

		<button class="navbar-toggler botoMobil" type="button"
			data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Logo i nom aplicació -->
		<div id="logo-container" class="navbar-brand menuGovern">
			<%-- <div class="logoGovern">
				<a href="http://www.fundaciobit.org"> <img
					src="<c:url value="/img/fundaciobit-logo-cap.png"/>"
					alt="FundacioBit-Govern Digital" />
				</a>
			</div> --%>

			<img id="logo-app" src="<c:url value="/img/app-logo.png"/>" alt="PinbalAdmin"
				title="PinbalAdmin" />

			<div>
				<h1 id="titol-app" class="titol"><%=org.fundaciobit.pinbaladmin.commons.utils.StaticVersion.PROJECT_NAME%></h1>
				<div id="usuari-info">
					<strong class="subtitol llevarMobil"><fmt:message
							key="usuari" />: </strong> <span class="subtitolMay"> <%=request.getUserPrincipal() == null ? "ANONIM" : request.getUserPrincipal().getName()%>
						| <%=request.getRemoteUser()%>
					</span>
				</div>
			</div>
		</div>



		<!-- FI Logo i nom aplicació -->

		<!-- Botons -->
		<div class="" id="navbarCollapse">

			<ul class="navbar-nav mobil">

				<%--  XYZ ZZZ  AQUI VAN ELS MENUS   --%>

				<%--  MENU DE ROLS --%>
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false" onclick="canviarPipella/${pipella}">
							<i class="fas fa-address-card fa-lg"></i>
							<fmt:message key="${pipella}" />
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenu1">

						<a class="rol-item ${(empty pipella)?'active' : '' }" 
							href="<c:url value="/canviarPipella/inici" />">
							<fmt:message key="inici" />
						</a>
						
						<sec:authorize access="hasRole('ROLE_USER')">
							<a class="rol-item ${(pipella eq 'operador')?'active' : '' }" 
							href="<c:url value="/canviarPipella/operador" />">
							    <fmt:message key="operador" />	
							</a>
						</sec:authorize>
						
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a class="rol-item ${(pipella eq 'admin')?'active' : '' }" 
						href="<c:url value="/canviarPipella/admin" />">
							<fmt:message key="admin" />
						</a>
					</sec:authorize>
					
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a class="rol-item ${(pipella eq 'webdb')?'active' : '' }" 
						href="<c:url value="/canviarPipella/webdb" />">
							<fmt:message key="webdb" />
						</a>
					</sec:authorize>
					
					<c:if test="${prefixLowercase}:isDesenvolupament()}">
						<a class="rol-item ${(pipella eq 'desenvolupament')?'active' : '' }" href="<c:url value="/canviarPipella/desenvolupament" />">
							<fmt:message key="desenvolupament" />
						</a>
					</c:if>
					
				</div>
			</li>


				<%--  MENU D'IDIOMES, ELS AGAFA DE LA BASE DE DADES--%>
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-language fa-lg"></i>
						<fmt:message key="idiomes" />
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
						<c:forEach var="idioma" items="${idiomes}" varStatus="status">
							<c:set var="idiomaID" value="${idioma.idiomaID}" />
							<a class="dropdown-item" href="?lang=${idiomaID}"> <img
								src="<c:url value="/img/${idiomaID}_petit_${lang eq idiomaID? 'on' : 'off'}.gif"/>"
								alt="${idiomaID}" style="margin-right: 0.5rem;" width="17"
								height="14" border="0" />${idioma.nom}
							</a>
						</c:forEach>

					</div>
				</li>



				<%--   OPCIONS  --%>
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-ellipsis-v"></i>
					</button>
					<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenu3">



						<c:if test="${ empty loginInfo  }">
							<a class="dropdown-item"
								href="<c:url value="/common/principal.html"></c:url>"> <i
								class="fas fa-sign-in-alt"></i> Login
							</a>
						</c:if>
						<c:if test="${ not empty loginInfo  }">

							<a class="dropdown-item"
								href="<c:url value="/configuracio"></c:url>"> <i
								class="fas fa-cog"></i> <fmt:message key="configuracio" />
							</a>

							<a class="dropdown-item" href="<c:url value="/logout"></c:url>">
								<i class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
							</a>
						</c:if>


					</div>
				</li>
			</ul>
		</div>
		<!-- FI Botons -->
	</nav>
	<!-- FI Header -->
</header>


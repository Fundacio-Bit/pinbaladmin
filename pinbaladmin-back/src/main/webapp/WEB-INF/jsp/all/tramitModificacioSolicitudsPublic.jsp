<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.PinfoPublicController"%>
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.PinfoDataPublicController"%>
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.IncidenciaPinfoPublicController"%>

<!--  Cap�alera i peu -->
<div id="header">
	<div id="logo-caib" class="third">
		<img alt="logo-caib"
			src="<c:url value="/img/logo-caib-sistra-blanc.png"/>"
			width="50px">

		<div id="user-info">
			<div id="user-name">
				<strong>Usuari: </strong>
				<c:if test="${empty usuariNIF}"><br></c:if>
				${usuariNom}
			</div>
			<c:if test="${not empty usuariNIF}">
				<div id="user-nif">
					<strong>DNI: </strong>${usuariNIF}
				</div>
			</c:if>
		</div>
	</div>

	<div id="titol-tramit" class="third">

		<c:choose>
		    <c:when test="${not empty isEsmena and isEsmena}">
		        <fmt:message key="tramit.esmenes.title" />
		    </c:when>
		    <c:otherwise>
		        <fmt:message key="tramit.modificacions.title" />
		    </c:otherwise>
		</c:choose>

	</div>
	<div id="button-menu" class="third">
		<a href="www.google.com"/></a>
	</div>
</div>

<div id="footer">
	<p>2026 - IBDigital</p>
</div>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		var body = document.body;
		var header = document.getElementById("header");
	    var footer = document.getElementById("footer");
	    
	    if (header) {
			body.insertBefore(header, body.firstElementChild);
	    }
	    if (footer) {
			body.appendChild(footer); // Mueve el footer al final del body
	    }
		
	    // Ocultar alertas (compatible con/sin jQuery)
	    var alerts = document.querySelectorAll(".alert.alert-success");
	    for (var i = 0; i < alerts.length; i++) {
	        alerts[i].style.display = 'none';
	    }
	});
</script>

<style>

body {
	margin: 0;
	padding: 0;
	/* min-height: 100vh; */
}

#footer {
	margin-top: auto !important; /* Fuerza al footer hacia abajo */
	flex-shrink: 0; /* Evita que el footer se encoja */
}


#header {
	padding: 1rem 4rem;
	text-align: center;
	background-color: #4DBA79;
	color: white;
	display: flex;
	flex-direction: row;
	flex-wrap: nowrap;
	justify-content: space-between;
}

#titol-tramit {
	font-size: 34px;
	font-weight: lighter;
}

#logo-caib {
	display: flex;
	gap: 10px;
	align-items: center;
}

#user-info {
	text-align: left;
}

#button-menu {
	display: flex;
	align-items: center;
	justify-content: flex-end;
}

#button-menu a {
/* 	background-color: #265d3c;
 */	color: white;
	border: none;
	padding: 0.5rem 1rem;
	border-radius: 5px;
	font-weight: lighter;
	text-decoration: none;
}

#button-menu a:hover {
/* 	background-color: #000;
 */}

.third {
	flex: 1;
}

#footer {
	padding: 2rem 1rem;
	text-align: center;
	background-color: #f8f9fa;
	color: #333;
	margin-top: 2rem;
	/* border-top: 3px solid #4DBA79; */
	box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
}


#footer p {
	margin: 0;
	font-size: 0.95rem;
	font-weight: 500;
	letter-spacing: 0.5px;
	color: #555;
}
</style>

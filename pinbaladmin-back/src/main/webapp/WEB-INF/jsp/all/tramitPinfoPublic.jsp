<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.PinfoPublicController"%>
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.PinfoDataPublicController"%>
<%@page import="org.fundaciobit.pinbaladmin.back.controller.all.IncidenciaPinfoPublicController"%>

<!--  Capçalera i peu -->
<div id="header">
	<div id="logo-caib" class="third">
		<img alt="logo-caib"
			src="https://se.caib.es/sistramitfront/resources/1/O1S9IEZ4-ZJLRBRT8-T8SVCTKJ.png"
			width="50px">
		<div id="user-info">
			<div id="user-name">
				<strong>Usuari: </strong>${usuariNom}
			</div>
			<div id="user-nif">
				<strong>DNI: </strong>${usuariNIF}
			</div>
		</div>
	</div>

	<div id="titol-tramit" class="third">Tramit PINFO</div>
	<div id="button-menu" class="third">
		<a href="<%=request.getContextPath() + PinfoPublicController.CONTEXT_WEB %>/list/1"><fmt:message key="tramitpinfo.exit"/></a>
	</div>
</div>

<div id="footer">
	<p>© 2024 - Fundació BIT</p>
</div>

<script>
	$(document).ready(function() {
		var body = document.body;
		var header = document.getElementById("header");
	    var footer = document.getElementById("footer");
	    
		body.insertBefore(header, body.firstElementChild);
		body.appendChild(footer);
		
		$(".alert.alert-success").hide();
	});
</script>

<style>
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
	font-size: 35px;
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
	background-color: #265d3c;
	color: white;
	border: none;
	padding: 0.5rem 1rem;
	border-radius: 5px;
	font-weight: lighter;
	text-decoration: none;
}

#button-menu a:hover {
	background-color: #000;
}

.third {
	flex: 1;
}

#footer {
	padding: 1rem;
	text-align: center;
	background-color: #white;
	color: black;
}
</style>

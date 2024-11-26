<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>


<c:if test="${pinfo.estat == 0}">


	<style>
#pinfoForm {
	margin: 3rem 5rem;
	border: 2px solid black;
	padding: 2rem 2rem;
	border-radius: 4px;
}

#iframePortafib {
	border: 1px solid grey;
	/* padding: 1rem 8rem; */
	padding: 1px;
	border-radius: 4px;
	text-align: center;

	height: 35rem;
	width: 45rem;
}

#framePinfoPDF {
	border: 1px solid grey;
	border-radius: 4px;
	text-align: center;
	width: 100%;
	margin-right: 0;
	padding: 1px;
}

#fitxerPDF {
	width: 100%;
	height: 100%;
}

.module_content {
	display: flex;
	margin-bottom: 1rem;
	gap: 2rem;
}

iframe{
	border: none;
}

</style>
	<div id="iframePortafib">
		<iframe src="http://ptrias:8080/pinbaladmin/public/pinfo/crearflux" width="100%" height="100%">
		</iframe>
	</div>

	<div id="framePinfoPDF">
		<object id="fitxerPDF" type="application/pdf" data="${urlPinfoPDF}"></object>
	</div>

	<script>
		var iframePortafib = document.getElementById('iframePortafib');
		var pinfoTable = document.getElementById('pinfo_tableid');
		pinfoTable.after(iframePortafib);

		var framePinfoPDF = document.getElementById('framePinfoPDF');
		var tab_container = document.getElementsByClassName('tab_container')[0];
		tab_container.after(framePinfoPDF);

		var v = document.getElementById("pinfo.incidenciaID");
		var titolIncidencia = v.nextSibling.value;
		var label_titol = document.getElementById("pinfoForm").firstElementChild.firstElementChild;
		label_titol.innerHTML = titolIncidencia;

/* 		var inputDestinatari = document.getElementById("pinfo.destinatariNIF");
		inputDestinatari.style.minWidth = "20rem"
		iframePortafib.append(inputDestinatari);
 */
		document.getElementById("pinfo_tableid").style.display = "none"
	</script>
</c:if>

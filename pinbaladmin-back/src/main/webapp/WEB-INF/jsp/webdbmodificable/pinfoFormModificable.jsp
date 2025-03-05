<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js"></script>


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

#fitxerPDF {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    width: 100%;
}
canvas {
    border: 1px solid #ccc;
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
}

iframe{
	border: none;
}

</style>
<!-- 	<div id="iframePortafib">
 		<iframe src="http://ptrias:8080/pinbaladmin/public/pinfo/crearflux" width="100%" height="100%">
		</iframe> 
	</div>
 -->	
	<div id="fitxerPDF"></div>

<%-- 
	<div id="framePinfoPDF">
		<object id="fitxerPDF" type="application/pdf" data="${urlPinfoPDF}"></object>
		<iframe id="fitxerPDF" src="${urlPinfoPDF}" width="100%" height="600px"></iframe> 
	</div>
--%>

	<script>
/* 	var framePinfoPDF = document.getElementById('framePinfoPDF');
	var tab_container = document.getElementsByClassName('tab_container')[0];
	tab_container.after(framePinfoPDF);
 */	
	document.getElementById("pinfo_tableid").style.display = "none"
	
    var url = "${urlPinfoPDF}";
    
    pdfjsLib.getDocument(url).promise.then(function(pdf) {
        var container = document.getElementById("fitxerPDF");

        for (let pageNum = 1; pageNum <= pdf.numPages; pageNum++) {
            pdf.getPage(pageNum).then(function(page) {
                var scale = 1.5;
                var viewport = page.getViewport({ scale: scale });
                var canvas = document.createElement("canvas");
                var context = canvas.getContext("2d");
                canvas.height = viewport.height;
                canvas.width = viewport.width;
                
                var renderContext = { canvasContext: context, viewport: viewport };
                page.render(renderContext);

                container.appendChild(canvas);
            });
        }
    });

/* 		var iframePortafib = document.getElementById('iframePortafib');
		var pinfoTable = document.getElementById('pinfo_tableid');
		pinfoTable.after(iframePortafib);

		var framePinfoPDF = document.getElementById('framePinfoPDF');
		var tab_container = document.getElementsByClassName('tab_container')[0];
		tab_container.after(framePinfoPDF);

		var v = document.getElementById("pinfo.incidenciaID");
		var titolIncidencia = v.nextSibling.value;
		var label_titol = document.getElementById("pinfoForm").firstElementChild.firstElementChild;
		label_titol.innerHTML = titolIncidencia;
 */
/* 		var inputDestinatari = document.getElementById("pinfo.destinatariNIF");
		inputDestinatari.style.minWidth = "20rem"
		iframePortafib.append(inputDestinatari);
 */
/* 		document.getElementById("pinfo_tableid").style.display = "none" */
	</script>
</c:if>

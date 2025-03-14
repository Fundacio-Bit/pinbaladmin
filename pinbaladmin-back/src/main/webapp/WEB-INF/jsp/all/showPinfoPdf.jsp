<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.min.js"></script>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>
#title {
	text-align: center;
}

.botonera {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin: 20px;
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
</style>

</head>
<body>

    <h3 id="title">PINFO ${pinfo.pinfoID}</h3>

	<div class="botonera">
		<a href="${urlFirmarPinfo}" class="btn btn-primary">Enviar a firmar</a>
		<a href="${urlPinfoPDF}" class="btn btn-primary">Descargar PDF</a>
	</div>

	<div id="fitxerPDF"></div>


	<script>
		var url = "${urlPinfoPDF}";

		pdfjsLib.getDocument(url).promise.then(function(pdf) {
			var container = document.getElementById("fitxerPDF");

			for (let pageNum = 1; pageNum <= pdf.numPages; pageNum++) {
				pdf.getPage(pageNum).then(function(page) {
					var scale = 1.5;
					var viewport = page.getViewport({
						scale : scale
					});
					var canvas = document.createElement("canvas");
					var context = canvas.getContext("2d");
					canvas.height = viewport.height;
					canvas.width = viewport.width;

					var renderContext = {
						canvasContext : context,
						viewport : viewport
					};
					page.render(renderContext);

					container.appendChild(canvas);
				});
			}
		});
	</script>

</body>
</html>
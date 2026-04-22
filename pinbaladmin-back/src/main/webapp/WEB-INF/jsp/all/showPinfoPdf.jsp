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
.container-pdf {
  	width: fit-content;
  	margin: 1rem auto;
	background: white;
	padding: 2rem;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	border: none;
}

/* Headers usan clases estándar de tramitPinfoPublic.jsp */

.icono-pdf {
	font-size: 2.5rem;
	color: #4DBA79;
	margin-bottom: 0.5rem;
	text-align: center;
}

.btn-primary-custom {
	background-color: #4DBA79;
	color: white;
	padding: 12px 28px;
	border: none;
	border-radius: 5px;
	font-size: 15px;
	font-weight: 500;
	cursor: pointer;
	transition: all 0.3s;
	text-decoration: none;
	display: inline-block;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-primary-custom:hover {
	background-color: #265d3c;
	color: white;
	text-decoration: none;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	transform: translateY(-1px);
}

.btn-secondary-custom {
	background-color: #6c757d;
	color: white;
	padding: 12px 28px;
	border: none;
	border-radius: 5px;
	font-size: 15px;
	font-weight: 500;
	cursor: pointer;
	transition: all 0.3s;
	text-decoration: none;
	display: inline-block;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-secondary-custom:hover {
	background-color: #5a6268;
	color: white;
	text-decoration: none;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	transform: translateY(-1px);
}

#fitxerPDF {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 15px;
	width: 100%;
}

canvas {
	border: 1px solid #ddd;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 4px;
}

.botonera-pdf {
	display: flex;
	justify-content: center;
	gap: 1rem;
	margin-bottom: 2rem;
}

.titol-pdf{
	justify-content: center;
  	margin-bottom: 0.5rem;
    border: none;
}

</style>

</head>
<body>

	<div class="container-pdf">
		<div class="icono-pdf">
			<i class="fas fa-file-pdf"></i>
		</div>
		
		<div class="titol-tramit-pinfo-header titol-pdf">
			<div class="titol-tramit-pinfo-container">
				<h3 class="titol-tramit-pinfo"><fmt:message key="tramit.pinfo.pdf.vistaprevia"/> ${pinfo.incidenciaID}</h3>
			</div>
		</div>
		<div class="titol-tramit-pinfo-botonera botonera-pdf">
			<a href="${urlFirmarPinfo}" class="btn-primary-custom">
				<i class="fas fa-paper-plane"></i> Enviar a firmar
			</a>
			<a href="${urlPinfoPDF}" class="btn-secondary-custom">
				<i class="fas fa-download"></i> Descargar PDF
			</a>
		</div>

		<div id="fitxerPDF"></div>
	</div>


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
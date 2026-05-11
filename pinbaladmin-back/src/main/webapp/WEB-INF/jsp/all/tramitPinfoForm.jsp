<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Afegir permisos a la solicitud</title>

<style>
/* ========================================
   WIZARD - INDICADOR DE PASOS
   ======================================== */
.step-indicator {
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 1rem auto 1.5rem auto;
	gap: 24px;
}

.step {
	display: flex;
	align-items: center;
	gap: 12px;
}

.step-circle {
	width: 44px;
	height: 44px;
	border-radius: 50%;
	background-color: #e9ecef;
	color: #6c757d;
	display: flex;
	align-items: center;
	justify-content: center;
	font-weight: 600;
	font-size: 18px;
	transition: all 0.3s ease;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.step-circle.active {
	background-color: #4DBA79;
	color: white;
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3);
	transform: scale(1.1);
}

.step-circle.completed {
	background-color: #265d3c;
	color: white;
}

.step-label {
	font-size: 15px;
	color: #6c757d;
	font-weight: 500;
}

.step-label.active {
	color: #4DBA79;
	font-weight: 600;
}

.step-separator {
	width: 80px;
	height: 3px;
	background-color: #e9ecef;
	border-radius: 2px;
}

.step-separator.completed {
	background-color: #4DBA79;
}

/* ========================================
   CONTENEDOR PRINCIPAL
   ======================================== */
#form-container {
	background: white;
	padding: 2rem 3rem;
	border-radius: 16px;
	margin: 0 3rem;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	border: none;
}

#form-content {
	display: flex;
	margin: 0 1rem;
}

section {
	padding: 0 1rem;
	min-width: 50rem;
	width: 100%;
	max-width: 100%;
	display: flex;
	flex-flow: column;
}

/* ========================================
   HEADERS DE SECCIÓN - Usando clases estándar de tramitPinfoPublic.jsp
   ======================================== */

.sub-title {
	font-size: 18px;
	margin: 0.5rem 0 1rem 0;
	color: #265d3c;
	font-weight: 600;
}

/* ========================================
   SUBTÍTULO Y LISTA DE USUARIOS
   ======================================== */
.usuaris-titulo {
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 0.75rem;
	color: #265d3c;
}

.usuaris-lista {
	font-size: 14px;
	background: linear-gradient(135deg, #f0f9f4 0%, #f8f9fa 100%);
	padding: 14px 16px;
	border-radius: 8px;
	border-left: 4px solid #4DBA79;
	line-height: 1.6;
}

/* ========================================
   SPINNER
   ======================================== */
.spinner {
	border: 4px solid #f3f3f3;
	border-top: 4px solid #4DBA79;
	border-radius: 50%;
	width: 40px;
	height: 40px;
	animation: spin 1s linear infinite;
	margin: 20px auto;
}

@keyframes spin {
	0% { transform: rotate(0deg); }
	100% { transform: rotate(360deg); }
}

/* ========================================
   BOTONES (específicos de este formulario)
   ======================================== */

.btn-primary-custom {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%);
	color: white;
	padding: 10px 28px;
	border: none;
	border-radius: 8px;
	font-size: 15px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.2);
}

.btn-primary-custom:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #265d3c 100%);
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3);
	transform: translateY(-2px);
}

.btn-secondary-custom {
	background-color: white;
	color: #6c757d;
	padding: 10px 28px;
	border: 2px solid #e9ecef;
	border-radius: 8px;
	font-size: 15px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: none;
}

.btn-secondary-custom:hover {
	background-color: #f8f9fa;
	border-color: #ced4da;
	color: #495057;
	transform: translateY(-1px);
}

/* ========================================
   INPUTS
   ======================================== */
.input-container.user, .input-container.procediment {
	width: 100%;
	margin-top: 0;
}

#cercador-usuaris, #cercador-procediments {
	position: relative;
	margin-bottom: 0.75rem;
}

#input-usuari-container {
	display: flex;
	width: 100%;
	margin-bottom: 0.5rem;
}

.campsUsuari {
	width: 100% !important;
}

#input-usuari-container input, #procedimentID {
	width: 100%;
	padding: 12px 16px;
	border: 2px solid #e9ecef;
	border-radius: 10px;
	font-size: 15px;
	transition: all 0.3s ease;
	background: white;
	color: #333;
}

#input-usuari-container input:focus, #procedimentID:focus {
	border-color: #4DBA79;
	outline: none;
	box-shadow: 0 0 0 4px rgba(77, 186, 121, 0.1);
	background: #fafafa;
}

#input-usuari-container input::placeholder, #procedimentID::placeholder {
	color: #adb5bd;
	font-style: normal;
}

/* ========================================
   AUTOCOMPLETE
   ======================================== */
#autocomplete-procediments, #autocomplete-usuaris {
	display: block;
	position: absolute;
	z-index: 1000;
	background-color: white;
	border: none;
	border-radius: 12px;
	max-height: 280px;
	overflow-y: auto;
	width: 100%;
	box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
	margin-top: 8px;
}

#autocomplete-procediments.hidden, #autocomplete-usuaris.hidden {
	display: none;
}

.procediment-item, .usuari-item {
	cursor: pointer;
	padding: 14px 18px;
	background-color: white;
	border-radius: 0;
	border: none;
	border-bottom: 1px solid #f8f9fa;
	transition: all 0.2s;
	font-size: 14px;
}

.procediment-item:first-child {
	border-radius: 12px 12px 0 0;
}

.procediment-item:last-child, .usuari-item:last-child {
	border-radius: 0 0 12px 12px;
	border-bottom: none;
}

.procediment-item:hover, .usuari-item:hover {
	background: linear-gradient(90deg, #f0f9f4 0%, #f8f9fa 100%);
	border-left: 3px solid #4DBA79;
	padding-left: 15px;
}

/* ========================================
   LISTAS DE ITEMS SELECCIONADOS
   ======================================== */
#llistat-procediments ul, #llistat-usuaris ul {
	margin-top: 0.5rem;
	list-style: none;
	padding-left: 0;
}

.usuari-li, .procediment-li {
	padding: 12px 16px;
	margin: 6px 0;
	border: none;
	border-radius: 10px;
	background: linear-gradient(135deg, #f0f9f4 0%, #f8f9fa 100%);
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.04);
	transition: all 0.3s;
}

.usuari-li:hover, .procediment-li:hover {
	background: linear-gradient(135deg, #e8f5e9 0%, #f0f9f4 100%);
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	transform: translateX(4px);
}

.usuari-data-container, .procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.usuari-data-text, .procediment-data-text {
	margin-right: 1rem;
	font-size: 14px;
	color: #333;
	font-weight: 500;
}

.usuari-data-delete, .procediment-data-delete {
	cursor: pointer;
	color: #dc3545;
	font-size: 18px;
	transition: all 0.2s;
	padding: 4px;
	border-radius: 4px;
}

.usuari-data-delete:hover, .procediment-data-delete:hover {
	color: white;
	background-color: #dc3545;
	transform: scale(1.1);
}

/* ========================================
   TABLA DE SERVICIOS
   ======================================== */
#taula-serveis {
	width: 100%;
	border-collapse: collapse;
	margin-top: 0.75rem;
	border: 1px solid #ddd;
}

#taula-serveis td {
	border: 1px solid #ddd;
}

#titol-procediments, #titol-serveis {
	background-color: #f5f5f5;
	color: #333;
	font-weight: 600;
	padding: 10px;
	text-align: center;
	font-size: 13px;
}

#titol-serveis {
	width: 160px;
}

.marcarAll {
	background-color: #fafafa;
	font-weight: 600;
	padding: 8px;
	text-align: center;
	color: #555;
	font-size: 12px;
}

.keyProc, .keyServ {
	background-color: #fafafa;
	font-weight: 500;
	padding: 8px;
	font-size: 13px;
	position: relative;
	cursor: help;
	color: #333;
}

.keyProc:hover, .keyServ:hover {
	background-color: #f0f0f0;
}

.keyProc {
	text-align: center;
	word-break: break-word;
}

.keyServ {
	width: 160px;
	text-align: left;
	padding-left: 10px;
}

.tooltip {
	position: absolute;
	background-color: #333;
	color: white;
	padding: 6px 10px;
	border-radius: 4px;
	pointer-events: none;
	opacity: 0;
	transition: opacity 0.2s;
	z-index: 1000;
	font-size: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
	max-width: 250px;
	word-wrap: break-word;
}

.solSer {
	text-align: center;
	background-color: white;
	cursor: pointer;
	transition: background-color 0.15s;
	padding: 12px;
}

.solSer.selected {
	background-color: #e8f5e9;
}

.solSer:hover {
	background-color: #f9f9f9;
}

.solSer.selected:hover {
	background-color: #d4ecd6;
}

/* ========================================
   CHECKBOXES PERSONALIZADOS
   ======================================== */
.solSer input[type="checkbox"],
.marcarAll input[type="checkbox"] {
	appearance: none;
	-webkit-appearance: none;
	width: 18px;
	height: 18px;
	border: 2px solid #bbb;
	border-radius: 3px;
	background-color: white;
	cursor: pointer;
	position: relative;
	transition: all 0.15s;
	outline: none;
}

.solSer input[type="checkbox"]:hover {
	border-color: #4DBA79;
}

.solSer input[type="checkbox"]:checked {
	background-color: #4DBA79;
	border-color: #4DBA79;
}

.solSer input[type="checkbox"]:checked::after {
	content: '✓';
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: white;
	font-weight: bold;
	font-size: 12px;
}

.marcarAll input[type="checkbox"] {
	width: 18px;
	height: 18px;
}

.marcarAll input[type="checkbox"]:checked {
	background-color: #555;
	border-color: #555;
}

.marcarAll input[type="checkbox"]:checked::after {
	content: '✓';
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: white;
	font-weight: bold;
	font-size: 12px;
}

#llistat-procediments ul, #llistat-usuaris ul {
	max-height: 300px;
	overflow-y: auto;
	padding-right: 8px;
}

/* Scrollbar personalizado */
#llistat-procediments ul::-webkit-scrollbar,
#llistat-usuaris ul::-webkit-scrollbar,
#autocomplete-procediments::-webkit-scrollbar,
#autocomplete-usuaris::-webkit-scrollbar {
	width: 8px;
}

#llistat-procediments ul::-webkit-scrollbar-track,
#llistat-usuaris ul::-webkit-scrollbar-track,
#autocomplete-procediments::-webkit-scrollbar-track,
#autocomplete-usuaris::-webkit-scrollbar-track {
	background: #f8f9fa;
	border-radius: 4px;
}

#llistat-procediments ul::-webkit-scrollbar-thumb,
#llistat-usuaris ul::-webkit-scrollbar-thumb,
#autocomplete-procediments::-webkit-scrollbar-thumb,
#autocomplete-usuaris::-webkit-scrollbar-thumb {
	background: #ced4da;
	border-radius: 4px;
}

#llistat-procediments ul::-webkit-scrollbar-thumb:hover,
#llistat-usuaris ul::-webkit-scrollbar-thumb:hover,
#autocomplete-procediments::-webkit-scrollbar-thumb:hover,
#autocomplete-usuaris::-webkit-scrollbar-thumb:hover {
	background: #4DBA79;
}
</style>

<script>
	// Variables de traducción para JavaScript
	var MSG_USUARIS_ALMENYSUN = '<fmt:message key="tramit.pinfo.usuaris.almenysun"/>';
	var MSG_PROCEDIMENTS_ALMENYSUN = '<fmt:message key="tramit.pinfo.procediments.almenysun"/>';
	var MSG_USUARIS_DUPLICAT = '<fmt:message key="tramit.pinfo.usuaris.duplicat"/>';
	var MSG_PROCEDIMENTS_DUPLICAT = '<fmt:message key="tramit.pinfo.procediments.duplicat"/>';
	var MSG_SERVEIS_ALMENYSUN = '<fmt:message key="tramit.pinfo.serveis.almenysun"/>';
</script>

</head>
<body>
	<!-- Indicador de pasos -->
	<div class="step-indicator">
		<div class="step">
			<div class="step-circle" id="step-circle-1">1</div>
			<span class="step-label" id="step-label-1"><fmt:message key="tramit.pinfo.step.usuaris"/></span>
		</div>
		<div class="step-separator" id="separator-1"></div>
		<div class="step">
			<div class="step-circle" id="step-circle-2">2</div>
			<span class="step-label" id="step-label-2"><fmt:message key="tramit.pinfo.step.procediments"/></span>
		</div>
		<div class="step-separator" id="separator-2"></div>
		<div class="step">
			<div class="step-circle" id="step-circle-3">3</div>
			<span class="step-label" id="step-label-3"><fmt:message key="tramit.pinfo.step.permisos"/></span>
		</div>
	</div>

	<div id="form-container">
		<form id="pinfoDataForm" action="procesarPermisos" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="usuaris"> <input type="hidden"
				name="procediments"> <input type="hidden"
				name="solicitudServeis">


			<div id="form-content">

				<section id="section1">
				<div class="titol-tramit-pinfo-header">
					<div class="titol-tramit-pinfo-container">
						<h3 class="titol-tramit-pinfo"><i class="fas fa-users"></i> <fmt:message key="tramit.pinfo.usuaris.introduir"/></h3>
					</div>
					<div class="titol-tramit-pinfo-botonera">
							<button type="button" class="btn-primary-custom" onclick="next();">
								<fmt:message key="tramitpinfodata.sec1.seguent"/> <i class="fas fa-arrow-right"></i>
							</button>
						</div>
					</div>
					
					<!-- Avís Paso 1: Usuarios -->
					<div class="aviso-tip">
						<i class="fas fa-lightbulb"></i>
						<strong>Consell:</strong> Pots cercar usuaris per NIF, codi d'usuari o nom. Escriu almenys 2 caràcters per iniciar la cerca.
					</div>
					
					<div class="input-container user">
						<div id="cercador-usuaris">
							<div id="input-usuari-container">
								<input id="usuariNom" name="userID" type="text"
									autocomplete="off" class="campsUsuari"
									placeholder="<fmt:message key="tramit.pinfo.usuaris.placeholder"/>">
							</div>
							<div id="autocomplete-usuaris" class="hidden"></div>
						</div>
						<div id="llistat-usuaris">
							<ul></ul>
						</div>
					</div>
				</section>

				<section id="section2">
				<div class="titol-tramit-pinfo-header">
					<div class="titol-tramit-pinfo-container">
						<h3 class="titol-tramit-pinfo"><i class="fas fa-list-alt"></i> <fmt:message key="tramit.pinfo.procediments.introduir"/></h3>
					</div>
					<div class="titol-tramit-pinfo-botonera">
							<button type="button" class="btn-primary-custom" onclick="prev();">
								<i class="fas fa-arrow-left"></i> <fmt:message key="tramitpinfodata.sec2.anterior"/>
							</button>
							<button type="button" class="btn-primary-custom" onclick="next();">
								<fmt:message key="tramitpinfodata.sec2.seguent"/> <i class="fas fa-arrow-right"></i>
							</button>
						</div>
					</div>
					
					<!-- Avís Paso 2: Procedimientos -->
					<div class="aviso-tip">
						<i class="fas fa-lightbulb"></i>
						<strong>Consell:</strong> Escriu el nom o codi del procediment. Si no apareix el que busques, potser hauries de fer un tramit nou per donar d'alta el procediment.
					</div>
					
					<div class="input-container procediment">
						<div id="cercador-procediments">
							<input id="procedimentID" name="procedimentID" type="text"
								autocomplete="off"
								placeholder="<fmt:message key="tramit.pinfo.procediments.placeholder"/>">
							<div id="autocomplete-procediments" class="hidden"></div>
						</div>
						<div id="llistat-procediments">
							<ul></ul>
						</div>
					</div>
				</section>

				<section id="section3">
				<div class="titol-tramit-pinfo-header">
					<div class="titol-tramit-pinfo-container">
						<h3 class="titol-tramit-pinfo"><i class="fas fa-check-square"></i> <fmt:message key="tramit.pinfo.permisos.assignar"/></h3>
					</div>
					<div class="titol-tramit-pinfo-botonera">
							<button type="button" class="btn-primary-custom" onclick="prev();">
								<i class="fas fa-arrow-left"></i> <fmt:message key="tramitpinfodata.sec3.anterior"/>
							</button>
							<input type="submit" class="btn-primary-custom" value="<fmt:message key="tramitpinfodata.sec3.seguent"/>">
						</div>
					</div>
					
					<div id="subtitle-usuaris" class="sub-title"></div>
					
					<!-- Avís Paso 3: Servicios -->
					<div class="aviso-important">
						<i class="fas fa-exclamation-circle"></i>
						<strong>Important:</strong> Marca els serveis que vols sol·licitar a la taula inferior. Els permisos seleccionats s'aplicaran a <strong>tots els usuaris</strong> que has afegit al pas 1.
						<br><small style="margin-top: 6px; display: block;">Després podràs revisar i eliminar permisos individuals si cal.</small>
					</div>
					
					<div class="input-container servei">
						<div id="taula-serveis-cont">
						<table id="taula-serveis"></table>
				</section>

		</div>
		</form>
	</div>

	<script type="text/javascript">
		var actualSection = 1;
		showSection(actualSection);

		var usuaris = [];
		var procediments = [];
		var solicitudServeis = [];

		$(document).ready(function() {

		let debounceTimerProc;

		$("#procedimentID").on("input", function() {
			clearTimeout(debounceTimerProc); // Limpiar el timer anterior

			debounceTimerProc = setTimeout(function() {
				var procediment = $("#procedimentID").val();
				console.log(procediment);
				if (procediment.length < 2) { 
					$("#autocomplete-procediments").empty().addClass("hidden");
					return; 
				}
				
				// Mostrar spinner mientras se carga
				$("#autocomplete-procediments").html("<div class='spinner'></div>").removeClass("hidden");
				
				$.ajax({
	                url : "jsonProcediments",
	                type : "GET",
	                data : { query : procediment },
	                success : function(data) {
	                    $("#autocomplete-procediments").empty();
	                    
	                    if (data.length === 0) {
	                        $("#autocomplete-procediments").html("<div style='padding: 10px; color: #666;'><fmt:message key='tramit.pinfo.procediments.notrobats'/></div>").removeClass("hidden");
	                    } else {
	                        data.forEach(function(proc) {
	                            afegirProcediment(proc);
	                        });
	                        $("#autocomplete-procediments").removeClass("hidden");
	                    }
	                },
	                error : function() {
	                    $("#autocomplete-procediments").empty().addClass("hidden");
	                }
	            });
			}, 500); // Esperar 500ms después de que el usuario deje de escribir
		});
		
		let debounceTimer;
		
		$(".campsUsuari").on("input", function() {
		    clearTimeout(debounceTimer); // Limpiar el timer anterior
		    
		    debounceTimer = setTimeout(function() {
		        var nom = $("#usuariNom").val();

		        console.log("nom: " + nom);
		        if (nom.length < 3) {
		            $("#autocomplete-usuaris").empty().addClass("hidden");
		            return;
		        }

		        // Mostrar spinner y eliminar clase hidden
		        $("#autocomplete-usuaris").html("<div class='spinner'></div>").removeClass("hidden");

		        $.ajax({
		            url : "jsonUsuaris",
		            type : "GET",
		            data : { nom : nom },
		            success : function(data) {
		                $("#autocomplete-usuaris").empty().removeClass("hidden");
		                
		                if (data == null) {
			                $("#autocomplete-usuaris").html("<div style='padding: 10px; color: red;'><fmt:message key='tramit.pinfo.usuaris.mescinccents'/></div>");
                        } else if (data.length === 0) {
		                    $("#autocomplete-usuaris").html("<div style='padding: 10px; color: #666;'><fmt:message key='tramit.pinfo.usuaris.notrobats'/></div>");
		                } else {
		                    data.forEach(function(usuari) {
		                        // Si el usuari ja està a la llista, no el mostri
		                        afegirUsuari(usuari);
		                    });
		                }
		            },
		            error : function() {
		                $("#autocomplete-usuaris").html("<div style='padding: 10px; color: red;'><fmt:message key='tramit.pinfo.usuaris.mescinccents'/></div>");
		            }
		        });
		    }, 500); // Espera 500ms antes de hacer la petición
		});
		});
		
		function afegirProcediment(proc) {
			var procedimentDiv = document.createElement("div");
            procedimentDiv.classList.add("procediment-item");
            procedimentDiv.innerHTML = proc.key + " - " + proc.value;
            procedimentDiv.onclick = function() {
                elegirProcediment(proc);
            };
            $("#autocomplete-procediments").append(procedimentDiv);
        }
		
/* 		function convertirUsuariEnUser(usuari) {
			            console.log(usuari);
            return { key : usuari.nif, value : usuari.nom, nom : usuari.nom, codi : usuari.codi };
		} */
		
		function afegirUsuari(usuari) {
            var usuariDiv = document.createElement("div");
            usuariDiv.classList.add("usuari-item");
            usuariDiv.innerHTML = usuari.administrationID + " - " + usuari.name + " " + usuari.surname1;
            usuariDiv.onclick = function() {
                elegirUsuari(usuari);
            };

            $("#autocomplete-usuaris").append(usuariDiv);
        }
		
		function elegirProcediment(proc) {
			            for (let i = 0; i < procediments.length; i++) {
                if (procediments[i].key == proc.key) {
                    alert(MSG_PROCEDIMENTS_DUPLICAT);
                    return;
                }
            }

            procediments.push(proc);
            let li = $("<li></li>").addClass("procediment-li");
            let container = $("<div></div>").addClass("procediment-data-container");
            
            let spanText = $("<span></span>").addClass("procediment-data-text").text(proc.key + " - " + proc.value);
            
            let spanDelete = $("<span></span>").addClass("procediment-data-delete").html('<i class="fas fa-times"></i>').click(function() {
                procediments = procediments.filter(function(p) {
                    return p.id != proc.id;
                });
                li.remove();
            });
            
            container.append(spanText);
            container.append(spanDelete);
            li.append(container);
            
            $("#llistat-procediments ul").append(li);
            $("input[name='procedimentID']").val("");
            $("#autocomplete-procediments").empty().addClass("hidden");
		}
		
		function elegirUsuari(usuari) {
			console.log(usuari);
            for (let i = 0; i < usuaris.length; i++) {
                if (usuaris[i].username == usuari.username) {
                    alert(MSG_USUARIS_DUPLICAT);
                    return;
                }
            }
            
            usuaris.push(usuari);
            
            let li = $("<li></li>").addClass("usuari-li");
            let container = $("<div></div>").addClass("usuari-data-container");
            
            let text = usuari.administrationID + " - " + usuari.name + " " +  usuari.surname1 + " - " + usuari.username;
            let spanText = $("<span></span>").addClass("usuari-data-text").text(text);
            
            let spanDelete = $("<span></span>").addClass("usuari-data-delete").html('<i class="fas fa-times"></i>').click(function() {
                usuaris = usuaris.filter(function(u) {
                    return u.username != usuari.username;
                });
                li.remove();
            });
            
            container.append(spanText);
            container.append(spanDelete);
            li.append(container);
            
            $("#llistat-usuaris ul").append(li);
            $(".campsUsuari").val("");
            $("#autocomplete-usuaris").empty().addClass("hidden");
        }
		
		function next() {
			//if actualSection is okey, then go to, else, show error

			if (actualSection == 1) {
				if ($("#llistat-usuaris ul li").length == 0) {
				alert(MSG_USUARIS_ALMENYSUN);
				return;
			}
		}

		if (actualSection == 2) {
			if ($("#llistat-procediments ul li").length == 0) {
				alert(MSG_PROCEDIMENTS_ALMENYSUN);
				return;
			}

				//Actualitzar taula de serveis:
				$("#taula-serveis").empty();

				//per cada procediment de la llista de procediments, fer una crida ajax per obtenir els serveis, i afegir-los a la taula.
				//La primera columna será amb el procediment, i les altres, per tots els serveis.
				var serveisTrobats = [];
				var allSoliServ = [];

				let procedimentsConsultats = 0;
				procediments.forEach(function(procediment) {
					$.ajax({
						url : "jsonServeisProcediment",
						type : "GET",
						data : {
							procedimentID : procediment.id
						},
						success : function(data) {
							var serveisSoli = [];
							data.forEach(function(solSer) {
								var found = false;
								for (let i = 0; i < serveisTrobats.length; i++) {
                                    if (serveisTrobats[i].key == solSer.key) {
                                        found = true;
                                        break;
                                    }
                                }
								
								if (!found) {
									serveisTrobats.push(solSer);
								}
								serveisSoli.push(solSer);
							});
							allSoliServ.push(serveisSoli);
							procedimentsConsultats++;
						}
					});
				});

				//Esperar a que totes les crides ajax hagin acabat
				var interval = setInterval(function() {
					if (procedimentsConsultats == procediments.length) {
						clearInterval(interval);
						construyeTablaServicios(serveisTrobats, allSoliServ);
					}
				}, 100);
			}
			showSection(++actualSection);
		}

		function construyeTablaServicios(serveisTrobats, allSoliServ) {

			generarSubtitolUsuaris();
			
			//Cream una primera fila amb els procediments, i despres de cada un, es mostren els serveis d'aquest
			var trTitol = $("<tr></tr>");	
			trTitol.append("<td id=\"titol-serveis\" rowspan=\"2\">SERVEIS</td>");
			trTitol.append("<td id=\"titol-procediments\" colspan=\"" + procediments.length + "\" style=\"text-align: center;\">PROCEDIMENTS</td>");
			
			$("#taula-serveis").append(trTitol);
			
 			var tr = $("<tr></tr>");
 			procediments.forEach(function(procediment) {
		        var td = $("<td></td>").addClass("keyProc").attr("data-tooltip", procediment.value).text(procediment.key);
		        tr.append(td);
			});
			$("#taula-serveis").append(tr);

			serveisTrobats.forEach(function(servei) {
                var tr = $("<tr></tr>");
                var td = $("<td></td>").addClass("keyServ").attr("data-tooltip", servei.value).text(servei.key);
                tr.append(td);
                
				for (let i = 0; i < procediments.length; i++) {
					var procediment = procediments[i];
                    var serveisSoli = allSoliServ[i];
                    var found = false;
                    serveisSoli.forEach(function(serveiSoli) {
                        if (serveiSoli.key == servei.key) {
                        	var tdSolSer = $("<td></td>").attr("value", serveiSoli.id).addClass("solSer"); // noSelected")//.attr("onclick", "seleccionaServei(this)");//.text("check");
                        	//Afegir input check per seleccionar procediment-servei
                        	
                        	var checkbox = $("<input class='solSerInput' id='" + serveiSoli.id + "'  type='checkbox' onchange='marcarSolSer(this)'>");
                        	checkbox.on("click", function(e) {
                        		e.stopPropagation();
                        	});
                        	tdSolSer.append(checkbox);
                        	
                        	tdSolSer.attr("proc", procediment.key);
                        	tdSolSer.attr("serv", servei.key);
                        	
                        	// Añadir evento click en la celda para marcar/desmarcar el checkbox
                        	tdSolSer.on("click", function(e) {
                        		var input = $(this).find("input[type='checkbox']");
                        		input.prop("checked", !input.prop("checked"));
                        		input.trigger("change");
                        	});
                        	
//                            tr.append("<td id='solser" + serveiSoli.id + "' value='" + serveiSoli.id + "' class='solSer noSelected' onclick='seleccionaServei(this)'>"+ "check" + "</td>");
                            tr.append(tdSolSer);
                            found = true;
                        }
                    });
                    if (!found) {
                        tr.append("<td class='solSer empty'></td>");
                    }
				}
				
				//Afegir botó al final de cada fila per seleccionar tots els serveis d'aquesta fila
				var td = $("<td></td>").addClass("marcarAll serv");
				td.append("<input type='checkbox' onchange='marcarServeiAll(this, \"" + servei.key + "\")'>");
				tr.append(td);
				
				$("#taula-serveis").append(tr);
            });
			
 			var trFinal = $("<tr></tr>");
 			trFinal.append("<td></td>");
 			procediments.forEach(function(procediment) {
 //				.attr("onclick", "marcarProcedimentAll(this, '" + procediment.key + "')")
				var td = $("<td></td>").addClass("marcarAll proc");
				
				td.append("<input type='checkbox' onchange='marcarProcedimentAll(this, \"" + procediment.key + "\")'>");
				
				
		        trFinal.append(td);
			});
			
			// Añadir checkbox para marcar/desmarcar todos
			var tdTots = $("<td></td>").addClass("marcarAll tots");
			tdTots.append("<input type='checkbox' onchange='marcarTots(this)'>");
			trFinal.append(tdTots);
			
			$("#taula-serveis").append(trFinal);
			
			// Crear el tooltip
		    const tooltip = $('<div class="tooltip"></div>').appendTo('body');

		    // Manejar eventos de ratón
		    $('.keyServ, .keyProc').on('mousemove', function(e) {
		        const text = $(this).attr('data-tooltip');
		        tooltip.text(text).css({
		            left: e.pageX + 20 + 'px',
		            top: e.pageY + 10 + 'px',
		            opacity: 1
		        });
		    }).on('mouseleave', function() {
		        tooltip.css('opacity', 0);
		    });
		}

	function generarSubtitolUsuaris(){
		var usuarisHTML = "<div class='usuaris-titulo'>Usuaris:</div>";
		usuarisHTML += "<div class='usuaris-lista'>";
		usuaris.forEach(function(u, index) {
			usuarisHTML += u.name + " " + u.surname1 + " (" + u.administrationID + " - " + u.username + ")";
			if (index < usuaris.length - 1) {
				usuarisHTML += "<br>";
			}
		});
		usuarisHTML += "</div>";
		document.getElementById("subtitle-usuaris").innerHTML = usuarisHTML;
	}		
	
	function prev() {
			showSection(--actualSection);
		}

		function showSection(section) {
			$("section").hide();
			$("#section" + section).show();
			
			// Actualizar indicador de pasos
			for (let i = 1; i <= 3; i++) {
				const circle = $("#step-circle-" + i);
				const label = $("#step-label-" + i);
				const separator = $("#separator-" + i);
				
				if (i < section) {
					// Paso completado
					circle.removeClass("active").addClass("completed");
					label.removeClass("active");
					if (separator.length) separator.addClass("completed");
				} else if (i === section) {
					// Paso actual
					circle.removeClass("completed").addClass("active");
					label.addClass("active");
					if (separator.length) separator.removeClass("completed");
				} else {
					// Paso pendiente
					circle.removeClass("active completed");
					label.removeClass("active");
					if (separator.length) separator.removeClass("completed");
				}
			}
		}

		function marcarSolSer(input) {
			var td = input.parentElement;
			var checked = input.checked;
			if (checked) {
				$(td).addClass("selected");
			} else {
				$(td).removeClass("selected");
			}

			testTdIsLast(td, checked);
		}

		function marcarSolSerVal(td, val) {
			var input = td.getElementsByTagName("input")[0];
			if (val) {
				input.checked = true;
				$(td).addClass("selected");
			} else {
				input.checked = false;
				$(td).removeClass("selected");
			}
			
			testTdIsLast(td, val);
		}
		
		function testTdIsLast(td, checked){
			let codiProc = td.getAttribute("proc");
			let codiServ = td.getAttribute("serv");

			var mismaColumna = $("td[proc='" + codiProc + "']");
			var mismaFila = $("td[serv='" + codiServ + "']");

			//variable para saber si los elementos de la misma columna estan igual que la actual.
			let columnaIguales = 0;
			for (let i = 0; i < mismaColumna.length; i++) {
                var td = mismaColumna[i];
                var input = td.getElementsByTagName("input")[0];
                if (input.checked != checked) {
					break;
				}else{
					columnaIguales++;
				}
			}

			if(mismaColumna.length == columnaIguales){
				//Marcar la de marcar todos como estén todos.
	        
        		// Obtiene el índice de la columna del td clickeado
                var indiceColumna = Array.prototype.indexOf.call(td.parentNode.children, td);
       			var tabla = document.getElementById('taula-serveis');

                // Obtiene la última fila de la tabla
                var ultimaFila = tabla.rows[tabla.rows.length - 1];

                // Obtiene el td correspondiente en la última fila
                var tdUltimaFila = ultimaFila.cells[indiceColumna];
				tdUltimaFila.firstElementChild.checked = checked;
			}
			
			let filaIguales = 0;
			for (let i = 0; i < mismaFila.length; i++) {
                var td = mismaFila[i];
                var input = td.getElementsByTagName("input")[0];
                if (input.checked != checked) {
                    break;
                }else{
                    filaIguales++;
                }
            }
			
			if (mismaFila.length == filaIguales) {
		        td.parentElement.lastElementChild.firstElementChild.checked = checked;
				console.log("marcar la fila");
				//Marcar la de marcar todos como estén todos.
			}

		}

		function marcarServeiAll(chcek, procKey) {
			var marcar = chcek.checked;
			console.log(marcar);

			var tds = $("td[serv='" + procKey + "']");
			console.log(tds);

			for (let i = 0; i < tds.length; i++) {
				var td = tds[i];
				marcarSolSerVal(td, marcar);
			}
		}

		function marcarProcedimentAll(chcek, procKey) {
			var marcar = chcek.checked;
			console.log(marcar);

			var tds = $("td[proc='" + procKey + "']");
			console.log(tds);

			for (let i = 0; i < tds.length; i++) {
				var td = tds[i];
				marcarSolSerVal(td, marcar);
			 }
		}

		function marcarTots(checkbox) {
			var marcar = checkbox.checked;
			var tds = $("td.solSer:not(.empty)");
			
			for (let i = 0; i < tds.length; i++) {
				var td = tds[i];
				marcarSolSerVal(td, marcar);
			}
		}

		$("#pinfoDataForm").submit(
			function(event) {
				event.preventDefault();
	
				let usuarisAuxx = [];
				$("input[name='usuaris']").val(
						usuaris.map(u => u.username).join(","));
	
				var selecteds = $("#taula-serveis .selected");
				if (selecteds.length == 0) {
				alert(MSG_SERVEIS_ALMENYSUN);
				return;
			}
	
				for (var i = 0; i < selecteds.length; i++) {
					let idNum = $(selecteds[i]).attr("value");
					solicitudServeis.push(idNum);
				}
	
				$("input[name='solicitudServeis']").val(
						solicitudServeis.join(","));
	
				this.submit();
			}
		);
	</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Afegir permisos a la solicitud</title>

<style>
/* body {
	display: flex;
	justify-content: center;
	min-height: 100vh;
	background: #e9faff;
} */
#form-container {
	background: white;
	padding: 1rem;
	border-radius: 6px;
	border: 2px solid black;
	margin: 0 3rem;
}

#form-content {
	display: flex;
	margin: 0 2rem;
}

section {
	padding: 1rem;
	min-width: 50rem;
	width: 100%;
	max-width: 100%;
	display: flex;
	flex-flow: column;
}

.header {
	display: flex;
	justify-content: space-between;
	margin-bottom: .5rem;
}

section .title {
	font-size: 25px;
}

.sub-title {
	font-size: 20px;
	margin: 0 0 .5rem 0;
}

.botones {
	text-align: right;
	margin-top: 1rem;
}

.procediment-item, .usuari-item {
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.procediment-item:hover, , .usuari-item:hover {
	background-color: #f1f1f1;
}

#autocomplete-procediments, #autocomplete-usuaris {
	display: block;
	position: absolute;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 170px;
	overflow-y: auto;
}

#autocomplete-procediments div {
	padding: 6px;
}

#autocomplete-usuaris div {
	padding: 6px;
}

/* #taula-serveis td:nth-child(1), #taula-serveis tr:nth-child(1) {
	background-color: #f1f1f1;
	font-weight: bold;
}
*/
#titol-procediments, #titol-serveis {
	background-color: #f1f1f1;
	font-weight: bold;
}

.marcarAll {
	background-color: #f1f1f1;
	font-weight: bold;
}

.keyProc, .keyServ {
	background-color: #f1f1f1;
	font-weight: bold;
	padding: 3px 6px;
}

.keyServ, .keyProc {
	position: relative;
	cursor: help;
}

.keyProc {
	text-align: center;
	word-break: break-word;
}

.tooltip {
	position: absolute;
	background-color: black;
	color: white;
	padding: 5px;
	border-radius: 5px;
	pointer-events: none;
	opacity: 0;
	transition: opacity 0.2s;
	z-index: 1000;
}

#titol-serveis {
	padding-top: 2rem;
	text-align: center;
}

#titol-procediments {
	padding: 0.3rem;
}

.solSer {
	text-align: center;
	background-color: #FFF;
}

.solSer.selected {
	background-color: #d6eecd;
	font-weight: bold;
	cursor: pointer;
}

.noSelected {
	background-color: #ffd0d0;
	cursor: pointer;
}

.marcarAll {
	padding: 6px 1rem;
	text-align: center;
}

#llistat-procediments ul {
	margin-top: 1rem;
}

.usuari-data-container, .procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.usuari-li, .procediment-li {
	padding-top: 5px;
	padding-right: 1rem;
	padding-bottom: 5px;
	margin: 2px 0px;
	border: 1px solid white;
}

.usuari-li:hover, .procediment-li:hover {
	background-color: #f1f1f1;
	border-bottom-color: black;
	border-top-color: black;
}

.usuari-data-text, .procediment-data-text {
	margin-right: 1rem;
}

.usuari-data-delete, .procediment-data-delete {
	cursor: pointer;
	color: #ae0808;
}

#backToList-button-container {
	text-align: right;
	margin: 1rem 5rem;
}

#input-usuari-container {
	display: flex;
}
</style>

</head>
<body>
	<div id="backToList-button-container">
		<a id="backToList-button" href="list/1" class="btn btn-secondary">Tornar al llistat</a>
	</div>

	<div id="form-container">
		<form id="pinfoDataForm" action="procesarPermisos" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="usuaris"> <input type="hidden"
				name="procediments"> <input type="hidden"
				name="solicitudServeis">


			<div id="form-content">

				<section id="section1">
					<div class="header">
						<div class="title">Introduce los usuarios:</div>
					
						<div class="botones">
							<button type="button" class="pagination-button btn"
								onclick="next();"><fmt:message key="tramitpinfodata.sec1.seguent"/></button>
						</div>
					</div>
					
					<div class="input-container user">
						<div id="cercador-usuaris">
<!-- 							<input type="text" name="userID" placeholder="Usuari"
								value="ptrias">
							<button type="button" class="btn" onclick="afegirUsuari();">Add</button> -->
	
							<div id="input-usuari-container">
							

 							<input id="usuariNom" name="userID" type="text"
								autocomplete="off" class="campsUsuari w-25 form-control"
								placeholder="Nom">
 							<input id="usuariNif" name="userID" type="text"
								autocomplete="off" class="campsUsuari w-25 form-control"
								placeholder="NIF">
							</div>
 
							<div id="autocomplete-usuaris"></div>
						</div>
						<div id="llistat-usuaris">
							<ul></ul>
						</div>
					</div>
				</section>

				<section id="section2">

					<div class="header">
						<div class="title">Introduce los procedimientos:</div>

						<div class="botones">
							<button type="button" class="pagination-button btn"
								onclick="prev();"><fmt:message key="tramitpinfodata.sec2.anterior"/></button>
							<button type="button" class="pagination-button btn"
								onclick="next();"><fmt:message key="tramitpinfodata.sec2.seguent"/></button>
						</div>
					</div>

					<div class="input-container procediment">
						<div id="cercador-procediments">

							<input id="procedimentID" name="procedimentID" type="text"
								autocomplete="off" class="w-100 form-control"
								placeholder="Procediment. Minim 2 caracters...">

							<div id="autocomplete-procediments"></div>
						</div>
						<div id="llistat-procediments">
							<ul ></ul>
						</div>
					</div>
				</section>

				<section id="section3">
					<div class="header">
						<div class="title">Assignar permisos:</div>
	
						<div class="botones">
							<button type="button" class="pagination-button btn"
								onclick="prev()"><fmt:message key="tramitpinfodata.sec3.anterior"/></button>
								
							<input type="submit" class="btn" value="<fmt:message key="tramitpinfodata.sec3.seguent"/>">
						</div>
					</div>
					<div id="subtitle-usuaris" class="sub-title"></div>
					

					<div class="input-container servei">
						<div id="taula-serveis-cont">
							<table id="taula-serveis" border="1"></table>
						</div>
					</div>
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

			$("#procedimentID").on("input", function() {
				var	procediment = $(this).val();
				console.log(procediment);
				if (procediment.length < 2) { 
					$("#autocomplete-procediments").empty(); 
					return; 
				}
				
				$.ajax({
                    url : "jsonProcediments",
                    type : "GET",
                    data : { query : procediment },
                    success : function(data) {
                        $("#autocomplete-procediments").empty();
                        data.forEach(function(proc) {
                            //Si el procediment ja esta a la llista, no el mostri
                            afegirProcediment(proc);
                        });
                    }
                });
								
			});
			
			$(".campsUsuari").on("input", function() {
                var nom = $("#usuariNom").val();
                var nif = $("#usuariNif").val();
                
                console.log("nom: " + nom + ", nif: " + nif );
                if (nom.length < 3 && nif.length < 3) {
                    $("#autocomplete-usuaris").empty();
                    return;
                }
                
                $.ajax({
                    url : "jsonUsuaris",
                    type : "GET",
                    data : { nom : nom, nif : nif },
                    success : function(data) {
                        $("#autocomplete-usuaris").empty();
                        data.forEach(function(usuari) {
                            //Si el usuari ja esta a la llista, no el mostri
                            afegirUsuari(usuari);
                        });
                    }
                });
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
		
		function convertirUsuariEnUser(usuari) {
			            console.log(usuari);
            return { key : usuari.nif, value : usuari.nom, nom : usuari.nom, codi : usuari.codi };
		}
		
		function afegirUsuari(usuari) {
            var usuariDiv = document.createElement("div");
            usuariDiv.classList.add("usuari-item");
            usuariDiv.innerHTML = usuari.administrationID + " - " + usuari.name + " " + usuari.surname1 + " " + usuari.surname2;
            usuariDiv.onclick = function() {
                elegirUsuari(usuari);
            };

            $("#autocomplete-usuaris").append(usuariDiv);
        }
		
		function elegirProcediment(proc) {
			            for (let i = 0; i < procediments.length; i++) {
                if (procediments[i].key == proc.key) {
                    alert("Ja el tenim a la llista");
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
            $("#autocomplete-procediments").empty();
		}
		
		function elegirUsuari(usuari) {
            for (let i = 0; i < usuaris.length; i++) {
                if (usuaris[i].username == usuari.username) {
                    alert("Ja el tenim a la llista");
                    return;
                }
            }
            
            usuaris.push(usuari);
            
            let li = $("<li></li>").addClass("usuari-li");
            let container = $("<div></div>").addClass("usuari-data-container");
            
            let text = usuari.administrationID + " - " + usuari.name + " " +  usuari.surname1 + " " + usuari.surname2 + " - " + usuari.username;
            let spanText = $("<span></span>").addClass("usuari-data-text").text(text);
            
            let spanDelete = $("<span></span>").addClass("usuari-data-delete").html('<i class="fas fa-times"></i>').click(function() {
                usuaris = usuaris.filter(function(u) {
                    return u.codi != usuari.codi;
                });
                li.remove();
            });
            
            container.append(spanText);
            container.append(spanDelete);
            li.append(container);
            
            $("#llistat-usuaris ul").append(li);
            $(".campsUsuari").val("");
            $("#autocomplete-usuaris").empty();
        }
		
		function next() {
			//if actualSection is okey, then go to, else, show error

			if (actualSection == 1) {
				if ($("#llistat-usuaris ul li").length == 0) {
					alert("Introduce al menos un usuario");
					return;
				}
			}

			if (actualSection == 2) {
				if ($("#llistat-procediments ul li").length == 0) {
					alert("Introduce al menos un procedimiento");
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

			document.getElementById("subtitle-usuaris").innerHTML = "Usuaris: " + usuaris.map(u => u.nom + " (" + u.nif + " - " + u.codi + ")").join(", ")
			
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
                        	
                        	tdSolSer.append("<input class='solSerInput' id='" + serveiSoli.id + "'  type='checkbox' onchange='marcarSolSer(this)'>")
                        	
                        	
                        	tdSolSer.attr("proc", procediment.key);
                        	tdSolSer.attr("serv", servei.key);
                        	
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

		function prev() {
			showSection(--actualSection);
		}

		function showSection(section) {
			$("section").hide();
			$("#section" + section).show();
		}

		function afegirUsuariOld() {
			var user = $(".campsUsuari").val();

			if (user == "")
				return;
			if (usuaris.includes(user))
				return;
			
			validarUsuariPluginUserInformation(user);
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

		$("#pinfoDataForm").submit(
				function(event) {
					event.preventDefault();

					let usuarisAuxx = [];
					$("input[name='usuaris']").val(
							usuaris.map(u => u.codi).join(","));

					var selecteds = $("#taula-serveis .selected");
					if (selecteds.length == 0) {
						alert("Selecciona al menos un servicio");
						return;
					}

					for (var i = 0; i < selecteds.length; i++) {
						let idNum = $(selecteds[i]).attr("value");
						solicitudServeis.push(idNum);
					}

					$("input[name='solicitudServeis']").val(
							solicitudServeis.join(","));

					this.submit();
				});
		
		
		
		function validarUsuariPluginUserInformation(user) {
			//Aqui ens arriba un string amb el nom de l'usuari. No es buit, i no está repetit.
			console.log("Validant usuari " + user + " a LDAP");
			
			//Aqui es on es faria la crida al plugin de validació d'usuaris.
			
			$.ajax({
				url : "validarUsuariPluginUserInformation",
				type : "GET",
				data : {
					user : user
				},
				success : function(usuari) {
                    console.log("Usuari validat");
        			console.log(usuari);
        			
        			if (usuari == null) {
        				alert("Usuari no trobat");
        				return;
        			}

        			
        			
        			let li = $("<li></li>").addClass("usuari-li");
        			let container = $("<div></div>").addClass("usuari-data-container");
        			
        			let spanText = $("<span></span>").addClass("usuari-data-text").text(usuari.nom + " - " + usuari.nif);
        			
        			let spanDelete = $("<span></span>").addClass("usuari-data-delete").html('<i class="fas fa-times"></i>').click(function() {
                        procediments = procediments.filter(function(p) {
                            return p.id != proc.id;
                        });
                        li.remove();
                    });
        			
        			container.append(spanText);
        			container.append(spanDelete);
        			li.append(container);
        			
        			$("#llistat-usuaris ul").append(li);
        			usuaris.push(usuari);
        			$(".campsUsuari").val("");
				}
			});
		}
		
	</script>
</body>
</html>
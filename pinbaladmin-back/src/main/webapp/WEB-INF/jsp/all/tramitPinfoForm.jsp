<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Afegir permisos a la solicitud</title>

<style>
body {
	display: flex;
	/* align-items: center; */
	justify-content: center;
	min-height: 100vh;
	background: #e9faff;
}

#form-container {
	background: white;
	padding: 1rem;
	border-radius: 6px;
	border: 2px solid black;
}

#form-content {
	display: flex;
	flex-wrap: wrap;
}

section {
	/* 	background: white;
 */
	padding: 1rem;
	min-width: 50rem;
	max-width: 60rem;
	display: flex;
	flex-flow: column;
}

section .title {
	font-size: 25px;
	margin: 0 0 .5rem 0;
}

.botones {
	text-align: right;
	margin-top: 1rem;
}

.input-container {
	min-height: 20rem;
}

.procediment-item {
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.procediment-item:hover {
	background-color: #f1f1f1;
}

#autocomplete-procediments {
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

.procediment-item:hover {
	background-color: #f1f1f1;
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

.solSer.empty {
	background-color: #FFF;
}

.keyServ, .keyProc {
	position: relative;
	cursor: help;
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

.selected {
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

.solSer {
	text-align: center;
}

#llistat-procediments ul {
	margin-top: 1rem;
}

.procediment-data-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.procediment-li {
	padding-top: 5px;
	padding-right: 1rem;
	padding-bottom: 5px;
	margin: 2px 0px;
	border: 1px solid white;
}

.procediment-li:hover {
	background-color: #f1f1f1;
	border-bottom-color: black;
	border-top-color: black;
}

.procediment-data-text {
	margin-right: 1rem;
}

.procediment-data-delete {
	cursor: pointer;
	color: #ae0808;
}
</style>

</head>
<body>

	<div>
		<h3>Afegir permisos a la solicitud</h3>
	</div>

	<div id="form-container">
		<form id="pinfoDataForm" action="procesarPermisos" method="post"
			enctype="multipart/form-data">

			<input type="hidden" name="usuaris"> <input type="hidden"
				name="procediments"> <input type="hidden"
				name="solicitudServeis">


			<div id="form-content">

				<section id="section1">
					<div class="title">Introduce los usuarios:</div>
					<div class="input-container user">
						<div id="cercador-usuaris">
							<input type="text" name="userID" placeholder="Usuari"
								value="ptrias">
							<button type="button" class="btn" onclick="afegirUsuari();">Add</button>
						</div>
						<div id="llistat-usuaris">
							<ul></ul>
						</div>
					</div>
					<div class="botones">
						<button type="button" class="pagination-button btn"
							onclick="next();">Siguiente</button>
					</div>
				</section>

				<section id="section2">
					<div class="title">Introduce los procedimientos:</div>

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

					<div class="botones">
						<button type="button" class="pagination-button btn"
							onclick="prev();">Anterior</button>
						<button type="button" class="pagination-button btn"
							onclick="next();">Siguiente</button>
					</div>
				</section>

				<section id="section3">
					<div class="title">Selecciona los servicios:</div>

					<div class="input-container servei">
						<div id="taula-serveis-cont">
							<table id="taula-serveis" border="1"></table>
						</div>
					</div>

					<div class="botones">
						<button type="button" class="pagination-button btn"
							onclick="prev()">Anterior</button>
						<input type="submit" class="btn" value="Submit">
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
				var procediment = $(this).val();
				console.log(procediment);
				if (procediment.length < 2) {
					$("#autocomplete-procediments").empty();
					return;
				}
				$.ajax({
					url : "jsonProcediments",
					type : "GET",
					data : {
						query : procediment
					},
					success : function(data) {
						$("#autocomplete-procediments").empty();
						data.forEach(function(proc) {
							//Si el procediment ja esta a la llista, no el mostri

							afegirProcediment(proc);
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

		function afegirUsuari() {
			var user = $("input[name='userID']").val();

			if (user == "")
				return;
			if (usuaris.includes(user))
				return;

			$("#llistat-usuaris ul").append("<li>" + user + "</li>");
			usuaris.push(user);
			$("input[name='userID']").val("");
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
				/* 			  var input = td.getElementsByTagName("input")[0];
				 if (marcar) {
				 input.checked = true;
				 //				  td.classList.remove("noSelected");
				 //				  td.classList.add("selected");
				 }else{
				 input.checked = false;
				 //				  td.classList.remove("selected");
				 //				  td.classList.add("noSelected");
				 }
				 */}

			//elem.innerHTML = "desmarcarAll";
			//elem.setAttribute("onclick", "desmarcarServeiAll(this)");
		}

		function marcarProcedimentAll(chcek, procKey) {

			var marcar = chcek.checked;
			console.log(marcar);

			var tds = $("td[proc='" + procKey + "']");

			console.log(tds);

			for (let i = 0; i < tds.length; i++) {
				var td = tds[i];
				marcarSolSerVal(td, marcar);

				/* 			  var input = td.getElementsByTagName("input")[0];
				 if (marcar) {
				 input.checked = true;
				 //				  td.classList.remove("noSelected");
				 //				  td.classList.add("selected");
				 }else{
				 input.checked = false;
				 //				  td.classList.remove("selected");
				 //				  td.classList.add("noSelected");
				 }
				 */}

			//elem.innerHTML = "desmarcarAll";
			//elem.setAttribute("onclick", "desmarcarServeiAll(this)");
		}

		$("#pinfoDataForm").submit(
				function(event) {
					event.preventDefault();

					$("input[name='usuaris']").val(usuaris.join(","));

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
	</script>
</body>
</html>
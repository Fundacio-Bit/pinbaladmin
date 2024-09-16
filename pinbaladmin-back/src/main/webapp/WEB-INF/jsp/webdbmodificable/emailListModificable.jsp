
<%@page import="org.fundaciobit.pinbaladmin.back.controller.operador.LlistaCorreusOperadorController"%>
<script>

    var emailIDCache;
    var tipus; // 1 incidencia     0 solucitud

    function myFunction() {
        var operador = document.getElementById("operador").value;
        var root = "<%=request.getContextPath()%>${contexte}";

        var returnUrl = root;
        
		var checkBox = document.getElementById("checkNewItem");
		var itemJaExistent = checkBox.checked == true;
		
        if (itemJaExistent) {
        	//Update
			if (tipus == 1) {
	            var incidenciaID = document.getElementById("incidenciaID").value;
	            returnUrl += "/incidenciaExistent/" + emailIDCache + "/" + incidenciaID;
			} else {
				var solicitudID = document.getElementById("solicitudID").value;
				returnUrl += "/solicitudExistent/" + emailIDCache + "/" + solicitudID;
			}
        } else {
			//Create 
	        if (tipus == 1) {
	            var tipusIncidencia = document.getElementById("tipusIncidencia").value;
	            returnUrl += "/incidencia/" + emailIDCache + "/" + operador + "/" + tipusIncidencia;
			}else{
	            var estatSoli = document.getElementById("estatSolicitud").value;
				returnUrl += "/solicitud/" + emailIDCache + "/" + operador + "/" + estatSoli;
			}
		}
        
        window.location.href = returnUrl;
    }

    function crearIncidencia(emailID) {
        emailIDCache = emailID;
        tipus = 1;
        
        $(".incidencia-info").show();
        $(".solicitud-info").hide();
        
        $(".modal-title").html("Crear incidencia");
        $("#label-checkNewItem").html("Incidencia existent");

        testExist();
        $("#modelSeleccioTramitador").modal();
    }

    function crearSolicitud(emailID) {
        emailIDCache = emailID;
        tipus = 0;

        $(".incidencia-info").hide();
        $(".solicitud-info").show();

        $(".modal-title").html("Crear solicitud");
        $("#label-checkNewItem").html("Solicitud existent");

        testExist();
        $("#modelSeleccioTramitador").modal();
    }
    
    function cleanModalCorreu() {
    	$("#visorCorreu").empty();
    	
		$("#adjuntsContainerCorreu").empty();
		$("#adjuntsContainerCorreu").hide();

		$("#div-nAdjunts").hide();
		$(".modal-title").empty();
	}
    
    function veureCorreu(emailID) {
    	
    	cleanModalCorreu();
    	
    	$.ajax({
			url : "<%= request.getContextPath() + LlistaCorreusOperadorController.CONTEXT_WEB %>/viewMessage2",
			type : "GET",
			data : {
				emailID : emailID
			},
			success : function(data) {
				var emi = JSON.parse(data);
				console.log(emi);
				console.log(typeof emi);
					
				if(typeof emi === "string"){
					$("#visorCorreu").html("<h1>" + emi +"</h1>");
                }else{
					
					$("#visorCorreu").html(emi.body);
			        $(".modal-title").html(emi.subject);
			        
			        var infoAdjunts = "Correu amb " + emi.attachments.length + " adjunts";
					$("#div-nAdjunts").html(infoAdjunts);
					$("#div-nAdjunts").show();

					emi.attachments.forEach(function(attach) {
						
						var clase = getClaseFromAttach(attach);
						var name = attach.fileName.replaceAll("_", ' ');
						var adjuntDiv = $("<div class='adjuntDivCorreu "+ clase + "'>"+name+"</div>");
						
	                    $("#adjuntsContainerCorreu").append(adjuntDiv);
	                    $("#adjuntsContainerCorreu").show();
					}); 
					
				}
			}
		});
        $("#modelVeureCorreu").modal();
    }
    
    function getClaseFromAttach(attach){
    	
    	var mime = attach.contentType;
		console.log(mime);
		
		var clase = "unknow";
		switch (mime) {
		  case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
		  case "application/vnd.ms-excel":
		    clase = "excel";
		    break;
		  case "application/msword":
		  case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
		    clase = "word";
		    break;
		  case "application/pdf":
		    clase = "pdf";
		    break;
		  case "image/jpeg":
		  case "image/png":
		    clase = "image";
		    break;
		  case "text/plain":
		    clase = "text";
		    break;
		  case "application/zip":
		    clase = "zip";
		    break;
		  case "application/xml":
		    clase = "xml";
		    break;
		}
		
		if(clase == "unknow"){
			var name = attach.fileName;
            console.log("name: " + name);
            if(name.includes("xlsx") || name.includes("xls")){
                clase = "excel";
            } else if(name.includes("word")){
            	clase = "word";
            } else if(name.includes("pdf")){
            	clase = "pdf";
            } else if(name.includes("png") || name.includes("jpeg")){
            	clase = "image";
            } else if(name.includes("text")){
            	clase = "text";
            } else if(name.includes("zip")){
            	clase = "zip";
            } else if(name.includes("xml")){
            	clase = "xml";
            }
        }
		
		return clase;
    }
    
	function testExist() {
		var checkBox = document.getElementById("checkNewItem");
		if (checkBox.checked == true) {
			$("#nou_element").hide();
			$("#existent_element").show();
		} else {
			$("#nou_element").show();
			$("#existent_element").hide();
		}
	}
	
	$(document).ready(function() {
		$('#modelVeureCorreu').on('hide.bs.modal', function(e) {
			cleanModalCorreu();
		});
	});
	
	
	
	//Metodo para obtener las posible asignaciones automaticas
	function assignacioAutomatica() {
        $.ajax({
            url : "<%=request.getContextPath()%>${contexte}/assignacioAutomatica",
            type : "GET",
            success : function(data) {
            	var emi = JSON.parse(data);
				console.log(emi);
            	
				var table = document.createElement("table");
				table.classList="table table-striped table-bordered table-hover";
				var thead = "<thead><tr><th>Asumpte</th><th>Item</th><th>Titol</th><th>Assignar</th></tr></thead>";
				table.innerHTML = thead;
				var tbody = document.createElement("tbody");
				
				emi.forEach(function(item) {
					var tr = document.createElement("tr");
					var td1 = document.createElement("td");
					td1.innerHTML = item[2];
					tr.appendChild(td1);
					
					var td2 = document.createElement("td");
					td2.innerHTML = item[0];
					tr.appendChild(td2);
					
					var td3 = document.createElement("td");
					td3.innerHTML = item[4];
					tr.appendChild(td3);
					
					var td4 = document.createElement("td");
					var btn = document.createElement("button");
					btn.classList="btn btn-primary assignar-btn";
					btn.innerHTML = "Assignar";
					btn.onclick = function(event) {
		                event.preventDefault(); // Prevenir el comportamiento predeterminado del formulario
						assignador(item[0], item[1], item[3]);
                    };
					td4.appendChild(btn);

					tr.appendChild(td4);
                    tbody.appendChild(tr);
                });
				
				table.appendChild(tbody);
				$("#assignacioAutomatica .modal-body").html(table);
            }
        });
        $("#assignacioAutomatica").modal();

    }
	
	
    function assignador(tipus, emailID, itemID){
		var msg = "assignador: " + tipus + " " + emailID + " " + itemID;
		console.log(msg);
    	$.ajax({
			url : "<%= request.getContextPath() + LlistaCorreusOperadorController.CONTEXT_WEB %>/itemExistent",
			type : "GET",
			data : {
				tipus : tipus,
				emailID : emailID,
				itemID : itemID
			},
			success : function(data) {
				console.log(data)
            	var msg = JSON.parse(data);

				console.log(msg)
				CORREUS_PENDENTS_ASSIGNACIO--;
			}
		});
		
    }
    
    var CORREUS_PENDENTS_ASSIGNACIO = 0;
    
	function assignadorAll(){
		console.log("assignadorAll");
		
		var btns = document.getElementsByClassName("assignar-btn")

		CORREUS_PENDENTS_ASSIGNACIO = btns.length;
		
		for(let i = btns.length -1; i >= 0; i--){
			setTimeout(() => {
				console.log("click: " + i);
				$(btns[i]).click()
			}, (btns.length - i-1) * 500);
		}
		
		
		console.log("start wait");
		const intervalID = setInterval(testEnd, 500);

		function testEnd() {
			console.log("waiting: " + CORREUS_PENDENTS_ASSIGNACIO);
			if(CORREUS_PENDENTS_ASSIGNACIO == 0){
                clearInterval(intervalID);
                location.reload();
			}
		}

		
/* 		setTimeout(() => {
			while(CORREUS_PENDENTS_ASSIGNACIO > 0){
				console.log("waiting: " + CORREUS_PENDENTS_ASSIGNACIO);
	        }
			location.reload();
		}, btns.length * 1000);
 */
	}
</script>


<!-- Modal -->
<% request.setAttribute("currentuser", request.getRemoteUser()); %>

<div class="modal fade" id="modelSeleccioTramitador" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modal-title" class="modal-title"></h4>
				<button type="button" class="close"
					style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
			</div>
			
			
			
			<div class="modal-body">

 				<label id="label-checkNewItem" for="checkNewItem"></label> 
				<input type="checkbox" id="checkNewItem" onclick="testExist()">		
				<br>
				
				<div id="nou_element">
					<label id="label_operador" for="operador"> Selecciona operador: </label> 
					<select id="operador" class="my_select">
						<c:forEach items="${operadors}" var="operador">
							<option value="${operador.key}"
								${(currentuser eq operador.key)?'selected':''}>${operador.value}</option>
						</c:forEach>
					</select> 
	
					<br>
					<div class="incidencia-info">
						<label id="label_tipusIncidencia" for="tipusIncidencia">Tipus d'incidencia:</label> 
						<select id="tipusIncidencia" class="my_select">
							<c:forEach items="${tipusIncidencies}" var="tipusIncidencia">
								<option value="${tipusIncidencia.key}">${tipusIncidencia.value}</option>
							</c:forEach>
						</select>
					</div>
					<div class="solicitud-info">
						<label id="label_estatSolicitud" for="estatSolicitud">Estats Solicitud </label> 
						<select id="estatSolicitud" class="my_select">
							<c:forEach items="${estatSolicituds}" var="estatSolicitud">
								<option value="${estatSolicitud.key}">${estatSolicitud.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div id="existent_element">
					<p>Indica la existent</p>

					<div class="incidencia-info">
						<label for="incidenciaid" style="font-size: 1.15rem;">Incidencia ID:</label>
						<input type="text" id="incidenciaID" />
					</div>
					<div class="solicitud-info">
						<label for="solicitudid" style="font-size: 1.15rem;">Solicitud ID:</label> 
						<input type="text" id="solicitudID" />
					</div>
				</div>

				<div class="modal-footer">
				<button type="button" class="btn btn-default" onClick="myFunction()"
					data-dismiss="modal">Ok</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL PER VEURE UN CORREU -->
<div class="modal fade" id="modelVeureCorreu" role="dialog">
	<div class="modal-dialog" style="max-width: 35rem;">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="modal-title" class="modal-title"></h4>
				<button type="button" class="close"
					style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body">
				<div id="div-nAdjunts"></div>
				<div id="adjuntsContainerCorreu"></div>
	
 				<div id="visorCorreu"></div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL PER ASSIGNACIO AUTOMATICA -->
<div class="modal fade" id="assignacioAutomatica" role="dialog">
    <div class="modal-dialog" style="max-width: none;margin: 3rem 20rem;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="modal-title" class="modal-title">Assignació automàtica</h4>
                <button type="button" class="close"
                    style="margin: 0px; padding: 0px" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p>Processant correus...</p>
            </div>
                <div class="modal-footer">
				<button type="button" class="btn btn-default"
					onClick="assignadorAll()">Assignar tots</button>
			</div>
        </div>
    </div>
</div>

<style>
.my_select {
	appearance: none;
	background-color: transparent;
	border: none;
	padding: 0 1em 0 0;
	margin: 0;
	width: 100%;
	font-family: inherit;
	font-size: inherit;
	cursor: inherit;
	line-height: inherit;
	outline: none;
}

.my_select::-ms-expand {
	display: none;
}

:root { -
	-select-border: #777; -
	-select-focus: blue; -
	-select-arrow: var(- -select-border);
}

.my_select {
	width: 100%;
	min-width: 15ch;
	max-width: 30ch;
	border: 1px solid var(- -select-border);
	border-radius: 0.25em;
	padding: 0.25em 0.5em;
	font-size: 1.15rem;
	cursor: pointer;
	line-height: 1.1;
	background-color: #fff;
	background-image: linear-gradient(to top, #f9f9f9, #fff 33%);
	display: grid;
	grid-template-areas: "select";
	align-items: center;
}

.my_select::after {
	content: "";
	width: 0.8em;
	height: 0.5em;
	background-color: var(- -select-arrow);
	clip-path: polygon(100% 0%, 0 0%, 50% 100%);
	justify-self: end;
}

.my_select, .select:after {
	grid-area: select;
}

#label_tipusIncidencia, #label_operador, #label_estatSolicitud {
	font-size: 1.15rem;
}

#modalVeureCorreu {
	max-width: 600px;
}

#div-nAdjunts {
	text-align: center;
	padding: 5px;
	border: 1px black outset;
	border-radius: 5px;
	margin-bottom: 1rem;
	background-color: #ececec;
}

#adjuntsContainerCorreu {
	display: grid;
	grid-template-columns: 1fr 1fr;
	margin: 1rem auto;
}

.adjuntDivCorreu {
	border: 1px black solid;
	margin: 3px;
	padding: 3px;
	padding-left: 1rem;
	border-radius: 3px;
	overflow: auto;
}

.excel {
	background-color: #28a745;
	color: white;
}

.word {
	background-color: #007bff;
	color: white;
}

.pdf {
	background-color: #dc3545;
	color: white;
}

.image {
	background-color: #17a2b8;
	color: white;
}

.text {
	background-color: #ffc107;
	color: black;
}

.zip {
	background-color: #9b3add;
	color: white;
}

.xml {
	background-color: #17a2b8;
	color: white;
}

.unknow {
	background-color: #6c757d;
	color: white;
}
</style>


<!-- VISTA DEL LLISTAT DE CORREUS -->
<style>
table {
	margin-right: 2rem;
	width: 100% !important;
}
#infoNumRegistres {
	display: none;
}
</style>

<style>
td:nth-child(-n+2) {
	width: 0px;
}
td:last-child {
	width: 0px;
}
</style>

<c:if test="${__mostrarMissatgeArxiu__ == 'true'}">
	<style>
td:nth-child(4) {
	width: min-content;
	max-width: 500px;
}

td:nth-child(5) {
	width: 0px;
}

td:nth-child(6) {
	width: 0px;
}

iframe {
	border: 1px solid;
	width: 400px;
	height: 320px;
	/* 	max-width: 400px; */
}

textarea {
	max-width: 400px;
	max-height: 350px;
}

.adjuntDiv {
	max-width: 230px;
	overflow: auto;
	margin-top: 10px;
}

.adjuntsContainer {
	width: fit-content;
	margin-bottom: 10px;
}

</style>
</c:if>


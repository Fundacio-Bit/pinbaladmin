<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>
</c:if>

<!-- CERCADOR AUTOCOMPLETAR SERVEIS -->
<input id="servei" type="text" autocomplete="off"
	class="w-100 form-control" placeholder="Minim 2 caracters..." >
<div id="autocomplete-serveis"></div>

<style>
.servei-item {
	cursor: pointer;
	padding: 6px;
	background-color: #fff;
}

.servei-item:hover {
	background-color: #f1f1f1;
}

#autocomplete-serveis {
	display: block;
	position: absolute;
	z-index: 1;
	background-color: #f9f9f9;
	border: 1px solid #e9e9e9;
	max-height: 170px;
	overflow-y: auto;
}
</style>

<script>
	$(document).ready(function() {
		document.getElementById("tramitIServ.nom").style.display = "none";

		$("#tramitIServ_nom_columnvalueid").append($("#servei"));
		$("#tramitIServ_nom_columnvalueid").append($("#autocomplete-serveis"));
	
		var codi = document.querySelector('[name="tramitIServ.codi"]');
		var nom = document.querySelector('[name="tramitIServ.nom"]');
		nom.style.display = "none";

		codi.readOnly = "readOnly";

		$("#servei").on("input", function() {
			var servei = $(this).val();
			console.log(servei);
			if (servei.length < 2) {
				$("#autocomplete-serveis").empty();
				return;
			}
			$.ajax({
				url : "jsonServeis",
				type : "GET",
				data : {
					query : servei
				},
				success : function(data) {
					$("#autocomplete-serveis").empty();
					data.forEach(function(servei) {
						afegirServei(servei);
					});
				}
			});
		});

		function afegirServei(servei) {
			var serveiDiv = document.createElement("div");
			serveiDiv.classList.add("servei-item");
			serveiDiv.innerHTML = servei.key + " - " + servei.value;
			serveiDiv.onclick = function() {
				elegirServei(servei.key, servei.value);
			};

			$("#autocomplete-serveis").append(serveiDiv);
		}
		
		function elegirServei(key, value) {

			codi.readOnly = "";
			codi.value = key;
			codi.readOnly = "readOnly";

			nom.value = value;

			$("#autocomplete-serveis").empty();
			$("#servei").val(value);
		}

		testServei();
		function testServei(){
			var v = document.getElementById("tramitIServ.nom").value;
			if (v != "") {
				$("#servei").val(v);
            }else {
				$("#servei").val("");
			}
		}

		$(document).click(function(event) {
			const $target = $(event.target);

			if ($target.closest("#autocomplete-serveis").length === 0) {
				
				$("#autocomplete-serveis").empty();
				testServei();
			}
		});
		
		document.getElementById("tramitIServ.norma").placeholder="<fmt:message key='tramitIServ.norma.placeholder' />";

	});
</script>


<!-- AFEGIR NORMES A UN SERVEI -->
<div id="botones-normas">
	<div id="afegirNorma" class="btn-norma btn">Afegir una altra norma</div>
	<div id="eliminarNorma" class="btn-norma btn">Eliminar darrera norma</div>
</div>

<style>
#botones-normas {
	margin: 1rem 0;
	text-align: right;
}

.btn-norma {
	margin-left: 3px;
}

#afegirNorma {
	background-color: #4be16d;
}

#eliminarNorma {
	background-color: #ea8f3e;
}
</style>

<script type="text/javascript">

	$(document).ready(function() {
	
		var totalNormes = 3;
		
		$(".tab_container").append($("#botones-normas"));
		
		$("#seccio_norma1").show();
		$("#seccio_norma2").hide();
		$("#seccio_norma3").hide();

		$("#eliminarNorma").hide();
		
		var normesAfegides = ${normesAfegides};
		
		console.log(normesAfegides);
		testNormes();
		
		function testNormes() {
			console.log("añadriermos " + normesAfegides + " normas");
			for (var i = 1; i <= totalNormes; i++) {
				if (i <= normesAfegides) {
					$("#seccio_norma" + i).show();
				} else {
					$("#seccio_norma" + i).hide();
				}
			}
			//Si solo hay una norma, no se puede eliminar, y si hay 3, no se puede añadir
			if (normesAfegides > 1) {
                $("#eliminarNorma").show();
            } else {
                $("#eliminarNorma").hide();
            }
			if (normesAfegides < totalNormes) {
                $("#afegirNorma").show();
            } else {
                $("#afegirNorma").hide();
            }
		}
	
		$("#afegirNorma").click(function() {
			normesAfegides++;
			if (normesAfegides > totalNormes) {
	            normesAfegides = totalNormes;
	        }
			testNormes();
		});
	
		$("#eliminarNorma").click(function() {
			normesAfegides--;
			if (normesAfegides < 1) {
	            normesAfegides = 1;
	            
	        }
			testNormes();
		});
		
		//onsubmit, poner vacios los campos de normas que no esten visibles
		$("form").submit(function() {
			console.log("submit");
			for (var i = normesAfegides+1; i <= totalNormes; i++) {
				document.getElementById("tramitIServ.norma" + i).value = "none";
			}
		});
    });
	
	
</script>




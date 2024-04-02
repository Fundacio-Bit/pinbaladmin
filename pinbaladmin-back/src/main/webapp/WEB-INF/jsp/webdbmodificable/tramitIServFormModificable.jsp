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

	});
</script>

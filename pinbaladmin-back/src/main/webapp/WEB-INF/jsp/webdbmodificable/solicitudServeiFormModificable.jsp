
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
				document.getElementById("solicitudServei.norma" + i).value = "none";
			}
		});
    });
</script>
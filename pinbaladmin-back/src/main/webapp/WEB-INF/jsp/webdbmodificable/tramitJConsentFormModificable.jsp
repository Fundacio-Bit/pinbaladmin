<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${isPublic == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitSistraPublic.jsp"%>

	<style>
		.input-group {
			flex-direction: column;
		}
	</style>

	<script type="text/javascript">
		$("input[type='file']").attr("accept",".pdf"); 
		
		//Afegir botó per eliminar el document adjuntat (span)
		$("input[type='file']").each(function(){
			var input = this;
			var div = this.parentElement.nextElementSibling;
			var id = this.id;
			
			$(this).change(function(){
				var ruta = $(this).val(); 		
				console.log(ruta);
				var rutaArray = ruta.split('\\');
                $(div).css('display','flex');
                
                var small = div.firstElementChild.firstElementChild;
                small.innerHTML = rutaArray[rutaArray.length - 1];
			})
		});	

		$("input[type='file']").each(function(){

			var input = this;
			var div = this.parentElement.nextElementSibling;
			var id = this.id;
			
			//Afegir botó per eliminar el document adjuntat (span)
			var span = document.createElement("span");
			span.className = "btn btn-danger btn-sm";
			span.style.padding = "10px";
			span.innerHTML = "<i class='fa fa-times'></i>";
			
			span.onclick = function() {
				eliminarFitxer(input);
			}
			
			//Si quan s'inicia la pagina, el document no esta adjuntat, no es mostra el div
			if (div.children[0].style.display == "none") {
				div.style.display = "none";
			}
			
			//Eliminar el botó genapp "esborrar"
			for (var i = 0; i < div.children.length; i++) {
				var child = div.children[i];
				if (child.innerHTML.includes("Esborrar")) {
					child.style.display = "none";
				} else {
					child.style.display = "block";
				}
			}

			$(div).append(span);
		});

		function eliminarFitxer(fitxerNorma) {
			var list = new DataTransfer();
			fitxerNorma.files = list.files;

			var div_file = fitxerNorma.parentElement.nextElementSibling;
			div_file.style.display = "none"
		}
				
		function submitForm() {
			var form = document.getElementById('tramitJConsentForm');
			if (validacioFormulario()) {
				if (confirm('Finalitzar tramit?')) {
					form.submit();
				}
			}else{
				crearMissatgeError(form, "Revisi els errors de formulari");
			}
		}
		
		function validacioFormulario() {
			var validacio = true;
			
			var fitxerAdjunt = document.getElementById("adjuntID");
			
			var consentiment = document.getElementById("tramitJConsent_consentiment");
			console.log("consentiment: " + consentiment.value);
			if (consentiment.value == "si" || consentiment.value == "noop") {
                if (!testFile(fitxerAdjunt)) {
                    crearMissatgeError(fitxerAdjunt, "El fitxer de la norma es obligatori.");
                    validacio &= false;
                }
            }
			return validacio;
		}

		function testFile(fitxerNorma){
			console.log("files: " + fitxerNorma.files.length);
			if (fitxerNorma.files.length > 0) {
				return true;
			}
			
			var div = fitxerNorma.parentElement.nextElementSibling;
			var a = $(div).find("a").length;
			
			console.log("a: " + a);
			
			if (div.style.display != "none" && a > 0) {
	            return true;
	        }
			
			return false;
		}
		
		function crearMissatgeError(element, missatge) {
			var span = document.createElement("span");
			span.id = element.id + ".errors";
			span.classList = "errorField alert alert-danger";
			span.innerHTML = missatge;
			if (element.type == "file") {
				element.parentElement.parentElement.prepend(span);
			} else {
				element.parentElement.prepend(span);
			}
		}
	</script>
</c:if>



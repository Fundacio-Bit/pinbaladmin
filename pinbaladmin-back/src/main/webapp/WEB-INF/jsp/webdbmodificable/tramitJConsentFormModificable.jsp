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
				var span = document.createElement("span");
				span.className = "btn btn-danger btn-sm";
				span.style.padding = "10px";
				span.innerHTML = "<i class='fa fa-times'></i>";
				span.onclick = function(){
                    eliminarFitxer(input);
                    span.style.display = "none";
                }
				$(div).append(span);
			})
		});		
		
		function eliminarFitxer(fitxerNorma){
//			var fitxerNorma = document.getElementById("fitxernorma" + 2 + "ID");

			var list = new DataTransfer();
			fitxerNorma.files = list.files;

			var div_file = fitxerNorma.parentElement.nextElementSibling.children[0];
			div_file.style.display="none"
		}
	</script>
</c:if>



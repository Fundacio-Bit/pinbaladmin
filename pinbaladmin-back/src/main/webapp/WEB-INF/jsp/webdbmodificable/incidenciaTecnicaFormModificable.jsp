

<c:if test="${isPinfo == 'true'}">
	<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

	<style>
form {
	padding: 2rem;
	border-radius: 6px;
	border: 2px solid black;
	margin: 2rem 7rem;
}

#incidenciaTecnica_tableid {
	width: auto;
	margin: auto;
}

td {
	vertical-align: middle !important;
}

label {
	padding-left: 1rem;
}
</style>

	<script type="text/javascript">
		$(document).ready(function() {
			var myInterval = setInterval(myIntervalFunction, 1);
			
			function myIntervalFunction() {
				console.log("myIntervalFunction");
				var textRich = document.getElementById("incidenciaTecnica.descripcio_parent")
	
				if (textRich != null) {
					myGreeting();
					console.log("myIntervalFunction - clearInterval");
					clearInterval(myInterval);
				}
			}
		});
		
		function myGreeting() {
			console.log("myGreeting");
			var textArea = document.getElementById("incidenciaTecnica.descripcio")
			textArea.style.display = "block";
			textArea.classList = "w-100 form-control";

			var textRich = document.getElementById("incidenciaTecnica.descripcio_parent")
			textRich.style.display = "none";
		}
	</script>

</c:if>

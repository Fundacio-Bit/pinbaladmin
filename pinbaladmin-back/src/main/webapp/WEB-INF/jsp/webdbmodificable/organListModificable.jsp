


<script>

<%-- function mostrarJerarquia(organid) {
    $('#jerarquiaModal').modal();
    
    var base = '<%=request.getContextPath()%>${contexte}/mostrarJerarquia/' + organid;
    console.log(base);
    spanJerarquia
//    window.location = base +peticioId + '/' + btoa(window.location);
} --%>



function mostrarJerarquia(organid) {
	
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		var jerarquia = this.responseText;
            
            console.log(jerarquia );
            var lineas = jerarquia.split('|');
            var stringJerarquia = lineas[lineas.length - 1];
            for (var j = 1; j < lineas.length; j++) {
                stringJerarquia += "<br>" + "&emsp;".repeat(3 * (j-1) + 1)
                        + String.fromCharCode(9492) + String.fromCharCode(9472) + " " + lineas[lineas.length - 1 - j];
            }

            
            
            
            console.log(stringJerarquia );
            
            $("#titolModal").html("Jerarquia");
            $("#spanJerarquia").html(stringJerarquia)
            $("#jerarquiaModal").modal();
         }
    };

    var base = '<%=request.getContextPath()%>${contexte}/mostrarJerarquia/' + organid;
    xhttp.open("GET", base, true);

    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send('');
}


</script>

<div class="modal fade" style="display: none" id="jerarquiaModal"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" style="max-width: max-content;"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="titolModal">
					<fmt:message key="titol.del.modal" />
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<p id="spanJerarquia" style="width: max-content;margin-right: auto;margin-left: auto;">jerarquia</p>
			</div>
		</div>
	</div>
</div>



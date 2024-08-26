<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<h3>Entrades Que Estic Fent</h3>

<form method="POST">
	Data: <br> <input id="inputDate" type="date" name="data"
		value="${data}"> <input id="inputSubmit" type="submit"
		value="Cercar" />
</form>


<c:if test="${empty items}">
	<b>NO HI HA ENTRADES PER AFEGIR!!!!!</b>
</c:if>


<style>
#inputDate {
	width: 10rem;
}

#inputSubmit {
	margin-left: 1rem;
	width: 6rem;
}

#taulaEntrades, #taulaEntradesAfegides {
	margin-top: 1rem;
	width: auto;
}

th {
	text-align: center;
}

.itemInfo {
	padding: 5px;
}

.itemInfo.itemSel {
	text-align: center;
	width: 2rem;
	vertical-align: middle;
}

.itemInfo.itemAdd {
	text-align: center;
	width: 0px;
	vertical-align: middle;
	color: #007bff;
}

.itemInfo.itemID {
	/* 	width: 150px; */
	
}

.myButton {
	color: white !important;
	margin-top: 1rem;
}

.itemInfo.itemHash {
	display: none;
}
</style>

<c:if test="${not empty items}">

	<a id="btnAddSelected" class="btn btn-info btn-sm myButton"
		onclick="afegirSeleccionats();" title="Afegir">
		 Afegir Seleccionats
	</a>

	<a id="btnAddAll" class="btn btn-primary btn-sm myButton"
		onclick="afegirTotesLesEntrades();" title="Afegir"> 
		
		<i class="fas fa-plus"></i> 
		
		Afegir totes les entrades
	</a>

	<table id="taulaEntrades" border="1"
		class="table table-sm table-bordered table-striped table-genapp">

		<thead>
			<tr>
				<th></th>
				<th>Tipus [ID]</th>
				<th>Títol</th>
				<th>Afegir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${items}">
				<tr>
					<td class="itemInfo itemSel"><input type="checkbox"
						name='checkbox[]' value='${item[2]}'></td>
					<td class="itemInfo itemID">${item[0]}</td>
					<td class="itemInfo itemMsg">${item[1]}</td>
					<td class="itemInfo itemHash">${item[2]}</td>
					<td class="itemInfo itemAdd"
						onclick="afegirUnaEntrada('${item[2]}')"><i
						class="fas fa-plus"></i></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<br>
	<c:if test="${not empty itemsAfegits}">
		<h4>Entrades Afegides</h4>
		<table id="taulaEntradesAfegides" border="1"
			class="table table-sm table-bordered table-striped table-genapp">

			<thead>
				<tr>
					<th></th>
					<th>Tipus [ID]</th>
					<th>Títol</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="afegit" items="${itemsAfegits}">
					<tr>
						<td class="itemInfo itemSel"><input type="checkbox"
							name='checkbox[]' value='${afegit[2]}'></td>
						<td class="itemInfo itemID">${afegit[0]}</td>
						<td class="itemInfo itemMsg">${afegit[1]}</td>
						<td class="itemInfo itemHash">${afegit[2]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<script type="text/javascript">
    	var user = '<%=request.getRemoteUser()%>';
    	var data = $("#inputDate").val();
	
        var ENTRADES_PER_AFEGIR;
    	var ENTRADES_AFEGIDES;
    	
    	function afegirEntradaXhttp(user, date, msg){
            
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                	testFinal();
                }
            };

            var url =  '<%=request.getContextPath()%> ${contexte}/afegirEntrada/' + user + "/" + date + "/" + msg;
            xhttp.open("GET", url, true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send('');
        }
        
    	
		function afegirUnaEntrada(missatge) {
			ENTRADES_AFEGIDES= 0;
			ENTRADES_PER_AFEGIR = 1;
			afegirEntradaXhttp(user, data, missatge);
		}

		function afegirTotesLesEntrades() {
			var items = document.getElementsByClassName("itemInfo itemHash");
			ENTRADES_AFEGIDES= 0;
            ENTRADES_PER_AFEGIR = items.length;

			for (var i = 0; i < items.length; i++) {
				var missatge = items[i].innerText;
				afegirEntradaXhttp(user, data, missatge);
			}
		}
		
		function afegirSeleccionats(){
	        var items = $('input[type=checkbox]:checked');
	        ENTRADES_AFEGIDES= 0;
            ENTRADES_PER_AFEGIR = items.length;

			for (var i = 0; i < items.length; i++) {
				var missatge = items[i].value;
				afegirEntradaXhttp(user, data, missatge);
			}
		}
		
		function testFinal(){
			ENTRADES_AFEGIDES++;

			if (ENTRADES_AFEGIDES == ENTRADES_PER_AFEGIR) {
				if (ENTRADES_AFEGIDES == 1) {
	                alert("S'ha afegit l'entrada");
				}else{
	                alert("S'han afegit " + ENTRADES_AFEGIDES + " entrades");
				}
			}
		}
	</script>

</body>
</html>
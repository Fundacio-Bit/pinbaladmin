<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:forEach var="entry" items="${divServeis}">

	<%-- 
<div id="modal_infoservei_${entry.key}" class="modal hide fade" style="width:750px" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 id="myModalLabel"><fmt:message key="solicitud_info"/></h3>
  </div>
  <div class="modal-body">
     <table >
      <tr style="border: 1px solid #f4f4f4 ;">
        <td align="right"><b><fmt:message key="solicitud.notes"/>: &nbsp;</b></td>
        <td> ${notesSolicitud[entry.key]} </td>
      </tr>
      <tr style="border: 1px solid #f4f4f4 ;">
        <td align="right"><b> <fmt:message key="solicitud.codiDescriptiu"/>:&nbsp;</b></td>
        <td> ${codiDescriptiuSolicitud[entry.key]} </td>
      </tr>
    </table>
    
  </div>

  <div class="modal-header">
    <h4 id="myModalLabel"><fmt:message key="servei.llistat"/></h4>
  </div>
  <div class="modal-body">
    ${entry.value}
  </div>
</div>
--%>

	<div id="modal_infoservei_${entry.key}" class="modal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document" style="max-width: 45%;">
			<div class="modal-content" style="width: fit-content;">
				<div class="modal-header">
					<h4 id="myModalLabel">
						<fmt:message key="servei.llistat" />
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b><fmt:message key="solicitud.notes" />:
									&nbsp;</b></td>
							<td>${notesSolicitud[entry.key]}</td>
						</tr>
						<tr style="border: 1px solid #f4f4f4;">
							<td align="right"><b> <fmt:message
										key="solicitud.codiDescriptiu" />:&nbsp;
							</b></td>
							<td>${codiDescriptiuSolicitud[entry.key]}</td>
						</tr>
					</table>
					${entry.value}
				</div>
			</div>
		</div>
	</div>



</c:forEach>



<style>
.elemOrgan {
/* 	width: 30%; */
}


.pOrganOpen {
	/* 	color: green; */
	max-width: fit-content;
/* 	font-weight: bold; */
}

.pOrganClose {
	/* 	color: orange; */
	max-width: fit-content;
}

.spanOrganOpen {
	font-weight: normal;
}

.spanOrganClose {
	display: none;
}

.nomProcOrganOpen {
/* 	width: 30%;
 */}
</style>

<script>
/*
    var tabla = document.getElementsByTagName("table");
	var ths = tabla[0].children[0].children[0].children;
	var trs = tabla[0].children[1].children;

	var nColOrgan;
	var nColNomProc;
	var nColEntitat;
	for (let i = 0; i < ths.length; i++) {
		let col = ths[i].innerHTML;
		console.log(col);
		if (col.indexOf('organid') > 0) {
			nColOrgan = i;
		}
		if (col.indexOf('procedimentNom') > 0) {
			nColNomProc = i;
		}
        if (col.indexOf('Entitat') > 0) {
            nColEntitat = i;
        }
		
	}

 	for (var i = 0; i < trs[0].children.length; i++) {
		if (i != nColOrgan && i != nColNomProc && i != nColEntitat) {
			trs[0].children[i].style.width = "0px";
		}
	}

 	if (nColOrgan != null) {
		var espacioEnBlanco = String.fromCharCode(32); // Carácter de espacio en blanco

		for (var i = 0; i < trs.length; i++) {
			var tdOrgan = trs[i].children[nColOrgan];
			var tdNomProc = trs[i].children[nColNomProc];

			tdOrgan.classList.add("elemOrgan");
			tdNomProc.classList.add("nomProcOrganOpen");

			var txt = tdOrgan.innerText;
		     console.log(txt);
		     
			var lineas = txt.split('|');

	
			var stringJerarquia = "";
			for (var j = 1; j < lineas.length; j++) {
				stringJerarquia += "<br>" + "&nbsp;".repeat(3 * j)
						+ String.fromCharCode(9492) + " " + lineas[j];
			}

			var p = document.createElement("p");
			p.innerHTML = lineas[0];
			p.classList.add("pOrganClose");

			var span = document.createElement("span");
			span.innerHTML = stringJerarquia;
			span.classList.add("spanOrganClose");

			p.appendChild(span);

			tdOrgan.innerHTML = '';
			tdOrgan.appendChild(p);

			tdOrgan.onclick = function() {
				toggleJerarquia(this);
			}
		}
	} */

	var organs = $(".elemOrgan");
	for (var i = 0; i < organs.length; i++) {
		var pa = organs[i];
		pa.parentElement.style.width = "30%";
		pa.parentElement.onclick = pa.onclick;
		pa.onclick = null;
	}
	
	function toggleJerarquia(elem) {

		console.log(elem);
		var myP = elem.getElementsByTagName("p")[0];
		console.log(myP);
		var mySpan = elem.getElementsByTagName("span")[0];
		console.log(mySpan);

		$(myP).toggleClass("pOrganOpen");
		$(myP).toggleClass("pOrganClose");

		$(mySpan).toggleClass("spanOrganOpen");
		$(mySpan).toggleClass("spanOrganClose");
	}
</script>




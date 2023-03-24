<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>



<h3>Dades de craci&ocute; de la Incid&egrave;ncia al MinHAP</h3>

<form id="myForm" action="${action}" method="post" target="_blank">
	<table
		class="tdformlabel table-condensed table table-bordered table-striped marTop10  ">
		<tbody>
			<tr>
				<td><label>Nom</label></td>
				<td><input type="text" name="nom" value="${nombre}" /></td>
			</tr>

			<tr>
				<td><label>Llinatge1</label></td>
				<td><input type="text" name="llinatge1" value="${apellido1}" /></td>
			</tr>

			<tr>
				<td><label>Llinatge2</label></td>
				<td><input type="text" name="llinatge2" value="${apellido2}" /></td>
			</tr>

			<tr>
				<td><label>Email</label></td>
				<td><input type="text" name="email" value="${email}" /></td>
			</tr>


			<tr>
				<td><label>Asumpte</label></td>
				<td><input type="text" name="asunto" value="${asunto}" /></td>
			</tr>

			<tr>
				<td><label>Fitxers</label></td>
				<td>
					<ul>
						<c:forEach items="${fitxers}" var="fitxer">
							<li>${fitxer.nom}( ${fitxer.tamany} bytes)</li>
						</c:forEach>
					</ul>
				</td>
			</tr>

			<tr>
				<td><label>Entorn afectat</label></td>
				<td><select name="entorn" id="Produccio">
						<option value="false" ${produccio ? '':'selected="selected"'}>Desarrollo</option>
						<option value="true" ${produccio ? 'selected="selected"':''}>Producci&oacute;n</option>
				</select></td>
			</tr>

			<tr>
				<td><label>Comentari</label></td>
				<td><textarea name="comentari" rows="4" cols="90">${comentario}</textarea></td>
			</tr>

			<tr>
				<td><label>Debug</label></td>
				<td><input type="checkbox" name="debug" value="true"></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit"
					value="Crear Incid&egrave;ncia"></td>
			</tr>

		</tbody>
	</table>
	<input type="hidden" name="fitxer" value="${fitxerB64}">

</form>
<script>

$('#myForm').submit(function() {
    setTimeout(() => {
       // alert('Handler for .submit() called.');
       document.location.href="${backurl}"
    }, 7000);
    
    return true;
  });

</script>
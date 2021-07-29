<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form id="myForm" action="${action}" method="post" target="_blank">
    <table
        class="tdformlabel table-condensed table table-bordered table-striped marTop10  ">
        <tbody>
            <tr>
                <td><label>Nombre</label></td>
                <td><input type="text" name="nom" value="${nombre}" /></td>
            </tr>

            <tr>
                <td><label>Apellido1</label></td>
                <td><input type="text" name="llinatge1" value="${apellido1}" /></td>
            </tr>

            <tr>
                <td><label>Apellido2</label></td>
                <td><input type="text" name="llinatge2" value="${apellido2}" /></td>
            </tr>

            <!--  tr>
				<td><label>Organismo</label></td>
				<td><input type = "text" name="data[organismo]" value="${organismo}" /></td>
			</tr -->

            <tr>
                <td><label>Email</label></td>
                <td><input type="text" name="email" value="${email}" /></td>
            </tr>


            <tr>
                <td><label>Asunto</label></td>
                <td><input type="text" name="asunto" value="${asunto}" /></td>
            </tr>

            <tr>
                <td><label>Fichero(*)</label></td>
                <td><input type="text" name="data[tipo]" readonly="readonly"
                    value="Plantilla-Procedimientos.xlsx" /></td>
            </tr>

            <tr>
                <td><label>Entorno afectado</label></td>
                <td><select name="entorn" id="Produccio">
                        <option value="false" ${produccio ? '':'selected="selected"'}>Desarrollo</option>
                        <option value="true" ${produccio ? 'selected="selected"':''}>Producci&oacute;n</option>
                </select></td>
            </tr>

            <tr>
                <td><label>Comentario</label></td>
                <td><textarea name="comentari" rows="4" cols="90">${comentario}</textarea></td>
            </tr>

            <tr>
                <td><label>Debug</label></td>
                <td><input type="checkbox" name="debug" value="true"></td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Submit"></td>
            </tr>

        </tbody>
    </table>
    (*) Sempre s'utilitzar&agrave; el nom d'aquest fitxer per donar d'alta la
    incid&egrave;ncia. <input type="hidden" name="callback" value="${callback}"> <input
        type="hidden" name="fitxer" value="${fitxerB64}">

</form>
<script>

$('#myForm').submit(function() {
    setTimeout(() => {
       // alert('Handler for .submit() called.');
       document.location.href="${backurl}"
       
    }, 5000);
    
    return true;
  });


</script>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form action = "https://ssweb.seap.minhap.es/ayuda/consulta/CAID" method="post" target="_blank">
	<table class="tdformlabel table-condensed table table-bordered table-striped marTop10  "> 
	    <tbody>    
			<tr>
				<td><label>Nombre</label></td>
				<td><input type = "text" name = "data[nombre]" value="${nombre}" /></td>
			</tr>
			
			<tr>
				<td><label>Apellido1</label></td>
				<td><input type = "text" name="data[apellido1]" value="${apellido1}" /></td>
			</tr>
			
			<tr>
				<td><label>Apellido2</label></td>
				<td><input type = "text" name="data[apellido2]" value="${apellido2}" /></td>
			</tr>
			
			<!--  tr>
				<td><label>Organismo</label></td>
				<td><input type = "text" name="data[organismo]" value="${organismo}" /></td>
			</tr -->
			
			<tr>
				<td><label>Email</label></td>
				<td><input type = "text" name="data[email]" value="${email}" /></td>
			</tr>
			
			<tr>
				<td><label>Confirmación email</label></td>
				<td><input type = "text" name="data[email2]" value="${email}" /></td>
			</tr>
			
			<tr>
				<td><label>Asunto</label></td>
				<td><input type = "text" name="data[asunto]" value="${asunto}" /></td>
			</tr>
			
			<tr>
				<td><label>Tipo</label></td>
				<td><input type = "text" name="data[tipo]" value="${tipo}" /></td>
			</tr>
			
			<tr>
				<td><label>Entorno afectado</label></td>
				<td><input type = "text" name="data[entorno_afectado]" value="${entorno}" /></td>
			</tr>
			
			<tr>
				<td><label>Comentario</label></td>
				<td><textarea name="data[comentario]">${comentario}</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type = "submit" value = "Submit"></td>
			</tr>
	     
	     </tbody>
	</table>

</form>
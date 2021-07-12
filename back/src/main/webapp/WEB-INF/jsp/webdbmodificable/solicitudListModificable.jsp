
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:forEach var="entry" items="${divServeis}">
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
</c:forEach>





<div id="modal_filtreavanzat" class="modal hide fade" style="width:750px" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 id="myModalLabel">Filtre Avan&ccedil;at</h3>
  </div>
  <div class="modal-body">
     <table >
      <tr style="border: 1px solid #f4f4f4 ;">
        <td align="right"><b><fmt:message key="solicitud.notes"/>: &nbsp;</b></td>
        <td> <input id="filtreavanzat" type="text" value="${filtreavanzat}" />  </td>
      </tr>
      <tr>
        <td colspan="2"> <button class="btn btn-primary" onclick="defineAdvancedFilter()">Filtrar</button>
      </tr>
     
    </table>
    
  </div>

</div>


<script>

function showAdvancedFilter() {
    $('#modal_filtreavanzat').modal('show');
}


function defineAdvancedFilter() {
    var field = document.getElementById("filtreavanzat");
    document.location.href = "${contexte}/advancedfilter?filter=" + encodeURIComponent(field.value);
}



function deleteAdvancedFilter() {
    var field = document.getElementById("filtreavanzat");
    document.location.href = "${contexte}/advancedfilter?filter=";
}

</script>



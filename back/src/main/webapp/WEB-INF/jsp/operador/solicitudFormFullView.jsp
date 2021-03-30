
<%@include file="/WEB-INF/jsp/webdb/solicitudForm.jsp" %>
<br/>
<br/>

<script> 
    $(function(){
      $("#includedContentSolicitudServei").load("<c:url value="/operador/solicitudserveionlycontent/list/1?solicitudID=${solicitudForm.solicitud.solicitudID}" />");
      $("#includedContentSolicitudDocument").load("<c:url value="/operador/solicituddocumentonlycontent/list/1?solicitudID=${solicitudForm.solicitud.solicitudID}" />");
    });
</script> 

<style type="text/css">
.pagination {display: none; }
</style>
<hr/>
<div id="includedContentSolicitudServei"></div>
<hr/>
<div id="includedContentSolicitudDocument"></div>
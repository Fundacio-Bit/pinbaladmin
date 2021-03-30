<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:forEach var="entry" items="${divSolicituds}">
<div id="modal_infosoli_${entry.key}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <h3 id="myModalLabel"><fmt:message key="solicitud.llistat"/></h3>
  </div>
  <div class="modal-body">
    ${entry.value}
  </div>
</div>
</c:forEach>
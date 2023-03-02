<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:forEach var="entry" items="${divSolicituds}">

<%--
<div id="modal_infosoli_${entry.key}" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 id="myModalLabel"><fmt:message key="solicitud.llistat"/></h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         ${entry.value}
      </div>
      

    </div>
  </div>
</div>
 --%>
 
 <div id="modal_infosoli_${entry.key}" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
       <h3 id="myModalLabel"><fmt:message key="solicitud.llistat"/></h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ${entry.value}
      </div>
     
    </div>
  </div>
</div>
 
 

</c:forEach>
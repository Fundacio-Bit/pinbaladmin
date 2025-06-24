  <c:if test="${empty modificacioSolicitudItems}">
     <%@include file="modificacioSolicitudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty modificacioSolicitudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="modificacioSolicitudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="modificacioSolicitudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="modificacioSolicitudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="modificacioSolicitud" items="${modificacioSolicitudItems}">

        <tr id="modificacioSolicitud_rowid_${modificacioSolicitud.modsoliID}">
          <%@include file="modificacioSolicitudListCoreMultipleSelect.jsp" %>

          <%@include file="modificacioSolicitudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="modificacioSolicitudListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

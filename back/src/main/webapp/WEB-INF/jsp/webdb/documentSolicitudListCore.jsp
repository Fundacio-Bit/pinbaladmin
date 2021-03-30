  <c:if test="${empty documentSolicitudItems}">
     <%@include file="documentSolicitudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty documentSolicitudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="documentSolicitudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="documentSolicitudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="documentSolicitudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="documentSolicitud" items="${documentSolicitudItems}">

        <tr id="documentSolicitud_rowid_${documentSolicitud.documentSolicitudID}">
          <%@include file="documentSolicitudListCoreMultipleSelect.jsp" %>

          <%@include file="documentSolicitudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="documentSolicitudListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

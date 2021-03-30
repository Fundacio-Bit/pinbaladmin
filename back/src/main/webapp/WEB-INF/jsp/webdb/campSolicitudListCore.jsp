  <c:if test="${empty campSolicitudItems}">
     <%@include file="campSolicitudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty campSolicitudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="campSolicitudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="campSolicitudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="campSolicitudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="campSolicitud" items="${campSolicitudItems}">

        <tr id="campSolicitud_rowid_${campSolicitud.campSolicitudID}">
          <%@include file="campSolicitudListCoreMultipleSelect.jsp" %>

          <%@include file="campSolicitudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="campSolicitudListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

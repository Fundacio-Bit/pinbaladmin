  <c:if test="${empty estatSolicitudItems}">
     <%@include file="estatSolicitudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatSolicitudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estatSolicitudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estatSolicitudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estatSolicitudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatSolicitud" items="${estatSolicitudItems}">

        <tr id="estatSolicitud_rowid_${estatSolicitud.estatSolicitudID}">
          <%@include file="estatSolicitudListCoreMultipleSelect.jsp" %>

          <%@include file="estatSolicitudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatSolicitudListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

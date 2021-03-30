  <c:if test="${empty solicitudItems}">
     <%@include file="solicitudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty solicitudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="solicitudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="solicitudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="solicitudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="solicitud" items="${solicitudItems}">

        <tr id="solicitud_rowid_${solicitud.solicitudID}">
          <%@include file="solicitudListCoreMultipleSelect.jsp" %>

          <%@include file="solicitudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="solicitudListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

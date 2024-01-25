  <c:if test="${empty documentEntitatItems}">
     <%@include file="documentEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty documentEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="documentEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="documentEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="documentEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="documentEntitat" items="${documentEntitatItems}">

        <tr id="documentEntitat_rowid_${documentEntitat.documentEntitatID}">
          <%@include file="documentEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="documentEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="documentEntitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

  <c:if test="${empty documentCedentItems}">
     <%@include file="documentCedentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty documentCedentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="documentCedentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="documentCedentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="documentCedentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="documentCedent" items="${documentCedentItems}">

        <tr id="documentCedent_rowid_${documentCedent.documentCedentID}">
          <%@include file="documentCedentListCoreMultipleSelect.jsp" %>

          <%@include file="documentCedentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="documentCedentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

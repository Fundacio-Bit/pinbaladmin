  <c:if test="${empty documentItems}">
     <%@include file="documentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty documentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="documentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="documentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="documentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="document" items="${documentItems}">

        <tr id="document_rowid_${document.documentID}">
          <%@include file="documentListCoreMultipleSelect.jsp" %>

          <%@include file="documentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="documentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

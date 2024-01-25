  <c:if test="${empty estatTiquetItems}">
     <%@include file="estatTiquetListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatTiquetItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estatTiquetListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estatTiquetListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estatTiquetListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatTiquet" items="${estatTiquetItems}">

        <tr id="estatTiquet_rowid_${estatTiquet.estatTiquetID}">
          <%@include file="estatTiquetListCoreMultipleSelect.jsp" %>

          <%@include file="estatTiquetListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatTiquetListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

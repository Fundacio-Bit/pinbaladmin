  <c:if test="${empty tiquetItems}">
     <%@include file="tiquetListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tiquetItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tiquetListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tiquetListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tiquetListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tiquet" items="${tiquetItems}">

        <tr id="tiquet_rowid_${tiquet.tiquetID}">
          <%@include file="tiquetListCoreMultipleSelect.jsp" %>

          <%@include file="tiquetListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tiquetListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

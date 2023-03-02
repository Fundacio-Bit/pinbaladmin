  <c:if test="${empty departamentItems}">
     <%@include file="departamentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty departamentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="departamentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="departamentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="departamentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="departament" items="${departamentItems}">

        <tr id="departament_rowid_${departament.departamentID}">
          <%@include file="departamentListCoreMultipleSelect.jsp" %>

          <%@include file="departamentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="departamentListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

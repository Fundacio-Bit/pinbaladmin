  <c:if test="${empty operadorItems}">
     <%@include file="operadorListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty operadorItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="operadorListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="operadorListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="operadorListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="operador" items="${operadorItems}">

        <tr id="operador_rowid_${operador.operadorID}">
          <%@include file="operadorListCoreMultipleSelect.jsp" %>

          <%@include file="operadorListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="operadorListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

  <c:if test="${empty grupEntitatCedentItems}">
     <%@include file="grupEntitatCedentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupEntitatCedentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="grupEntitatCedentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="grupEntitatCedentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="grupEntitatCedentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="grupEntitatCedent" items="${grupEntitatCedentItems}">

        <tr id="grupEntitatCedent_rowid_${grupEntitatCedent.grupEntitatCedentID}">
          <%@include file="grupEntitatCedentListCoreMultipleSelect.jsp" %>

          <%@include file="grupEntitatCedentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="grupEntitatCedentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

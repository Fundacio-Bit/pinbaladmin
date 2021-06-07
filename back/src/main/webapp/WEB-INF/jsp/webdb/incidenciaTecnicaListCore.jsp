  <c:if test="${empty incidenciaTecnicaItems}">
     <%@include file="incidenciaTecnicaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty incidenciaTecnicaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="incidenciaTecnicaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="incidenciaTecnicaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="incidenciaTecnicaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="incidenciaTecnica" items="${incidenciaTecnicaItems}">

        <tr id="incidenciaTecnica_rowid_${incidenciaTecnica.incidenciaTecnicaID}">
          <%@include file="incidenciaTecnicaListCoreMultipleSelect.jsp" %>

          <%@include file="incidenciaTecnicaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="incidenciaTecnicaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

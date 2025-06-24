  <c:if test="${empty modificacioSoliServItems}">
     <%@include file="modificacioSoliServListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty modificacioSoliServItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="modificacioSoliServListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="modificacioSoliServListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="modificacioSoliServListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="modificacioSoliServ" items="${modificacioSoliServItems}">

        <tr id="modificacioSoliServ_rowid_${modificacioSoliServ.modsoliservid}">
          <%@include file="modificacioSoliServListCoreMultipleSelect.jsp" %>

          <%@include file="modificacioSoliServListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="modificacioSoliServListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

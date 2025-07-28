  <c:if test="${empty infoMadridItems}">
     <%@include file="infoMadridListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty infoMadridItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="infoMadridListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="infoMadridListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="infoMadridListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="infoMadrid" items="${infoMadridItems}">

        <tr id="infoMadrid_rowid_${infoMadrid.infoMadridID}">
          <%@include file="infoMadridListCoreMultipleSelect.jsp" %>

          <%@include file="infoMadridListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="infoMadridListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

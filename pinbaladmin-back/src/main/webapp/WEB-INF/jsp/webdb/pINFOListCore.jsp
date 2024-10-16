  <c:if test="${empty pINFOItems}">
     <%@include file="pINFOListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pINFOItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pINFOListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pINFOListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pINFOListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pINFO" items="${pINFOItems}">

        <tr id="pINFO_rowid_${pINFO.PinfoID}">
          <%@include file="pINFOListCoreMultipleSelect.jsp" %>

          <%@include file="pINFOListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pINFOListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

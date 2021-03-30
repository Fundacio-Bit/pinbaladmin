  <c:if test="${empty areaItems}">
     <%@include file="areaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty areaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="areaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="areaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="areaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="area" items="${areaItems}">

        <tr id="area_rowid_${area.areaID}">
          <%@include file="areaListCoreMultipleSelect.jsp" %>

          <%@include file="areaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="areaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

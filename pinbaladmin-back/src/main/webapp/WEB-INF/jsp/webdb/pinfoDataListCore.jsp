  <c:if test="${empty pinfoDataItems}">
     <%@include file="pinfoDataListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pinfoDataItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pinfoDataListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pinfoDataListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pinfoDataListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pinfoData" items="${pinfoDataItems}">

        <tr id="pinfoData_rowid_${pinfoData.pinfodataID}">
          <%@include file="pinfoDataListCoreMultipleSelect.jsp" %>

          <%@include file="pinfoDataListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pinfoDataListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

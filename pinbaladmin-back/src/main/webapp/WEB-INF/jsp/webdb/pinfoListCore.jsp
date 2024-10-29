  <c:if test="${empty pinfoItems}">
     <%@include file="pinfoListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pinfoItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pinfoListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pinfoListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pinfoListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pinfo" items="${pinfoItems}">

        <tr id="pinfo_rowid_${pinfo.pinfoID}">
          <%@include file="pinfoListCoreMultipleSelect.jsp" %>

          <%@include file="pinfoListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pinfoListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

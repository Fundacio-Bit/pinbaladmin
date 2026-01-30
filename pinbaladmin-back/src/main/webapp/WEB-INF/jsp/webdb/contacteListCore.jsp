  <c:if test="${empty contacteItems}">
     <%@include file="contacteListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty contacteItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="contacteListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="contacteListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="contacteListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="contacte" items="${contacteItems}">

        <tr id="contacte_rowid_${contacte.ContacteID}">
          <%@include file="contacteListCoreMultipleSelect.jsp" %>

          <%@include file="contacteListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="contacteListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

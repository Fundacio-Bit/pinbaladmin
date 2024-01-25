  <c:if test="${empty emailItems}">
     <%@include file="emailListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty emailItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="emailListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="emailListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="emailListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="email" items="${emailItems}">

        <tr id="email_rowid_${email.emailID}">
          <%@include file="emailListCoreMultipleSelect.jsp" %>

          <%@include file="emailListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="emailListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

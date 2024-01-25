  <c:if test="${empty serveiItems}">
     <%@include file="serveiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty serveiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="serveiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="serveiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="serveiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="servei" items="${serveiItems}">

        <tr id="servei_rowid_${servei.serveiID}">
          <%@include file="serveiListCoreMultipleSelect.jsp" %>

          <%@include file="serveiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="serveiListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

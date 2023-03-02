  <c:if test="${empty estatServeiItems}">
     <%@include file="estatServeiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatServeiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estatServeiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estatServeiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estatServeiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatServei" items="${estatServeiItems}">

        <tr id="estatServei_rowid_${estatServei.estatServeiID}">
          <%@include file="estatServeiListCoreMultipleSelect.jsp" %>

          <%@include file="estatServeiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatServeiListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

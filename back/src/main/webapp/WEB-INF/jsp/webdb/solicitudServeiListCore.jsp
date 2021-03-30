  <c:if test="${empty solicitudServeiItems}">
     <%@include file="solicitudServeiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty solicitudServeiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="solicitudServeiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="solicitudServeiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="solicitudServeiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="solicitudServei" items="${solicitudServeiItems}">

        <tr id="solicitudServei_rowid_${solicitudServei.id}">
          <%@include file="solicitudServeiListCoreMultipleSelect.jsp" %>

          <%@include file="solicitudServeiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="solicitudServeiListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

  <c:if test="${empty estatSolicitudServeiItems}">
     <%@include file="estatSolicitudServeiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatSolicitudServeiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estatSolicitudServeiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estatSolicitudServeiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estatSolicitudServeiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatSolicitudServei" items="${estatSolicitudServeiItems}">

        <tr id="estatSolicitudServei_rowid_${estatSolicitudServei.estatSolicitudServeiID}">
          <%@include file="estatSolicitudServeiListCoreMultipleSelect.jsp" %>

          <%@include file="estatSolicitudServeiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatSolicitudServeiListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

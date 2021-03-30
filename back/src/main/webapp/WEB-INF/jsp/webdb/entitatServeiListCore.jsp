  <c:if test="${empty entitatServeiItems}">
     <%@include file="entitatServeiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty entitatServeiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="entitatServeiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="entitatServeiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="entitatServeiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="entitatServei" items="${entitatServeiItems}">

        <tr id="entitatServei_rowid_${entitatServei.entitatServeiID}">
          <%@include file="entitatServeiListCoreMultipleSelect.jsp" %>

          <%@include file="entitatServeiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="entitatServeiListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

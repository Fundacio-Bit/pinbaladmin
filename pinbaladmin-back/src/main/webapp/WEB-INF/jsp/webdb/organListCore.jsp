  <c:if test="${empty organItems}">
     <%@include file="organListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty organItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="organListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="organListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="organListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="organ" items="${organItems}">

        <tr id="organ_rowid_${organ.organid}">
          <%@include file="organListCoreMultipleSelect.jsp" %>

          <%@include file="organListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="organListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

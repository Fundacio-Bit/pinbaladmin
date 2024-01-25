  <c:if test="${empty tramitECteAudItems}">
     <%@include file="tramitECteAudListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitECteAudItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitECteAudListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitECteAudListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitECteAudListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitECteAud" items="${tramitECteAudItems}">

        <tr id="tramitECteAud_rowid_${tramitECteAud.cteaudid}">
          <%@include file="tramitECteAudListCoreMultipleSelect.jsp" %>

          <%@include file="tramitECteAudListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitECteAudListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

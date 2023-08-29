  <c:if test="${empty tramitDCteAutItems}">
     <%@include file="tramitDCteAutListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitDCteAutItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitDCteAutListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitDCteAutListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitDCteAutListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitDCteAut" items="${tramitDCteAutItems}">

        <tr id="tramitDCteAut_rowid_${tramitDCteAut.cteautid}">
          <%@include file="tramitDCteAutListCoreMultipleSelect.jsp" %>

          <%@include file="tramitDCteAutListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitDCteAutListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

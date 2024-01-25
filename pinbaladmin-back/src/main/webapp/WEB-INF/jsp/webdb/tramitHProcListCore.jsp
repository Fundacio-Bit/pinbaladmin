  <c:if test="${empty tramitHProcItems}">
     <%@include file="tramitHProcListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitHProcItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitHProcListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitHProcListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitHProcListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitHProc" items="${tramitHProcItems}">

        <tr id="tramitHProc_rowid_${tramitHProc.procid}">
          <%@include file="tramitHProcListCoreMultipleSelect.jsp" %>

          <%@include file="tramitHProcListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitHProcListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

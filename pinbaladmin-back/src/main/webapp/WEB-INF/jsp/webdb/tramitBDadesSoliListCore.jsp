  <c:if test="${empty tramitBDadesSoliItems}">
     <%@include file="tramitBDadesSoliListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitBDadesSoliItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitBDadesSoliListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitBDadesSoliListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitBDadesSoliListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitBDadesSoli" items="${tramitBDadesSoliItems}">

        <tr id="tramitBDadesSoli_rowid_${tramitBDadesSoli.dadessoliid}">
          <%@include file="tramitBDadesSoliListCoreMultipleSelect.jsp" %>

          <%@include file="tramitBDadesSoliListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitBDadesSoliListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

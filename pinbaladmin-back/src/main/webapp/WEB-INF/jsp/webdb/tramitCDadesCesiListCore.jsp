  <c:if test="${empty tramitCDadesCesiItems}">
     <%@include file="tramitCDadesCesiListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitCDadesCesiItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitCDadesCesiListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitCDadesCesiListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitCDadesCesiListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitCDadesCesi" items="${tramitCDadesCesiItems}">

        <tr id="tramitCDadesCesi_rowid_${tramitCDadesCesi.dadescesiid}">
          <%@include file="tramitCDadesCesiListCoreMultipleSelect.jsp" %>

          <%@include file="tramitCDadesCesiListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitCDadesCesiListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

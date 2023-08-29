  <c:if test="${empty tramitGDadesTitItems}">
     <%@include file="tramitGDadesTitListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitGDadesTitItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitGDadesTitListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitGDadesTitListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitGDadesTitListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitGDadesTit" items="${tramitGDadesTitItems}">

        <tr id="tramitGDadesTit_rowid_${tramitGDadesTit.dadestitid}">
          <%@include file="tramitGDadesTitListCoreMultipleSelect.jsp" %>

          <%@include file="tramitGDadesTitListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitGDadesTitListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

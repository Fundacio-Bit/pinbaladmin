  <c:if test="${empty tipusTiquetItems}">
     <%@include file="tipusTiquetListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusTiquetItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusTiquetListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusTiquetListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusTiquetListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusTiquet" items="${tipusTiquetItems}">

        <tr id="tipusTiquet_rowid_${tipusTiquet.tipusTiquetID}">
          <%@include file="tipusTiquetListCoreMultipleSelect.jsp" %>

          <%@include file="tipusTiquetListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusTiquetListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

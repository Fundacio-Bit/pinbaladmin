  <c:if test="${empty tramitIServItems}">
     <%@include file="tramitIServListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitIServItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitIServListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitIServListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitIServListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitIServ" items="${tramitIServItems}">

        <tr id="tramitIServ_rowid_${tramitIServ.servid}">
          <%@include file="tramitIServListCoreMultipleSelect.jsp" %>

          <%@include file="tramitIServListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitIServListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

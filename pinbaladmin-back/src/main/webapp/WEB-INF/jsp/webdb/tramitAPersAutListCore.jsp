  <c:if test="${empty tramitAPersAutItems}">
     <%@include file="tramitAPersAutListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitAPersAutItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitAPersAutListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitAPersAutListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitAPersAutListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitAPersAut" items="${tramitAPersAutItems}">

        <tr id="tramitAPersAut_rowid_${tramitAPersAut.persautid}">
          <%@include file="tramitAPersAutListCoreMultipleSelect.jsp" %>

          <%@include file="tramitAPersAutListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitAPersAutListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

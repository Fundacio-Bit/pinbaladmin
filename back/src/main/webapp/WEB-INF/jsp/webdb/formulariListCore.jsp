  <c:if test="${empty formulariItems}">
     <%@include file="formulariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty formulariItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="formulariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="formulariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="formulariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="formulari" items="${formulariItems}">

        <tr id="formulari_rowid_${formulari.formulariid}">
          <%@include file="formulariListCoreMultipleSelect.jsp" %>

          <%@include file="formulariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="formulariListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

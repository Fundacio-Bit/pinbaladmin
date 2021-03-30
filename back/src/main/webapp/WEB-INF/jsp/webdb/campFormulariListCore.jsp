  <c:if test="${empty campFormulariItems}">
     <%@include file="campFormulariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty campFormulariItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="campFormulariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="campFormulariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="campFormulariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="campFormulari" items="${campFormulariItems}">

        <tr id="campFormulari_rowid_${campFormulari.campFormulariID}">
          <%@include file="campFormulariListCoreMultipleSelect.jsp" %>

          <%@include file="campFormulariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="campFormulariListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

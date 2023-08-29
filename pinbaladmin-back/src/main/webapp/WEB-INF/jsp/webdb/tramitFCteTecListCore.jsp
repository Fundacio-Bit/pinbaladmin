  <c:if test="${empty tramitFCteTecItems}">
     <%@include file="tramitFCteTecListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitFCteTecItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitFCteTecListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitFCteTecListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitFCteTecListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitFCteTec" items="${tramitFCteTecItems}">

        <tr id="tramitFCteTec_rowid_${tramitFCteTec.ctetecid}">
          <%@include file="tramitFCteTecListCoreMultipleSelect.jsp" %>

          <%@include file="tramitFCteTecListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitFCteTecListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

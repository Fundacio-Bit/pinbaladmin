  <c:if test="${empty tramitJConsentItems}">
     <%@include file="tramitJConsentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tramitJConsentItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tramitJConsentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tramitJConsentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tramitJConsentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tramitJConsent" items="${tramitJConsentItems}">

        <tr id="tramitJConsent_rowid_${tramitJConsent.consentid}">
          <%@include file="tramitJConsentListCoreMultipleSelect.jsp" %>

          <%@include file="tramitJConsentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tramitJConsentListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  

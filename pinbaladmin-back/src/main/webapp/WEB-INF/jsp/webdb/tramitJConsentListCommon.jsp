<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitJConsentFilterForm.contexte}"/>
  <c:set var="formName" value="tramitJConsent" />
  <c:set var="__theFilterForm" value="${tramitJConsentFilterForm}" />
  <c:if test="${empty tramitJConsentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitJConsent.tramitJConsent"/>
  </c:if>
  <c:if test="${not empty tramitJConsentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitJConsentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitJConsentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitJConsent.tramitJConsent"/>
  </c:if>
  <c:if test="${not empty tramitJConsentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitJConsentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitJConsent.submit();  
  }
</script>

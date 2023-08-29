<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitECteAudFilterForm.contexte}"/>
  <c:set var="formName" value="tramitECteAud" />
  <c:set var="__theFilterForm" value="${tramitECteAudFilterForm}" />
  <c:if test="${empty tramitECteAudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitECteAud.tramitECteAud"/>
  </c:if>
  <c:if test="${not empty tramitECteAudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitECteAudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitECteAudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitECteAud.tramitECteAud"/>
  </c:if>
  <c:if test="${not empty tramitECteAudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitECteAudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitECteAud.submit();  
  }
</script>

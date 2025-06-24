<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${modificacioSoliServFilterForm.contexte}"/>
  <c:set var="formName" value="modificacioSoliServ" />
  <c:set var="__theFilterForm" value="${modificacioSoliServFilterForm}" />
  <c:if test="${empty modificacioSoliServFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="modificacioSoliServ.modificacioSoliServ"/>
  </c:if>
  <c:if test="${not empty modificacioSoliServFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${modificacioSoliServFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty modificacioSoliServFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="modificacioSoliServ.modificacioSoliServ"/>
  </c:if>
  <c:if test="${not empty modificacioSoliServFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${modificacioSoliServFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.modificacioSoliServ.submit();  
  }
</script>

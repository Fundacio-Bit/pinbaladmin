<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${serveiFilterForm.contexte}"/>
  <c:set var="formName" value="servei" />
  <c:set var="__theFilterForm" value="${serveiFilterForm}" />
  <c:if test="${empty serveiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="servei.servei"/>
  </c:if>
  <c:if test="${not empty serveiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${serveiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty serveiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="servei.servei"/>
  </c:if>
  <c:if test="${not empty serveiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${serveiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.servei.submit();  
  }
</script>

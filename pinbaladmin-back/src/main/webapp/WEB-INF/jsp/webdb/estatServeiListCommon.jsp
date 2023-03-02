<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estatServeiFilterForm.contexte}"/>
  <c:set var="formName" value="estatServei" />
  <c:set var="__theFilterForm" value="${estatServeiFilterForm}" />
  <c:if test="${empty estatServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatServei.estatServei"/>
  </c:if>
  <c:if test="${not empty estatServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatServeiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatServei.estatServei"/>
  </c:if>
  <c:if test="${not empty estatServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatServeiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estatServei.submit();  
  }
</script>

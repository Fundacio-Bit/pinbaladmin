<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${entitatServeiFilterForm.contexte}"/>
  <c:set var="formName" value="entitatServei" />
  <c:set var="__theFilterForm" value="${entitatServeiFilterForm}" />
  <c:if test="${empty entitatServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="entitatServei.entitatServei"/>
  </c:if>
  <c:if test="${not empty entitatServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${entitatServeiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty entitatServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="entitatServei.entitatServei"/>
  </c:if>
  <c:if test="${not empty entitatServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${entitatServeiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.entitatServei.submit();  
  }
</script>

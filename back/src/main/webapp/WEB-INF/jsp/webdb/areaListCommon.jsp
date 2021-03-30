<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${areaFilterForm.contexte}"/>
  <c:set var="formName" value="area" />
  <c:set var="__theFilterForm" value="${areaFilterForm}" />
  <c:if test="${empty areaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="area.area"/>
  </c:if>
  <c:if test="${not empty areaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${areaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty areaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="area.area"/>
  </c:if>
  <c:if test="${not empty areaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${areaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.area.submit();  
  }
</script>

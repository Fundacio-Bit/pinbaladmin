<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pINFOFilterForm.contexte}"/>
  <c:set var="formName" value="pINFO" />
  <c:set var="__theFilterForm" value="${pINFOFilterForm}" />
  <c:if test="${empty pINFOFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pINFO.pINFO"/>
  </c:if>
  <c:if test="${not empty pINFOFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pINFOFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pINFOFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pINFO.pINFO"/>
  </c:if>
  <c:if test="${not empty pINFOFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pINFOFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pINFO.submit();  
  }
</script>

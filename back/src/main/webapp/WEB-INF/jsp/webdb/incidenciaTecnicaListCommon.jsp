<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${incidenciaTecnicaFilterForm.contexte}"/>
  <c:set var="formName" value="incidenciaTecnica" />
  <c:set var="__theFilterForm" value="${incidenciaTecnicaFilterForm}" />
  <c:if test="${empty incidenciaTecnicaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="incidenciaTecnica.incidenciaTecnica"/>
  </c:if>
  <c:if test="${not empty incidenciaTecnicaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${incidenciaTecnicaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty incidenciaTecnicaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="incidenciaTecnica.incidenciaTecnica"/>
  </c:if>
  <c:if test="${not empty incidenciaTecnicaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${incidenciaTecnicaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.incidenciaTecnica.submit();  
  }
</script>

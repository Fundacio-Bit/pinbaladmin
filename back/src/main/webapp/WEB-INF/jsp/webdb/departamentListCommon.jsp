<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${departamentFilterForm.contexte}"/>
  <c:set var="formName" value="departament" />
  <c:set var="__theFilterForm" value="${departamentFilterForm}" />
  <c:if test="${empty departamentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="departament.departament"/>
  </c:if>
  <c:if test="${not empty departamentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${departamentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty departamentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="departament.departament"/>
  </c:if>
  <c:if test="${not empty departamentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${departamentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.departament.submit();  
  }
</script>

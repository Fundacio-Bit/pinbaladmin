<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${operadorFilterForm.contexte}"/>
  <c:set var="formName" value="operador" />
  <c:set var="__theFilterForm" value="${operadorFilterForm}" />
  <c:if test="${empty operadorFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="operador.operador"/>
  </c:if>
  <c:if test="${not empty operadorFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${operadorFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty operadorFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="operador.operador"/>
  </c:if>
  <c:if test="${not empty operadorFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${operadorFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.operador.submit();  
  }
</script>

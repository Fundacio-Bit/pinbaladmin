<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${grupEntitatCedentFilterForm.contexte}"/>
  <c:set var="formName" value="grupEntitatCedent" />
  <c:set var="__theFilterForm" value="${grupEntitatCedentFilterForm}" />
  <c:if test="${empty grupEntitatCedentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="grupEntitatCedent.grupEntitatCedent"/>
  </c:if>
  <c:if test="${not empty grupEntitatCedentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupEntitatCedentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty grupEntitatCedentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="grupEntitatCedent.grupEntitatCedent"/>
  </c:if>
  <c:if test="${not empty grupEntitatCedentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${grupEntitatCedentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.grupEntitatCedent.submit();  
  }
</script>

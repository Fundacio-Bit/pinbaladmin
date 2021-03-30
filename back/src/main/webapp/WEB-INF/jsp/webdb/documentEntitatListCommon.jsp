<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${documentEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="documentEntitat" />
  <c:set var="__theFilterForm" value="${documentEntitatFilterForm}" />
  <c:if test="${empty documentEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="documentEntitat.documentEntitat"/>
  </c:if>
  <c:if test="${not empty documentEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${documentEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty documentEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="documentEntitat.documentEntitat"/>
  </c:if>
  <c:if test="${not empty documentEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${documentEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.documentEntitat.submit();  
  }
</script>

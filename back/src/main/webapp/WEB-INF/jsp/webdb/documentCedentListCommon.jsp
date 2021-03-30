<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${documentCedentFilterForm.contexte}"/>
  <c:set var="formName" value="documentCedent" />
  <c:set var="__theFilterForm" value="${documentCedentFilterForm}" />
  <c:if test="${empty documentCedentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="documentCedent.documentCedent"/>
  </c:if>
  <c:if test="${not empty documentCedentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${documentCedentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty documentCedentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="documentCedent.documentCedent"/>
  </c:if>
  <c:if test="${not empty documentCedentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${documentCedentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.documentCedent.submit();  
  }
</script>

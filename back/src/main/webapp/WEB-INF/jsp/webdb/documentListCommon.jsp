<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${documentFilterForm.contexte}"/>
  <c:set var="formName" value="document" />
  <c:set var="__theFilterForm" value="${documentFilterForm}" />
  <c:if test="${empty documentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="document.document"/>
  </c:if>
  <c:if test="${not empty documentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${documentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty documentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="document.document"/>
  </c:if>
  <c:if test="${not empty documentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${documentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.document.submit();  
  }
</script>

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${organFilterForm.contexte}"/>
  <c:set var="formName" value="organ" />
  <c:set var="__theFilterForm" value="${organFilterForm}" />
  <c:if test="${empty organFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="organ.organ"/>
  </c:if>
  <c:if test="${not empty organFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${organFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty organFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="organ.organ"/>
  </c:if>
  <c:if test="${not empty organFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${organFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.organ.submit();  
  }
</script>

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${emailFilterForm.contexte}"/>
  <c:set var="formName" value="email" />
  <c:set var="__theFilterForm" value="${emailFilterForm}" />
  <c:if test="${empty emailFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="email.email"/>
  </c:if>
  <c:if test="${not empty emailFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${emailFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty emailFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="email.email"/>
  </c:if>
  <c:if test="${not empty emailFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${emailFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.email.submit();  
  }
</script>

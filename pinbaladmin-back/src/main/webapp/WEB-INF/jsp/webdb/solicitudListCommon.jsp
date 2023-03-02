<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${solicitudFilterForm.contexte}"/>
  <c:set var="formName" value="solicitud" />
  <c:set var="__theFilterForm" value="${solicitudFilterForm}" />
  <c:if test="${empty solicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="solicitud.solicitud"/>
  </c:if>
  <c:if test="${not empty solicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${solicitudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty solicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="solicitud.solicitud"/>
  </c:if>
  <c:if test="${not empty solicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${solicitudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.solicitud.submit();  
  }
</script>

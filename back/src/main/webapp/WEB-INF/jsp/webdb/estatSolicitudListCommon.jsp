<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estatSolicitudFilterForm.contexte}"/>
  <c:set var="formName" value="estatSolicitud" />
  <c:set var="__theFilterForm" value="${estatSolicitudFilterForm}" />
  <c:if test="${empty estatSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatSolicitud.estatSolicitud"/>
  </c:if>
  <c:if test="${not empty estatSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatSolicitudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatSolicitud.estatSolicitud"/>
  </c:if>
  <c:if test="${not empty estatSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatSolicitudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estatSolicitud.submit();  
  }
</script>

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${modificacioSolicitudFilterForm.contexte}"/>
  <c:set var="formName" value="modificacioSolicitud" />
  <c:set var="__theFilterForm" value="${modificacioSolicitudFilterForm}" />
  <c:if test="${empty modificacioSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="modificacioSolicitud.modificacioSolicitud"/>
  </c:if>
  <c:if test="${not empty modificacioSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${modificacioSolicitudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty modificacioSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="modificacioSolicitud.modificacioSolicitud"/>
  </c:if>
  <c:if test="${not empty modificacioSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${modificacioSolicitudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.modificacioSolicitud.submit();  
  }
</script>

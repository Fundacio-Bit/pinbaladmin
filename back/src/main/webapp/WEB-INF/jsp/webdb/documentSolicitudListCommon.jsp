<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${documentSolicitudFilterForm.contexte}"/>
  <c:set var="formName" value="documentSolicitud" />
  <c:set var="__theFilterForm" value="${documentSolicitudFilterForm}" />
  <c:if test="${empty documentSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="documentSolicitud.documentSolicitud"/>
  </c:if>
  <c:if test="${not empty documentSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${documentSolicitudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty documentSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="documentSolicitud.documentSolicitud"/>
  </c:if>
  <c:if test="${not empty documentSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${documentSolicitudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.documentSolicitud.submit();  
  }
</script>

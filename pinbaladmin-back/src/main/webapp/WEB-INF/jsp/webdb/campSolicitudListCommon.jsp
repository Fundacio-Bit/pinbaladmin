<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${campSolicitudFilterForm.contexte}"/>
  <c:set var="formName" value="campSolicitud" />
  <c:set var="__theFilterForm" value="${campSolicitudFilterForm}" />
  <c:if test="${empty campSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="campSolicitud.campSolicitud"/>
  </c:if>
  <c:if test="${not empty campSolicitudFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${campSolicitudFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty campSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="campSolicitud.campSolicitud"/>
  </c:if>
  <c:if test="${not empty campSolicitudFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${campSolicitudFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.campSolicitud.submit();  
  }
</script>

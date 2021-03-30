<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estatSolicitudServeiFilterForm.contexte}"/>
  <c:set var="formName" value="estatSolicitudServei" />
  <c:set var="__theFilterForm" value="${estatSolicitudServeiFilterForm}" />
  <c:if test="${empty estatSolicitudServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatSolicitudServei.estatSolicitudServei"/>
  </c:if>
  <c:if test="${not empty estatSolicitudServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatSolicitudServeiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatSolicitudServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatSolicitudServei.estatSolicitudServei"/>
  </c:if>
  <c:if test="${not empty estatSolicitudServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatSolicitudServeiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estatSolicitudServei.submit();  
  }
</script>

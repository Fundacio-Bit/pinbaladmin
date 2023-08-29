<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitHProcFilterForm.contexte}"/>
  <c:set var="formName" value="tramitHProc" />
  <c:set var="__theFilterForm" value="${tramitHProcFilterForm}" />
  <c:if test="${empty tramitHProcFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitHProc.tramitHProc"/>
  </c:if>
  <c:if test="${not empty tramitHProcFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitHProcFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitHProcFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitHProc.tramitHProc"/>
  </c:if>
  <c:if test="${not empty tramitHProcFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitHProcFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitHProc.submit();  
  }
</script>

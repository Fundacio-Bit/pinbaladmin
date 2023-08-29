<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitDCteAutFilterForm.contexte}"/>
  <c:set var="formName" value="tramitDCteAut" />
  <c:set var="__theFilterForm" value="${tramitDCteAutFilterForm}" />
  <c:if test="${empty tramitDCteAutFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitDCteAut.tramitDCteAut"/>
  </c:if>
  <c:if test="${not empty tramitDCteAutFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitDCteAutFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitDCteAutFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitDCteAut.tramitDCteAut"/>
  </c:if>
  <c:if test="${not empty tramitDCteAutFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitDCteAutFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitDCteAut.submit();  
  }
</script>

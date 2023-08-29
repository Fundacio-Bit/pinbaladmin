<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitBDadesSoliFilterForm.contexte}"/>
  <c:set var="formName" value="tramitBDadesSoli" />
  <c:set var="__theFilterForm" value="${tramitBDadesSoliFilterForm}" />
  <c:if test="${empty tramitBDadesSoliFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitBDadesSoli.tramitBDadesSoli"/>
  </c:if>
  <c:if test="${not empty tramitBDadesSoliFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitBDadesSoliFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitBDadesSoliFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitBDadesSoli.tramitBDadesSoli"/>
  </c:if>
  <c:if test="${not empty tramitBDadesSoliFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitBDadesSoliFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitBDadesSoli.submit();  
  }
</script>

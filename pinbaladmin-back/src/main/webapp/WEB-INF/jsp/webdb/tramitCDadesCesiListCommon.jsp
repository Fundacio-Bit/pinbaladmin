<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitCDadesCesiFilterForm.contexte}"/>
  <c:set var="formName" value="tramitCDadesCesi" />
  <c:set var="__theFilterForm" value="${tramitCDadesCesiFilterForm}" />
  <c:if test="${empty tramitCDadesCesiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitCDadesCesi.tramitCDadesCesi"/>
  </c:if>
  <c:if test="${not empty tramitCDadesCesiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitCDadesCesiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitCDadesCesiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitCDadesCesi.tramitCDadesCesi"/>
  </c:if>
  <c:if test="${not empty tramitCDadesCesiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitCDadesCesiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitCDadesCesi.submit();  
  }
</script>

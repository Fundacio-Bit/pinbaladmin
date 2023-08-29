<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitGDadesTitFilterForm.contexte}"/>
  <c:set var="formName" value="tramitGDadesTit" />
  <c:set var="__theFilterForm" value="${tramitGDadesTitFilterForm}" />
  <c:if test="${empty tramitGDadesTitFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitGDadesTit.tramitGDadesTit"/>
  </c:if>
  <c:if test="${not empty tramitGDadesTitFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitGDadesTitFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitGDadesTitFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitGDadesTit.tramitGDadesTit"/>
  </c:if>
  <c:if test="${not empty tramitGDadesTitFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitGDadesTitFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitGDadesTit.submit();  
  }
</script>

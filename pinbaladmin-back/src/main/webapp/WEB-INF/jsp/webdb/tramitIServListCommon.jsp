<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitIServFilterForm.contexte}"/>
  <c:set var="formName" value="tramitIServ" />
  <c:set var="__theFilterForm" value="${tramitIServFilterForm}" />
  <c:if test="${empty tramitIServFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitIServ.tramitIServ"/>
  </c:if>
  <c:if test="${not empty tramitIServFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitIServFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitIServFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitIServ.tramitIServ"/>
  </c:if>
  <c:if test="${not empty tramitIServFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitIServFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitIServ.submit();  
  }
</script>

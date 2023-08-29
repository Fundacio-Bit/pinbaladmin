<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitAPersAutFilterForm.contexte}"/>
  <c:set var="formName" value="tramitAPersAut" />
  <c:set var="__theFilterForm" value="${tramitAPersAutFilterForm}" />
  <c:if test="${empty tramitAPersAutFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitAPersAut.tramitAPersAut"/>
  </c:if>
  <c:if test="${not empty tramitAPersAutFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitAPersAutFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitAPersAutFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitAPersAut.tramitAPersAut"/>
  </c:if>
  <c:if test="${not empty tramitAPersAutFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitAPersAutFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitAPersAut.submit();  
  }
</script>

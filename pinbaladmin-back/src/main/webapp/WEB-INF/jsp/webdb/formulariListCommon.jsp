<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${formulariFilterForm.contexte}"/>
  <c:set var="formName" value="formulari" />
  <c:set var="__theFilterForm" value="${formulariFilterForm}" />
  <c:if test="${empty formulariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="formulari.formulari"/>
  </c:if>
  <c:if test="${not empty formulariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${formulariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty formulariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="formulari.formulari"/>
  </c:if>
  <c:if test="${not empty formulariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${formulariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.formulari.submit();  
  }
</script>

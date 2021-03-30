<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tiquetFilterForm.contexte}"/>
  <c:set var="formName" value="tiquet" />
  <c:set var="__theFilterForm" value="${tiquetFilterForm}" />
  <c:if test="${empty tiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tiquet.tiquet"/>
  </c:if>
  <c:if test="${not empty tiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tiquetFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tiquet.tiquet"/>
  </c:if>
  <c:if test="${not empty tiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tiquetFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tiquet.submit();  
  }
</script>

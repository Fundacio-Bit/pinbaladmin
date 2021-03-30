<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estatTiquetFilterForm.contexte}"/>
  <c:set var="formName" value="estatTiquet" />
  <c:set var="__theFilterForm" value="${estatTiquetFilterForm}" />
  <c:if test="${empty estatTiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatTiquet.estatTiquet"/>
  </c:if>
  <c:if test="${not empty estatTiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatTiquetFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatTiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatTiquet.estatTiquet"/>
  </c:if>
  <c:if test="${not empty estatTiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatTiquetFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estatTiquet.submit();  
  }
</script>

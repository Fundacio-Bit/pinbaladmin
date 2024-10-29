<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pinfoFilterForm.contexte}"/>
  <c:set var="formName" value="pinfo" />
  <c:set var="__theFilterForm" value="${pinfoFilterForm}" />
  <c:if test="${empty pinfoFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pinfo.pinfo"/>
  </c:if>
  <c:if test="${not empty pinfoFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pinfoFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pinfoFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pinfo.pinfo"/>
  </c:if>
  <c:if test="${not empty pinfoFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pinfoFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pinfo.submit();  
  }
</script>

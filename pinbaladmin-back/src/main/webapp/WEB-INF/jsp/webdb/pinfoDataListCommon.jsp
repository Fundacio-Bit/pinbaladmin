<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pinfoDataFilterForm.contexte}"/>
  <c:set var="formName" value="pinfoData" />
  <c:set var="__theFilterForm" value="${pinfoDataFilterForm}" />
  <c:if test="${empty pinfoDataFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pinfoData.pinfoData"/>
  </c:if>
  <c:if test="${not empty pinfoDataFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pinfoDataFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pinfoDataFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pinfoData.pinfoData"/>
  </c:if>
  <c:if test="${not empty pinfoDataFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pinfoDataFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pinfoData.submit();  
  }
</script>

<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${solicitudServeiFilterForm.contexte}"/>
  <c:set var="formName" value="solicitudServei" />
  <c:set var="__theFilterForm" value="${solicitudServeiFilterForm}" />
  <c:if test="${empty solicitudServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="solicitudServei.solicitudServei"/>
  </c:if>
  <c:if test="${not empty solicitudServeiFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${solicitudServeiFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty solicitudServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="solicitudServei.solicitudServei"/>
  </c:if>
  <c:if test="${not empty solicitudServeiFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${solicitudServeiFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.solicitudServei.submit();  
  }
</script>

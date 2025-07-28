<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${infoMadridFilterForm.contexte}"/>
  <c:set var="formName" value="infoMadrid" />
  <c:set var="__theFilterForm" value="${infoMadridFilterForm}" />
  <c:if test="${empty infoMadridFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="infoMadrid.infoMadrid"/>
  </c:if>
  <c:if test="${not empty infoMadridFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${infoMadridFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty infoMadridFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="infoMadrid.infoMadrid"/>
  </c:if>
  <c:if test="${not empty infoMadridFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${infoMadridFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.infoMadrid.submit();  
  }
</script>

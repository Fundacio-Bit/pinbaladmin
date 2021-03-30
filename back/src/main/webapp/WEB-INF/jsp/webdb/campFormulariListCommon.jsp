<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${campFormulariFilterForm.contexte}"/>
  <c:set var="formName" value="campFormulari" />
  <c:set var="__theFilterForm" value="${campFormulariFilterForm}" />
  <c:if test="${empty campFormulariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="campFormulari.campFormulari"/>
  </c:if>
  <c:if test="${not empty campFormulariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${campFormulariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty campFormulariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="campFormulari.campFormulari"/>
  </c:if>
  <c:if test="${not empty campFormulariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${campFormulariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.campFormulari.submit();  
  }
</script>

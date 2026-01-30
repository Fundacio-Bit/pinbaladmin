<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${contacteFilterForm.contexte}"/>
  <c:set var="formName" value="contacte" />
  <c:set var="__theFilterForm" value="${contacteFilterForm}" />
  <c:if test="${empty contacteFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="contacte.contacte"/>
  </c:if>
  <c:if test="${not empty contacteFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${contacteFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty contacteFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="contacte.contacte"/>
  </c:if>
  <c:if test="${not empty contacteFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${contacteFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.contacte.submit();  
  }
</script>

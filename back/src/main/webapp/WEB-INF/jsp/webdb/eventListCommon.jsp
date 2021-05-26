<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${eventFilterForm.contexte}"/>
  <c:set var="formName" value="event" />
  <c:set var="__theFilterForm" value="${eventFilterForm}" />
  <c:if test="${empty eventFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="event.event"/>
  </c:if>
  <c:if test="${not empty eventFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${eventFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty eventFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="event.event"/>
  </c:if>
  <c:if test="${not empty eventFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${eventFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.event.submit();  
  }
</script>

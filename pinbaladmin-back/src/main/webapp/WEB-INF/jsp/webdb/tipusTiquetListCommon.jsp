<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusTiquetFilterForm.contexte}"/>
  <c:set var="formName" value="tipusTiquet" />
  <c:set var="__theFilterForm" value="${tipusTiquetFilterForm}" />
  <c:if test="${empty tipusTiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusTiquet.tipusTiquet"/>
  </c:if>
  <c:if test="${not empty tipusTiquetFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusTiquetFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusTiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusTiquet.tipusTiquet"/>
  </c:if>
  <c:if test="${not empty tipusTiquetFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusTiquetFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusTiquet.submit();  
  }
</script>

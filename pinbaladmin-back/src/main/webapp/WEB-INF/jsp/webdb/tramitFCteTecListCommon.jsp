<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tramitFCteTecFilterForm.contexte}"/>
  <c:set var="formName" value="tramitFCteTec" />
  <c:set var="__theFilterForm" value="${tramitFCteTecFilterForm}" />
  <c:if test="${empty tramitFCteTecFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tramitFCteTec.tramitFCteTec"/>
  </c:if>
  <c:if test="${not empty tramitFCteTecFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tramitFCteTecFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tramitFCteTecFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tramitFCteTec.tramitFCteTec"/>
  </c:if>
  <c:if test="${not empty tramitFCteTecFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tramitFCteTecFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tramitFCteTec.submit();  
  }
</script>

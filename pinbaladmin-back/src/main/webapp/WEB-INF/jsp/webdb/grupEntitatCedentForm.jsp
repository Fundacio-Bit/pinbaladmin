
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="grupEntitatCedentFormTitle.jsp" %>


<form:form modelAttribute="grupEntitatCedentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${grupEntitatCedentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupEntitatCedentFormCorePre.jsp" %>

  <%@include file="grupEntitatCedentFormCore.jsp" %>

  <%@include file="grupEntitatCedentFormCorePost.jsp" %>

  <%@include file="grupEntitatCedentFormButtons.jsp" %>

  <c:if test="${not empty grupEntitatCedentForm.sections}">
     <c:set var="__basename" value="grupEntitatCedent" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${grupEntitatCedentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupEntitatCedentFormModificable.jsp" %>
  </c:if>

</form:form>



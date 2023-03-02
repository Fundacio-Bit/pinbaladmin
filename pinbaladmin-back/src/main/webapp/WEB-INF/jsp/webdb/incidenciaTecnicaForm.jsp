
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="incidenciaTecnicaFormTitle.jsp" %>


<form:form modelAttribute="incidenciaTecnicaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${incidenciaTecnicaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="incidenciaTecnicaFormCorePre.jsp" %>

  <%@include file="incidenciaTecnicaFormCore.jsp" %>

  <%@include file="incidenciaTecnicaFormCorePost.jsp" %>

  <%@include file="incidenciaTecnicaFormButtons.jsp" %>

  <c:if test="${not empty incidenciaTecnicaForm.sections}">
     <c:set var="__basename" value="incidenciaTecnica" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${incidenciaTecnicaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/incidenciaTecnicaFormModificable.jsp" %>
  </c:if>

</form:form>



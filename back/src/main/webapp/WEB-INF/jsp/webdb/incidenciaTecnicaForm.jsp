
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="incidenciaTecnicaFormTitle.jsp" %>


<form:form modelAttribute="incidenciaTecnicaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${incidenciaTecnicaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="incidenciaTecnicaFormCorePre.jsp" %>
  <%@include file="incidenciaTecnicaFormCore.jsp" %>

  <%@include file="incidenciaTecnicaFormCorePost.jsp" %>

  <%@include file="incidenciaTecnicaFormButtons.jsp" %>

  <c:if test="${incidenciaTecnicaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/incidenciaTecnicaFormModificable.jsp" %>
  </c:if>

</form:form>




<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="modificacioSolicitudForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="modificacioSolicitudFormTitle.jsp" %>
 
  <c:set var="contexte" value="${modificacioSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="modificacioSolicitudFormCorePre.jsp" %>

  <%@include file="modificacioSolicitudFormCore.jsp" %>

  <%@include file="modificacioSolicitudFormCorePost.jsp" %>

  <%@include file="modificacioSolicitudFormButtons.jsp" %>

  <c:if test="${not empty modificacioSolicitudForm.sections}">
     <c:set var="__basename" value="modificacioSolicitud" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${modificacioSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/modificacioSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



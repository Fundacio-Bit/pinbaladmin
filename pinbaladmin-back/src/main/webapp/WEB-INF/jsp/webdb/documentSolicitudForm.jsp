
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="documentSolicitudForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="documentSolicitudFormTitle.jsp" %>
 
  <c:set var="contexte" value="${documentSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentSolicitudFormCorePre.jsp" %>

  <%@include file="documentSolicitudFormCore.jsp" %>

  <%@include file="documentSolicitudFormCorePost.jsp" %>

  <%@include file="documentSolicitudFormButtons.jsp" %>

  <c:if test="${not empty documentSolicitudForm.sections}">
     <c:set var="__basename" value="documentSolicitud" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${documentSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



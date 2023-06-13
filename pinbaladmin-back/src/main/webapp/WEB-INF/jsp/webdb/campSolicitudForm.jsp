
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="campSolicitudForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="campSolicitudFormTitle.jsp" %>
 
  <c:set var="contexte" value="${campSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="campSolicitudFormCorePre.jsp" %>

  <%@include file="campSolicitudFormCore.jsp" %>

  <%@include file="campSolicitudFormCorePost.jsp" %>

  <%@include file="campSolicitudFormButtons.jsp" %>

  <c:if test="${not empty campSolicitudForm.sections}">
     <c:set var="__basename" value="campSolicitud" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${campSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/campSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



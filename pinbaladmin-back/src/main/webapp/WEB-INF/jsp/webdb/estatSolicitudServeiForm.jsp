
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="estatSolicitudServeiForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="estatSolicitudServeiFormTitle.jsp" %>
 
  <c:set var="contexte" value="${estatSolicitudServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatSolicitudServeiFormCorePre.jsp" %>

  <%@include file="estatSolicitudServeiFormCore.jsp" %>

  <%@include file="estatSolicitudServeiFormCorePost.jsp" %>

  <%@include file="estatSolicitudServeiFormButtons.jsp" %>

  <c:if test="${not empty estatSolicitudServeiForm.sections}">
     <c:set var="__basename" value="estatSolicitudServei" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${estatSolicitudServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatSolicitudServeiFormModificable.jsp" %>
  </c:if>

</form:form>



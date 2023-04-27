
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="solicitudForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="solicitudFormTitle.jsp" %>
 
  <c:set var="contexte" value="${solicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="solicitudFormCorePre.jsp" %>

  <%@include file="solicitudFormCore.jsp" %>

  <%@include file="solicitudFormCorePost.jsp" %>

  <%@include file="solicitudFormButtons.jsp" %>

  <c:if test="${not empty solicitudForm.sections}">
     <c:set var="__basename" value="solicitud" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${solicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/solicitudFormModificable.jsp" %>
  </c:if>

</form:form>



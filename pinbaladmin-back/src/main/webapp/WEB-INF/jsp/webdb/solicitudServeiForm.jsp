
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="solicitudServeiForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="solicitudServeiFormTitle.jsp" %>
 
  <c:set var="contexte" value="${solicitudServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="solicitudServeiFormCorePre.jsp" %>

  <%@include file="solicitudServeiFormCore.jsp" %>

  <%@include file="solicitudServeiFormCorePost.jsp" %>

  <%@include file="solicitudServeiFormButtons.jsp" %>

  <c:if test="${not empty solicitudServeiForm.sections}">
     <c:set var="__basename" value="solicitudServei" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${solicitudServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/solicitudServeiFormModificable.jsp" %>
  </c:if>

</form:form>



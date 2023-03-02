
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatServeiFormTitle.jsp" %>


<form:form modelAttribute="estatServeiForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatServeiFormCorePre.jsp" %>

  <%@include file="estatServeiFormCore.jsp" %>

  <%@include file="estatServeiFormCorePost.jsp" %>

  <%@include file="estatServeiFormButtons.jsp" %>

  <c:if test="${not empty estatServeiForm.sections}">
     <c:set var="__basename" value="estatServei" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${estatServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatServeiFormModificable.jsp" %>
  </c:if>

</form:form>



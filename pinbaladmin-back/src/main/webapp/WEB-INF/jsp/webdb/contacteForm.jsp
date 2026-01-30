
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="contacteForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="contacteFormTitle.jsp" %>
 
  <c:set var="contexte" value="${contacteForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="contacteFormCorePre.jsp" %>

  <%@include file="contacteFormCore.jsp" %>

  <%@include file="contacteFormCorePost.jsp" %>

  <%@include file="contacteFormButtons.jsp" %>

  <c:if test="${not empty contacteForm.sections}">
     <c:set var="__basename" value="contacte" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${contacteForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/contacteFormModificable.jsp" %>
  </c:if>

</form:form>



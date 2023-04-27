
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="emailForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="emailFormTitle.jsp" %>
 
  <c:set var="contexte" value="${emailForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="emailFormCorePre.jsp" %>

  <%@include file="emailFormCore.jsp" %>

  <%@include file="emailFormCorePost.jsp" %>

  <%@include file="emailFormButtons.jsp" %>

  <c:if test="${not empty emailForm.sections}">
     <c:set var="__basename" value="email" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${emailForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/emailFormModificable.jsp" %>
  </c:if>

</form:form>



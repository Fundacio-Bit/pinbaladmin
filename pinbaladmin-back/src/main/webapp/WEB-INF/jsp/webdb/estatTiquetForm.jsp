
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatTiquetFormTitle.jsp" %>


<form:form modelAttribute="estatTiquetForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatTiquetForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatTiquetFormCorePre.jsp" %>

  <%@include file="estatTiquetFormCore.jsp" %>

  <%@include file="estatTiquetFormCorePost.jsp" %>

  <%@include file="estatTiquetFormButtons.jsp" %>

  <c:if test="${not empty estatTiquetForm.sections}">
     <c:set var="__basename" value="estatTiquet" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${estatTiquetForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatTiquetFormModificable.jsp" %>
  </c:if>

</form:form>



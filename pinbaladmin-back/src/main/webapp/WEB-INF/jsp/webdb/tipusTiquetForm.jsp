
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusTiquetFormTitle.jsp" %>


<form:form modelAttribute="tipusTiquetForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusTiquetForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusTiquetFormCorePre.jsp" %>

  <%@include file="tipusTiquetFormCore.jsp" %>

  <%@include file="tipusTiquetFormCorePost.jsp" %>

  <%@include file="tipusTiquetFormButtons.jsp" %>

  <c:if test="${not empty tipusTiquetForm.sections}">
     <c:set var="__basename" value="tipusTiquet" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tipusTiquetForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusTiquetFormModificable.jsp" %>
  </c:if>

</form:form>



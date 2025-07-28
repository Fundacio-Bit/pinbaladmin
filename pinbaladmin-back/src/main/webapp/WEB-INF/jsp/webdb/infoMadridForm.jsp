
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="infoMadridForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="infoMadridFormTitle.jsp" %>
 
  <c:set var="contexte" value="${infoMadridForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="infoMadridFormCorePre.jsp" %>

  <%@include file="infoMadridFormCore.jsp" %>

  <%@include file="infoMadridFormCorePost.jsp" %>

  <%@include file="infoMadridFormButtons.jsp" %>

  <c:if test="${not empty infoMadridForm.sections}">
     <c:set var="__basename" value="infoMadrid" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${infoMadridForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/infoMadridFormModificable.jsp" %>
  </c:if>

</form:form>



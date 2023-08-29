
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitAPersAutForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitAPersAutFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitAPersAutForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitAPersAutFormCorePre.jsp" %>

  <%@include file="tramitAPersAutFormCore.jsp" %>

  <%@include file="tramitAPersAutFormCorePost.jsp" %>

  <%@include file="tramitAPersAutFormButtons.jsp" %>

  <c:if test="${not empty tramitAPersAutForm.sections}">
     <c:set var="__basename" value="tramitAPersAut" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitAPersAutForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitAPersAutFormModificable.jsp" %>
  </c:if>

</form:form>



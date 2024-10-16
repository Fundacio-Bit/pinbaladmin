
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pINFOForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pINFOFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pINFOForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pINFOFormCorePre.jsp" %>

  <%@include file="pINFOFormCore.jsp" %>

  <%@include file="pINFOFormCorePost.jsp" %>

  <%@include file="pINFOFormButtons.jsp" %>

  <c:if test="${not empty pINFOForm.sections}">
     <c:set var="__basename" value="pINFO" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pINFOForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pINFOFormModificable.jsp" %>
  </c:if>

</form:form>



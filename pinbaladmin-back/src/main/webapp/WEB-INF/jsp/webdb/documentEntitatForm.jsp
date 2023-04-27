
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="documentEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="documentEntitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${documentEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentEntitatFormCorePre.jsp" %>

  <%@include file="documentEntitatFormCore.jsp" %>

  <%@include file="documentEntitatFormCorePost.jsp" %>

  <%@include file="documentEntitatFormButtons.jsp" %>

  <c:if test="${not empty documentEntitatForm.sections}">
     <c:set var="__basename" value="documentEntitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${documentEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentEntitatFormModificable.jsp" %>
  </c:if>

</form:form>



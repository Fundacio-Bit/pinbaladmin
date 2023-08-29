
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitFCteTecForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitFCteTecFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitFCteTecForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitFCteTecFormCorePre.jsp" %>

  <%@include file="tramitFCteTecFormCore.jsp" %>

  <%@include file="tramitFCteTecFormCorePost.jsp" %>

  <%@include file="tramitFCteTecFormButtons.jsp" %>

  <c:if test="${not empty tramitFCteTecForm.sections}">
     <c:set var="__basename" value="tramitFCteTec" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitFCteTecForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitFCteTecFormModificable.jsp" %>
  </c:if>

</form:form>




<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitECteAudForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitECteAudFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitECteAudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitECteAudFormCorePre.jsp" %>

  <%@include file="tramitECteAudFormCore.jsp" %>

  <%@include file="tramitECteAudFormCorePost.jsp" %>

  <%@include file="tramitECteAudFormButtons.jsp" %>

  <c:if test="${not empty tramitECteAudForm.sections}">
     <c:set var="__basename" value="tramitECteAud" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitECteAudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitECteAudFormModificable.jsp" %>
  </c:if>

</form:form>



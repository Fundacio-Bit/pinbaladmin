
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitJConsentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitJConsentFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitJConsentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitJConsentFormCorePre.jsp" %>

  <%@include file="tramitJConsentFormCore.jsp" %>

  <%@include file="tramitJConsentFormCorePost.jsp" %>

  <%@include file="tramitJConsentFormButtons.jsp" %>

  <c:if test="${not empty tramitJConsentForm.sections}">
     <c:set var="__basename" value="tramitJConsent" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitJConsentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitJConsentFormModificable.jsp" %>
  </c:if>

</form:form>



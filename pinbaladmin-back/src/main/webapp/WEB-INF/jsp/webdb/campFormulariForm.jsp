
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="campFormulariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="campFormulariFormTitle.jsp" %>
 
  <c:set var="contexte" value="${campFormulariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="campFormulariFormCorePre.jsp" %>

  <%@include file="campFormulariFormCore.jsp" %>

  <%@include file="campFormulariFormCorePost.jsp" %>

  <%@include file="campFormulariFormButtons.jsp" %>

  <c:if test="${not empty campFormulariForm.sections}">
     <c:set var="__basename" value="campFormulari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${campFormulariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/campFormulariFormModificable.jsp" %>
  </c:if>

</form:form>



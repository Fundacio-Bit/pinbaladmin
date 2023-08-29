
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitDCteAutForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitDCteAutFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitDCteAutForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitDCteAutFormCorePre.jsp" %>

  <%@include file="tramitDCteAutFormCore.jsp" %>

  <%@include file="tramitDCteAutFormCorePost.jsp" %>

  <%@include file="tramitDCteAutFormButtons.jsp" %>

  <c:if test="${not empty tramitDCteAutForm.sections}">
     <c:set var="__basename" value="tramitDCteAut" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitDCteAutForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitDCteAutFormModificable.jsp" %>
  </c:if>

</form:form>



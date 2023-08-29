
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitHProcForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitHProcFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitHProcForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitHProcFormCorePre.jsp" %>

  <%@include file="tramitHProcFormCore.jsp" %>

  <%@include file="tramitHProcFormCorePost.jsp" %>

  <%@include file="tramitHProcFormButtons.jsp" %>

  <c:if test="${not empty tramitHProcForm.sections}">
     <c:set var="__basename" value="tramitHProc" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitHProcForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitHProcFormModificable.jsp" %>
  </c:if>

</form:form>



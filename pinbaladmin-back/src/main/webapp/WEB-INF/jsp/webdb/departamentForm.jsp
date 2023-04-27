
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="departamentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="departamentFormTitle.jsp" %>
 
  <c:set var="contexte" value="${departamentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="departamentFormCorePre.jsp" %>

  <%@include file="departamentFormCore.jsp" %>

  <%@include file="departamentFormCorePost.jsp" %>

  <%@include file="departamentFormButtons.jsp" %>

  <c:if test="${not empty departamentForm.sections}">
     <c:set var="__basename" value="departament" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${departamentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/departamentFormModificable.jsp" %>
  </c:if>

</form:form>



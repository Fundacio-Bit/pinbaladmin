
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="documentCedentFormTitle.jsp" %>


<form:form modelAttribute="documentCedentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${documentCedentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentCedentFormCorePre.jsp" %>

  <%@include file="documentCedentFormCore.jsp" %>

  <%@include file="documentCedentFormCorePost.jsp" %>

  <%@include file="documentCedentFormButtons.jsp" %>

  <c:if test="${not empty documentCedentForm.sections}">
     <c:set var="__basename" value="documentCedent" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${documentCedentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentCedentFormModificable.jsp" %>
  </c:if>

</form:form>



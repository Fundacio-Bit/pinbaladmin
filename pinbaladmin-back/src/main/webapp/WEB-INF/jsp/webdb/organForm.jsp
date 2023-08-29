
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="organForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="organFormTitle.jsp" %>
 
  <c:set var="contexte" value="${organForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="organFormCorePre.jsp" %>

  <%@include file="organFormCore.jsp" %>

  <%@include file="organFormCorePost.jsp" %>

  <%@include file="organFormButtons.jsp" %>

  <c:if test="${not empty organForm.sections}">
     <c:set var="__basename" value="organ" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${organForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/organFormModificable.jsp" %>
  </c:if>

</form:form>



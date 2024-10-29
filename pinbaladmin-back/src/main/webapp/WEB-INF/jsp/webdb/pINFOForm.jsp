
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pinfoForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pinfoFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pinfoForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pinfoFormCorePre.jsp" %>

  <%@include file="pinfoFormCore.jsp" %>

  <%@include file="pinfoFormCorePost.jsp" %>

  <%@include file="pinfoFormButtons.jsp" %>

  <c:if test="${not empty pinfoForm.sections}">
     <c:set var="__basename" value="pinfo" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pinfoForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pinfoFormModificable.jsp" %>
  </c:if>

</form:form>



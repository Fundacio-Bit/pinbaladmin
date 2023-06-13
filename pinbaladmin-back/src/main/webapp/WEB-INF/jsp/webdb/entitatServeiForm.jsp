
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="entitatServeiForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="entitatServeiFormTitle.jsp" %>
 
  <c:set var="contexte" value="${entitatServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="entitatServeiFormCorePre.jsp" %>

  <%@include file="entitatServeiFormCore.jsp" %>

  <%@include file="entitatServeiFormCorePost.jsp" %>

  <%@include file="entitatServeiFormButtons.jsp" %>

  <c:if test="${not empty entitatServeiForm.sections}">
     <c:set var="__basename" value="entitatServei" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${entitatServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/entitatServeiFormModificable.jsp" %>
  </c:if>

</form:form>




<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pinfoDataForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pinfoDataFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pinfoDataForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pinfoDataFormCorePre.jsp" %>

  <%@include file="pinfoDataFormCore.jsp" %>

  <%@include file="pinfoDataFormCorePost.jsp" %>

  <%@include file="pinfoDataFormButtons.jsp" %>

  <c:if test="${not empty pinfoDataForm.sections}">
     <c:set var="__basename" value="pinfoData" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pinfoDataForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pinfoDataFormModificable.jsp" %>
  </c:if>

</form:form>



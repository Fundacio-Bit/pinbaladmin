
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="modificacioSoliServForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="modificacioSoliServFormTitle.jsp" %>
 
  <c:set var="contexte" value="${modificacioSoliServForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="modificacioSoliServFormCorePre.jsp" %>

  <%@include file="modificacioSoliServFormCore.jsp" %>

  <%@include file="modificacioSoliServFormCorePost.jsp" %>

  <%@include file="modificacioSoliServFormButtons.jsp" %>

  <c:if test="${not empty modificacioSoliServForm.sections}">
     <c:set var="__basename" value="modificacioSoliServ" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${modificacioSoliServForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/modificacioSoliServFormModificable.jsp" %>
  </c:if>

</form:form>



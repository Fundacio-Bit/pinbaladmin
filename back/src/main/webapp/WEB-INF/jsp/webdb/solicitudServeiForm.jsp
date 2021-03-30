
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="solicitudServeiFormTitle.jsp" %>


<form:form modelAttribute="solicitudServeiForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${solicitudServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="solicitudServeiFormCorePre.jsp" %>
  <%@include file="solicitudServeiFormCore.jsp" %>

  <%@include file="solicitudServeiFormCorePost.jsp" %>

  <%@include file="solicitudServeiFormButtons.jsp" %>

  <c:if test="${solicitudServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/solicitudServeiFormModificable.jsp" %>
  </c:if>

</form:form>



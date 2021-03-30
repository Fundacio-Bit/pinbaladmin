
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatSolicitudServeiFormTitle.jsp" %>


<form:form modelAttribute="estatSolicitudServeiForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatSolicitudServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatSolicitudServeiFormCorePre.jsp" %>
  <%@include file="estatSolicitudServeiFormCore.jsp" %>

  <%@include file="estatSolicitudServeiFormCorePost.jsp" %>

  <%@include file="estatSolicitudServeiFormButtons.jsp" %>

  <c:if test="${estatSolicitudServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatSolicitudServeiFormModificable.jsp" %>
  </c:if>

</form:form>



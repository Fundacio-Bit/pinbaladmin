
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatSolicitudFormTitle.jsp" %>


<form:form modelAttribute="estatSolicitudForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatSolicitudFormCorePre.jsp" %>
  <%@include file="estatSolicitudFormCore.jsp" %>

  <%@include file="estatSolicitudFormCorePost.jsp" %>

  <%@include file="estatSolicitudFormButtons.jsp" %>

  <c:if test="${estatSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



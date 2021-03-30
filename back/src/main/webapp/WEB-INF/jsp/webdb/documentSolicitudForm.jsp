
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="documentSolicitudFormTitle.jsp" %>


<form:form modelAttribute="documentSolicitudForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${documentSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentSolicitudFormCorePre.jsp" %>
  <%@include file="documentSolicitudFormCore.jsp" %>

  <%@include file="documentSolicitudFormCorePost.jsp" %>

  <%@include file="documentSolicitudFormButtons.jsp" %>

  <c:if test="${documentSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



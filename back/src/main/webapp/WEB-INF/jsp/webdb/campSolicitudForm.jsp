
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="campSolicitudFormTitle.jsp" %>


<form:form modelAttribute="campSolicitudForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${campSolicitudForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="campSolicitudFormCorePre.jsp" %>
  <%@include file="campSolicitudFormCore.jsp" %>

  <%@include file="campSolicitudFormCorePost.jsp" %>

  <%@include file="campSolicitudFormButtons.jsp" %>

  <c:if test="${campSolicitudForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/campSolicitudFormModificable.jsp" %>
  </c:if>

</form:form>



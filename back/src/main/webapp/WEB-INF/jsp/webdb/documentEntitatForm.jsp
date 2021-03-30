
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="documentEntitatFormTitle.jsp" %>


<form:form modelAttribute="documentEntitatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${documentEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentEntitatFormCorePre.jsp" %>
  <%@include file="documentEntitatFormCore.jsp" %>

  <%@include file="documentEntitatFormCorePost.jsp" %>

  <%@include file="documentEntitatFormButtons.jsp" %>

  <c:if test="${documentEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentEntitatFormModificable.jsp" %>
  </c:if>

</form:form>




<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="documentFormTitle.jsp" %>


<form:form modelAttribute="documentForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${documentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentFormCorePre.jsp" %>
  <%@include file="documentFormCore.jsp" %>

  <%@include file="documentFormCorePost.jsp" %>

  <%@include file="documentFormButtons.jsp" %>

  <c:if test="${documentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentFormModificable.jsp" %>
  </c:if>

</form:form>



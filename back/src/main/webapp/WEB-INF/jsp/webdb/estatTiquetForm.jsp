
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatTiquetFormTitle.jsp" %>


<form:form modelAttribute="estatTiquetForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatTiquetForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatTiquetFormCorePre.jsp" %>
  <%@include file="estatTiquetFormCore.jsp" %>

  <%@include file="estatTiquetFormCorePost.jsp" %>

  <%@include file="estatTiquetFormButtons.jsp" %>

  <c:if test="${estatTiquetForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatTiquetFormModificable.jsp" %>
  </c:if>

</form:form>



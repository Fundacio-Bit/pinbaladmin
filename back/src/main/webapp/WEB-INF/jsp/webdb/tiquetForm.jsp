
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tiquetFormTitle.jsp" %>


<form:form modelAttribute="tiquetForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tiquetForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tiquetFormCorePre.jsp" %>
  <%@include file="tiquetFormCore.jsp" %>

  <%@include file="tiquetFormCorePost.jsp" %>

  <%@include file="tiquetFormButtons.jsp" %>

  <c:if test="${tiquetForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tiquetFormModificable.jsp" %>
  </c:if>

</form:form>



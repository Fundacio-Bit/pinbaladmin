
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="documentCedentFormTitle.jsp" %>


<form:form modelAttribute="documentCedentForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${documentCedentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="documentCedentFormCorePre.jsp" %>
  <%@include file="documentCedentFormCore.jsp" %>

  <%@include file="documentCedentFormCorePost.jsp" %>

  <%@include file="documentCedentFormButtons.jsp" %>

  <c:if test="${documentCedentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/documentCedentFormModificable.jsp" %>
  </c:if>

</form:form>



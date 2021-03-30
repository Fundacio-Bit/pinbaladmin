
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="departamentFormTitle.jsp" %>


<form:form modelAttribute="departamentForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${departamentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="departamentFormCorePre.jsp" %>
  <%@include file="departamentFormCore.jsp" %>

  <%@include file="departamentFormCorePost.jsp" %>

  <%@include file="departamentFormButtons.jsp" %>

  <c:if test="${departamentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/departamentFormModificable.jsp" %>
  </c:if>

</form:form>



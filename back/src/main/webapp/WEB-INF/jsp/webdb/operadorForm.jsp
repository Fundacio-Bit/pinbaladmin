
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="operadorFormTitle.jsp" %>


<form:form modelAttribute="operadorForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${operadorForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="operadorFormCorePre.jsp" %>
  <%@include file="operadorFormCore.jsp" %>

  <%@include file="operadorFormCorePost.jsp" %>

  <%@include file="operadorFormButtons.jsp" %>

  <c:if test="${operadorForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/operadorFormModificable.jsp" %>
  </c:if>

</form:form>



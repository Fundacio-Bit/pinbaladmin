
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="campFormulariFormTitle.jsp" %>


<form:form modelAttribute="campFormulariForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${campFormulariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="campFormulariFormCorePre.jsp" %>
  <%@include file="campFormulariFormCore.jsp" %>

  <%@include file="campFormulariFormCorePost.jsp" %>

  <%@include file="campFormulariFormButtons.jsp" %>

  <c:if test="${campFormulariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/campFormulariFormModificable.jsp" %>
  </c:if>

</form:form>



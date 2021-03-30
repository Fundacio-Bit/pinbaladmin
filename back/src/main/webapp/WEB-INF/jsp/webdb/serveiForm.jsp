
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="serveiFormTitle.jsp" %>


<form:form modelAttribute="serveiForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${serveiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="serveiFormCorePre.jsp" %>
  <%@include file="serveiFormCore.jsp" %>

  <%@include file="serveiFormCorePost.jsp" %>

  <%@include file="serveiFormButtons.jsp" %>

  <c:if test="${serveiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/serveiFormModificable.jsp" %>
  </c:if>

</form:form>



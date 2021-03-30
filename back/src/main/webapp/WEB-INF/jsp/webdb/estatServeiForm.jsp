
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatServeiFormTitle.jsp" %>


<form:form modelAttribute="estatServeiForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatServeiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatServeiFormCorePre.jsp" %>
  <%@include file="estatServeiFormCore.jsp" %>

  <%@include file="estatServeiFormCorePost.jsp" %>

  <%@include file="estatServeiFormButtons.jsp" %>

  <c:if test="${estatServeiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatServeiFormModificable.jsp" %>
  </c:if>

</form:form>



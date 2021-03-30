
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="emailFormTitle.jsp" %>


<form:form modelAttribute="emailForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${emailForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="emailFormCorePre.jsp" %>
  <%@include file="emailFormCore.jsp" %>

  <%@include file="emailFormCorePost.jsp" %>

  <%@include file="emailFormButtons.jsp" %>

  <c:if test="${emailForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/emailFormModificable.jsp" %>
  </c:if>

</form:form>



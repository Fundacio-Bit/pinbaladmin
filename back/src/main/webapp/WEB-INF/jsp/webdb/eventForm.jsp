
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="eventFormTitle.jsp" %>


<form:form modelAttribute="eventForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${eventForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="eventFormCorePre.jsp" %>
  <%@include file="eventFormCore.jsp" %>

  <%@include file="eventFormCorePost.jsp" %>

  <%@include file="eventFormButtons.jsp" %>

  <c:if test="${eventForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/eventFormModificable.jsp" %>
  </c:if>

</form:form>



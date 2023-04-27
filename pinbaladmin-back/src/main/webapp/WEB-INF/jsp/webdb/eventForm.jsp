
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="eventForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="eventFormTitle.jsp" %>
 
  <c:set var="contexte" value="${eventForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="eventFormCorePre.jsp" %>

  <%@include file="eventFormCore.jsp" %>

  <%@include file="eventFormCorePost.jsp" %>

  <%@include file="eventFormButtons.jsp" %>

  <c:if test="${not empty eventForm.sections}">
     <c:set var="__basename" value="event" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${eventForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/eventFormModificable.jsp" %>
  </c:if>

</form:form>



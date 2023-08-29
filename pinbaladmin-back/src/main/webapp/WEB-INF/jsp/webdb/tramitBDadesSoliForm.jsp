
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitBDadesSoliForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitBDadesSoliFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitBDadesSoliForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitBDadesSoliFormCorePre.jsp" %>

  <%@include file="tramitBDadesSoliFormCore.jsp" %>

  <%@include file="tramitBDadesSoliFormCorePost.jsp" %>

  <%@include file="tramitBDadesSoliFormButtons.jsp" %>

  <c:if test="${not empty tramitBDadesSoliForm.sections}">
     <c:set var="__basename" value="tramitBDadesSoli" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitBDadesSoliForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitBDadesSoliFormModificable.jsp" %>
  </c:if>

</form:form>



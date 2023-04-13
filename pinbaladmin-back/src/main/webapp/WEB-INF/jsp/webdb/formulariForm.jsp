
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="formulariFormTitle.jsp" %>


<form:form modelAttribute="formulariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${formulariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="formulariFormCorePre.jsp" %>

  <%@include file="formulariFormCore.jsp" %>

  <%@include file="formulariFormCorePost.jsp" %>

  <%@include file="formulariFormButtons.jsp" %>

  <c:if test="${not empty formulariForm.sections}">
     <c:set var="__basename" value="formulari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${formulariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/formulariFormModificable.jsp" %>
  </c:if>

</form:form>


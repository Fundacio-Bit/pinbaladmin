
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitIServForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitIServFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitIServForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitIServFormCorePre.jsp" %>

  <%@include file="tramitIServFormCore.jsp" %>

  <%@include file="tramitIServFormCorePost.jsp" %>

  <%@include file="tramitIServFormButtons.jsp" %>

  <c:if test="${not empty tramitIServForm.sections}">
     <c:set var="__basename" value="tramitIServ" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitIServForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitIServFormModificable.jsp" %>
  </c:if>

</form:form>



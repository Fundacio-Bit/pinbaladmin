
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitCDadesCesiForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitCDadesCesiFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitCDadesCesiForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitCDadesCesiFormCorePre.jsp" %>

  <%@include file="tramitCDadesCesiFormCore.jsp" %>

  <%@include file="tramitCDadesCesiFormCorePost.jsp" %>

  <%@include file="tramitCDadesCesiFormButtons.jsp" %>

  <c:if test="${not empty tramitCDadesCesiForm.sections}">
     <c:set var="__basename" value="tramitCDadesCesi" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitCDadesCesiForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitCDadesCesiFormModificable.jsp" %>
  </c:if>

</form:form>



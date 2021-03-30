
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="areaFormTitle.jsp" %>


<form:form modelAttribute="areaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${areaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="areaFormCorePre.jsp" %>
  <%@include file="areaFormCore.jsp" %>

  <%@include file="areaFormCorePost.jsp" %>

  <%@include file="areaFormButtons.jsp" %>

  <c:if test="${areaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/areaFormModificable.jsp" %>
  </c:if>

</form:form>



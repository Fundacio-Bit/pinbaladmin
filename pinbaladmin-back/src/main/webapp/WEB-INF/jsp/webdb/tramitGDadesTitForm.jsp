
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tramitGDadesTitForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tramitGDadesTitFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tramitGDadesTitForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tramitGDadesTitFormCorePre.jsp" %>

  <%@include file="tramitGDadesTitFormCore.jsp" %>

  <%@include file="tramitGDadesTitFormCorePost.jsp" %>

  <%@include file="tramitGDadesTitFormButtons.jsp" %>

  <c:if test="${not empty tramitGDadesTitForm.sections}">
     <c:set var="__basename" value="tramitGDadesTit" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tramitGDadesTitForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tramitGDadesTitFormModificable.jsp" %>
  </c:if>

</form:form>



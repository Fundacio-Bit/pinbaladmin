<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="DocumentEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields"/>
  
  <c:set var="__theForm" value="${documentEntitatForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.documentEntitat,hiddenField)}">
        <form:errors path="documentEntitat.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="documentEntitat.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table id="documentEntitat_tableid" class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp table-genapp-form" > 
    <tbody>      


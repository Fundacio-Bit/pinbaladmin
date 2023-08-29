<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitGDadesTitFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields"/>
  
  <c:set var="__theForm" value="${tramitGDadesTitForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.tramitGDadesTit,hiddenField)}">
        <form:errors path="tramitGDadesTit.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="tramitGDadesTit.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table id="tramitGDadesTit_tableid" class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp" > 
    <tbody>      


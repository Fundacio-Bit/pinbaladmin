<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitGDadesTitForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitGDadesTitForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitGDadesTitForm.titleCode}">
    <fmt:message key="${tramitGDadesTitForm.titleCode}" >
      <fmt:param value="${tramitGDadesTitForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitGDadesTitForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitGDadesTit.tramitGDadesTit"/>
    </c:if>
    <c:if test="${not empty tramitGDadesTitForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitGDadesTitForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitGDadesTitForm.nou?'genapp.createtitle':(tramitGDadesTitForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitGDadesTitForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitGDadesTitForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitGDadesTitForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitGDadesTitForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
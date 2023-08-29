<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitCDadesCesiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitCDadesCesiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitCDadesCesiForm.titleCode}">
    <fmt:message key="${tramitCDadesCesiForm.titleCode}" >
      <fmt:param value="${tramitCDadesCesiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitCDadesCesiForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitCDadesCesi.tramitCDadesCesi"/>
    </c:if>
    <c:if test="${not empty tramitCDadesCesiForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitCDadesCesiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitCDadesCesiForm.nou?'genapp.createtitle':(tramitCDadesCesiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitCDadesCesiForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitCDadesCesiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitCDadesCesiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitCDadesCesiForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitJConsentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitJConsentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitJConsentForm.titleCode}">
    <fmt:message key="${tramitJConsentForm.titleCode}" >
      <fmt:param value="${tramitJConsentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitJConsentForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitJConsent.tramitJConsent"/>
    </c:if>
    <c:if test="${not empty tramitJConsentForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitJConsentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitJConsentForm.nou?'genapp.createtitle':(tramitJConsentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitJConsentForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitJConsentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitJConsentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitJConsentForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitECteAudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitECteAudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitECteAudForm.titleCode}">
    <fmt:message key="${tramitECteAudForm.titleCode}" >
      <fmt:param value="${tramitECteAudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitECteAudForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitECteAud.tramitECteAud"/>
    </c:if>
    <c:if test="${not empty tramitECteAudForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitECteAudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitECteAudForm.nou?'genapp.createtitle':(tramitECteAudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitECteAudForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitECteAudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitECteAudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitECteAudForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
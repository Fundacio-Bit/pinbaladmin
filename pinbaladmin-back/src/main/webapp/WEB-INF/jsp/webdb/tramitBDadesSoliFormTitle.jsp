<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitBDadesSoliForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitBDadesSoliForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitBDadesSoliForm.titleCode}">
    <fmt:message key="${tramitBDadesSoliForm.titleCode}" >
      <fmt:param value="${tramitBDadesSoliForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitBDadesSoliForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitBDadesSoli.tramitBDadesSoli"/>
    </c:if>
    <c:if test="${not empty tramitBDadesSoliForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitBDadesSoliForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitBDadesSoliForm.nou?'genapp.createtitle':(tramitBDadesSoliForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitBDadesSoliForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitBDadesSoliForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitBDadesSoliForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitBDadesSoliForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
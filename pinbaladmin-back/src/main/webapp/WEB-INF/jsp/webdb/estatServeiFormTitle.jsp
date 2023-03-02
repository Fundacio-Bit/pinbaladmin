<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(estatServeiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estatServeiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estatServeiForm.titleCode}">
    <fmt:message key="${estatServeiForm.titleCode}" >
      <fmt:param value="${estatServeiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estatServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="estatServei.estatServei"/>
    </c:if>
    <c:if test="${not empty estatServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="${estatServeiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estatServeiForm.nou?'genapp.createtitle':(estatServeiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty estatServeiForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estatServeiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estatServeiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estatServeiForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
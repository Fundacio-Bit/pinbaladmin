<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(estatSolicitudServeiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estatSolicitudServeiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estatSolicitudServeiForm.titleCode}">
    <fmt:message key="${estatSolicitudServeiForm.titleCode}" >
      <fmt:param value="${estatSolicitudServeiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estatSolicitudServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="estatSolicitudServei.estatSolicitudServei"/>
    </c:if>
    <c:if test="${not empty estatSolicitudServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="${estatSolicitudServeiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estatSolicitudServeiForm.nou?'genapp.createtitle':(estatSolicitudServeiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty estatSolicitudServeiForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estatSolicitudServeiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estatSolicitudServeiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estatSolicitudServeiForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
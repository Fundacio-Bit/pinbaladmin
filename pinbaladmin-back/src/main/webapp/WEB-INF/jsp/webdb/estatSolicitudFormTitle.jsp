<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(estatSolicitudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estatSolicitudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estatSolicitudForm.titleCode}">
    <fmt:message key="${estatSolicitudForm.titleCode}" >
      <fmt:param value="${estatSolicitudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estatSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="estatSolicitud.estatSolicitud"/>
    </c:if>
    <c:if test="${not empty estatSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="${estatSolicitudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estatSolicitudForm.nou?'genapp.createtitle':(estatSolicitudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty estatSolicitudForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estatSolicitudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estatSolicitudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estatSolicitudForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
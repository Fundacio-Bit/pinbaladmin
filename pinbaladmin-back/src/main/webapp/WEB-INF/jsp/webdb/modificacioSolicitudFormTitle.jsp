<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(modificacioSolicitudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(modificacioSolicitudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty modificacioSolicitudForm.titleCode}">
    <fmt:message key="${modificacioSolicitudForm.titleCode}" >
      <fmt:param value="${modificacioSolicitudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty modificacioSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="modificacioSolicitud.modificacioSolicitud"/>
    </c:if>
    <c:if test="${not empty modificacioSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="${modificacioSolicitudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${modificacioSolicitudForm.nou?'genapp.createtitle':(modificacioSolicitudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty modificacioSolicitudForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(modificacioSolicitudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(modificacioSolicitudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${modificacioSolicitudForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(campSolicitudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(campSolicitudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty campSolicitudForm.titleCode}">
    <fmt:message key="${campSolicitudForm.titleCode}" >
      <fmt:param value="${campSolicitudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty campSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="campSolicitud.campSolicitud"/>
    </c:if>
    <c:if test="${not empty campSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="${campSolicitudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${campSolicitudForm.nou?'genapp.createtitle':(campSolicitudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty campSolicitudForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(campSolicitudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(campSolicitudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${campSolicitudForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
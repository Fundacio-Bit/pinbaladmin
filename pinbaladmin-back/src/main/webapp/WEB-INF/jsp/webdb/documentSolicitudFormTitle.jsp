<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(documentSolicitudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(documentSolicitudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty documentSolicitudForm.titleCode}">
    <fmt:message key="${documentSolicitudForm.titleCode}" >
      <fmt:param value="${documentSolicitudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty documentSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="documentSolicitud.documentSolicitud"/>
    </c:if>
    <c:if test="${not empty documentSolicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="${documentSolicitudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${documentSolicitudForm.nou?'genapp.createtitle':(documentSolicitudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty documentSolicitudForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(documentSolicitudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(documentSolicitudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${documentSolicitudForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
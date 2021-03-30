<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(solicitudForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(solicitudForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty solicitudForm.titleCode}">
    <fmt:message key="${solicitudForm.titleCode}" >
      <fmt:param value="${solicitudForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty solicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="solicitud.solicitud"/>
    </c:if>
    <c:if test="${not empty solicitudForm.entityNameCode}">
      <fmt:message var="entityname" key="${solicitudForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${solicitudForm.nou?'genapp.createtitle':(solicitudForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty solicitudForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(solicitudForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(solicitudForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${solicitudForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
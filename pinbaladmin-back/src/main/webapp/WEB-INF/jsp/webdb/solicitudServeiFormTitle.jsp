<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(solicitudServeiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(solicitudServeiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty solicitudServeiForm.titleCode}">
    <fmt:message key="${solicitudServeiForm.titleCode}" >
      <fmt:param value="${solicitudServeiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty solicitudServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="solicitudServei.solicitudServei"/>
    </c:if>
    <c:if test="${not empty solicitudServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="${solicitudServeiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${solicitudServeiForm.nou?'genapp.createtitle':(solicitudServeiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty solicitudServeiForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(solicitudServeiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(solicitudServeiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${solicitudServeiForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
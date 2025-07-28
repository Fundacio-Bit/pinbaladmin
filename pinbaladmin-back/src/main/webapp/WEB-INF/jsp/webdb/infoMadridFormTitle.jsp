<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(infoMadridForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(infoMadridForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty infoMadridForm.titleCode}">
    <fmt:message key="${infoMadridForm.titleCode}" >
      <fmt:param value="${infoMadridForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty infoMadridForm.entityNameCode}">
      <fmt:message var="entityname" key="infoMadrid.infoMadrid"/>
    </c:if>
    <c:if test="${not empty infoMadridForm.entityNameCode}">
      <fmt:message var="entityname" key="${infoMadridForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${infoMadridForm.nou?'genapp.createtitle':(infoMadridForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty infoMadridForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(infoMadridForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(infoMadridForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${infoMadridForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitAPersAutForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitAPersAutForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitAPersAutForm.titleCode}">
    <fmt:message key="${tramitAPersAutForm.titleCode}" >
      <fmt:param value="${tramitAPersAutForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitAPersAutForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitAPersAut.tramitAPersAut"/>
    </c:if>
    <c:if test="${not empty tramitAPersAutForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitAPersAutForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitAPersAutForm.nou?'genapp.createtitle':(tramitAPersAutForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitAPersAutForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitAPersAutForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitAPersAutForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitAPersAutForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
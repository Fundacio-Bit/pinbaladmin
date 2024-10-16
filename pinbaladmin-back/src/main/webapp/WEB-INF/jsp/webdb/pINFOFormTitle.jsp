<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pINFOForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pINFOForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pINFOForm.titleCode}">
    <fmt:message key="${pINFOForm.titleCode}" >
      <fmt:param value="${pINFOForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pINFOForm.entityNameCode}">
      <fmt:message var="entityname" key="pINFO.pINFO"/>
    </c:if>
    <c:if test="${not empty pINFOForm.entityNameCode}">
      <fmt:message var="entityname" key="${pINFOForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pINFOForm.nou?'genapp.createtitle':(pINFOForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pINFOForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pINFOForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pINFOForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pINFOForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
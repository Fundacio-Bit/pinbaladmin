<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(documentEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(documentEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty documentEntitatForm.titleCode}">
    <fmt:message key="${documentEntitatForm.titleCode}" >
      <fmt:param value="${documentEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty documentEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="documentEntitat.documentEntitat"/>
    </c:if>
    <c:if test="${not empty documentEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${documentEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${documentEntitatForm.nou?'genapp.createtitle':(documentEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty documentEntitatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(documentEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(documentEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${documentEntitatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
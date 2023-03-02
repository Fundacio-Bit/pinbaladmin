<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(serveiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(serveiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty serveiForm.titleCode}">
    <fmt:message key="${serveiForm.titleCode}" >
      <fmt:param value="${serveiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty serveiForm.entityNameCode}">
      <fmt:message var="entityname" key="servei.servei"/>
    </c:if>
    <c:if test="${not empty serveiForm.entityNameCode}">
      <fmt:message var="entityname" key="${serveiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${serveiForm.nou?'genapp.createtitle':(serveiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty serveiForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(serveiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(serveiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${serveiForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
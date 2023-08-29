<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitHProcForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitHProcForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitHProcForm.titleCode}">
    <fmt:message key="${tramitHProcForm.titleCode}" >
      <fmt:param value="${tramitHProcForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitHProcForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitHProc.tramitHProc"/>
    </c:if>
    <c:if test="${not empty tramitHProcForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitHProcForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitHProcForm.nou?'genapp.createtitle':(tramitHProcForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitHProcForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitHProcForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitHProcForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitHProcForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
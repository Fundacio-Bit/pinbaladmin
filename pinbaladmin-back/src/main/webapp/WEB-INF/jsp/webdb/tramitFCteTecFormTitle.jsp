<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitFCteTecForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitFCteTecForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitFCteTecForm.titleCode}">
    <fmt:message key="${tramitFCteTecForm.titleCode}" >
      <fmt:param value="${tramitFCteTecForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitFCteTecForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitFCteTec.tramitFCteTec"/>
    </c:if>
    <c:if test="${not empty tramitFCteTecForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitFCteTecForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitFCteTecForm.nou?'genapp.createtitle':(tramitFCteTecForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitFCteTecForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitFCteTecForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitFCteTecForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitFCteTecForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
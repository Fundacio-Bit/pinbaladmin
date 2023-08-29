<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tramitIServForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tramitIServForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tramitIServForm.titleCode}">
    <fmt:message key="${tramitIServForm.titleCode}" >
      <fmt:param value="${tramitIServForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tramitIServForm.entityNameCode}">
      <fmt:message var="entityname" key="tramitIServ.tramitIServ"/>
    </c:if>
    <c:if test="${not empty tramitIServForm.entityNameCode}">
      <fmt:message var="entityname" key="${tramitIServForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tramitIServForm.nou?'genapp.createtitle':(tramitIServForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tramitIServForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tramitIServForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tramitIServForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tramitIServForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
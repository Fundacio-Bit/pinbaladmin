<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(estatTiquetForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(estatTiquetForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty estatTiquetForm.titleCode}">
    <fmt:message key="${estatTiquetForm.titleCode}" >
      <fmt:param value="${estatTiquetForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty estatTiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="estatTiquet.estatTiquet"/>
    </c:if>
    <c:if test="${not empty estatTiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="${estatTiquetForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${estatTiquetForm.nou?'genapp.createtitle':(estatTiquetForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty estatTiquetForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(estatTiquetForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(estatTiquetForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${estatTiquetForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
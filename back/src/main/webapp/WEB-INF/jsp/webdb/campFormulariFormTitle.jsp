<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(campFormulariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(campFormulariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty campFormulariForm.titleCode}">
    <fmt:message key="${campFormulariForm.titleCode}" >
      <fmt:param value="${campFormulariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty campFormulariForm.entityNameCode}">
      <fmt:message var="entityname" key="campFormulari.campFormulari"/>
    </c:if>
    <c:if test="${not empty campFormulariForm.entityNameCode}">
      <fmt:message var="entityname" key="${campFormulariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${campFormulariForm.nou?'genapp.createtitle':(campFormulariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty campFormulariForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(campFormulariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(campFormulariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${campFormulariForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
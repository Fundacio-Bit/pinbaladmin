<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(modificacioSoliServForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(modificacioSoliServForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty modificacioSoliServForm.titleCode}">
    <fmt:message key="${modificacioSoliServForm.titleCode}" >
      <fmt:param value="${modificacioSoliServForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty modificacioSoliServForm.entityNameCode}">
      <fmt:message var="entityname" key="modificacioSoliServ.modificacioSoliServ"/>
    </c:if>
    <c:if test="${not empty modificacioSoliServForm.entityNameCode}">
      <fmt:message var="entityname" key="${modificacioSoliServForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${modificacioSoliServForm.nou?'genapp.createtitle':(modificacioSoliServForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty modificacioSoliServForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(modificacioSoliServForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(modificacioSoliServForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${modificacioSoliServForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
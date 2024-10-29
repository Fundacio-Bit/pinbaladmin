<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pinfoForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pinfoForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pinfoForm.titleCode}">
    <fmt:message key="${pinfoForm.titleCode}" >
      <fmt:param value="${pinfoForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pinfoForm.entityNameCode}">
      <fmt:message var="entityname" key="pinfo.pinfo"/>
    </c:if>
    <c:if test="${not empty pinfoForm.entityNameCode}">
      <fmt:message var="entityname" key="${pinfoForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pinfoForm.nou?'genapp.createtitle':(pinfoForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pinfoForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pinfoForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pinfoForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pinfoForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
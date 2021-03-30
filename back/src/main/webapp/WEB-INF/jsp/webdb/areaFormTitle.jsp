<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(areaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(areaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty areaForm.titleCode}">
    <fmt:message key="${areaForm.titleCode}" >
      <fmt:param value="${areaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty areaForm.entityNameCode}">
      <fmt:message var="entityname" key="area.area"/>
    </c:if>
    <c:if test="${not empty areaForm.entityNameCode}">
      <fmt:message var="entityname" key="${areaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${areaForm.nou?'genapp.createtitle':(areaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty areaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(areaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(areaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${areaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
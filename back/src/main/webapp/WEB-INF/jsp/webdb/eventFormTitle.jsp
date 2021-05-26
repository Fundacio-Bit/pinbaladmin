<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(eventForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(eventForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty eventForm.titleCode}">
    <fmt:message key="${eventForm.titleCode}" >
      <fmt:param value="${eventForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty eventForm.entityNameCode}">
      <fmt:message var="entityname" key="event.event"/>
    </c:if>
    <c:if test="${not empty eventForm.entityNameCode}">
      <fmt:message var="entityname" key="${eventForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${eventForm.nou?'genapp.createtitle':(eventForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty eventForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(eventForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(eventForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${eventForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
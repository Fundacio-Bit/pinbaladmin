<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(documentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(documentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty documentForm.titleCode}">
    <fmt:message key="${documentForm.titleCode}" >
      <fmt:param value="${documentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty documentForm.entityNameCode}">
      <fmt:message var="entityname" key="document.document"/>
    </c:if>
    <c:if test="${not empty documentForm.entityNameCode}">
      <fmt:message var="entityname" key="${documentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${documentForm.nou?'genapp.createtitle':(documentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty documentForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(documentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(documentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${documentForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(emailForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(emailForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty emailForm.titleCode}">
    <fmt:message key="${emailForm.titleCode}" >
      <fmt:param value="${emailForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty emailForm.entityNameCode}">
      <fmt:message var="entityname" key="email.email"/>
    </c:if>
    <c:if test="${not empty emailForm.entityNameCode}">
      <fmt:message var="entityname" key="${emailForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${emailForm.nou?'genapp.createtitle':(emailForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty emailForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(emailForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(emailForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${emailForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
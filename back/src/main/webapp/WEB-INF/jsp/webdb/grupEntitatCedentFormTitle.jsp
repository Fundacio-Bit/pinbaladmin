<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(grupEntitatCedentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(grupEntitatCedentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty grupEntitatCedentForm.titleCode}">
    <fmt:message key="${grupEntitatCedentForm.titleCode}" >
      <fmt:param value="${grupEntitatCedentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty grupEntitatCedentForm.entityNameCode}">
      <fmt:message var="entityname" key="grupEntitatCedent.grupEntitatCedent"/>
    </c:if>
    <c:if test="${not empty grupEntitatCedentForm.entityNameCode}">
      <fmt:message var="entityname" key="${grupEntitatCedentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${grupEntitatCedentForm.nou?'genapp.createtitle':(grupEntitatCedentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty grupEntitatCedentForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(grupEntitatCedentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(grupEntitatCedentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${grupEntitatCedentForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
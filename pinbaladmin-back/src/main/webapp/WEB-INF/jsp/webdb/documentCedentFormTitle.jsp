<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(documentCedentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(documentCedentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty documentCedentForm.titleCode}">
    <fmt:message key="${documentCedentForm.titleCode}" >
      <fmt:param value="${documentCedentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty documentCedentForm.entityNameCode}">
      <fmt:message var="entityname" key="documentCedent.documentCedent"/>
    </c:if>
    <c:if test="${not empty documentCedentForm.entityNameCode}">
      <fmt:message var="entityname" key="${documentCedentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${documentCedentForm.nou?'genapp.createtitle':(documentCedentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty documentCedentForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(documentCedentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(documentCedentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${documentCedentForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
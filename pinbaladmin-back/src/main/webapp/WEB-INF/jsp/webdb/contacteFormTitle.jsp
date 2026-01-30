<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(contacteForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(contacteForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty contacteForm.titleCode}">
    <fmt:message key="${contacteForm.titleCode}" >
      <fmt:param value="${contacteForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty contacteForm.entityNameCode}">
      <fmt:message var="entityname" key="contacte.contacte"/>
    </c:if>
    <c:if test="${not empty contacteForm.entityNameCode}">
      <fmt:message var="entityname" key="${contacteForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${contacteForm.nou?'genapp.createtitle':(contacteForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty contacteForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(contacteForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(contacteForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${contacteForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
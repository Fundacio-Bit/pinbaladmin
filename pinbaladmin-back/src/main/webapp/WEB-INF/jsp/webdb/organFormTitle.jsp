<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(organForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(organForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty organForm.titleCode}">
    <fmt:message key="${organForm.titleCode}" >
      <fmt:param value="${organForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty organForm.entityNameCode}">
      <fmt:message var="entityname" key="organ.organ"/>
    </c:if>
    <c:if test="${not empty organForm.entityNameCode}">
      <fmt:message var="entityname" key="${organForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${organForm.nou?'genapp.createtitle':(organForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty organForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(organForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(organForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${organForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
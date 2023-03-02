<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(departamentForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(departamentForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty departamentForm.titleCode}">
    <fmt:message key="${departamentForm.titleCode}" >
      <fmt:param value="${departamentForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty departamentForm.entityNameCode}">
      <fmt:message var="entityname" key="departament.departament"/>
    </c:if>
    <c:if test="${not empty departamentForm.entityNameCode}">
      <fmt:message var="entityname" key="${departamentForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${departamentForm.nou?'genapp.createtitle':(departamentForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty departamentForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(departamentForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(departamentForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${departamentForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
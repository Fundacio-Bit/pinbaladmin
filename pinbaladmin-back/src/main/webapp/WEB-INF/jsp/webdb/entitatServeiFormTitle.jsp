<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(entitatServeiForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(entitatServeiForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty entitatServeiForm.titleCode}">
    <fmt:message key="${entitatServeiForm.titleCode}" >
      <fmt:param value="${entitatServeiForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty entitatServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="entitatServei.entitatServei"/>
    </c:if>
    <c:if test="${not empty entitatServeiForm.entityNameCode}">
      <fmt:message var="entityname" key="${entitatServeiForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${entitatServeiForm.nou?'genapp.createtitle':(entitatServeiForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty entitatServeiForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(entitatServeiForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(entitatServeiForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${entitatServeiForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
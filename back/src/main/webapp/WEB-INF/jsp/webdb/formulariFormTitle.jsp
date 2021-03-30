<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(formulariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(formulariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty formulariForm.titleCode}">
    <fmt:message key="${formulariForm.titleCode}" >
      <fmt:param value="${formulariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty formulariForm.entityNameCode}">
      <fmt:message var="entityname" key="formulari.formulari"/>
    </c:if>
    <c:if test="${not empty formulariForm.entityNameCode}">
      <fmt:message var="entityname" key="${formulariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${formulariForm.nou?'genapp.createtitle':(formulariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty formulariForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(formulariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(formulariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${formulariForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
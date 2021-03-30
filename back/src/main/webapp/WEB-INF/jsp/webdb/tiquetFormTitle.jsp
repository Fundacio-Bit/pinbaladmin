<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(tiquetForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tiquetForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tiquetForm.titleCode}">
    <fmt:message key="${tiquetForm.titleCode}" >
      <fmt:param value="${tiquetForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="tiquet.tiquet"/>
    </c:if>
    <c:if test="${not empty tiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="${tiquetForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tiquetForm.nou?'genapp.createtitle':(tiquetForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty tiquetForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tiquetForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tiquetForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tiquetForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(operadorForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(operadorForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty operadorForm.titleCode}">
    <fmt:message key="${operadorForm.titleCode}" >
      <fmt:param value="${operadorForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty operadorForm.entityNameCode}">
      <fmt:message var="entityname" key="operador.operador"/>
    </c:if>
    <c:if test="${not empty operadorForm.entityNameCode}">
      <fmt:message var="entityname" key="${operadorForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${operadorForm.nou?'genapp.createtitle':(operadorForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty operadorForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(operadorForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(operadorForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${operadorForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>
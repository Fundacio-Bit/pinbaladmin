<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(incidenciaTecnicaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(incidenciaTecnicaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty incidenciaTecnicaForm.titleCode}">
    <fmt:message key="${incidenciaTecnicaForm.titleCode}" >
      <fmt:param value="${incidenciaTecnicaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty incidenciaTecnicaForm.entityNameCode}">
      <fmt:message var="entityname" key="incidenciaTecnica.incidenciaTecnica"/>
    </c:if>
    <c:if test="${not empty incidenciaTecnicaForm.entityNameCode}">
      <fmt:message var="entityname" key="${incidenciaTecnicaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${incidenciaTecnicaForm.nou?'genapp.createtitle':(incidenciaTecnicaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty incidenciaTecnicaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(incidenciaTecnicaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(incidenciaTecnicaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${incidenciaTecnicaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(tipusTiquetForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusTiquetForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusTiquetForm.titleCode}">
    <fmt:message key="${tipusTiquetForm.titleCode}" >
      <fmt:param value="${tipusTiquetForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusTiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusTiquet.tipusTiquet"/>
    </c:if>
    <c:if test="${not empty tipusTiquetForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusTiquetForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusTiquetForm.nou?'genapp.createtitle':(tipusTiquetForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty tipusTiquetForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusTiquetForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusTiquetForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusTiquetForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
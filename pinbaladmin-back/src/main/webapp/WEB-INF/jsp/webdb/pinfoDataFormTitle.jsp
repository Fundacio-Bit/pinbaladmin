<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(pinfoDataForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(pinfoDataForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty pinfoDataForm.titleCode}">
    <fmt:message key="${pinfoDataForm.titleCode}" >
      <fmt:param value="${pinfoDataForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty pinfoDataForm.entityNameCode}">
      <fmt:message var="entityname" key="pinfoData.pinfoData"/>
    </c:if>
    <c:if test="${not empty pinfoDataForm.entityNameCode}">
      <fmt:message var="entityname" key="${pinfoDataForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${pinfoDataForm.nou?'genapp.createtitle':(pinfoDataForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty pinfoDataForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(pinfoDataForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(pinfoDataForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${pinfoDataForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tramitJConsent" cssClass="form-search"  modelAttribute="tramitJConsentFilterForm" 
        method="${(empty method)?'post':method}"  enctype="multipart/form-data">

  <%@include file="tramitJConsentListCommon.jsp" %>
  <div id="${formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="tramitJConsentListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tramitJConsentListSubtitle.jsp" %>
  <%@include file="tramitJConsentListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tramitJConsentListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tramitJConsentListCore.jsp" %>
  <c:if test="${not empty tramitJConsentItems && __theFilterForm.footerListVisible}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tramitJConsentListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    


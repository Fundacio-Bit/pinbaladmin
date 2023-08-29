<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tramitBDadesSoli" cssClass="form-search"  modelAttribute="tramitBDadesSoliFilterForm" 
        method="${(empty method)?'post':method}"  enctype="multipart/form-data">

  <%@include file="tramitBDadesSoliListCommon.jsp" %>
  <div id="${formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="tramitBDadesSoliListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tramitBDadesSoliListSubtitle.jsp" %>
  <%@include file="tramitBDadesSoliListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tramitBDadesSoliListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tramitBDadesSoliListCore.jsp" %>
  <c:if test="${not empty tramitBDadesSoliItems && __theFilterForm.footerListVisible}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tramitBDadesSoliListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    


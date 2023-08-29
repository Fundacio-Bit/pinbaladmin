<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tramitGDadesTit" cssClass="form-search"  modelAttribute="tramitGDadesTitFilterForm" 
        method="${(empty method)?'post':method}"  enctype="multipart/form-data">

  <%@include file="tramitGDadesTitListCommon.jsp" %>
  <div id="${formName}_listheader" class="filterLine lead" style="margin-bottom:10px">
    <%@include file="tramitGDadesTitListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tramitGDadesTitListSubtitle.jsp" %>
  <%@include file="tramitGDadesTitListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tramitGDadesTitListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tramitGDadesTitListCore.jsp" %>
  <c:if test="${not empty tramitGDadesTitItems && __theFilterForm.footerListVisible}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tramitGDadesTitListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    


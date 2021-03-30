<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CampSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.CampSolicitudFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampSolicitudFields.CAMPSOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,CampSolicitudFields.CAMPSOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampSolicitudFields.CAMPFORMULARIID)}">
        <th>${pad:getSortIcons(__theFilterForm,CampSolicitudFields.CAMPFORMULARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampSolicitudFields.SOLICITUDSERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,CampSolicitudFields.SOLICITUDSERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,CampSolicitudFields.VALOR)}">
        <th>${pad:getSortIcons(__theFilterForm,CampSolicitudFields.VALOR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


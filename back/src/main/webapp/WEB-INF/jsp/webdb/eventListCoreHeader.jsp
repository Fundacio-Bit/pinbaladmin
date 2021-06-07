<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EventFields" className="org.fundaciobit.pinbaladmin.model.fields.EventFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.EVENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.EVENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.SOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.SOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.INCIDENCIATECNICAID)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.INCIDENCIATECNICAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.DATAEVENT)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.DATAEVENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.TIPUS)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.PERSONA)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.PERSONA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.COMENTARI)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.COMENTARI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.FITXERID)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EventFields.NOLLEGIT)}">
        <th>${pad:getSortIcons(__theFilterForm,EventFields.NOLLEGIT)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


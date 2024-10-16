<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PinfoDataFields" className="org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.PINFODATAID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.PINFODATAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.PINFOID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.PINFOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.ESTAT)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.USUARIID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.USUARIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.PROCEDIMENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.PROCEDIMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.SERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.SERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PinfoDataFields.ALTA)}">
        <th>${pad:getSortIcons(__theFilterForm,PinfoDataFields.ALTA)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


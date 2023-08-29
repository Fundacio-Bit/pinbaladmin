<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitBDadesSoliFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitBDadesSoliFields.DADESSOLIID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitBDadesSoliFields.DADESSOLIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitBDadesSoliFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitBDadesSoliFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitBDadesSoliFields.TIPUSSOLICITUD)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitBDadesSoliFields.TIPUSSOLICITUD)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitBDadesSoliFields.ENTORN)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitBDadesSoliFields.ENTORN)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


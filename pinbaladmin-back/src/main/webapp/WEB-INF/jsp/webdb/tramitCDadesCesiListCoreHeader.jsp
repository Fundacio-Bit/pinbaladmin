<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitCDadesCesiFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.DADESCESIID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.DADESCESIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.ORGANID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.ORGANID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.ORGANARRELID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.ORGANARRELID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.DENOMINACIO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.DENOMINACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.RESPONSABLE)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.RESPONSABLE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.DIR3RESPONSABLE)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.DIR3RESPONSABLE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.DIR3ARREL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.DIR3ARREL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.DIRECCIO)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.DIRECCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.CODIPOSTAL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.CODIPOSTAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitCDadesCesiFields.MUNICIPI)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitCDadesCesiFields.MUNICIPI)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


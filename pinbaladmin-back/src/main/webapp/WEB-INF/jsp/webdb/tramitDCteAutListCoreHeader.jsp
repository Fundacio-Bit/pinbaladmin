<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitDCteAutFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.CTEAUTID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.CTEAUTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.CARREC)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.CARREC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.TELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.TELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitDCteAutFields.MAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitDCteAutFields.MAIL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


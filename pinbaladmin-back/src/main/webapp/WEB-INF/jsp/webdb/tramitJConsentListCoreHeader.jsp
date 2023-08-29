<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitJConsentFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitJConsentFields.CONSENTID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitJConsentFields.CONSENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitJConsentFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitJConsentFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitJConsentFields.ADJUNTID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitJConsentFields.ADJUNTID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


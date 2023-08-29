<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TramitECteAudFields" className="org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.CTEAUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.CTEAUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.TRAMITID)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.TRAMITID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.NIF)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.NIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.NOM)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.LLINATGE1)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.LLINATGE1)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.LLINATGE2)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.LLINATGE2)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.CARREC)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.CARREC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.TELEFON)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.TELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,TramitECteAudFields.MAIL)}">
        <th>${pad:getSortIcons(__theFilterForm,TramitECteAudFields.MAIL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>


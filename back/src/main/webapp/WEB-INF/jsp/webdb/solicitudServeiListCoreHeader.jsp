<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="SolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SOLICITUDID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.SOLICITUDID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.SERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.SERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.ESTATSOLICITUDSERVEIID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NORMALEGAL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.NORMALEGAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZNORMALEGAL)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.ENLLAZNORMALEGAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ARTICLES)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.ARTICLES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.TIPUSCONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.TIPUSCONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.CONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.CONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.ENLLAZCONSENTIMENT)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.ENLLAZCONSENTIMENT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,SolicitudServeiFields.NOTES)}">
        <th>${pad:getSortIcons(__theFilterForm,SolicitudServeiFields.NOTES)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${pad:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

